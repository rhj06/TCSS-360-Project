package dungeongame.src.view;

public final class Four_Way_Tile extends MapTile{

    /**
     * Four Way Tile Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public Four_Way_Tile(int theXCoor, int theYCoor) {
        super(theXCoor, theYCoor);
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
