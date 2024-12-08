package dungeongame.src.view;

import dungeongame.src.model.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;

import java.awt.*;

public class MazeBorderPane extends BorderPane {

    /**Grid size constant*/
    private static final int GRID_SIZE = 100;
    /**The Difference of BorderPanes height and Grid height*/
    private static final int BORDERPANE_TO_GRID_DIFFERENCE = 150;

    private static final Point CENTER = new Point(1, 1);
    private static final int SIZE_OF_THREE = 3;
    /**List of MapTiles*/
    private final MapTile[][] myTiles;
    private final Maze myMaze;
    private final Room[] myRoom;
    /**BorderPane Width and Height*/
    private final double myWidth, myHeight;

    public MazeBorderPane(final double theWidth, final double theHeight) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        myMaze = Maze.getInstance();
        myRoom = myMaze.getNeighborsClockwise(myMaze.getPlayerCords().y, myMaze.getPlayerCords().x);
        myTiles = new MapTile[SIZE_OF_THREE][SIZE_OF_THREE];

        defaultLayout();
    }

    private void defaultLayout() {
        GridHandler backgroundGridHandler = new GridHandler(myWidth, myHeight, GRID_SIZE, this);
        backgroundGridHandler.createGrid();

        MapTile[] tiles = new MapTile[SIZE_OF_THREE * SIZE_OF_THREE];
        for (int i = 0; i < SIZE_OF_THREE * SIZE_OF_THREE; i++) {
            if (i == 4) {
                tiles[i] = createTile(myMaze.getRooms()[myMaze.getPlayerCords().y][myMaze.getPlayerCords().x]);
            } else {
                tiles[i] = createTile(myRoom[i]);
            }
        }

        for (MapTile tile : tiles) {
            tile.setFill(new ImagePattern(new Image("file:maze_tile_images/" + tile.getImageFileName())));
        }
    }

    private int typeOfTile(final Room theCurrentRoom) {
        int numOfDoors = 0;
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
        return numOfDoors;
    }

    private MapTile createTile(Room theCurrentRoom) {
        MapTile tile;
        if (typeOfTile(theCurrentRoom) == 1) {
            tile = rotateOneWayTile(theCurrentRoom, new One_Way_Tile(
                    (int) theCurrentRoom.getCords().getX() * GRID_SIZE,
                    (int) theCurrentRoom.getCords().getY() * GRID_SIZE));
        } else if (typeOfTile(theCurrentRoom) == 2) {
            if ((theCurrentRoom.isNorthDoor() && theCurrentRoom.isSouthDoor()) || (theCurrentRoom.isEastDoor() && theCurrentRoom.isWestDoor())) {
                tile = rotateTwoWayAcrossTile(theCurrentRoom, new Two_Way_Tile_Across(
                        (int) theCurrentRoom.getCords().getX() * GRID_SIZE,
                        (int) theCurrentRoom.getCords().getY() * GRID_SIZE));
            } else {
                tile = rotateTwoWayAdjacentTile(theCurrentRoom, new Two_Way_Tile_Adjacent(
                        (int) theCurrentRoom.getCords().getX() * GRID_SIZE,
                        (int) theCurrentRoom.getCords().getY() * GRID_SIZE));
            }
        } else if (typeOfTile(theCurrentRoom) == 3) {
            tile = rotateThreeWayTile(theCurrentRoom, new Three_Way_Tile(
                    (int) theCurrentRoom.getCords().getX() * GRID_SIZE,
                    (int) theCurrentRoom.getCords().getY() * GRID_SIZE));
        } else if (typeOfTile(theCurrentRoom) == 4) {
            tile = new Four_Way_Tile(
                    (int) theCurrentRoom.getCords().getX() * GRID_SIZE,
                    (int) theCurrentRoom.getCords().getY() * GRID_SIZE);
        } else {
            tile = new MapTile((int) theCurrentRoom.getCords().getX() * GRID_SIZE,
                    (int) theCurrentRoom.getCords().getY() * GRID_SIZE);
        }

        return tile;
    }

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

    private MapTile rotateTwoWayAcrossTile(final Room theCurrentRoom, final MapTile theCurrentTile) {
        if (!theCurrentRoom.isWestDoor()) {
            theCurrentTile.rotateClockwise();
        }
        return theCurrentTile;
    }

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
