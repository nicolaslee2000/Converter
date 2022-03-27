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
import java.nio.file.Path;
import java.util.Map;

public class Converter {
	
	private File file;
	private Path path;
	private Map<String, Charset> availableCharsets;
	private Charset charset;
	String charsetsToBeTested[];
	
	Converter() {
		availableCharsets = Charset.availableCharsets();
		charsetsToBeTested = new String[]{"UTF-8", "windows-1253", "ISO-8859-7", "euc-kr"};
	}
	Converter(File file) {
		this();
		this.file = file;
	}
	
	Converter(String filePath) {
		this();
		this.file = new File(filePath);
	}
	
	public void changeExtension(String ext) {

		int index = file.getAbsolutePath().lastIndexOf(".");
		file.renameTo(new File(file.getAbsolutePath().substring(0, index) + ext));
		
	}
	
	public void transform() {

		try (
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr"));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\Users\\nicol\\Documents\\converting with java\\plz.txt")),"Big5"));
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
	
	public Charset detectFileEncoding() {
		CharsetDecoder decoder;
		byte[] byteArray;
		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));){
			byteArray = new byte[500];
			identified:
			while ((input.read(byteArray) != -1)) {
				for(String cs : charsetsToBeTested) {
					decoder = Charset.forName(cs).newDecoder();
					decoder.reset();
					try {
						decoder.decode(ByteBuffer.wrap(byteArray));
					} catch (CharacterCodingException e) {
						continue;
					}
					charset = Charset.forName(cs);
					break identified;
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return charset;
	}

}
