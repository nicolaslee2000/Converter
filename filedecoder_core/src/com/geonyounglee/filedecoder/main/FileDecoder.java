package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class FileDecoder extends Decoder{
	private Set<File> sourceFiles;
	private Charset sourceCharset;
	private Charset targetCharset;
	private boolean overwrite;
	private File targetFolder;
	public FileDecoder() {
	      super();
	}
	public Set<File> getSourceFiles() {
		return sourceFiles;
	}
	public void setSourceFiles(Set<File> sourceFiles) {
		this.sourceFiles = sourceFiles;
	}
	public Charset getSourceCharset() {
		return sourceCharset;
	}
	public void setSourceCharset(Charset sourceCharset) {
		this.sourceCharset = sourceCharset;
	}
	public Charset getTargetCharset() {
		return targetCharset;
	}
	public void setTargetCharset(Charset targetCharset) {
		this.targetCharset = targetCharset;
	}
	public boolean isOverwrite() {
		return overwrite;
	}
	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}
	public File getTargetFolder() {
		return targetFolder;
	}
	public void setTargetFolder(File targetFolder) {
		this.targetFolder = targetFolder;
	}

	public void decode() {
		for(File f : sourceFiles) {
			if(overwrite) {
				decode(f, f, sourceCharset, targetCharset);
			} else {
				targetFolder.mkdirs();
				File tf = new File(targetFolder, f.getName());
				try {
					tf.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				decode(f, tf, sourceCharset, targetCharset);
			}
		}
	}
	
}

