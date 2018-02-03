package com.hackpack.main;

import java.io.File;

import com.hackpack.main.UI.Interface;
import com.hackpack.main.UI.TextUserInterface;

public class Main {
	
	static Interface inter = new TextUserInterface();
	
	public static void main(String[] args){
		File[] files = new File("/Users/Zach/Desktop/HackPoly/HackPoly/src").listFiles();
		System.out.println(files.length);
		showFiles(files);
	}
	
	public static void showFiles(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	            System.out.println("Directory: " + file.getName());
	            showFiles(file.listFiles());
	        } else {
	            System.out.println("File: " + file.getName());
	        }
	    }
	}
	
}
