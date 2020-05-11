package App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MyFonts {
    private VBox fontAndSize;// 防止外部更改
    MyFonts(){
        VBox fontAndSize=new VBox();
        fontAndSize.setAlignment(Pos.CENTER);//居中对齐
        //字体下拉选择框
        ComboBox<String> fontFamily=new ComboBox<String>();
        ObservableList<String> fontFamilyItems = FXCollections.observableArrayList();
        for (int i = 0; i < Font.getFamilies().size(); i++) {
            fontFamilyItems.add(Font.getFamilies().get(i));
        }
        fontFamily.setStyle("-fx-base:#888888;-fx-background-color:#666666;");
        fontFamily.setPrefWidth(90);
        fontFamily.setItems(fontFamilyItems);
        fontFamily.getSelectionModel().select(0);
        fontFamily.valueProperty().addListener((ov, oldv, newv) -> {
            Status.setFontFamily(newv);
        });
        //设置大小，包括线条粗细，字体大小
        Label sLabel=new Label("1-36");
        Slider sizeS=new Slider();
        sizeS.setMax(36);
        sizeS.setMin(1);
        sizeS.addEventHandler(MouseEvent.MOUSE_DRAGGED,event -> {
            Status.lineSize=sizeS.getValue();
            Status.fontSize=sizeS.getValue()+7;//这样设应该比较自然
        });
        fontAndSize.getChildren().addAll(fontFamily,sLabel,sizeS);
    }
   public VBox getFontsPane() {return fontAndSize;}
}
