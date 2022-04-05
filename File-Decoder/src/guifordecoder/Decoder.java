package guifordecoder;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Nicolaslee
 * working with IO package for now. Will try with NIO later.
 * 
 */
public class Decoder {
	
	private Map<String, Charset> availableCharsets;
		
	public Map<String, Charset> getAvailableCharsets() {
		return availableCharsets;
	}
	public void setAvailableCharsets(Map<String, Charset> availableCharsets) {
		this.availableCharsets = availableCharsets;
	}
	Decoder() {
		setAvailableCharsets(Charset.availableCharsets());
	}
	
	public void decode(File source, File target, Charset sourceCs, Charset targetCs) {
		List<Integer> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), sourceCs));) {
			int read;
			while((read = reader.read()) != -1) {
				data.add(read);
			}
			target.delete();
			target.createNewFile();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), targetCs));) {
			for(int i : data) {
				writer.write(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Charset detectEncodingAutomatic(File file, HashSet<Charset> c) {
		CharsetDecoder decoder;
		byte[] byteArray;
		Charset charset = null;
		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));){
			byteArray = new byte[512];
			identified:
			while ((input.read(byteArray) != -1)) {
				for(Charset cs : c) {
					decoder = cs.newDecoder();
					decoder.reset();
					try {
						decoder.decode(ByteBuffer.wrap(byteArray));
					} catch (CharacterCodingException e) {
						continue;
					}
					charset = cs;
					break identified;
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return charset;
	}
	
	//TODO Files.readallbytes test later
	public String detectEncodingBruteforce(File file, HashSet<Charset> cs, int sampleSize) {
		byte[] byteArray = new byte[512];
		String msg = "";
		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));){
			input.read(byteArray);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Charset c : cs) {
			String str = new String(byteArray, c);
			msg += c.displayName() + " --> " + "UTF-8\n" + str.substring(0, sampleSize) + "\n\n";
		}
		return msg;
	}
}
