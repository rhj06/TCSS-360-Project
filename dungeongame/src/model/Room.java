package dungeongame.src.model;

import java.awt.*;
import java.util.Objects;

/**
 * Represents a room object which will be used to construct the dungeon maze in the dungeon adventure game.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
public class Room {
    private int myContents;
    private Point myCords;
    private Room myNorthNeighbor;
    private Room myEastNeighbor;
    private Room mySouthNeighbor;
    private Room myWestNeighbor;
    private boolean myNorthDoor;
    private boolean myEastDoor;
    private boolean mySouthDoor;
    private boolean myWestDoor;
    private AbstractMonster myMonster;
    private Item myItem;
    private boolean myMonsterSlain;
    private boolean myItemTaken;

    /**
     * Constructor for the room object.
     */
    Room() {
        myContents = 0;
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
        myMonsterSlain = false;
        myItemTaken = false;
    }

    /**
     * Returns the column row coordinates of the room in the maze.
     */
    public Point getCords() {
        return myCords;
    }

    /**
     * Returns the int representation of the contents of the room.
     * 0 = empty
     * 1 = item
     * 2 = monster
     */
    public int getContents() {
        return myContents;
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
        //myNorthNeighbor.setSouthDoor(theNorthConnect);
    }

    /**
     * Sets the value of the East door.
     */
    public void setEastDoor(boolean theEastConnect) {
        myEastDoor = theEastConnect;
        //myEastNeighbor.setWestDoor(theEastConnect);

    }

    /**
     * Sets the value of the South door.
     */
    public void setSouthDoor(boolean theSouthConnect) {
        mySouthDoor = theSouthConnect;
        //mySouthNeighbor.setNorthDoor(theSouthConnect);
    }

    /**
     * Sets the value of the West door.
     */
    public void setWestDoor(boolean theWestConnect) {
        myWestDoor = theWestConnect;
        //myWestNeighbor.setEastDoor(theWestConnect);
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
     * Returns true if the room contained a monster, but the monster was killed.
     */
    public boolean isMonsterSlain() {
        return myMonsterSlain;
    }

    /**
     * Returns true if the room contained an item, but the item was taken.
     */
    public boolean isItemTaken() {
        return myItemTaken;
    }

    /**
     * Sets the room contents to be the input integer.
     * 0 = empty
     * 1 = item
     * 2 = monster
     */
    public void setContents(int theContents) {
        myContents = theContents;
    }

    /**
     * Sets the monster of the room to be the inputted monster.
     */
    public void setMyMonster(AbstractMonster theMonster) {
        myMonster = theMonster;
    }

    /**
     * Sets the item in the room to be the inputted item.
     */
    public void setItem(Item theItem) {
        myItem = theItem;
    }

    /**
     * Sets the slain status of the monster.
     */
    public void setMonsterSlain(boolean theMonsterSlainStatus) {
        myMonsterSlain = theMonsterSlainStatus;
    }

    /**
     * Sets the taken status of the item.
     */
    public void setItemTaken(boolean theItemTakenStatus) {
        myItemTaken = theItemTakenStatus;
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
