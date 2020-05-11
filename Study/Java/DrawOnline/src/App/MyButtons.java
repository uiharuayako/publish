package App;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.List;

//设定按钮
public class MyButtons {
    private TilePane buttonBox;
    MyButtons(){
        buttonBox = new TilePane();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10,10,10,10));
        buttonBox.setPrefColumns(2);
        buttonBox.setHgap(5);
        buttonBox.setVgap(5);
        String[] buttons={"PEN","RUBBER","BARREL","TEXT","LINE","RECTANGLEY","RECTANGLEZ","POLYGON","ARC"};
    }
}
