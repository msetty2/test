package com.owls.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class TestData {

	// public static String Approver_employe_code =
	// getApproverEmployeeCodeFromExcel();
	// public static String Approver_password = getApproverPasswordFromExcel();

	/*
	 * ===================================== SIGN UP
	 * ====================================================
	 */

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	static int hour1 = TestData.randBetween(7, 12); // Hours will be displayed
													// in between 9 to 22
	static int min = TestData.randBetween(0, 59);

	static int hour2 = TestData.randBetween(13, 20);

	public static String intime = hour1 + ":" + min;
	public static String outtime = hour2 + ":" + min;

	public static int diff = (((hour2) * 60) + min) - (((hour1) * 60) + min);

	public static String total_time = Integer.toString(diff);

	public static Sheet getExcelSheet(int sheetIndex) {
		String dataFilePath = "Resource/Credential.xlsx";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {

			System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();

		} catch (Exception e) {

		}
		return firstSheet;
	}

	public static Sheet upload(int sheetIndex) {
		String dataFilePath = "Resource/firstname.txt";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {

			System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();

		} catch (Exception e) {

		}
		return firstSheet;
	}

	public static void WriteCandidateexcel1(String filename,int sheetNo, int rowNo, int columnNo, String value) {
		{
			String dataFilePath = "Resource/"+filename;
			String dataFilePath2 = "Resource";
			File datafile = new File(dataFilePath);
			String fullpath = datafile.getAbsolutePath();

			File datafile2 = new File(dataFilePath2);
			String fullpath2 = datafile2.getAbsolutePath();
			XSSFSheet firstSheet = null;
			try {

				System.out.println(
						"full path ::::" + datafile.getAbsolutePath() + " con :: " + datafile.getCanonicalPath());

				System.out.println(
						"full path ::::" + datafile2.getAbsolutePath() + " con :: " + datafile2.getCanonicalPath());

				FileInputStream inputStream = new FileInputStream(new File(fullpath));

				XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
				firstSheet = workbook.getSheetAt(sheetNo);

				// System.out.println("Row =
				// "+firstSheet.getRow(2).getCell(1).setCellValue(value));

				System.out.println("written Data :: " + value);
				try {
					firstSheet.getRow(rowNo).getCell(columnNo).setCellValue(value);

				} catch (Exception e) {
					// TODO: handle exception
					firstSheet.createRow(rowNo).createCell(columnNo).setCellValue(value);
				}

				/*
				 * for (int i = rowNo; i < 8; i++) {
				 * if(firstSheet.getRow(i)!=null) {
				 * System.out.println("========"); try {
				 * firstSheet.removeRow(firstSheet.getRow(i)); } catch
				 * (Exception e) { // TODO: handle exception break; }
				 * 
				 * firstSheet.removeRow(firstSheet.getRow(rowNo+2));
				 * firstSheet.removeRow(firstSheet.getRow(rowNo+3)); //}
				 * 
				 * }
				 */
				/*
				 * firstSheet.createRow(rowNo)
				 * .createCell(5).setCellValue(value);
				 */

				inputStream.close();
				FileOutputStream fos = new FileOutputStream(new File(fullpath));
				workbook.write(fos);

				FileOutputStream fileOut = new FileOutputStream(fullpath2);
				workbook.write(fileOut);
				
			workbook.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	// Admin
	public static String getAdminURL() {

		return getExcelSheet(0).getRow(1).getCell(1).getStringCellValue();
	}

	public static String getPortalUserName() {

		return getExcelSheet(0).getRow(2).getCell(1).getStringCellValue();
	}

	public static String getPortalPassword() {

		return getExcelSheet(0).getRow(3).getCell(1).getStringCellValue();
	}

	public static String getApproverChangepassword() {

		return getExcelSheet(9).getRow(1).getCell(4).getStringCellValue();
	}

	public static String getRandomDate()
	    {
	        
	    	Calendar cal = Calendar.getInstance();

			cal.add(Calendar.DAY_OF_MONTH, 1);

			int fromday = cal.get(Calendar.DAY_OF_MONTH);
			int fromyear = cal.get(Calendar.YEAR);
			
			cal.add(Calendar.MONTH, 2);
			
			String day=String.valueOf(getDate(fromday));
			String month=TestData.getMonthD(cal.get(Calendar.MONTH));
			String year=String.valueOf(fromyear);
			
			return day+" "+month+" "+year;	
	    }

	public static int getDate(int fromday) {

		if (fromday < 10) {

			return 10 + (int) Math.round(Math.random() * (30 - 10));
		} else {
			return fromday;
		}
	}

	public static String getMonthD(int month) {
		switch (month) {
		case 1:
			return "Jan";

		case 2:
			return "Feb";

		case 3:
			return "Mar";

		case 4:
			return "Apr";

		case 5:
			return "May";

		case 6:
			return "Jun";

		case 7:
			return "Jul";

		case 8:
			return "Aug";

		case 9:
			return "Sep";

		case 10:
			return "Oct";

		case 11:
			return "Nov";

		case 12:
			return "Dec";

		default:
			return "Invalid month";

		}
	}
	
	public static String getCity(){
		 
		  ArrayList<String> city = new ArrayList<String>();
		  
		  //city.add("Ahmedabad");
		  //city.add("Pune");
		  city.add("Surat");
		  city.add("Trivendram");
		  city.add("Melborn");
		  city.add("Sydney");
		  city.add("Perth");
		  city.add("Brisbane");
		  city.add("Kuchipudi");
		  city.add("Banglore");
		  city.add("Sreenagar");
		  
		  Random ran=new Random();
		  
		  int a=ran.nextInt(city.size()-1);
		  
		  return city.get(a);
		 }
	
	public static String getcandidatePassword() {

		return getExcelSheet(2).getRow(3).getCell(1).getStringCellValue();
	}
	
	public static String getcandidateUserNamePrefix() {

		return getExcelSheet(2).getRow(2).getCell(1).getStringCellValue();
	}
	
	public static String getcandidateEmail() {

		return getExcelSheet(2).getRow(4).getCell(1).getStringCellValue();
	}
	
	public static String getInvigilatorPassword() {

		return getExcelSheet(1).getRow(3).getCell(1).getStringCellValue();
	}
	
	public static String getCandidatePassword() {

		return getExcelSheet(2).getRow(3).getCell(1).getStringCellValue();
	}
	
	public static String getInvigilatorURL()
	{
		return getExcelSheet(1).getRow(1).getCell(0).getStringCellValue();
	}
	
	public static String getcandidateURL()
	{
		return getExcelSheet(2).getRow(1).getCell(1).getStringCellValue();
	}
	
	public static String getInvigilatorUserNamePrefix() {

		  return getExcelSheet(1).getRow(0).getCell(0).getStringCellValue();
		 }
	
	 public  String getValueFromConfig(String value,String filename) throws IOException {
	  String result="";
	  File file = new File(filename);
	  FileInputStream fileInput = null;
	  try {
	   fileInput = new FileInputStream(file);
	  } catch (FileNotFoundException e) {
	   e.printStackTrace();
	  }
	  Properties prop = new Properties();
	  try {
	    prop.load(fileInput);
	    result = prop.getProperty(value);
	  } catch (Exception e) {
	   System.out.println("Exception: " + e);
	  } finally {
	  }
	  return result;
	 }
	 
	 	public static String getCRMURL() {

		  return getExcelSheet(1).getRow(1).getCell(0).getStringCellValue();
		 }
		 
		 public static String getCRMUsername() {

		  return getExcelSheet(1).getRow(3).getCell(1).getStringCellValue();
		 }
		 
		 public static String getCRMPassword() {

		  return getExcelSheet(1).getRow(4).getCell(1).getStringCellValue();
		 }
		 
		 
		 public static String getCRMInspecterUsername() {

			  return getExcelSheet(2).getRow(3).getCell(1).getStringCellValue();
			 }
			 
		 
			 public static String getCRMInspectorPassword() {

			  return getExcelSheet(2).getRow(4).getCell(1).getStringCellValue();
			 }

			 public static String getCRMDelegateUsername() {

				  return getExcelSheet(3).getRow(3).getCell(1).getStringCellValue();
				 }
				 
			 
				 public static String getCRMDelegatePassword() {

				  return getExcelSheet(3).getRow(4).getCell(1).getStringCellValue();
				 }

}
