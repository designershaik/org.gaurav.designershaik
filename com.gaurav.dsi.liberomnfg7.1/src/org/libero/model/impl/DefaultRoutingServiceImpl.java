/**
 * Licensed under the KARMA v.1 Law of Sharing. As others have shared freely to you, so shall you share freely back to us.
 * If you shall try to cheat and find a loophole in this license, then KARMA will exact your share.
 * and your worldly gain shall come to naught and those who share shall gain eventually above you.
 * In compliance with previous GPLv2.0 works of ComPiere USA, eEvolution MEXICO, iDempiere contributors and Mutlimage SLOVAKIA
 */
package org.libero.model.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_WF_Node;
import org.compiere.model.I_AD_Workflow;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_S_Resource;
import org.compiere.model.MProcess;
import org.compiere.model.MResource;
import org.compiere.model.MResourceAssignment;
import org.compiere.model.MResourceType;
import org.compiere.model.MTable;
import org.compiere.model.MUOM;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Workflow;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.wf.MWFActivity;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFProcess;
import org.compiere.wf.MWorkflow;
import org.libero.model.MPPMRP;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderNode;
import org.libero.model.RoutingService;
import org.libero.tables.I_PP_Cost_Collector;
import org.libero.tables.I_PP_Order_Node;

/**
 * Default Routing Service Implementation
 * @author Teo Sarca
 */
public class DefaultRoutingServiceImpl implements RoutingService
{
	private final CLogger log = CLogger.getCLogger(getClass());
	
	private Timestamp startAssignTime;

	public BigDecimal estimateWorkingTime(I_AD_WF_Node node)
	{
		final double duration;
		if (node.getUnitsCycles().signum() == 0)
		{
			duration = node.getDuration();
		}
		else
		{
			duration = node.getDuration() / node.getUnitsCycles().doubleValue();
		}
		return BigDecimal.valueOf(duration);
	}
	public BigDecimal estimateWorkingTime(I_PP_Order_Node node, BigDecimal qty)
	{
		double unitDuration = node.getDuration();
		double cycles = calculateCycles(node.getUnitsCycles(), qty);
		BigDecimal duration = BigDecimal.valueOf(unitDuration * cycles);
		return duration;
	}
	
	public BigDecimal estimateWorkingTime(I_PP_Cost_Collector cc)
	{
		final String trxName = (cc instanceof PO ? ((PO)cc).get_TrxName() : null);
		final BigDecimal qty = cc.getMovementQty();
		MPPOrderNode node = MPPOrderNode.get(Env.getCtx(), cc.getPP_Order_Node_ID(), trxName);
		return estimateWorkingTime(node, qty);
	}

	
	/**
	 * Calculate how many cycles are needed for given qty and units per cycle
	 * @param unitsCycle
	 * @param qty
	 * @return number of cycles
	 */
	protected int calculateCycles(int unitsCycle, BigDecimal qty)
	{
		BigDecimal cycles = qty;
		BigDecimal unitsCycleBD = BigDecimal.valueOf(unitsCycle);
		if (unitsCycleBD.signum() > 0)
		{
			cycles = qty.divide(unitsCycleBD, 0, RoundingMode.UP);
		}
		return cycles.intValue();
	}
	
	/**
	 * Calculate node duration in DurationUnit UOM (see AD_Workflow.DurationUnit)
	 * @param node
	 * @param setupTime setup time (workflow duration unit)
	 * @param durationTotal (workflow duration unit)
	 * @reeturn duration
	 */
	protected BigDecimal calculateDuration(I_AD_WF_Node node, I_PP_Cost_Collector cc)
	{
		if (node == null)
		{
			node = cc.getPP_Order_Node().getAD_WF_Node();
		}
		final I_AD_Workflow workflow = node.getAD_Workflow();
		final double batchSize = workflow.getQtyBatchSize().doubleValue();
		final double setupTime;
		final double duration;
		if (cc != null)
		{
			setupTime = cc.getSetupTimeReal().doubleValue();
			duration = cc.getDurationReal().doubleValue();
		}
		else
		{
			setupTime = node.getSetupTime();
			// Estimate total duration for 1 unit of final product as duration / units cycles
			duration = estimateWorkingTime(node).doubleValue(); 
		}
		
		double totalDuration;
		if(batchSize > 0)
			totalDuration = ((setupTime / batchSize) + duration);
		else
			totalDuration = setupTime  + duration;
		
		return BigDecimal.valueOf(totalDuration);
	}
	
