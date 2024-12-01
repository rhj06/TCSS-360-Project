package dungeongame.src.view;

public final class Three_Way_Tile extends MapTile{

    /**
     * Three Way Tile Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public Three_Way_Tile(int theXCoor, int theYCoor) {
        super(theXCoor, theYCoor);
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
