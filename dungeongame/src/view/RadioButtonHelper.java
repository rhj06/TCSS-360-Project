package dungeongame.src.view;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * Helper class to create and manage difficulty selection radio buttons.
 *
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 *
 */
public class RadioButtonHelper {

    private final RadioButton myEasyButton;
    private final RadioButton myNormalButton;
    private final RadioButton myHardButton;

    /**
     * Constructor initializes radio buttons and their toggle group, with "Normal" as default.
     */
    public RadioButtonHelper() {
        ToggleGroup myDifficultyGroup = new ToggleGroup();
        myEasyButton = createRadioButton("Easy");
        myNormalButton = createRadioButton("Normal");
        myHardButton = createRadioButton("Hard");

        myEasyButton.setToggleGroup(myDifficultyGroup);
        myNormalButton.setToggleGroup(myDifficultyGroup);
        myHardButton.setToggleGroup(myDifficultyGroup);
        myNormalButton.setSelected(true);
    }

    /**
     * Creates and returns an HBox containing the difficulty buttons.
     *
     * @return HBox with Easy, Normal, Hard buttons in that order.
     */
    public HBox createDifficultyButtons() {
        HBox layout = new HBox(20, myEasyButton, myNormalButton, myHardButton);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    /**
     * Returns the maze size corresponding to the selected difficulty.
     *
     * @return 5 for Easy, 7 for Hard, and 6 for Normal.
     */
    public int getSelectedMazeSize() {
        if (myEasyButton.isSelected()) return 5;
        if (myHardButton.isSelected()) return 7;

        return 6;
    }

    /**
     * Helper method to create a styled RadioButton.
     *
     * @param theText The label of the RadioButton.
     * @return A styled RadioButton.
     */
    private RadioButton createRadioButton(String theText) {
        RadioButton radioButton = new RadioButton(theText);
        radioButton.setStyle("-fx-text-fill: gray;");

        return radioButton;
    }
}