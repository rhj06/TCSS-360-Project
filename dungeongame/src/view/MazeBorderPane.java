package dungeongame.src.view;

import dungeongame.src.model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * MazeBorderPane, Child of BorderPane, Creates a Visual representation of the current Players coordinates in the Maze
 */
public class MazeBorderPane extends BorderPane {

    /**Grid size constant*/
    private static final int GRID_SIZE = 150;
    /** Character sprite offset for center placement */
    private static final int CHARACTER_SPRITE_SHIFT = GRID_SIZE + 30;
    /** Size of the vision rectangle */
    private static final int SIZE_OF_VISION_RECTANGLE = 450;
    /** Size of center tile */
    private static final int SIZE_OF_CENTER_RECTANGLE = 150;
    /** Constant of three */
    private static final int SIZE_OF_THREE = 3;
    /** List of MapTiles */
    private final MapTile[][] myTiles;
    /** My instance of maze */
    private final Maze myMaze;
    /***/
    private final PlayerInventory myPlayerInventory;
    /** my neighboring rooms */
    private Room[] myRoom;
    /** BorderPane Width and Height */
    private final double myWidth, myHeight;
    /** String for the Main Character Sprite */
    private final String myPlayerSprite;
    /** Boolean Check for if the vision potion is used */
    private boolean myVisionPostionCheck = false;

