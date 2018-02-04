package com.hackpack.main.UI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.hackpack.main.Engine.Engine.MyFile;

public class TextUserInterface implements UI {

	Scanner input;
	SimpleDateFormat sdf;

	public TextUserInterface() {
		input = new Scanner(System.in);
		sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	}
	
	@Override 
	public String[] requestExt() {
		ArrayList<String> ext = new ArrayList<>();
		System.out.println("Do you have specific extensions you want?");
		System.out.println("y/n");
		String opt = input.nextLine();
		while(!opt.toLowerCase().equals("y") && !opt.toLowerCase().equals("n")){
			System.out.println("Invalid entry. Please type y/n");
			opt = input.nextLine();
		}
		switch(opt.toLowerCase()){
		case "y":
			System.out.println("Please enter all the file extends you want to filter in the format: .extension");
			System.out.println("When you are finished, type done");
			String extension = "";
			while(!extension.equalsIgnoreCase("done")){
				extension = input.nextLine();
				ext.add(extension);
			}
			break;
		case "n":
			String[] extensions = { ".html", ".png", ".jpg", ".doc", ".docx", ".pdf", ".xls", ".xlsx", ".ppt", "pptx", ".txt"};
			return extensions;
		}
		String[] extensions = new String[ext.size()];
		for(int i = 0; i < ext.size(); i++){
			extensions[i] = ext.get(i);
		}
		return extensions;
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
		System.out.println(titlePath + "\tSize (kb):\t\t\tLast Modified:");
		for (MyFile file : files) {
			String path = file.getPath();
			String fixed = path + new String(new char[width - path.length()]).replace('\0', fill);
			System.out.println(fixed + "\t" + file.getTotalSpace() + "\t\t\t" + sdf.format(file.getTimeStamp()));
		}
	}

	@Override
	public String deletePrompt() {
		String answer = "";
		System.out.println("Would you like to delete this file? Type y/n/done");
		answer = input.nextLine();
		while(!answer.equals("y") && !answer.equals("n") && !answer.equals("done")){
			System.out.println("Invalid entry. Please try again. Type y/n/done.");
			answer = input.nextLine();
		}
		return answer;
	}

	@Override
	public void running() {
		System.out.println("Program is now running. Please wait as it indexes your files.");
	}

	@Override
	public void printFile(MyFile file) {
		System.out.println(file.getPath());
	}

}
