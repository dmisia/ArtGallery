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

public class MovePainting extends JFrame{

	private JTextField titleTextField;
	private JTextField destinationTextField;
	/**
	 * Create the application.
	 */
	public MovePainting() {
		
		titleTextField = new JTextField();
		titleTextField.setColumns(10);
		
		JLabel lblTitle = new JLabel("title:");
		
		JButton btnDo = new JButton("DO");
		btnDo.setBackground(new Color(255, 250, 205));
		btnDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				String title = " ";
				String roomID = " ";
				if (titleTextField.getText() != null) 
					title = titleTextField.getText();
				if (destinationTextField.getText() != null) 
					roomID = destinationTextField.getText();
				
				//STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      try {
					stmt = HomeFrame.conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      String sql;
			      sql = "UPDATE Gallery_Paintings "
			      		+ "SET room = ";
			      StringBuilder b = new StringBuilder(sql);
			      b.append(roomID);
			      b.append(" WHERE title = ");
			      b.append("'"); b.append(title); b.append("'");
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
		
		destinationTextField = new JTextField();
		destinationTextField.setColumns(10);
		
		JLabel lblDestination = new JLabel("destination room ID:");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTitle)
									.addGap(38)
									.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDestination, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(destinationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestination)
						.addComponent(destinationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
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
