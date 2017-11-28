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
		functions.addElement("Put Marks");
		functions.addElement("");
	}
	public void putMarks(Course c) {
		c.printAllStudents();
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
		sc.close();
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
