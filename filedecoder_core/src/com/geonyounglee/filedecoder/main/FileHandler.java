package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class FileHandler {
	
    public void changeExtension(Set<File> files, String ext) {
            for(File f:files) {
                    int index = f.getAbsolutePath().lastIndexOf(".");
                    f.renameTo(new File(f.getAbsolutePath().substring(0, index + 1) + ext));
            }
    }

    public void rename(Set<File> files, String original, String replace, boolean regex) {

        for(File f : files) {
            System.out.println("ORIGINAL"+original);
            System.out.println("FILE"+f);
            String str = original == null ? f.getName() : original;
            System.out.println("STR "+str);
            String newname;
            if(regex) {
                    newname = f.getName().replaceFirst(str, replace);
            } else {
                    newname = f.getName().replace(str, replace);
            }
            f.renameTo(new File(f.getParentFile().getAbsolutePath(), newname));
        }
    }

    public Set<File> searchFilecontent(Set<File> files, String text, boolean regex) {
            Set<File> resultfiles = new HashSet<>();
            for(File f : files) {
                    Path fileName = Path.of(f.getAbsolutePath());
                    try {
                            String str = Files.readString(fileName);
                            if(regex) {
                                    if(str.matches(text)) 
                                            resultfiles.add(f);
                            } else {
                                    if(str.contains(text))
                                            resultfiles.add(f);
                            }
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
            }
            return resultfiles;
    }
}

