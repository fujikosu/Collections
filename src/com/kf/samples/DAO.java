package com.kf.samples;

import java.util.ArrayList;

public interface DAO {
		public void search(Student student);
		public void write(Student student);
		public ArrayList<Student> read();
		public void delete();
		void update(Student before, Student after);
}
