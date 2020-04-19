package ch07IoStream;
import java.io.*;
public class SimpleCharOutputTest{
 public static void main(String args[]){
      double Pi = 3.141599999999;
      int i = 10;
      boolean okay = true;
      char cc = 'J';
      String s = "Java C Pascal";
      try{
         FileWriter fw = new FileWriter("sample-char.txt");
         PrintWriter out = new PrintWriter(new BufferedWriter(fw));
         out.println(Pi);
         out.println(i);
         out.println(okay);
         out.println(cc);
         out.println(s);
         out.close();
         if (out.checkError())
            System.out.println("An error has occured during output.");
      }catch(IOException ioe) {
         System.out.println("Error while opening the file."); 
}
   }
}
