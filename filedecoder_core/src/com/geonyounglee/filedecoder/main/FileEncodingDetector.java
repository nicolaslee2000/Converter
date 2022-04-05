package main;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashSet;

public class FileEncodingDetector extends Decoder{
	private boolean bruteforce;
	private HashSet<Charset> charsetsToBeTested;
	private File sampleFile;
	private int sampleSize;
	public boolean isBruteforce() {
		return bruteforce;
	}
	public void setBruteforce(boolean bruteforce) {
		this.bruteforce = bruteforce;
	}
	public HashSet<Charset> getCharsetsToBeTested() {
		return charsetsToBeTested;
	}
	public void setCharsetsToBeTested(HashSet<Charset> charsetsToBeTested) {
		this.charsetsToBeTested = charsetsToBeTested;
	}
	public int getSampleSize() {
		return sampleSize;
	}
	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}
	public File getSampleFile() {
		return sampleFile;
	}
	public void setSampleFile(File sampleFile) {
		this.sampleFile = sampleFile;
	}
	
	public String detectEncoding() {
		if(bruteforce) {
			return detectEncodingBruteforce(sampleFile, charsetsToBeTested, sampleSize);
		}
		return detectEncodingAutomatic(sampleFile, charsetsToBeTested).displayName();
	}
}
