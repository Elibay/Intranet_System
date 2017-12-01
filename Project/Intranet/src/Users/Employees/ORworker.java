package Users.Employees;
import Other.*;
import Users.*;
import Course.*;
import Storage.*;
import java.util.*;
import java.io.*;

public class ORworker extends Employee implements View {

	public ORworker(String name, String surname, int id, int salary) {
		super(name, surname, id, salary, "ORworker");
		functions.add("Add Course");
		functions.add("Change Course Teacher");
		functions.add("Add News");
		
		functions.addAll(ViewFunctions.toAdd());
	}
	
	public void addNews() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter text of News:");
		String text = sc.nextLine();
		Storage.news.add(text);
	}
	
	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter:");
		System.out.println("Name of Course");
		String name = sc.nextLine();
		System.out.println("Max Capasity of Course");
		int cap = sc.nextInt();
		System.out.println("How many credits course has?");
		int cred = sc.nextInt();
		int id = Storage.courses.size();
		id ++;
		Course course = new Course (name, cap, cred, id);
		Storage.courses.put(id, course);
		System.out.println("Course added!");
	}
	
	public static void save (Map <Integer, ORworker> ORWorkers) throws IOException {
		try {
			FileOutputStream fos2 = new FileOutputStream("ORWorkers.out");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(ORWorkers);
			fos2.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error In Save ORWorkers");
		}
	}
	
	public static Map <Integer, ORworker> getORworkers() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis2 = new FileInputStream("ORWorkers.out");
			ObjectInputStream oin2 = new ObjectInputStream(fis2);
			Map <Integer, ORworker> ORworkers = (HashMap<Integer, ORworker>) oin2.readObject();
			oin2.close();
			fis2.close();
			return ORworkers;
		} catch (FileNotFoundException e) {
			System.out.println("Error In get OR Workers");
			return new HashMap <Integer, ORworker>();
		}
		
	}
	
	@Override
	public String viewGpa(int id) {
		// TODO Auto-generated method stub
		return ViewFunctions.viewGpa(id);
	}

	@Override
	public void viewAttandance(int id) {
		// TODO Auto-generated method stub
		ViewFunctions.viewAttandance(id);
	}

	@Override
	public String viewTranscript(int id) {
		// TODO Auto-generated method stub
		return ViewFunctions.viewTranscript(id);
	}

	@Override
	public void viewNews() {
		// TODO Auto-generated method stub
		ViewFunctions.viewNews();
	}
	
}
