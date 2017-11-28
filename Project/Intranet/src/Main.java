import java.io.IOException;
import Other.*;
import Users.*;
import Users.Employees.Admin;
import Users.Employees.ORworker;
import Users.Employees.Teacher;
import Users.Other.Parent;
import Users.Other.Student;
import Course.*;
import Storage.*;

public class Main {
	static void initialize() throws ClassNotFoundException, IOException {
		Storage.get();
//		Admin admin = new Admin("Elibay", "Nuptebek", 0, 100);
//		admin.addUser(admin);
//		Storage.save();
		
	}
	static void start() {
		int userID = 0;
		System.out.println("Hello welcome to our INTRANET System please login at first");
		while (true) {
			userID = Login.login();
			if (userID != -1)
				break;
		}
		User u = Storage.users.get(userID);
		System.out.println("Welcome " + u.name);
		if (u.type.equals("Student")) {
			Run.runStudent(Storage.students.get(userID));
		}
		else if (u.type.equals("Parent")) {
			Run.runParent(Storage.parents.get(userID));
		}
		else if (u.type.equals("ORworeker")) {
			Run.runORworker(Storage.ORworkers.get(userID));
		}
		else if (u.type.equals("Admin")) {
			Run.runAdmin(Storage.admins.get(userID));
		}
		else if (u.type.equals("Teacher")) {
			Run.runTeacher(Storage.teachers.get(userID));
		}
		
	}
	static void finish() throws IOException {
		Storage.save();
		System.out.println("Bye!");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		initialize();
		start();
		finish();
	}

}