	public BigDecimal calculateDuration(I_AD_WF_Node node)
	{
		return calculateDuration(node, null);
	}
	public BigDecimal calculateDuration(I_PP_Cost_Collector cc)
	{
		return calculateDuration(getAD_WF_Node(cc), cc);
	}

	/** 
	 * red1 - to set ResourceAssignment same time and pass values Start/End TimeStamp
	 *  also each node has its own Resource! and later product can also have WF!
	 *  Duration Unit must synch with Resource base time unit (min/hour/day/week).
	 */
	public BigDecimal calculateDuration(MPPMRP mrp, I_AD_Workflow wf, I_S_Resource plant, BigDecimal qty)
	{
		if (plant == null)
			return Env.ZERO;
		final Properties ctx = mrp.getCtx();
		//final MResourceType S_ResourceType = MResourceType.get(ctx, plant.getS_ResourceType_ID());  	

		Double nodeTotal = 0.0;
		double durationTotal = 0.0; 
		BigDecimal duration = Env.ZERO;
		MWFNode[] nodes = ((MWorkflow)wf).getNodes(false, Env.getAD_Client_ID(ctx));
		//going thru each node that has own resource to accumulate its duration and trigger its Activity and Schedule booking
		for (I_AD_WF_Node node : nodes)
		{
			//get the Node's Resource, then calculate the duration of its use
			MResourceType S_ResourceType = MResourceType.get(ctx, plant.getS_ResourceType_ID());
			BigDecimal AvailableDayTime  = BigDecimal.valueOf(S_ResourceType.getTimeSlotHours());
			int AvailableDays = S_ResourceType.getAvailableDaysWeek();
			double durationBaseSec = getDurationBaseSec(wf.getDurationUnit());// 1 hour = 3600 seconds

			// Qty independent times:
			nodeTotal += node.getQueuingTime();
			nodeTotal += node.getSetupTime();
			nodeTotal += node.getWaitingTime();
			nodeTotal += node.getMovingTime();
			
			// Get OverlapUnits - number of units that must be completed before they are moved the next activity 
			double overlapUnits = qty.doubleValue();
			if (node.getOverlapUnits() > 0 && node.getOverlapUnits() < overlapUnits)
			{
				overlapUnits = node.getOverlapUnits();
			}
			double durationBeforeOverlap = node.getDuration() * overlapUnits;
			durationTotal = nodeTotal;
			durationTotal += durationBeforeOverlap;
			
			//red1 do Activity thread
			createWFActivity(mrp, wf);
			
			BigDecimal requiredTime = BigDecimal.valueOf(durationTotal * durationBaseSec / 60);// removed / 60 to retain minutes for adding later.
			// TODO: implement here, Victor's suggestion - https://sourceforge.net/forum/message.php?msg_id=5179460
			//  " : cannot find the above thread.

			// Weekly Factor  	-- red1 made adjustments according to my understanding that all in minutes base
			BigDecimal DayTime = BigDecimal.valueOf(24); // red1 done this as a factor instead of a divisor
			AvailableDayTime = AvailableDayTime.divide(DayTime);// red1 done this as a factor instead of a divisor
			BigDecimal WeeklyFactor = BigDecimal.valueOf(7).divide(BigDecimal.valueOf(AvailableDays), 8, RoundingMode.UP);
			duration = (requiredTime.multiply(WeeklyFactor)).divide(AvailableDayTime, 0, RoundingMode.UP); //
			
			MResourceAssignment ra = createResourceAssign(mrp, ctx, duration,
					node);
			
			//set start end times to MRP
			mrp.setDateStartSchedule(startAssignTime);
			mrp.setDateFinishSchedule(ra.getAssignDateTo());
			mrp.saveEx(mrp.get_TrxName());
			//red1 -- end
		} 
		return duration;
	}
	/**
	 * Display plant/resource/machine defined in MfgWF at their duration on the InfoSchedule View
	 * Each new assignment of same resource will be placed subsequent times.
	 * No checking of ResourceType Available Slots i.e. assumed open 24/7 TODO
	 * Thereafter they have to be managed manually or programmatically (future version as MPS)
	 * @param mrp
	 * @param ctx
	 * @param duration
	 * @param node
	 * @return
	 */
	private MResourceAssignment createResourceAssign(MPPMRP mrp,
			final Properties ctx, BigDecimal duration, I_AD_WF_Node node) {
		//red1 -- set ResourceAssignment.. first get lastAssignTime
		MResourceAssignment resourceschedule = new Query(Env.getCtx(), MResourceAssignment.Table_Name, MResourceAssignment.COLUMNNAME_S_Resource_ID+"=?", null)
		.setParameters(node.getS_Resource_ID()) 
		.first();
		MResourceAssignment ra;				
		java.util.Date date= new java.util.Date();
		startAssignTime = new Timestamp(date.getTime());
		if (resourceschedule!=null){
			if (resourceschedule.getName().equals("MRP:"+mrp.get_ID()+" Order:"+mrp.getC_Order().getDocumentNo())){
				ra = resourceschedule;
			}					
			else{	
				startAssignTime = resourceschedule.getAssignDateTo();
				ra = new MResourceAssignment(ctx, 0, mrp.get_TrxName());
			}			
		}
		else {
			ra = new MResourceAssignment(ctx, 0, mrp.get_TrxName());
		}
		ra.setAD_Org_ID(mrp.getAD_Org_ID()); 
		ra.setName("MRP:"+mrp.get_ID()+" Order:"+mrp.getC_Order().getDocumentNo());
		ra.setAssignDateFrom(startAssignTime);
		ra.setAssignDateTo(TimeUtil.addMinutess(startAssignTime, duration.intValueExact())); 
		//TODO red1 in future to check ResourceType.isDayAvailable and move balance after.
		ra.setS_Resource_ID(node.getS_Resource_ID());
		ra.setDescription(mrp.getC_OrderLine().getM_Product().getName()+" "+mrp.getC_OrderLine().getQtyOrdered());
		ra.saveEx(mrp.get_TrxName());
		return ra;
	}
	/**
	 * Only first level nodes WFActivity created as main. Processing them should trigger the rest
	 * acting as visual guide to shopfloor operations what WFActivities are outstanding.
	 * For what resources/plants/machine are in use in respective Production Schedule, refer createResourceAssignment
	 * Processing WFActivity will refer only attached PPMRP.processIt(). 
	 * No impact with PP_Order, S_ResourceAssignment or MRP data.
	 * @param mrp
	 * @param wf
	 */
	private void createWFActivity(MPPMRP mrp, I_AD_Workflow wf) {
		//WF must have Action=UserWindow, Window=MRP, Table hardcoded below. WFState=OS-Suspended for it to appear in Dashboard
		if (wf != null)
			try { 
				int Record_ID = mrp.get_ID(); 
				MWFActivity act = new Query(Env.getCtx(),MWFActivity.Table_Name,MWFActivity.COLUMNNAME_Record_ID+"=?",mrp.get_TrxName())
				.setParameters(Record_ID)
				.first();
				if (act!=null) {
					if (act.getWFState().equals(MWFActivity.WFSTATE_Suspended))
						act.delete(true); //red1 delete similar thread before update
					else log.severe("Workflow Activity Was Created and Processed Before This!"); 
				}
				int Table_ID = MPPOrder.Table_ID; 
				//process MFG_WF_Activity is pre-defined for this specific purpose
				int AD_Process_ID = MProcess.getProcess_ID("MFG_WF_Activity", mrp.get_TrxName());									
				//hard set MRP ID to WF
				PO po = mrp;
				wf.setAD_Table_ID(po.get_Table_ID());
				//create WFProcess
				ProcessInfo pi = new ProcessInfo(wf.getName(), AD_Process_ID, Table_ID, Record_ID);		
				pi.setTransactionName(mrp.get_TrxName());;
				pi.setPO(po);
				MWFProcess wfProcess = new MWFProcess((MWorkflow) wf, pi, mrp.get_TrxName());			
				wfProcess.startWork();
						 
			} catch (Exception e) {
				log.warning("Workflow Activity failed to work");
		}
	}

