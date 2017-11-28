package Users.Other;

import Other.*;
import Users.*;

import java.util.Map;

import Course.*;

public class Student extends User implements View{

	private String faculty;
	private double gpa;
	private int sumOfCredits;
	private int yearOfStudy;
	private Map < String, Integer > courses;
	public Student(String name, String surname, int id, String faculty) {
		super(name, surname, id, "Student");
		this.faculty = faculty;
		gpa = 0;
		sumOfCredits = 0;
		yearOfStudy = 1;
		functions.addAll(ViewFunctions.toAdd());
		functions.addElement("Add Course");
	}
	public void AddCourse (Course c) {
		if (c.canAddStudent()) {
			c.addStudent(this);
			System.out.println("Course " + c + "added!");
		}
		else {
			System.out.println("You can't add course");
		}
	}
	public void updateGpa (double gpa, int credit, String nameOfCourse) {
		if (courses.containsKey(nameOfCourse)) {
			sumOfCredits -= courses.get(nameOfCourse);
		}
		this.gpa = this.gpa * sumOfCredits + gpa * credit;
		sumOfCredits += credit;
		this.gpa /= sumOfCredits;
	}
	@Override
	public void viewGpa(int id) {
		ViewFunctions.viewGpa(id);
	}

	@Override
	public void viewAttandance(int id) {
		ViewFunctions.viewAttandance(id);
	}

	@Override
	public void viewTranscript(int id) {
		// TODO Auto-generated method stub
		ViewFunctions.viewTranscript(id);
	}

	@Override
	public void viewNews() {
		// TODO Auto-generated method stub
		ViewFunctions.viewNews();
	}

}
