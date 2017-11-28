package Storage;
import Other.*;
import Users.*;
import Users.Employees.Admin;
import Users.Employees.ORworker;
import Users.Employees.Teacher;
import Users.Other.Parent;
import Users.Other.Student;
import Course.*;
import Storage.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


public class Storage {
	public static Map < Integer, User > users; // +
	public static Map < Integer, ORworker> ORworkers; // +
	public static Map < Integer, Student > students; // +
	public static Map < Integer, Parent > parents; // +
	public static Map < Integer, Course> courses; // +
	public static Map < Integer, Teacher> teachers; // +
	public static Map < Integer, Admin > admins; // +
	public static Vector < String> news; // +
	
	public static int getNumberOfUsers() {
		return users.size();
	}
	
	public static void get() throws ClassNotFoundException, IOException {
		courses = Course.getCourses();
		ORworkers = ORworker.getORworkers();
		parents = Parent.getParents();
		teachers = Teacher.getTeachers();
		admins = Admin.getAdmins();
		users = getUsers();
		news = getNews();
	}
	
	public static void save() throws IOException {
		Course.save(courses);
		ORworker.save(ORworkers);
		Parent.save(parents);
		Teacher.save(teachers);
		Admin.save(admins);
		save(users);
		save(news);
	}
	
	public static void save (Vector <String> news) throws IOException {
		try {
			FileOutputStream fos2 = new FileOutputStream("News.out");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(news);
			fos2.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error In Save News");
		}
	}
	
	public static void save (Map <Integer, User> Users) throws IOException {
		try {
			FileOutputStream fos2 = new FileOutputStream("Users.out");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(Users);
			fos2.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error In Save Users");
		}
	}
	
	public static Vector <String> getNews() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis2 = new FileInputStream("News.out");
			ObjectInputStream oin2 = new ObjectInputStream(fis2);
			Vector < String > news = (Vector<String>) oin2.readObject();
			oin2.close();
			fis2.close();
			return news;
		} catch (FileNotFoundException e) {
			System.out.println("Error In get OR Workers");
			return new Vector <String>();
		}
		
	}
	public static Map <Integer, User> getUsers() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis2 = new FileInputStream("Users.out");
			ObjectInputStream oin2 = new ObjectInputStream(fis2);
			Map <Integer, User> Users = (HashMap<Integer, User>) oin2.readObject();
			oin2.close();
			fis2.close();
			return Users;
		} catch (FileNotFoundException e) {
			System.out.println("Error In get OR Users");
			return new HashMap <Integer, User>();
		}
		
	}
	
}
