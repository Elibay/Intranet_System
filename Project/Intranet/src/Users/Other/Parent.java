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

public class Parent extends User {
	public Parent(String name, String surname, int id) {
		super(name, surname, id, "Parent");
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

}
