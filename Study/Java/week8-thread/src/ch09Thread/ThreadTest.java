package ch09Thread;

public class ThreadTest
{  public static void main(String args[])
   {  System.out.println("Starting ThreadTest");
      CountingThread thread1 = new CountingThread();
      thread1.start();
      CountingThread thread2 = new CountingThread();
      thread2.start();
      CountingThread thread3 = new CountingThread();
      thread3.start();
      System.out.println("ThreadTest is done.");
    }
}

