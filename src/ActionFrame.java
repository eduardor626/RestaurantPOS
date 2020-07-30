import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * 
 * @author eduardorocha
 * 
 * BUGS: None yet
 * 
 * ActionFrame is basically the user's home window where all 
 * actions can be performed. Table icons can be added to ActionFrame
 * using the NewTable pop up window. If the table icon is clicked
 * then open up the Menu Frame!!
 *
 */
public class ActionFrame extends JPanel{
	
	//All the panels in our action frame
	JPanel exitPan;
	JPanel north;
	JPanel south;
	JPanel tablesPan;
	JPanel tablesView;
	//Employee's action frame
	Employee e;

	//Exit button to kill window
	JButton exit;
	
	/**
	 * Constructor which takes in an employee to welcome and store
	 * @param E employee whose action frame this is
	 */
	ActionFrame(Employee E){
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.ORANGE);
		createComponents(E);
		wireComponents();
	}
	
	/**
	 * Constructor that takes in an employee and table object
	 * @param E employee
	 * @param T table
	 */
	ActionFrame(Employee E,Table T){
		
	}
	
	/**
	 * Creating the components of Action Frame
	 * @param E employee to be welcomed
	 */
	public void createComponents(Employee E) {
		setNorth(E);
		setSouth();
		setTablesPan();
	}
	/**
	 * Create the tables Panel in center with side borders
	 */
	public void setTablesPan() {
		tablesView = new JPanel();
		tablesView.setLayout(new BorderLayout());
		tablesPan = new JPanel();
		tablesPan.setLayout(new GridLayout(5,3,10,10));
		tablesPan.setPreferredSize(new Dimension(100,200));
		tablesPan.setBackground(new Color(242, 161, 0));
		setLeftRightBorder();
	}
	//side borders for the tables Panel section
	public void setLeftRightBorder() {
		JPanel leftOf = new JPanel();
		leftOf.setBackground(new Color(242, 161, 0));
		leftOf.setPreferredSize(new Dimension(50,this.getHeight()));
		
		JPanel rightOf = new JPanel();
		rightOf.setBackground(new Color(242, 161, 0));
		rightOf.setPreferredSize(new Dimension(50,this.getHeight()));
		
		tablesView.add(tablesPan,BorderLayout.CENTER);
		add(tablesView,BorderLayout.CENTER);
		add(leftOf,BorderLayout.WEST);
		add(rightOf,BorderLayout.EAST);
	}
	
	
	//wiring the exit button with button listener
	public void wireComponents() {
		exit.addActionListener(new ButtonListener());
	}
	
	//Creating the south Panel with options for the user
	public void setSouth() {
		JPanel buttonOptions = new JPanel();
		buttonOptions.setPreferredSize(new Dimension(this.WIDTH,80));
		buttonOptions.setBackground(Color.orange);
		buttonOptions.setLayout(new GridLayout(1,5));
		buttonOptions.add(makeButton("New Table"));
		buttonOptions.add(makeButton("Tips"));
		buttonOptions.add(makeButton("Sales"));
		buttonOptions.add(makeButton("Break"));
		buttonOptions.add(makeButton("Clock Out"));

		add(buttonOptions,BorderLayout.SOUTH);
	}
	
	/**
	 * method that creates a button and attaches the table picture 
	 * to it. Need to figure out how to make button just a table picture
	 * icon
	 * @param name name of table to be created 
	 * @return the button with table name and picture along with listener
	 * to open up a new Order Menu
	 */
	public JButton makeButtonPic(String name) {
	   ImageIcon icon = new ImageIcon("src/table.png");
	   Image img = icon.getImage() ;  
	   Image newimg = img.getScaledInstance( 20, 20, Image.SCALE_SMOOTH ) ;  
	   icon = new ImageIcon( newimg );
	   JButton insertMe = new JButton(name, icon);
	   insertMe.setPreferredSize(new Dimension(100,20));
	   insertMe.setPressedIcon(icon);
	   insertMe.addActionListener(new TableListener());
	   return insertMe;
	}
	
	/**
	 * TableListener class to open Order Menu when a table is clicked
	 */
	public class TableListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame orderMenu = new JFrame();
			orderMenu.setTitle("Order Menu");
			final int FRAME_WIDTH2 = 800;
			final int FRAME_HEIGHT2 = 700;
			String name = e.getActionCommand();
			orderMenu.setPreferredSize(new Dimension(FRAME_WIDTH2,FRAME_HEIGHT2));
			orderMenu.add(new OrderMenu(name));
			orderMenu.pack();
			orderMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			orderMenu.setVisible(true);
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(tablesPan);
			frame.dispose();
		}
	}
	
	/**
	 * Creating buttons
	 * @param name name of button
	 * @return the button with a certain action to it
	 */
	public JButton makeButton(String name) {
		JButton insertMe = new JButton(name);
		insertMe.setFont(new Font("Times New Roman", Font.BOLD, 16));
		ButtonListener listener = new ButtonListener();
		insertMe.addActionListener(listener);
		return insertMe;
	}

	/**
	 * Setting the North Panel with welcome message on North Left
	 * @param E employee that is going to be greeted
	 */
	public void setNorth(Employee E) {
		exitPan = new JPanel();
		exitPan.setBackground(Color.ORANGE);
		north = new JPanel();
		north.setBackground(Color.ORANGE);
		north.setPreferredSize(new Dimension(this.getWidth(),50));
		north.setLayout(new BorderLayout());
		exit = new JButton("Exit");
		exit.setFont(new Font("Lucida Grande",Font.PLAIN,20));
		exit.setForeground(Color.RED);
		exitPan.add(exit);
		north.add(exitPan,BorderLayout.EAST);
		add(north,BorderLayout.NORTH);
		setNorthLeft(E);

	}
	
	/**
	 * Creating welcome message on North Left Panel
	 * @param E employee to be welcomed
	 */
	public void setNorthLeft(Employee E) {
		String employeeName = E.getName();
		JLabel welcomeEmployee = new JLabel("Welcome, "+ employeeName+"!");
		welcomeEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		welcomeEmployee.setForeground(Color.BLACK);
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BorderLayout());
		leftSide.setBackground(Color.ORANGE);
		leftSide.add(welcomeEmployee,BorderLayout.WEST);
		north.add(leftSide,BorderLayout.WEST);
	}
	
	/**
	 * ButtonListener class that performs certain actions
	 * when buttons in Action Frame are clicked	 
	 */
	public class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case"Exit":
				System.exit(0);				
				break;
			case"New Table":
				JFrame newPan = new TableWindow();
				newPan.setSize(300,300);
				newPan.setVisible(true);
				newPan.pack();
				break;
			case"Tips":
				break;
			case"Sales":
				break;
			case"Break":
				break;
			case "Clock Out":
				break;
			}
		}
	}
	
	/**
	 * Creating the TableWindow where user inputs there
	 * new table number and a new icon is created in Action Frame
	 * in the center
	 */
	public class TableWindow extends JFrame{
		
		String tableNumber = "\t";
		JPanel northPan = new JPanel(new BorderLayout());
		JPanel main = new JPanel();
		JTextArea tableInput = new JTextArea(1,5);
		JLabel enterTable = new JLabel("Enter Table Number");
		JButton exit = new JButton("EXIT");
		JButton clear = new JButton("Clear");
		JButton enter = new JButton("Enter");
		JPanel keyPad = new JPanel(new GridLayout(4,3));
		
		//Table Window Constructor
		TableWindow(){
			setLayout(new BorderLayout());
			createComponents();
			wireComponents();
			
		}
		//Creating our keypad 
		public void createComponents() {
			for(int i = 1; i<=9; i++) {
				keyPad.add(makeButton(String.valueOf(i)));
			}
			keyPad.add(clear);
			keyPad.add(makeButton("0"));
			keyPad.add(enter);
			main.add(keyPad,BorderLayout.CENTER);
			main.setBackground(Color.ORANGE);
			
			
			//creating the north pan with label and field
			northPan.add(enterTable,BorderLayout.NORTH);
			northPan.add(tableInput,BorderLayout.CENTER);
			northPan.setBackground(Color.ORANGE);
			add(northPan,BorderLayout.NORTH);
			add(main,BorderLayout.CENTER);
		}
		
		/**
		 * Listener buttons for key pad
		 * @param name the name of the keypad buttons
		 * @return the button with tableListener action
		 */
		public JButton makeButton(String name) {
			JButton insertMe = new JButton(name);
			insertMe.setFont(new Font("Times New Roman", Font.BOLD, 24));
			AddTableListener listener = new AddTableListener();
			insertMe.addActionListener(listener);
			return insertMe;
		}
		
		//wiring the enter and clear components
		public void wireComponents() {
			enter.addActionListener(new AddTableListener());
			clear.addActionListener(new AddTableListener());
		}
		
		/**
		 * AddTableListener class which creates a table in ActionFrame
		 * once a valid table number/name is input		 
		 */
		public class AddTableListener implements ActionListener{
			public String getTable(String tableNumber) {
				return tableNumber;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "Clear":
					tableNumber = "\t";
					tableInput.setText("");
					tableInput.setText(tableNumber);
					break;
				case "Enter":
					tableInput.setText(tableNumber);
					tableNumber = tableNumber.trim();
					tablesPan.add(makeButtonPic("Table "+tableNumber));
					tablesView.add(tablesPan,BorderLayout.CENTER);
					tablesView.revalidate(); 
					dispose();	
					break;
				default:
					tableNumber = tableNumber+e.getActionCommand();
					tableInput.setText(tableNumber);
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Employee person = new Employee("Eduardo Rocha","00961652","Server");
		JFrame nextFrame = new JFrame();
		nextFrame.setTitle("User Home");
		final int FRAME_WIDTH2 = 600;
		final int FRAME_HEIGHT2 = 600;
		nextFrame.setSize(FRAME_WIDTH2,FRAME_HEIGHT2);
		nextFrame.add(new ActionFrame(person));
		
		
		
		nextFrame.pack();
		nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nextFrame.setVisible(true);
	}

}
