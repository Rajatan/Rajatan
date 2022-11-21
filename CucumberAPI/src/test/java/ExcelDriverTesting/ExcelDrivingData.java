package ExcelDriverTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelDrivingData {

	@Test
	public void excelAccess() throws IOException {
		ExcelDrivingData excelData=new ExcelDrivingData();
		ArrayList<String> data=excelData.getData();
		System.out.println(data);
	}
	
	
	public ArrayList<String> getData() throws IOException {

		ArrayList<String> dataRow=new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("E:\\Rajat Selenium\\Rest API notes\\ExcelDrivenTesting.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		int column = 0;
		int sheets = workBook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("testCases")) {
				XSSFSheet sheet = workBook.getSheetAt(i);
				// Identify testcases column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					if (cell.getStringCellValue().equalsIgnoreCase("TestCases")) {
						column = cell.getColumnIndex();
					}
				}
				System.out.println("Column: " + column);
//				Once column is identified then scan the entire TestCases column to identify Purchase 
				while (rows.hasNext()) {
					Row row = rows.next();
					if (row.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase")) {
						Iterator<Cell> purchaseRow = row.cellIterator();
						while (purchaseRow.hasNext()) {
//							After you grab Purchase TestCase row, then pull all the data of that rows iterating cell by cell
//							System.out.println(purchaseRow.next().getStringCellValue());
							
//							What if the excel cell contains Integer, float or double ie, any Numeric data
//							the getStringCellValue doesn't work so will have to check the Type of the Data that is present 
//							in the Excel cell before we extract. 
//							if it is Numeric data the will have to extract it from getNumericCellValue() method else getStringCellValue() method
							
							Cell cellData = purchaseRow.next();
							
							if(cellData.getCellType()==CellType.STRING) {
								dataRow.add(cellData.getStringCellValue());

							}
							else {
								
//							The extracted numeric data can't be passed to ArrayList as it's limited to String so will have to convert
//							that cell extracted from getNumericCellValue() method to String to do that POI has NumberToTextConverter call
//							and to convert to String it has toText() method.
//							
							String numbericToString = NumberToTextConverter.toText(cellData.getNumericCellValue());
							dataRow.add(numbericToString);
							}
						}
					}

				}
			}

		}
		return dataRow;

	
	}

}