	protected BigDecimal convertDurationToResourceUOM(BigDecimal duration, int S_Resource_ID, I_AD_WF_Node node)
	{
		MResource resource = MResource.get(Env.getCtx(), S_Resource_ID);
		I_AD_Workflow wf = MWorkflow.get(Env.getCtx(), node.getAD_Workflow_ID());
		I_C_UOM resourceUOM = MUOM.get(Env.getCtx(), resource.getC_UOM_ID());
		return convertDuration(duration, wf.getDurationUnit(), resourceUOM);
	}
	
	@Override
	public BigDecimal getResourceBaseValue(int S_Resource_ID, I_PP_Cost_Collector cc)
	{
		return getResourceBaseValue(S_Resource_ID, null, cc);
	}
	@Override
	public BigDecimal getResourceBaseValue(int S_Resource_ID, I_AD_WF_Node node)
	{
		return getResourceBaseValue(S_Resource_ID, node, null);
	}
	protected BigDecimal getResourceBaseValue(int S_Resource_ID, I_AD_WF_Node node, I_PP_Cost_Collector cc)
	{
		if (node == null)
			node = cc.getPP_Order_Node().getAD_WF_Node();
		final Properties ctx = (node instanceof PO ? ((PO)node).getCtx() : Env.getCtx());
		final MResource resource = MResource.get(ctx, S_Resource_ID);
		final MUOM resourceUOM = MUOM.get(ctx, resource.getC_UOM_ID());
		//
		if (isTime(resourceUOM))
		{
			BigDecimal duration = calculateDuration(node, cc);
			I_AD_Workflow wf = MWorkflow.get(ctx, node.getAD_Workflow_ID());
			BigDecimal convertedDuration = convertDuration(duration, wf.getDurationUnit(), resourceUOM);
			return convertedDuration;
		}
		else
		{
			throw new AdempiereException("@NotSupported@ @C_UOM_ID@ - "+resourceUOM);
		}
	}

