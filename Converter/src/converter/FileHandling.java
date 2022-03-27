package converter;

import java.io.File;
import java.util.HashSet;

//not used anymore

public class FileHandling {
	
	private static HashSet<File> listOfFiles;
	private File rootFile;
	
	FileHandling (String path) {
		listOfFiles = new HashSet<File>();
		rootFile = new File(path);
		getAllFiles(rootFile);
	}
	public static HashSet<File> getAllFiles(File file) {
		if(file.isDirectory()) {
			for(File subFiles : file.listFiles()) {
				if(subFiles.isDirectory()) {
					getAllFiles(subFiles);
				} else {
					listOfFiles.add(subFiles);				
					}
			}
		} else {
			listOfFiles.add(file);				
			}
		return listOfFiles;
	}
	public static void getAllFiles(File file, String... extension) {
		for(String ext : extension) {
			
		}
	}
	
	public static void main(String[] args) {
		FileHandling fh = new FileHandling("C:\\Users\\nicol\\Documents\\converting with java\\Testfolder");
	}
}
