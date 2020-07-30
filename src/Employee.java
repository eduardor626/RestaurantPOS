import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
/**
 * Employee class that is used to define an employee. Use this employee to
 * access their data 
 * @author eduardorocha
 * 
 * BUGS: none recorded yet here
 *
 */
public class Employee implements Comparable<Employee>{
	
	//Data members of an employee
	private String name;
	private String ID;
	private String position;
	private String  phoneNum;
	private Double tips;
	private Double sales;
	
	/**
	 * Employee constructor
	 * @param name of employee
	 * @param id of employee
	 * @param position either server or a waiter in this program
	 */
	Employee(String name, String id, String position){
		this.name = name;
		ID = id;
		this.position = position;
	}
	
	//setting phone number
	public void setPhoneNum(String number) {
		phoneNum = number;
	}
	//getting phone number
	public String getPhoneNum() {
		return phoneNum;
	}
	//setting position
	public void setPosition(String position) {
		this.position = position;
	}
	//getting position
	public String getPosition() {
		return this.position;
	}
	/**
	 * Setting tip amount for employee
	 * @param amount they got tipped
	 */
	public void setTips(Double amount) {
		this.tips+=amount;
	}
	/**
	 * Setting the sales amount for an employee
	 * @param amount of money they have sold
	 */
	public void setSales(Double amount) {
		this.sales+= amount;
	}
	//getting the tip amount
	public Double getTips() {
		return this.tips;
	}
	//getting the sales amount
	public Double getSales() {
		return this.sales;
	}
	//getting ID of employee
	public String getID() {
		return this.ID;
	}
	
	/**
	 * Overriding the toString function in object
	 * to get nice employee data
	 */
	@Override
	public String toString() {
		return this.name + " "+this.ID + " "+this.position;
		
	}
	//getting name of employee
	public String getName() {
		return this.name;
	}
	
	/**
	 * Implementing comparable method
	 * How to compare employees for map implementation etc.
	 */
	@Override
	public int compareTo(Employee o) {
		return this.name.compareTo(o.name);
	}

	/**
	 * EmployeeID class which
	 * Tests comparisons between employee ID's using comparator
	 */
	public class EmployeeByID implements Comparator<Employee>{
		@Override
		public int compare(Employee e1, Employee e2) {
			return e1.getID().compareTo(e2.getID());
		}
	}
	
	
	public static void main(String[] args) {		

	}


}
