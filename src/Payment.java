import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Payment class that takes care of the amount of money
 * the user gives to the business. Displays a total, an order
 * summary along with button options for payment choice. Allows
 * for the user to close the payment panel once Total balance is met
 * @author eduardorocha
 * 
 * BUGS: fix the validation when user enters payment amount they can enter
 * 		something that doesn't convert nicely to a double
 *
 */
public class Payment extends JPanel{
	
	//Main panels that the payment menu will work with
	JPanel center;
	JPanel right;
	JPanel north;
	JPanel south;
	
	//Sub Panels
	JPanel centerLeft;
	JPanel centerMiddle;
	JPanel closePan;
	JPanel centerRight;

	
	//Area where total is displayed (ARRAY LIST<ORDER>)
	JTextArea totalArea;
	ArrayList<Order> thisOrder;
	JLabel tableName;

	//Data members to work with that handle payment calculations
	private Double subTotal;
	private Double Total;
	private Double tax;
	private Double balance;
	private Double tip;	
	private String currentTotal;
	private String currentBalance;	
	boolean closed = false;

	
	/**
	 * Takes in a table name, and its order
	 * produces a payment window with all functionalities of payment
	 * @param name name of table
	 */
	Payment(String name, ArrayList<Order> orders){
		thisOrder = new ArrayList<Order>();
		thisOrder = orders;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800,600));
		createComponents(name);
		setOrder();
		setTotalArea();
	}
	
	/**
	 * Creating the components of the Payment Panel
	 * @param name name of table associated with the order
	 */
	public void createComponents(String name) {
		createNorth();
		setSouth();
		setCenter(name);
	}
	
	/**
	 * Setting the center panel which will be the most
	 * panel with the most functionality 
	 * @param name table name to be displayed w/ the order
	 */
	public void setCenter(String name) {
		setCenterLeft(name);
		setCenterMiddle();
		setCenterRight();
	}
	
	/**
	 * The center panel of the middle center panel which
	 * displays the close button when a table is paid out
	 */
	public void setCenterMiddle() {
		centerMiddle = new JPanel();
		centerMiddle.setLayout(new BorderLayout());
		centerMiddle.setPreferredSize(new Dimension(50,this.HEIGHT));
		
		closePan = new JPanel();
		closePan.setPreferredSize(new Dimension(50,this.HEIGHT));
		closePan.setBackground(new Color(242, 161, 0));
		closePan.setLayout(new GridLayout(1,0,0,5));
		centerMiddle.add(closePan,BorderLayout.CENTER);

		setBordersForCenterMiddle();
		center.add(centerMiddle,BorderLayout.CENTER);
	}
	
	/**
	 * Setting nice borders in the center middle panel
	 * so that the close button displays properly 
	 * in the center
	 */
	public void setBordersForCenterMiddle() {
		JPanel rightBorder = new JPanel();
		rightBorder.setPreferredSize(new Dimension(10,this.HEIGHT));
		rightBorder.setBackground(new Color(242, 161, 0));
		
		JPanel leftBorder = new JPanel();
		leftBorder.setPreferredSize(new Dimension(10,this.HEIGHT));
		leftBorder.setBackground(new Color(242, 161, 0));

		JPanel northBorder = new JPanel();
		northBorder.setPreferredSize(new Dimension(this.WIDTH,190));
		northBorder.setBackground(new Color(242, 161, 0));

		JPanel southBorder = new JPanel();
		southBorder.setPreferredSize(new Dimension(this.WIDTH,190));
		southBorder.setBackground(new Color(242, 161, 0));
		centerMiddle.add(northBorder,BorderLayout.NORTH);
		centerMiddle.add(southBorder,BorderLayout.SOUTH);
		centerMiddle.add(rightBorder,BorderLayout.EAST);
		centerMiddle.add(leftBorder,BorderLayout.WEST);	
	}
	
	/**
	 * Setting the center left of center panel which displays
	 * the table name, order, and balances.
	 * @param name table name to be displayed
	 */
	public void setCenterLeft(String name) {
		center = new JPanel();
		center.setLayout(new BorderLayout());
		centerLeft = new JPanel();
		centerLeft.setPreferredSize(new Dimension(300,this.HEIGHT));
		centerLeft.setBackground(new Color(242, 161, 0));
		
		setOrderArea(name);
		center.add(centerLeft,BorderLayout.WEST);
		add(center);
	}
	
	/**
	 * Setting the order area of the left side of the center
	 * panel. Also using fonts to set up how font is displayed
	 * 
	 * @param name table name to be displayed 
	 */
	public void setOrderArea(String name) {
		totalArea = new JTextArea(30,25); 
		totalArea.setFont(new Font("Arial", Font.BOLD, 14));
		totalArea.setEditable(true);
		JScrollPane scroll = new JScrollPane(totalArea);
		JLabel tableNum = new JLabel(name);
		tableNum.setFont(new Font("Times New Roman", Font.BOLD,20));
		
		centerLeft.add(tableNum);
		centerLeft.add(scroll);
	}
	
	/**
	 * Setting the center right of the center panel
	 * which will have all the payment options that will be used
	 * for the calculations. 
	 */
	public void setCenterRight() {
		centerRight = new JPanel();
		centerRight.setLayout(new BorderLayout());
		centerRight.setPreferredSize(new Dimension(340,this.HEIGHT));
		setCenterRightBorders();
		
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());
		subPanel.setBackground(Color.CYAN);
		setPaymentButtons(subPanel);
		rightOfsubPanel(subPanel);

		centerRight.add(subPanel);
		
		JPanel northBorder = new JPanel();
		northBorder.setPreferredSize(new Dimension(this.WIDTH,10));
		northBorder.setBackground(new Color(242, 161, 0));
		centerRight.add(northBorder,BorderLayout.NORTH);
		
		center.add(centerRight,BorderLayout.EAST);
	}
	
	/**
	 * Creating the payment buttons $(1-100) along with a cash
	 * button which will display a new frame when clicked on
	 * 
	 * @param subPanel the panel that the payment buttons will be 
	 * set on
	 */
	public void setPaymentButtons(JPanel subPanel) {
		JPanel paymentButtons = new JPanel();
		paymentButtons.setLayout(new GridLayout(8,0,2,8));
		paymentButtons.setPreferredSize(new Dimension(130,this.HEIGHT));
		paymentButtons.setBackground(Color.ORANGE);
		paymentButtons.add(makeCashButton("CASH"));
		paymentButtons.add(makeCashButton("$1"));
		paymentButtons.add(makeCashButton("$5"));
		paymentButtons.add(makeCashButton("$10"));
		paymentButtons.add(makeCashButton("$20"));
		paymentButtons.add(makeCashButton("$50"));
		paymentButtons.add(makeCashButton("$100"));
		subPanel.add(paymentButtons,BorderLayout.WEST);
	}
	
	/**
	 * Setting the borders of the center right of the center panel
	 * for cleaner organization display
	 * @param subPanel the panel where borders are applied
	 */
	public void rightOfsubPanel(JPanel subPanel) {
		JPanel centerPan = new JPanel();
		centerPan.setLayout(new BorderLayout());
		centerPan.setBackground(Color.ORANGE);
		
		centerRightBorders(centerPan);
		extraCashButtons(centerPan);
		subPanel.add(centerPan,BorderLayout.CENTER);
	}
	

	/**
	 * Making the credit card and clear payment buttons
	 * and aligning them to the right of the center right pan
	 * @param centerPan the panel buttons will be placed on
	 */
	public void extraCashButtons(JPanel centerPan) {
		JPanel rightPan = new JPanel();
		rightPan.setPreferredSize(new Dimension(130,this.HEIGHT));
		rightPan.setBackground(Color.ORANGE);
		rightPan.setLayout(new GridLayout(2,0,2,8));
		rightPan.add(makeCashButton("Credit Card"));
		rightPan.add(makeCashButton("Clear Payment"));
		centerPan.add(rightPan,BorderLayout.CENTER);
	}
	
	/**
	 * Making borders for right center panel so everything is aligned
	 * @param centerPan the panel to set borders on
	 */
	public void centerRightBorders(JPanel centerPan) {
		JPanel fillBorder = new JPanel();
		fillBorder.setPreferredSize(new Dimension(this.WIDTH,300));
		fillBorder.setBackground(Color.ORANGE);
		centerPan.add(fillBorder,BorderLayout.SOUTH);
		
		JPanel fillBorder2 = new JPanel();
		fillBorder2.setPreferredSize(new Dimension(50,this.HEIGHT));
		fillBorder2.setBackground(Color.ORANGE);
		centerPan.add(fillBorder2,BorderLayout.WEST);
	}
	
	/**
	 * Making a cash button that has a listener and adjusted
	 * font to make user friendly buttons
	 * @param name name of the button to be made 
	 * @return the button created
	 */
	public JButton makeCashButton(String name) {
		JButton insertMe = new JButton(name);
		insertMe.setFont(new Font("Arial",Font.PLAIN,16));
		insertMe.addActionListener(new PaymentListener());
		return insertMe;	
	}
	
	/**
	 * Setting the order names and prices in the total Area 
	 */
	public void setOrder() {
		totalArea.setText("");
		String me;
		int count = 0;
		for(Order or: thisOrder) {
			me = or.lineOrder(or);
			totalArea.append(me);
			totalArea.append("\n");
		}
	}
	/**
	 * Initializing the data members that will be used for
	 * calculations.  
	 */
	public void setTotal() {
		subTotal = 0.00;
		tax = 0.00;
		Total = 0.00;
		balance = 0.00;
		//calculating price of all items in the order
		for(Order or: thisOrder) {
			subTotal = subTotal+ or.linePrice(or);
		}
		tax = subTotal * 0.095;
		Total = subTotal + tax;
		balance = Total;
	}

	/**
	 * Setting the appropriate data in the total Area
	 * This will go after the list of food items are displayed
	 */
	public void setTotalArea() {
		setTotal();
		totalArea.append("\n");
		totalArea.append("--------------------------------\n");
		String sub = cutOff(String.valueOf(subTotal));
		String taxer = cutOff(String.valueOf(tax));
		String totalStr = cutOff(String.valueOf(Total));
		String balanceDue = cutOff(String.valueOf(balance));
		currentTotal = "Total \t"+totalStr;
		currentBalance = "Balance Due \t"+balanceDue;
		appendTheStringVals(sub,taxer);
	}
	
	/**
	 * Appending the permanent subtotal and tax for the order
	 * @param subtotal the sub total value of order
	 * @param tax the tax value on the whole order
	 */
	public void appendTheStringVals(String subtotal, String tax) {
		totalArea.append("Subtotal \t"+subtotal);
		totalArea.append("\n");
		totalArea.append("Tax \t"+tax);
		totalArea.append("\n");
		totalArea.append("\n");
		
		totalArea.append(currentTotal);
		totalArea.append("\n");
		totalArea.append(currentBalance);
		totalArea.append("\n");
		
	}
	
	/**
	 * PaymentListener that will perform actions based 
	 * on which button is clicked
	 */
	public class PaymentListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String payment = e.getActionCommand();
			switch(payment){
			case "Credit Card":
				break;
			case "Clear Payment":
				setOrder();
				setTotalArea();
				break;
			case "CASH":
				JFrame newPan = new PayPad();
				newPan.setSize(300,300);
				newPan.setVisible(true);
				newPan.pack();
				break;
			case "EXIT":
				System.exit(0);				
				break;
			default:
				payment = payment.substring(payment.indexOf("$")+1)+".00";
				Double amount = Double.valueOf(payment);
				updateTotal(amount);
				if(!checkTotalAboveZero() && !closed) {
					setUpCloseButton();
					closed = true;
				}
				break;
			}
	        }
		}
	
	/**
	 * updating the total displayed in the total Area
	 * @param amount payment amount
	 */
	public void updateTotal(Double amount) {
		balance = balance - amount;
		Total = balance;
		String totalStr = cutOff(String.valueOf(Total));
		String totalBal = totalStr;
		replaceTotalBal(totalStr);
	}
	
	/**
	 * Setting up the close button which will only
	 * come on when total == 0.00
	 */
	public void setUpCloseButton() {
		JButton close = new JButton("Close");
		close.setFont(new Font("Arial",Font.BOLD,22));
		closePan.add(close);
		center.revalidate();
	}
	
	/**
	 * checking whether total is paid for
	 * @return true if total is 0 false if total balance still due
	 */
	public boolean checkTotalAboveZero() {
		return Total == 0.00;
	}
	
	/**
	 * PayPad class that creates the buttons frame that allows
	 * the user to enter a cash payment 
	 */
	public class PayPad extends JFrame{
		String amount = "\t";
		JPanel northPan = new JPanel(new BorderLayout());
		JPanel main = new JPanel();
		JTextArea tableInput = new JTextArea(1,5);
		JLabel enterTable = new JLabel("Payment: ");
		JButton exit = new JButton("EXIT");
		JButton clear = new JButton("Clear");
		JButton enter = new JButton("Enter");
		JPanel keyPad = new JPanel(new GridLayout(5,3));
		
		
		 //Constructor which creates the whole PayPad frame
		PayPad(){
			setLayout(new BorderLayout());
			createComponents();
			wireComponents();
		}
		//Creating our keypad again
		public void createComponents() {
			for(int i = 1; i<=9; i++) {
				keyPad.add(makeButton(String.valueOf(i)));
			}
			//decimal button
			keyPad.add(makeButton("."));
			keyPad.add(makeButton("0"));
			keyPad.add(enter);
			keyPad.add(clear);
			main.add(keyPad,BorderLayout.CENTER);
			main.setBackground(Color.ORANGE);
			
			//creating the north pan with label and field
			northPan.add(enterTable,BorderLayout.NORTH);
			northPan.add(tableInput,BorderLayout.CENTER);
			northPan.setBackground(Color.ORANGE);
			this.add(northPan,BorderLayout.NORTH);
			this.add(main,BorderLayout.CENTER);
		}
		
		/**
		 * Creates a button for the keyPad frame and adjusts
		 * its font and uses an Action Listener
		 * @param name name of the button
		 * @return the button created
		 */
		public JButton makeButton(String name) {
			JButton insertMe = new JButton(name);
			insertMe.setFont(new Font("Times New Roman", Font.BOLD, 24));
			ButtonListener listener = new ButtonListener();
			insertMe.addActionListener(listener);
			return insertMe;
		}
		
		//wiring the clear and enter buttons
		public void wireComponents() {
			enter.addActionListener(new ButtonListener());
			clear.addActionListener(new ButtonListener());
		}
		
		/**
		 * ButtonListener class that will be used for the keyPad frame
		 * Checks what to do based on what the user clicks
		 */
		public class ButtonListener implements ActionListener{	
			public String getTable(String amount) {
				return amount;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "Clear":
					amount = "\t";
					tableInput.setText("");
					tableInput.setText(amount);
					break;
				case "Enter":
					tableInput.setText(amount);
					amount = amount.trim();
					Double paymentAmount = Double.valueOf(amount);
					updateTotal(paymentAmount);
					if(!checkTotalAboveZero()) {
						setUpCloseButton();
					}
					dispose();
					break;
				default:
					amount = amount.trim();
					amount = amount+e.getActionCommand();
					amount = "\t"+amount;
					tableInput.setText(amount);
					break;
				}
			}
		}
	};
	
	/**
	 * Replacing the total and balance displayed in the table Area
	 * where the order summary is 
	 * @param replaceWith string to replace the current total and balance
	 */
	public void replaceTotalBal(String replaceWith) {
		String from = currentTotal;
		currentTotal = "Total \t"+replaceWith;
		if(Double.valueOf(replaceWith) <= 0.00){
			currentTotal = "Total \t"+"0.00";
		}
		int start = totalArea.getText().indexOf(from);
		findAndReplace(start,from,replaceWith);
       
    }
	
	/**
	 * Finding the position to start replacing data from
	 * @param start the position in total Area to begin at
	 * @param from the string that is at that current position
	 * @param replaceWith the string that will take over the previos
	 */
	public void findAndReplace(int start, String from, String replaceWith) {
		if (start >= 0 && from.length() > 0) {
            totalArea.replaceRange(currentTotal, start, start
                + from.length());
        	}
        from = currentBalance;
        currentBalance = "Balance Due \t"+replaceWith;
        start = totalArea.getText().indexOf(from);
        if (start >= 0 && from.length() > 0) {
            totalArea.replaceRange(currentBalance, start, start
                + from.length());
        	}
		
	}
	/**
	 * Making the double fixed to two decimal points
	 * @param name string representation of double
	 * @return cleaned up string ie: (12.50) 
	 */
	public String cutOff(String name) {
		name = name.substring(0, name.indexOf(".")+3);
		return name;
	}
	
	/**
	 * Setting the borders of the center right to look cleaner
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
	 * Creating the north side of the PaymentPanel which will
	 * have a few buttons including an exit button
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
	 * Creating the north buttons 
	 */
	public void createNorthButtons() {
		
		JPanel northLeftButtons = new JPanel();
		northLeftButtons.setBackground(Color.ORANGE);
		northLeftButtons.setPreferredSize(new Dimension(300,60));

		northLeftButtons.setLayout(new BorderLayout());
		northLeftButtons.add(makeButton("Tables"),BorderLayout.CENTER);
		JPanel borderFill = new JPanel();
		borderFill.setPreferredSize(new Dimension(80,north.HEIGHT));
		borderFill.setBackground(Color.ORANGE);
		northLeftButtons.add(borderFill,BorderLayout.EAST);
		
		
		JPanel donePan = new JPanel();
		donePan.setBackground(Color.RED);
		donePan.setLayout(new BorderLayout());
		JButton done = new JButton("EXIT");
		done.addActionListener(new PaymentListener());
		done.setFont(new Font("Arial", Font.BOLD, 22));
		done.add(Box.createRigidArea(new Dimension(5,0)));
		donePan.add(done,BorderLayout.CENTER);
		
		JPanel northRightButtons = new JPanel();
		northRightButtons.setPreferredSize(new Dimension(300,60));
		northRightButtons.setLayout(new BorderLayout());
		northRightButtons.setBackground(Color.ORANGE);
		northRightButtons.add(makeButton("Tips"),BorderLayout.CENTER);
		northRightButtons.add(makeButton("Split Check"),BorderLayout.EAST);


		north.add(donePan,BorderLayout.CENTER);
		north.add(northLeftButtons,BorderLayout.WEST);
		north.add(northRightButtons,BorderLayout.EAST);
	}
	
	/**
	 * creating button for north side of Payment Panel
	 * @param name name of button to be created
	 * @return the button
	 */
	public JButton makeButton(String name) {
		JButton insertMe = new JButton(name);
		insertMe.setFont(new Font("Arial",Font.BOLD,18));
		return insertMe;
	}
	/**
	 * set tip amount when user clicks on tip
	 * @param number tip amount
	 */
	public void tipAmount(Double number) {
		tip = number;
	}
	//getter for tip
	public Double getTipAmount() {
		return tip;
	}
	/**
	 * splitting the check a number of ways
	 * @param number number of ways to split bill
	 * @return the approximate amount due for each person
	 */
	public Double splitCheck(Double number) {
		Total = Total/number;
		String divided = cutOff(String.valueOf(Total));
		currentTotal = "Total \t"+divided;
		Double returnMe = Double.valueOf(divided);
		return returnMe;
	}
	/**
	 * Setting the south panel of the Payment Panel
	 */
	public void setSouth() {
		south = new JPanel();
		south.setLayout(new BorderLayout());
		south.setBackground(Color.ORANGE);
		south.setPreferredSize(new Dimension(this.WIDTH,80));
		
		JPanel southNorth = new JPanel();
		southNorth.setBackground(new Color(242, 161, 0));
		southNorth.setPreferredSize(new Dimension(this.WIDTH,10));
		south.add(southNorth,BorderLayout.NORTH);
		add(south,BorderLayout.SOUTH);
	}
	

	public static void main(String[] args) {
		JFrame nextFrame = new JFrame();
		nextFrame.setTitle("Payment Menu");
		final int FRAME_WIDTH2 = 800;
		final int FRAME_HEIGHT2 = 600;
		nextFrame.setSize(FRAME_WIDTH2,FRAME_HEIGHT2);
		
		ArrayList<Order> orderz = new ArrayList<>();
		Order me1 = new Order();
		Order me2 = new Order();
		me1.populateFoodMap("Coke", 2.55);
		me2.populateFoodMap("Beef & Broccoli", 11.99);
		orderz.add(me1);
		orderz.add(me2);
		
		nextFrame.add(new Payment("Table 3",orderz));
		nextFrame.pack();
		nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nextFrame.setVisible(true);

	}

}
