package dungeongame.src.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * MapTileList of type MapTile extends ArrayList of type MapTile
 * Implements Serializable for save feature
 * @param <MapTile> List can only contain object MapTile
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class MapTileList<MapTile> extends ArrayList<MapTile> implements Serializable {

    @Serial
    private static final long serialVersionUID = 534215432523L;
    /** MapTileList uniqueInstance */
    private static MapTileList uniqueInstance;

    /**
     * Get the unique instance of MapTileList
     * @return this.MapTileList
     */
    public static MapTileList getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MapTileList<>();
        }
        return uniqueInstance;
    }

    /**
     * Update uniqueInstance to be the saved theOtherList
     * @param theOtherList the serialized MapTileList
     */
    public void updateFrom(MapTileList<MapTile> theOtherList) {
        uniqueInstance = theOtherList;
    }
}
