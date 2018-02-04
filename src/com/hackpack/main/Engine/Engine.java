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
	String[] extensions;

	public Engine(UI ui) {
		aList = new ArrayList<MyFile>();
		this.ui = ui;
	}

	public void run() {
		File[] files = getRoot().listFiles();
		date = ui.requestDate();
		createFileFilter();
		ui.running();
		showFiles(files);
		Collections.sort(aList);
		ui.printList(aList, maxLength);
		populateDeleteList();
	}

	private File getRoot() {
		File temp = new File(System.getProperty("user.home"));
		System.out.println(temp.getAbsolutePath());
		while (temp.getParent() != null)
			temp = temp.getParentFile();
		return temp;
	}

	public void showFiles(File[] files) {
		if (files != null) {
			for (File file : files) {
				// System.out.println(file.getAbsolutePath());
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

	private void createFileFilter() {
		extensions = ui.requestExt();
	}

	private boolean fileFilter(File file) {
		for (String str : extensions) {
			if (file.getAbsolutePath().contains(str)) {
				return true;
			}
		}
		return false;
	}

	private void populateDeleteList() {
		ArrayList<MyFile> deleteList = new ArrayList<MyFile>();
		boolean finished = false;
		for (MyFile file : aList) {
			if (finished)
				break;
			switch (ui.deletePrompt(file)) {
			case "y":
				deleteList.add(file);
				break;
			case "n":
				break;
			case "done":
				finished = true;
			}
		}
		confirmAndDelete(deleteList);
	}

	private void confirmAndDelete(ArrayList<MyFile> dList) {
		if (dList.size() == 0)
			return;
		ui.printList(dList, maxLength);
		String confirmation = ui.comfirmDelete();
		if (confirmation.equalsIgnoreCase("y")) {
			for (MyFile file : dList) {
				file.delete();
			}
		}
	}

	public class MyFile implements Comparable<MyFile> {
		private String path;
		private long timeStamp, fileSize;
		private File file;

		private MyFile(File file) {
			this.path = file.getAbsolutePath();
			this.timeStamp = file.lastModified();
			this.fileSize = file.length();
			this.file = file;
		}

		public String getPath() {
			return path;
		}

		public long getTimeStamp() {
			return timeStamp;
		}

		public long getTotalSpace() {
			return fileSize / 1024;
		}

		private void delete() {
			file.delete();
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