import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
	
public class ReadExcelFile {
	
	static Random randm = new Random();
	 
	 public static int rndmday() {
			int day = randm.nextInt(28-10)+10;
			return day;
		}
	 public static String rndmmonth() {
		 
		 ArrayList<String> month = new ArrayList<String>();
			
		 month.add("Jan");
		 month.add("Feb");
		 month.add("Mar");
		 month.add("Apr");
		 month.add("May");
		 month.add("Jun");
		 month.add("Jul");
		 month.add("Aug");
		 month.add("Sep");
		 month.add("Oct");
		 month.add("Nov");
		 month.add("Dec");
		 
		 int i = randm.nextInt(month.size());
		 	String month1 = month.get(i);
			return month1;
		}
	 public static int rndmyear() {
		 	int start = 1970;
		 	int end = 1997;
			int day = randm.nextInt(end-start)+start;
			return day;
		}
	 
	 public static String rndBDY() {
			String final_bday = new StringBuilder(20).append(rndmday())
					.append(rndmmonth()).append(rndmyear()).toString();

			System.out.println("========"+final_bday+"=======");
			return final_bday;
		}
	 static String removeurl = "";

	 public  static String firstname() throws IOException
	 {
			
				File file = new File("Resource/test.txt");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
//				 BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				StringBuffer stringBuffer = new StringBuffer();
				String currentline;
				while ((currentline = bufferedReader.readLine()) != null) {
					stringBuffer.append(currentline);
					stringBuffer.append("\n");
				
					break;
				}
				fileReader.close();
				System.out.println("Contents of file:::::::::::");
				System.out.println(stringBuffer.toString());
				
				return stringBuffer.toString();
			
	 }
	 
	 
	    public static void main(String[] args) throws IOException {
	    	
	 
			 ReadExcelFile rd = new ReadExcelFile();
	//	rd.firstname();
			 
		String s1 = "asdffdzlvhsjkvyufgwdhcbwuigfiwefc";
		String s2 =s1.substring(0, 5);
		System.out.println("======================="+s2);
		
				/*
	        GregorianCalendar gc = new GregorianCalendar();

	        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

	        gc.set(gc.DAY_OF_YEAR, dayOfYear);
	        
	        int year = randBetween(1900, 2010);

	        gc.set(gc.YEAR, year);

	      System.out.println(gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH));

	        System.out.println(gc.get(gc.DAY_OF_MONTH)+"-"+(gc.get(gc.MONTH) + 1)+gc.get(gc.YEAR));
	    }

	    public static int randBetween(int start, int end) {
	        return start + (int)Math.round(Math.random() * (end - start));
	    }*/
	    
	    }
	    
	}

