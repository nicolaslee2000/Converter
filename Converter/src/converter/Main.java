package converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		
		//Converter.changeExtension(new File("C:\\Users\\nicol\\Documents\\converting with java\\Test01.java"), ".txt");
		//Converter.rewrite(new File("C:\\Users\\nicol\\Documents\\converting with java\\Test01 - Copy.txt"));
		//Converter.convert(new File("C:\\Users\\nicol\\Documents\\converting with java\\Test01short.txt"));
		//Converter.euc(new File("C:\\Users\\nicol\\Documents\\converting with java\\Test01short1.txt"));
	
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\nicol\\Documents\\converting with java\\Test01.txt"), "euc-kr"));
	     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\nicol\\Documents\\converting with java\\plz.txt"), "utf-8")); 
	          char[] buffer = new char[16384];
	          String read;
	          String st = "";
	          while ((read = br.readLine()) != null) {
	        	  st+= read;
	        	  bw.write(st);
	          }
	             
	    System.out.println(st);      
		br.close();
		bw.close();
		//Converter.changeExtension(new File("C:\\Users\\nicol\\Documents\\converting with java\\Test01.txt"), ".java");
	   }
}
