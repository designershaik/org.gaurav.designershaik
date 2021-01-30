/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for GS_ImportAttendance
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_ImportAttendance 
{

    /** TableName=GS_ImportAttendance */
    public static final String Table_Name = "GS_ImportAttendance";

    /** AD_Table_ID=1000238 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DS_Day1 */
    public static final String COLUMNNAME_DS_Day1 = "DS_Day1";

	/** Set Day 1	  */
	public void setDS_Day1 (BigDecimal DS_Day1);

	/** Get Day 1	  */
	public BigDecimal getDS_Day1();

    /** Column name DS_Day10 */
    public static final String COLUMNNAME_DS_Day10 = "DS_Day10";

	/** Set Day 10	  */
	public void setDS_Day10 (BigDecimal DS_Day10);

	/** Get Day 10	  */
	public BigDecimal getDS_Day10();

    /** Column name DS_Day11 */
    public static final String COLUMNNAME_DS_Day11 = "DS_Day11";

	/** Set Day 11	  */
	public void setDS_Day11 (BigDecimal DS_Day11);

	/** Get Day 11	  */
	public BigDecimal getDS_Day11();

    /** Column name DS_Day12 */
    public static final String COLUMNNAME_DS_Day12 = "DS_Day12";

	/** Set Day 12	  */
	public void setDS_Day12 (BigDecimal DS_Day12);

	/** Get Day 12	  */
	public BigDecimal getDS_Day12();

    /** Column name DS_Day13 */
    public static final String COLUMNNAME_DS_Day13 = "DS_Day13";

	/** Set Day 13	  */
	public void setDS_Day13 (BigDecimal DS_Day13);

	/** Get Day 13	  */
	public BigDecimal getDS_Day13();

    /** Column name DS_Day14 */
    public static final String COLUMNNAME_DS_Day14 = "DS_Day14";

	/** Set Day 14	  */
	public void setDS_Day14 (BigDecimal DS_Day14);

	/** Get Day 14	  */
	public BigDecimal getDS_Day14();

    /** Column name DS_Day15 */
    public static final String COLUMNNAME_DS_Day15 = "DS_Day15";

	/** Set Day 15	  */
	public void setDS_Day15 (BigDecimal DS_Day15);

	/** Get Day 15	  */
	public BigDecimal getDS_Day15();

    /** Column name DS_Day16 */
    public static final String COLUMNNAME_DS_Day16 = "DS_Day16";

	/** Set Day 16	  */
	public void setDS_Day16 (BigDecimal DS_Day16);

	/** Get Day 16	  */
	public BigDecimal getDS_Day16();

    /** Column name DS_Day17 */
    public static final String COLUMNNAME_DS_Day17 = "DS_Day17";

	/** Set Day 17	  */
	public void setDS_Day17 (BigDecimal DS_Day17);

	/** Get Day 17	  */
	public BigDecimal getDS_Day17();

    /** Column name DS_Day18 */
    public static final String COLUMNNAME_DS_Day18 = "DS_Day18";

	/** Set Day 18	  */
	public void setDS_Day18 (BigDecimal DS_Day18);

	/** Get Day 18	  */
	public BigDecimal getDS_Day18();

    /** Column name DS_Day19 */
    public static final String COLUMNNAME_DS_Day19 = "DS_Day19";

	/** Set Day 19	  */
	public void setDS_Day19 (BigDecimal DS_Day19);

	/** Get Day 19	  */
	public BigDecimal getDS_Day19();

    /** Column name DS_Day2 */
    public static final String COLUMNNAME_DS_Day2 = "DS_Day2";

	/** Set Day 2	  */
	public void setDS_Day2 (BigDecimal DS_Day2);

	/** Get Day 2	  */
	public BigDecimal getDS_Day2();

    /** Column name DS_Day20 */
    public static final String COLUMNNAME_DS_Day20 = "DS_Day20";

	/** Set Day 20	  */
	public void setDS_Day20 (BigDecimal DS_Day20);

	/** Get Day 20	  */
	public BigDecimal getDS_Day20();

    /** Column name DS_Day21 */
    public static final String COLUMNNAME_DS_Day21 = "DS_Day21";

	/** Set Day 21	  */
	public void setDS_Day21 (BigDecimal DS_Day21);

	/** Get Day 21	  */
	public BigDecimal getDS_Day21();

    /** Column name DS_Day22 */
    public static final String COLUMNNAME_DS_Day22 = "DS_Day22";

	/** Set Day 22	  */
	public void setDS_Day22 (BigDecimal DS_Day22);

	/** Get Day 22	  */
	public BigDecimal getDS_Day22();

    /** Column name DS_Day23 */
    public static final String COLUMNNAME_DS_Day23 = "DS_Day23";

	/** Set Day 23	  */
	public void setDS_Day23 (BigDecimal DS_Day23);

	/** Get Day 23	  */
	public BigDecimal getDS_Day23();

    /** Column name DS_Day24 */
    public static final String COLUMNNAME_DS_Day24 = "DS_Day24";

	/** Set Day 24	  */
	public void setDS_Day24 (BigDecimal DS_Day24);

	/** Get Day 24	  */
	public BigDecimal getDS_Day24();

    /** Column name DS_Day25 */
    public static final String COLUMNNAME_DS_Day25 = "DS_Day25";

	/** Set Day 25	  */
	public void setDS_Day25 (BigDecimal DS_Day25);

	/** Get Day 25	  */
	public BigDecimal getDS_Day25();

    /** Column name DS_Day26 */
    public static final String COLUMNNAME_DS_Day26 = "DS_Day26";

	/** Set Day 26	  */
	public void setDS_Day26 (BigDecimal DS_Day26);

	/** Get Day 26	  */
	public BigDecimal getDS_Day26();

    /** Column name DS_Day27 */
    public static final String COLUMNNAME_DS_Day27 = "DS_Day27";

	/** Set Day 27	  */
	public void setDS_Day27 (BigDecimal DS_Day27);

	/** Get Day 27	  */
	public BigDecimal getDS_Day27();

    /** Column name DS_Day28 */
    public static final String COLUMNNAME_DS_Day28 = "DS_Day28";

	/** Set Day 28	  */
	public void setDS_Day28 (BigDecimal DS_Day28);

	/** Get Day 28	  */
	public BigDecimal getDS_Day28();

    /** Column name DS_Day29 */
    public static final String COLUMNNAME_DS_Day29 = "DS_Day29";

	/** Set Day 29	  */
	public void setDS_Day29 (BigDecimal DS_Day29);

	/** Get Day 29	  */
	public BigDecimal getDS_Day29();

    /** Column name DS_Day3 */
    public static final String COLUMNNAME_DS_Day3 = "DS_Day3";

	/** Set Day 3	  */
	public void setDS_Day3 (BigDecimal DS_Day3);

	/** Get Day 3	  */
	public BigDecimal getDS_Day3();

    /** Column name DS_Day30 */
    public static final String COLUMNNAME_DS_Day30 = "DS_Day30";

	/** Set Day 30	  */
	public void setDS_Day30 (BigDecimal DS_Day30);

	/** Get Day 30	  */
	public BigDecimal getDS_Day30();

    /** Column name DS_Day31 */
    public static final String COLUMNNAME_DS_Day31 = "DS_Day31";

	/** Set Day 31	  */
	public void setDS_Day31 (BigDecimal DS_Day31);

	/** Get Day 31	  */
	public BigDecimal getDS_Day31();

    /** Column name DS_Day4 */
    public static final String COLUMNNAME_DS_Day4 = "DS_Day4";

	/** Set Day 4	  */
	public void setDS_Day4 (BigDecimal DS_Day4);

	/** Get Day 4	  */
	public BigDecimal getDS_Day4();

    /** Column name DS_Day5 */
    public static final String COLUMNNAME_DS_Day5 = "DS_Day5";

	/** Set Day 5	  */
	public void setDS_Day5 (BigDecimal DS_Day5);

	/** Get Day 5	  */
	public BigDecimal getDS_Day5();

    /** Column name DS_Day6 */
    public static final String COLUMNNAME_DS_Day6 = "DS_Day6";

	/** Set Day 6	  */
	public void setDS_Day6 (BigDecimal DS_Day6);

	/** Get Day 6	  */
	public BigDecimal getDS_Day6();

    /** Column name DS_Day7 */
    public static final String COLUMNNAME_DS_Day7 = "DS_Day7";

	/** Set Day 7	  */
	public void setDS_Day7 (BigDecimal DS_Day7);

	/** Get Day 7	  */
	public BigDecimal getDS_Day7();

    /** Column name DS_Day8 */
    public static final String COLUMNNAME_DS_Day8 = "DS_Day8";

	/** Set Day 8	  */
	public void setDS_Day8 (BigDecimal DS_Day8);

	/** Get Day 8	  */
	public BigDecimal getDS_Day8();

    /** Column name DS_Day9 */
    public static final String COLUMNNAME_DS_Day9 = "DS_Day9";

	/** Set Day 9	  */
	public void setDS_Day9 (BigDecimal DS_Day9);

	/** Get Day 9	  */
	public BigDecimal getDS_Day9();

    /** Column name GS_HR_MonthlyAttendance_ID */
    public static final String COLUMNNAME_GS_HR_MonthlyAttendance_ID = "GS_HR_MonthlyAttendance_ID";

	/** Set Monthly Attendance	  */
	public void setGS_HR_MonthlyAttendance_ID (int GS_HR_MonthlyAttendance_ID);

	/** Get Monthly Attendance	  */
	public int getGS_HR_MonthlyAttendance_ID();

	public I_GS_HR_MonthlyAttendance getGS_HR_MonthlyAttendance() throws RuntimeException;

    /** Column name GS_ImportAttendance_ID */
    public static final String COLUMNNAME_GS_ImportAttendance_ID = "GS_ImportAttendance_ID";

	/** Set Import Attendance	  */
	public void setGS_ImportAttendance_ID (int GS_ImportAttendance_ID);

	/** Get Import Attendance	  */
	public int getGS_ImportAttendance_ID();

    /** Column name GS_ImportAttendance_UU */
    public static final String COLUMNNAME_GS_ImportAttendance_UU = "GS_ImportAttendance_UU";

	/** Set GS_ImportAttendance_UU	  */
	public void setGS_ImportAttendance_UU (String GS_ImportAttendance_UU);

	/** Get GS_ImportAttendance_UU	  */
	public String getGS_ImportAttendance_UU();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
