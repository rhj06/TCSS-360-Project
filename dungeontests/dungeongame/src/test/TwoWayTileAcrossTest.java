package dungeongame.src.test;

import dungeongame.src.model.Two_Way_Tile_Across;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TwoWayTileAcrossTest {

    @Test
    void getImageFileName() {
        Assertions.assertEquals("Two_Way_Tile_Across.jpg", new Two_Way_Tile_Across(0,0,0,0).getImageFileName());
    }
}