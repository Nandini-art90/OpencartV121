package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	
	//To capture excel file/sheet location/path automatically
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	
	//To get row count
	public  int getRowCount(String sheetName) throws IOException//Method to get total number of rows in workbook
	{//String xfile=Location of the excel file/workbook
		//String xsheet=Location of the sheet in the workbook
		fi=new FileInputStream(path);//Opens excel file
		workbook=new XSSFWorkbook(fi);//opens workbook
		sheet=workbook.getSheet(sheetName);//opens mentioned sheet
		int rowcount=sheet.getLastRowNum();//returns last row number which is equal to taotal number of rows
		workbook.close();//closes workbook
	     fi.close();//closes file
	     return rowcount;//returns total number of rows
	
	}
	
	
	//To get column count
	public  int getCellCount(String sheetName,int rownum) throws IOException//Method to get total number of cells in a row
	{//String xfile=Location of the excel file/workbook
		//String xsheet=Location of the sheet in the workbook//int rownum=rownumber in which we want cell count
		fi=new FileInputStream(path);//Opens excel file
		workbook=new XSSFWorkbook(fi);//opens workbook
		sheet=workbook.getSheet(sheetName);//opens mentioned sheet
		row=sheet.getRow(rownum);//Gets row number
		int cellcount=row.getLastCellNum();//returns last row number which is equal to taotal number of rows
		workbook.close();//closes workbook
	     fi.close();//closes file
	     return cellcount;//returns total number of rows
	
	}
	
	//To get cell data
	public  String getCellData(String sheetName,int rownum,int colnum) throws IOException//Method to read data from cell
	{//String xfile=Location of the excel file/workbook
		//String xsheet=Location of the sheet in the workbook//int rownum=rownumber in which we want cell count
		fi=new FileInputStream(path);//Opens excel file
		workbook=new XSSFWorkbook(fi);//opens workbook
		sheet=workbook.getSheet(sheetName);//opens mentioned sheet
		row=sheet.getRow(rownum);//Gets row number
		cell=row.getCell(colnum);//gets column number
		
	
		DataFormatter formatter=new DataFormatter();//class from apache api to read data from cell.
		
		String data;
		try
		{
			
			
			data=formatter.formatCellValue(cell);//Reads any data cell  in the form of string.It is similar to tostring
			
			
		}
		catch(Exception e)
		{
			data=" ";//to catch datanotfoundexception if cell is null(" "=empty string)
		}
		workbook.close();
		fi.close();
		return data;
	
	}
	
	public  void setCellData(String xfile,String sheetName,int rownum,int colnum,String data) throws IOException//Method to write  data into the cell
	{
		//To verify file exist or not
		File xlfile=new File(path);
		if(xlfile.exists())
		{
			workbook=new XSSFWorkbook(fi);//opens workbook
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi=new FileInputStream(path);//Opens excel file
		workbook=new XSSFWorkbook(fi);
		if(workbook.getSheetIndex(sheetName)==-1)//If sheet not exist ,then create new sheet
		{
			workbook.createSheet(sheetName);
			sheet=workbook.getSheet(sheetName);
		}
		
		if(sheet.getRow(rownum)==null)//If row not exist,create new row
		{
			sheet.createRow(rownum);
			row=sheet.getRow(rownum);
		}
		
		
		//Write data
		cell=row.createCell(colnum);//gets column number
		cell.setCellValue(data);//writes data into cell
		fo=new FileOutputStream(path);
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	
	//To set red colour to cell
	public void FillGreenColor(String sheetName,int rownum,int colnum) throws IOException//Method to fill cell with color(green)
	{//String xfile=Location of the excel file/workbook
		//String xsheet=Location of the sheet in the workbook//int rownum=rownumber in which we want cell count
		//int colnum=cell number in which we want to write the data.String data=Data we want to pass to cell
		fi=new FileInputStream(path);//Opens excel file
		workbook=new XSSFWorkbook(fi);//opens workbook
		sheet=workbook.getSheet(sheetName);//opens mentioned sheet
		row=sheet.getRow(rownum);//Gets row number
		cell=row.getCell(colnum);
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	
	//To set red colour to cell
	public void FillRedColor(String sheetName,int rownum,int colnum) throws IOException//Method to fill cell with color(green)
	{//String xfile=Location of the excel file/workbook
		//String xsheet=Location of the sheet in the workbook//int rownum=rownumber in which we want cell count
		//int colnum=cell number in which we want to write the data.String data=Data we want to pass to cell
		fi=new FileInputStream(path);//Opens excel file
		workbook=new XSSFWorkbook(fi);//opens workbook
		sheet=workbook.getSheet(sheetName);//opens mentioned sheet
		row=sheet.getRow(rownum);//Gets row number
		cell=row.getCell(colnum);
		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
