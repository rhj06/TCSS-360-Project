package dungeongame.src.model;

public final class Two_Way_Tile_Adjacent extends MapTile {

    /**
     * Two Way Tile Adjacent Constructor
     * @param theXCoor the X coordinate
     * @param theYCoor the Y coordinate
     */
    public Two_Way_Tile_Adjacent(final int theXCoor, final int theYCoor, final int theWidth, final int theHeight) {
        super(theXCoor, theYCoor, theWidth, theHeight);
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
