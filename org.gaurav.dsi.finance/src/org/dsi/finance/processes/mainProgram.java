package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.compiere.util.Env;
public class mainProgram {

	public static void main(String[] args) {
		
		BigDecimal QtyRequired = new BigDecimal(59);
		BigDecimal QtyAvailable = new BigDecimal(80);
//		System.out.println(new BigDecimal(100.00).signum());
//		System.out.println(new BigDecimal(100.20).signum()>=0);
//		System.out.println("String : "+a.setScale(2, RoundingMode.CEILING).toString());
//		System.out.println("Engineering string : "+a.setScale(2, RoundingMode.CEILING).toEngineeringString());
//		System.out.println("Plain string : "+a.setScale(2, RoundingMode.CEILING).toPlainString());
//		String b = " asd as d ";
//		String ab = "asc\r\ngaurav";
//		System.out.println(ab);
//		System.out.println(b.trim());
//		String c= "hjdg$h&jk8^/()!@^!@%i0ssh6";
//	    System.out.println(c);
//	    System.out.println(c.replace("\\p{Ps}|\\{Pe}", ""));
//	    String abb = "gaura1221v-123";
//	    System.out.println(abb.substring(0,abb.indexOf("-")).toUpperCase());
//	    System.out.println(Integer.MAX_VALUE);
//	  //Usage of Apache Commons Lang 3
//	    String cp = "CR";
//	    String bp = "BP";
//	    if(cp.endsWith("R"))
//	    {
//	    	System.out.println("True");
//	    }
//	    if(bp.endsWith("R"))
//	    {
//	    	System.out.println("false");
//	    }
//	    int i=1; // Line 2
//		Object obj = new Object(); // Line 3
//		main mem = new main(); // Line 4
//		mem.foo(obj); // Line 5
//		System.out.println("Comparison  "+QtyRequired.compareTo(QtyAvailable));
		String link = "123^12345^12^1233456789";
//		
//
//		String repo = link.substring(0, link.indexOf("^"));
//		int legnthToTheRepo = repo.length();
//		
//		String repoPath = link.substring(legnthToTheRepo,link.length()).substring(legnthToTheRepo, link.indexOf("^"));
//		int lengthToRepoPath = repoPath.length();
//
//		String fileVersion = link.substring(lengthToRepoPath, link.indexOf("^"));
//		int lengthTofileVersion = fileVersion.length();
//
//		String repoVersion = link.substring(lengthTofileVersion, link.indexOf("^"));
//		
//		System.out.println("Repo : "+repo+" Repo Path: "+repoPath+" File version : "+fileVersion+" Repo Version "+repoVersion);
//		
//		BigDecimal a = new BigDecimal("10.8565");
//		BigDecimal b = new BigDecimal("10.994");
//		Integer abc = Integer.parseInt(a.remainder(new BigDecimal(1)).
//				setScale(2, RoundingMode.HALF_UP).toString().substring(2));
//		System.out.println("A "+abc);
//		int hour = abc/60;
//		int minutes = abc % 60;
//		String min = minutes<10 ? "0"+minutes:Integer.toString(minutes);
//		System.out.println("Hour "+hour+" Hour from actual value"+a.intValue()+" Minutes "+min);
//		HashSet<Integer> hash = new HashSet<Integer>() ;
//		hash.add(10);
//		hash.add(20);
//		hash.add(10);
//		for(Integer i : hash)
//		{
//			System.out.println(i);
//		}
//		System.out.println(a.setScale(2, RoundingMode.CEILING));
//		System.out.println(a.setScale(2, RoundingMode.HALF_UP));
//		System.out.println("-----------------------------------------------------------------");
//		System.out.println(b.setScale(2, RoundingMode.CEILING));
//		System.out.println(b.setScale(2, RoundingMode.HALF_UP));
//		System.out.println(a.toString().replace(".", ":"));
//		for(int k =0; k<10;k++)
//		{
//			System.out.println("Value of i "+k);
//		}
		
//		(0[1-9])|(1[012])
//			System.out.print("\"Hello\"");[-A-Z][0-9X][0-9][A-Z]
		String srNo = "123456 78 9 10";
		String regEpression = "WA[0-9]{3}[-A-Z][0-9]{2}";
		System.out.println(Pattern.matches(regEpression, "WA001B20"));
//		System.out.println(srNo.indexOf("9"));
//		System.out.println(srNo.concat(null));
//		System.out.println(new BigDecimal("180.5").divideToIntegralValue(new BigDecimal("50")));
//		System.out.println(new BigDecimal("180").setScale(2, RoundingMode.CEILING));
//		System.out.println("123456".substring(0, 2));
//		System.out.println("123456".substring(2, 4));
//		System.out.println("123456".substring(4, 6));
//		System.out.println("123456".substring(0,1));
		
		 String string = new BigDecimal("1.36544").stripTrailingZeros().toPlainString();
		 int index = string.indexOf(".");
		 int decimalPlaces = index < 0 ? 0 : string.length() - index - 1;
		 String a = "\\\\1921681016\\ProductImages\\GASKET-02-0Ijpg";
		 
		 int lastIndex = a.lastIndexOf(".");
		 System.out.println(a.substring(lastIndex+1,a.length()));
		 
		 System.out.println(decimalPlaces);
		 
		 
		 BigDecimal openAmt = new BigDecimal(140);
		 System.out.println("Integer amt : "+openAmt.divide(new BigDecimal(50)).intValue());
		 BigDecimal penaltyRate = openAmt.multiply(new BigDecimal(0.01)).setScale(5, RoundingMode.HALF_UP);
		 BigDecimal agingInBD = new BigDecimal(1635);
		 BigDecimal months = agingInBD.divide(new BigDecimal(365), 5, RoundingMode.HALF_UP).multiply(new BigDecimal(12));
		 BigDecimal penalty = penaltyRate.multiply(months).compareTo(Env.ZERO)<0 ? Env.ZERO:penaltyRate.multiply(months);
//		 totalPenaltyAmt = totalPenaltyAmt.add(penalty);
		 System.out.println("Opent Amt: "+openAmt+" penalty Rate: "+penaltyRate+" Aging in months: "+months+" Actual Penalty: "+penalty.setScale(2, RoundingMode.HALF_UP));
	   }
	}

