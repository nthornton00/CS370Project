package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class helpWindow extends JFrame {
	
		public void MainWindow() {
	        setTitle("Main Window");
	        setSize(400, 300);
	        setLayout(new GridLayout(5, 1));
	        addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent windowEvent) {
	                System.exit(0);
	            }
	        });

	        Button loginButton = createButton("Logging in");
	        Button labMapButton = createButton("LabMap Window");
	        Button accountButton = createButton("Account");
	        Button maintenanceButton = createButton("Maintenance");
	        Button changeRegionButton = createButton("Change Region");

	        add(loginButton);
	        add(labMapButton);
	        add(accountButton);
	        add(maintenanceButton);
	        add(changeRegionButton);
	    }

	    private Button createButton(String label) {
	        Button button = new Button(label);
	        button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String additionalInfo = getAdditionalInfo(label);
	                openAdditionalWindow(label, additionalInfo);
	            }
	        });
	        return button;
	    }

	    private String getAdditionalInfo(String label) {
	        String additionalInfo = "";
	        
	        switch (label) {
	            case "Logging in":
	                additionalInfo = "-> Enter the username and password of an existing user\n"
	                		+ "	-> If login is successful, the program will prompt the user to the labmap window (main menu).\n"
	                		+ "-> If the login is unsuccessful, the program will prompt the user to reenter their credentials until a successful login occurs, or the user exits the program.";
	                break;
	            case "LabMap Window":
	                additionalInfo = "->Displays a visual of the available testers for the selected region.\n"
	                		+ "->Also displays the following functions: \"Account,\"  \"Maintenance,\"  \"Change Region,\" \"Shift Staffing,\" and \"Help.\"";
	                break;
	            case "Account":
	                additionalInfo = "User Info:\nDisplays the name, role, and project of the current user.\nLogout:\nLogs out the current user and redirects the user back to the login page.";
	                break;
	            case "Maintenance":
	                additionalInfo = "Active Requests\nShows active maintenance requests for a tester.\nHistory Log\nShows history log of all maintenance requests made.\nSend Requests\nAllows a user to log and send a maintenance request.";
	                break;
	            case "Change Region":
	                additionalInfo = "->Will display a list of the laboratory regions.\n"
	                		+ "->Clicking on a region will update the window with the contents of the respective region.";
	                break;
	        }
	        return additionalInfo;
	    }

	    private void openAdditionalWindow(String label, String additionalInfo) {
	        JFrame additionalWindow = new JFrame();
	        additionalWindow.setTitle(label);
	        additionalWindow.setSize(400, 200);
	        additionalWindow.setLayout(new FlowLayout());
	        additionalWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
	        
	        JTextArea textArea = new JTextArea();
	        textArea.setBounds(0,0,350,200);
	        textArea.setText(additionalInfo);
	        textArea.setWrapStyleWord(true);
	        textArea.setLineWrap(true);
	        additionalWindow.add(textArea);
	        
	        additionalWindow.setVisible(true);
	    }

	    public static void main(String[] args) {
	        helpWindow mainWindow = new helpWindow();
	        mainWindow.MainWindow();
	        mainWindow.setVisible(true);
	    }
	}