package com.hackpack.main.Engine;

import java.io.File;
import java.util.ArrayList;
public class Engine {

	private ArrayList<MyFile> aList;
	
	public Engine() {
		File[] files = new File("/Users/Zach/Desktop/HackPoly/HackPoly/src").listFiles();
		System.out.println(files.length);
		aList = new ArrayList<MyFile>();
		showFiles(files);
	}

	public void showFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Directory: " + file.getName());
				showFiles(file.listFiles());
			} else {
				System.out.println("File: " + file.getName());
				aList.add(new MyFile(file));
			}
		}
	}

	public class MyFile{
		private String path;
		private long timeStamp, fileSize;

		private MyFile(File file) {
			this.path = file.getAbsolutePath();
			this.timeStamp = file.lastModified();
			this.fileSize = file.getTotalSpace();
		}
		
		public String getPath(){
			return path;
		}
		public long getTimeStamp(){
			return timeStamp;
		}
		public long getTotalSpace(){
			return fileSize;
		}
	}
}