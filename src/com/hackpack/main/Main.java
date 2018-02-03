package com.hackpack.main;

import com.hackpack.main.UI.Interface;
import com.hackpack.main.UI.TextUserInterface;

public class Main {
	
	static Interface inter = new TextUserInterface();
	
	public static void main(String[] args){
		System.out.println(inter.requestDate());
	}
	
}
