package dungeongame.src.test;

import dungeongame.src.model.Three_Way_Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThreeWayTileTest {

    @Test
    void getImageFileName() {
        Assertions.assertEquals("Three_Way_Tile.jpg", new Three_Way_Tile(0,0,0,0).getImageFileName());
    }
}