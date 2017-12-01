package Other;

import java.util.Vector;
import Storage.Storage;
import Users.Other.Student;
public class ViewFunctions {
	
	public static Vector <String> toAdd() {
		Vector <String> toAdd = new Vector <String>();
		//toAdd.add("View Attandance");
		toAdd.add("View Gpa");
		toAdd.add("View Transcript");
		toAdd.add("View News");
		return toAdd;
	}
	
	public static String viewGpa(int id) {
		if (Storage.students.containsKey(id) == false) {
			return "Error incorrect id\n";
		}
		Student s = Storage.students.get(id);
		return s.viewGpa(id);
	}

	public static String viewAttandance(int id) {
		if (Storage.students.containsKey(id) == false) {
			return "Error incorrect id\n";
		}
		Student s = Storage.students.get(id);
		return "";
	}

	public static String viewTranscript(int id) {
		return "";
	}

	public static void viewNews() {
		for (String s: Storage.news) {
			System.out.println(s);
		}
	}
	
}
