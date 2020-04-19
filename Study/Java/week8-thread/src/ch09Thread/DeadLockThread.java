package ch09Thread;

import java.awt.TextArea;
public class DeadLockThread extends Thread{
   private DeadLock bank;
   private int  id;
   private TextArea display;
   public DeadLockThread(int _id, DeadLock _bank, TextArea _display) {
      bank    = _bank;
      id      = _id;
      display  = _display;
      start();
   }
   public void run(){
      while (true) {
         int amount = (int)(1500 * Math.random());
         display.append("\nThread " + DeadLock.NAMES[id] + " sends $" +
                        amount + " into " + DeadLock.NAMES[(1-id)]);
         try{
           sleep(20);
         } catch (InterruptedException ie) {
             System.err.println("Interrupted");
         }
         bank.transfer(id, 1-id, amount);
      }
   }
}
