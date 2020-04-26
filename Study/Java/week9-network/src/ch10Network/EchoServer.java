package ch10Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static boolean running = true;

	public static void main(String args[]) {
		try {
			ServerSocket server = new ServerSocket(6666);
			System.out.println("Server started on " + server.getLocalPort());
			while (running) {
				Socket connection = server.accept();
				System.out.println("New connection moved to thread.");
				EchoServerThread handler = new EchoServerThread(connection);
			}
		} catch (IOException ioe) {
			System.err.println("Error: " + ioe);
		}
	}
}
