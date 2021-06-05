package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CAClient extends JFrame{
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public CAClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		setTitle("CLIENT");
		JPanel panel = new JPanel();
		JTextField jtf = new JTextField(20);
		JButton send = new JButton("SEND MESSAGE");
		JLabel label = new JLabel();
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
			
			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			
			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				label.setText((String)is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
	public void sendMessage(String message) {
		try {
			if (os != null) {
				os.writeObject(message + " SENT FROM CLIENT");
				os.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
