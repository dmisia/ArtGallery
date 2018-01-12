import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class HomeFrame {
	
	// JDBC driver name and database URL
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://localhost/Art_Gallery";

	//  Database credentials
	public static final String ADMINUSER = "admin";
	public static final String ADMINPASS = "1234";
	public static final String EUSER = "employee";
	public static final String EPASS = "1234";
	public static final String EPLUSUSER = "employee+";
	public static final String EPLUSPASS = "1234";
	
	public static Connection conn = null;

	public JComboBox<String> comboBox;
	public String[] user = new String[] {"Employee","Employee+","Admin"};
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame window = new HomeFrame();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox(user);
		comboBox.setBackground(new Color(255, 250, 205));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox.setBounds(47, 114, 236, 70);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("LOG IN");
		btnNewButton.setBackground(new Color(255, 250, 205));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			   try {
				   //STEP 2: Register JDBC driver
				  Class.forName("com.mysql.jdbc.Driver");
				
				  System.out.println("Connecting to the database...");
				  
			     //STEP 3: Open a connection
				  if (comboBox.getSelectedIndex() == 0) {
					  conn = DriverManager.getConnection(DB_URL, EUSER, EPASS);
					  System.out.println("Connected as user: " + EUSER);
				  }
				  else if (comboBox.getSelectedIndex() == 1) {
					  conn = DriverManager.getConnection(DB_URL, EPLUSUSER, EPASS);
					  System.out.println("Connected as user: " + EPLUSUSER);
				  }
				  else if (comboBox.getSelectedIndex() == 2) {
					  conn = DriverManager.getConnection(DB_URL, ADMINUSER, ADMINPASS);
					  System.out.println("Connected as user: " + ADMINUSER);
				  }
			     
				} catch (SQLException e) {
					e.printStackTrace();
				}
				catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				MainFrame game = new MainFrame();
	            game.setVisible(true);
	            game.setLocation(100,100);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(347, 114, 236, 70);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("                          Art Gallery");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblNewLabel.setBounds(0, 0, 631, 78);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBackground = new JLabel("");
		lblBackground .setIcon(new ImageIcon("src/background.jpg"));
		lblBackground .setBounds(0, -5, 1054, 617);
		frame.getContentPane().add(lblBackground );
	
	}
}
