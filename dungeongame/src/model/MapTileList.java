package dungeongame.src.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public final class MapTileList<MapTile> extends ArrayList<MapTile> implements Serializable {

    @Serial
    private static final long serialVersionUID = 534215432523L;
    private static MapTileList uniqueInstance;

    public static MapTileList getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MapTileList<>();
        }
        return uniqueInstance;
    }
}
