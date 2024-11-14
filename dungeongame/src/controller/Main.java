package dungeongame.src.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dungeongame.src.view.MainMenu;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        MainMenu mainMenu = new MainMenu();
        Scene mainMenuScene = mainMenu.createMainMenuScene(primaryStage);
        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Dungeon Adventure");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}