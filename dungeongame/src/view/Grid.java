package dungeongame.src.view;

import javafx.scene.layout.BorderPane;

public interface Grid {

    /**
     * Get tile row
     * @return myRowTiles
     */
    int getMyRowTiles();

    /**
     * get tile column
     * @return myColumnTiles
     */
    int getMyColumnTiles();

    /**
     * get total tiles
     * @return myTotalTiles
     */
    int getMyTotalTiles();

    /**
     * get size of one tile
     * @return myGridSizePerTile
     */
    int getMySingleTileSize();

    /**
     * get current instance of BorderPane
     * @return myBorderPane
     */
    BorderPane getMyBorderPane();
}
