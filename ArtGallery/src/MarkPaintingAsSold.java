import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MarkPaintingAsSold extends JFrame{

	private JTextField titleTextField;
	private JTextField buyerIDTextField;
	private JTextField dateTextField;
	/**
	 * Create the application.
	 */
	public MarkPaintingAsSold() {
		
		titleTextField = new JTextField();
		titleTextField.setColumns(10);
		
		JLabel lblTitle = new JLabel("title:");
		
		JButton btnDo = new JButton("DO");
		btnDo.setBackground(new Color(255, 250, 205));
		btnDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				String title = " ";
				String buyerID = " ";
				String date = " ";
				
				if (titleTextField.getText() != null) 
					title = titleTextField.getText();
				if (buyerIDTextField.getText() != null) 
					buyerID = buyerIDTextField.getText();
				if (dateTextField.getText() != null) 
					date = dateTextField.getText();
				
				
				//STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      try {
					stmt = HomeFrame.conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      String artist = " ";
			      String artstyle = " ";
			      String yearOfCreation = " ";
			      String yearOfAcquisition = " ";
			      String sql;
			      ResultSet rs;
			      sql = "SELECT artist FROM Gallery_Paintings WHERE title = " + "'" + title + "'"; 
			      try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
				        artist = rs.getString("artist");
				      }
				      rs.close();
				} catch (SQLException e1) {
					System.out.println("Exception in artist");
				}
			     
			      sql = "SELECT artstyle FROM Gallery_Paintings WHERE title = " + "'" + title + "'";
			      try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
				        artstyle = rs.getString("artstyle");
				      }
				      rs.close();
				} catch (SQLException e2) {
					System.out.println("Exception in artstyle");
				}
			      
			      sql = "SELECT year_of_creation FROM Gallery_Paintings WHERE title = " + "'" + title + "'";
			      try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
				        yearOfCreation = rs.getString("year_of_creation");
				      }
				      rs.close();
				} catch (SQLException e3) {
					System.out.println("Exception in YoC");
				}
			      
			      
			      sql = "SELECT year_of_acquisition FROM Gallery_Paintings WHERE title = " + "'" + title + "'"; 
			      try {
					rs = stmt.executeQuery(sql);
					while(rs.next()){
				        yearOfAcquisition = rs.getString("year_of_acquisition");
				      }
				      rs.close();
				} catch (SQLException e4) {
					System.out.println("Exception in YoA");
				}
			      
			      
			      
			      
			      sql = "INSERT INTO Sold_Paintings (title, artist, artstyle,"
			      		+ " year_of_creation, year_of_acquisition, buyer, selling_date)"
			      		+ " VALUES " + "(";
			      StringBuilder b = new StringBuilder(sql);
			      b.append("'"); b.append(title); b.append("'"); b.append(",");
			      b.append(artist); b.append(",");
			      b.append("'"); b.append(artstyle); b.append("'"); b.append(",");
			      b.append(yearOfCreation); b.append(",");
			      b.append(yearOfAcquisition); b.append(",");
			      b.append(buyerID); b.append(",");
			      b.append("'"); b.append(date); b.append("'"); b.append(")");
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
		
		
		buyerIDTextField = new JTextField();
		buyerIDTextField.setColumns(10);
		
		dateTextField = new JTextField();
		dateTextField.setColumns(10);
		
		JLabel lblBuyerID = new JLabel("buyer ID:");
		
		JLabel lblDate = new JLabel("date:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(111)
							.addComponent(btnDo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTitle)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBuyerID, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(buyerIDTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(71))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buyerIDTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBuyerID))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(btnDo)
					.addGap(31))
		);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/background.jpg"));
		lblNewLabel.setBounds(0, -5, 1054, 617);
		getContentPane().add(lblNewLabel);
		getContentPane().setLayout(groupLayout);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 366, 244);
		setVisible(true);
		setResizable(false);
	}

}
