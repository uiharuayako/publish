package ch07IoStream;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RafDemo1 {
	public static void main(String args[]) throws IOException {
		// rw 表示对文件读、写
		RandomAccessFile raf = new RandomAccessFile("random.txt", "rw");
		raf.writeBoolean(true);
		raf.writeInt(168168);
		raf.writeChar('i');
		raf.writeDouble(168.168);

		raf.seek(1);
		System.out.println(raf.readInt());
		System.out.println(raf.readChar());
		System.out.println(raf.readDouble());

		raf.seek(0);
		System.out.println(raf.readBoolean());

		raf.close();
	}
}
