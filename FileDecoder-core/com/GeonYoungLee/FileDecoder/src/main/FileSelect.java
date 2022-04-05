package main;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileSelect {
    private HashSet<File> files;
    private HashMap<String, HashSet<File>> tempfiles;
    
    public FileSelect() {
            files = new HashSet<>();
            tempfiles = new HashMap<>();
    }

    public HashSet<File> getFiles() {
        return files;
    }

    public HashMap<String, HashSet<File>> getTempfiles() {
        return tempfiles;
    }
    
    public void addFiles(HashSet<File> files) {
            files.forEach(e -> this.files.addAll(getAllFiles(e)));
    }
    public void addFiles(File files) {
            this.files.addAll(getAllFiles(files));
    }
    public void removeFiles(HashSet<File> files) {
        this.files.removeAll(files);
        if(this.files.isEmpty()) 
        	tempfiles.clear();
	}
    public void removeFiles(File files) {
        this.files.remove(files);
        if(this.files.isEmpty())
        	tempfiles.clear();
	}
	
    private HashSet<File> getAllFiles(File file) {
        HashSet<File> files = new HashSet<>();
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
    
    public void setFilterHidden(boolean showHidden) {
        FileFilter f = showHidden ? e -> false : e -> e.isHidden(); 
        setFilter(f, "Hidden");
    }

    public void setFilterExt(String ext) {
        FileFilter f = e -> !e.getPath().toLowerCase().endsWith(ext.toLowerCase());
        setFilter(f, "Ext");
    }
    
    public void setFilterRegex(String pattern) {
        Pattern p = Pattern.compile(pattern);
        FileFilter f = e-> {
                Matcher m = p.matcher(e.getName());
                return !m.matches();
        };
        setFilter(f, "Ext");
    }
    
    public void setFilterName(String name) {
        FileFilter f = e ->  !e.getName().toLowerCase().contains(name.toLowerCase());
        setFilter(f, "Name");
    }
    
    private void setFilter(FileFilter i, String filterName) {
        HashSet<File> temp1 = new HashSet<>();
        HashSet<File> temp2 = new HashSet<>();
        for(File f : files) {
            if(i.accept(f)) {
                temp1.add(f);
            }
        }
        if(tempfiles.containsKey(filterName)) {
            for(File f : tempfiles.get(filterName)){
            if(!i.accept(f)){
                temp2.add(f);
                }
            }
        }
        tempfiles.putIfAbsent(filterName, temp1);
        tempfiles.get(filterName).addAll(temp1);
        files.removeAll(temp1);
        tempfiles.get(filterName).removeAll(temp2);
        files.addAll(temp2);
    }
}
