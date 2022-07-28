package za.co.ntier.processes;

import org.apache.http.client.utils.URIBuilder;
import org.compiere.model.MNote;
import org.compiere.model.MOrder;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.icoderman.shopify.DefaultHttpClient;
import com.icoderman.shopify.HttpClient;
import za.co.ntier.model.X_zz_shopify;
import za.co.ntier.shopify.SfOrder;

/**
 *
 * Start a thread to collect unsynchronised orders from WooCommerce website
 *
 * @author yogan naidoo
 */

public class Shopify extends SvrProcess {

	public class MyRunnable implements Runnable {

		@Override
		public void run() {
			// Get Shopify defaults

			final PO sfDefaults;
			String whereClause = " isactive = 'Y' AND AD_Client_ID = ?";
			sfDefaults = new Query(getCtx(), X_zz_shopify.Table_Name, whereClause, null)
					.setParameters(new Object[] { Env.getAD_Client_ID(getCtx()) }).firstOnly();
			if (sfDefaults == null)
				throw new IllegalStateException("/nShopify Defaults need to be set on iDempiere /n");

			// Setup client
			/*
			 * OAuthConfig config = new OAuthConfig((String) wcDefaults.get_Value("url"),
			 * (String) wcDefaults.get_Value("consumerkey"), (String)
			 * wcDefaults.get_Value("consumersecret")); OAuthConfig config = new
			 * OAuthConfig("https://ntiertest.myshopify.com/admin",
			 * "36d41e61a77eaece34456a92287f119f",
			 * "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"); Woocommerce wooCommerce = new
			 * WooCommerceAPI(config, ApiVersionType.V202010);
			 */
			HttpClient client = new DefaultHttpClient((String) sfDefaults.get_Value("consumerkey"),	(String) sfDefaults.get_Value("consumerSecret"));
			
			// String startDateTime = (LocalDateTime.parse("2020-12-01T02:07:00").atOffset(ZoneOffset.ofHours(-5)))
			String startDateTime = (LocalDateTime.parse((String) sfDefaults.get_Value("syncfrom"))
					.atOffset(ZoneOffset.ofHours(-5))).toString();

			URIBuilder builder = null;
			try {
				builder = new URIBuilder((String) sfDefaults.get_Value("url") + "/admin/orders.json");
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			// builder.setParameter("updated_at_min", "2020-12-01T02:07:00-05:00")
//			builder.setParameter("updated_at_min", startDateTime).setParameter("fulfillment_status", "shipped").setParameter("status", "any");
			builder.setParameter("updated_at_min", startDateTime).setParameter("status", "any");

			StringBuilder notes = new StringBuilder("");
			LinkedHashMap<?, ?> mapWcOrders = client.getAll(builder.toString());
			List<?> wcOrders = (List<?>) mapWcOrders.get("orders");
			// Iterate through each order
			for (int i = 0; i < wcOrders.size(); i++) {
				Map<?, ?> order = (Map<?, ?>) wcOrders.get(i);
				
				int C_Order_ID = DB.getSQLValue(get_TrxName(),
						"select c_order_id from c_order " + "where POReference = ? and ad_client_id = ? ",order.get("order_number").toString(), Env.getAD_Client_ID(getCtx()));
				if (C_Order_ID >=0) 
					notes.append("!!!! Order : " + order.get("order_number").toString() + " already exists "+"\n");
				else
				{
					SfOrder sfOrder = new SfOrder(getCtx(), get_TrxName(), sfDefaults);
					C_Order_ID = sfOrder.createOrder(order);
					// Iterate through each order Line
					List<?> lines = (List<?>) order.get("line_items");
					for (int j = 0; j < lines.size(); j++) {
						Map<?, ?> line = (Map<?, ?>) lines.get(j);
						String orderLineDescription = sfOrder.createOrderLine(line, order);
						Object name = line.get("name");
						notes.append(orderLineDescription+"\n"+" Product Name: "+name);
					}
					sfOrder.createShippingCharge(order);
					MOrder so = new MOrder(getCtx(), C_Order_ID, get_TrxName());
					MNote note = new MNote(getCtx(), 0, get_TrxName());
					note.setAD_Table_ID(MOrder.Table_ID);
					note.setRecord_ID(C_Order_ID);
					note.setAD_Message_ID("sales.order");
					note.setAD_User_ID(sfDefaults.get_ValueAsInt("SalesRep_ID"));
					note.setReference(so.getPOReference());
					note.setTextMsg(notes.toString());
					note.saveEx();
					
				}
				
//				sfOrder.createPosPayment(order);
//				sfOrder.completeOrder();

				/*
				 * // Update syncedToIdempiere to 'yes' Map<String, Object> body = new
				 * HashMap<>(); List<Map<String, String>> listOfMetaData = new ArrayList();
				 * Map<String, String> metaData = new HashMap<>(); metaData.put("key",
				 * "syncedToIdempiere"); metaData.put("value", "yes");
				 * listOfMetaData.add(metaData);
				 * 
				 * body.put("meta_data", listOfMetaData); Map<?, ?> response =
				 * wooCommerce.update(EndpointBaseType.ORDERS.getValue(), id, body);
				 * System.out.println(response.toString());
				 */
			}
			// adjust startDateTime time to current time minus 10 min
			startDateTime = LocalDateTime.now().minusMinutes(10).format(DateTimeFormatter.ISO_DATE_TIME);
			Integer maxSize = 19;
			if(startDateTime.length() > maxSize ){
				startDateTime = startDateTime.substring(0, maxSize);
			}
			sfDefaults.set_ValueOfColumn("syncfrom", startDateTime);
			sfDefaults.saveEx();
		}

	}

	@Override
	protected void prepare() {
	}

	@Override
	protected String doIt() throws Exception {
		Thread thread = new Thread(new MyRunnable());
		thread.start();

		return "Synchronisation from Shopify initiated";
	}

}
