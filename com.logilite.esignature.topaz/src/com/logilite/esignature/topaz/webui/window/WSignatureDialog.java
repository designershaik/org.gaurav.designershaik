package com.logilite.esignature.topaz.webui.window;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Window;
import org.apache.commons.codec.binary.Base64;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.lang.Objects;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Center;
import org.zkoss.zul.Image;
import org.zkoss.zul.Script;
import org.zkoss.zul.South;

import com.logilite.esignature.topaz.util.Utils;

public class WSignatureDialog extends Window implements EventListener<Event>
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6372165428638797270L;

	public final static String	ON_SIGNATURE_EVENT	= "onSignatureEvent";

	/** Logger */
	private CLogger				log					= CLogger.getCLogger(WSignatureDialog.class);

	private Borderlayout		mainLayout			= new Borderlayout();
	private Center				center;
	private ConfirmPanel		confirmPanel		= new ConfirmPanel(true, false, false, false, false, false);

	private MImage				m_mImage			= null;
	private AImage				m_aImage			= null;
	private Image				image				= new Image();

	private boolean				cancel				= false;
	private boolean				isSaveImgRecord		= true;

	public WSignatureDialog(AImage aImage)
	{
		super();
		m_aImage = aImage;
		isSaveImgRecord = false;
		init();

		try
		{
			image.setContent(m_aImage);
			image.setStyle("margin: 8px; padding: 1px;");
		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "load image", e);
		}

		AEnv.showCenterScreen(this);

		if (m_aImage == null)
			Events.postEvent(confirmPanel.getButton(ConfirmPanel.A_NEW), new Event(Events.ON_CLICK, confirmPanel.getButton(ConfirmPanel.A_NEW)));
	} // WSignatureDialog

	public WSignatureDialog(MImage mImage)
	{
		super();

		m_mImage = mImage;
		init();

		// load data
		if (m_mImage == null)
			m_mImage = MImage.get(Env.getCtx(), 0);
		else
		{
			try
			{
				AImage aImage = new AImage(m_mImage.getName(), m_mImage.getData());
				image.setContent(aImage);
				image.setStyle("margin: 8px; padding: 1px;");
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "load image", e);
			}
		}

		AEnv.showCenterScreen(this);

		if (mImage == null)
			Events.postEvent(confirmPanel.getButton(ConfirmPanel.A_NEW), new Event(Events.ON_CLICK, confirmPanel.getButton(ConfirmPanel.A_NEW)));
	} // WSignatureDialog

	/**
	 * Initialize
	 */
	void init()
	{
		this.setAttribute(Window.MODE_KEY, Window.MODE_HIGHLIGHTED);
		this.setTitle("Signature");
		this.setStyle("position: relative;");
		this.setSclass("popup-dialog");
		this.setBorder("normal");
		this.setWidth("485px");
		this.setHeight("345px");

		Script jsSignTopazPad = new Script();
		Script jsSignApp = new Script();
		// Script jsTopazSignApp = new Script();
		// Script jsDrawingSignApp = new Script();
		Script jsSignPad = new Script();
		try
		{
			URL url1 = Utils.getJSResource("signature_app.js");
			URL url2 = Utils.getJSResource("SignWebTablet.js");
			// URL url3 = Utils.getJSResource("drawing_signature_app.js");
			// URL url4 = Utils.getJSResource("topaz_signature_app.js");
			URL url5 = Utils.getJSResource("signature_pad.min.js");

			byte[] byte1 = Files.readAll(url1.openStream());
			byte[] byte2 = Files.readAll(url2.openStream());
			// byte[] byte3 = Files.readAll(url3.openStream());
			// byte[] byte4 = Files.readAll(url4.openStream());
			byte[] byte5 = Files.readAll(url5.openStream());

			jsSignApp.setContent(new String(byte1));
			jsSignTopazPad.setContent(new String(byte2));
			// jsDrawingSignApp.setContent(new String(byte3));
			// jsTopazSignApp.setContent(new String(byte4));
			jsSignPad.setContent(new String(byte5));
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, "Issue while reading JS file.", e);
			throw new AdempiereException("Issue while reading JS file.", e);
		}

		// this.appendChild(jsDrawingSignApp);
		// this.appendChild(jsTopazSignApp);
		this.appendChild(jsSignPad);
		this.appendChild(jsSignTopazPad);
		this.appendChild(jsSignApp);

		mainLayout.setParent(this);
		mainLayout.setHflex("1");
		mainLayout.setVflex("1");

		center = new Center();
		center.setParent(mainLayout);
		center.setSclass("dialog-content");
		center.appendChild(image);
		center.setStyle("background-color: transparent; box-shadow: rgba(170, 235, 255, 0.5) 0px 0px 20px 6px inset; padding: 0px !important;");

		South south = new South();
		south.setParent(mainLayout);
		south.setSclass("dialog-footer");
		south.setStyle("background-color: transparent; border: none;");
		south.appendChild(confirmPanel);

		confirmPanel.addComponentsLeft(confirmPanel.createButton(ConfirmPanel.A_NEW));
		confirmPanel.addComponentsLeft(confirmPanel.createButton(ConfirmPanel.A_DELETE));
		confirmPanel.getButton(ConfirmPanel.A_NEW).setVisible(true);
		confirmPanel.getButton(ConfirmPanel.A_DELETE).setVisible(false);
		confirmPanel.getButton(ConfirmPanel.A_OK).setVisible(false);
		confirmPanel.addActionListener(Events.ON_CLICK, this);

		this.addEventListener(ON_SIGNATURE_EVENT, this);

	} // init

	public void onEvent(Event e) throws Exception
	{
		if (e.getTarget().getId() == ConfirmPanel.A_NEW)
		{
			cmd_new();
		}
		else if (ON_SIGNATURE_EVENT.equals(e.getName()))
		{
			cmd_save(e);
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			cancel = true;
			detach();
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_DELETE))
		{
			AImage img = null;
			image.setContent(img);
		}
	}

	/**
	 * Load Signature & display board
	 */
	private void cmd_new()
	{
		Clients.evalJavaScript(	"signatureApp.initSignaturePad('"	+ image.getUuid() + "', '" + center.getUuid() + "', '"
								+ this.getUuid() + "', '" + confirmPanel.getButton(ConfirmPanel.A_DELETE).getUuid() + "', '"
								+ confirmPanel.getButton(ConfirmPanel.A_OK).getUuid() + "', '"
								+ confirmPanel.getButton(ConfirmPanel.A_CANCEL).getUuid() + "')");

		confirmPanel.getButton(ConfirmPanel.A_NEW).setVisible(false);
		confirmPanel.getButton(ConfirmPanel.A_DELETE).setVisible(true);
		confirmPanel.getButton(ConfirmPanel.A_OK).setVisible(true);
	} // cmd_new

	/**
	 * Save image data from event and if data is null then remove existing
	 * records if exists.
	 * 
	 * @param e - event from signature_app.js
	 */
	private void cmd_save(Event e)
	{
		String imgData = null;

		if (e.getData() instanceof JSONObject)
		{
			JSONObject data = (JSONObject) e.getData();
			if (data == null || data.size() < 1)
			{
				log.log(Level.SEVERE, "ILLEGAL_REQUEST_WRONG_DATA", e);
				throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] { Objects.toString(data), this });
			}

			JSONArray jArray = (JSONArray) data.get("data");
			imgData = (String) jArray.get(0);
		}

		AImage aimg = null;
		if (imgData != null)
		{
			try
			{
				String encodingPrefix = "base64,";
				int contentStartIndex = imgData.indexOf(encodingPrefix) + encodingPrefix.length();
				aimg = new AImage("Sign.png", trim(Base64.decodeBase64((imgData.substring(contentStartIndex)).getBytes())));
			}
			catch (IOException ex)
			{
				log.log(Level.SEVERE, "wrong image data", ex);
			}
		}

		image.setContent(aimg);

		// Save info
		if (isSaveImgRecord)
		{
			if (m_mImage == null)
				m_mImage = MImage.get(Env.getCtx(), 0);
			m_mImage.setName("Signature");
			m_mImage.setImageURL("Signature");
			if (image.getContent() != null)
				m_mImage.setBinaryData(image.getContent().getByteData());
			else
				m_mImage.setBinaryData(null);

			if (image.getContent() != null)
			{
				m_mImage.saveEx();
			}
			else if (m_mImage != null && m_mImage.getAD_Image_ID() > 0)
			{
				m_mImage.deleteEx(true);
				m_mImage = null;
			}
		}
		else
		{
			m_aImage = null;
			if (image.getContent() != null)
			{
				try
				{
					m_aImage = new AImage("Signature", image.getContent().getByteData());
				}
				catch (Exception ex)
				{
					log.log(Level.WARNING, "load image", ex);
				}
			}
		}

		detach();
	} // cmd_save

	/**
	 * @return true if dialog cancel by user
	 */
	public boolean isCancel()
	{
		return cancel;
	}

	/**
	 * Get Image ID
	 * 
	 * @return ID or 0
	 */
	public int getAD_Image_ID()
	{
		if (m_mImage != null)
			return m_mImage.getAD_Image_ID();
		return 0;
	} // getAD_Image_ID

	/**
	 * Get AImage
	 * 
	 * @return AImage or null
	 */
	public AImage getAImage()
	{
		if (m_aImage != null)
			return m_aImage;
		return null;
	} // getAImage

	public byte[] trim(byte[] bytes)
	{
		try
		{
			BufferedImage image = toBufferedImage(bytes);
			int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE, x2 = 0, y2 = 0;
			for (int x = 0; x < image.getWidth(); x++)
			{
				for (int y = 0; y < image.getHeight(); y++)
				{
					int argb = image.getRGB(x, y);
					if (argb != -1)
					{
						x1 = Math.min(x1, x);
						y1 = Math.min(y1, y);
						x2 = Math.max(x2, x);
						y2 = Math.max(y2, y);
					}
				}
			}
			WritableRaster r = image.getRaster();
			ColorModel cm = image.getColorModel();
			r = r.createWritableChild(x1, y1, x2 - x1, y2 - y1, 0, 0, null);
			BufferedImage bi = new BufferedImage(cm, r, cm.isAlphaPremultiplied(), null);
			return toByteArray(bi, "png");
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "something wrong to trim image", e);
		}
		return bytes;
	}   

	// convert BufferedImage to byte[]
	public byte[] toByteArray(BufferedImage bi, String format) throws IOException
	{

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format, baos);
		byte[] bytes = baos.toByteArray();
		return bytes;

	}

	// convert byte[] to BufferedImage
	public BufferedImage toBufferedImage(byte[] bytes) throws IOException
	{

		InputStream is = new ByteArrayInputStream(bytes);
		BufferedImage bi = ImageIO.read(is);
		return bi;

	}	
}
