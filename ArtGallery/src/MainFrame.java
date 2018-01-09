//import java.util.*;
import javax.swing.JPanel;

//import java.util.concurrent.locks.*;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private JPanel panel;
	Statement stmt = null;
	JTextArea inputTextArea;
	public static JTextArea outputTextArea;


	
	
	
	public void initialize(){
			 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1066, 668);
		setTitle("Art Gallery");
		panel = new JPanel();
		panel.setForeground(new Color(221, 160, 221));
		panel.setBorder(null);
		setContentPane(panel);
		panel.setLayout(null);
		
		JButton doSelectButton = new JButton("DO");
		doSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 System.out.println("Creating statement...");
			      try {
					stmt = HomeFrame.conn.createStatement();
					ResultSet rs;
					ResultSetMetaData rsmd;
					
					String data = " ";
					if (inputTextArea.getText() != null) 
						data = inputTextArea.getText();
				
			      String sql = "SELECT ";
			      StringBuilder b = new StringBuilder(sql);
			      b.append(data);
			      sql = b.toString();
			      rs = stmt.executeQuery(sql);
			      rsmd = rs.getMetaData();
			      int columnsNumber = rsmd.getColumnCount();
			      MainFrame.outputTextArea.setText("");
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			    	  for(int i = 1 ; i <= columnsNumber; i++){

			    	      outputTextArea.append(rs.getString(i) + " "); //Print one element of a row

			    	}

			    	  outputTextArea.append(System.getProperty("line.separator"));//Move to the next line to print the next row.           

			      }
			      rs.close();
			      } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		doSelectButton.setBounds(840, 28, 127, 57);
		panel.add(doSelectButton);
		
		JLabel selectLabel = new JLabel("SELECT:");
		selectLabel.setBounds(35, 43, 90, 27);
		panel.add(selectLabel);
		
		inputTextArea = new JTextArea();
		inputTextArea.setBounds(134, 28, 662, 57);
		panel.add(inputTextArea);
		
		outputTextArea = new JTextArea();
		outputTextArea.setBounds(45, 101, 751, 117);
		panel.add(outputTextArea);
		
		JLabel label = new JLabel("_________________________________________________________________________________________________________________________________________________________________________");
		label.setBounds(0, 234, 1182, 20);
		panel.add(label);
		
		JLabel lblManagePaintings = new JLabel("MANAGE PAINTINGS");
		lblManagePaintings.setBounds(65, 270, 162, 36);
		panel.add(lblManagePaintings);
		
		JLabel lblManageArtists = new JLabel("MANAGE ARTISTS");
		lblManageArtists.setBounds(321, 270, 162, 36);
		panel.add(lblManageArtists);
		
		JLabel lblManageRooms = new JLabel("MANAGE ROOMS");
		lblManageRooms.setBounds(835, 270, 162, 36);
		panel.add(lblManageRooms);
		
		JLabel lblManageBuyers = new JLabel("MANAGE BUYERS");
		lblManageBuyers.setBounds(578, 270, 162, 36);
		panel.add(lblManageBuyers);
		
		JButton btnAddBuyers = new JButton("ADD");
		btnAddBuyers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBuyer add = new AddBuyer();
			}
		});
		btnAddBuyers.setBounds(588, 322, 115, 29);
		panel.add(btnAddBuyers);
		
		JButton btnAddRooms = new JButton("ADD");
		btnAddRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRoom add = new AddRoom();
			}
		});
		btnAddRooms.setBounds(840, 322, 115, 29);
		panel.add(btnAddRooms);
		
		JButton btnAddArtists = new JButton("ADD");
		btnAddArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddArtist add = new AddArtist();
			}
		});
		btnAddArtists.setBounds(331, 322, 115, 29);
		panel.add(btnAddArtists);
		
		JButton btnAddPaintings = new JButton("ADD");
		btnAddPaintings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPainting add = new AddPainting();
			}
		});
		btnAddPaintings.setBounds(87, 322, 115, 29);
		panel.add(btnAddPaintings);
		
		JButton btnDeletePaintings = new JButton("DELETE");
		btnDeletePaintings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeletePainting delete = new DeletePainting();
			}
		});
		btnDeletePaintings.setBounds(87, 358, 115, 29);
		panel.add(btnDeletePaintings);
		
		JButton btnDeleteArtists = new JButton("DELETE");
		btnDeleteArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteArtist delete = new DeleteArtist();
			}
		});
		btnDeleteArtists.setBounds(331, 358, 115, 29);
		panel.add(btnDeleteArtists);
		
		JButton btnDeleteRooms = new JButton("DELETE");
		btnDeleteRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteRoom delete = new DeleteRoom();
			}
		});
		btnDeleteRooms.setBounds(840, 358, 115, 29);
		panel.add(btnDeleteRooms);
		
		JButton btnMovePainting = new JButton("MOVE");
		btnMovePainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovePainting delete = new MovePainting();
			}
		});
		btnMovePainting.setBounds(87, 395, 115, 29);
		panel.add(btnMovePainting);
		
		JButton btnMarkAsSold = new JButton("MARK AS SOLD");
		btnMarkAsSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarkPaintingAsSold delete = new MarkPaintingAsSold();
			}
		});
		btnMarkAsSold.setBounds(65, 440, 162, 36);
		panel.add(btnMarkAsSold);
		
		JButton btnChangeDesignation = new JButton("CHANGE DESIGNATION");
		btnChangeDesignation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeRoomDesignation delete = new ChangeRoomDesignation();
			}
		});
		btnChangeDesignation.setBounds(799, 404, 207, 36);
		panel.add(btnChangeDesignation);
		
		JButton btnBackUp = new JButton("BACK UP");
		btnBackUp.setBounds(506, 547, 213, 49);
		panel.add(btnBackUp);
		
		JButton btnRestore = new JButton("RESTORE");
		btnRestore.setBounds(764, 547, 213, 49);
		panel.add(btnRestore);

		
	}
	
	
	public MainFrame() {
		initialize();
		}
}





