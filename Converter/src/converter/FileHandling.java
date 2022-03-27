package converter;

import java.io.File;
import java.util.HashSet;

public class FileHandling {
	
	private HashSet<File> listOfFiles;
	private File rootFile;
	
	FileHandling (String path) {
		listOfFiles = new HashSet<File>();
		rootFile = new File(path);
		getAllFiles(rootFile);
		System.out.println(listOfFiles);
		System.out.println(listOfFiles.size());
	}
	
	private void getAllFiles(File file) {
		if(file.isDirectory()) {
			for(File subFiles : file.listFiles()) {
				if(subFiles.isDirectory()) {
					this.getAllFiles(subFiles);
				} else {
					listOfFiles.add(subFiles);				
					}
			}
		} 
	}
	public static void main(String[] args) {
		FileHandling fh = new FileHandling("C:\\Users\\nicol\\Documents\\converting with java\\Testfolder");
		
	}
}
