package filedecoder;

public class FileHandler {

}
//TODO change later
//public void changeExtension(String ext) {
//	for(File f:files) {
//		int index = f.getAbsolutePath().lastIndexOf(".");
//		f.renameTo(new File(f.getAbsolutePath().substring(0, index + 1) + ext));
//	}
//	
//}

//TODO remove later
//public void checkText() {
//	HashSet<File> hs = new HashSet<>();
//	for(File f:files) {
//		Path fileName
//        = Path.of(f.getAbsolutePath());
//
//    // Now calling Files.readString() method to
//    // read the file
//    try {
//		String str = Files.readString(fileName);
//		if(str.toLowerCase().contains("grayfilter")) {
//			hs.add(f);
//		}
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	}
//	for(File ff : hs) {
//		System.out.println(ff);
//	}
//	
//}

//use NIO to copy entire directory, possibly decoding while copying file(combine transform and convert)
