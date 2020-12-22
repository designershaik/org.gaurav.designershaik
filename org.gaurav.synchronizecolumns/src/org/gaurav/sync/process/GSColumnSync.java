/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.gaurav.sync.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MColumn;
import org.compiere.model.MReference;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.ValueNamePair;

/**
 *	Synchronize Column with Database
 *	
 *  @author Victor Perez, Jorg Janke
 *  @version $Id: ColumnSync.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 *  
 *  @author Teo Sarca
 *  	<li>BF [ 2854358 ] SyncColumn should load table in transaction
 *  		https://sourceforge.net/tracker/?func=detail&aid=2854358&group_id=176962&atid=879332
 */
public class GSColumnSync extends SvrProcess
{
	/** The Column				*/
	private int			p_AD_Column_ID = 0;
	private int p_AD_Reference_ID = 0 ;
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Reference_ID"))
				p_AD_Reference_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	
	}	//	prepare

	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		int columnsids[] = DB.getIDsEx(get_TrxName(), "select cl.AD_Column_ID from ad_column cl,ad_table tb where cl.ad_table_id = tb.ad_table_id and cl.AD_Reference_ID = ? and lower(tb.tablename) in " + 
				"(SELECT table_name FROM information_schema.tables WHERE table_schema='adempiere' AND table_type='BASE TABLE')", p_AD_Reference_ID);
		
		for(int AD_Column_ID : columnsids)
		{
			p_AD_Column_ID = AD_Column_ID;

			MColumn column = new MColumn(getCtx(), p_AD_Column_ID, get_TrxName());
			if (log.isLoggable(Level.INFO)) log.info("C_Column_ID=" + p_AD_Column_ID);
			if (p_AD_Column_ID == 0)
				throw new AdempiereUserError("@No@ @AD_Column_ID@");
			if (column.get_ID() == 0)
				throw new AdempiereUserError("@NotFound@ @AD_Column_ID@ " + p_AD_Column_ID);
			
			MTable table = new MTable(getCtx(), column.getAD_Table_ID(), get_TrxName());
			if (table.get_ID() == 0)
				throw new AdempiereUserError("@NotFound@ @AD_Table_ID@ " + column.getAD_Table_ID());
			if(column.getAD_Reference_ID()==20 && column.getDefaultValue()==null)
			{
				column.setDefaultValue("N");
				column.saveEx();
			}
			//	Find Column in Database
			Connection conn = null;
			ResultSet rs = null;
			try {
				conn = DB.getConnectionRO();
				DatabaseMetaData md = conn.getMetaData();
				String catalog = DB.getDatabase().getCatalog();
				String schema = DB.getDatabase().getSchema();
				String tableName = table.getTableName();
				if (md.storesUpperCaseIdentifiers())
				{
					tableName = tableName.toUpperCase();
				}
				else if (md.storesLowerCaseIdentifiers())
				{
					tableName = tableName.toLowerCase();
				}
				int noColumns = 0;
				String sql = null;
				//
				rs = md.getColumns(catalog, schema, tableName, column.getColumnName());
				while (rs.next())
				{
					noColumns++;
					String columnName = rs.getString ("COLUMN_NAME");
					if (!columnName.equalsIgnoreCase(column.getColumnName()))
						continue;
					
					//	update existing column
					boolean notNull = DatabaseMetaData.columnNoNulls == rs.getInt("NULLABLE");
					sql = column.getSQLModify(table, column.isMandatory() != notNull);
					String fkConstraintSql = MColumn.getForeignKeyConstraintSql(md, catalog, schema, tableName, table, column, false);
					if (fkConstraintSql != null && fkConstraintSql.length() > 0)
						sql += fkConstraintSql;

					int no = 0;
					if (sql.indexOf(DB.SQLSTATEMENT_SEPARATOR) == -1)
					{
						no = DB.executeUpdate(sql, false, get_TrxName());
						addLog (0, null, new BigDecimal(no), sql);
					}
					else
					{
						String statements[] = sql.split(DB.SQLSTATEMENT_SEPARATOR);
						for (int i = 0; i < statements.length; i++)
						{
							int count = DB.executeUpdateEx(statements[i], get_TrxName());
							addLog (0, null, new BigDecimal(count), statements[i]);
							no += count;
						}
					}
			
					if (no == -1)
					{
						StringBuilder msg = new StringBuilder("@Error@ ");
						ValueNamePair pp = CLogger.retrieveError();
						if (pp != null)
							msg = new StringBuilder(pp.getName()).append(" - ");
						msg.append(sql);
						throw new AdempiereUserError (msg.toString());
					}
				}
			} finally {
				DB.close(rs);
				rs = null;
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {}
				}
			}
		}
		return "@OK@";
	}	//	doIt
	
}	//	ColumnSync
