package org.gaurav.dsi.processes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Util;
public class AlfrescoSync extends SvrProcess
{
	private int tCount = 0;
	private int sCount = 0;
	private int fCount = 0;
	private int adClientID= 0;
	private boolean isActiveCheck = false;
	public static CCache<Integer, String> tblMap= new CCache<Integer, String>("TableCache", 100); 
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID")) {
				adClientID =((BigDecimal) para[i].getParameter()).intValue();
			}else if(name.equals("onlyActiveRecords")){
			     if(((String) para[i].getParameter()).equalsIgnoreCase("Y"))
			    	 isActiveCheck = true;
			}else{
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
			}
		}
	} // prepare

	/**
	 * Process
	 *
	 * @return message
	 * @throws Exception
	 */
	protected String doIt() throws Exception
	{
		int limit = 10;
		int adAttachmentID = 0;
		log.log(Level.SEVERE, new Date().toString());
		log.log(Level.SEVERE, "*********************** alfresco migration log started **********************************");	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean dataFlag = true;
		while (dataFlag){
			dataFlag = false;
			try{
				String attSql = "SELECT AD_Attachment_ID FROM AD_Attachment  WHERE AD_Attachment_ID > ? AND AD_Client_ID = ? "; 
				if (isActiveCheck)
					attSql += "AND IsActive = 'Y'";
				attSql+=" ORDER BY AD_Attachment_ID ";
			
				pstmt = DB.prepareStatement(attSql, get_TrxName());
				pstmt.setInt(1, adAttachmentID);
				pstmt.setInt(2, adClientID);
				pstmt.setMaxRows(limit);
				rs = pstmt.executeQuery();
				while(rs.next()){
					dataFlag = true;
					adAttachmentID = rs.getInt("AD_Attachment_ID");
					MAttachment attach = new MAttachment(getCtx(), adAttachmentID, get_TrxName());
					if(attach.getTitle().equals(MAttachment.ZIP) && attach.getAD_Client_ID()==adClientID)
					{
						byte[] data = attach.getBinaryData();
						if (data != null)
						{
							ArrayList<MAttachmentEntry> copu_m_items = new ArrayList<MAttachmentEntry>();
							if (!MAttachment.ZIP.equals(attach.getTitle()))
							{
								copu_m_items.add(new MAttachmentEntry(attach.getTitle(), data, 1));
							}
							if (data != null)
							{
								ByteArrayInputStream in = new ByteArrayInputStream(data);
								ZipInputStream zip = new ZipInputStream(in);
								ZipEntry entry = zip.getNextEntry();
								while (entry != null)
								{
									String name = entry.getName();
									//
									byte[] dataEntry = readDataFromZipInputStream(zip);
									log.fine(name + " - size=" + dataEntry.length + " - zip=" + entry.getCompressedSize() + "("
										+ entry.getSize() + ") " + (entry.getCompressedSize() * 100 / entry.getSize()) + "%");
									//
									copu_m_items.add(new MAttachmentEntry(name, dataEntry, copu_m_items.size() + 1));
									entry = zip.getNextEntry();
								}	
							}
							attach.m_items = copu_m_items;
							attach.setTitle(MAttachment.ZIP);
							attach.setBinaryData(null);
							String tableName = getTableName(attach.getAD_Table_ID());
							String str = "["+ attach.getAD_Client_ID()+"->"+attach.getAD_Org_ID()+"->"+tableName+"->"+attach.getRecord_ID();
							if(attach.save())
							{
								sCount++;
								str += " : Success] \r\n";	
							}else
							{
								fCount++;
								str += " : Failure] \r\n";
							}
							log.log(Level.SEVERE, str);	
							tCount++;
						}
					}
				}
			}catch(Exception e){
				log.log(Level.SEVERE, e.getMessage());	
			}finally {
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
		}
		
		log.log(Level.SEVERE, "*********************** alfresco migration log complited **********************************");	
		return      " Total Attachment  : " + tCount 
				+ "\n Success Count     : " + sCount
				+ "\n Failure Count     : " + fCount;
	} // doIt

	private byte[] readDataFromZipInputStream(ZipInputStream zip) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int length = zip.read(buffer);
		while (length != -1)
		{
			out.write(buffer, 0, length);
			length = zip.read(buffer);
		}
		return out.toByteArray();
	}
	
	private String getTableName(int tableID)
	{
		String tableName = tblMap.get(tableID);
		if(Util.isEmpty(tableName)){
			tableName = DB.getSQLValueString(get_TrxName(), "Select TableName from AD_Table WHERE AD_Table_ID = ?",tableID);
			tblMap.put(tableID, tableName);
		}
		return tableName;
	}
	
	private void saveToLogFile(StringBuffer msg)
	{
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File("alfresco_migration.log"));
			writer.append(new Date().toString());
			writer.write("\r\n");
			writer.append("Total Attachment    : " + tCount);
			writer.write("\r\n");
			writer.append("Success Count       : " + sCount);
			writer.write("\r\n");
			writer.append("Failure Count       : " + fCount);
			writer.write("\r\n");
			writer.append(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

} // AllocationReset
