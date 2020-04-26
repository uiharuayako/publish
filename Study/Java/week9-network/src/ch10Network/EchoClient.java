package ch10Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	public static void main(String args[]) {
		try {
			Socket connection = new Socket("localhost", 6666);
			System.out.println("Connection established:");
			DataInputStream in = new DataInputStream(connection.getInputStream());
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			String line = new String("");
			while (!line.toUpperCase().equals(".QUIT")) {
				System.out.print("Enter string: ");
				line = readString();
				System.out.println("\tSending string to server ... ");
				out.writeUTF(line);
				System.out.println("\tWaiting for server response ...");
				line = in.readUTF();
				System.out.println("Received: " + line);
			}
			in.close();
			out.close();
			connection.close();
		} catch (UnknownHostException uhe) {
			System.err.println("Unknown host: " + args[0]);
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe);
		}
	}

	public static String readString() {
		String string = new String();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			string = in.readLine();
		} catch (IOException e) {
			System.out.println("Console.readString: Unknown error...");
			System.exit(-1);
		}
		return string;
	}
}
