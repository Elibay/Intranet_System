package Users.Employees;
import Users.*;

public class Employee extends User {
	protected int salary;
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Employee(String name, String surname, int id, int salary, String type) {
		super (name, surname, id, type);
		this.salary = salary;
//		functions.add("View Salary");
	}
	
}
