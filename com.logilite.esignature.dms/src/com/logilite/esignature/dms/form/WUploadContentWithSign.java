/******************************************************************************
 * Copyright (C) 2016 Logilite Technologies LLP								  *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package com.logilite.esignature.dms.form;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.ClientInfo;
import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.factory.ButtonFactory;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Center;
import org.zkoss.zul.South;

import com.logilite.dms.DMS;
import com.logilite.dms.DMS_Context_Util;
import com.logilite.dms.DMS_ZK_Util;
import com.logilite.dms.constant.DMSConstant;
import com.logilite.dms.form.AbstractUploadContent;
import com.logilite.dms.model.I_DMS_Content;
import com.logilite.dms.model.MDMSAssociation;
import com.logilite.dms.model.MDMSContent;
import com.logilite.dms.model.MDMSContentType;
import com.logilite.dms.util.Utils;
import com.logilite.esignature.topaz.webui.component.SignatureImgBox;

/**
 * Upload content into DMS + Signature editor support
 * 
 * @author Sachin Bhimani
 */
public class WUploadContentWithSign extends AbstractUploadContent
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1655014012650269815L;

	public String				MSG_SIGNATURE		= Msg.translate(Env.getCtx(), "Signature");

	private Label				lblSignature;
	private Textbox				txtSign;
	private Textbox				txtSignDesc;
	private SignatureImgBox		signatureBox;

	/**
	 * Constructor
	 * 
	 * @param dms
	 * @param content
	 * @param isVersion
	 * @param tableID
	 * @param recordID
	 * @param windowNo
	 * @param tabNo
	 */
	public WUploadContentWithSign(DMS dms, I_DMS_Content content, boolean isVersion, int tableID, int recordID, int windowNo, int tabNo)
	{
		super(dms, content, isVersion, tableID, recordID, windowNo, tabNo);
	}

	/**
	 * initialize components
	 */
	public void init()
	{
		mainLayout.setParent(this);

		if (!isVersion)
		{
			this.setStyle("min-height:30%; max-height:100%; overflow-y:auto;");
			this.setWidth("60%");
			this.setHeight("60%");
		}
		else
		{
			contentTypeRow.setVisible(false);
			tabBoxAttribute.setVisible(false);
			this.setWidth("40%");
			this.setHeight("40%");
		}

		if (ClientInfo.isMobile())
		{
			this.setHeight("100%");
			this.setWidth("100%");
		}

		this.setClosable(true);
		this.setMaximizable(true);
		this.addEventListener(Events.ON_OK, this);
		this.setTitle(DMSConstant.MSG_UPLOAD_CONTENT);

		//
		Center center = new Center();
		center.setParent(mainLayout);
		center.setSclass("dialog-content");
		center.appendChild(gridView);

		gridView.setStyle("overflow: auto; position:relative;");
		gridView.makeNoStrip();
		gridView.setOddRowSclass("even");
		gridView.setZclass("none");
		gridView.setWidth("100%");
		gridView.setHeight("100%");

		int Column_ID = MColumn.getColumn_ID(MDMSContent.Table_Name, MDMSContent.COLUMNNAME_DMS_ContentType_ID);
		MLookup lookup = MLookupFactory.get(Env.getCtx(), windowNo, tabNo, Column_ID, DisplayType.TableDir);
		lookup.refresh();
		editorContentType = new WTableDirEditor(MDMSContentType.COLUMNNAME_DMS_ContentType_ID, false, false, true, lookup);

		// Load value from context if available for Content Type field
		DMS_Context_Util.setEditorDefaultValueFromCtx(Env.getCtx(), windowNo, tabNo, lookup.getDisplayType(), editorContentType);

		//
		lblContentType.setValue(DMSConstant.MSG_DMS_CONTENT_TYPE);
		btnFileUpload.setLabel(DMSConstant.MSG_UPLOAD_CONTENT);
		btnFileUpload.setWidth("100%");
		btnFileUpload.setHeight("60px");
		LayoutUtils.addSclass("txt-btn", btnFileUpload);

		//
		rows = gridView.newRows();
		Row row = rows.newRow();
		row.appendCellChild(btnFileUpload, 5);

		row = rows.newRow();
		txtSign = createTextbox("", "Content Name", false);
		txtSignDesc = createTextbox("", DMSConstant.MSG_DESCRIPTION, true);
		txtSign.setVisible(false);
		txtSignDesc.setVisible(false);
		row.appendCellChild(txtSign, 2);
		row.appendCellChild(txtSignDesc, 2);

		row = rows.newRow();
		lblSignature = new Label();
		signatureBox = new SignatureImgBox(false);
		lblSignature.setValue(MSG_SIGNATURE);
		row.appendCellChild(lblSignature, 2);
		row.appendCellChild(signatureBox, 3);
		signatureBox.addEventListener(Events.ON_CHANGE, this);

		contentTypeRow.appendCellChild(lblContentType, 2);
		contentTypeRow.appendCellChild(editorContentType.getComponent(), 3);
		editorContentType.addValueChangeListener(this);
		rows.appendChild(contentTypeRow);

		//
		row = rows.newRow();
		Cell cell = new Cell();
		cell.setColspan(5);

		tabBoxAttribute.appendChild(tabsAttribute);
		tabBoxAttribute.appendChild(tabPanelsAttribute);

		tabAttribute.setLabel(DMSConstant.MSG_ATTRIBUTE_SET);
		tabsAttribute.appendChild(tabAttribute);
		tabPanelsAttribute.appendChild(tabPanelAttribute);
		tabPanelAttribute.setStyle("min-height :20px; max-height: 200px; overflow: auto;");
		tabBoxAttribute.setMold("accordion");
		tabBoxAttribute.setVisible(false);

		cell.appendChild(tabBoxAttribute);
		row.appendChild(cell);

		//
		South south = new South();
		south.setParent(mainLayout);
		south.setSclass("dialog-footer");
		south.setStyle("background-color: transparent; border: none;");
		south.appendChild(confirmPanel);

		btnOk = confirmPanel.getOKButton();
		btnClose = confirmPanel.getButton(ConfirmPanel.A_CANCEL);

		// Load ASI Panel if ContentType value pre-filled from the context
		loadASIPanel();

		btnFileUpload.setUpload("multiple=true," + DMS_ZK_Util.getUploadSetting());
		btnFileUpload.addEventListener(Events.ON_UPLOAD, this);
		btnClose.addEventListener(Events.ON_CLICK, this);
		btnOk.addEventListener(Events.ON_CLICK, this);
		addEventListener(Events.ON_UPLOAD, this);

		AEnv.showCenterScreen(this);
	} // init

	@Override
	public void onEvent(Event e) throws Exception
	{
		if (e instanceof UploadEvent)
		{
			UploadEvent ue = (UploadEvent) e;
			processUploadMedia(ue.getMedias());
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_OK) || Events.ON_OK.equals(e.getName()))
		{
			if (isVersion && mapUploadInfo.size() > 1)
			{
				throw new WrongValueException(btnFileUpload, "Please upload only single content for versioning");
			}

			saveUploadedDocument();
		}
		else if (e.getTarget().getId().startsWith(BTN_ID_UPLOAD_CANCEL_PREFIX))
		{
			Button btnCancelRow = (Button) e.getTarget();
			int rowNo = (int) btnCancelRow.getAttribute(ATTRIB_ROW_NO);
			Row rowRef = (Row) btnCancelRow.getAttribute(ATTRIB_ROW_REF);

			//
			rows.removeChild(rowRef);
			mapUploadInfo.remove(rowNo);
			//
			if (mapUploadInfo.size() <= 0)
			{
				txtSign.setValue(null);
				txtSign.setVisible(false);

				txtSignDesc.setVisible(false);
				txtSignDesc.setValue(null);

				signatureBox.setReadWrite(true);

				btnFileUpload.setEnabled(true);
				setBtnLabelFileUpload();
			}
			else
			{
				setBtnLabelFileUpload();
			}
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			isCancel = true;
			this.detach();
		}
		else if (e.getTarget().equals(signatureBox) || e.getTarget().equals(signatureBox.getImage()))
		{
			signatureBox.setContent(signatureBox.getAImage());
			if (signatureBox.getAImage() != null)
			{
				btnFileUpload.setEnabled(false);
				if (isVersion)
				{
					MDMSContent parentContent = new MDMSContent(Env.getCtx(), content.getDMS_Content_Related_ID(), null);
					txtSign.setValue(parentContent.getName().substring(0, parentContent.getName().lastIndexOf(".")));
				}
				else
				{
					txtSign.setValue("Signature_" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()));
				}

				// row, media, txtName, txtDesc, byte[]
				mapUploadInfo.put(0, new Object[] { null, null, txtSign, txtSignDesc, signatureBox.getAImage().getByteData() });

				txtSign.setVisible(true);
				txtSignDesc.setVisible(true);
			}
			else
			{
				btnFileUpload.setEnabled(true);

				txtSign.setValue(null);
				txtSign.setVisible(false);

				txtSignDesc.setVisible(false);
				txtSignDesc.setValue(null);
			}
		}
	} // onEvent

	/**
	 * check medias is uploaded
	 * 
	 * @param medias
	 */
	private void processUploadMedia(Media[] medias)
	{
		if (medias == null)
			return;

		try
		{
			for (Media media : medias)
			{
				String name = media.getName();
				if (name.lastIndexOf(".") > 0)
					name = name.subSequence(0, name.lastIndexOf(".")).toString();

				//
				Textbox txtName = createTextbox(name, "Content Name", false);
				Textbox txtDesc = createTextbox("", DMSConstant.MSG_DESCRIPTION, true);
				Button btnCancel = ButtonFactory.createNamedButton("Cancel", false, true);
				btnCancel.setId(BTN_ID_UPLOAD_CANCEL_PREFIX + rowCount);
				txtName.setWidth("98%");

				Row row = new Row();
				row.appendCellChild(txtName, 2);
				row.appendCellChild(txtDesc, 2);
				row.appendCellChild(btnCancel, 1);
				btnCancel.setAttribute(ATTRIB_ROW_REF, row);
				btnCancel.setAttribute(ATTRIB_ROW_NO, rowCount);
				btnCancel.addEventListener(Events.ON_CLICK, this);

				rows.insertBefore(row, txtSign.getParent().getParent());
				row.getLastCell().setWidth("10px");

				Object[] value = new Object[] { row, new AMedia(media.getName(), null, null, media.getByteData()), txtName, txtDesc };
				mapUploadInfo.put(rowCount, value);
				rowCount++;
			}

			setBtnLabelFileUpload();
			signatureBox.setReadWrite(false);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Upload Content Failure: ", e);
			throw new AdempiereException("Upload Content Failure: " + e);
		}
	} // processUploadMedia

	public void validateUploadedDocInfo()
	{
		if (mapUploadInfo.size() <= 0 && signatureBox.getAImage() == null)
			throw new WrongValueException(btnFileUpload, DMSConstant.MSG_UPLOAD_CONTENT);

		for (Entry<Integer, Object[]> map : mapUploadInfo.entrySet())
		{
			Textbox txtBox = (Textbox) map.getValue()[2];
			if (Util.isEmpty(txtBox.getValue(), true))
				throw new WrongValueException(txtBox, DMSConstant.MSG_FILL_MANDATORY);

			String errorMsg = Utils.isValidFileName(txtBox.getValue(), false);
			if (!Util.isEmpty(errorMsg))
				throw new WrongValueException(txtBox, errorMsg);
		}
	} // validateUploadedDocInfo

	/**
	 * save uploaded document in current directory
	 */
	protected void saveUploadedDocument()
	{
		validateUploadedDocInfo();

		// Add content in DMS one by one
		for (Entry<Integer, Object[]> map : mapUploadInfo.entrySet())
		{
			File tmpFile = null;
			byte[] fileData = null;
			Textbox txtName = null;
			Textbox txtDesc = null;
			try
			{
				txtName = (Textbox) map.getValue()[2];
				txtDesc = (Textbox) map.getValue()[3];

				// Prepare temporary file
				if (map.getValue()[0] == null && map.getValue()[4] != null)
				{
					tmpFile = File.createTempFile(txtName.getValue(), ".png");
					fileData = (byte[]) map.getValue()[4];
				}
				else
				{
					AMedia media = (AMedia) map.getValue()[1];
					tmpFile = File.createTempFile(media.getName(), "." + media.getFormat());
					fileData = media.getByteData();
				}

				FileOutputStream os = new FileOutputStream(tmpFile);
				os.write(fileData);
				os.flush();
				os.close();

				if (content != null && content.getDMS_Content_ID() > 0)
				{
					MDMSAssociation destAssociation = dms.getParentAssociationFromContent(content.getDMS_Content_ID());
					tableID = destAssociation.getAD_Table_ID();
					recordID = destAssociation.getRecord_ID();
				}

				//
				saveToDMS(tmpFile, txtName.getValue(), txtDesc.getValue());

				if (!isVersion)
				{
					// Remove from map as content added in DMS
					Object[] data = mapUploadInfo.remove(map.getKey());

					if (data[0] != null)
					{
						// Remove name and description field row
						rows.removeChild((Row) data[0]);
					}
					setBtnLabelFileUpload();
				}
			}
			catch (IOException e)
			{
				log.log(Level.SEVERE, "Fail to convert Media to File for " + txtName.getValue(), e);
				throw new WrongValueException(txtName, "Fail to convert Media to File: " + e.getLocalizedMessage(), e);
			}
			catch (Exception e)
			{
				throw new WrongValueException(txtName, e.getLocalizedMessage(), e);
			}
			finally
			{
				if (tmpFile != null)
					tmpFile.delete();
			}
		}

		this.detach();

	} // saveUploadedDocument
}
