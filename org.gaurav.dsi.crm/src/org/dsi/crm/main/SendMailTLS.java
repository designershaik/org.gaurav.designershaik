package org.dsi.crm.main;

import java.math.BigDecimal;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {
   public static void main(String[] args) {
    
	   String version = "WA1255-C";
	   String abc = "WA1255AC";
	   BigDecimal a = new BigDecimal("10");
	   BigDecimal b = new BigDecimal("11");
	   if(a.compareTo(b)>1)
		   System.out.println("a is bigger than b");
	   if(a.compareTo(b)<1)
		   System.out.println("b is bigger than a");
	   if(a.compareTo(b)==0)
		   System.out.println("a is bigger than b");
	   
	   int index = version.indexOf("-");
	   System.out.println(" Character at "+index+ "  position is "+abc.substring(index,index+1));
	   
	   BigDecimal ab = new BigDecimal(-100);
	   System.out.println(ab.compareTo(new BigDecimal(0))<0);
   }
}