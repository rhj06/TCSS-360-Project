package dungeongame.src.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Utility class for creating and customizing {@link Label} elements in a JavaFX application.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public class LabelHelper {

    /**
     * Creates a {@link Label} with the specified text, font, size, and style.
     *
     * @param theText    The text to display in the label.
     * @param theFontPath The path to the font file.
     * @param theFontSize The size of the font.
     * @param theStyle    The CSS style to apply to the label.
     * @return A {@link Label} configured with the specified properties.
     */
    public static Label createLabel(String theText, String theFontPath, int theFontSize, String theStyle) {
        Font font = loadFont(theFontPath, theFontSize);
        Label label = new Label(theText);
        label.setFont(font);
        label.setStyle(theStyle);

        return label;
    }

    /**
     * Creates a centered {@link Label} with the specified text, font, size, and style.
     * The label's alignment is set to {@link Pos#CENTER}.
     *
     * @param theText    The text to display in the label.
     * @param theFontPath The path to the font file.
     * @param theFontSize The size of the font.
     * @param theStyle    The CSS style to apply to the label.
     * @return A centered {@link Label} configured with the specified properties.
     */
    public static Label createCenteredLabel(String theText, String theFontPath, int theFontSize, String theStyle) {
        Label label = createLabel(theText, theFontPath, theFontSize, theStyle);
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);

        return label;
    }

    /**
     * Loads a {@link Font} from the specified file path and size.
     * Falls back to the default "Arial" font if the specified font cannot be loaded.
     *
     * @param theFontPath The path to the font file.
     * @param theFontSize The size of the font.
     * @return A {@link Font} loaded from the specified file or the default font if loading fails.
     */
    private static Font loadFont(String theFontPath, int theFontSize) {
        try {
            Font font = Font.loadFont(theFontPath, theFontSize);
            if (font == null) {
                throw new RuntimeException("Font not loaded: " + theFontPath);
            }
            return font;
        } catch (Exception e) {
            System.out.println("Failed to load font: " + theFontPath + ". Falling back to default.");

            return Font.font("Arial", theFontSize);
        }
    }
}