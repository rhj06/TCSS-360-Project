package dungeongame.src.test;

import dungeongame.src.model.Four_Way_Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FourWayTileTest {

    @Test
    void getImageFileName() {
        Assertions.assertEquals("Four_Way_Tile.jpg", new Four_Way_Tile(0,0,0,0).getImageFileName());
    }
}