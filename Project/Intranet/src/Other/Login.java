package Other;
import Other.*;
import Users.*;
import Course.*;
import Storage.*;

import java.util.Scanner;

public class Login {
	public static int login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter login:");
		String input = sc.nextLine();
		int id = Integer.parseInt(input);
		if (Storage.users.containsKey(id) == false) {
			System.out.println("Incorrect login");
			return -1;
		}
		User u = Storage.users.get(id);
		System.out.println("Please enter password:");
		input = sc.nextLine();
		String pass = input;
		if (pass.equals(u.password)) {
			return id;
		}
		System.out.println("Incorrect password");
		return -1;
	}

}
