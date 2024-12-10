package dungeongame.src.view;

import dungeongame.src.controller.GameSaver;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuBar {

    private final Stage myStage;

    public MenuBar(Stage theStage) {
        myStage = theStage;
    }

    public VBox createMenuBar() {
        MenuButton menuButton = new MenuButton("Menu");
        menuButton.setStyle("-fx-font-size: 16px;");

        MenuItem saveGame = new MenuItem("Save Game");
        saveGame.setOnAction(e -> {save();
            System.out.println("Game saved!");});

        MenuItem quitGame = new MenuItem("Quit");
        quitGame.setOnAction(e -> myStage.close());

        menuButton.getItems().addAll(saveGame, quitGame);

        VBox menuBox = new VBox(menuButton);
        menuBox.setStyle("-fx-alignment: top-left; -fx-padding: 10;");
        return menuBox;
    }

    public void save() {
        GameSaver.getInstance().saveGame();
    }

}