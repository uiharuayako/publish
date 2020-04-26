package ch10Network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class ClientSide {
	public static void main(String[] args) {
		PrintStream output;
		DataInputStream input;
		String string;
		Socket socket = null;

		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		System.out.println("prepare connect...");
		try {
			socket = new Socket("127.0.0.1", 1111);
		} catch (IOException e) {
			System.out.println("Error" + e);
			return;
		}

		try {
			input = new DataInputStream(socket.getInputStream());
			output = new PrintStream(socket.getOutputStream());

			System.out.println(input.readLine());
			byte bArray[] = new byte[20];
			System.in.read(bArray);
			String s = new String(bArray, 0);
			output.println(s);

			System.out.println(input.readLine());
			System.out.print("Logging off...");
			socket.close();
			input.close();
			output.close();
			System.out.println("Done");

		} catch (IOException e) {
		}

	}
}
