package com.gravypod.RainbowTabel;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class FileHandle {
	
	PrintWriter pw;

	public FileHandle() {
		
		try {
			pw = new PrintWriter(new FileWriter(new File("HashList.txt"), true));
		} catch (Exception e) {
		}
		
	}
	
	public synchronized void print(String string) {
		
		pw.println(string);
		
		pw.flush();
		
	}
	
}
