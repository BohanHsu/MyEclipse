package fileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Bohan
 * a file copier
 */
public class Copier {

	/**
	 * 
	 * @param oldFile: the old file object
	 * @param newFile: the new file object
	 * @throws IOException
	 * 
	 * copy the old file to new file
	 */
	public static void copy(File oldFile, File newFile) throws IOException{
		InputStream is = new FileInputStream(oldFile);
		OutputStream os = new FileOutputStream(newFile);
		
		byte[] bs = new byte[6];
		int len = 0;
		
		while((len = is.read(bs)) != -1){
			os.write(bs, 0, len);
		}
		
		os.flush();
		os.close();
		is.close();
	}
	
	/**
	 * test the functions
	 * @param args
	 */
	public static void main(String[] args) {
		String oldPath = "D:\\testOld.JPG";
		String newPath = "D:\\testNew.JPG";
		File fOld = new File(oldPath);
		File fNew = new File(newPath);
		
		try {
			Copier.copy(fOld, fNew);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
