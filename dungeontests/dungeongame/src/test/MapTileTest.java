package dungeongame.src.test;

import dungeongame.src.model.MapTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing the {@link MapTile} class.
 * <p>
 * This class includes tests for if the MapTile Object including
 * getting proper file names, x position, and y position of MapTile
 * </p>
 */
class MapTileTest {

    private MapTile myMapTile;

    /**
     * Setup for MapTile instance
     */
    @BeforeEach
    void setUp() {
        myMapTile = new MapTile(1,1,50,50);
    }

    /**
     * get file name
     */
    @Test
    void getImageFileName() {
        assertEquals("Basic_Tile.png", myMapTile.getImageFileName());
    }

    /**
     * test for get X position
     */
    @Test
    void getPositionX() {
        assertEquals(1,myMapTile.getPositionX());
    }

    /**
     * test for get Y position
     */
    @Test
    void getPositionY() {
        assertEquals(1,myMapTile.getPositionY());
    }

}