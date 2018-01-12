import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
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
	private JTextField yearOfBirthTextField;
	
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
		
		yearOfBirthTextField = new JTextField();
		yearOfBirthTextField.setColumns(10);
		
		
		JLabel lblFirstName = new JLabel("first name:");
		
		JLabel lblSecondName = new JLabel("second name:");
		
		JLabel lblBirthplace = new JLabel("birthplace:");
		
		JLabel lblYearOfBirth = new JLabel("year of birth:");
		
		JLabel lblCountry = new JLabel("county:");
		
		JButton btnDo = new JButton("DO");
		btnDo.setBackground(new Color(255, 250, 205));

		btnDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				String firstName = " ";
				String secondName = " ";
				String birthplace = " ";
				String yearOfBirth = " ";
				String country = " ";
				if (firstNameTextField.getText() != null) 
					firstName = firstNameTextField.getText();
				if (secondNameTextField.getText() != null) 
					secondName = secondNameTextField.getText();
				if (birthplaceTextField.getText() != null) 
					birthplace = birthplaceTextField.getText();
				if (countryTextField.getText() != null) 
					country = countryTextField.getText();
				if (yearOfBirthTextField.getText() != null) 
					yearOfBirth = yearOfBirthTextField.getText();
				
				
				
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
			      StringBuilder b = new StringBuilder(sql);
			      b.append("'"); b.append(firstName); b.append("'"); b.append(",");
			      b.append("'"); b.append(secondName); b.append("'"); b.append(",");
			      b.append("'"); b.append(birthplace); b.append("'"); b.append(",");
			      b.append(yearOfBirth); b.append(",");
			      b.append("'"); b.append(country); b.append("'"); b.append(")");
			      sql = b.toString();
			      System.out.println(sql);
			      try {
					stmt.executeUpdate(sql);
					MainFrame.outputTextArea.setText("SUCCESS");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					MainFrame.outputTextArea.setText("ERROR");
				}

			  
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(61, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(73))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCountry)
								.addComponent(lblYearOfBirth)
								.addComponent(lblBirthplace))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(birthplaceTextField)
								.addComponent(countryTextField)
								.addComponent(yearOfBirthTextField)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(firstNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(lblSecondName, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(secondNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(129))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFirstName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(secondNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSecondName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBirthplace)
						.addComponent(birthplaceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblYearOfBirth)
						.addComponent(yearOfBirthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCountry)
						.addComponent(countryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(btnDo)
					.addContainerGap())
		);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/background.jpg"));
		lblNewLabel.setBounds(0, -5, 1054, 617);
		getContentPane().add(lblNewLabel);
		getContentPane().setLayout(groupLayout)
		;
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
