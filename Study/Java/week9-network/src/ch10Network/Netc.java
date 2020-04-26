package ch10Network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Netc {
	static Socket sock[]; // define an arrary of socket
	static InetAddress Serveraddr[];
	// define an arrary to hold the IP address of
	// all the servers
	static DataInputStream datain[]; // define an arrary of input stream
	static DataOutputStream dataout[];
	static int NumServers;

	public static void main(String args[]) throws IOException {
		int i;
		DataInputStream ServerConfigFile; /* for server config file */
		String IntString = null, Servernames[];

		ServerConfigFile = new DataInputStream(new FileInputStream("srvcfg.txt"));

		try {
			IntString = ServerConfigFile.readLine();
		} catch (IOException ioe) {
			System.out.println("Error reading the # servers");
			System.exit(1);
		}

		try {
			NumServers = Integer.parseInt(IntString); // string to int
		} catch (NumberFormatException nfe) {
			System.out.println(" r servers is not an interger.");
			System.exit(1);
		}

		// now we know number of servers, we can instantiate the arrarys
		Servernames = new String[NumServers];
		sock = new Socket[NumServers];
		Serveraddr = new InetAddress[NumServers];
		datain = new DataInputStream[NumServers];
		dataout = new DataOutputStream[NumServers];

		for (i = 0; i < NumServers; i++) {
			try {
				Servernames[i] = ServerConfigFile.readLine();
			} catch (IOException e) {
				System.out.println("Error reading server names");
				System.exit(1);
			}
			Servernames[i] = Servernames[i].trim();
		}

		try {
			ServerConfigFile.close();
		} catch (IOException e) {
			System.out.println("Error reading server names");
			System.exit(1);
		}

		// open socket to the server and setup the stream
		try {
			for (i = 0; i < NumServers; i++) { // get IP address
				Serveraddr[i] = InetAddress.getByName(Servernames[i]);

				System.out.println("i=" + Serveraddr[i] + Servernames[i]);

				sock[i] = new Socket(Serveraddr[i], 1237);

				datain[i] = new DataInputStream(new BufferedInputStream(sock[i].getInputStream()));
				dataout[i] = new DataOutputStream(new BufferedOutputStream(sock[i].getOutputStream()));
			}
			;
		} catch (IOException E) {
			System.out.println("I/O Error openning stream sockets.");
			System.exit(1);
		}

		// call the client body procedure to solve problem
		ClientBody();

		try { // close all streams and sockets
			for (i = 0; i < NumServers; i++) {
				dataout[i].close();
				datain[i].close();
				sock[i].close();
			}
			;
		} catch (IOException E) {
			System.out.println("error closing streams and Sockets");
			System.exit(1);
		}
	}

	public static void ClientBody() throws IOException {

		int i, j, k;
		int TotNum = 0, NumRows = 0;
		int A[][], B[][], C[][];

		DataInputStream ClientConfigFile;
		String IntString = null;

		ClientConfigFile = new DataInputStream(new FileInputStream("clicfg.txt"));

		try {
			IntString = ClientConfigFile.readLine();
		} catch (IOException ioe) {
			System.out.println("error reading N from file.");
			System.exit(1);
		}

		try {
			TotNum = Integer.parseInt(IntString); // string to integer
		} catch (NumberFormatException nfe) {
			System.out.println("the value for N is not an integer.");
			System.exit(1);
		}

		try {
			ClientConfigFile.close();
		} catch (IOException e) {
			System.out.println("I/O error closing config file.");
			System.exit(1);
		}

		NumRows = TotNum / NumServers;

		A = new int[TotNum][TotNum];
		B = new int[TotNum][TotNum];
		C = new int[TotNum][TotNum];

		for (i = 0; i < TotNum; i++)
			for (j = 0; j < TotNum; j++) {
				A[i][j] = i;
				B[i][j] = j;
				C[i][j] = 0;
			}

		System.out.println("Sending information to servers.");

		try {
			for (i = 0; i < NumServers; i++) {
				dataout[i].write(TotNum);
				dataout[i].write(NumRows);
				dataout[i].flush();

				for (j = NumRows * i; j < NumRows * (i + 1); j++)
					for (k = 0; k < TotNum; k++)
						dataout[i].writeInt(A[j][k]);
				dataout[i].flush();

				for (j = 0; j < TotNum; j++)
					for (k = 0; k < TotNum; k++)
						dataout[i].writeInt(B[j][k]);
				dataout[i].flush();
			}
			;
		} catch (IOException ioe) {
			System.out.println("I/O error reading matrix to server");
			System.exit(1);
		}

		try {
			for (i = 0; i < NumServers; i++)
				for (j = NumRows * i; j < NumRows * (i + 1); j++)
					for (k = 0; k < TotNum; k++)
						C[j][k] = datain[i].readInt();
		} catch (IOException ioe) {
			System.out.println("I/O error receiving result from server");
			System.exit(1);
		}
		System.out.println();
		System.out.println("Resultant Matrix");
		System.out.println();

		for (i = 0; i < TotNum; i++) {
			for (j = 0; j < TotNum; j++)
				System.out.print(C[i][j] + " ");
			System.out.println();
		}
	}
}
