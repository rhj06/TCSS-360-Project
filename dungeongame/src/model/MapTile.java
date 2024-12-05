package dungeongame.src.model;

import javafx.scene.shape.Rectangle;

/**
 * MapTile is a Rectangle with a uniques image file name and a constant size
 */
public class MapTile extends Rectangle {

    /**Constant Default size of Tile*/
    private static final int DEFAULT_SIZE = 50;
    /**Constant Degree of Rotation*/
    private static final int DEGREE_OF_ROTATION = 90;
    /**X position*/
    private final int myPositionX;
    /**Y position*/
    private final int myPositionY;

    /**
     * MapTile Constructor
     * @param theXCoor
     * @param theYCoor
     */
    public MapTile(int theXCoor, int theYCoor) {
        super(theXCoor, theYCoor, DEFAULT_SIZE, DEFAULT_SIZE);
        myPositionX = theXCoor;
        myPositionY = theYCoor;
    }

    /**
     * Default file Name
     * @return Basic_Tile.png
     */
    public String getImageFileName() {
        return "Basic_Tile.png";
    }

    /**
     * get tiles current X position
     * @return myPositionX
     */
    public int getMyPositionX() {
        return myPositionX;
    }

    /**
     * get tiles current Y position
     * @return myPositionY
     */
    public int getStartPositionY() {
        return myPositionY;
    }

    /**
     * Rotate Method for rotating object clockwise 90 degrees
     */
    public void rotateClockwise() {
        this.setRotate((this.getRotate() + DEGREE_OF_ROTATION) % 360);
    }

    /**
     * Rotate Method for rotating object counterclockwise 90 degrees
     */
    public void rotateCounterClockwise() {
        this.setRotate((this.getRotate() - DEGREE_OF_ROTATION + 360) % 360);
    }
}
