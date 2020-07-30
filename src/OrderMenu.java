import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 * OrderMenu class that sets up the order menu panel
 * This panel is where an order is constructed and allows the user
 * to navigate through choices based on a pre-defined menu
 * 
 * @author eduardorocha
 * 
 * BUGS: none recorded yet so far
 * 
 * Program Report: still need to implement some buttons
 *
 */
public class OrderMenu extends JPanel{
	
	//Main Panels
	JPanel center;
	JPanel north;
	JPanel south;
	
	//Sub Panels
	JPanel centerLeft;
	JPanel centerMiddle;
	JPanel centerRight;
	
	//Sub Order Pan
	JPanel options;
	
	JTextArea foodArea;
	
	//Order and Food 
	Order myOrder;
	ArrayList<Order> orders;
	Food theFood = new Food();
	Order currentOrder = new Order();
	private String nameOfTable;
	
	
	//For highlights
	private Highlighter.HighlightPainter painter;
	//Check if order has been ordered
	private boolean ordered;
	
	
	/**
	 * Order Menu initializer
	 * @param name name of table
	 */
	OrderMenu(String name){
		nameOfTable = name;
		setLayout(new BorderLayout());
		createComponents(name);
	}
	/**
	 * Creating components of the Order Menu
	 * @param name name of table
	 */
	public void createComponents(String name) {
		orders = new ArrayList<>();
		createNorth();
		setSouth();
		setCenter(name);
	}
	
	/**
	 * Setting the center panels of Order Menu
	 * which are the most important 
	 * @param name of table to be displayed in Order Area
	 */
	public void setCenter(String name) {
		setCenterLeft(name);
		setCenterMiddle();
		setCenterRight();
	}
	
	/**
	 * Setting the center left panel with the order area
	 * @param name the name of table
	 */
	public void setCenterLeft(String name) {
		center = new JPanel();
		center.setLayout(new BorderLayout());
		
		centerLeft = new JPanel();
		centerLeft.setPreferredSize(new Dimension(300,this.HEIGHT));
		centerLeft.setBackground(new Color(242, 161, 0));
		
		foodArea = new JTextArea(30,25); 
		foodArea.setFont(new Font("Arial",Font.BOLD,14));
		foodArea.setEditable(true);
		JScrollPane scroll = new JScrollPane(foodArea);
		
		JLabel tableNum = new JLabel(name);
		tableNum.setFont(new Font("Times New Roman", Font.BOLD,20));
		
		centerLeft.add(tableNum);
		centerLeft.add(scroll);
		center.add(centerLeft,BorderLayout.WEST);
		add(center);	
	}
	
	//setting the food options panel to the right
	public void setCenterRight() {
		centerRight = new JPanel();
		centerRight.setLayout(new BorderLayout());
		centerRight.setPreferredSize(new Dimension(340,this.HEIGHT));
		setCenterRightBorders();
		setFoodChoiceButtons("Beverages");
		center.add(centerRight,BorderLayout.EAST);
	}
	
	//Creating the button panel for the sub categories
	public JButton makeSubSetButtons(String names) {
		JButton subSet = new JButton(names);
		subSet.addActionListener(new OrderListener());
		return subSet;
	}
	
	/**
	 * Creating the borders of the center right
	 */
	public void setCenterRightBorders() {
		JPanel n = new JPanel();
		JPanel s = new JPanel();
		JPanel e = new JPanel();
		JPanel w = new JPanel();
		
		JPanel[] border = new JPanel[4];
		border[0] =n;
		border[1] =s;
		border[2] =e;
		border[3] =w;
		
		for(int i = 0; i<4; i++) {
			border[i].setBackground(Color.ORANGE);
			if(i <2) {
			border[i].setPreferredSize(new Dimension(centerRight.WIDTH,10));
			}
			else {
				border[i].setPreferredSize(new Dimension(10,centerRight.HEIGHT));
			}
		}
		centerRight.add(border[0],BorderLayout.NORTH);
		centerRight.add(border[1],BorderLayout.SOUTH);
		centerRight.add(border[2],BorderLayout.EAST);
		centerRight.add(border[3],BorderLayout.WEST);
	}
	
