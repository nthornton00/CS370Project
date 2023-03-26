package gui;

import Details.*;

import javax.swing.*;

public class initialize {
	public static void main(String[] args) {
		try {
			establish_connection.login();
			tester_details.claim_tester(7199, "Fanta");
			tester_detail_window dialog = new tester_detail_window("Fanta");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
