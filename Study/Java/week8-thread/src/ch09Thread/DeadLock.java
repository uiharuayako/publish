package ch09Thread;

import java.awt.*;
import java.awt.event.*;
import java.awt.TextArea;
public class DeadLock extends Frame{
   protected static final String[] NAMES = {"A","B"};
   private int accounts[] = {1000, 1000};
   private TextArea info = new TextArea(5,40);
   private TextArea status  = new TextArea(5,40);
   public DeadLock(){
      super("Deadly DeadLock");
      setLayout(new GridLayout(2,1));
      add(makePanel(info, "Accounts")); add(makePanel(status, "Threads"));
      validate(); pack(); setVisible(true);
      DeadLockThread A = new DeadLockThread(0, this, status);
      DeadLockThread B = new DeadLockThread(1, this, status);
      addWindowListener(new WindowAdapter()
         {  public void windowClosing(WindowEvent we)
            {  System.exit(0); }
         });
   }
   public synchronized void transfer(int from, int into, int amount){
      info.append("\nAccount A: $" + accounts[0]);
      info.append("\tAccount B: $" + accounts[1]);
      info.append("\n=> $" + amount + " from " + NAMES[from] + 
                  " to " + NAMES[into]);
      while (accounts[from] < amount) {
         try{
           wait(); 
         }catch(InterruptedException ie) { 
           System.err.println("Error: " + ie);
         }
      }
      accounts[from] -= amount;
      accounts[into] += amount;
      notify();
   }
   private Panel makePanel(TextArea text, String title) {
      Panel p = new Panel();
      p.setLayout(new BorderLayout());
      p.add("North", new Label(title)); p.add("Center", text);
      return p;
   }
   public static void main(String args[])
   {  DeadLock bank = new DeadLock(); }
}


