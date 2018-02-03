package com.hackpack.main;

import com.hackpack.main.Engine.Engine;
import com.hackpack.main.UI.TextUserInterface;
import com.hackpack.main.UI.UI;

public class Main {
	
	static UI inter = new TextUserInterface();
	
	public static void main(String[] args){
		
		Engine engine = new Engine(inter);
		engine.run();
		
	}
}
