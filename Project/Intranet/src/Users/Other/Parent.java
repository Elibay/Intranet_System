package Users.Other;
import Other.*;
import Users.*;
import Course.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Parent extends User implements View {
	Map < Integer, Student > childs;
	public Parent(String name, String surname, int id) {
		super(name, surname, id, "Parent");
		childs = new HashMap < Integer, Student>();
		functions.addAll(ViewFunctions.toAdd());
	}
	public void printChilds() {
		for (Map.Entry<Integer, Student> a : childs.entrySet()) {
			System.out.println(a.getValue());
		}
	}
	public void addChild(Student s) {
		childs.put(s.id, s);
	}
	public static void save (Map <Integer, Parent> Parents) throws IOException {
		try {
			FileOutputStream fos2 = new FileOutputStream("Parents.out");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(Parents);
			fos2.close();
			oos2.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error In Save Parents");
		}
	}
	
	public static Map <Integer, Parent> getParents() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis2 = new FileInputStream("Parents.out");
			ObjectInputStream oin2 = new ObjectInputStream(fis2);
			Map <Integer, Parent> Parents = (HashMap<Integer, Parent>) oin2.readObject();
			oin2.close();
			fis2.close();
			return Parents;
		} catch (FileNotFoundException e) {
			System.out.println("Error In get Parents");
			return new HashMap <Integer, Parent>();
		}
		
	}

	@Override
	public String viewGpa(int id) {
		if (childs.containsKey(id) == false) {
			return "Can't watch this student\n";
		}
		return ViewFunctions.viewGpa(id);
	}

	@Override
	public void viewAttandance(int id) {
		
	}

	@Override
	public String viewTranscript(int id) {
		if (childs.containsKey(id) == false) {
			return "Can't watch this student\n";
		}
		return ViewFunctions.viewGpa(id);
	}

	@Override
	public void viewNews() {
		// TODO Auto-generated method stub
		
	}

}
