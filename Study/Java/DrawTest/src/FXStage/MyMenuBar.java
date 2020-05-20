package FXStage;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class MyMenuBar {
    MenuBar myBar;
    public MyMenuBar(MyCanvas tmpCanvas){
        // 以下是菜单栏的设计
        myBar=new MenuBar();
        // 文件选单
        Menu fileMenu=new Menu();
        fileMenu.setText("文件");
        fileMenu.setStyle("-fx-font-size:16;");
        MenuItem newFile=new MenuItem("新建");
        newFile.setStyle("-fx-font-size:14;");
        newFile.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
        newFile.setOnAction(event -> {
            tmpCanvas.clear();
        });
        MenuItem openImage=new MenuItem("打开");
        openImage.setStyle("-fx-font-size:14;");
        openImage.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        openImage.setOnAction(event -> {
            boolean isOK=true;
            if(!tmpCanvas.getList().isEmpty()) {
                Alert confirmOpen = new Alert(Alert.AlertType.CONFIRMATION,null,new ButtonType("取消", ButtonBar.ButtonData.NO),
                        new ButtonType("确定", ButtonBar.ButtonData.YES));
                confirmOpen.setTitle("数据删除警告");
                confirmOpen.setHeaderText("您当前正在进行的是打开文件操作");
                confirmOpen.initStyle(StageStyle.UTILITY);
                confirmOpen.setContentText("这意味着当前画板内容将被清空");
                Optional<ButtonType> buttonType = confirmOpen.showAndWait();
                // 根据点击结果
                if(!buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
                    isOK = false;
                }
            }
            if(isOK) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("尺寸800*800的PNG文件", "*.png"));
                fileChooser.setInitialDirectory(new File("./我的作品"));
                fileChooser.setTitle("打开图片");// 打开图片
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    try {
                        Image image = new Image(new FileInputStream(file));
                        tmpCanvas.setImage(image);
                        file = null;
                    } catch (Exception ex) {}
                }
            }
        });
        MenuItem saveImage=new MenuItem("保存");
        saveImage.setStyle("-fx-font-size:14;");
        saveImage.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        saveImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./我的作品"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("我的绘画", "*.png"));
            fileChooser.setTitle("保存图片");// 保存图片
            File file = fileChooser.showSaveDialog(new Stage());
            try {
                if (file != null) {
                    ImageIO.write(tmpCanvas.getNewImage(), "PNG", file);
                    file = null;
                }
            } catch (IOException err){}
        });
        fileMenu.getItems().addAll(newFile,openImage,saveImage);
        // 编辑选单
        Menu editMenu = new Menu();
        editMenu.setText("编辑");
        editMenu.setStyle("-fx-font-size:16;");
        MenuItem undo=new MenuItem("撤销");
        undo.setStyle("-fx-font-size:14;");
        undo.setAccelerator(KeyCombination.keyCombination("Ctrl+z"));
        undo.setOnAction(event -> {
            tmpCanvas.undo();
        });
        MenuItem redo=new MenuItem("重做");
        redo.setStyle("-fx-font-size:14;");
        redo.setAccelerator(KeyCombination.keyCombination("Ctrl+y"));
        redo.setOnAction(event -> {
            tmpCanvas.redo();
        });
        editMenu.getItems().addAll(undo,redo);
        // 网络选单
        Menu netMenu=new Menu();
        netMenu.setText("网络");
        netMenu.setStyle("-fx-font-size:16;");
        MenuItem connectNet = new MenuItem("与网络连接");
        connectNet.setStyle("-fx-font-size:14;");
        connectNet.setOnAction(event -> {
            if(MyStatus.networkConnect) {
                connectNet.setText("与网络连接");
                MyStatus.networkConnect = false;
            }
            else{
                connectNet.setText("与网络断开");
                MyStatus.networkConnect = true;
            }
        });
        netMenu.getItems().addAll(connectNet);
        // 添加进menu
        myBar.getMenus().addAll(fileMenu,editMenu,netMenu);
    }
    public MenuBar getMenu(){
        return myBar;
    }

}
