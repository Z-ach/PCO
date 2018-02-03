package com.hackpack.main.Engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.hackpack.main.UI.UI;

public class Engine {

	private ArrayList<MyFile> aList;
	UI ui;
	
	public Engine(UI ui) {
		aList = new ArrayList<MyFile>();
		this.ui = ui;
	}

	public void run(){
		File[] files = new File("/Users/Zach/Desktop/HackPoly/HackPoly/src").listFiles();
		showFiles(files);
		Collections.sort(aList);
		ui.printList(aList);
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
	


	public class MyFile implements Comparable<MyFile>{
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

		@Override
		public int compareTo(MyFile file) {
			return (int)(this.getTimeStamp() - file.getTimeStamp());
		}
	}
}