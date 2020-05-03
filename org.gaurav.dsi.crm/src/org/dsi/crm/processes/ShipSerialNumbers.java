package org.dsi.crm.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.gaurav.dsi.model.MDSISerialNoTrx;

public class ShipSerialNumbers extends SvrProcess {
	int p_ID;
	int qtyOrderded;
	int m_ProductID;
	int m_LocatorID;
	int m_LocatorToID;
	int m_Line_ID;
	int qtyOnSrNo;
	String prefix;
	String suffix;
	String trxType;
	int srNo;
	MInOut mi;
	int p_Table_ID;
	MMovement mm;
	MInventory miv;
	String message;
	String composedSrNo = "";
	boolean oldSerialNumberFormat = false;
	MMovementLine moveLine  = null;
	int p_From_SrNumber = 0;
	int p_To_SrNumber = 0;
	@Override
	protected void prepare()
	{

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DSI_SrNo"))
			{
				p_From_SrNumber = para[i].getParameterAsInt();
				p_To_SrNumber = para[i].getParameter_ToAsInt();
			}
			else

				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		/* TableID and Table Name
		 * 323= M_Movement
		 * 319= M_InOut
		 * 321= M_Inventory
		 */
		p_ID = getRecord_ID();
		p_Table_ID=getTable_ID();
		if(p_Table_ID==MMovement.Table_ID)
		{
			mm=new MMovement(getCtx(), p_ID, get_TrxName());
		}
		if(p_Table_ID==MInOut.Table_ID)
		{
			mi=new MInOut(getCtx(), p_ID, get_TrxName());
			trxType=mi.getMovementType();
		}
		else if(p_Table_ID==MInventory.Table_ID)
		{
			miv=new MInventory(getCtx(),p_ID,get_TrxName());
			trxType=mi.getMovementType();
		}
		else if(p_Table_ID==MMovementLine.Table_ID)
		{
			moveLine=new MMovementLine(getCtx(),p_ID,get_TrxName());
			moveSerialNumberInRange();
		}

	}