	protected I_AD_WF_Node getAD_WF_Node(I_PP_Cost_Collector cc)
	{
		I_PP_Order_Node activity = cc.getPP_Order_Node();
		return activity.getAD_WF_Node();
	}
	
	/**
	 * Convert durationUnit to seconds
	 * @param durationUnit
	 * @return duration in seconds
	 */
	public long getDurationBaseSec (String durationUnit)
	{
		if (durationUnit == null)
			return 0;
		else if (X_AD_Workflow.DURATIONUNIT_Second.equals(durationUnit))
			return 1;
		else if (X_AD_Workflow.DURATIONUNIT_Minute.equals(durationUnit))
			return 60;
		else if (X_AD_Workflow.DURATIONUNIT_Hour.equals(durationUnit))
			return 3600;
		else if (X_AD_Workflow.DURATIONUNIT_Day.equals(durationUnit))
			return 86400;
		else if (X_AD_Workflow.DURATIONUNIT_Month.equals(durationUnit))
			return 2592000;
		else if (X_AD_Workflow.DURATIONUNIT_Year.equals(durationUnit))
			return 31536000;
		return 0;
	}	//	getDurationSec
	
	/**
	 * Convert uom to seconds
	 * @param uom time UOM 
	 * @return duration in seconds
	 * @throws AdempiereException if UOM is not supported
	 */
	public long getDurationBaseSec(I_C_UOM uom)
	{
		MUOM uomImpl = (MUOM)uom;
		//
		if(uomImpl.isWeek())
		{
			return 60*60*24*7;
		}
		if(uomImpl.isDay())
		{
			return 60*60*24;
		}
		else if (uomImpl.isHour())
		{
			return 60*60;
		}
		else if (uomImpl.isMinute())
		{
			return 60;
		}
		else if (uomImpl.isSecond())
		{
			return 1;
		}
		else
		{
			throw new AdempiereException("@NotSupported@ @C_UOM_ID@="+uom.getName());
		}
	}
	
	/**
	 * Check if it's an UOM that measures time 
	 * @param uom 
	 * @return true if is time UOM
	 */
	public boolean isTime(I_C_UOM uom)
	{
		String x12de355 = uom.getX12DE355();
		return MUOM.X12_SECOND.equals(x12de355)
		|| MUOM.X12_MINUTE.equals(x12de355)
		|| MUOM.X12_HOUR.equals(x12de355)
		|| MUOM.X12_DAY.equals(x12de355)
		|| MUOM.X12_DAY_WORK.equals(x12de355)
		|| MUOM.X12_WEEK.equals(x12de355)
		|| MUOM.X12_MONTH.equals(x12de355)
		|| MUOM.X12_MONTH_WORK.equals(x12de355)
		|| MUOM.X12_YEAR.equals(x12de355)
		;
	}
	
	/**
	 * Convert duration from given UOM to given UOM
	 * @param duration
	 * @param fromDurationUnit duration UOM
	 * @param toUOM target UOM
	 * @return duration converted to toUOM
	 */
	public BigDecimal convertDuration(BigDecimal duration, String fromDurationUnit, I_C_UOM toUOM)
	{
		double fromMult = getDurationBaseSec(fromDurationUnit);
		double toDiv = getDurationBaseSec(toUOM);
		BigDecimal convertedDuration = BigDecimal.valueOf(duration.doubleValue() * fromMult / toDiv);
		// Adjust scale to UOM precision
/*		int precision = toUOM.getStdPrecision();
		if (convertedDuration.scale() > precision)
			convertedDuration = convertedDuration.setScale(precision, RoundingMode.HALF_UP);*/
		//
		return convertedDuration;
	}
	@Override
	public Timestamp getStartAssignTime(){
		return startAssignTime;
	}
}
