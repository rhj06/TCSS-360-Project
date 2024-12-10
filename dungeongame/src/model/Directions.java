package dungeongame.src.model;

/**
 * Direction enum to angle directional based inputs and orientation
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public enum Directions {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    /**
     * Attach Direction to integer
     * @param theValue Integer
     */
    Directions(final int theValue) {
    }

}
