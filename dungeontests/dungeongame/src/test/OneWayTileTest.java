package dungeongame.src.test;

import dungeongame.src.model.One_Way_Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OneWayTileTest {

    @Test
    void getImageFileName() {
        Assertions.assertEquals("One_Way_Tile.jpg", new One_Way_Tile(0,0,0,0).getImageFileName());
    }
}