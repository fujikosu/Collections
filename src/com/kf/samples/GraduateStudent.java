package com.kf.samples;

public class GraduateStudent extends Student {
	public GraduateStudent(String name, int age) {
		super(name, age);
	}
	
	private String major;
	private int grade;
	private int scholarship;
	
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getScholarship() {
		return scholarship;
	}
	public void setScholarship(int scholarship) {
		this.scholarship = scholarship;
	}
}