	private void moveSerialNumberInRange() 
	{
	
		m_ProductID = moveLine.getM_Product_ID();
		m_LocatorID = moveLine.getM_Locator_ID();
		m_LocatorToID = moveLine.getM_LocatorTo_ID();
		qtyOrderded = moveLine.getMovementQty().intValue();
		m_Line_ID = moveLine.getM_MovementLine_ID();
		int availableSrNo = DB.getSQLValue(get_TrxName(), "Select count(*) From DSI_SerialNoTrx "
				+ "Where DSI_SrNo between ? and ? and M_Locator_ID = ? and M_Product_ID = ? ",p_From_SrNumber,p_To_SrNumber,m_LocatorID,m_ProductID);
		if(availableSrNo<moveLine.getMovementQty().intValue())
			throw new AdempiereException(Msg.getMsg(getCtx(), "DS_QuantityEnteredIsLessThanQuantityAvailable"));
		
		
		PreparedStatement pstmt=null;
		ResultSet rs1 = null;
		int totalCount;
		String sql1 = "select strx.prefix,strx.suffix, sum(strx.qty) as qty,strx.dsi_srno,strx.DS_SerialNumberFinal,strx.DS_OldSerialNumberFormat "
				+ "from dsi_serialnotrx strx "
				+ "where strx.m_product_id=? and strx.m_locator_id=? and strx.dsi_srno between ? and ? " +
				"group by strx.prefix,strx.suffix,strx.dsi_srno,strx.DS_SerialNumberFinal,strx.DS_OldSerialNumberFormat having sum(strx.qty)<>0 "
				+ "order by strx.DS_OldSerialNumberFormat Desc ,strx.dsi_srno";
		pstmt = DB.prepareStatement(sql1, get_TrxName());
		int count = 0;
		int k = 0;
		try
		{
			pstmt.setInt(1, m_ProductID);
			pstmt.setInt(2, m_LocatorID);
			pstmt.setInt(3, p_From_SrNumber);
			pstmt.setInt(4, p_To_SrNumber);
			rs1 = pstmt.executeQuery();
			while(rs1.next() && k==0) 
			{
				totalCount = count++;
				if (totalCount < qtyOrderded) 
				{
					qtyOnSrNo = rs1.getInt("qty");
					srNo = rs1.getInt("dsi_srno");
					prefix = rs1.getString("prefix");
					suffix = rs1.getString("suffix");
					composedSrNo = rs1.getString("DS_SerialNumberFinal");
					oldSerialNumberFormat = rs1.getString("DS_OldSerialNumberFormat").equals("Y") ? true:false;
					if (qtyOnSrNo != 0)
					{
						MDSISerialNoTrx msi = new MDSISerialNoTrx(getCtx(),0, get_TrxName());
						msi.setQty(new BigDecimal(-1));
						msi.setM_Product_ID(m_ProductID);
						msi.setM_Locator_ID(m_LocatorID);
						msi.setM_MovementLine_ID(m_Line_ID);
						msi.setPrefix(prefix);
						msi.setSuffix(suffix);
						msi.setDSI_SrNo(srNo);
						msi.setDS_SerialNumberFinal(composedSrNo);
						msi.setDS_OldSerialNumberFormat(oldSerialNumberFormat);
						msi.save();

						MDSISerialNoTrx msiTo = new MDSISerialNoTrx(getCtx(),0, get_TrxName());
						msiTo.setQty(new BigDecimal(1));
						msiTo.setM_Product_ID(m_ProductID);
						msiTo.setM_Locator_ID(m_LocatorToID);
						msiTo.setM_MovementLine_ID(m_Line_ID);
						msiTo.setPrefix(prefix);
						msiTo.setSuffix(suffix);
						msiTo.setDSI_SrNo(srNo);
						msiTo.setDS_SerialNumberFinal(composedSrNo);
						msi.setDS_OldSerialNumberFormat(oldSerialNumberFormat);
						msiTo.save();
					}
				}
				else
				{
					k = 1;
				}
			}
		} 
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql1, e);
		}
		finally
		{
			DB.close(rs1, pstmt);
			pstmt = null;
			rs1=null;
		}
	}



	@Override
	protected String doIt()
	{
		/*	C+	Customer Returns	
		 *  C-	Customer Shipment		
		 *  I+	Inventory In			
		 *  I-	Inventory Out
		 *  M+	Movement To			
		 *  M-	Movement From
		 *  P+	Production +	
		 *  P-	Production -	
		 *  V+	Vendor Receipts	
		 *  V-	Vendor Returns			
		 *  W+	Work Order +	
		 *  W-	Work Order -	*/


		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int token = 0;
		if(p_Table_ID==MInOut.Table_ID)
		{
			if (trxType.equalsIgnoreCase("C-") && !mi.getDocStatus().equals("VO"))
			{
				String sql = "select mline.m_product_id, mline.m_locator_id,mline.m_InOutline_ID,mline.QtyEntered "
						+ "from m_inoutline mline where mline.m_inout_id="
						+ p_ID+" and (select DISTINCT trx.m_InOutline_ID from DSI_SerialNoTrx trx where trx.m_InOutline_ID=mline.m_inoutline_id) is null order by mline.m_inoutline_id";
				pstmt = DB.prepareStatement(sql, get_TrxName());
				try
				{
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						token=1;
						m_ProductID = rs.getInt("m_product_id");
						m_LocatorID = rs.getInt("m_locator_id");
						m_Line_ID = rs.getInt("m_InOutline_ID");
						qtyOrderded = rs.getInt("QtyEntered");
						ShipSerialNumber();

					}
					if(token==1)
					{
						message="Serial Number Attached To Shipment Line";
					}
					else
					{
						message="Serial numbers are already attached for this Shipment";
					}
				} 
				catch (Exception e) 
				{
					log.log(Level.SEVERE, sql, e);
				} 
				finally
				{
					DB.close(rs, pstmt);
					pstmt = null;
					rs=null;
				}

			}
			else
				if(trxType.equalsIgnoreCase("C+"))
				{
					recieveSerialNumbers();
					message="Serial numbers are attached from Original shipment , please keep serial numbers which are received in Serial Number Tab";
				}
		}

		if(p_Table_ID==MMovement.Table_ID)
		{
			if(!mm.getDocStatus().equals("VO"))
			{
				String sql = "select mline.m_product_id, mline.m_locator_id,mline.M_LocatorTo_ID,mline.m_movementline_id,mline.MovementQty from m_movementline mline where mline.m_movement_ID="
						+ p_ID+" and (select DISTINCT trx.m_movementline_id from DSI_SerialNoTrx trx where trx.m_movementline_id=mline.m_movementline_id) is null order by mline.m_movementline_id";
				pstmt = DB.prepareStatement(sql, get_TrxName());
				try
				{
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						m_ProductID = rs.getInt("m_product_id");
						m_LocatorID = rs.getInt("m_locator_id");
						m_Line_ID = rs.getInt("m_movementline_id");
						m_LocatorToID=rs.getInt("M_LocatorTo_ID");
						qtyOrderded = rs.getInt("MovementQty");
						TransferSerialNumbers();
						message="Serial Numbers Moved , See serial numbers tab";
					}
				} 
				catch (Exception e) 
				{
					log.log(Level.SEVERE, sql, e);
				} 
				finally
				{
					DB.close(rs, pstmt);
					pstmt = null;
					rs=null;
				}
			}
		}
		if(p_Table_ID==MMovement.Table_ID)
		{

		}
		addLog("Serial Numbers are generated");
		return message;

	}


	private void ShipSerialNumber() 
	{
		PreparedStatement pstmt=null;
		ResultSet rs1 = null;
		int totalCount;
		String sql1 = "select strx.prefix,strx.suffix, sum(strx.qty) as qty,strx.dsi_srno,strx.DS_SerialNumberFinal,strx.DS_OldSerialNumberFormat "
				+ " from dsi_serialnotrx strx "
				+ "	where strx.m_product_id="+ m_ProductID+ " and strx.m_locator_id="+ m_LocatorID+""
				+ "	group by strx.prefix,strx.suffix,strx.dsi_srno,strx.DS_SerialNumberFinal,strx.DS_OldSerialNumberFormat "
				+ " having sum(strx.qty)<>0 "
				+ "	order by strx.DS_OldSerialNumberFormat desc ,strx.dsi_srno";
		pstmt = DB.prepareStatement(sql1, get_TrxName());
		int count = 0;
		int k = 0;
		try
		{
			rs1 = pstmt.executeQuery();
			while(rs1.next() && k==0) 
			{
				totalCount = count++;
				if (totalCount < qtyOrderded) 
				{
					qtyOnSrNo = rs1.getInt("qty");
					srNo = rs1.getInt("dsi_srno");
					prefix = rs1.getString("prefix");
					suffix = rs1.getString("suffix");
					composedSrNo = rs1.getString("DS_SerialNumberFinal");
					oldSerialNumberFormat = rs1.getString("DS_OldSerialNumberFormat").equals("Y") ? true:false;
					if (qtyOnSrNo != 0)
					{
						MDSISerialNoTrx msi = new MDSISerialNoTrx(getCtx(),0, get_TrxName());
						msi.setQty(new BigDecimal(-1));
						msi.setM_Product_ID(m_ProductID);
						msi.setM_Locator_ID(m_LocatorID);
						msi.setM_InOutLine_ID(m_Line_ID);
						msi.setPrefix(prefix);
						msi.setSuffix(suffix);
						msi.setDSI_SrNo(srNo);
						msi.setDS_SerialNumberFinal(composedSrNo);
						msi.setDS_OldSerialNumberFormat(oldSerialNumberFormat);
						msi.save();
					}
				}
				else
				{
					k = 1;
				}
			}
		} 
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql1, e);
		}
		finally
		{
			DB.close(rs1, pstmt);
			pstmt = null;
			rs1=null;
		}

	}
	private void recieveSerialNumbers() 
	{

		PreparedStatement pstmt=null;
		ResultSet rs1 = null;
		String sql1 = "select origshipline.m_product_id, line.m_locator_id, trx.suffix," +
				"trx.prefix,trx.dsi_srno,line.m_inoutline_id,trx.DS_SerialNumberFinal,trx.DS_OldSerialNumberFormat " +
				"from m_inout mi , m_inoutline line , " +
				"m_rmaline rma, m_inoutline origshipline, DSI_SerialNoTrx trx " +
				"where mi.m_inout_id=line.m_inout_id " +
				"and line.m_rmaline_id= rma.m_rmaline_id and" +
				" rma.m_inoutline_id= origshipline.m_inoutline_id " +
				"and  origshipline.m_inoutline_id= trx.m_inoutline_id  and mi.M_InOut_ID="+p_ID;
		pstmt = DB.prepareStatement(sql1, get_TrxName());


		try
		{
			rs1 = pstmt.executeQuery();
			while(rs1.next()) 
			{
				srNo = rs1.getInt("dsi_srno");
				prefix = rs1.getString("prefix");
				suffix = rs1.getString("suffix");
				m_ProductID=rs1.getInt("m_product_id");
				m_LocatorID=rs1.getInt("m_locator_id");
				m_Line_ID=rs1.getInt("m_inoutline_id");
				composedSrNo = rs1.getString("DS_SerialNumberFinal");
				oldSerialNumberFormat = rs1.getString("DS_OldSerialNumberFormat").equals("Y") ? true:false;
				MDSISerialNoTrx msi = new MDSISerialNoTrx(getCtx(),0, get_TrxName());
				msi.setQty(new BigDecimal(1));
				msi.setM_Product_ID(m_ProductID);
				msi.setM_Locator_ID(m_LocatorID);
				msi.setM_InOutLine_ID(m_Line_ID);
				msi.setPrefix(prefix);
				msi.setSuffix(suffix);
				msi.setDSI_SrNo(srNo);
				msi.setDS_SerialNumberFinal(composedSrNo);
				msi.setDS_OldSerialNumberFormat(oldSerialNumberFormat);
				msi.save();
			}
		} 
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql1, e);
		}
		finally
		{
			DB.close(rs1, pstmt);
			pstmt = null;
			rs1=null;
		}
	}

	private void TransferSerialNumbers() 
	{
		PreparedStatement pstmt=null;
		ResultSet rs1 = null;
		int totalCount;
		String sql1 = "select strx.prefix,strx.suffix, sum(strx.qty) as qty,strx.dsi_srno,strx.DS_SerialNumberFinal,strx.DS_OldSerialNumberFormat "
				+ "from dsi_serialnotrx strx "
				+ "where strx.m_product_id="+ m_ProductID+ " and strx.m_locator_id="+ m_LocatorID+ " " +
				"group by strx.prefix,strx.suffix,strx.dsi_srno,strx.DS_SerialNumberFinal,strx.DS_OldSerialNumberFormat having sum(strx.qty)<>0 "
				+ "order by strx.DS_OldSerialNumberFormat Desc ,strx.dsi_srno";
		pstmt = DB.prepareStatement(sql1, get_TrxName());
		int count = 0;
		int k = 0;
		try
		{
			rs1 = pstmt.executeQuery();
			while(rs1.next() && k==0) 
			{
				totalCount = count++;
				if (totalCount < qtyOrderded) 
				{
					qtyOnSrNo = rs1.getInt("qty");
					srNo = rs1.getInt("dsi_srno");
					prefix = rs1.getString("prefix");
					suffix = rs1.getString("suffix");
					composedSrNo = rs1.getString("DS_SerialNumberFinal");
					oldSerialNumberFormat = rs1.getString("DS_OldSerialNumberFormat").equals("Y") ? true:false;
					if (qtyOnSrNo != 0)
					{
						MDSISerialNoTrx msi = new MDSISerialNoTrx(getCtx(),0, get_TrxName());
						msi.setQty(new BigDecimal(-1));
						msi.setM_Product_ID(m_ProductID);
						msi.setM_Locator_ID(m_LocatorID);
						msi.setM_MovementLine_ID(m_Line_ID);
						msi.setPrefix(prefix);
						msi.setSuffix(suffix);
						msi.setDSI_SrNo(srNo);
						msi.setDS_SerialNumberFinal(composedSrNo);
						msi.setDS_OldSerialNumberFormat(oldSerialNumberFormat);
						msi.save();

						MDSISerialNoTrx msiTo = new MDSISerialNoTrx(getCtx(),0, get_TrxName());
						msiTo.setQty(new BigDecimal(1));
						msiTo.setM_Product_ID(m_ProductID);
						msiTo.setM_Locator_ID(m_LocatorToID);
						msiTo.setM_MovementLine_ID(m_Line_ID);
						msiTo.setPrefix(prefix);
						msiTo.setSuffix(suffix);
						msiTo.setDSI_SrNo(srNo);
						msiTo.setDS_SerialNumberFinal(composedSrNo);
						msi.setDS_OldSerialNumberFormat(oldSerialNumberFormat);
						msiTo.save();
					}
				}
				else
				{
					k = 1;
				}
			}
		} 
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql1, e);
		}
		finally
		{
			DB.close(rs1, pstmt);
			pstmt = null;
			rs1=null;
		}
	}
}
