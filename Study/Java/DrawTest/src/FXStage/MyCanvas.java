package FXStage;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;// 这个...不算awt吧
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
//这是一个画板，把许多画布合成而来，同时，包括一个状态栏。
public class MyCanvas {
    //鼠标按下位置
    private double x1;
    private double y1;
    //鼠标松开位置
    private double x2;
    private double y2;
    //绘制多边形使用的特殊鼠标位置
    private double[] x;
    private double[] y;
    private int numPoints;
    //画板相关
    private Group content;
    private VBox vbox;
    private Canvas drawingCanvas;
    private GraphicsContext gc;
    public static int drawingCanvasWidth;
    public static int drawingCanvasHeight;
    private List<Canvas> listCanvas;
    //网络相关
    Socket mySocket;
    //状态栏相关
    private Label curPos;
    private Label info;
    private Label polyLabel;
    private NetLabel netLabel;
    class NetLabel extends Label{
        NetLabel() {
            this.setFont(Font.font ("Microsoft YaHei", 16));
            this.setAlignment(Pos.CENTER_LEFT);
            update();
        }
        void update() {
            if(mySocket != null){
                MyStatus.isConnected = mySocket.isConnected();
            }
            this.setText("网络 "+(MyStatus.networkConnect?"开启":"关闭")+(MyStatus.isConnected?" 已连接":" 未连接"));
        }
    }
    private HBox statusBar;
    //重做相关
    private Canvas newCanvas;
    public MyCanvas(){
        //网络声明
        mySocket = null;
        newCanvas = null;
        //状态栏声明
        numPoints=0;
        statusBar=new HBox();
        polyLabel=new Label("多边形状态栏");
        info=new Label("工具状态栏");
        curPos=new Label("欢迎使用画板");
        netLabel=new NetLabel();
        polyLabel.setFont(Font.font ("Microsoft YaHei", 16));
        polyLabel.setAlignment(Pos.CENTER);
        info.setFont(Font.font ("Microsoft YaHei", 16));
        info.setAlignment(Pos.CENTER);
        curPos.setFont(Font.font ("Microsoft YaHei", 16));
        curPos.setAlignment(Pos.CENTER_RIGHT);
        //以下是画板
        content = new Group();
        vbox = new VBox();
        x=new double[20];
        y=new double[20];
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 20, 0, 0));
        vbox.getChildren().add(content);
        drawingCanvas = new Canvas(800,800);
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800,800);
        gc.restore();
        content.getChildren().add(drawingCanvas);

        setStatus(drawingCanvas, MyStatus.color, false);
        drawingCanvasWidth = (int) drawingCanvas.getWidth();
        drawingCanvasHeight = (int) drawingCanvas.getHeight();
        listCanvas = new ArrayList<>();
        // 加载默认图片
        try {
            File asImageFile = new File("./我的作品/AutoSave.png");
            if (asImageFile.exists()) {
                Image image = new Image(new FileInputStream(asImageFile));
                setImage(image);
            }
        }catch (IOException e){}
        //以下是绘图主函数
        //当鼠标移动
        drawingCanvas.setOnMouseMoved(event -> {
            if(numPoints<=9) polyLabel.setText("多边形状态: 边数:0"+numPoints+"/20  "+(MyStatus.drawPoly?"下次按键绘图":"下次按键记录"));
            else polyLabel.setText("多边形状态: 边数:"+numPoints+"/20  "+(MyStatus.drawPoly?"下次按键绘图":"下次按键记录"));
            info.setText("当前工具:"+ MyStatus.toolName+" 当前文字:"+ MyStatus.myText);
            curPos.setText(String.format("(%03d, %03d)", (int)event.getX(), (int)event.getY()));
        });
        drawingCanvas.setOnMouseExited(event -> {
            curPos.setText("(000, 000)");
        });
        //当鼠标按下
        drawingCanvas.setOnMousePressed(event -> {
            Canvas c = new Canvas(drawingCanvasWidth, drawingCanvasHeight);
            gc = c.getGraphicsContext2D();
            //获取鼠标位置
            x1 = event.getX();
            y1 = event.getY();
            //将Canvas和GraphicsContext的鼠标位置同步以实现多次绘图
            c.setOnMousePressed(drawingCanvas.getOnMousePressed());
            c.setOnMouseDragged(drawingCanvas.getOnMouseDragged());
            c.setOnMouseReleased(drawingCanvas.getOnMouseReleased());
            c.setOnMouseMoved(drawingCanvas.getOnMouseMoved());
            c.setOnMouseExited(drawingCanvas.getOnMouseExited());
            if (MyStatus.toolName.equals("OVAL") || MyStatus.toolName.equals("RECTANGLEZ") || MyStatus.toolName.equals("RECTANGLEY")) {
                if (!MyStatus.fill) {
                    gc.setLineWidth(MyStatus.lineSize);
                    setStatus(c, MyStatus.color, false);
                } else if (MyStatus.fill) {
                    gc.setLineWidth(MyStatus.lineSize);
                    setStatus(c, MyStatus.color, true);
                }
            }else if(MyStatus.toolName.equals("BARREL")){
                setStatus(c, MyStatus.color, true);
            }else{
                gc.setLineWidth(MyStatus.lineSize);
                setStatus(c, MyStatus.color, false);
            }

            if (MyStatus.toolName.equals("RUBBER"))
                gc.setStroke(Color.WHITE);

            //这是个特例哈，因为可以直接绘出文字
            if(MyStatus.toolName.equals("TEXT")){
                gc.setLineWidth(1);
                gc.setFont(Font.font(MyStatus.fontFamily, MyStatus.fontSize));
                gc.setStroke(MyStatus.color);
                gc.strokeText(MyStatus.myText, event.getX(), event.getY());
            }
            if(MyStatus.toolName.equals("POLYGON")){
                if(MyStatus.drawPoly) {
                    if(MyStatus.fill){
                        gc.fillPolygon(x, y, numPoints);
                    }
                    else{
                        gc.strokePolygon(x, y, numPoints);
                    }
                    x=new double[20];
                    y=new double[20];
                    numPoints=0;
                }
                else{
                x[numPoints]=x1;//放入一个点
                y[numPoints]=y1;
                numPoints++;
                }
            }
            listCanvas.add(c);
            content.getChildren().add(c);
            autoSave();
        });
        //当鼠标拖动，触发事件
        drawingCanvas.setOnMouseDragged(event -> {
            curPos.setText(String.format("%.1f, %.1fpx ", event.getX(), event.getY()));
            if (MyStatus.toolName.equals("PEN") || MyStatus.toolName.equals("RUBBER")) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });
        //当鼠标释放，绘出图形
        drawingCanvas.setOnMouseReleased(event -> {
            //再次获取鼠标位置
            x2 = event.getX();
            y2 = event.getY();
            double width = x2 - x1;
            double height = y2 - y1;
            if (MyStatus.toolName.equals("LINE")) {
                gc.moveTo(x1, y1);
                gc.lineTo(x2, y2);
                gc.stroke();
            } else if (MyStatus.toolName.equals("OVAL")) {
                if (width < 0) {
                    width = -width;
                    x1 = x1 - width;
                }
                if (height < 0) {
                    height = -height;
                    y1 = y1 - height;
                }
                if(MyStatus.fill)
                    gc.fillOval(x1, y1, width, height);
                else
                    gc.strokeOval(x1, y1, width, height);
            } else if (MyStatus.toolName.equals("RECTANGLEZ")) {
                if (width < 0) {
                width = -width;
                x1 = x1 - width;
            }
                if (height < 0) {
                    height = -height;
                    y1 = y1 - height;
                }
                if(MyStatus.fill)
                    gc.fillRect(x1, y1, width, height);
                else
                    gc.strokeRect(x1, y1, width, height);
            } else if (MyStatus.toolName.equals("RECTANGLEY")) {
                if (width < 0) {
                    width = -width;
                    x1 = x1 - width;
                }
                if (height < 0) {
                    height = -height;
                    y1 = y1 - height;
                }
                if(MyStatus.fill)
                    gc.fillRoundRect(x1, y1, width, height, 30, 30);
                else
                    gc.strokeRoundRect(x1, y1, width, height, 30, 30);
            } else if (MyStatus.toolName.equals("BARREL")) {
                gc.fillRect(0,0,drawingCanvasWidth, drawingCanvasHeight);
            }
            gc.stroke();
            autoSave();
        });

    }
    public void setImage(Image newImage){
        gc.drawImage(newImage,0,0);
        gc.restore();
        listCanvas.add(drawingCanvas);
        autoSave();
    }
    // 清理门户
    public void clear() {
        Canvas c = new Canvas(drawingCanvasWidth, drawingCanvasHeight);
        gc = c.getGraphicsContext2D();
        // 填一张白色
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800,800);
        gc.restore();
        listCanvas.add(c);
        content.getChildren().add(c);
    }
    // 这个函数旨在快速填充参数
    void setStatus(Canvas c, Color color, boolean f){
        gc = c.getGraphicsContext2D();
        if(f)
            gc.setFill(color);
        else
            gc.setStroke(color);
    }
    // 这个函数完成了撤销操作，这也是用链表的意义
    void undo() {
        if (!listCanvas.isEmpty()) {
            newCanvas = listCanvas.get(listCanvas.size()-1);
            content.getChildren().remove(newCanvas);
            listCanvas.remove(listCanvas.size()-1);
            autoSave();
        }
    }
    // 重做，与之前相似
    void redo() {
        if (newCanvas != null) {
            listCanvas.add(newCanvas);
            content.getChildren().add(newCanvas);
            newCanvas = null;
            autoSave();
        }
    }
    // 用来判断是否为空
    public List<Canvas> getList() {
        return listCanvas;
    }
    public VBox getCanvas() {
        return vbox;
    }
    public HBox getStatusBar(){
        statusBar.getChildren().addAll(info,new Separator(),polyLabel,new Separator(),curPos);
        statusBar.setAlignment(Pos.CENTER);
        statusBar.setSpacing(50);
        statusBar.setPadding(new Insets(5, 30, 5, 100));
        return statusBar;
    }
    // 获取当前
    public RenderedImage getNewImage() {
        // 以下代码，旨在合成一个图层
        Canvas oneCanvas=new Canvas(800,800);
        // 新建一个快照
        SnapshotParameters mySP=new SnapshotParameters();
        mySP.setFill(Color.TRANSPARENT);// 设一个透明背景，这是必定
        for(Canvas thisCanvas:listCanvas){
            // 这个在其他地方感觉没啥用的功能难道是专门为了画板设计的吗...
            WritableImage thisImage=thisCanvas.snapshot(mySP,null);
            // 写入主图层
            oneCanvas.getGraphicsContext2D().drawImage(thisImage,0,0);
        }
        WritableImage myWI=new WritableImage(800,800);
        oneCanvas.snapshot(null,myWI);
        return SwingFXUtils.fromFXImage(myWI,null);
    }
    public void autoSave() {
        File imageFile = new File("./我的作品/AutoSave.png");
        try {
            ImageIO.write(getNewImage(), "PNG", imageFile);
        } catch (IOException err){}
        if(MyStatus.networkConnect) {
            FileInputStream myFIS = null;
            OutputStream os = null;
            // 缓冲
            byte[] buffer = new byte[4096 * 5];
            try {
                mySocket = new Socket("127.0.0.1", 4004);
                os = mySocket.getOutputStream();
                MyStatus.isConnected = true;
                netLabel.update();
            }catch (IOException e) {
                MyStatus.isConnected = false;
                netLabel.update();
            }
            try {
                myFIS = new FileInputStream(imageFile);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                assert mySocket != null;
                PrintStream myPS = new PrintStream(mySocket.getOutputStream());
                // 命令格式：sync$id$文件长度$当前工具
                //         join$id$你的昵称
                //         stop$id
                myPS.println("sync$" + MyStatus.id + "$" + myFIS.available() + "$" + MyStatus.toolName);
                myPS.flush();
            } catch (IOException e) {
                MyStatus.isConnected = false;
                netLabel.update();
            }
            try {
                int size = 0;
                // 循环读取
                while((size = myFIS.read(buffer)) != -1){
                    netLabel.setText("发送数据包，大小为" + size);
                    os.write(buffer, 0, size);
                    os.flush();
                    myFIS.close();
                }
            } catch (Exception e){
                MyStatus.isConnected = false;
                netLabel.update();
            }
        }
    }

}
