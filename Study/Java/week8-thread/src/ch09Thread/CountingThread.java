package ch09Thread;

public class CountingThread extends Thread
{  public void run()
   {  System.out.println("Thread started: " + this);
      for (int i = 0; i < 18; i++)
        System.out.print(this.getName()+".i = " + (i+1) + "\n");
      System.out.println("Thread ended: " + this);
   }
}

