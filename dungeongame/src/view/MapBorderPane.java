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

/**
 * MapBorderPane is a BorderPane
 * Creates a grid base Map where tiles can be inserted and dragged around to create map of the players maze
 * Can also be used to make silly shapes :)
 */
public final class MapBorderPane extends BorderPane {

    /** Constant of 10 */
    private static final int TEN = 10;

    /** Constant of 20 */
    private static final int TWENTY = 20;

    /** Constant of 150 */
    private static final int ONE_HUNDRED_FIFTY = 150;

    /**Grid size constant*/
    private static final int GRID_SIZE = 50;
    /**The Difference of BorderPanes height and Grid height*/
    private static final int BORDERPANE_TO_GRID_DIFFERENCE = 150;
    /**Instance of a Draggable Grid*/
    private DraggableGrid myDraggableGridBase;
    /**List of MapTiles*/
    private final ArrayList<MapTile> myTiles;
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
        myTiles = MapTileList.getInstance().getList();
        myLastDraggedTile = new LastDraggedTile(null);
        defaultLayout();
    }

    /**
     * Default Layout of MapBorderPane
     */
    private void defaultLayout() {
        this.setStyle("-fx-background-color: black;"); //Set Background to black

        // create instance of draggable grid over the current borderpane
        myDraggableGridBase = new DraggableGrid(myWidth, myHeight - BORDERPANE_TO_GRID_DIFFERENCE,
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

        if(!myTiles.isEmpty()) {
            for(MapTile tile : myTiles) {
                this.getChildren().add(tile);
                myLastDraggedTile.setMyTile(tile);
                myDraggableGridBase.makeDraggable(tile, myLastDraggedTile);
            }
        }
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
        button.setMinWidth(ONE_HUNDRED_FIFTY);
        button.setPadding(new Insets(TEN, TWENTY, TEN, TWENTY));
        button.setOnAction(_ -> {
            MapTile tile = createTile(theText);
            tile.setFill(new ImagePattern(new Image("file:map_tile_images/" + tile.getImageFileName())));
            myTiles.add(tile);
            this.getChildren().add(tile);
            myLastDraggedTile.setMyTile(tile);
            myDraggableGridBase.makeDraggable(tile, myLastDraggedTile);

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
            case "One Way" -> new One_Way_Tile(0, 0, GRID_SIZE, GRID_SIZE);
            case "Two Way Adjacent" -> new Two_Way_Tile_Adjacent(0, 0, GRID_SIZE, GRID_SIZE);
            case "Two Way Across" -> new Two_Way_Tile_Across(0, 0, GRID_SIZE, GRID_SIZE);
            case "Three Way" -> new Three_Way_Tile(0, 0, GRID_SIZE, GRID_SIZE);
            default -> new Four_Way_Tile(0, 0, GRID_SIZE, GRID_SIZE);
        };
    }

    /**
     * Creates a delete Button
     * @return Button that deletes the instance of the LastDraggedTile from the MyTiles ArrayList or
     *  Deletes the last tile created
     */
    private Button deleteButton() {
        Button button = new Button("Delete");
        button.setMinWidth(ONE_HUNDRED_FIFTY);
        button.setPadding(new Insets(TEN, TWENTY, TEN, TWENTY));
        button.setOnAction(_ -> {
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
        button.setMinWidth(ONE_HUNDRED_FIFTY);
        button.setPadding(new Insets(TEN, TWENTY, TEN, TWENTY));
        button.setOnAction(_ -> {
            if (myTiles.contains(myLastDraggedTile.getMyTile())) {
                 MapTile tile = myTiles.get(myTiles.indexOf(myLastDraggedTile.getMyTile()));
                 tile.rotateClockwise();
                 myLastDraggedTile.setMyTile(tile);
                 myTiles.set(myTiles.indexOf(myLastDraggedTile.getMyTile()), tile);
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
        button.setMinWidth(ONE_HUNDRED_FIFTY);
        button.setPadding(new Insets(TEN, TWENTY, TEN, TWENTY));
        button.setOnAction(_ -> {
            if (myTiles.contains(myLastDraggedTile.getMyTile())) {
                MapTile tile = myTiles.get(myTiles.indexOf(myLastDraggedTile.getMyTile()));
                tile.rotateCounterClockwise();
                myLastDraggedTile.setMyTile(tile);
                myTiles.set(myTiles.indexOf(myLastDraggedTile.getMyTile()), tile);
            }
        });

        return button;
    }
}
