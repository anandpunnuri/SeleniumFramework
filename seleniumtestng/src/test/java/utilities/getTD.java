package utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getTD 
{
public static XSSFSheet sheet;
public static XSSFCell cell;

public static String getCellData(XSSFWorkbook workbook, XSSFSheet sheetname, int rownum, int colnum)
{
	//sheet = workbook.getSheet(sheetname);
	cell = sheetname.getRow(rownum).getCell(colnum);
	String celldata = cell.getRichStringCellValue().toString();
	return celldata;	
}


public static Object[][] getTableArray(XSSFWorkbook workbook, String sheetname, String testcasename)
{
	String[][] obj= null;
	int di = 0,dj = 0;
	sheet = workbook.getSheet(sheetname);
	System.out.println(sheet.getSheetName());
	int totalrows = sheet.getPhysicalNumberOfRows();
	System.out.println(totalrows);
	XSSFRow currentrow;
	int testcasecount = 0;
	int numcols=0;
	for(int i=1;i<totalrows;i++)
	{
		String testname = sheet.getRow(i).getCell(0).getRichStringCellValue().toString();
		if(testname.equalsIgnoreCase(testcasename))
		{
			numcols=sheet.getRow(i).getPhysicalNumberOfCells();
			testcasecount++;
		}
	}
	obj=new String[testcasecount][numcols-1];
	
	for(int i=1;i<totalrows;i++)
	{
		String testname = sheet.getRow(i).getCell(0).getRichStringCellValue().toString();
		if(testname.equalsIgnoreCase(testcasename))
		{
			currentrow = sheet.getRow(i);
			System.out.println("current row :"+currentrow.getRowNum());
			System.out.println("Number of columns: "+currentrow.getPhysicalNumberOfCells());
			
			for(int j=1;j<currentrow.getPhysicalNumberOfCells();j++)
			{	
				System.out.println(currentrow.getCell(j).getStringCellValue());
				obj[di][dj] = currentrow.getCell(j).getStringCellValue();//getTD.getCellData(workbook, sheet, i, j);
				dj++;
			}
			di++;
			dj=0;
		}
	}
	return obj;	
}

}
