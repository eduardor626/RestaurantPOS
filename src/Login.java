import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author eduardorocha
 * BUGS: None in this class
 * Program Report: This is the opening panel of the whole program.
 *
 * Login interface that allows employee to enter their user
 * identification number and if valid closes Login and opens the 
 * user's Action Menu. From the Action Menu the whole program caters to
 * whichever employee logged in
 */
public class Login extends JPanel{
	
	//current employee logging in and list of employees
	Employee person;
	private ArrayList<Employee> employees;
	private String employeeID;
	
	//Setting the different panels of the main panel
	private JPanel north;
	private JPanel center;
	private JPanel left;
	private JPanel right;
	private JPanel south;
	
	private JPanel keyPad;
	
	//Buttons that need to be diff color
	private JButton enter;
	private JButton clear;
	
	private JTextArea asteriskArea;
	private JLabel welcome;
	
	//filename to read employees from
	static String filename = "src/input.txt";
	static File openMe = new File(filename);
	
	/**
	 * Constructing the Login panel and components of panel
	 * will call the Login constructor that takes in the file name
	 */
	Login(){
		this(openMe);
		setLayout(new BorderLayout());
		setBackground(Color.orange);
		wireComponents();
		createComponents();
	}
	
	/**
	 * Constructing our list of employees for the login panel
	 * @param employeeList file that contains information needed
	 */
	Login(File employeeList){
		employees = new ArrayList<>();
		try {
			String name;
			String id; 
			String position;
			Scanner in = new Scanner(employeeList);
			while(in.hasNextLine()) {
				String line = in.nextLine();
				name = line.substring(0,line.indexOf("/"));
				line = line.substring(line.indexOf("/")+1);
				id = line.substring(0,line.indexOf("/"));
				position = line.substring(line.indexOf("/")+1);
				Employee current = new Employee(name,id,position);
				employees.add(current);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Creating components of the panel
	 */
	public void createComponents() {
		setKeyPad();
		setTextArea();
		setLeft();
		setRight();
		setSouth();
	}
	/**
	 * wiring the enter and clear buttons
	 */
	public void wireComponents() {
		enter = new JButton("Enter");
		enter.setForeground(Color.GREEN);
		enter.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		clear = new JButton("Clear");
		clear.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		clear.setForeground(Color.RED);
		enter.addActionListener(new ButtonListener());
		clear.addActionListener(new ButtonListener());
	}
	
	/**
	 * Setting the text Area where user ID is displayed upon 
	 * button being clicked
	 */
	public void setTextArea() {
		employeeID = "";
		north = new JPanel();
		north.setBackground(Color.ORANGE);

		north.setPreferredSize(new Dimension(this.WIDTH,70));
		north.setLayout(new BorderLayout());
		
		JPanel asteriskPan = new JPanel();
		asteriskPan.setBackground(Color.ORANGE);
		JPanel welcomePan = new JPanel();
		welcomePan.setBackground(Color.ORANGE);

		asteriskArea = new JTextArea(1,10);
		asteriskArea.setEditable(false);
		asteriskArea.setBackground(new Color(242, 161, 0));
		asteriskPan.add(asteriskArea);

		north.add(asteriskPan,BorderLayout.SOUTH);
		
		Font font = new Font("Times New Roman", Font.BOLD,20);
		welcome = new JLabel("Enter Employee Number");
		welcome.setFont(font);
		welcomePan.add(welcome);
		north.add(welcomePan,BorderLayout.CENTER);
		
		add(north,BorderLayout.NORTH);
	}
	
	//Setting the south border
	public void setSouth() {
		south = new JPanel();
		south.setBackground(Color.ORANGE);
		south.setPreferredSize(new Dimension(this.WIDTH,70));
		add(south,BorderLayout.SOUTH);
	}
	
	//left border
	public void setLeft() {
		left = new JPanel();
		left.setBackground(Color.ORANGE);
		left.setPreferredSize(new Dimension(70,this.HEIGHT));
		add(left,BorderLayout.WEST);
	}
	//right border
	public void setRight() {
		right = new JPanel();
		right.setBackground(Color.ORANGE);
		right.setPreferredSize(new Dimension(70,this.HEIGHT));
		add(right,BorderLayout.EAST);
	}
	/**
	 * Creating the keypad buttons and panel for user input
	 */
	public void setKeyPad() {
		center = new JPanel();
		center.setLayout(new BorderLayout());
		center.setPreferredSize(new Dimension(400,600));
		keyPad = new JPanel();
		keyPad.setLayout(new GridLayout(4,3));
		for(int i = 1; i<=9; i++) {
			keyPad.add(makeButton(String.valueOf(i)));
		}
		keyPad.add(clear);
		keyPad.add(makeButton("0"));
		keyPad.add(enter);
		center.add(keyPad,BorderLayout.CENTER);
		add(center,BorderLayout.CENTER);
	}
	
	/**
	 * Making the keypad button for user login
	 * @param name of the button which will be integer
	 * @return the button with listener for it
	 */
	public JButton makeButton(String name) {
		JButton insertMe = new JButton(name);
		insertMe.setFont(new Font("Times New Roman", Font.BOLD, 28));
		ButtonListener listener = new ButtonListener();
		insertMe.addActionListener(listener);
		return insertMe;
	}
	
	/**
	 * ButtonListener class that performs an action when 
	 * certain buttons are clicked	 
	 */
	public class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "Clear":
				employeeID = "";
				asteriskArea.setText("");
				break;
			case "Enter":
				person = isAnEmployee(asteriskArea.getText());
				if(person != null) {
					JFrame nextFrame = new JFrame();
					nextFrame.setTitle("User Home");
					nextFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					final int FRAME_WIDTH2 = 600;
					final int FRAME_HEIGHT2 = 600;
					nextFrame.setSize(FRAME_WIDTH2,FRAME_HEIGHT2);
					nextFrame.setVisible(true);
					nextFrame.add(new ActionFrame(person));
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(center);
					frame.dispose();
				}
				break;
			default:
				if(!(employeeID.length() == 15)) {
				employeeID+=e.getActionCommand();
				asteriskArea.setText(employeeID);
				}
			}	
		}	
	}
	

	/**
	 * prints the list of employees from file read. 
	 */
	public void printList() {
		for(Employee el: employees) {
			System.out.println(el.toString());
		}
	}
	/**
	 * Checks whether the user input is associated with an employee
	 * @param ID to check
	 * @return the employee associated with ID or null if not foudn
	 */
	public Employee isAnEmployee(String ID) {
		for(Employee el: employees) {
			if(el.getID().equals(ID)) {
				return el;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		JFrame loginFrame = new JFrame();
		loginFrame.setTitle("USER LOGIN");
		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 600;
		loginFrame.setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

		

		loginFrame.add(new Login());
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
