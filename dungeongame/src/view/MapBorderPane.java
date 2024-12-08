package dungeongame.src.view;

import dungeongame.src.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import java.util.ArrayList;
import java.util.List;

/**
 * MapBorderPane is a BorderPane
 * Creates a grid base Map where tiles can be inserted and dragged around to create map of the players maze
 * Can also be used to make silly shapes :)
 */
public class MapBorderPane extends BorderPane {

    /**Grid size constant*/
    private static final int GRID_SIZE = 50;
    /**The Difference of BorderPanes height and Grid height*/
    private static final int BORDERPANE_TO_GRID_DIFFERENCE = 150;
    /**Instance of a Draggable Grid*/
    private DraggableGrid draggableGridBase;
    /**List of MapTiles*/
    private final MapTileList myTiles;
    /**BorderPane Width and Height*/
    private final double myWidth, myHeight;
    /**LastDraggedTile object*/
    private final LastDraggedTile myLastDraggedTile;

    /**
     * MapBorderPane Constructor
     * @param theWidth BorderPane Width
     * @param theHeight BorderPane Height
     */
    public MapBorderPane(final double theWidth, final double theHeight) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myTiles = MapTileList.getInstance();
        myLastDraggedTile = new LastDraggedTile(null);
        defaultLayout();
    }

    /**
     * Default Layout of MapBorderPane
     */
    private void defaultLayout() {
        this.setStyle("-fx-background-color: black;"); //Set Background to black

        // create instance of draggable grid over the current borderpane
        draggableGridBase = new DraggableGrid(myWidth, myHeight - BORDERPANE_TO_GRID_DIFFERENCE,
                GRID_SIZE, this);
        GridHandler backgroundGridHandler = new GridHandler(myWidth, myHeight - BORDERPANE_TO_GRID_DIFFERENCE,
                GRID_SIZE, this);
        backgroundGridHandler.createGrid();

        // create buttons for creating new tiles and basic functions to edit the map
        VBox buttonTiles = buttonTiles();
        VBox buttonFunctions = buttonFunctions();
        buttonTiles.setAlignment(Pos.TOP_RIGHT);
        buttonFunctions.setAlignment(Pos.BOTTOM_RIGHT);
        this.setRight(buttonTiles);
        this.setBottom(buttonFunctions);

    }

    /**
     * Creates a VBox containing 5 buttons for each unique tile
     * @return VBox of tile buttons
     */
    private VBox buttonTiles() {
        return new VBox(
                createTileButton("One Way"),
                createTileButton("Two Way Adjacent"),
                createTileButton("Two Way Across"),
                createTileButton("Three Way"),
                createTileButton("Four Way")
        );
    }

    /**
     * Creates a VBox containing 3 buttons for basic functions for editing/deleting tiles
     * @return VBox of function buttons of tile rotations and delete tile
     */
    private VBox buttonFunctions() {
        return new VBox(
                deleteButton(),
                rightRotationButton(),
                leftRotationButton()
        );
    }

    /**
     * Creates a Tile Button
     * @param theText Name of Button/Tile
     * @return Button that creates a new MapTile at coordinates (0,0)
     */
    private Button createTileButton(final String theText) {
        Button button = new Button(theText);
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            MapTile tile = createTile(theText);
            tile.setFill(new ImagePattern(new Image("file:map_tile_images/" + tile.getImageFileName())));
            myTiles.add(tile);
            this.getChildren().add(tile);
            myLastDraggedTile.setMyTile(tile);
            draggableGridBase.makeDraggable(tile, myLastDraggedTile);

        });
        return button;
    }

    /**
     * Creates a new MapTile matching theTileType String
     * @param theTileName Name of Tile
     * @return new Instance of a MapTile matching theTileName param
     */
    private MapTile createTile(final String theTileName) {
        return switch (theTileName) {
            case "One Way" -> new One_Way_Tile(0, 0);
            case "Two Way Adjacent" -> new Two_Way_Tile_Adjacent(0, 0);
            case "Two Way Across" -> new Two_Way_Tile_Across(0, 0);
            case "Three Way" -> new Three_Way_Tile(0, 0);
            default -> new Four_Way_Tile(0, 0);
        };
    }

    /**
     * Creates a delete Button
     * @return Button that deletes the instance of the LastDraggedTile from the MyTiles ArrayList or
     *  Deletes the last tile created
     */
    private Button deleteButton() {
        Button button = new Button("Delete");
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            if (!myTiles.isEmpty() && myLastDraggedTile.getMyTile() != null) {
                boolean tile = myTiles.remove(myLastDraggedTile.getMyTile());
                if (tile) {
                    this.getChildren().remove(myLastDraggedTile.getMyTile());
                }
                if (!myTiles.isEmpty()) {
                    myLastDraggedTile.setMyTile(myTiles.getLast());
                } else {
                    myLastDraggedTile.setMyTile(null);
                }
            }
        });
        return button;
    }

    /**
     * Creates a Clockwise Rotation button for tiles
     * @return Button that rotates the tile Clockwise 90 degrees
     */
    private Button rightRotationButton() {
        Button button = new Button("Right Rotation");
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            if (myTiles.contains(myLastDraggedTile.getMyTile())) {
                myTiles.get(myTiles.indexOf(myLastDraggedTile.getMyTile())).rotateClockwise();
            }
        });

        return button;
    }

    /**
     * Creates a Counterclockwise Rotation button for tiles
     * @return Button that rotates the tile Counterclockwise 90 degrees
     */
    private Button leftRotationButton() {
        Button button = new Button("Left Rotation");
        button.setMinWidth(150);
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setOnAction(e -> {
            if (myTiles.contains(myLastDraggedTile.getMyTile())) {
                myTiles.get(myTiles.indexOf(myLastDraggedTile.getMyTile())).rotateCounterClockwise();
            }
        });

        return button;
    }
}
