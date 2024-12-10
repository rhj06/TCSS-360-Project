package dungeongame.src.model;

/**
 * Four way tile object
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class Four_Way_Tile extends MapTile {

    /**
     * Four Way Tile Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public Four_Way_Tile(final int theXCoor, final int theYCoor, final int theWidth, final int theHeight) {
        super(theXCoor, theYCoor, theWidth, theHeight);
    }

    /**
     * Image File Name for a Four Way Tile
     * @return Four_Way_Tile.jpg
     */
    @Override
    public String getImageFileName() {
        return "Four_Way_Tile.jpg";
    }
}
