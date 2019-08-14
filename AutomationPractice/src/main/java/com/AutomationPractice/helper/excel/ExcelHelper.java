package com.AutomationPractice.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.AutomationPractice.helper.logger.LoggerHelper;

public class ExcelHelper {
	private Logger log = LoggerHelper.getLogger(ExcelHelper.class);

	
	public static Object[][] getExcelData(String excelLocation, String sheetName) {
		try {
			Object dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get sheet name from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// Get active rows in excel sheet
			int rowCount = sheet.getLastRowNum();
			// Get active columns in the row
			int columnCount = sheet.getRow(0).getLastCellNum();
			dataSets = new Object[rowCount + 1][columnCount];
			// Iterate through each row in the sheet
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				// For each row, iterate through each column
				Row row = (Row) rowIterator.next();
				if(row.getRowNum()==0) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					j++;
					Cell cell = (Cell) cellIterator.next();
					switch (cell.getCellType()) {
					case STRING:
						dataSets[i - 1][j - 1] = cell.getStringCellValue();
						break;
					case NUMERIC:
						dataSets[i - 1][j - 1] = cell.getNumericCellValue();
						break;
					case BOOLEAN:
						dataSets[i - 1][j - 1] = cell.getBooleanCellValue();
						break;
					case FORMULA:
						dataSets[i - 1][j - 1] = cell.getCellFormula();
						break;
					default:
						System.out.println("No matching ENUM type found");
						break;
					}
				}
			}
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateResults(String excelLocation, String sheetName, String testCaseName, String testStatus) {

		try {
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create workbook instances
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get sheet name from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getLastRowNum() + 1;
			// int columnCount = sheet.getRow(0).getLastCellNum();
			for (int i = 1; i <= rowCount; i++) {
				XSSFRow row = sheet.getRow(i);
				String cell = row.getCell(0).getStringCellValue();
				if (cell.contains(testCaseName)) {
					row.createCell(1).setCellValue(testStatus);
					file.close();
					log.info("Result updated...");
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
