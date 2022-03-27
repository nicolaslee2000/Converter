package converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Converter {

	public static void changeExtension(File file, String ext) {

		int index = file.getAbsolutePath().lastIndexOf(".");
		// print filename
		// System.out.println(file.getName().substring(0, index));
		// print extension
		// System.out.println(file.getName().substring(index));
		// use file.renameTo() to rename the file
		file.renameTo(new File(file.getAbsolutePath().substring(0, index) + ext));
		System.out.println(file);
	}
	
	public static void transform(File source, String srcEncoding, File target, String tgtEncoding) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source), srcEncoding));
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(target), tgtEncoding));) {
			char[] buffer = new char[16384];
			int read;
			while ((read = br.read(buffer)) != -1)
				bw.write(buffer, 0, read);
		}
	}

}