    /**
     * MazeBorderPane Constructor
     * @param theWidth Width of BorderPane
     * @param theHeight Height of BorderPane
     * @param thePlayerSprite String of Character Sprite
     */
    public MazeBorderPane(final double theWidth, final double theHeight, final String thePlayerSprite) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myMaze = Maze.getInstance();
        myRoom = myMaze.getNeighborsClockwise(myMaze.getPlayerCords().y, myMaze.getPlayerCords().x);
        myTiles = new MapTile[SIZE_OF_THREE][SIZE_OF_THREE];
        myPlayerSprite = thePlayerSprite;
        myPlayerInventory = PlayerInventory.getInstance();
        myMaze.addPropertyChangeListener(event -> {
            if("change in direction".equals(event.getPropertyName())) {
                myVisionPostionCheck = false;
                updateBorderPane();
            }
        });
        myPlayerInventory.addPropertyChangeListener(event -> {
            if("VisionPotionUsed".equals(event.getPropertyName())) {
                myVisionPostionCheck = true;
                updateBorderPane();
            }
        });
        defaultLayout();
    }

    /**
     * Default Layout of the MazeBorderPane
     */
    private void defaultLayout() {
        GridHandler backgroundGridHandler = new GridHandler(myWidth, myHeight, GRID_SIZE, this);
        backgroundGridHandler.createGrid();

        updateBorderPane();
    }

    /**
     * Update Borderpane method for when the player moves tiles
     */
    private void updateBorderPane() {
        myRoom = myMaze.getNeighborsClockwise(myMaze.getPlayerCords().y, myMaze.getPlayerCords().x);
        int count = 0;
        for (int i = 0; i < SIZE_OF_THREE; i++) {
            for (int j = 0; j < SIZE_OF_THREE; j++) {
                if (i == 1 && j == 1) { //center of the 3x3 matrix
                    myTiles[i][j] = createTile(j, i, myMaze.getRooms()[myMaze.getPlayerCords().y][myMaze.getPlayerCords().x]);
                    myTiles[i][j].setFill(new ImagePattern(new Image("file:maze_tile_images/" + myTiles[i][j].getImageFileName())));
                    this.getChildren().add(myTiles[i][j]);

                } else {
                    myTiles[i][j] = createTile(j, i, myRoom[count]);
                    count++;
                    myTiles[i][j].setFill(new ImagePattern(new Image("file:maze_tile_images/" + myTiles[i][j].getImageFileName())));
                    this.getChildren().add(myTiles[i][j]);
                }
            }
        }
        ImageView sprite = new ImageView(new Image("file:character_sprite/" + myPlayerSprite));
        sprite.setFitWidth((double) GRID_SIZE/2);
        sprite.setFitHeight((double) GRID_SIZE/2);
        sprite.setX(CHARACTER_SPRITE_SHIFT);
        sprite.setY(CHARACTER_SPRITE_SHIFT);

        this.getChildren().add(sprite);
        if (!myVisionPostionCheck) {
            this.getChildren().add(createVisionBoarder());
        }

    }

    /**
     * Create a Shape where the Rectangle covers the 8 neighboring tiles
     * @return Shape
     */
    private Shape createVisionBoarder() {
        Rectangle outer = new Rectangle(0,0,SIZE_OF_VISION_RECTANGLE,SIZE_OF_VISION_RECTANGLE);
        Rectangle inner = new Rectangle(SIZE_OF_CENTER_RECTANGLE,SIZE_OF_CENTER_RECTANGLE,SIZE_OF_CENTER_RECTANGLE,SIZE_OF_CENTER_RECTANGLE);
        Shape hole = Shape.subtract(outer, inner);
        hole.setFill(Color.BLACK);
        return hole;
    }
    private int typeOfTile(final Room theCurrentRoom) {
        int numOfDoors = 0;
        if (theCurrentRoom != null) {
            if (theCurrentRoom.isNorthDoor()) {
                numOfDoors++;
            }
            if (theCurrentRoom.isSouthDoor()) {
                numOfDoors++;
            }
            if (theCurrentRoom.isEastDoor()) {
                numOfDoors++;
            }
            if (theCurrentRoom.isWestDoor()) {
                numOfDoors++;
            }
        }
        return numOfDoors;
    }

    /**
     * Creates a MapTile with the given X and Y coordinates of the matrix
     * @param theJ Y Coordinate
     * @param theI X Coordinate
     * @param theCurrentRoom Current Room that the Tile is being created from
     * @return MapTile (One of 6 possibilities)
     */
    private MapTile createTile(final int theJ, final int theI, Room theCurrentRoom) {
        MapTile tile = null;
        if (typeOfTile(theCurrentRoom) == 1) {
            tile = rotateOneWayTile(theCurrentRoom, new One_Way_Tile(
                    theJ * GRID_SIZE,
                    theI * GRID_SIZE,
                    GRID_SIZE,
                    GRID_SIZE));
        } else if (typeOfTile(theCurrentRoom) == 2) {
            if ((theCurrentRoom.isNorthDoor() && theCurrentRoom.isSouthDoor()) || (theCurrentRoom.isEastDoor() && theCurrentRoom.isWestDoor())) {
                tile = rotateTwoWayAcrossTile(theCurrentRoom, new Two_Way_Tile_Across(
                        theJ * GRID_SIZE,
                        theI * GRID_SIZE,
                        GRID_SIZE,
                        GRID_SIZE));
            } else {
                tile = rotateTwoWayAdjacentTile(theCurrentRoom, new Two_Way_Tile_Adjacent(
                        theJ * GRID_SIZE,
                        theI * GRID_SIZE,
                        GRID_SIZE,
                        GRID_SIZE));
            }
        } else if (typeOfTile(theCurrentRoom) == 3) {
            tile = rotateThreeWayTile(theCurrentRoom, new Three_Way_Tile(
                    theJ * GRID_SIZE,
                    theI * GRID_SIZE,
                    GRID_SIZE,
                    GRID_SIZE));
        } else if (typeOfTile(theCurrentRoom) == 4) {
            tile = new Four_Way_Tile(
                    theJ * GRID_SIZE,
                    theI * GRID_SIZE,
                    GRID_SIZE,
                    GRID_SIZE);
        } else if (theCurrentRoom == null) {
            tile = new MapTile(
                    theJ * GRID_SIZE,
                    theI * GRID_SIZE,
                    GRID_SIZE,
                    GRID_SIZE);
        }

        return tile;
    }

    /**
     * Algorithm for Rotating One Way MapTile
     * @param theCurrentRoom Current room that MapTile is created from
     * @param theCurrentTile Current MapTile that is needed for rotation
     * @return theCurrentTile of which is rotated based on  where the door/s are located
     */
    private MapTile rotateOneWayTile(final Room theCurrentRoom, final MapTile theCurrentTile) {
        if (!theCurrentRoom.isWestDoor()) {
            if (!theCurrentRoom.isNorthDoor()) {
                if (!theCurrentRoom.isEastDoor()) {
                    theCurrentTile.rotateCounterClockwise();
                } else {
                    theCurrentTile.rotateClockwise();
                    theCurrentTile.rotateClockwise();
                }
            } else {
                theCurrentTile.rotateClockwise();
            }
        }
        return theCurrentTile;
    }

    /**
     * Algorithm for Rotating Two Way Adjacent MapTile
     * @param theCurrentRoom Current room that MapTile is created from
     * @param theCurrentTile Current MapTile that is needed for rotation
     * @return theCurrentTile of which is rotated based on  where the door/s are located
     */
    private MapTile rotateTwoWayAdjacentTile(final Room theCurrentRoom, final MapTile theCurrentTile) {
        if (!theCurrentRoom.isWestDoor()) {
            if (!theCurrentRoom.isNorthDoor()) {
                theCurrentTile.rotateClockwise();
                theCurrentTile.rotateClockwise();
            } else {
                theCurrentTile.rotateClockwise();
            }
        } else if (!theCurrentRoom.isNorthDoor()) {
            theCurrentTile.rotateCounterClockwise();
        }
        return theCurrentTile;
    }

    /**
     * Algorithm for Rotating Two Way Across MapTile
     * @param theCurrentRoom Current room that MapTile is created from
     * @param theCurrentTile Current MapTile that is needed for rotation
     * @return theCurrentTile of which is rotated based on  where the door/s are located
     */
    private MapTile rotateTwoWayAcrossTile(final Room theCurrentRoom, final MapTile theCurrentTile) {
        if (!theCurrentRoom.isWestDoor()) {
            theCurrentTile.rotateClockwise();
        }
        return theCurrentTile;
    }

    /**
     * Algorithm for Rotating Three Way MapTile
     * @param theCurrentRoom Current room that MapTile is created from
     * @param theCurrentTile Current MapTile that is needed for rotation
     * @return theCurrentTile of which is rotated based on  where the door/s are located
     */
    private MapTile rotateThreeWayTile(final Room theCurrentRoom, final MapTile theCurrentTile) {
        if (theCurrentRoom.isEastDoor()) {
            if (!theCurrentRoom.isSouthDoor()) {
                theCurrentTile.rotateClockwise();
            } else if (!theCurrentRoom.isWestDoor()) {
                theCurrentTile.rotateClockwise();
                theCurrentTile.rotateClockwise();
            } else {
                theCurrentTile.rotateCounterClockwise();
            }
        }
        return theCurrentTile;
    }

}
