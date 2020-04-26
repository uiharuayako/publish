/*
package ch10Network;
import com.sun.webkit.WebPage;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
public class WebViewer extends Frame implements ActionListener{
   private TextField address = new TextField();
   private TextArea  display = new TextArea();
   private Button    go    = new Button("View Page");
   private class WindowCloser extends WindowAdapter{
      public void windowClosing(WindowEvent we)
      {  System.exit(0); }
   }
   public WebViewer(){
      super("Web Viewer Lite");
      Panel north = new Panel();
      north.setLayout(new BorderLayout());
      north.add("West", new Label("URL:"));
      north.add("Center", address);
      north.add("East", go); go.addActionListener(this);
      Panel center = new Panel();
      setLayout(new BorderLayout());
      add("North", north);
      add("Center", display);
      addWindowListener(new WindowCloser());
      validate(); pack(); setVisible(true);
   }
   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == go)
         showURL(address.getText());
   }
   public void showURL(String address) {
      try{
         setCursor(new Cursor(Cursor.WAIT_CURSOR));
         URL url = new URL(address);
         String host = url.getHost(); 
         int port = url.getPort();
         if (port <= 0)
            port = 80;
         WebPage page = new WebPage(host, port, url.getFile());
         display.setText(page.getContent());
      } catch(MalformedURLException murle) {
          display.setText("Invalid URL: " + address);
      } finally{
          setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      }
   }
   

   public static void main(String args[])
   {  WebViewer viewer = new WebViewer(); }
}
*/
