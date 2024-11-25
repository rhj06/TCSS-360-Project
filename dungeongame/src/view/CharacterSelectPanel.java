package dungeongame.src.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Represents a character panel for the CharacterSelectScreen.
 * Each panel includes an image and a button for selecting a character.
 */
public class CharacterSelectPanel {

    private static final double IMAGE_WIDTH = 200;
    private static final double IMAGE_HEIGHT = 400;

    private static final String FONT_PATH = "file:.idea/resources/fonts/VIKING-N.TTF";

    /**
     * Creates a character panel with an image and a selection button.
     *
     * @param theImagePath   The file path to the character's image.
     * @param theButtonText  The text for the character selection button.
     * @param theSelectAction The action to be performed when the button is clicked.
     * @return A VBox containing the character's image and button.
     */
    public VBox createCharacterPanel(String theImagePath, String theButtonText, Runnable theSelectAction) {
        ImageView imageView = new ImageView(new Image(theImagePath));
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);

        Button selectButton = new Button(theButtonText);
        selectButton.setFont(Font.loadFont(FONT_PATH, 16));
        selectButton.setOnAction(e -> theSelectAction.run());

        VBox panel = new VBox(10, imageView, selectButton);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10));

        return panel;
    }
}