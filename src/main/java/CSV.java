
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSV {
	static String newFile = "";
	public String geturl() throws IOException {
		// TODO Auto-generated method stub
		String currentLine = "";
		File inputFile = new File("Resource/test.txt");
		

		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		
	
		currentLine = reader.readLine();

		String removeurl = currentLine.trim();
//		System.out.println("current line : " + currentLine);
		while ((currentLine) != null) {
			// trim newline when comparing with lineToRemove
			
			String trimmedLine = currentLine.trim();
			
			if (trimmedLine.equals(removeurl)) {
				currentLine = reader.readLine();
				continue;
			}else{
				//writer.write(currentLine + System.getProperty("line.separator"));
				if(newFile=="")
				{
					//newFile = newFile+"\n"+currentLine;
					newFile = currentLine;
				}else
				{
					newFile = newFile+"\n"+currentLine;
				}
					
				
				currentLine = reader.readLine();
			}
			
		}
		reader.close();
		deleteUrl();
		return removeurl;
	}
	
	public void deleteUrl() throws IOException
	{
	//	System.out.println("New File : \n"+newFile);
		File tempFile = new File("Resource/test.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		writer.write(newFile);
		writer.close();
		
		//boolean successful = tempFile.renameTo(inputFile);
	}

public static void main(String args[]) throws IOException
{
	
	CSV c = new CSV();
	//sc.geturl();
	
	String m = c.geturl();
	System.out.println("============"+m);
}

}
