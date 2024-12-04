package dungeongame.src.view;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * A factory class for creating styled buttons with custom fonts and dimensions.
 */
public class ButtonFactory {
    private final double myButtonWidth;
    private final Font myFont;

    /**
     * Constructs a ButtonFactory with a custom font.
     *
     * @param theFontPath    the file path to the font (e.g., "file:src/resources/fonts/MyFont.ttf")
     * @param theFontSize    the size of the font
     * @param theButtonWidth the minimum width for the button
     */
    public ButtonFactory(String theFontPath, double theFontSize, double theButtonWidth) {
        Font font = Font.loadFont(theFontPath, theFontSize); // Attempt to load the font
        if (font == null) {
            System.err.println("Font loading failed for: " + theFontPath + ". Using default font.");
            font = Font.font("Arial", theFontSize); // Fallback to default system font
        }
        myFont = font;
        myButtonWidth = theButtonWidth;
    }

    /**
     * Constructs a ButtonFactory with the default system font.
     *
     * @param theButtonWidth the minimum width for the button
     */
    public ButtonFactory(double theButtonWidth) {
        this(Font.font("Arial", 16), theButtonWidth); // Use Arial with default size
    }

    /**
     * Private constructor for internal use when a Font object is already available.
     *
     * @param theFont        the Font to be used for buttons
     * @param theButtonWidth the minimum width for the button
     */
    private ButtonFactory(Font theFont, double theButtonWidth) {
        myFont = theFont;
        myButtonWidth = theButtonWidth;
    }

    /**
     * Creates a styled button with the specified text and action.
     *
     * @param theText   the text to display on the button
     * @param theAction the action to perform when the button is clicked
     * @return a Button styled with the factory's properties
     */
    public Button createButton(String theText, Runnable theAction) {
        Button myButton = new Button(theText);
        myButton.setFont(myFont); // Apply the preloaded font
        myButton.setMinWidth(myButtonWidth); // Set minimum width
        myButton.setOnAction(e -> theAction.run()); // Set action
        return myButton;
    }
}