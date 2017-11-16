import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel {

		/*
		 System.out.println("-->> :");
		getCompanycodeFromExcel();
		*/
		
		public  static Sheet getExcelSheet(int sheetIndex) {
			String dataFilePath = "Resource/DataConfiguration.xlsx";
			File datafile = new File(dataFilePath);
			String fullpath = datafile.getAbsolutePath();
			Sheet firstSheet = null;

			try {

				System.out.println("full path " + datafile.getAbsolutePath()
						+ " con " + datafile.getCanonicalPath());

				FileInputStream inputStream = new FileInputStream(
						new File(fullpath));

				Workbook workbook = new XSSFWorkbook(inputStream);
				firstSheet = workbook.getSheetAt(sheetIndex);

				workbook.close();
				inputStream.close();

			} catch (Exception e) {

			}
			return firstSheet;
		}

		public static String getCompanycodeFromExcel() {
			
			DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
			 Cell cell = getExcelSheet(0).getRow(9).getCell(0);
			 String j_username = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type

			 System.out.println("-->> :" +j_username);
			 
			return j_username;
		}
		
	
	public static void main(String args[]){
		
		getCompanycodeFromExcel();
		
	}
	
	
}
