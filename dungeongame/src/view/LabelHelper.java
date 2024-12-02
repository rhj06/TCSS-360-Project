package dungeongame.src.view;

import javafx.scene.control.Label;
import static javafx.scene.text.Font.loadFont;

public class LabelHelper {

    public static Label createLabel(String text, String fontPath, int fontSize, String style) {
        Label label = new Label(text);
        label.setFont(loadFont(fontPath, fontSize));
        label.setStyle(style);
        return label;
    }
}