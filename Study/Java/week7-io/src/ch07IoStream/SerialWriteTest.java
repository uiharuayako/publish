package ch07IoStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SerialWriteTest {
	public static void main(String args[]) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sample.dat"));
			int i = 10000;
			Date now = new Date();

			Address address = new Address("fxzhu", "fxzhu@x.y");
			out.writeInt(i);
			out.writeObject(now);
			out.writeObject(address);
			out.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
