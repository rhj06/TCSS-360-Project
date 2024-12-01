package dungeongame.src.view;

public final class Two_Way_Tile_Across extends MapTile {

    /**
     * Two Way Tile Across Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public Two_Way_Tile_Across(int theXCoor, int theYCoor) {
        super(theXCoor, theYCoor);
    }

    /**
     * Image File Name for a Two Way Tile Across
     * @return Two_Way_Tile_Across.jpg
     */
    @Override
    public String getImageFileName() {
        return "Two_Way_Tile_Across.jpg";
    }
}
