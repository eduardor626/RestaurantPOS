import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author eduardorocha
 * 
 * BUGS: None Reported Yet
 * 
 * Description: Creating an Order type , which will contain a 
 * list of items and a total depending on how many items in the map.
 * Will also deduct the total or add total 
 *
 */
public class Order{
	private Double total; //price of only items
	private Double balance; //price including tax
	private Double change; //change
	private final Double CA_TAX = 0.095; //CA TAX
	private TreeMap<String,Double> menu; //food and price of food
	
	/**
	 * Initializing an Order
	 */
	Order(){
		total = 0.00;
		balance = 0.00;
		change = 0.00;
		menu = new TreeMap<>();
	}
	
	/**
	 * Order constructor with input of a text file
	 * if user wants to input an order from a file
	 * @param input text file to be read
	 */
	Order(String input){
		total = 0.00;
		balance = 0.00;
		change = 0.00;
		File inputFile = new File(input);
		menu = new TreeMap<>();
		try{Scanner in = new Scanner(inputFile);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			int pos = line.indexOf("/");
			String food = line.substring(0,pos);
			Double price = Double.valueOf(line.substring(pos+1).trim());
			populateFoodMap(food, price);
		}
		}catch(IOException e) {
			System.out.println("File DNE!");
		}
	}
	
	/**
	 * Order constructor taking in another order, copying that order
	 * @param o order to be copied
	 */
	Order(Order o){
		Order me = copy(o);
		this.menu = me.menu;
		
	}
	/**
	 * Clear contents of order
	 */
	public void clearOrder() {
		total = 0.00;
		balance = 0.00;
		change = 0.00;
		menu.clear();
	}
	
	/**
	 * Get the menu of an order
	 * @return the menu which is a map
	 */
	public TreeMap<String,Double> getMenu(){
		return menu;
	}
	
	/**
	 * Populating the food map with a name(key) and price associated
	 * with the key
	 * @param foodName name of food
	 * @param price of that food
	 */
	public void populateFoodMap(String foodName, Double price) {
		if(menu.containsKey(foodName)) {
			Double newPrice = menu.get(foodName)+ price;
			menu.put(foodName, newPrice);
			total = total+newPrice;
			return;
		}
		menu.put(foodName, price);
		total = total + price;
	}
	
	/**
	 * delete a certain food choice from the menu
	 * @param foodName to delete from menu
	 */
	public void deleteFoodChoice(String foodName) {
		Double d = getPrice(foodName);
		menu.remove(foodName);
		total = total - d;
		balance = calcBalance();
	}
	/**
	 * get the price of a certain food 
	 * @param foodName to find price of
	 * @return the price associated with that food name
	 */
	public Double getPrice(String foodName) {
		if(menu.containsKey(foodName))
			return menu.get(foodName);
		return 0.00;
	}
	/**
	 * get the balance
	 * @return the balance of menu
	 */
	public Double getBalance() {
		return balance;
	}
	
	/**
	 * getting the order line by line in a string format
	 * @param or order to be read
	 * @return the order in a string format
	 */
	public String lineOrder(Order or) {
		Food theFood = new Food();
		String me;
		for(Map.Entry<String, Double> e : menu.entrySet()) {
			return e.getKey()+" "+ theFood.findMe(e.getKey()).getPrice(e.getKey());
		}	
		return null;
	}
	
	/**
	 * Geting the price of an order
	 * @param or order to be read
	 * @return the price of that order
	 */
	public Double linePrice(Order or) {
		Food theFood = new Food();
		for(Map.Entry<String, Double> e : menu.entrySet()) {
			return theFood.findMe(e.getKey()).getPrice(e.getKey());
		}	
		return null;
	}
	/**
	 * Calculating the balance of an order
	 * @return the total of the whole order after taxes
	 */
	public Double calcBalance() {
		Double total = 0.00;
		for(Map.Entry<String, Double> e : menu.entrySet()) {
			total = total + e.getValue();
		}		
		Double tax = (CA_TAX * total);
		total = total + tax;
		balance = total;
		return total;
	}
	
	/**
	 * Calculating the subtotal , which is price of order
	 * excluding tax
	 * @return the total
	 */
	public Double calcSubTotal() {
		for(Map.Entry<String, Double> e : this.menu.entrySet()) {
			total = total + e.getValue();
		}	
		return total;
	}
	
	/**
	 * Printing the order
	 */
	public void printOrder() {
		for(Map.Entry<String, Double> e : menu.entrySet()) {
			System.out.println(e.getKey()+" "+e.getValue());
		}	
	}
	
	/**
	 * Copying an order to another
	 * @param other order to be copied
	 * @return a copied version of that order
	 */
	public Order copy(Order other) {
		Order copied = new Order();
		Set<String> names = other.getMenu().keySet();
		for(String n : names) {
			copied.populateFoodMap(n,other.getMenu().get(n));
		}
		return copied;
	}
	
	/**
	 * Creating an order from a name given
	 * @param name name of order
	 * @return an order with correct price and name
	 */
	public Order createFrom(String name) {
		Order mine = new Order();
		Double tempPrice = 0.00;
		Set<String> namez = this.getMenu().keySet();
		for(String n: namez) {
			if(n.equals(name)) {
				tempPrice = this.getMenu().get(n);
				mine.populateFoodMap(name, tempPrice);
			}
		}
		System.out.println();
		return mine;
		
	}
	/**
	 * returning the name of an order along with a price
	 * @param name name of food
	 * @return the name of food and price associated with it 
	 * null otherwise
	 */
	public String toString(String name) {
		Set<String> names = this.getMenu().keySet();
		for(String n : names) {
			if(n.equals(name)) {
				return name + " "+ this.getPrice(name);
			}
		}
		return null;
	}
	
	/**
	 * Accepting a payment 
	 * @param cash amount to be subtracted from balance
	 */
	public void acceptPayment(Double cash) {
		balance = balance - cash;
	}

	/**
	 * Calculating the balance without tax
	 * @return the subtotal
	 */
	public Double calcBalanceNO_TAX() {
		Double total = 0.00;
		Set<String> names = this.getMenu().keySet();
		for(String n : names) {
			total = this.getMenu().get(n);
			System.out.println(n + " = "+ total);
		}
		return total;
	}
	/**
	 * Checking if a client overpaid for the order
	 * @return true if yes false if no
	 */
	public boolean overPayed() {
		if(balance < 0) {
			change = balance * -1;
			return true;
		}
		return false;
	}
	/**
	 * Getting the amount of change due to the client
	 * @return change due
	 */
	public Double getChange() {
		return change;
	}
	/**
	 * Checking the balance divided by number they want to split check by
	 * @param number to split check by
	 * @return the average everyone will be paying
	 */
	public Double splitBill(Integer number) {
		Double splitAmount = balance / number;
		return splitAmount;
	}
	
	public static void main(String[] args) {
		
		Food myFood = new Food();
		Order myOrder = new Order();
	}

}
