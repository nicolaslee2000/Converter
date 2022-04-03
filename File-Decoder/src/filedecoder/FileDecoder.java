package filedecoder;

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
	
	public void setSourceFiles(HashSet<File> sourceFiles) {
		this.sourceFiles = sourceFiles;
	}

	public void setSourceCharset(Charset sourceCharset) {
		this.sourceCharset = sourceCharset;
	}

	public void setTargetCharset(Charset targetCharset) {
		this.targetCharset = targetCharset;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
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

