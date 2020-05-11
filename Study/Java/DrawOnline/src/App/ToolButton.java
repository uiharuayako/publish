package App;

import icon.IconImage;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

// 这是自定义的按钮类，这个按钮只有一个作用---改变信息
public class ToolButton extends Button {
    ToolButton(String name){
        this.setGraphic(getImageView(IconImage.getImage(name)));//设定按钮外观
        this.setOnAction(event -> {
            Status.toolName=name;//设定当前工具
        });
        this.setStyle("-fx-base:#aaaaaa;");
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            this.setEffect(new DropShadow());
        });

        this.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            this.setEffect(null);
        });
    }
    ImageView getImageView(Image curImg) {
        ImageView curImgV = new ImageView(curImg);
        curImgV.setFitHeight(30);
        curImgV.setFitWidth(30);
        return curImgV;
    }
}
