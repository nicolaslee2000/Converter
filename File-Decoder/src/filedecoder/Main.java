package filedecoder;

import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		File f = new File("C:\\Users\\nicol\\Documents\\converting with java\\Test01.txt");
		File folder = new File("C:\\Users\\nicol\\Documents\\converting with java\\Testfoledr2");
		folder.mkdirs();
		File ff = new File(folder,f.getName());
		System.out.println(f.getName());
		try {
			ff.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
		
