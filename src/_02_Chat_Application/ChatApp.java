package _02_Chat_Application;

import javax.swing.JOptionPane;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	CAServer server = new CAServer(8080);;
	CAClient client = new CAClient(server.getIPAddress(), 8080);;
	String msg;

	public static void main(String args[]) {
		new ChatApp();
	}
 
	public ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new CAServer(8080);
			server.start();
			
		}else{
			client.start();
		}
	}
}
