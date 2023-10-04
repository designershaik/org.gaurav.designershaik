package com.logilite.esignature.topaz.webui.component;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.ZkCssHelper;
import org.adempiere.webui.event.DialogEvents;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.MImage;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;

import com.logilite.esignature.topaz.webui.window.WSignatureDialog;

/**
 * Signature component
 * 
 * @author Sachin Bhimani @ Logilite Technologies
 */
public class SignatureImgBox extends Panel implements EventListener<Event>
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -402997702348672867L;

	private MImage				mImage				= null;
	private Image				image				= new Image();

	private Button				btnAddUpdate		= null;
	private Button				btnClear			= null;

	private boolean				isReadWrite			= true;
	private boolean				isCreateImageRecord	= true;

	/**
	 * @param isCreateImageRecord - If true then create entry of signature in AD_Image table.
	 */
	public SignatureImgBox(boolean isCreateImageRecord)
	{
		this.isCreateImageRecord = isCreateImageRecord;

		//
		initComponents();
	}

	private void initComponents()
	{
		this.setSclass("signature-box");

		ZKUpdateUtil.setWindowHeightX(this, 150);
		ZKUpdateUtil.setWindowWidthX(this, 150);

		ZKUpdateUtil.setHeight(image, "100%");
		ZKUpdateUtil.setWidth(image, "100%");

		//
		Div imgContainer = new Div();
		ZKUpdateUtil.setWidth(imgContainer, "100%");
		ZKUpdateUtil.setHeight(imgContainer, "80%");
		ZkCssHelper.appendStyle(imgContainer, "border: 1px solid;");
		imgContainer.appendChild(image);
		appendChild(imgContainer);

		btnAddUpdate = new Button();
		btnAddUpdate.setId("AddUpdate");
		btnAddUpdate.setTooltiptext(Msg.getMsg(Env.getCtx(), "New"));
		btnAddUpdate.addEventListener(Events.ON_CLICK, this);
		appendChild(btnAddUpdate);

		btnClear = new Button();
		btnClear.setId("Delete");
		btnClear.setTooltiptext(Msg.getMsg(Env.getCtx(), "Delete"));
		btnClear.addEventListener(Events.ON_CLICK, this);
		appendChild(btnClear);

		if (ThemeManager.isUseFontIconForImage())
		{
			btnAddUpdate.setIconSclass("z-icon-New");
			btnClear.setIconSclass("z-icon-Delete");
		}
		else
		{
			btnAddUpdate.setImage(ThemeManager.getThemeResource("images/New16.png"));
			btnClear.setImage(ThemeManager.getThemeResource("images/Delete16.png"));
		}
	} // initComponents

	private void setEnabledCursor(boolean enable)
	{
		if (enable)
		{
			this.setStyle("cursor: pointer;");
			btnAddUpdate.setStyle("cursor: pointer;");
			btnClear.setStyle("cursor: pointer;");
			btnAddUpdate.setEnabled(true);
			btnClear.setEnabled(true);
		}
		else
		{
			this.setStyle("cursor: default;");
			btnAddUpdate.setStyle("cursor: default;");
			btnClear.setStyle("cursor: default;");
			btnAddUpdate.setEnabled(false);
			btnClear.setEnabled(false);
		}
	} // setEnabledCursor

	public void setReadWrite(boolean isReadWrite)
	{
		this.isReadWrite = isReadWrite;
		setEnabledCursor(isReadWrite);
	}

	public boolean isReadWrite()
	{
		return isReadWrite;
	}

	/**
	 * @param aImage set content to image
	 */
	public void setContent(AImage aImage)
	{
		image.setContent(aImage);
		if (aImage == null)
			applyNewButton();
		else
			applyEditButton();
	}

	private void applyNewButton()
	{
		if (ThemeManager.isUseFontIconForImage())
			btnAddUpdate.setIconSclass("z-icon-New");
		else
			btnAddUpdate.setImage(ThemeManager.getThemeResource("images/New16.png"));

		btnAddUpdate.setTooltiptext("New");
		removeChild(btnClear);
	} // applyNewButton

	private void applyEditButton()
	{
		if (ThemeManager.isUseFontIconForImage())
			btnAddUpdate.setIconSclass("z-icon-Edit");
		else
			btnAddUpdate.setImage(ThemeManager.getThemeResource("images/EditRecord16.png"));

		btnAddUpdate.setTooltiptext("Edit");
		appendChild(btnClear);
	} // applyEditButton

	public AImage getAImage()
	{
		return (AImage) image.getContent();
	}

	public Image getImage()
	{
		return image;
	}

	public MImage getMImage()
	{
		return mImage;
	}

	public int getAD_Image_ID()
	{
		if (isCreateImageRecord && mImage != null)
			return mImage.getAD_Image_ID();
		return 0;
	}

	public void setMImage(MImage mImage)
	{
		this.mImage = mImage;
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		if (Events.ON_CLICK.equals(event.getName()) && isReadWrite())
		{
			if (event.getTarget().equals(btnAddUpdate))
			{
				SignatureImgBox signImgBox = this;
				final WSignatureDialog sign;
				if (isCreateImageRecord)
					sign = new WSignatureDialog(mImage);
				else
					sign = new WSignatureDialog(getAImage());

				sign.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception
					{
						if (!sign.isCancel())
						{
							if (isCreateImageRecord)
							{
								int AD_Image_ID = sign.getAD_Image_ID();
								if (AD_Image_ID != 0)
								{
									mImage = MImage.get(Env.getCtx(), Integer.valueOf(AD_Image_ID));
								}
							}
							else
							{
								setContent(sign.getAImage());
							}
							Events.sendEvent(Events.ON_CHANGE, signImgBox, null);
						}
					}
				});
			}
			else if (event.getTarget().equals(btnClear))
			{
				AImage img = null;
				image.setContent(img);
				mImage = null;
				Events.sendEvent(Events.ON_CHANGE, this, null);
			}
		}
	} // onEvent
}
