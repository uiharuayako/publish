package ch08JavaFX.homework;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Question345 extends Application {
    File thisFile = null;
    FileChooser fileChooser = new FileChooser();
    @Override
    public void start(Stage textEditor){
        TextArea notePad = new TextArea();// 一个写字板
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 640, 480, Color.WHITE);
        root.setCenter(notePad);// 写字板放在中间
        textEditor.setTitle("简易文本编辑器 powered by JavaFX -- Uiharu");// Uiharu是我的笔名，不用在意
        // 菜单开始
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(textEditor.widthProperty());
        root.setTop(menuBar);

        // 文件菜单
        Menu fileMenu = new Menu("文件");
        MenuItem newMenuItem = new MenuItem("新建");
        MenuItem openMenuItem = new MenuItem("打开");
        MenuItem saveMenuItem = new MenuItem("保存");
        MenuItem exitMenuItem = new MenuItem("退出");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        openMenuItem.setOnAction(event -> {
            fileChooser.setTitle("打开文件");
            thisFile = fileChooser.showOpenDialog(textEditor);
            if(thisFile!=null){
            notePad.setText(readFile(thisFile));
            textEditor.setTitle(thisFile.toString());
            }

        });
        newMenuItem.setOnAction(event -> {
            notePad.clear();// 清空当前内容
            Stage newStage=new Stage();
            BorderPane newPane=new BorderPane();
            Scene newScene=new Scene(newPane,320,240,Color.WHITE);
            newStage.setScene(newScene);
            newStage.setTitle("新建文件对话框");
            TextField newFilePath=new TextField();
            Label newLabel=new Label("请输入要新建文件的绝对路径，包括文件名\n如D:\\a.java");
            Button newButton=new Button("新建");
            newPane.setTop(newLabel);
            newPane.setCenter(newFilePath);
            newPane.setBottom(newButton);
            newButton.setOnAction(event1 -> {
                try {
                    File file = new File(newFilePath.getText());
                    //这是第四题
                    if (file.exists()){
                        message("文件已存在，打开该文件").show();
                        thisFile=file;
                        notePad.setText(readFile(thisFile));//readFile创建了一个输入流
                        textEditor.setTitle(thisFile.toString());
                    }
                    else {
                        if(file.createNewFile()){
                            thisFile=file;
                            notePad.setText(readFile(thisFile));//readFile创建了一个输入流
                            textEditor.setTitle(thisFile.toString());
                            message("新建完成").show();
                        }
                    }
                    newStage.hide();
                }
                catch (Exception err){
                    message("未知原因的文件错误").show();
                }
            });
            newStage.show();
        });
        saveMenuItem.setOnAction(event -> {
            if(thisFile==null){
                fileChooser.setTitle("选择要保存到的文件，或自行新建文件保存");
                thisFile = fileChooser.showOpenDialog(textEditor);
                if(thisFile!=null){
                    textEditor.setTitle(thisFile.toString());
                }
            }
            if(thisFile!=null){
                writeFile(thisFile,notePad.getText());
            }
        });
        fileMenu.getItems().addAll(newMenuItem, openMenuItem,saveMenuItem,new SeparatorMenuItem(), exitMenuItem);
        //编辑菜单
        Menu editMenu=new Menu("编辑");
        MenuItem searchIt=new MenuItem("查找");
        MenuItem replaceIt=new MenuItem("替换");
        searchIt.setOnAction(event -> {
            //下面是搜索功能的具体实现
            Stage search=new Stage();
            search.setTitle("查找");
            Label searchLabel=new Label("在当前光标位置之后查找指定字符串\n并将光标置于串后");
            BorderPane searchPane=new BorderPane();
            Scene searchScene=new Scene(searchPane,320,240,Color.WHITE);
            TextField searchField=new TextField();
            Button searchButton=new Button("向下查找");
            searchButton.setOnAction(event1 -> {
                String target=searchField.getText();
                String allString=notePad.getText();
                try {
                    int place=allString.indexOf(target,notePad.getCaretPosition());
                    if(place==-1){
                        message("无此字符串，自动将光标回到文件头").show();
                    }
                    notePad.positionCaret(1+place);
                }
                catch (Exception e){
                    message("未知错误的查找失败！").show();
                }
            });
            searchPane.setCenter(searchField);
            searchPane.setBottom(searchButton);
            searchPane.setTop(searchLabel);

            search.setScene(searchScene);
            search.show();
        });
        replaceIt.setOnAction(event -> {
            //下面是替换功能的具体实现
            Stage search=new Stage();
            search.setTitle("查找并替换");
            Label searchLabel=new Label("在当前光标位置之后查找指定字符串\n并将该字符串替换为指定字符串");
            VBox searchPane=new VBox(5);
            Scene searchScene=new Scene(searchPane,320,240,Color.WHITE);
            TextField searchField=new TextField();
            TextField replaceField=new TextField();
            Button searchButton=new Button("向下查找并立即替换");
            searchButton.setOnAction(event1 -> {
                String target=searchField.getText();
                String allString=notePad.getText();
                try {
                    int place=allString.indexOf(target,notePad.getCaretPosition());
                    if(place==-1){
                        message("无此字符串，自动将光标回到文件头").show();
                        notePad.positionCaret(0);
                    }else {
                        message("找到目标，将进行替换").show();
                        notePad.replaceText(place,place+searchField.getLength(),replaceField.getText());
                    }
                }
                catch (Exception e){
                    message("未知错误的查找失败！").show();
                }
            });
            searchPane.getChildren().addAll(searchLabel,searchField,replaceField,searchButton);
            search.setScene(searchScene);
            search.show();
        });
        MenuItem copyIt=new MenuItem("复制");
        MenuItem cutIt=new MenuItem("剪切");
        MenuItem pastaIt=new MenuItem("粘贴");
        copyIt.setOnAction(event -> notePad.copy());
        cutIt.setOnAction(event -> notePad.cut());
        pastaIt.setOnAction(event -> notePad.paste());
        editMenu.getItems().addAll(copyIt,cutIt,pastaIt,searchIt,replaceIt);
        //其他菜单
        Menu otherMenu=new Menu("其他");
        MenuItem question3=new MenuItem("第三题 显示两个程序文件");
        question3.setOnAction(event -> {
            textEditor.setTitle("第三题的演示");
            File file1=new File("./prog1.java");
            File file2=new File("./prog2.java");
            notePad.setText(readTwoFile(file1,file2));
        });
        MenuItem about=new MenuItem("关于");
        about.setOnAction(event ->message("by Uiharu 2020.4.19 我太难了").show());
        otherMenu.getItems().addAll(question3,about);
        //添加到菜单栏
        menuBar.getMenus().addAll(fileMenu,editMenu,otherMenu);

        //菜单部分结束

        //下面是简单的弹出对话框

        textEditor.setScene(scene);
        textEditor.show();
    }
    //一个虚假的弹出窗口，实际上是一个新窗口
    static Stage message(String saySomething){
        Stage result=new Stage();
        Label msg=new Label(saySomething);
        BorderPane pmsg=new BorderPane();
        Scene scene = new Scene(pmsg, 300, 50, Color.WHITE);
        Button exitButton=new Button();
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        pmsg.setCenter(msg);// 放在中间
        result.setScene(scene);
        return result;
    }
    // 读文件方法
    public static String readFile(File file) {
        StringBuilder resultStr = new StringBuilder();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bReader.readLine()) != null) {
                resultStr.append(line + "\r\n");// 记得换行...哦
            }
            bReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr.toString();
    }
    // 第三题
    public static String readTwoFile(File file1,File file2) {
        StringBuilder resultStr = new StringBuilder();
        try {
            InputStream input1 = new FileInputStream(file1);
            InputStream input2 = new FileInputStream(file2);
            SequenceInputStream sReader = new SequenceInputStream(input1, input2);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(sReader));
            String line;
            while ((line = bReader.readLine()) != null) {
                resultStr.append(line + "\r\n");// 记得换行...哦
            }
            sReader.close();
            bReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr.toString();
    }
    // 写文件方法
    public static void writeFile(File file, String str) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
            bWriter.write(str);
            bWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
