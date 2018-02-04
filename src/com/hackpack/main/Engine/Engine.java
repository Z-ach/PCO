package com.hackpack.main.Engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.hackpack.main.UI.UI;

public class Engine {

	private ArrayList<MyFile> aList;
	UI ui;
	long date;
	int maxLength;

	public Engine(UI ui) {
		aList = new ArrayList<MyFile>();
		this.ui = ui;
	}

	public void run() {
		File[] files = getRoot().listFiles();
		date = ui.requestDate();
		showFiles(files);
		Collections.sort(aList);
		ui.printList(aList, maxLength);
	}
	
	private File getRoot(){
		File temp = new File(System.getProperty("user.home"));
		while(temp.getParent() != null)
			temp = temp.getParentFile();
		return temp;
	}

	public void showFiles(File[] files) {
		if (files != null) {
			for (File file : files) {
				//System.out.println(file.getAbsolutePath());
				if (file.isDirectory() && !file.isHidden() && notApp(file) && notUnwanted(file)) {
					showFiles(file.listFiles());
				} else {
					if (fileFilter(file) && file.lastModified() < date) {
						if (maxLength < file.getAbsolutePath().length()) {
							maxLength = file.getAbsolutePath().length();
						}
						aList.add(new MyFile(file));

					}
				}
			}
		}
	}

	private boolean notUnwanted(File file) {
		return !file.getAbsolutePath().contains(" ");
	}

	private boolean notApp(File file) {
		return !file.getAbsolutePath().contains("\\.") && !file.getAbsolutePath().contains(".app");
	}

	private boolean fileFilter(File file) {
		String[] extensions = { ".exe", ".html", ".png", ".jpg", ".doc", ".docx", ".pdf", ".xls", ".xlsx", ".ppt", "pptx", ".txt"};
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
			return fileSize/1024;
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