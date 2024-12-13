package dungeongame.src.view;

import javafx.scene.layout.BorderPane;

/**
 * AbstractGrid to calculate grid rows and columns with getter methods
 * @version 1.0
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 */
public abstract class AbstractGrid implements Grid {

    /**My total grid tiles for a single row*/
    private final int myRowTiles;
    /**My total tiles for a single column*/
    private final int myColumnTiles;
    /**my total grid tiles*/
    private final int myTotalTiles;
    /**Size of a singe grid tile*/
    private final int mySingleTileSize;
    /**My instance of the current BorderPane*/
    private final BorderPane myBorderPane;

    /**
     * AbstractGrid constructor
     * @param theGridWidth Grid width
     * @param theGridHeight Grid height
     * @param theSingleTileSize size of one tile
     * @param theBorderPane the current instance of the BorderPane
     */
    public AbstractGrid(final double theGridWidth, final double theGridHeight,
                        final int theSingleTileSize, final BorderPane theBorderPane) {
        mySingleTileSize = theSingleTileSize;
        myBorderPane = theBorderPane;
        myRowTiles = (int) (theGridWidth / mySingleTileSize);
        myTotalTiles = (int) ((theGridWidth / mySingleTileSize) * (theGridHeight / mySingleTileSize));
        myColumnTiles = myTotalTiles / myRowTiles;
    }

    /**
     * Get tile row
     * @return myRowTiles
     */
    public int getMyRowTiles() {
        return myRowTiles;
    }

    /**
     * get tile column
     * @return myColumnTiles
     */
    public int getMyColumnTiles() {
        return myColumnTiles;
    }

    /**
     * get total tiles
     * @return myTotalTiles
     */
    public int getMyTotalTiles() {
        return myTotalTiles;
    }

    /**
     * get size of one tile
     * @return myGridSizePerTile
     */
    public int getMySingleTileSize() {
        return mySingleTileSize;
    }

    /**
     * get current instance of BorderPane
     * @return myBorderPane
     */
    public BorderPane getMyBorderPane() {
        return myBorderPane;
    }
}
