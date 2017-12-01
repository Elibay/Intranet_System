package Users.Employees;
import Other.*;
import Users.*;
import Users.Other.Student;
import Course.*;
import Storage.Storage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Teacher extends Employee implements View {
	
	public Teacher(String name, String surname, int id, int salary) {
		super(name, surname, id, salary, "Teacher");
		functions.addAll(ViewFunctions.toAdd());
		functions.add("Put Marks");
		functions.add("View Course");
		functions.add("Manage Course Files");
	}
	public boolean equals(Object a) {
		if (a.getClass() != this.getClass() || a == null)
			return false;
		Teacher t = (Teacher) a;
		if (t.id != id)
			return false;
		return true;
	}
	public void viewCourse(int id) {
		Course c = Storage.courses.get(id);
		if (Storage.courses.containsKey(id) == false || this.equals(c.getTeacher()) == false) {
			System.out.println("Can't watch this course");
			return;
		}
		c.printAllStudents();
	}
	public void viewCourses() {
		for (Map.Entry< Integer, Course> entry : Storage.courses.entrySet()){
		    Course c = entry.getValue();
		    if (this.equals(c.getTeacher())) {
		    	System.out.println(c.getID() + ") " + c);
		    }
		}
	}
	public static boolean viewAll() {
		if (Storage.teachers.size() == 0) {
			System.out.println("Doesn't have avialbe courses");
			return false;
		}
		for (Map.Entry< Integer, Teacher> entry : Storage.teachers.entrySet()){
		    System.out.println(entry.getValue());
		}
		return true;
	}
	public void putMarks(Course c) {
		System.out.println("All students:");
		c.printAllStudents();
		System.out.println();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter 0 to exit or write ID of student");
			int next = sc.nextInt();
			if (next == 0)
				break;
			System.out.println("Mark:");
			int mark = sc.nextInt();
			Student s = Storage.students.get(next);
			c.changeMark(s, mark);
		}
	}
	
	public static void save (Map <Integer, Teacher> Teachers) throws IOException {
		try {
			FileOutputStream fos2 = new FileOutputStream("Teachers.out");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(Teachers);
			fos2.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error In Save Teachers");
		}
	}
	
	public static Map <Integer, Teacher> getTeachers() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis2 = new FileInputStream("Teachers.out");
			ObjectInputStream oin2 = new ObjectInputStream(fis2);
			Map <Integer, Teacher> Teachers = (HashMap<Integer, Teacher>) oin2.readObject();
			oin2.close();
			fis2.close();
			return Teachers;
		} catch (FileNotFoundException e) {
			System.out.println("Error In get Teachers");
			return new HashMap <Integer, Teacher>();
		}
		
	}
	@Override
	public String viewGpa(int id) {
		return ViewFunctions.viewGpa(id);
	}

	@Override
	public void viewAttandance(int id) {
		ViewFunctions.viewAttandance(id);
	}

	@Override
	public String viewTranscript(int id) {
		return ViewFunctions.viewTranscript(id);
	}

	@Override
	public void viewNews() {
		// TODO Auto-generated method stub
		ViewFunctions.viewNews();
	}
}
