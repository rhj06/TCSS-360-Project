package dungeongame.src.view;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class RadioButtonHelper {

    private final RadioButton easyButton;
    private final RadioButton normalButton;
    private final RadioButton hardButton;

    public RadioButtonHelper() {
        easyButton = createRadioButton("Easy");
        normalButton = createRadioButton("Normal");
        hardButton = createRadioButton("Hard");

        ToggleGroup difficultyGroup = new ToggleGroup();
        easyButton.setToggleGroup(difficultyGroup);
        normalButton.setToggleGroup(difficultyGroup);
        hardButton.setToggleGroup(difficultyGroup);

        normalButton.setSelected(true); // Default selection
    }

    public HBox createDifficultyButtons() {
        HBox layout = new HBox(20, easyButton, normalButton, hardButton);
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    public int getSelectedMazeSize() {
        if (easyButton.isSelected()) return 5;
        if (hardButton.isSelected()) return 7;
        return 6; // Default to "Normal"
    }

    private RadioButton createRadioButton(String text) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setStyle(String.format("-fx-text-fill: %s;", "gray"));
        return radioButton;
    }
}