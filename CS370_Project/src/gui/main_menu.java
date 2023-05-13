package gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Details.establish_connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class main_menu extends JFrame {
	private static final long serialVersionUID = 782716336516707771L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_page.startLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 * @throws Exception 
	 */
	public main_menu(int emplNum, String region) throws Exception {
		Calendar C = new GregorianCalendar();
        int hour = C.get( Calendar.HOUR_OF_DAY);
        String current_shift;
        String next_shift;
        
        if (hour > 19 || hour < 7) {
        	current_shift = "Night";
        	next_shift = "Day";
        }
        else {
        	current_shift = "Day";
        	next_shift = "Night";
        }
		
		setTitle("CS370 Project Lab Map");
		//Initialize connection to the database
		Connection c = establish_connection.connect();
		
		//Create the database statement
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		//Initialize result set and classes for tester database based in the selected region
		ResultSet rsTester = s.executeQuery("SELECT * FROM labmap.tester WHERE region='" + region + "'");
		
		int maxX = 300; //Default max width of the GUI
		int maxY = 32; //Default max height of the GUI
		
		while(rsTester.next()) { //Find out what's the biggest variable in the region
			int x = rsTester.getInt("x_coord");
			int y = rsTester.getInt("y_coord");
			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, maxX + 125, maxY + 72); //Boundaries based on gotten coordinates
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setResizable(false); //Do not allow the user to resize the GUI

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Adds menu bar to the GUI
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, maxX + 109, 22);
		contentPane.add(menuBar);
		
		
		JMenu menuAccount = new JMenu("Account");
		menuBar.add(menuAccount);
		
		JMenuItem menuItemUserInfo = new JMenuItem("User Info");
		menuAccount.add(menuItemUserInfo);
		
		JMenuItem menuItemLogout = new JMenuItem("Logout");
		menuAccount.add(menuItemLogout);
		menuItemLogout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				try {
					login_page.startLogin();
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		JMenu menuMaintenance = new JMenu("Maintenance");
		menuBar.add(menuMaintenance);
		
		JMenuItem menuItemActiveRequests = new JMenuItem("Active Requests");
		menuMaintenance.add(menuItemActiveRequests);
		menuItemActiveRequests.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				try {
					maintenance_active maintActive = new maintenance_active();
					maintActive.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		JMenuItem menuItemHistoryLog = new JMenuItem("History Log");
		menuMaintenance.add(menuItemHistoryLog);
		menuItemHistoryLog.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				try {
					maintenance_log maintLog = new maintenance_log();
					maintLog.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		JMenuItem menuItemSendRequests = new JMenuItem("Send Requests");
		menuMaintenance.add(menuItemSendRequests);
		menuItemSendRequests.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				try {
					submit_request submitRequest = new submit_request(emplNum);
					submitRequest.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		JMenu regionMenuBar = new JMenu("Change Region");
		menuBar.add(regionMenuBar);
		
		//Region>San Diego, reload GUI with San Diego region map
		JMenuItem menuItemRegionSD = new JMenuItem("San Diego");
		regionMenuBar.add(menuItemRegionSD);
		menuItemRegionSD.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	main_menu frame;
				try {
					frame = new main_menu(emplNum, "San Diego");
					frame.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		//Region>Taiwan, reload GUI with Taiwan region map
		JMenuItem menuItemRegionTW = new JMenuItem("Taiwan");
		regionMenuBar.add(menuItemRegionTW);
		menuItemRegionTW.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	main_menu frame;
				try {
					frame = new main_menu(emplNum, "Taiwan");
					frame.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		//Region>India, reload GUI with India region map
		JMenuItem menuItemRegionIN = new JMenuItem("India");
		regionMenuBar.add(menuItemRegionIN);
		menuItemRegionIN.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	main_menu frame;
				try {
					frame = new main_menu(emplNum, "India");
					frame.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		while(rsTester.next()) {
			int x = rsTester.getInt("x_coord");
			int y = rsTester.getInt("y_coord");
			if (x > maxX)
				maxX = x;
			if (y > maxY)
				maxY = y;
		}
		
		JMenu menuShift = new JMenu("Shift Staffing");
		menuBar.add(menuShift);
		
		JMenuItem menuItemCurrentShift = new JMenuItem("Current Shift");
		menuShift.add(menuItemCurrentShift);
		menuItemCurrentShift.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				try {
					staffing currStaff = new staffing(current_shift, region);
					currStaff.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		JMenuItem menuItemNextShift = new JMenuItem("Next Shift");
		menuShift.add(menuItemNextShift);
		menuItemNextShift.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
				try {
					staffing currStaff = new staffing(next_shift, region);
					currStaff.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		JMenu menuAbout = new JMenu("Help");
		menuBar.add(menuAbout);
		
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuAbout.add(menuItemAbout);
		
		while(rsTester.previous()) { //Utilizing same result list, go backwards to place all testers
			if (rsTester.getString("region").equals(region)) {
				String tester_name = rsTester.getString("tester_name");
				JButton button = new JButton(tester_name);
				int x = rsTester.getInt("x_coord");
				int y = rsTester.getInt("y_coord");
				button.setBounds(x, y, 100, 23);
				contentPane.add(button);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							tester_detail_window tester_detail = new tester_detail_window(tester_name, emplNum);
							tester_detail.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							tester_detail.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		}
		
		rsTester.beforeFirst();
	}
}
