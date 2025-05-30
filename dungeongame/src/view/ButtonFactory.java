package dungeongame.src.view;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * A factory class for creating styled buttons with custom fonts and dimensions.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public final class ButtonFactory {

    /** constant of 16 */
    private static final int SIXTEEN = 16;
    /** The width of the button. */
    private final double myButtonWidth;

    /** The font of the button text. */
    private final Font myFont;

    /**
     * Constructs a ButtonFactory with a custom font.
     *
     * @param theFontPath    the file path to the font (e.g., "file:src/resources/fonts/MyFont.ttf")
     * @param theFontSize    the size of the font
     * @param theButtonWidth the minimum width for the button
     */
    public ButtonFactory(final String theFontPath, final double theFontSize, final double theButtonWidth) {
        Font font = Font.loadFont(theFontPath, theFontSize);
        if (font == null) {
            System.err.println("Font loading failed for: " + theFontPath + ". Using default font.");
            font = Font.font("Arial", theFontSize);
        }
        myFont = font;
        myButtonWidth = theButtonWidth;
    }

    /**
     * Constructs a ButtonFactory with the default system font.
     *
     * @param theButtonWidth the minimum width for the button
     */
    public ButtonFactory(final double theButtonWidth) {
        this(Font.font("Arial", SIXTEEN), theButtonWidth);
    }

    /**
     * Private constructor for internal use when a Font object is already available.
     *
     * @param theFont        the Font to be used for buttons
     * @param theButtonWidth the minimum width for the button
     */
    private ButtonFactory(final Font theFont, final double theButtonWidth) {
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
    public Button createButton(final String theText, final Runnable theAction) {
        Button myButton = new Button(theText);
        myButton.setFont(myFont);
        myButton.setMinWidth(myButtonWidth);
        myButton.setOnAction(_ -> theAction.run());
        return myButton;
    }
}