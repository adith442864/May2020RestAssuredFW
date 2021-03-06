package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;

	public static String TESTDATA_SHEET_PATH = "D:\\Workspace\\May2020RestAssuredFramework"
			+ "\\src\\main\\java\\com\\qa\\api\\gorest\\testdata\\GoRestTestData.xlsx";
	
	
	public static Object[][] getTestData(String sheetName) {

		FileInputStream file = null;

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// for loop:
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) { // rows
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) { //cols
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString(); //fill the data.
			}
		}

		return data;

	}

}
