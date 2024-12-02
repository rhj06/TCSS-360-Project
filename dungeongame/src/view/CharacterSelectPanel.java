package dungeongame.src.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CharacterSelectPanel {

    private static final double IMAGE_WIDTH = 225;
    private static final double IMAGE_HEIGHT = 370;
    private static final String FONT_PATH = "file:.idea/resources/fonts/VIKING-N.TTF";

    public VBox createCharacterPanel(String theImagePath, String theButtonText, Runnable theSelectAction) {
        ImageView imageView = new ImageView(new Image(theImagePath));
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);

        Button selectButton = new Button(theButtonText);
        selectButton.setFont(Font.loadFont(FONT_PATH, 16));
        selectButton.setOnAction(e -> theSelectAction.run());
        selectButton.prefWidthProperty().bind(imageView.fitWidthProperty());

        VBox panel = new VBox(10, imageView, selectButton);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-background-color: rgb(96, 96, 96); -fx-border-color: black; -fx-border-width: 2;");

        return panel;
    }
}