	/**
	 * Creating the centermiddle of the center panel
	 */
	public void setCenterMiddle() {
		centerMiddle = new JPanel();
		centerMiddle.setLayout(new BorderLayout());
		centerMiddle.setPreferredSize(new Dimension(50,this.HEIGHT));

		//right border to look nice
		JPanel rightBorder = new JPanel();
		rightBorder.setPreferredSize(new Dimension(10,this.HEIGHT));
		rightBorder.setBackground(new Color(242, 161, 0));
		//left border to look nice
		JPanel leftBorder = new JPanel();
		leftBorder.setPreferredSize(new Dimension(10,this.HEIGHT));
		leftBorder.setBackground(new Color(242, 161, 0));
		
		JPanel optionsPan = new JPanel();
		optionsPan.setPreferredSize(new Dimension(50,this.HEIGHT));
		optionsPan.setBackground(new Color(242, 161, 0));
		optionsPan.setLayout(new GridLayout(8,0,0,5));
		optionsPan.add(makeButton("Beverages",true));
		optionsPan.add(makeButton("Soup & Salads",true));
		optionsPan.add(makeButton("Market Sides",true));
		optionsPan.add(makeButton("Chicken",true));
		optionsPan.add(makeButton("Beef & Pork",true));
		optionsPan.add(makeButton("Seafood",true));
		optionsPan.add(makeButton("Vegatarian",true));
		optionsPan.add(makeButton("Noodles",true));

		centerMiddle.add(optionsPan,BorderLayout.CENTER);
		centerMiddle.add(rightBorder,BorderLayout.EAST);
		centerMiddle.add(leftBorder,BorderLayout.WEST);
		center.add(centerMiddle,BorderLayout.CENTER);
	}
	
	/**
	 * Creating the food buttons for each category s
	 * EX: "Chicken" -> chicken orders
	 * @param name name of the key with buttons to set
	 */
	public void setFoodChoiceButtons(String name) {
		//Panel where grid is on centerRight's center
		if(!(name.equals("Beverages"))) {
			options.removeAll();
			options = new JPanel();
			options.setPreferredSize(new Dimension(200,this.HEIGHT));
			options.setLayout(new GridLayout(5,10,3,3));
			options.setBackground(Color.ORANGE);
		}
		else {
		options = new JPanel();
		options.setPreferredSize(new Dimension(200,this.HEIGHT));
		options.setLayout(new GridLayout(5,10,3,3));
		options.setBackground(Color.ORANGE);
		}
		
		createFoodOptions(name);
	}
	
	/**
	 * Creating the food options panels based on category
	 * @param name category of food choices
	 */ 
	public void createFoodOptions(String name) {
		theFood = new Food();
		Order myOrder = new Order();
		myOrder = theFood.getOrder(name);
		Set<String> names = myOrder.getMenu().keySet();
		for(String n : names) {
			System.out.println(n+" "+ myOrder.getMenu().get(n));
			options.add(makeSubSetButtons(n));
			centerRight.add(options,BorderLayout.CENTER);
			options.revalidate();
			centerMiddle.revalidate();
		}
		
	}
	
