package main;

import java.io.File;

public class MyFileFilter extends javax.swing.filechooser.FileFilter {
	public MyFileFilter(String ext, String description) {
        this.ext = ext;
    }
	public boolean accept(File f){
        if(f != null){
            if(f.isDirectory()){
                return true;
            }
            return f.toString().endsWith(ext);
        }
        return false;
	}
	public String getDescription(){
	   return description;
	}

	String ext, description;
}