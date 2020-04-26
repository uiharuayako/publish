package ch10Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The <B>Console</B> class provides methods that allow easy reading of the
 * basic data types <TT>double</TT>, <TT>int</TT>, and <TT>String</TT> from
 * standard input. <I>Must be placed in the same directory as the source code
 * using it.</I>
 * <P>
 * 
 * <B>Usage Example:</B>
 * 
 * <PRE>
 * System.out.println("Enter a double number: ");
 * double x = Console.readDouble();
 * System.out.println("You have entered: " + x);
 * </PRE>
 */
public class Console {
	/**
	 * Reads a single double from the keyboard. Stops execution in case of error.
	 * 
	 * @return double
	 */
	public static double readDouble() {
		try {
			return Double.valueOf(readString().trim()).doubleValue();
		} catch (NumberFormatException ne) {
			System.err.println("Console.readDouble: Not a double...");
			System.exit(-1);
			return 0.0;
		}
	}

	/**
	 * Reads a single int from the keyboard. Stops execution in case of error.
	 * 
	 * @return int
	 */
	public static int readInt() {
		try {
			return Integer.valueOf(readString().trim()).intValue();
		} catch (NumberFormatException ne) {
			System.err.println("Console.readInt: Not an integer...");
			System.exit(-1);
			return -1;
		}
	}

	/**
	 * Reads a String from the keyboard until RETURN or ENTER key is pressed.
	 * 
	 * @return String
	 */
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
