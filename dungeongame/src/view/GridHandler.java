package dungeongame.src.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class GridHandler extends AbstractGrid {
    /**Constant Color of White*/
    private static final Color BACKGROUND_COLOR_WHITE = Color.WHITE;
    /**Constant Color of Gray*/
    private static final Color BACKGROUND_COLOR_GRAY = Color.color(0.8,0.8,0.8);

    /**
     * Constructor for the GridHandler
     * @param theGridWidth
     * @param theGridHeight
     * @param theSingleTileSize
     * @param theBorderPane
     */
    public GridHandler(final double theGridWidth, final double theGridHeight,
                       final int theSingleTileSize, final BorderPane theBorderPane) {
        super(theGridWidth, theGridHeight, theSingleTileSize, theBorderPane);
    }

    /**
     * Creates a checkerboard grid over the current BorderPane
     */
    public void createGrid() {
        for(int i = 0; i < getMyTotalTiles(); i++){
            int x = (i % getMyRowTiles());
            int y = (i / getMyRowTiles());

            Rectangle rectangle = new Rectangle(x * getMySingleTileSize(),y * getMySingleTileSize(), getMySingleTileSize(), getMySingleTileSize());

            if((x + y) % 2 == 0){
                rectangle.setFill(BACKGROUND_COLOR_WHITE);
            } else {
                rectangle.setFill(BACKGROUND_COLOR_GRAY);
            }
            getMyBorderPane().getChildren().add(rectangle);
        }
    }
}