package dungeongame.src.model;

/**
 * Three Way MapTile
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class Three_Way_Tile extends MapTile {

    /**
     * Three Way Tile Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public Three_Way_Tile(final int theXCoor, final int theYCoor, final int theWidth, final int theHeight) {
        super(theXCoor, theYCoor, theWidth, theHeight);
    }

    /**
     * Image File Name for a Three Way Tile
     * @return Three_Way_Tile.jpg
     */
    @Override
    public String getImageFileName() {
        return "Three_Way_Tile.jpg";
    }
}
