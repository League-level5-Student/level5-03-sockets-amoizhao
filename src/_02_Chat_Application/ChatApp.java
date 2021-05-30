package _02_Chat_Application;

import javax.swing.JOptionPane;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	CAServer server;
	CAClient client;
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
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new CAClient(ipStr, port);
			client.start();
		}
	}
}
