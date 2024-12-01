package dungeongame.src.view;

import javafx.scene.layout.BorderPane;

/**
 * GridBase that handle drag events for Tiles
 */
public final class DraggableGrid extends AbstractGrid {

    /** Current Mouse Coordinates*/
    private double myMouseXCoordinate;
    private double myMouseYCoordinate;

    /**
     * DraggableGrid Constructor
     * @param theGridWidth Grid width
     * @param theGridHeight Grid Height
     * @param theSingleTileSize size per grid section
     * @param theBorderPane The panel
     */
    public DraggableGrid(final double theGridWidth, final double theGridHeight,
                         final int theSingleTileSize, final BorderPane theBorderPane) {
        super(theGridWidth, theGridHeight, theSingleTileSize, theBorderPane);
    }

    /**
     * Makes object Tile Draggable over the Grid
     * @param theTile The current tile
     * @param theLastDraggedTile Object holding last tile interacted with
     */
    public void makeDraggable(final MapTile theTile, final LastDraggedTile theLastDraggedTile) {
        theTile.setOnMouseDragged(mouseEvent -> {
            myMouseXCoordinate = mouseEvent.getSceneX();
            myMouseYCoordinate = mouseEvent.getSceneY();

            final int x = (int) ((myMouseXCoordinate / getMySingleTileSize()) % getMyRowTiles()) * getMySingleTileSize();
            final int y = (int) ((myMouseYCoordinate / getMySingleTileSize()) % getMyColumnTiles()) * getMySingleTileSize();

            theTile.setLayoutX(x - theTile.getMyPositionX());
            theTile.setLayoutY(y - theTile.getStartPositionY());

            theLastDraggedTile.setMyTile(theTile);
        });
    }
}