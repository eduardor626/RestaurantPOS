import java.util.ArrayList;

/**
 * 
 * @author eduardorocha
 * 
 * Program Report: "Has no bugs yet.. still working."
 * 
 * Description: The Table class of this gui will take in a table number
 * and have an order associated with each table number
 *
 */
public class Table {
	
	//table number and order
	private String name;
	private ArrayList<Order> itsOrder;
	
	/**
	 * Table constructor 
	 * @param name
	 * @param orders
	 */
	Table(String name, ArrayList<Order> orders){
		itsOrder = new ArrayList<>();
		this.name = name;
		itsOrder = orders;		
	}
	//get the order of a table
	public ArrayList<Order> getOrder(){
		return this.itsOrder;
	}
	//get the name of a table
	public String getTableName() {
		return this.name;
	}
	

}
