package ch10Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoServerThread extends Thread {
	private Socket connection;

	public EchoServerThread(Socket _connection) {
		connection = _connection;
		start();
	}

	public void run() {
		try {
			DataInputStream in = new DataInputStream(connection.getInputStream());
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			String line = new String("");
			while (!line.equalsIgnoreCase(".QUIT")) {
				line = in.readUTF();
				System.out.println("Echoing: " + line);
				out.writeUTF(line);
			}
			in.close();
			out.close();
			connection.close();
			System.out.println("Connection closed.");
		} catch (IOException ioe) {
			System.out.println("Connection closed unexpectedly.");
		}
	}
}
