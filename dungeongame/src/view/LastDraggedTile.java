package dungeongame.src.view;

import dungeongame.src.model.MapTile;

/**
 * Object that contains an instance of the Tile last used in a Drag event
 */
public final class LastDraggedTile {

    /**The instance of a Dragged Tile*/
    private MapTile myTile;

    /**
     * LastDraggedTile constructor
     * @param TheLastDraggedTile instance of the Tile in the last Drag event
     */
    public LastDraggedTile(final MapTile TheLastDraggedTile) {
        myTile = TheLastDraggedTile;
    }

    /**
     * getter for current instance of tile
     * @return myTile
     */
    public MapTile getMyTile() {
        return myTile;
    }

    /**
     * setter for setting new instance of latest dragged tile
     * @param theNewLastDraggedTile new instance of the latest dragged tile
     */
    public void setMyTile(final MapTile theNewLastDraggedTile) {
        myTile = theNewLastDraggedTile;
    }


}
