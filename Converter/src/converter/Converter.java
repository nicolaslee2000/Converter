package converter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Nicolaslee
 * working with IO package for now. Will try with NIO later.
 *
 */
public class Converter {
	
	private HashSet<File> files;
	private Map<String, Charset> availableCharsets;
	private Charset charset;
	private String charsetsToBeTested[];
	private File root;
	
	private Converter() {
		availableCharsets = Charset.availableCharsets();
		files = new HashSet<>();
		setCharsetsToBeTested(new String[]{"UTF-8", "windows-1253", "ISO-8859-7", "EUC-KR", "ISO-8859-1"});
	}
	
	private void setCharsetsToBeTested(String[] charsetsToBeTested) {
		this.charsetsToBeTested = charsetsToBeTested;
	}

	Converter(String filePath) {
		this();
		root = new File(filePath);
		files.addAll(FileHandling.getAllFiles(root));
	
		transform(files, new File("C:\\Users\\nicol\\Documents\\converting with java\\Testfolder - Copy"));
	}
		
//	public void changeExtension(String ext) {
//
//		int index = file.getAbsolutePath().lastIndexOf(".");
//		file.renameTo(new File(file.getAbsolutePath().substring(0, index + 1) + ext));
//	}

	
	//if tgFolder is null automatically create copy of rootFolder to put converted files to. Otherwise put files to tgFolder
	//use NIO to copy entire directory, possibly decoding while copying file(combine transform and convert)
	//TODO ask if here is an easier way
	public void transform(HashSet<File> files, File tgFolder) {
		
		//had to use NIO to get relative path
		File folder = tgFolder == null ? new File(root.getParent(), root.getName()+" - Copy") : tgFolder;
		folder.mkdir();
		for(File file : files) {
			File sf = new File(folder, root.toPath().relativize(file.toPath()).toString());
			sf.getParentFile().mkdirs();
			try {
				sf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			convert(file, sf, Charset.forName("euc-kr"), Charset.forName("utf-8"));
		}
		
	}

	
	private void convert(File source, File target, Charset sourceCs, Charset targetCs) {
		try (
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), sourceCs));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), targetCs));
				) {
			int read;
			while((read = reader.read()) != -1)
				writer.write(read);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public Charset detectFileEncoding() {
//		CharsetDecoder decoder;
//		byte[] byteArray;
//		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));){
//			byteArray = new byte[500];
//			identified:
//			while ((input.read(byteArray) != -1)) {
//				for(String cs : charsetsToBeTested) {
//					decoder = Charset.forName(cs).newDecoder();
//					decoder.reset();
//					try {
//						decoder.decode(ByteBuffer.wrap(byteArray));
//					} catch (CharacterCodingException e) {
//						continue;
//					}
//					charset = Charset.forName(cs);
//					break identified;
//				}
//	        }
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return charset;
//	}
	
	//nested static class for readability. Use for getAllFiles methods only.
	private static class FileHandling {
		private FileHandling() {
		}
		
		private static HashSet<File> getAllFiles(File file) {
			HashSet<File> files = new HashSet<File>();
	        if(file.isDirectory()) {
	            for(File subFiles : file.listFiles()) {
	                if(subFiles.isDirectory()) {
	                    files.addAll(getAllFiles(subFiles));
	                } else {
	                    files.add(subFiles);              
	                    }
	            }
	        } else {
	            files.add(file);              
	        }
	        return files;
	    }
		private static HashSet<File> getAllFiles(File file, String... extension) {
			HashSet<File> set = new HashSet<File>();
			for(String ext : extension) {
				for(File fl : getAllFiles(file)) {
					if(fl.getAbsolutePath().toLowerCase().endsWith(ext.toLowerCase())) {
						set.add(fl);
					}
				}
			}
			return set;
		}
		

	}
	
}
