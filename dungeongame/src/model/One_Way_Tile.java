package dungeongame.src.model;

public final class One_Way_Tile extends MapTile {

    /**
     * One Way Tile Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public One_Way_Tile(final int theXCoor, final int theYCoor, final int theWidth, final int theHeight) {
        super(theXCoor, theYCoor, theWidth, theHeight);
    }

    /**
     * Image File Name for a One Way Tile
     * @return One_Way_Tile.jpg
     */
    @Override
    public String getImageFileName() {
        return "One_Way_Tile.jpg";
    }
}
