package dungeongame.src.model;

import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

/**
 * MapTile is a Rectangle with a uniques image file name and a constant size
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public class MapTile extends Rectangle implements Serializable {

    @Serial
    private static final long serialVersionUID = 354215342543L;
    /** Constant of 360 */
    private static final int THREE_SIXTY = 360;
    /** Constant Degree of Rotation */
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
    public MapTile(final int theXCoor, final int theYCoor, final int theWidth, final int theHeight) {
        super(theXCoor, theYCoor, theWidth, theHeight);
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
    public int getPositionX() {
        return myPositionX;
    }

    /**
     * get tiles current Y position
     * @return myPositionY
     */
    public int getPositionY() {
        return myPositionY;
    }

    /**
     * Rotate Method for rotating object clockwise 90 degrees
     */
    public void rotateClockwise() {
        this.setRotate((this.getRotate() + DEGREE_OF_ROTATION) % THREE_SIXTY);
    }

    /**
     * Rotate Method for rotating object counterclockwise 90 degrees
     */
    public void rotateCounterClockwise() {
        this.setRotate((this.getRotate() - DEGREE_OF_ROTATION + THREE_SIXTY) % THREE_SIXTY);
    }

}
