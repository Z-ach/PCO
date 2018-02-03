package com.hackpack.main.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener, Interface{
	
	JTextField startDate, prompt1, prompt2, prompt3;
	JButton enter;
	String date;
	String prompt;
	SimpleDateFormat sdf;
	static CountDownLatch latch;
	
	public GUI(){
		startDate = new JTextField();
		enter = new JButton();
		date = "";
		sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		prompt1 = new JTextField();
		prompt2 = new JTextField();
		prompt3 = new JTextField();
		prompt1.setText("Please enter a date in the format: MM/DD/YYYY");
		prompt2.setText("A list of files will then be returned to you");
		prompt3.setText("These files have not been modified after your specified date.");
	}

	@Override
	public long requestDate() {
		latch = new CountDownLatch(1);
		try{
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	public void printList(ArrayList<File> files) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == enter){
			date = startDate.getText();
			latch.countDown();
		}
	}

}
