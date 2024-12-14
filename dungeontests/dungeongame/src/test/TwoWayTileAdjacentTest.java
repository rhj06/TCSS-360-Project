package dungeongame.src.test;

import dungeongame.src.model.Two_Way_Tile_Adjacent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TwoWayTileAdjacentTest {

    @Test
    void getImageFileName() {
        Assertions.assertEquals("Two_Way_Tile_Adjacent.jpg", new Two_Way_Tile_Adjacent(0,0,0,0).getImageFileName());
    }
}