package ch07IoStream;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class TextViewer extends Frame implements ActionListener{
    private Menu fileMenu = new Menu("File");
    private MenuItem fileOpen  = new MenuItem("Open");
    private MenuItem fileExit   = new MenuItem("Exit");
    private TextArea text       = new TextArea();
    public TextViewer(){
        super("Text Viewer");
        fileMenu.add(fileOpen); fileOpen.addActionListener(this);
        fileMenu.addSeparator();
        fileMenu.add(fileExit); fileExit.addActionListener(this);
        MenuBar menu = new MenuBar();
        menu.add(fileMenu);
        setMenuBar(menu);
        setLayout(new BorderLayout());
        add("Center", text);
        text.setEditable(true);
        setSize(400,400);
        setVisible(true);
    }
    public void readFile(String file) {
        text.setText("");
        try{
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null)
                text.append(line + "\n");
            in.close();
            text.setCaretPosition(0);
        } catch(IOException ioe) {
            System.err.println(ioe); 
        }
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fileExit)
            System.exit(0);
        else if (ae.getSource() == fileOpen) {
            FileDialog fd =new FileDialog(this,
                "Open File",FileDialog.LOAD);
            fd.setVisible(true);
            if (fd.getFile() != null) {
                File file = new File(fd.getDirectory() + fd.getFile());
                if (file.exists())
                    readFile(file.toString());
                else
                    text.setText("File name: " + file + " invalid.");
            }
            fd.dispose();
        }
    }
    public static void main(String args[]){
        TextViewer editor = new TextViewer(); 
    }
}

