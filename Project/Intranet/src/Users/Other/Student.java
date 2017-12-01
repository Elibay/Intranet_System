package Users.Other;

import Other.*;
import Users.*;
import Users.Employees.Admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
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
		courses = new HashMap <String, Integer>();
	}
	public String toString() {
		return super.toString() + " Faculty " + faculty;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sumOfCredits;
		result = prime * result + yearOfStudy;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (Double.doubleToLongBits(gpa) != Double.doubleToLongBits(other.gpa))
			return false;
		if (sumOfCredits != other.sumOfCredits)
			return false;
		if (yearOfStudy != other.yearOfStudy)
			return false;
		return true;
	}
	public void addCourse (Course c) {
		if (c.canAddStudent()) {
			courses.put(c.getCourseName(), 0);
			//
			System.out.println(this);
			//
			c.addStudent(this);
			System.out.println("Course " + c + "added!");
		}
		else {
			System.out.println("Overflow");
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
	public String viewGpa(int id) {
		return gpa + "";
	}

	@Override
	public void viewAttandance(int id) {
		ViewFunctions.viewAttandance(id);
	}

	@Override
	public String viewTranscript(int id) {
		String ans = "";
		for(Map.Entry<String, Integer > cur : courses.entrySet()) {
		    String key = cur.getKey();
		    Integer value = cur.getValue();
		    ans += key + " " + value + "\n";
		}
		return ans;
	}
	@Override
	public void viewNews() {
		// TODO Auto-generated method stub
		ViewFunctions.viewNews();
	}
	
	public static void save (Map <Integer, Student> Students) throws IOException {
		try {
			FileOutputStream fos2 = new FileOutputStream("Students.out");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(Students);
			fos2.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error In Save Student");
		}
	}
	public static Map < Integer, Student> getStudents() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis2 = new FileInputStream("Students.out");
			ObjectInputStream oin2 = new ObjectInputStream(fis2);
			Map<Integer, Student> Students = (HashMap<Integer, Student>) oin2.readObject();
			oin2.close();
			fis2.close();
			return Students;
		} catch (FileNotFoundException e) {
			System.out.println("Error ");
			return new HashMap <Integer, Student>();
		}
		
	}	

}
