package Users;
import Other.*;
import Users.*;
import Course.*;
import Storage.*;
import java.io.Serializable;
import java.util.Vector;

public class User implements Serializable {
	public String name, surname;
	public String password;
	public int id;
	
	public String type;
	public Vector <String> functions;
	
	public String toString() {
		return id + " " + name + " " + surname;
	}
	
	public User (String name, String surname, int id, String type) {
		this.name = name;
		this.surname = surname;
		this.type = type;
		this.id = id;
		password = "123456";
		functions = new Vector<String>();
		functions.add("Exit");
		functions.add("Change Password");
	}
	public boolean changePassword(String oldPassword, String newPassword) {
		if (oldPassword.equals(password)) {
			password = newPassword;
			System.out.println("Password changed");
			return true;
		}
		else {
			System.out.println("Incorrect old password");
			return false;
		}
	}
	public void viewListOfFunctions () {
		int p = 1;
		for (String function: functions) {
			System.out.println(p + ") " + function);
			++ p;
		}
	}
	
}
