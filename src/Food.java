import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * Food class that initializes all the food in the menu
 * of restaurant
 * 
 * @author eduardorocha
 *
 */
public class Food {
	
	//The food and info
	Map<String, Order> food;
	ArrayList<Order> foodInfo;
	//Food Categories
	Order bevs;
	Order chkn;
	Order beef;
	Order noodles;
	Order soupSides;
	Order marketSides;
	Order veggie;
	Order sea;

	//Initializing
	Food(){
		initializeFoodMap();
	}
	
	/**
	 * Finding the food name in the menu
	 * @param name name of food
	 * @return the order associated with that food
	 */
	Order findMe(String name){
		foodInfo = new ArrayList<>();
		foodInfo.add(bevs);
		foodInfo.add(chkn);
		foodInfo.add(beef);
		foodInfo.add(noodles);
		foodInfo.add(soupSides);
		foodInfo.add(marketSides);
		foodInfo.add(veggie);
		foodInfo.add(sea);
		
		Order mine = new Order();
		Double tempPrice = 0.00;

		for(Order or: foodInfo) {
			Set<String> namez = or.getMenu().keySet();
			for(String n: namez) {
				if(n.equals(name)) {
					tempPrice = or.getMenu().get(n);
					mine.populateFoodMap(name, tempPrice);
				}
			}
		}
		return mine;
	}

	/**
	 * Populating each category of food listed in the menu
	 * hardcoded and can be cleaner!
	 */
	public void	initializeFoodMap() {
		
		food = new TreeMap<>();
		bevs = new Order();
		bevs.populateFoodMap("Coke", 2.55);
		bevs.populateFoodMap("Sprite", 2.55);
		bevs.populateFoodMap("Lemonade", 2.55);
		bevs.populateFoodMap("Iced Tea", 2.55);
		bevs.populateFoodMap("Arnold Palmer", 2.55);
		bevs.populateFoodMap("Dr. Pepper", 2.55);
		bevs.populateFoodMap("Water", 2.55);
		bevs.populateFoodMap("Club Soda", 2.55);
		food.put("Beverages",bevs);
		
		chkn = new Order();
		chkn.populateFoodMap("Sesame Chicken", 11.99);
		chkn.populateFoodMap("Orange Chicken", 10.99);
		chkn.populateFoodMap("Honey Chicken", 10.99);
		chkn.populateFoodMap("Kung Pao Chicken", 10.99);
		food.put("Chicken", chkn);
		
		beef = new Order();
		beef.populateFoodMap("Mongolian Beef", 10.99);
		beef.populateFoodMap("Beef Sichuan", 11.99);
		beef.populateFoodMap("Beef & Broccoli", 11.99);
		beef.populateFoodMap("Filet Mignon", 20.99);
		food.put("Beef & Pork", beef);
		
		noodles = new Order();
		noodles.populateFoodMap("Chow Mein Veg", 10.99);
		noodles.populateFoodMap("Chow Mein Chk", 10.99);
		noodles.populateFoodMap("Chow Mein Beef", 10.99);
		noodles.populateFoodMap("Chow Mein Pork", 10.99);
		noodles.populateFoodMap("Chow Mein Shr", 10.99);
		noodles.populateFoodMap("Chow Mein Combo", 10.99);
		noodles.populateFoodMap("Pad Thai Chk", 11.99);
		noodles.populateFoodMap("Pad Thai Beef", 10.99);
		noodles.populateFoodMap("Pad Thai Shr", 10.99);
		noodles.populateFoodMap("Pad Thai Combo", 10.99);
		noodles.populateFoodMap("Pad Thai Veg", 10.99);
		food.put("Noodles", noodles);
		
		
		
		soupSides = new Order();
		soupSides.populateFoodMap("Egg Drop Cup", 11.99);
		soupSides.populateFoodMap("Egg Drop Bowl", 11.99);
		soupSides.populateFoodMap("Hot & Sour Cup", 10.99);
		soupSides.populateFoodMap("Hot & Sour Bowl", 10.99);
		soupSides.populateFoodMap("Wonton Cup", 10.99);
		soupSides.populateFoodMap("Chicken Bowl", 10.99);
		soupSides.populateFoodMap("Caesar Salad", 10.99);
		soupSides.populateFoodMap("Mandarin Salad", 10.99);
		soupSides.populateFoodMap("Side Salad", 10.99);
		food.put("Soup & Salads", soupSides);
		
		marketSides = new Order();
		marketSides.populateFoodMap("Spinach w Garl", 6.75);
		marketSides.populateFoodMap("Fried Rice", 6.75);
		marketSides.populateFoodMap("Sich Asparagus", 6.75);
		marketSides.populateFoodMap("Spicy Green Beans", 6.75);
		marketSides.populateFoodMap("Veggies Stir Fry", 6.75);
		food.put("Market Sides",marketSides);
		
		veggie = new Order();
		veggie.populateFoodMap("Ma Po Tofu", 6.79);
		veggie.populateFoodMap("Buddha Feast", 6.75);
		veggie.populateFoodMap("Spicy Eggplant", 6.75);
		veggie.populateFoodMap("Harvest Curry Bowl", 6.75);
		food.put("Vegatarian", veggie);
		
		sea = new Order();
		sea.populateFoodMap("Honey Shr", 12.00);
		sea.populateFoodMap("Kung Pao Shr", 12.00);
		sea.populateFoodMap("Walnut Shr", 12.00);
		sea.populateFoodMap("Spicy Shr", 12.00);
		food.put("Seafood", sea);
		
	}
	/**
	 * This function is getting the whole order
	 * of chicken,beef,noodles etc. But not a specefic Order!
	 * @param name name of food
	 * @return order
	 */
	public Order getOrder(String name) {
		Order returnMe = new Order();
		returnMe = returnMe.copy(food.get(name));
		return returnMe;
	}
	/**
	 * Get the sub order 
	 * @param name of food
	 * @return the sub order associated with food name
	 */
	public Order getSubOrder(String name) {
		Order subOrder = new Order();
		subOrder = subOrder.createFrom(name);
		return subOrder;
	}

	public static void main(String[] args) {
		

	}

}
