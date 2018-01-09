import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AddArtist extends JFrame{

	private JTextField firstNameTextField;
	private JTextField secondNameTextField;
	private JTextField birthplaceTextField;
	private JTextField countryTextField;
	
	/**
	 * Create the application.
	 */
	public AddArtist() {
		
		firstNameTextField = new JTextField();
		firstNameTextField.setColumns(10);
		
		secondNameTextField = new JTextField();
		secondNameTextField.setColumns(10);
		
		birthplaceTextField = new JTextField();
		birthplaceTextField.setColumns(10);
		
		countryTextField = new JTextField();
		countryTextField.setColumns(10);
		
		
		JLabel lblFirstName = new JLabel("first name:");
		
		JLabel lblSecondName = new JLabel("second name:");
		
		JLabel lblBirthplace = new JLabel("birthplace:");
		
		JLabel lblCountry = new JLabel("county:");
		
		JButton btnDo = new JButton("DO");
		btnDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				String firstName;
				String secondName;
				String birthplace;
				String country;
				if (firstNameTextField.getText() != null) 
					firstName = firstNameTextField.getText();
				
				
				//STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      try {
					stmt = HomeFrame.conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      String sql;
			      sql = "INSERT INTO Artists (first_name, second_name, birthplace, year_of_birth, country)"
			      		+ " VALUES " + "(";
			      try {
					ResultSet rs = stmt.executeQuery(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			  
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFirstName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(firstNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSecondName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(secondNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBirthplace)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(birthplaceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCountry)
							.addGap(18)
							.addComponent(countryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(73)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFirstName)
						.addComponent(firstNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(secondNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSecondName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(birthplaceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBirthplace))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(countryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCountry))
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addComponent(btnDo)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setResizable(false);
	}

}
