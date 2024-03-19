package za.co.ntier.shopify;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.model.X_C_POSPayment;
import org.compiere.model.X_C_Payment;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *
 * Create Order and lines on iDempiere as received from WooCommerce
 *
 * @author yogan naidoo
 */

public final class SfOrder {
	private final Properties ctx;
	private final String trxName;
	private final int POSTENDERTYPE_ID = 1000000;
	// private final int priceList_ID = 101;
	final String PAYMENT_RULE = "M";
	// final String PAYMENT_RULE = "P";
	private final MOrder order;
	private Boolean isTaxInclusive;
	private static CLogger log = CLogger.getCLogger(SfOrder.class);
	private PO sfDefaults;

	public SfOrder(Properties ctx, String trxName, PO sfDefaults) {
		this.ctx = ctx;
		this.trxName = trxName;
		this.sfDefaults = sfDefaults;
		order = new MOrder(ctx, 0, trxName);
	}

	public int createOrder(Map<?, ?> orderSf) {
		
		order.setClientOrg(Env.getAD_Client_ID(ctx), 11);
		order.setPOReference((orderSf.get("order_number").toString()));
		order.setAD_Org_ID((int) sfDefaults.get_Value("ad_org_id"));
		int BP_Id = getBPId(orderSf);
		order.setC_BPartner_ID(BP_Id);
		int BPLocationId = getBPLocationId(BP_Id);
		order.setC_BPartner_Location_ID(BPLocationId); // order.setAD_User_ID(101);
		order.setBill_BPartner_ID(BP_Id);
		order.setBill_Location_ID(BPLocationId);
		// order.setBill_User_ID(); order.setSalesRep_ID(101);
		isTaxInclusive = (orderSf.get("taxes_included").toString().equals("true")) ? true : false;
		order.setM_PriceList_ID(getPriceList(orderSf));
		// order.setM_PriceList_ID(101);
		order.setIsSOTrx(true);
		order.setM_Warehouse_ID((int) sfDefaults.get_Value("m_warehouse_id"));
		order.setDateOrdered(getDate(orderSf));
		order.setDateAcct(getDate(orderSf));
		order.setC_DocTypeTarget_ID((int)sfDefaults.get_Value("C_DocType_ID"));
		order.setPaymentRule(MOrder.PAYMENTRULE_CreditCard);
		order.setC_OrderSource_ID((int)sfDefaults.get_Value("C_OrderSource_ID"));
		//order.setDeliveryRule("O");
		order.setDeliveryViaRule("S");
		order.setInvoiceRule("I");
		order.setFreightCostRule("F");
		order.set_ValueNoCheck("DS_ShippingTerms", "DAP");
		if (!order.save()) {
			throw new IllegalStateException("Could not create order");
		}
		return order.getC_Order_ID();
	}

