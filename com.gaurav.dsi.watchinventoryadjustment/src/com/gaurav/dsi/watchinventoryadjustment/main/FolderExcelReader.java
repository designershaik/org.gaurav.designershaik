package com.gaurav.dsi.watchinventoryadjustment.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FolderExcelReader {
    public static void main(String[] args) {
        // Specify the folder path containing Excel files
        File folder = new File("/Users/gauravsontakke/Documents/spieretech/clients/DSI/watchinventory/FINAL"); // Change this to your folder path

        // Filter to process only Excel files
        File[] excelFiles = folder.listFiles(file -> file.isFile() && file.getName().endsWith(".xlsx"));

        if (excelFiles != null && excelFiles.length > 0) 
        {
        	for (File file : excelFiles) 
        	{
        		String fileName = file.getName();
        		
                System.out.println("Processing file: " + fileName.substring(0, file.getName().indexOf("_")));
                readExcelFile(file);
            }
        } else {
            System.out.println("No Excel files found in the folder.");
        }
    }

    // Method to read an Excel file
    private static void readExcelFile(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows
            for (Row row : sheet) {
                // Read individual columns (cells) from the row
                Cell cell1 = row.getCell(0); // Column A (index 0)
                Cell cell2 = row.getCell(1); // Column B (index 1)
                Cell cell3 = row.getCell(3); // Column A (index 0)
//                Cell cell4 = row.getCell(4); // Column B (index 1)
//                Cell cell5 = row.getCell(5); // Column B (index 1)
                System.out.println("Processing file: " + getCellValue(cell1).substring(0,getCellValue(cell1).indexOf("_")));
                // Print cell values
//                System.out.print(getCellValue(cell1) + "\t");
//                System.out.println(getCellValue(cell2)+ "\t");
//                System.out.print(getCellValue(cell3) + "\t");
//                System.out.print(getCellValue(cell4) + "\t");
//                System.out.print(getCellValue(cell5));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getName());
            e.printStackTrace();
        }
    }

    // Helper method to get cell value as String
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Format date as needed
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}