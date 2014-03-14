package fileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * read file
 * @author Bohan
 *
 */
public class MyFileReader {
	private String path = null;
	private File file = null;
	
	public MyFileReader(String path) {
		super();
		this.path = path;
		file = new File(path);
	}
	
	/**
	 * read all lines in the file.
	 * @return a Arraylist which store each line of the file in a element.
	 * @throws IOException
	 */
	public List<String> readAllLines() throws IOException{
		List<String> resultList = new ArrayList<String>();
	
		InputStream is = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String line = null;
		int count = 0;
		
		while((line = br.readLine()) != null){
			count++;
			System.out.println("-- reading: " + line);
			resultList.add(line);
		}
		
		System.out.println("-- read " + count + " lines");
		is.close();
		isr.close();
		br.close();
		return resultList;
	}
	
	public static void main(String[] args) {
		String path  = "D:\\photo\\album\\Untitled.txt";
		MyFileReader mfr = new MyFileReader(path);
		
		try {
			mfr.readAllLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
