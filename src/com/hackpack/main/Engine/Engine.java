package com.hackpack.main.Engine;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

import com.hackpack.main.UI.UI;

public class Engine {

	private ArrayList<MyFile> aList;
	UI ui;
	long date;

	public Engine(UI ui) {
		aList = new ArrayList<MyFile>();
		this.ui = ui;
	}

	public void run() {
		File[] files = new File(System.getProperty("user.home")).listFiles();
		date = ui.requestDate();
		showFiles(files);
		Collections.sort(aList);
		ui.printList(aList);
	}

	public void showFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory() && !file.isHidden() && notApp(file)) {
				showFiles(file.listFiles());
			} else {
				if (fileFilter(file) && file.lastModified() < date) {
					aList.add(new MyFile(file));
					if (aList.size() % 100000 == 0)
						System.out.println(aList.size());
				}
			}
		}
	}
	
	private boolean notApp(File file){
		return !file.getAbsolutePath().contains(".app");
	}

	private boolean fileFilter(File file) {
		String[] extensions = { ".html", ".png", ".jpg" };
		for (String str : extensions) {
			if (file.getAbsolutePath().contains(str)) {
				return true;
			}
		}
		return false;
	}

	public class MyFile implements Comparable<MyFile> {
		private String path;
		private long timeStamp, fileSize;

		private MyFile(File file) {
			this.path = file.getAbsolutePath();
			this.timeStamp = file.lastModified();
			this.fileSize = file.length();
		}

		public String getPath() {
			return path;
		}

		public long getTimeStamp() {
			return timeStamp;
		}

		public long getTotalSpace() {
			return fileSize;
		}

		@Override
		public int compareTo(MyFile file) {
			if (this.getTimeStamp() < file.getTimeStamp()) {
				return -1;
			} else if (this.getTimeStamp() > file.getTimeStamp()) {
				return 1;
			}
			return 0;
		}
	}
}