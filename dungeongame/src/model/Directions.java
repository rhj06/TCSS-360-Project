package dungeongame.src.model;

public enum Directions {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    final private int myValue;

    Directions(final int theValue) {
        myValue = theValue;
    }

}
