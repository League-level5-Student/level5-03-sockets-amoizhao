package _02_Chat_Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CAServer extends JFrame{
	private int port;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public CAServer(int port) {
		this.port = port;
	}

	public void start(){
		setTitle("SERVER");
		JPanel panel = new JPanel();
		JTextField jtf = new JTextField(20);
		JLabel label = new JLabel();
		JButton send = new JButton("SEND MESSAGE");
		panel.add(jtf);
		panel.add(send);
		panel.add(label);
		add(panel);
		setVisible(true);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		send.addActionListener((e)->{
			String message = jtf.getText();
			sendMessage(message);
		});
		
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			while (connection.isConnected()) {
				try {
					label.setText((String)is.readObject());
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage(String message) {
		try {
			if (os != null) {
				os.writeObject(message + " SENT FROM SERVER");
				os.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
