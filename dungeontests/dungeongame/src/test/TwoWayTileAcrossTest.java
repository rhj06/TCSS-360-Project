package dungeongame.src.test;

import dungeongame.src.model.Two_Way_Tile_Across;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * JUnit test class for testing the {@link Two_Way_Tile_Across} class.
 * <p>
 * This class includes tests for the tile's image file name
 * </p>
 */
class TwoWayTileAcrossTest {

    /**
     * returns the correct file name of object
     */
    @Test
    void getImageFileName() {
        Assertions.assertEquals("Two_Way_Tile_Across.jpg", new Two_Way_Tile_Across(0,0,0,0).getImageFileName());
    }
}