package ch07IoStream;

import java.io.EOFException;
import java.io.RandomAccessFile;

class RafDemo2 {
	public static void main(String args[]) {
		try {
			RandomAccessFile raf = new RandomAccessFile(args[0], "r");
			long count = Long.valueOf(args[1]).longValue();
			long position = raf.length();

			// 位置指针定位在参数指定处
			position -= count;
			if (position < 0)
				position = 0;

			raf.seek(position);

			while (true) {
				try {
					byte b = raf.readByte();
					System.out.print((char) b);
				} catch (EOFException eofe) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
