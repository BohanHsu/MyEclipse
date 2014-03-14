package fileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFunctionalClass {
	public static void main(String[] args) throws IOException {
		String path = "D:\\photo\\album\\Untitled.txt";
		MyFileReader mfr = new MyFileReader(path);
		
		List<String> resultList = null;
		
		try {
			resultList = mfr.readAllLines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String oldFolder = "D:\\photo\\california";
		String prefix = "IMG_0";
		String suffix = ".JPG";
		String newFolder = "D:\\photo\\album";
		
		for (String numStr : resultList) {
			String fileName = prefix + numStr + suffix;
			String oldPath = oldFolder + "\\" + fileName;
			String newPath = newFolder + "\\" + fileName;
			
			System.out.println("--oldPath: " + oldPath);
			System.out.println("--newPaht: " + newPath);
			
			Copier.copy(new File(oldPath), new File(newPath));
		}
	}
}
