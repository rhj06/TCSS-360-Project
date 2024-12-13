package dungeongame.src.model;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a room object which will be used to construct the dungeon maze in the dungeon adventure game.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 65432135L;

    /** The coordinates of the room in the maze. */
    private Point myCords;

    /** The room to the north of this room. */
    private Room myNorthNeighbor;

    /** The room to the east of this room. */
    private Room myEastNeighbor;

    /** The room to the south of this room. */
    private Room mySouthNeighbor;

    /** The room to the west of this room. */
    private Room myWestNeighbor;

    /** Whether the room has a north door. */
    private boolean myNorthDoor;

    /** Whether the room has an east door. */
    private boolean myEastDoor;

    /** Whether the room has a south door. */
    private boolean mySouthDoor;

    /** Whether the room has a west door. */
    private boolean myWestDoor;

    /** The monster in the room. */
    private AbstractMonster myMonster;

    /** The item in the room. */
    private Item myItem;

    /**
     * Constructor for the room object.
     */
    Room() {
        myCords = null;
        myNorthNeighbor = null;
        myEastNeighbor = null;
        mySouthNeighbor = null;
        myWestNeighbor = null;
        myNorthDoor = false;
        myEastDoor = false;
        mySouthDoor = false;
        myWestDoor = false;
        myMonster = null;
        myItem = null;
    }

    /**
     * Returns the column row coordinates of the room in the maze.
     */
    public Point getCords() {
        return myCords;
    }

    /**
     * Returns the room that is the north neighbor of this room.
     */
    public Room getNorthNeighbor() {
        return myNorthNeighbor;
    }

    /**
     * Returns the room that is the east neighbor of this room.
     */
    public Room getEastNeighbor() {
        return myEastNeighbor;
    }

    /**
     * Returns the room that is the south neighbor of this room.
     */
    public Room getSouthNeighbor() {
        return mySouthNeighbor;
    }

    /**
     * Returns the room that is the west neighbor of this room.
     */
    public Room getWestNeighbor() {
        return myWestNeighbor;
    }

    /**
     * Sets the column row coordinates of the room.
     *
     * @param theX The column value the room.
     * @param theY The row value of the room.
     */
    public void setCords(int theX, int theY) {
        if (theX <= 0 || theY <= 0) {
            throw new IllegalArgumentException("Coordinates must be greater than 0.");
        }
        myCords = new Point(theX, theY);
    }

    /**
     * Sets the north neighbor of the current room to be the inputted room.
     *
     * @param theNorthNeighbor The room to be set as the north neighbor of the current room.
     */
    public void setNorthNeighbor(Room theNorthNeighbor) {
        myNorthNeighbor = theNorthNeighbor;
    }

    /**
     * Sets the east neighbor of the current room to be the inputted room.
     *
     * @param theEastNeighbor The room to be set as the east neighbor of the current room.
     */
    public void setEastNeighbor(Room theEastNeighbor) {
        myEastNeighbor = theEastNeighbor;
    }

    /**
     * Sets the south neighbor of the current room to be the inputted room.
     *
     * @param theSouthNeighbor The room to be set as the south neighbor of the current room.
     */
    public void setSouthNeighbor(Room theSouthNeighbor) {
        mySouthNeighbor = theSouthNeighbor;
    }

    /**
     * Sets the west neighbor of the current room to be the inputted room.
     *
     * @param theWestNeighbor The room to be set as the west neighbor of the current room.
     */
    public void setWestNeighbor(Room theWestNeighbor) {
        myWestNeighbor = theWestNeighbor;
    }

    /**
     * Returns true if the room has a door in the north direction.
     */
    public boolean isNorthDoor() {
        return myNorthDoor;
    }

    /**
     * Returns true if the room has a door in the east direction.
     */
    public boolean isEastDoor() {
        return myEastDoor;
    }

    /**
     * Returns true if the room has a door in the south direction.
     */
    public boolean isSouthDoor() {
        return mySouthDoor;
    }

    /**
     * Returns true if the room has a door in the west direction.
     */
    public boolean isWestDoor() {
        return myWestDoor;
    }

    /**
     * Sets the value of the North door.
     */
    public void setNorthDoor(boolean theNorthConnect) {
        myNorthDoor = theNorthConnect;
    }

    /**
     * Sets the value of the East door.
     */
    public void setEastDoor(boolean theEastConnect) {
        myEastDoor = theEastConnect;
    }

    /**
     * Sets the value of the South door.
     */
    public void setSouthDoor(boolean theSouthConnect) {
        mySouthDoor = theSouthConnect;
    }

    /**
     * Sets the value of the West door.
     */
    public void setWestDoor(boolean theWestConnect) {
        myWestDoor = theWestConnect;
    }

    /**
     * Returns the monster the room contains.
     */
    public AbstractMonster getMonster() {
        return myMonster;
    }

    /**
     * Returns the item the room contains.
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * Sets the monster of the room to be the inputted monster.
     */
    public void setMonster(AbstractMonster theMonster) {
        myMonster = theMonster;
    }
//
    /**
     * Sets the item in the room to be the inputted item.
     */
    public void setItem(Item theItem) {
        myItem = theItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(myCords, room.myCords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myCords);
    }
}
