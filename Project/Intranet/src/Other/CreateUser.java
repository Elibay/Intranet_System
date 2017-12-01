package Other;

import java.util.Scanner;

import Users.Employees.Admin;
import Users.Employees.ORworker;
import Users.Employees.Teacher;
import Users.Other.Parent;
import Users.Other.Student;

public class CreateUser {
	public static void updateUser(Admin a, int id){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter type of User");
		String type = sc.nextLine();
		System.out.println("Please enter Name of User");
		String name = sc.nextLine();
		System.out.println("Please enter Surname of User");
		String surname = sc.nextLine();
		int salary = 0;
		if (!(type.equals("Student") || type.equals("Parent"))) {
			System.out.println("Please enter Salary of User");
			salary = sc.nextInt();
		}
		if (type.equals("Student")) {
			System.out.println("Please enter Faculty of User");
			String faculty = sc.nextLine();
			Student s = new Student(name, surname, id, faculty);
			a.addUser(s);
		}
		else if (type.equals("Admin")) {
			Admin admin = new Admin(name, surname, id, salary);
			a.addUser(admin);
		}
		else if (type.equals("Teacher")) {
			Teacher teacher = new Teacher(name, surname, id, salary);
			a.addUser(teacher);
		}
		else if (type.equals("ORworker")) {
			ORworker or = new ORworker(name, surname, id, salary);
			a.addUser(or);
		}
		else if (type.equals("Parent")) {
			Parent pr = new Parent(name, surname, id);
			while (true) {
				System.out.println("Please enter id Of student or 0 to exit");
				int idChild = sc.nextInt();
				if (idChild == 0)
					break;
				Student s = Storage.Storage.students.get(idChild);
				pr.addChild(s);	
			}
			a.addUser(pr);
		}
	}
}
