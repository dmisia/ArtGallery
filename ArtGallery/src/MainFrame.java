//import java.util.*;
import javax.swing.JPanel;

//import java.util.concurrent.locks.*;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;

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
						outputTextArea.setText("ERROR");
					}
			}
		});
		doSelectButton.setBounds(840, 28, 127, 57);
		doSelectButton.setBackground(new Color(255, 250, 205));
		panel.add(doSelectButton);
		
		JLabel selectLabel = new JLabel("SELECT:");
		selectLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		selectLabel.setBounds(35, 43, 90, 27);
		panel.add(selectLabel);
		
		inputTextArea = new JTextArea();
		inputTextArea.setBounds(134, 28, 662, 57);
		panel.add(inputTextArea);
		
		outputTextArea = new JTextArea();
		outputTextArea.setBounds(45, 101, 751, 117);
		panel.add(outputTextArea);
		
		JScrollPane scroll = new JScrollPane (outputTextArea);
	    scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scroll.setBounds(134, 99, 662, 112);
	    panel.add(scroll);
	    getContentPane().add(scroll);
		
		JLabel label = new JLabel("_________________________________________________________________________________________________________________________________________________________________________");
		label.setBounds(0, 234, 1182, 20);
		panel.add(label);
		
		JLabel lblManagePaintings = new JLabel("MANAGE PAINTINGS");
		lblManagePaintings.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblManagePaintings.setBounds(53, 270, 207, 36);
		panel.add(lblManagePaintings);
		
		JLabel lblManageArtists = new JLabel("MANAGE ARTISTS");
		lblManageArtists.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblManageArtists.setBounds(315, 270, 168, 36);
		panel.add(lblManageArtists);
		
		JLabel lblManageRooms = new JLabel("MANAGE ROOMS");
		lblManageRooms.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblManageRooms.setBounds(812, 270, 185, 36);
		panel.add(lblManageRooms);
		
		JLabel lblManageBuyers = new JLabel("MANAGE BUYERS");
		lblManageBuyers.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblManageBuyers.setBounds(572, 270, 168, 36);
		panel.add(lblManageBuyers);
		
		JButton btnAddBuyers = new JButton("ADD");
		btnAddBuyers.setBackground(new Color(255, 250, 205));
		btnAddBuyers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBuyer add = new AddBuyer();
			}
		});
		btnAddBuyers.setBounds(564, 322, 177, 29);
		panel.add(btnAddBuyers);
		
		JButton btnAddRooms = new JButton("ADD");
		btnAddRooms.setBackground(new Color(255, 250, 205));
		btnAddRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRoom add = new AddRoom();
			}
		});
		btnAddRooms.setBounds(799, 322, 207, 29);
		panel.add(btnAddRooms);
		
		JButton btnAddArtists = new JButton("ADD");
		btnAddArtists.setBackground(new Color(255, 250, 205));
		btnAddArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddArtist add = new AddArtist();
			}
		});
		btnAddArtists.setBounds(308, 322, 177, 29);
		panel.add(btnAddArtists);
		
		JButton btnAddPaintings = new JButton("ADD");
		btnAddPaintings.setBackground(new Color(255, 250, 205));
		btnAddPaintings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPainting add = new AddPainting();
			}
		});
		btnAddPaintings.setBounds(53, 322, 185, 29);
		panel.add(btnAddPaintings);
		
		JButton btnDeleteArtists = new JButton("DELETE");
		btnDeleteArtists.setBackground(new Color(255, 250, 205));
		btnDeleteArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteArtist delete = new DeleteArtist();
			}
		});
		btnDeleteArtists.setBounds(308, 367, 175, 29);
		panel.add(btnDeleteArtists);
		
		JButton btnDeleteRooms = new JButton("DELETE");
		btnDeleteRooms.setBackground(new Color(255, 250, 205));
		btnDeleteRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteRoom delete = new DeleteRoom();
			}
		});
		btnDeleteRooms.setBounds(799, 367, 207, 29);
		panel.add(btnDeleteRooms);
		
		JButton btnMovePainting = new JButton("MOVE");
		btnMovePainting.setBackground(new Color(255, 250, 205));
		btnMovePainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovePainting delete = new MovePainting();
			}
		});
		btnMovePainting.setBounds(53, 367, 185, 29);
		panel.add(btnMovePainting);
		
		JButton btnMarkAsSold = new JButton("MARK AS SOLD");
		btnMarkAsSold.setBackground(new Color(255, 250, 205));
		btnMarkAsSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarkPaintingAsSold delete = new MarkPaintingAsSold();
			}
		});
		btnMarkAsSold.setBounds(53, 412, 185, 36);
		panel.add(btnMarkAsSold);
		
		JButton btnChangeDesignation = new JButton("CHANGE DESIGNATION");
		btnChangeDesignation.setBackground(new Color(255, 250, 205));
		btnChangeDesignation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeRoomDesignation delete = new ChangeRoomDesignation();
			}
		});
		btnChangeDesignation.setBounds(799, 412, 207, 36);
		panel.add(btnChangeDesignation);
		
		JButton btnBackUp = new JButton("BACK UP");
		btnBackUp.setBackground(new Color(255, 250, 205));
		btnBackUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					Process process = Runtime.getRuntime().exec("mysqldump -u root -p1234 Art_Gallery > ag.sql");
					System.out.println("Backup");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBackUp.setBounds(506, 547, 213, 49);
		panel.add(btnBackUp);
		
		JButton btnRestore = new JButton("RESTORE");
		btnRestore.setBackground(new Color(255, 250, 205));
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					Process process = Runtime.getRuntime().exec("mysql -u root -p1234 Art_Gallery < ag.sql");
					System.out.println("Restore");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRestore.setBounds(764, 547, 213, 49);
		panel.add(btnRestore);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/background.jpg"));
		lblNewLabel.setBounds(0, -5, 1054, 617);
		panel.add(lblNewLabel);
		

		
	}
	
	
	public MainFrame() {
		initialize();
		}
}





