package dungeongame.src.test;

import dungeongame.src.model.Four_Way_Tile;
import dungeongame.src.model.Two_Way_Tile_Adjacent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for testing the {@link Two_Way_Tile_Adjacent} class.
 * <p>
 * This class includes tests for the tile's image file name
 * </p>
 */
class TwoWayTileAdjacentTest {

    /**
     * returns the correct file name of object
     */
    @Test
    void getImageFileName() {
        Assertions.assertEquals("Two_Way_Tile_Adjacent.jpg", new Two_Way_Tile_Adjacent(0,0,0,0).getImageFileName());
    }
}