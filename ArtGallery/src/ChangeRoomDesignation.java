import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ChangeRoomDesignation extends JFrame{

	private JTextField roomIDTextField;
	private JTextField designationTextField;
	/**
	 * Create the application.
	 */
	public ChangeRoomDesignation() {
		
		roomIDTextField = new JTextField();
		roomIDTextField.setColumns(10);
		
		JLabel lblroomID = new JLabel("room ID:");
		
		JButton btnDo = new JButton("DO");
		btnDo.setBackground(new Color(255, 250, 205));
		btnDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				String roomID = " ";
				String designation = " ";
				if (roomIDTextField.getText() != null) 
					roomID = roomIDTextField.getText();
				if (designationTextField.getText() != null) 
					designation = designationTextField.getText();
				
				//STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      try {
					stmt = HomeFrame.conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      String sql;
			      sql = "UPDATE Rooms "
			      		+ "SET designation = ";
			      StringBuilder b = new StringBuilder(sql);
			      b.append("'"); b.append(designation); b.append("'");
			      b.append(" WHERE ID = ");
			      b.append(roomID);
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
		
		designationTextField = new JTextField();
		designationTextField.setColumns(10);
		
		JLabel lblDesignation = new JLabel("new designation:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblroomID)
									.addGap(18)
									.addComponent(roomIDTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDesignation)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(designationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(roomIDTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblroomID))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(designationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDesignation))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
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
		setBounds(100, 100, 366, 232);
		setVisible(true);
		setResizable(false);
	}

}
