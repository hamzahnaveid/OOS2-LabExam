import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/* C22428264 - Hamzah Naveid */
public class OrderManagement extends JFrame implements ActionListener {
	
	ArrayList<Item> itemList = new ArrayList<Item>();
	String[] ingredients = {"Tomato Sauce", "Cheese", "Pineapple", "Olives", "Mushrooms"};
	String[] meats = {"Chicken", "Salami", "Pepperoni", "Meatballs"};
	JLabel welcome;
	JTextArea orderDetails;
	JList<String> ingredientsList;
	JList<String> meatsList;
	
	public OrderManagement() {
		super("Order Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel welcomePane = new JPanel();
		JPanel ordersPane = new JPanel();
		
		welcome = new JLabel("Welcome");
		orderDetails = new JTextArea();
		
		welcomePane.add(welcome);
		ordersPane.add(orderDetails);
		
		add(welcomePane, BorderLayout.NORTH);
		add(orderDetails, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu user = new JMenu("User");
		JMenuItem logIn = new JMenuItem("Log in");
		logIn.setMnemonic(KeyEvent.VK_L);
		logIn.addActionListener(this);
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.setMnemonic(KeyEvent.VK_Q);
		quit.addActionListener(this);
		
		user.add(logIn);
		user.add(quit);
		
		JMenu order = new JMenu("Order");
		JMenuItem pizza = new JMenuItem("Pizza");
		pizza.setMnemonic(KeyEvent.VK_P);
		pizza.addActionListener(this);
		
		JMenuItem drink = new JMenuItem("Drink");
		drink.setMnemonic(KeyEvent.VK_D);
		drink.addActionListener(this);
		
		order.add(pizza);
		order.add(drink);
		
		JMenu checkout = new JMenu("Check Out");
		JMenuItem total = new JMenuItem("Total");
		total.addActionListener(this);
		checkout.add(total);
		
		menuBar.add(user);
		menuBar.add(order);
		menuBar.add(checkout);
		
		setJMenuBar(menuBar);
		
	}

	public static void main(String[] args) {
		OrderManagement win = new OrderManagement();
		win.setSize(800,600);
		win.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Quit")) {
			dispose();
		}
		else if(e.getActionCommand().equals("Log in")) {
			JTextField userField = new JTextField();
			JPasswordField passField = new JPasswordField();
			String message = "Please enter your user name and password.";
			int result = JOptionPane.showOptionDialog(this,
			new Object[] { message, userField, passField },
			"Login", JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null, null, null);
			
			if(result == JOptionPane.OK_OPTION) {
				welcome.setText("Welcome " + userField.getText());
			}

		}
		else if(e.getActionCommand().equals("Pizza")) {
			JDialog pizzaOrder = new JDialog(this, "Pizza Order");
			pizzaOrder.setLayout(new GridLayout(0,1));
			pizzaOrder.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			pizzaOrder.setSize(400,300);
			pizzaOrder.setVisible(true);
			
			pizzaOrder.add(new JLabel("Select one or multiple ingredients:"));
			
			ingredientsList = new JList<>(ingredients);
			ingredientsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane ingredientScrl = new JScrollPane(ingredientsList);
			pizzaOrder.add(ingredientScrl);
			
			pizzaOrder.add(new JLabel("Select one meat:"));
			
			meatsList = new JList<>(meats);
			meatsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane meatScrl = new JScrollPane(meatsList);
			pizzaOrder.add(meatScrl);
			
			JButton order = new JButton("Order");
			order.addActionListener(this);
			pizzaOrder.add(order);		
		}		
		else if(e.getActionCommand().equals("Drink")) {
			String input = JOptionPane.showInputDialog(this, "Input the number of drinks.");
			int quantity = Integer.parseInt(input);
			
			Item drink = new Item("Drink", quantity*3);
			
			itemList.add(drink);
			displayList();
			
		}
		else if(e.getActionCommand().equals("Total")) {
			double total = calculateTotal();
			JOptionPane.showMessageDialog(this, "Total order is " + total, "Total", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand().equals("Order")) {
			Item ingredient = new Item(ingredients[ingredientsList.getSelectedIndex()], 1.5);
			Item meat = new Item(meats[meatsList.getSelectedIndex()], 2);
			
			itemList.add(ingredient);
			itemList.add(meat);
			
			displayList();
		}
	}
	
	public void displayList() {
		String display = "";
		for(int i = 0; i < itemList.size(); i++) {
			display += itemList.get(i).toString() + "\n";
		}
		orderDetails.setText(display);
	}
	
	public double calculateTotal() {
		double total = 0.0;
		
		for(int i = 0; i < itemList.size(); i++) {
			total += itemList.get(i).getPrice();
		}
		return total;
	}

}