	/**
	 * MenuChoiceListener class which checks for what categories are
	 * clicked in the center middle panel and revalidates the options 
	 * panel based on the selected category	 
	 */
	public class MenuChoiceListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
			case "Beverages":
				centerRight.remove(options);
				setFoodChoiceButtons("Beverages");
				options.revalidate();
				break;
			case "Soup & Salads":
				setFoodChoiceButtons("Soup & Salads");
				options.revalidate();
				break;
			case "Market Sides":
				setFoodChoiceButtons("Market Sides");
				options.revalidate();
				break;
			case "Chicken":
				setFoodChoiceButtons("Chicken");
				break;
			case "Beef & Pork":
				setFoodChoiceButtons("Beef & Pork");
				break;
			case "Seafood":
				setFoodChoiceButtons("Seafood");
				break;
			case "Vegatarian":
				setFoodChoiceButtons("Vegatarian");
				break;
			case "Noodles":
				setFoodChoiceButtons("Noodles");
				break;
			case "DONE":
				foodArea.getHighlighter().removeAllHighlights();
				ordered = true;
				System.exit(0);
				break;
			case "Order":
			case "To Go":
				ordered = true;
				foodArea.getHighlighter().removeAllHighlights();
				break;
			case "Close Table":
				if(ordered) 
				{
					JFrame myFrame = new JFrame();
					Payment newPan = new Payment(nameOfTable,orders);
					newPan.setVisible(true);
					myFrame.add(new Payment(nameOfTable,orders));
					myFrame.setVisible(true);
					myFrame.pack();
					myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(center);
					frame.dispose();
				}
				break;
			}
		}
	}
	
	/**
	 * OrderListener class that appends the food ordered area
	 * with current choice picked by user
	 */
	public class OrderListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			centerRight.revalidate();
			String me = e.getActionCommand();
			me = me.trim();
			orders.add(storeMe(me));
			currentOrder = theFood.findMe(me);
			foodArea.append(currentOrder.toString(me)+"\n");
			highlightTextArea();
		}
	}
	
	/**
	 * Method to store an order by finding its name
	 * @param me the food name
	 * @return the order associated with the food name
	 */
	public Order storeMe(String me) {
		
		Order temp = new Order();
		temp = theFood.findMe(me);
		temp.populateFoodMap(me, temp.getPrice(me));
		return temp;
	}
	
	/**
	 * Highlighting method that highlights the order list 
	 * based upon length of the order
	 */
	public void highlightTextArea() {
        try {
			int startIndex = foodArea.getLineStartOffset(0);
            int endIndex = foodArea.getLineEndOffset(0);//end of line
            painter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
            foodArea.getHighlighter().addHighlight(startIndex, endIndex, painter);//highlight that area

		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Printing the order
	 */
	public void convertToOrder() {

	}
	
	/**
	 * Making button
	 * @param name of the button name
	 * @param t if true one of the menu categories button is made
	 * @return a button with a listener if t = true, else a regular button
	 */
	public JButton makeButton(String name, boolean t) {
		if(t) {
			JButton myButton = new JButton(name);
			myButton.setFont(new Font("Arial",Font.PLAIN,16));
			myButton.addActionListener(new MenuChoiceListener());
			return myButton;	
		}else {
			return makeButton(name);
		}	
	}
	

	/**
	 * Creating the north side of the Order Menu
	 */
	public void createNorth() {
		north = new JPanel();
		north.setLayout(new BorderLayout());
		north.setBackground(Color.ORANGE);
		north.setPreferredSize(new Dimension(this.WIDTH,60));
		createNorthButtons();
		add(north,BorderLayout.NORTH);
	}
	
	/**
	 * Creating the north buttons of the Order Menu
	 */
	public void createNorthButtons() {
		JPanel northLeftButtons = new JPanel();
		northLeftButtons.setBackground(Color.ORANGE);
		northLeftButtons.setPreferredSize(new Dimension(300,60));
		northLeftButtons.setLayout(new GridLayout(1,2,2,2));
		northLeftButtons.add(makeButton("Tables"));
		northLeftButtons.add(makeButton("Split Check"));
		
		JPanel donePan = new JPanel();
		JPanel northRightButtons = new JPanel();
		setNorthRightButtons(donePan,northRightButtons);

		north.add(donePan,BorderLayout.CENTER);
		north.add(northLeftButtons,BorderLayout.WEST);
		north.add(northRightButtons,BorderLayout.EAST);
	}
	
	/**
	 * Creating the north right buttons of north panel
	 * @param donePan the panel that exits panel
	 * @param northRightButtons panel with rest of buttons 
	 */
	public void setNorthRightButtons(JPanel donePan,JPanel northRightButtons) {
		donePan.setLayout(new BorderLayout());
		JButton done = new JButton("DONE");
		done.setFont(new Font("Arial", Font.BOLD, 18));
		done.setBackground(Color.GREEN);
		done.add(Box.createRigidArea(new Dimension(5,0)));
		done.setOpaque(true);
		done.addActionListener(new MenuChoiceListener());
		donePan.add(done,BorderLayout.CENTER);
		
		northRightButtons.setPreferredSize(new Dimension(300,60));
		northRightButtons.setLayout(new GridLayout(1,6,4,8));
		northRightButtons.setBackground(Color.ORANGE);
		northRightButtons.add(makeButton("Order"));
		northRightButtons.add(makeButton("To Go"));
		
	}

	/**
	 * Making a button from a name
	 * @param name of button
	 * @return a button with a listener
	 */
	public JButton makeButton(String name) {
		JButton myButton = new JButton(name);
		myButton.setFont(new Font("Arial",Font.BOLD,16));
		myButton.addActionListener(new MenuChoiceListener());
		return myButton;
	}
	
	/**
	 * Setting the south side of the Order Menu
	 */
	public void setSouth() {
		south = new JPanel();
		south.setLayout(new BorderLayout());
		south.setBackground(Color.ORANGE);
		south.setPreferredSize(new Dimension(this.WIDTH,80));
		setSouthLeft();
		setSouthRight();
		
		add(south,BorderLayout.SOUTH);
	}
	
	/**
	 * Creating the south left panel of the Order Menu
	 */
	public void setSouthLeft() {
		JPanel southLeft= new JPanel();
		southLeft.setPreferredSize(new Dimension(150,south.HEIGHT));
		southLeft.setLayout(new BorderLayout());
		southLeft.setBackground(Color.RED);
		southLeft.add(makeButton("Close Table"),BorderLayout.CENTER);
		south.add(southLeft,BorderLayout.WEST);
	}
	
	/**
	 * Setting the south right panels 
	 */
	public void setSouthRight() {
		//Whole southRight Panel
		JPanel southRight  = new JPanel();
		southRight.setPreferredSize(new Dimension(400,south.HEIGHT));
		southRight.setLayout(new BorderLayout());
		southRight.setBackground(Color.magenta);
		setBorders(southRight);
		
		JPanel bottomRowButtons = new JPanel();
		createBottomRowButtons(bottomRowButtons);
		
		southRight.add(bottomRowButtons,BorderLayout.CENTER);
		south.add(southRight,BorderLayout.CENTER);
	}
	
	/**
	 * Setting the bottom row buttons for the south panel of Order
	 * Menu
	 * @param bottomRowButtons buttons panel where buttons will be placed
	 */
	public void createBottomRowButtons(JPanel bottomRowButtons) {
		bottomRowButtons.setBackground(Color.ORANGE);
		bottomRowButtons.setLayout(new GridLayout(1,0,4,0));
		bottomRowButtons.add(makeButton("Repeat"));
		bottomRowButtons.add(makeButton("Modify"));
		bottomRowButtons.add(makeButton("Delete"));
		bottomRowButtons.add(makeButton("Hold"));
		
	}
	
	/**
	 * Setting the borders of the south right panel
	 * @param southRight panel borders will be set on
	 */
	public void setBorders(JPanel southRight) {
		//leftBorder of south rightPanel
		JPanel leftBoard = new JPanel();
		leftBoard.setPreferredSize(new Dimension(150,south.HEIGHT));
		leftBoard.setBackground(Color.ORANGE);
		southRight.add(leftBoard,BorderLayout.WEST);
		
		//northBorder of south rightPanel
		JPanel northBoard = new JPanel();
		northBoard.setPreferredSize(new Dimension(southRight.WIDTH,5));
		northBoard.setBackground(new Color(242, 161, 0));
		southRight.add(northBoard,BorderLayout.NORTH);
		
	}

	public static void main(String[] args) {	

	}



}
