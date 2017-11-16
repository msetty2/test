
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

	public static void main(String[] args) {

		try {
			FileOutputStream fos = new FileOutputStream("test-result.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			File folder = new File("test-output");
			File[] listOfFiles = folder.listFiles();
			ArrayList<File> filelist = new ArrayList<File>();
			System.out.println("Folder path :"+folder.getPath());
			listf(folder.getPath(), filelist);
			    for (int i = 0; i < filelist.size(); i++) {
			      if (filelist.get(i).isFile()) {
			        //System.out.println("File " + listOfFiles[i].getPath());
			    	addToZipFile(filelist.get(i).getPath(), zos);
			      } else if (filelist.get(i).isDirectory()) {
			        //System.out.println("Directory " + filelist.get(i).getName());
			      }
			    }
			
		
			/*addToZipFile(file2Name, zos);
			addToZipFile(file3Name, zos);
			addToZipFile(file4Name, zos);
			addToZipFile(file5Name, zos);*/

			zos.close();
			fos.close();
			
			System.out.println("-=-=-=-=-=-=->> TestOutput Zip File created successfully.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	public static void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	    	//System.out.println("files"+file.getPath());
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	        	//System.out.println("Dir "+file.getPath());
	            listf(file.getPath(), files);
	        }
	    }
	}
	public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

		//System.out.println("Writing '" + fileName + "' to zip file");

		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
			//System.out.println("Length : "+length);
		}

		zos.closeEntry();
		fis.close();
	}

}
