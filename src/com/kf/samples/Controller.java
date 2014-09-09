package com.kf.samples;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller {
	private DAO storage;
	private Student student;
	private View view;
	String fileName = "";
	ArrayList<Student> studentList;
	private int index;

	public Controller(DAO storage, View view) {
		this.storage = storage;
		this.view = view;
		student = new Student();
		view.addButtonDeleteActionListener(new DeleteListener());
		view.addButtonReadActionListener(new ReadListener());
		view.addButtonWriteActionListener(new WriteListener());
	}

	public Student getPerson() {
		return student;
	}

	public void setPerson(Student person) {
		this.student = person;
	}

	public class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			storage.delete();
		}
	}

	public class ReadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkStorage(storage);
			checkReadForNUll(storage.read());
			studentList = storage.read();
			view.showResult(studentList);
			view.addButtonChangeActionListener(new ChangeListener());
			view.addGetDataListSelectionListener(new GetDataListSelectionListener());
		}

		private void checkStorage(DAO storage) {

			if (storage == null) {
				System.out.println("NOOO");
			}

		}

		private void checkReadForNUll(ArrayList<Student> read) {

			if (read == null) {
				System.out.println("KOSKEEEEEEE !!!! NO MAN");
			}

		}
	}

	/**
	 * 
	 * @author TEST
	 *
	 */
	public class WriteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/**
			 * when correct data isn't written, this produces error window
			 */
			Student person = new Student();
			String name = view.getFieldNameText();
			String age = view.getFieldAgeText();
			if (!name.isEmpty() && !age.isEmpty()) {
				person.setName(name);
				try {
					int ageValue = Integer.parseInt(age);
					person.setAge(ageValue);
					storage.write(person);
				} catch (NumberFormatException nfe) {
					View.caution("Type in number");
				}
			} else {
				View.caution("Type in data");
			}
		}
		
		

	}
	/**
	 * Execute update into Db or File and change data on list 
	 * This dosen't read the data from db after updating but change data on list directly
	 * @author TEST
	 *
	 */
	//TODO I don't know if it is Ok to not read data from data but change directly
	public class UpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Student after = new Student();
			String name = view.getFieldNameText();
			String age = view.getFieldAgeText();
			if (!name.isEmpty() && !age.isEmpty()) {
				after.setName(name);
				try {
					int ageValue = Integer.parseInt(age);
					after.setAge(ageValue);
					storage.update(student, after);
					//List on window is changed
					view.getModel().set(index, "name: " + after.getName() +" age: " + after.getAge() + "\n");
					//Arraylist is changed
					studentList.set(index, after);
					for(Student i : studentList){
						System.out.println(i.getName() + i.getAge());
					}
					closeAttachedWindow(e);
				} catch (NumberFormatException nfe) {
					View.caution("Type in number");
				}
			} else {
				View.caution("Type in data");
			}
		}

		private void closeAttachedWindow(ActionEvent e) {
			Window w = SwingUtilities.getWindowAncestor((Component) e.getSource());
			w.dispose();
		}
	}

	/**
	 * This is called when change button is clicked with something is selected
	 * @author TEST
	 *
	 */
	public class ChangeListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {
			if(!view.getLt().isSelectionEmpty()){
				index = view.getLt().getSelectedIndex();
				student = studentList.get(index);
				view.updateWindow(student);
				view.addButtonUpdateActionListener(new UpdateListener());
			}
			else{
				System.out.println("Select the content");
			}
		}
	}
	/**
	 * This isn't used because this invokes event twice when clicked and released
	 * @author TEST
	 *
	 */
	// TODO check and implement the function of update
	public class GetDataListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
//			if (e.getValueIsAdjusting()) {
//				student = new Student();
//				student = studentList.get(view.getLt().getSelectedIndex());
//				System.out.println(student.getName());
//			}
		}
	}
}
