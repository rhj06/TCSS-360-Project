package dungeongame.src.view;

import dungeongame.src.controller.Arena;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;

public class ArenaBorderPane extends BorderPane {
    /**BorderPane Width and Height*/
    private final double myWidth, myHeight;

    private Arena myArena;

    /**
     * MapBorderPane Constructor
     * @param theWidth BorderPane Width
     * @param theHeight BorderPane Height
     */
    public ArenaBorderPane(final double theWidth, final double theHeight, Arena theArena) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myArena = theArena;
        defaultLayout();
    }

    /**
     * Default Layout of MapBorderPane
     */
    private void defaultLayout() {
        this.setStyle("-fx-background-color: black;"); //Set Background to black

        // create buttons for creating new tiles and basic functions to edit the map
        VBox buttonFunctions = buttonFunctions();
        buttonFunctions.setAlignment(Pos.BOTTOM_RIGHT);
        this.setBottom(buttonFunctions);

    }

    /**
     * Creates a VBox containing 3 buttons for basic functions for editing/deleting tiles
     * @return VBox of function buttons of tile rotations and delete tile
     */
    private VBox buttonFunctions() {
        return new VBox(
                attackButton(),
                healthPotionButton(),
                specialButton(),
                debugButton()
        );
    }

    /**
     * Creates an attack Button
     * @return Button that deletes the instance of the LastDraggedTile from the MyTiles ArrayList or
     *  Deletes the last tile created
     */
    private Button attackButton() {
        Button button = new Button("Delete");
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            myArena.setPlayerMove(0);
        });
        return button;
    }

    /**
     * Creates a delete Button
     * @return Button that deletes the instance of the LastDraggedTile from the MyTiles ArrayList or
     *  Deletes the last tile created
     */
    private Button healthPotionButton() {
        Button button = new Button("Delete");
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            myArena.setPlayerMove(1);
        });
        return button;
    }

    /**
     * Creates a delete Button
     * @return Button that deletes the instance of the LastDraggedTile from the MyTiles ArrayList or
     *  Deletes the last tile created
     */
    private Button specialButton() {
        Button button = new Button("Delete");
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            myArena.setPlayerMove(2);
        });
        return button;
    }

    /**
     * Creates a delete Button
     * @return Button that deletes the instance of the LastDraggedTile from the MyTiles ArrayList or
     *  Deletes the last tile created
     */
    private Button debugButton() {
        Button button = new Button("Delete");
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            myArena.setPlayerMove(3);
        });
        return button;
    }
}
