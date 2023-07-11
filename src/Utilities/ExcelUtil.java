package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private XSSFSheet excelWSheet;
	private XSSFWorkbook excelWBook;
	private XSSFCell cellGlobal;
	private XSSFRow rowGlobal;
	int reqcellrowno;
	int reqcellcolno;

	public void setExcelFile(String path, String sheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream excelFile = new FileInputStream(path);
			// Access the required test data sheet
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			// LOGGER.error("Error while intializing the excel file " + e);
			printConsole("Error while intializing the excel file " + e);
		}
	}

	public void printConsole(String msg) {
		System.out.println(msg);
	}

	public Map<String, String> getSheetData(String tcID, String sheetName) {
		final List<String> rowData = new ArrayList<String>();
		final LinkedHashMap<String, String> rowVal = new LinkedHashMap<String, String>();
		Object value;
		final Sheet sheet = getSheet(sheetName);
		final List<String> coulmnNames = getColumns(sheet);
		final int totalRows = sheet.getPhysicalNumberOfRows();
		final Row row = sheet.getRow(0);
		final int firstCellNum = row.getFirstCellNum();
		final int lastCellNum = row.getLastCellNum();
		for (int i = 1; i < totalRows; i++) {
			final Row rows = sheet.getRow(i);
			final String testLinkID = rows.getCell(0).getStringCellValue();
			if (tcID.equalsIgnoreCase(testLinkID)) {
				for (int j = firstCellNum; j < lastCellNum; j++) {
					final Cell cell = rows.getCell(j);
					if (cell == null || cell.getCellType() == CellType.BLANK) {
						rowData.add("");
					} else if (cell.getCellType() == CellType.NUMERIC) {
						final Double val = cell.getNumericCellValue();
						value = val.intValue();
						rowData.add(value.toString());
					} else if (cell.getCellType() == CellType.STRING) {
						rowData.add(cell.getStringCellValue());
					} else if (cell.getCellType() == CellType.FORMULA) {
						rowData.add(cell.getStringCellValue());
					} else if (DateUtil.isCellDateFormatted(cell)) {
						rowData.add(cell.getDateCellValue().toString());
					} else if (cell.getCellType() == CellType.BOOLEAN
							|| cell.getCellType() == CellType.ERROR)
							 {
						try {
							throw new Exception(" Cell Type is not supported ");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					rowVal.put(coulmnNames.get(j), rowData.get(j).trim());

				}
				break;
			}

		}
		return rowVal;

	}
	
	
	private Sheet getSheet(String sheetName)
	{
		return excelWBook.getSheet(sheetName);
	}
	
	private List<String> getColumns(Sheet sheet) {
		final Row row = sheet.getRow(0);
		final List<String> columnValues = new ArrayList<String>();
		final int firstCellNum = row.getFirstCellNum();
		final int lastCellNum = row.getLastCellNum();
		for (int i = firstCellNum; i < lastCellNum; i++) {
			final Cell cell = row.getCell(i);
			columnValues.add(cell.getStringCellValue());
		}
		return columnValues;
	}

}
