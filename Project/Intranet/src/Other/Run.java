package Other;
import Storage.Storage;
import java.util.Scanner;

import Course.Course;
import Users.User;
import Users.Employees.Admin;
import Users.Employees.ORworker;
import Users.Employees.Teacher;
import Users.Other.Parent;
import Users.Other.Student;

public class Run {
	private static void changePass(User a) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter old Password");
		String old = sc.nextLine();
		System.out.println("Please Enter new Password");
		String newPassword = sc.nextLine();
		a.changePassword(old, newPassword);
		User u = Storage.users.get(a.id);
		if (u.changePassword(old, newPassword))
			System.out.println("Password changed");
		else
			System.out.println("Incorrect old password");
	}
	
	
	public static boolean run(Object a, int function) {
		User u = (User) a;
		System.out.println("You choose funciton " + u.functions.elementAt(function - 1) + ":");
		if (function == 1)
			return true;
		if (function == 2) {
			changePass(u);
			return false;
		}
		if (u.type.equals("Student")) {
			Student s = (Student) a;
			runStudent (s, function);
		}
		else if (u.type.equals("Parent")) {
			Parent p = (Parent) a;
			runParent(p, function);
		}
		else if (u.type.equals("ORworker")) {
			ORworker orr = (ORworker) a;
			runOR(orr, function);
		}
		else if (u.type.equals("Admin")) {
			Admin admin = (Admin) a;
			runAdmin(admin, function);
		}
		else if (u.type.equals("Teacher")) {
			Teacher t = (Teacher) a;
			runTeacher(t, function);
		}
		return false;
	}
	
	public static void runParent(Parent a, int function) {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		if (function != 6) {
			System.out.println("Please enter ID of student");
			id = sc.nextInt();
		}
		switch (function) {
		case 4:
			a.viewGpa(id);
			break;
		case 5:
			a.viewTranscript(id);
			break;
		case 6:
			a.viewNews();
			break;
		default:
			System.out.println("Error");
		}
	}
	
	
	public static void runTeacher(Teacher a, int function) {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		if (function == 2 || function == 3) {
			System.out.println("Print ID of student");
			id = sc.nextInt();
		}
		if (function == 6 || function == 7) {
			a.viewCourses();
			System.out.println("Print ID of Course");
			id = sc.nextInt();
		}
		switch (function) {
		case 3: 
			System.out.println("GPA : " + a.viewGpa(id));
			break;
		case 4:
			System.out.print(a.viewTranscript(id));
			break;
		case 5:
			a.viewNews();
			break;
		case 6:
			Course c = Storage.courses.get(id);
			a.putMarks(c);
			break;
		case 7:
			a.viewCourse(id);
			break;
		default:
			System.out.println("Error");
			break;
		}
	}
	
	public static void runStudent(Student a, int function) {
		switch (function) {
		case 3: 
			System.out.println("GPA : " + a.viewGpa(a.id));
			break;
		case 4:
			System.out.print(a.viewTranscript(a.id));
			break;
		case 5:
			a.viewNews();
			break;
		case 6:
			if (Course.viewAllCourses()) {
				Scanner sc = new Scanner (System.in);
				int func = sc.nextInt();
				if (Storage.courses.containsKey(func) == false)  {
					System.out.println("Error");
					return;
				}
				Course c = Storage.courses.get(func);
				a.addCourse(c);
			}
			break;
		default:
			System.out.println("Error");
			break;
		}
	}
	
	public static void runOR(ORworker a, int function) {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		if (function == 6 || function == 7) {
			System.out.println("Print ID of student");
			id = sc.nextInt();
		}
		switch (function) {
		case 3:
			a.addCourse();
			break;
		case 4:
			if (Teacher.viewAll()) {
				System.out.println("Enter ID of Teacher");
				id = sc.nextInt();
				if (Course.viewAllCourses()) {
					System.out.println("Enter ID of Course");
					int id2 = sc.nextInt();
					Course c = Storage.courses.get(id2);
					Teacher t = Storage.teachers.get(id);
					c.setTeacher(t);
					System.out.println("Teacher Added");
				}
				else {
					System.out.println("Error");
				}
			}
			else {
				System.out.println("Error");
			}
			break;
		case 5:
			String news = sc.nextLine();
			Storage.news.addElement(news);
			break;
		case 6: 
			System.out.println("GPA : " + a.viewGpa(id));
			break;
		case 7:
			System.out.print(a.viewTranscript(id));
			break;
		case 8:
			a.viewNews();
			break;		
		default:
			System.out.println("Error");
			break;
		}
	}
	
	public static void runAdmin(Admin a, int function) {
		int id = Storage.getNumberOfUsers();
		Scanner sc = new Scanner(System.in);
		if (function >= 4 && function <= 8) {
			System.out.println("Please enter id");
			id = sc.nextInt();
		}
		switch (function) {
		case 3:
			Other.CreateUser.updateUser(a, id);
			System.out.println("Added User " + Storage.users.get(id));
			break;
		case 4:
			a.delUser(id); 
			break;
		case 5:
			Other.CreateUser.updateUser(a, id);
			System.out.println("Updated User " + Storage.users.get(id));
			break;
		case 6: 
			System.out.println("GPA : " + a.viewGpa(id));
			break;
		case 7:
			System.out.print(a.viewTranscript(id));
			break;
		case 8:
			a.viewNews();
			break;
		default:
			System.out.println("Error");
			break;
		}
	}
}
