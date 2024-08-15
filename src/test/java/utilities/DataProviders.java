package utilities;

	import java.io.IOException;

	import org.testng.annotations.DataProvider;

	public class DataProviders {

		//DataProvider 1
		
		@DataProvider(name="LoginData")//name of data provider
		public String [][] getData() throws IOException
		{
			String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData folder. .\= Gives current project location
			
			
			ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility class.It takes path and same will be passed tp excel utility class.
			
			int totalrows=xlutil.getRowCount("Sheet1");	//to gettotal number of rows
			int totalcols=xlutil.getCellCount("Sheet1",1);//to get cell count of row 1
					
			String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password from excel file.
			//totalrows and totalcols should be same as excel file rows and columns
			
			for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array.//Starting from row 1 as we ignore header row
			{		
				for(int j=0;j<totalcols;j++)  //0    i is rows j is col
				{
					logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j); //Getting data from excel file i row and j column,store that in array	
					//i-1 as array start from 0 and row started from 1.
					}
			}
		return logindata;//returning two dimension array
					
		}
		
		//DataProvider 2
		
		//DataProvider 3
		
		//DataProvider 4
	}



