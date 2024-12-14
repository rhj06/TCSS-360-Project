package dungeongame.src.test;

import dungeongame.src.model.MapTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTileTest {

    private MapTile myMapTile;
    @BeforeEach
    void setUp() {
        myMapTile = new MapTile(1,1,50,50);
    }

    @Test
    void getImageFileName() {
        assertEquals("Basic_Tile.png", myMapTile.getImageFileName());
    }

    @Test
    void getPositionX() {
        assertEquals(1,myMapTile.getPositionX());
    }

    @Test
    void getPositionY() {
        assertEquals(1,myMapTile.getPositionY());
    }

}