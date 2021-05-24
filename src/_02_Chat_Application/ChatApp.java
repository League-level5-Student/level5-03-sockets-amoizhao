package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	Server server;
	Client client;
	
	JButton sendServ = new JButton("Send a message to the server.");
	JButton button = new JButton("Send a message to the server.");

	public static void main(String args[]) {
		new ChatApp();
	}

	public ChatApp() {
		
		String messageSent = JOptionPane.showInputDialog("Send a message to the server");
		
		server = new Server(8080);
		System.out.println("Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
		server.sendMessage(messageSent);
		server.start();
		

	}
}
