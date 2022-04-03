package filedecoder;

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
import java.util.Map;
import java.util.Set;

/**
 * @author Nicolaslee
 * working with IO package for now. Will try with NIO later.
 * 
 */
public class Decoder {
	
	private Map<String, Charset> availableCharsets;
	private Set<String> charsetsToBeTested;
		
	public Map<String, Charset> getAvailableCharsets() {
		return availableCharsets;
	}

	public void setAvailableCharsets(Map<String, Charset> availableCharsets) {
		this.availableCharsets = availableCharsets;
	}

	public Set<String> getCharsetsToBeTested() {
		return charsetsToBeTested;
	}

	public void setCharsetsToBeTested(Set<String> charsetsToBeTested) {
		this.charsetsToBeTested = charsetsToBeTested;
	}

	Decoder() {
		setAvailableCharsets(Charset.availableCharsets());
	}
	
	public void decode(File source, File target, Charset sourceCs, Charset targetCs) {
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
	
	public Charset detectFileEncoding(File file) {
		CharsetDecoder decoder;
		byte[] byteArray;
		Charset charset = null;
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
