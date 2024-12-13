package dungeongame.src.view;

import dungeongame.src.model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * MazeBorderPane, Child of BorderPane, Creates a Visual representation of the current Players coordinates in the Maze
 */
public final class MazeBorderPane extends BorderPane {

    /** Constant of 2 */
    private static final int TWO = 2;
    /** Constant of 3 */
    private static final int THREE = 3;
    /** Constant of 4 */
    private static final int FOUR = 4;
    /** Grid size constant */
    private static final int GRID_SIZE = 150;
    /** Warrior sprite offset for center placement */
    private static final int WARRIOR_SPRITE_SHIFT = GRID_SIZE + 40;
    /** Warrior sprite scale to fit inside maze tile */
    private static final double WARRIOR_SPRITE_SCALE = GRID_SIZE / 2.1;
    /** Thief sprite offset for center placement */
    private static final int THIEF_SPRITE_SHIFT = GRID_SIZE + 47;
    /** Thief sprite scale to fit inside maze tile */
    private static final double Thief_SPRITE_SCALE = GRID_SIZE / 2.5;
    /** Wizard sprite offset for center placement */
    private static final int WIZARD_SPRITE_SHIFT = GRID_SIZE + 35;
    /** Wizard sprite scale to fit inside maze tile */
    private static final double WIZARD_SPRITE_SCALE = GRID_SIZE / 2.0;
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
    /** my neighboring rooms */
    private Room[] myRoom;
    /** BorderPane Width and Height */
    private final double myWidth, myHeight;
    /** String for the Main Character Sprite */
    private final String myPlayerSprite;
    /** Boolean Check for if the vision potion is used */
    private boolean myVisionPotionCheck = false;

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

        PlayerInventory myPlayerInventory = PlayerInventory.getInstance();
        myMaze.addPropertyChangeListener(event -> {
            if("change in direction".equals(event.getPropertyName())) {
                updateBorderPane();
                myVisionPotionCheck = false;
            }
        });
        myPlayerInventory.addPropertyChangeListener(event -> {
            if("VisionPotionUsed".equals(event.getPropertyName())) {
                myVisionPotionCheck = true;
                updateBorderPane();
            }
        });
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

                } else {
                    myTiles[i][j] = createTile(j, i, myRoom[count]);
                    count++;
                }
                myTiles[i][j].setFill(new ImagePattern(new Image("file:maze_tile_images/" + myTiles[i][j].getImageFileName())));
                this.getChildren().add(myTiles[i][j]);
            }
        }
        ImageView sprite = createCharacterSpriteImage();

        this.getChildren().add(sprite);
        if (!myVisionPotionCheck) {
            this.getChildren().add(createVisionBorder());
        }

    }

    /**
     * Create current character sprite for MazeBorderPane
     * @return ImageView (Character Sprite PNG)
     */
    private ImageView createCharacterSpriteImage() {
        ImageView sprite = new ImageView(new Image("file:character_sprite/" + myPlayerSprite));
        if ("warrior.png".equals(myPlayerSprite)) {
            sprite.setFitWidth(WARRIOR_SPRITE_SCALE);
            sprite.setFitHeight(WARRIOR_SPRITE_SCALE);
            sprite.setX(WARRIOR_SPRITE_SHIFT);
            sprite.setY(WARRIOR_SPRITE_SHIFT);
        } else if ("thief.png".equals(myPlayerSprite)) {
            sprite.setFitWidth(Thief_SPRITE_SCALE);
            sprite.setFitHeight(Thief_SPRITE_SCALE);
            sprite.setX(THIEF_SPRITE_SHIFT);
            sprite.setY(THIEF_SPRITE_SHIFT);
        } else {
            sprite.setFitWidth(WIZARD_SPRITE_SCALE);
            sprite.setFitHeight(WIZARD_SPRITE_SCALE);
            sprite.setX(WIZARD_SPRITE_SHIFT);
            sprite.setY(WIZARD_SPRITE_SHIFT);
        }
        return sprite;
    }

    /**
     * Create a Shape where the Rectangle covers the 8 neighboring tiles
     * @return Shape
     */
    private Shape createVisionBorder() {
        Rectangle outer = new Rectangle(0,0,SIZE_OF_VISION_RECTANGLE,SIZE_OF_VISION_RECTANGLE);
        Rectangle inner = new Rectangle(SIZE_OF_CENTER_RECTANGLE,SIZE_OF_CENTER_RECTANGLE,SIZE_OF_CENTER_RECTANGLE,SIZE_OF_CENTER_RECTANGLE);
        Shape hole = Shape.subtract(outer, inner);
        hole.setFill(new ImagePattern(new Image("file:.idea/resources/dungeon.jpg")));
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
        } else if (typeOfTile(theCurrentRoom) == TWO) {
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
        } else if (typeOfTile(theCurrentRoom) == THREE) {
            tile = rotateThreeWayTile(theCurrentRoom, new Three_Way_Tile(
                    theJ * GRID_SIZE,
                    theI * GRID_SIZE,
                    GRID_SIZE,
                    GRID_SIZE));
        } else if (typeOfTile(theCurrentRoom) == FOUR) {
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
