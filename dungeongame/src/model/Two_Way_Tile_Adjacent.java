package dungeongame.src.model;

public final class Two_Way_Tile_Adjacent extends MapTile {

    /**
     * Two Way Tile Adjacent Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public Two_Way_Tile_Adjacent(int theXCoor, int theYCoor) {
        super(theXCoor, theYCoor);
    }

    /**
     * Image File Name for a Two Way Tile Adjacent
     * @return Two_Way_Tile_Adjacent.jpg
     */
    @Override
    public String getImageFileName() {
        return "Two_Way_Tile_Adjacent.jpg";
    }
}
