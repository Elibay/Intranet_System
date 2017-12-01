package Course;
import Storage.Storage;
import java.io.*;
import java.util.*;

import Users.Employees.Teacher;
import Users.Other.Student;

public class Course implements Serializable {
	private String name;
	private Teacher teacher;
	private int capacity, credit;
	private int idOfCourse, numberOfStudents;
	private Map < Student, Integer > students;
	
	public int getID() {
		return idOfCourse;
	}
	public static boolean viewAllCourses () {
		if (Storage.courses.size() == 0) {
			System.out.println("Doesn't have avialbe courses");
			return false;
		}
		for (Map.Entry< Integer, Course> entry : Storage.courses.entrySet()){
		    System.out.println(entry.getKey() + ") " + entry.getValue());
		}
		return true;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void changeMark(Student s, int mark) {
		if (students.containsKey(s) == false) {
			System.out.println("Student not found");
			return;
		}
		students.replace(s, mark);
		System.out.println("Mark changed on student " + s.name);
		double gpa = getGpa(mark);
		s.updateGpa(gpa, credit, name);
	}
	public void printAllStudents() {
		if (students.isEmpty()) {
			System.out.println("Course hasn't got students");
			return;
		}
		for (Map.Entry< Student, Integer> entry : students.entrySet()){
		    System.out.println(entry.getKey() + " MARK: " + entry.getValue());
		}
	}
	public void addStudent(Student s) {
		students.put(s, 0);
	}
	private double getGpa(int x) {
		if (x < 50)
			return 0;
		x -= 50;
		x /= 5;
		return Math.min(4.0, 1 + x * 0.34);
	}
	
	public static void save (Map <Integer, Course> courses) throws IOException {
		try {
			FileOutputStream fos2 = new FileOutputStream("courses.out");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(courses);
			fos2.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error In Save Course");
		}
	}
	public static Map < Integer, Course> getCourses() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis2 = new FileInputStream("courses.out");
			ObjectInputStream oin2 = new ObjectInputStream(fis2);
			Map<Integer, Course> courses = (HashMap<Integer, Course>) oin2.readObject();
			oin2.close();
			fis2.close();
			return courses;
		} catch (FileNotFoundException e) {
			System.out.println("Error ");
			return new HashMap <Integer, Course>();
		}
		
	}
	
	public Course(String name, int capacity, int credit, int idOfCourse) {
		this.name = name;
		this.capacity = capacity;
		this.credit = credit;
		this.idOfCourse = idOfCourse;
		numberOfStudents = 0;
		students = new HashMap < Student, Integer >();
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public int getSumCredit() {
		return credit;
	}
	public String getCourseName() {
		return name;
	}
	public int getCapacity() {
		return capacity;
	}
	public String toString() {
		if (teacher != null)
			return name + " Capacity: " + capacity + " Credits: " + credit + " Teacher: " + teacher.name + " " + teacher.surname;
		return name + " Capacity: " + capacity + " Credits: " + credit + " Teacher: -";
	}
	public boolean canAddStudent() {
		if (capacity >= numberOfStudents + 1)
			return true;
		return false;
	}
	
}
