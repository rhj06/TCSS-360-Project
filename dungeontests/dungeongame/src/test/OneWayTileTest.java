package dungeongame.src.test;

import dungeongame.src.model.One_Way_Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for testing the {@link One_Way_Tile} class.
 * <p>
 * This class includes tests for the tile's image file name
 * </p>
 */
class OneWayTileTest {

    /**
     * returns the correct file name of object
     */
    @Test
    void getImageFileName() {
        Assertions.assertEquals("One_Way_Tile.jpg", new One_Way_Tile(0,0,0,0).getImageFileName());
    }
}