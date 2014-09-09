package com.kf.samples;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class View{
	private JFrame mainFrame;
	JLabel labelName;
	JLabel labelAge;


	private JTextField fieldName;
	private JTextField fieldAge;
	private JButton buttonWrite;
	private JButton buttonRead;
	private JButton buttonDelete;
	private JButton buttonUpdate;
	private JList<String> lt;
	private DefaultListModel<String> model;
	
	public DefaultListModel<String> getModel() {
		return model;
	}

	public void setModel(DefaultListModel<String> model) {
		this.model = model;
	}

	private JButton buttonOpenUpdate;
	private JFrame listFrame;

	/**
	 * Show main window
	 */
	public View() {
		mainFrame = new JFrame("Administration of person data");
		mainFrame.setSize(300,270);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		labelName = new JLabel("Type your name");
		labelName.setSize(200,30);
		labelName.setLocation(20,20);
		mainFrame.add(labelName);
		
		fieldName = new JTextField();
		fieldName.setSize(200,20);
		fieldName.setLocation(20,50);
		mainFrame.add(fieldName);

		labelName = new JLabel("Type your age");
		labelName.setSize(200,30);
		labelName.setLocation(20,80);
		mainFrame.add(labelName);
		
		fieldAge = new JTextField();
		fieldAge.setSize(200,20);
		fieldAge.setLocation(20,110);
		mainFrame.add(fieldAge);
		
		buttonWrite = new JButton("Send");
		buttonWrite.setSize(100,25);
		buttonWrite.setLocation(20,135);
		mainFrame.add(buttonWrite);
		
		buttonRead = new JButton("Show");
		buttonRead.setSize(100,25);
		buttonRead.setLocation(140,135);
		mainFrame.add(buttonRead);
		
		buttonDelete = new JButton("Delete");
		buttonDelete.setSize(100,25);
		buttonDelete.setLocation(80,170);
		mainFrame.add(buttonDelete, BorderLayout.SOUTH);
		
		mainFrame.setVisible(true);
	}
	
	/**
	 * Show some caution about something
	 * this is used for indicating lack of data
	 * @param str the statement you want to show
	 */
	public static void caution(String str){
		JFrame cautionFrame = new JFrame("Caution");
		cautionFrame.setSize(300,200);
		cautionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cautionFrame.setLocationRelativeTo(null);
		JLabel caution = new JLabel(str);
		cautionFrame.add(caution,BorderLayout.CENTER);
		cautionFrame.setVisible(true);
	}
	
	/**
	 * When show button is pushed, this is invoked
	 * This shows data in mysql or textfile as list
	 * @param personList array list of student data
	 */
	//TODO get the data from JList and look for the way to get the index and value
	public void showResult(ArrayList<Student> personList) {
		listFrame = new JFrame();
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		listFrame.setLocationByPlatform(true);
		buttonOpenUpdate = new JButton("Change");
		buttonOpenUpdate.setSize(100,25);
		
		listFrame.setSize(300,400);
		listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//make a JList from arraylist
		setList(personList);
		
		getLt().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane sp = new JScrollPane(getLt());
		sp.setSize(200,300);
		listFrame.add(sp);
		bottomPanel.add(buttonOpenUpdate);
		listFrame.getContentPane().add(topPanel,BorderLayout.NORTH);
		listFrame.getContentPane().add(bottomPanel,BorderLayout.SOUTH);
		listFrame.setVisible(true);
	}
	
	/**
	 * Show a window with text boxes to change one student data
	 * @param student data which will be changed
	 */
	public void updateWindow(Student student){
		JFrame updateFrame;
		updateFrame = new JFrame("Update");
		updateFrame.setSize(300,270);
		updateFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		updateFrame.setLayout(null);
		updateFrame.setLocationByPlatform(true);
		labelName = new JLabel("Rewrite your name");
		labelName.setSize(200,30);
		labelName.setLocation(20,20);
		updateFrame.add(labelName);
		
		fieldName = new JTextField();
		fieldName.setSize(200,20);
		fieldName.setLocation(20,50);
		fieldName.setText(student.getName());
		updateFrame.add(fieldName);

		labelName = new JLabel("Rewrite your age");
		labelName.setSize(200,30);
		labelName.setLocation(20,80);
		updateFrame.add(labelName);
		
		fieldAge = new JTextField();
		fieldAge.setSize(200,20);
		fieldAge.setLocation(20,110);
		fieldAge.setText(Integer.toString(student.getAge())); 
		updateFrame.add(fieldAge);
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setSize(100,25);
		buttonUpdate.setLocation(20,135);
		updateFrame.add(buttonUpdate);
		
		updateFrame.setVisible(true);
		
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public JFrame getListFrame() {
		return listFrame;
	}

	public void setListFrame(JFrame listFrame) {
		this.listFrame = listFrame;
	}

	/**
	 * make model for JList from ArrayList which includes all atudents data
	 * @param personList
	 */
	public void setList(ArrayList<Student> personList) {
		model = new DefaultListModel<>();
		for(Student person : personList){
			model.addElement("name: " + person.getName() +" age: " + person.getAge() + "\n");
		}
		setLt(new JList<String>(model));
	}
	


	
	
	void addButtonWriteActionListener(ActionListener wal){
		buttonWrite.addActionListener(wal);
	}
	
	void addButtonReadActionListener(ActionListener ral){
		buttonRead.addActionListener(ral);
	}
	
	void addButtonDeleteActionListener(ActionListener dal){
		buttonDelete.addActionListener(dal);
	}
	
	void addButtonUpdateActionListener(ActionListener ual){
		buttonUpdate.addActionListener(ual);
	}
	
	void addButtonChangeActionListener(ActionListener oual){
		buttonOpenUpdate.addActionListener(oual);
	}
	
	void addGetDataListSelectionListener(ListSelectionListener lsl){
		getLt().addListSelectionListener(lsl);
	}

	public String getFieldNameText() {
		return fieldName.getText();
	}

	public String getFieldAgeText() {
		return fieldAge.getText();
	}

	public JList<String> getLt() {
		return lt;
	}

	public void setLt(JList<String> lt) {
		this.lt = lt;
	}


}
