package ch07IoStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SafeCopy {
	// 输入流复制到输出流
	public static void copyFile(DataInputStream in, DataOutputStream out) throws IOException {
		try {
			while (true)
				out.writeByte(in.readByte());
		} catch (EOFException eof) {
			return;
		}
	}

	public static void main(String args[]) {
		if (args.length != 2)
			System.out.println("Usage: java SafeCopy sourceFile targetFile");
		else {
			String inFileName = args[0], outFileName = args[1];
			File inFile = new File(inFileName);
			File outFile = new File(outFileName);
			if (!inFile.exists())
				System.out.println(inFileName + " does not exist.");
			else if (outFile.exists())
				System.out.println(outFileName + " already exists.");
			else {
				try {
					DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(inFileName)));
					DataOutputStream out = new DataOutputStream(
							new BufferedOutputStream(new FileOutputStream(outFileName)));
					copyFile(in, out);
					in.close();
					out.close();
				} catch (IOException ioe) {
					System.out.println("Unknown error: " + ioe);
				}
			}
		}
	}
}
