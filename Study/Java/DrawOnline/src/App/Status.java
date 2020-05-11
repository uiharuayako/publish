package App;

import javafx.scene.paint.Color;

// 记录当前窗口的状态，使用的工具情况，线的粗细等
public class Status {
    static String toolName = "Pen";
    static double lineSize = 7;
    static double fontSize = 12;
    static String fontFamily = "AIGDT";
    static Color color = Color.BLACK;
    static String Text = "";
    static void setToolName(String name){
        Status.toolName = name;
    }
    static void setLineSize(double size){
        Status.lineSize = size;
    }
    static void setFontSize(double size){
        Status.fontSize = size;
    }
    static void setFontFamily(String font){
        Status.fontFamily = font;
    }
    static void setColor(Color c){
        Status.color = c;
    }
    static void setText(String s){
        Status.Text = s;
    }
}
