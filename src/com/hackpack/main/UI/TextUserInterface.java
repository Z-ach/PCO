package com.hackpack.main.UI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.hackpack.main.Engine.Engine.MyFile;

public class TextUserInterface implements UI{
	
	Scanner input;
	SimpleDateFormat sdf;
	
	public TextUserInterface(){
		input = new Scanner(System.in);
		sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	}
	
	@Override
	public long requestDate() {
		System.out.println("Please enter a date in the format: MM/DD/YYYY");
		System.out.println("A list of files will then be returned to you");
		System.out.println("These files have not been modified after your specified date.");
		String date = input.nextLine();
		date += " 00:00:00";
		Date parsedDate = null;
		try {
			parsedDate = sdf.parse(date);
		} catch (ParseException e) {
			System.err.println("Invalid date entered.");
			System.exit(0);
			e.printStackTrace();
		}
		return parsedDate.getTime();
	}

	@Override
	public void printList(ArrayList<MyFile> files, int length) {
		int width = length + 5;
		char fill = ' ';
		String title = "Path:";
		String titlePath = title + new String(new char[width - title.length()]).replace('\0', fill);
		System.out.println(titlePath + "\tSize(KB):\t\t\tLast Modified:");
		for(MyFile file : files){
			String path = file.getPath();
			String fixed = path + new String(new char[width - path.length()]).replace('\0', fill);
			System.out.println(fixed+ "\t" + file.getTotalSpace()%1000 + "\t\t\t" + sdf.format(file.getTimeStamp()));
		}
	}

}
