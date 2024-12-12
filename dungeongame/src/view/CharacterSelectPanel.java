package dungeongame.src.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Represents a character selection panel containing an image and a button
 * for selecting a specific character.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public class CharacterSelectPanel {

    /** width of the character image */
    private static final double IMAGE_WIDTH = 225;

    /** height of the character image */
    private static final double IMAGE_HEIGHT = 370;

    /** Factory instance for creating buttons with a consistent style. */
    private final ButtonFactory myButtonFactory;

    /**
     * Constructs a CharacterSelectPanel instance.
     *
     * @param theButtonFactory the factory used to create buttons for the panel
     */
    public CharacterSelectPanel(ButtonFactory theButtonFactory) {
        myButtonFactory = theButtonFactory;
    }

    /**
     * Creates a character selection panel containing an image and a button.
     *
     * @param theImagePath the file path to the character's image
     * @param theButtonText the text to display on the button
     * @param theAction the action to perform when the button is clicked
     * @return a VBox containing the image and the button
     */
    public VBox createCharacterPanel(String theImagePath, String theButtonText, Runnable theAction) {
        ImageView myImageView = new ImageView(new Image(theImagePath));
        myImageView.setFitWidth(IMAGE_WIDTH);
        myImageView.setFitHeight(IMAGE_HEIGHT);

        Button mySelectButton = myButtonFactory.createButton(theButtonText, theAction);
        mySelectButton.prefWidthProperty().bind(myImageView.fitWidthProperty());

        VBox myPanel = new VBox(10, myImageView, mySelectButton);
        myPanel.setAlignment(Pos.CENTER);
        myPanel.setPadding(new Insets(10));
        myPanel.setStyle("-fx-background-color: rgb(96, 96, 96); -fx-border-color: black; -fx-border-width: 2;");

        return myPanel;
    }
}