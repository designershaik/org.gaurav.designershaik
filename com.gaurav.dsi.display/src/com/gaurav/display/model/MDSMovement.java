package com.gaurav.display.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import org.compiere.model.I_M_MovementLine;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.Query;

public class MDSMovement extends MMovement{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSMovement(Properties ctx, int M_Movement_ID, String trxName) {
		super(ctx, M_Movement_ID, trxName);
		
	}

	public MDSMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	public MDSMovementLine[] getLines ()
	{
		MDSMovementLine[]		m_lines = null;
		
		final String whereClause = "M_Movement_ID=?";
		List<MDSMovementLine> list = new Query(getCtx(), I_M_MovementLine.Table_Name, whereClause, get_TrxName())
		 										.setParameters(getM_Movement_ID())
		 										.setOrderBy(MMovementLine.COLUMNNAME_Line)
		 										.list();
		m_lines = new MDSMovementLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines

}
