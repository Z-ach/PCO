package com.hackpack.main.UI;

import java.util.ArrayList;

import com.hackpack.main.Engine.Engine.MyFile;

public interface UI {
	
	public long requestDate();
	public void printList(ArrayList<MyFile> files, int length);
	public String[] requestExt();
	public String deletePrompt();
}
