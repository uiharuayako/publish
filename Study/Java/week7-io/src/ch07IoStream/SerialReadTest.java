package ch07IoStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

public class SerialReadTest {
	public static void main(String args[]) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("sample.dat"));
			int i = in.readInt();
			Date date = (Date) in.readObject();
			Address address = (Address) in.readObject();
			in.close();
			System.out.println("Integer£º " + i);
			System.out.println("Date£º " + date);
			System.out.println("Address£º " + address);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
