package ch10Network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Nets {
	static Socket mySocket; // define an socket for client connections
	static ServerSocket SS;
	// define as the server socket
	static DataInputStream datain; // define an input stream
	static DataOutputStream dataout;
	static int MumServers;

	public static void main(String args[]) throws IOException {
		System.out.println("Server coming up ...");

		try {
			SS = new ServerSocket(1237);
		} catch (IOException eos) {
			System.out.println("Error opening server socket.");
			System.exit(1);
		}

		while (true) { // wait for connetion infinitely
			try {
				mySocket = SS.accept(); // Accept client connection
			} catch (IOException e) {
				System.out.println(" I/O error waiting. Exiting.");
				System.exit(1);
			}

			// setup the input and the output stream
			datain = new DataInputStream(new BufferedInputStream(mySocket.getInputStream()));
			dataout = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));
			ServerBody(); // code for actual computation
		}
	}

	public static void ServerBody() throws IOException {

		int i, j, k, sum;
		int TotNum = 0, NumRows = 0; // Values for N, R
		int A[][], B[][], Result[][];

		try {
			TotNum = datain.read(); // read the value of N
		} catch (IOException e) {
			System.out.println("I/O error getting information  from client.Exiting");
			System.exit(1);
		}

		try {
			NumRows = datain.read(); // read value of R
		} catch (IOException e) {
			System.out.println("I/O error while getting info from client. Exiting.");
			System.exit(1);
		}

		// Now N, T are Known, we can actually instantiate the matrices
		A = new int[NumRows][TotNum];
		B = new int[TotNum][TotNum];
		Result = new int[NumRows][TotNum];

		System.out.println("Receiving Matrix A from client.");
		// Read the value for Matrix A
		for (i = 0; i < NumRows; i++)
			for (j = 0; j < TotNum; j++)
				try {
					A[i][j] = datain.readInt();
				} catch (IOException e) {
					System.out.println("I/O error while getting info from client. Exiting");
					System.exit(1);
				}

		System.out.println("receiving matrix B from client.");
		// read the value for Matrix B

		for (i = 0; i < TotNum; i++)
			for (j = 0; j < TotNum; j++)
				try {
					B[i][j] = datain.readInt();
				} catch (IOException e) {
					System.out.println(" I/O Error while getting info from client. Exiting");
					System.exit(1);
				}

		// Compute the matrix multiplication
		for (i = 0; i < NumRows; i++)
			for (j = 0; j < TotNum; j++) {
				sum = 0;
				for (k = 0; k < TotNum; k++)
					sum += A[i][k] * B[k][j];
				Result[i][j] = sum;
			}
		;

		for (i = 0; i < NumRows; i++)
			for (j = 0; j < TotNum; j++)
				try {
					dataout.writeInt(Result[i][j]);
				} catch (IOException e) {
					System.out.println("I/O error while writing to client. Exiting");
					System.exit(1);
				}
		dataout.flush();
		System.out.println("Resultant Matrix sent to client");

	}

}
