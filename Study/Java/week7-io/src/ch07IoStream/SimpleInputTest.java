package ch07IoStream;
import java.io.*;
public class SimpleInputTest{
   public static void main(String args[]){
      try{
         FileInputStream fs_in = new FileInputStream("d:/sample");
         DataInputStream in = new DataInputStream(fs_in);
         double Pi = in.readDouble();
         int i = in.readInt();
         boolean okay = in.readBoolean();
         char cc = in.readChar();
         String s = in.readUTF();
         in.close();
         System.out.println("Pi = " + Pi + ", i = " + i);
         System.out.println("okay = " + okay + ", cc = " + cc);
         System.out.println("s = " + s);
      } catch(FileNotFoundException fnfe) {
         System.err.println(fnfe);
      }catch(IOException ioe) {
        System.err.println(ioe); 
}
   }
}
