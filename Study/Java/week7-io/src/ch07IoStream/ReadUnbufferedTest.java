package ch07IoStream;
import java.io.*;
public class ReadUnbufferedTest{
    public static void main(String args[]){
        double sum = 0;
        try{
            long start = System.currentTimeMillis();
            FileInputStream fs_in = new FileInputStream ("sample.dat");
            DataInputStream in = new DataInputStream(fs_in);
            for (int i = 0; i < 10000000; i++)
                sum += in.readDouble();
            in.close();
            long stop  = System.currentTimeMillis();
            System.out.println("Average: " + (sum / 10000000));
            System.out.println("Time passed: " + (stop - start));

        } catch(IOException ioe) {
            System.out.println(ioe);
        }
    }
}
