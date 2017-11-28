package Other;

import java.util.Scanner;

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
		User u = Storage.Storage.users.get(a.id);
		u.changePassword(old, newPassword);
	}
	public static void runStudent(Student s) {
		
	}
	public static boolean run(Admin a, int function) {
		System.out.println("You choose funciton " + a.functions.elementAt(function - 1) + ":");
		Scanner sc = new Scanner(System.in);
		int id = Storage.Storage.getNumberOfUsers();
		if (function == 1) {
			return true;
		}
		if (function == 2) {
			changePass(a);
			return false;
		}
		if (function == 3) {
			System.out.println("Please enter type of User");
			String type = sc.nextLine();
			System.out.println("Please enter Name of User");
			String name = sc.nextLine();
			System.out.println("Please enter Surname of User");
			String surname = sc.nextLine();
			if (type.equals("Student")) {
				System.out.println("Please enter Faculty of User");
				String faculty = sc.nextLine();
				Student s = new Student(name, surname, id, faculty);
				a.addUser(s);
			}
			else if (type.equals("Admin")) {
				System.out.println("Please enter Salary of User");
				int salary = sc.nextInt();
				Admin admin = new Admin(name, surname, id, salary);
				a.addUser(admin);
			}
			else if (type.equals("Teacher")) {
				System.out.println("Please enter Salary of User");
				int salary = sc.nextInt();
				Teacher teacher = new Teacher(name, surname, id, salary);
				a.addUser(teacher);
			}
			else if (type.equals("ORworker")) {
				System.out.println("Please enter Salary of User");
				int salary = sc.nextInt();
				ORworker or = new ORworker(name, surname, id, salary);
				a.addUser(or);
			}
			else if (type.equals("Parent")) {
				Parent pr = new Parent(name, surname, id);
				a.addUser(pr);
			}
			return false;
		}
		return false;
	}
	public static void runAdmin(Admin a) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please choose functions");
			a.viewListOfFunctions();
			int function = sc.nextInt();
			if (run(a, function)) {
				return;
			}
		}
	}
	public static void runTeacher(Teacher t) {
		
	}
	public static void runORworker(ORworker o) {
		
	}
	public static void runParent(Parent p) {
		
	}
	
	
}
