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

public class AddPainting extends JFrame{

	private JTextField titleTextField;
	private JTextField artistTextField;
	private JTextField artstyleTextField;
	private JTextField creationTextField;
	private JTextField acquisitionTextField;
	private JTextField roomTextField;
	/**
	 * Create the application.
	 */
	public AddPainting() {
		
		titleTextField = new JTextField();
		titleTextField.setColumns(10);
		
		artistTextField = new JTextField();
		artistTextField.setColumns(10);
		
		artstyleTextField = new JTextField();
		artstyleTextField.setColumns(10);
		
		creationTextField = new JTextField();
		creationTextField.setColumns(10);
		
		acquisitionTextField = new JTextField();
		acquisitionTextField.setColumns(10);
		
		roomTextField = new JTextField();
		roomTextField.setColumns(10);
		
		JLabel lblTitle = new JLabel("title:");
		
		JLabel lblArtist = new JLabel("artist:");
		
		JLabel lblArtstyle = new JLabel("artstyle:");
		
		JLabel lblYearOfCreation = new JLabel("year of creation:");
		
		JLabel lblYearOf = new JLabel("year of acquisition:");
		
		JLabel lblRoom = new JLabel("room:");
		
		JButton btnDo = new JButton("DO");
		btnDo.setBackground(new Color(255, 250, 205));
		btnDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				String title = " ";
				String artist = " ";
				String artstyle = " ";
				String yearOfCreation = " ";
				String yearOf = " ";
				String room = " ";
				if (titleTextField.getText() != null) 
					title = titleTextField.getText();
				if (artistTextField.getText() != null) 
					artist = artistTextField.getText();
				if (artstyleTextField.getText() != null) 
					artstyle = artstyleTextField.getText();
				if (creationTextField.getText() != null) 
					yearOfCreation = creationTextField.getText();
				if (acquisitionTextField.getText() != null) 
					yearOf = acquisitionTextField.getText();
				if (roomTextField.getText() != null) 
					room = roomTextField.getText();
				
				
				//STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      try {
					stmt = HomeFrame.conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      String sql;
			      sql = "INSERT INTO Gallery_Paintings (title, artist, artstyle, year_of_creation, year_of_acquisition, room)"
			      		+ " VALUES " + "(";
			      StringBuilder b = new StringBuilder(sql);
			      b.append("'"); b.append(title); b.append("'"); b.append(",");
			      b.append(artist); b.append(",");
			      b.append("'"); b.append(artstyle); b.append("'"); b.append(",");
			      b.append(yearOfCreation); b.append(",");
			      b.append(yearOf); b.append(",");
			      b.append(room); b.append(")");
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
					.addGap(55)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblArtist)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(artistTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblArtstyle)
							.addGap(17)
							.addComponent(artstyleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblYearOfCreation)
							.addGap(18)
							.addComponent(creationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblYearOf)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(acquisitionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(btnDo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblRoom)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(roomTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTitle)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(artistTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblArtist))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(artstyleTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblArtstyle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(creationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYearOfCreation))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(acquisitionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYearOf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(roomTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRoom))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnDo)
					.addContainerGap())
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
		setBounds(100, 100, 450, 300);
		setVisible(true);
		setResizable(false);
	}

}