	private java.sql.Timestamp getDate(Map<?, ?> orderSf) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		String date = orderSf.get("created_at").toString();
		OffsetDateTime odt = OffsetDateTime.parse(date, dateTimeFormatter);
		Instant instant = odt.toInstant();
		return java.sql.Timestamp.from(instant);
	}

	private int getPriceList(Map<?, ?> orderSf) {
		String wcCurrency = (String) orderSf.get("currency");
		String localCurrency = DB.getSQLValueString(trxName,
				"select iso_code from C_Currency " + "where C_Currency_ID = " + "(select C_Currency_ID "
						+ "from M_PriceList " + "where M_PriceList_id = ?) ",
				(int) sfDefaults.get_Value("local_incl_pricelist_id"));

		Boolean local = (wcCurrency.equals(localCurrency)) ? true : false;

		int priceList;
		if (local) {
			priceList = (isTaxInclusive) ? (int) sfDefaults.get_Value("local_incl_pricelist_id")
					: (int) sfDefaults.get_Value("local_excl_pricelist_id");
		} else {
			priceList = (isTaxInclusive) ? (int) sfDefaults.get_Value("intl_incl_pricelist_id")
					: (int) sfDefaults.get_Value("intl_excl_pricelist_id");
		}
		return (priceList);
	}

	public int getBPId(Map<?, ?> orderSf) {
		String email = (String) orderSf.get("email");
		String phone = (String) orderSf.get("phone");
		
		email = email ==null ? "":email;
		phone = phone ==null ? "":phone;
		
		int c_bpartner_id = DB.getSQLValue(trxName, "select c_bpartner_id from ad_user where email like ? and AD_Client_ID = ? ", email,Env.getAD_Client_ID(ctx));
		if (c_bpartner_id < 0) 
		{
			c_bpartner_id = DB.getSQLValue(trxName, "select c_bpartner_id from ad_user where replace(phone,'+','') like ? and AD_Client_ID = ? ", phone.replace("+", ""),Env.getAD_Client_ID(ctx));
			if (c_bpartner_id < 0)
				c_bpartner_id = createBP(orderSf);
			else
				updateBPartner(c_bpartner_id,email,phone);
		}
		
		return c_bpartner_id;
	}

	private void updateBPartner(int c_bpartner_id, String email, String phone)
	{
		int AD_User_ID = DB.getSQLValue(trxName, "Select AD_User_ID From AD_User Where C_BPartner_ID = ? ",c_bpartner_id);
		MUser user = new MUser(Env.getCtx(),AD_User_ID,trxName);
		user.setEMail(email);
		user.setPhone(phone);
		user.saveEx();
	}

	int createBP(Map<?, ?> orderSf) {
		Map<?, ?> customer = (Map<?, ?>) orderSf.get("customer");
		Map<?, ?> defaultAddress = (Map<?, ?>) customer.get("default_address");
		int C_BPartner_ID = DB.getSQLValue(trxName, "Select C_BPartner_ID From C_BPartner Where Upper(Value) like Upper(?) and AD_Client_ID = ?  ",(String) defaultAddress.get("name"),Env.getAD_Client_ID(ctx));
		
		String name = (String) defaultAddress.get("first_name");
		String name2 = (String) defaultAddress.get("last_name");
		String phone = (String) customer.get("phone");
		String email = (String) customer.get("email");
		MBPartner businessPartner = new MBPartner(ctx, C_BPartner_ID, trxName);
		businessPartner.setClientOrg(Env.getAD_Client_ID(ctx), 0);
		businessPartner.setValue((String) defaultAddress.get("name"));
		businessPartner.setName(name.concat(" ").concat(name2));
		businessPartner.setName2(name2);
		businessPartner.setIsCustomer(true);
		businessPartner.setIsProspect(false);
		businessPartner.setIsVendor(false);
		businessPartner.setM_PriceList_ID((int)sfDefaults.get_Value("Intl_Excl_PriceList_ID"));
		
		businessPartner.setInvoiceRule(MBPartner.INVOICERULE_Immediate);
		businessPartner.setC_PaymentTerm_ID((int)sfDefaults.get_Value("Intl_Excl_PriceList_ID"));
		businessPartner.setDeliveryRule(MBPartner.DELIVERYRULE_CompleteOrder);		
		businessPartner.setPaymentRule(MBPartner.PAYMENTRULE_CreditCard);
		businessPartner.setDeliveryViaRule(MBPartner.DELIVERYVIARULE_Shipper);
		businessPartner.set_ValueNoCheck("C_OrderSource_ID", (int)sfDefaults.get_Value("C_OrderSource_ID"));	
		businessPartner.setC_BP_Group_ID((int)sfDefaults.get_Value("C_BP_Group_ID"));
		businessPartner.setC_PaymentTerm_ID((int)sfDefaults.get_Value("C_PaymentTerm_ID"));
		businessPartner.set_ValueNoCheck("DS_ShippingTerms", "DAP");
		businessPartner.saveEx();
		int C_Location_ID = createLocation(orderSf);
		int C_BPartner_Location_ID = createBPLocation(businessPartner.getC_BPartner_ID(), C_Location_ID);
		createUser(businessPartner, email, phone, C_BPartner_Location_ID);

		return businessPartner.get_ID();

	}

	private void createUser(MBPartner businessPartner, String email, String phone, int C_BPartner_Location_ID) {
		MUser user = new MUser(ctx, 0, trxName);
		user.setAD_Org_ID(0);
		user.setC_BPartner_ID(businessPartner.getC_BPartner_ID());
		user.setC_BPartner_Location_ID(C_BPartner_Location_ID);
		user.setName(businessPartner.getName());
		user.setEMail(email);
		user.setPhone(phone);
		user.setPhone2(phone);
		user.saveEx();
	}

	private int createLocation(Map<?, ?> orderSf) {
		Map<?, ?> customer = (Map<?, ?>) orderSf.get("customer");
		Map<?, ?> defaultAddress = (Map<?, ?>) customer.get("default_address");
		String countryCode = (String) defaultAddress.get("country_code");
		int c_country_id;
		if (isBlankOrNull(countryCode))
			c_country_id = (int) sfDefaults.get_Value("c_country_id");
		else
			c_country_id = DB.getSQLValue(trxName, "select c_country_id " + "from c_country " + "where countrycode = ?",
					countryCode);
		String address1 = (String) defaultAddress.get("address1");
		if (isBlankOrNull(address1))
			address1 = (String) sfDefaults.get_Value("address1");
		String address2 = (String) defaultAddress.get("address2");
		String city = (String) defaultAddress.get("city");
		if (isBlankOrNull(city))
			city = (String) sfDefaults.get_Value("city");
		String postal = (String) defaultAddress.get("zip");
		MLocation location = new MLocation(ctx, c_country_id, 0, city, trxName);
		location.setAD_Org_ID(0);
		location.setAddress1(address1);
		location.setAddress2(address2);
		location.setPostal(postal);
		location.saveEx();
		return location.get_ID();
	}

	private int createBPLocation(int C_BPartner_ID, int C_Location_ID) {
		MBPartnerLocation BPartnerLocation = new MBPartnerLocation(ctx, 0, trxName);
		BPartnerLocation.setAD_Org_ID(0);
		BPartnerLocation.setC_BPartner_ID(C_BPartner_ID);
		BPartnerLocation.setC_Location_ID(C_Location_ID);
		BPartnerLocation.setIsBillTo(true);
		BPartnerLocation.setIsShipTo(true);
		BPartnerLocation.saveEx();
		return BPartnerLocation.getC_BPartner_Location_ID();
	}

	public int getBPLocationId(int bp_Id) {
		int c_bpartner_location_id = DB.getSQLValue(trxName,
				"select c_bpartner_location_id " + "from C_BPartner_Location " + "where c_bpartner_id = ?", bp_Id);
		if (c_bpartner_location_id < 0) {
			log.severe("BP with id : " + bp_Id + " does not have a C_BPartner_Location on iDempiere");
			int c_bpartner_id = (int) sfDefaults.get_Value("c_bpartner_id");
			c_bpartner_location_id = DB.getSQLValue(trxName,
					"select c_bpartner_location_id " + "from C_BPartner_Location " + "where c_bpartner_id = ?",
					c_bpartner_id);
		}
		return c_bpartner_location_id;
	}

	public void completeOrder() {
		order.setDocAction(DocAction.ACTION_Complete);
		if (order.processIt(DocAction.ACTION_Complete)) {
			if (log.isLoggable(Level.FINE))
				log.fine("Order: " + order.getDocumentNo() + " completed fine");
		} else
			throw new IllegalStateException("Order: " + order.getDocumentNo() + " Did not complete");

		order.saveEx();
	}

	public String createOrderLine(Map<?, ?> line, Map<?, ?> orderSf) 
	{
		String description = "";
		MOrderLine orderLine = new MOrderLine(order);
		orderLine.setAD_Org_ID(order.getAD_Org_ID());
		// orderLine.setAD_Org_ID(11);
		
		int m_Product_ID = getProductId(line.get("sku").toString());
				
		if(m_Product_ID<=0)
		{
			m_Product_ID = (int) sfDefaults.get_Value("m_product_id");
			description = "Product not found from the SKU please correct."+line.get("sku")+"\n";
		}
		orderLine.setM_Product_ID(m_Product_ID);
		// orderLine.setC_UOM_ID(originalOLine.getC_UOM_ID());
		// orderLine.setC_Tax_ID(originalOLine.getC_Tax_ID());
		orderLine.setM_Warehouse_ID(order.getM_Warehouse_ID());
		orderLine.setC_Tax_ID(getTaxRate());
		orderLine.setDescription("SKU: "+line.get("sku")+" Name: "+line.get("name"));
		// orderLine.setC_Currency_ID(originalOLine.getC_Currency_ID());
		long qty = ((Number) line.get("quantity")).longValue();
		orderLine.setQty(BigDecimal.valueOf((long) qty));
		// orderLine.setC_Project_ID(originalOLine.getC_Project_ID());
		 orderLine.setC_Activity_ID(1000024);
		 orderLine.setUser2_ID(1001189);
		 orderLine.setUser1_ID(1001230);
		// orderLine.setC_Campaign_ID(originalOLine.getC_Campaign_ID());
		orderLine.setPrice(new BigDecimal(Double.parseDouble((String) line.get("price"))));
		orderLine.setPrice();
		if (!orderLine.save()) {
			throw new IllegalStateException("Could not create Order Line");
		}
		return description;
	}

	public int getProductId(String sku) {
		int m_Product_ID = DB.getSQLValue(trxName, "select m_product_id " + "from m_product " + "where upper(value) like ? AND AD_Client_ID = ? ",
		sku.toUpperCase(),Env.getAD_Client_ID(Env.getCtx()));
		
		//int m_Product_ID = DB.getSQLValue(trxName, "select m_product_id " + "from m_product" + "where upper(value) == ?",
		//sku.toUpperCase());
		return m_Product_ID;
			}
	
	
	public void createShippingCharge(Map<?, ?> orderWc) {
		BigDecimal shippingCost = getShippingCost(orderWc);
		if (shippingCost.compareTo(BigDecimal.ZERO) == 0) {
		return; // no need to create a shipping charge
		}
		
		MOrderLine orderLine = new MOrderLine(order);
		orderLine.setAD_Org_ID(order.getAD_Org_ID());
		orderLine.setC_Charge_ID((int) sfDefaults.get_Value("c_charge_id"));
		// orderLine.setC_UOM_ID(originalOLine.getC_UOM_ID());
		orderLine.setM_Warehouse_ID(order.getM_Warehouse_ID());
		orderLine.setC_Tax_ID(getTaxRate());
		// orderLine.setC_Currency_ID(originalOLine.getC_Currency_ID());
		orderLine.setQty(BigDecimal.ONE);
		orderLine.setPrice(shippingCost);

		if (!orderLine.save()) {
			throw new IllegalStateException("Could not create Order Line");
		}
	}

	public int getTaxRate() 
	{
		return (int) sfDefaults.get_Value("standard_tax_id");
	}

	public BigDecimal getShippingCost(Map<?, ?> orderWc) {
		List<?> shippingLines = (List<?>) orderWc.get("shipping_lines");
		Map<?, ?> shippingLine = (Map<?, ?>) shippingLines.get(0);
		Double total = Double.parseDouble((String) shippingLine.get("price"));
		
/*		List<?> taxLines = (List<?>) shippingLine.get("tax_lines");
		Map<?, ?> taxLine = (Map<?, ?>) taxLines.get(0);
		Double totalTax = Double.parseDouble((String) taxLine.get("price"));
		//Double totalTax = Double.parseDouble((String) shippingLine.get("total_tax"));
		BigDecimal shippingCost = (!isTaxInclusive) ? BigDecimal.valueOf((Double) total + totalTax)
				: BigDecimal.valueOf((Double) total); */
		BigDecimal shippingCost = BigDecimal.valueOf((Double) total);
		return (shippingCost.setScale(4, RoundingMode.HALF_EVEN));
	}

	public void createPosPayment(Map<?, ?> orderSf) {
		X_C_POSPayment posPayment = new X_C_POSPayment(ctx, null, trxName);
		posPayment.setC_Order_ID(order.getC_Order_ID());
		posPayment.setAD_Org_ID(order.getAD_Org_ID());
		posPayment.setPayAmt(new BigDecimal(orderSf.get("total_price").toString()));
		posPayment.setC_POSTenderType_ID(POSTENDERTYPE_ID); // credit card
		posPayment.setTenderType(X_C_Payment.TENDERTYPE_CreditCard); // credit card
		if (!posPayment.save())
			throw new IllegalStateException("Could not create POSPayment");
	}

	public static boolean isBlankOrNull(String str) {
		return (str == null || "".equals(str.trim()));
	}

}