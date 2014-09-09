package com.kf.samples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Model of file system
 * When you put File for an argument, text file is used for storing all students data
 * @author TEST
 *
 */
public class FileIODAO implements DAO {
	private BufferedWriter bw;

	/**
	 * Check if text file for storing data already exists or not
	 * @param person
	 */

	private boolean checkBeforeWritefile(File file) {
		if (file.exists()) {
			System.out.println("exist");
			if (file.isFile() && file.canWrite()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Write data into text file
	 */
	@Override
	public void write(Student person) {
		try {
			File file = new File("C:\\Users\\TEST\\Documents\\test.txt");
			System.out.println(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (checkBeforeWritefile(file)) {
				// Here true is to append the content to file
				bw = new BufferedWriter(new FileWriter(file, true));

				bw.write(person.getName());
				bw.write(",");
				bw.write(Integer.toString(person.getAge()));
				bw.newLine();
				bw.close();
			} else {
				System.out.println("error");
			}
		} catch (IOException e) {

		}
	}

	/**
	 * Read data from text file and return as arraylist
	 */
	@Override
	public ArrayList<Student> read() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String sCurrentLine = null;
		ArrayList<Student> result = new ArrayList<Student>();
		try {
			is = new FileInputStream("C:\\Users\\TEST\\Documents\\test.txt");

			System.out.println(is);

			isr = new InputStreamReader(is);

			br = new BufferedReader(isr);
			while ((sCurrentLine = br.readLine()) != null) {
				String[] strAry = sCurrentLine.split(",");
				Student person = new Student(strAry[0],
						Integer.parseInt(strAry[1]));
				result.add(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * delete all data in text file
	 */
	@Override
	public void delete() {
		try {
			File file = new File("C:\\Users\\TEST\\Documents\\test.txt");
			System.out.println(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (checkBeforeWritefile(file)) {
				// Here true is to append the content to file
				bw = new BufferedWriter(new FileWriter(file, false));
				bw.close();
			} else {
				System.out.println("error");
			}
		} catch (IOException e) {

		}
	}

	@Override
	public void search(Student student) {
		// TODO Auto-generated method stub

	}

	/**
	 * update one student data to another one which user input
	 */
	//TODO I want to find another way to write this code smarter and simpler
	@Override
	public void update(Student before, Student after) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String sCurrentLine = null;
		ArrayList<Student> result = new ArrayList<Student>();
		try {
			is = new FileInputStream("C:\\Users\\TEST\\Documents\\test.txt");

			System.out.println(is);

			isr = new InputStreamReader(is);

			br = new BufferedReader(isr);
			while ((sCurrentLine = br.readLine()) != null) {
				String[] strAry = sCurrentLine.split(",");
				Student person = new Student(strAry[0],
						Integer.parseInt(strAry[1]));
				if (person.getName().equals(before.getName())
						&& person.getAge() == before.getAge()) {
					result.add(after);
				} else
					result.add(person);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		try {
			File file = new File("C:\\Users\\TEST\\Documents\\test.txt");
			System.out.println(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (checkBeforeWritefile(file)) {
				// Here false is to overwrite file
				bw = new BufferedWriter(new FileWriter(file, false));
				for (Student student : result) {
					bw.write(student.getName());
					bw.write(",");
					bw.write(Integer.toString(student.getAge()));
					bw.newLine();
				}
				bw.close();
			} else {
				System.out.println("error");
			}
		} catch (IOException e) {

		}

	}
}
