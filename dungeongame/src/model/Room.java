package dungeongame.src.model;

import java.awt.*;
import java.util.Objects;

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
//    private Monster myMonster;
    private Item myItem;
    private boolean myMonsterSlain;
    private boolean myItemTaken;

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
//        myMonster = null;
        myItem = null;
        myMonsterSlain = false;
        myItemTaken = false;
    }

    public Point getCords() {
        return myCords;
    }

    public int getContents() {
        return myContents;
    }

    public Room getNorthNeighbor() {
        return myNorthNeighbor;
    }

    public Room getEastNeighbor() {
        return myEastNeighbor;
    }

    public Room getSouthNeighbor() {
        return mySouthNeighbor;
    }

    public Room getWestNeighbor() {
        return myWestNeighbor;
    }

    public void setCords(int theX, int theY) {
        Point cords = new Point(theX, theY);
        myCords = cords;
    }

    public void setNorthNeighbor(Room theNorthNeighbor) {
        myNorthNeighbor = theNorthNeighbor;
    }

    public void setEastNeighbor(Room theEastNeighbor) {
        myEastNeighbor = theEastNeighbor;
    }

    public void setSouthNeighbor(Room theSouthNeighbor) {
        mySouthNeighbor = theSouthNeighbor;
    }

    public void setWestNeighbor(Room theWestNeighbor) {
        myWestNeighbor = theWestNeighbor;
    }

    public boolean isNorthDoor() {
        return myNorthDoor;
    }

    public boolean isEastDoor() {
        return myEastDoor;
    }

    public boolean isSouthDoor() {
        return mySouthDoor;
    }

    public boolean isWestDoor() {
        return myWestDoor;
    }

    public void setNorthDoor(boolean theNorthConnect) {
        myNorthDoor = theNorthConnect;
        //myNorthNeighbor.setSouthDoor(theNorthConnect);
    }

    public void setEastDoor(boolean theEastConnect) {
        myEastDoor = theEastConnect;
        //myEastNeighbor.setWestDoor(theEastConnect);

    }

    public void setSouthDoor(boolean theSouthConnect) {
        mySouthDoor = theSouthConnect;
        //mySouthNeighbor.setNorthDoor(theSouthConnect);
    }

    public void setWestDoor(boolean theWestConnect) {
        myWestDoor = theWestConnect;
        //myWestNeighbor.setEastDoor(theWestConnect);
    }

//    public Monster getMonster() {
//        return myMonster;
//    }

    public Item getItem() {
        return myItem;
    }

    public boolean isMonsterSlain() {
        return myMonsterSlain;
    }

    public boolean isItemTaken() {
        return myItemTaken;
    }

    public void setContents(int theContents) {
        myContents = theContents;
    }

//    public void setMyMonster(Monster theMonster) {
//        myMonster = theMonster;
//    }

    public void setItem(Item theItem) {
        myItem = theItem;
    }

    public void setMonsterSlain(boolean theMonsterSlainStatus) {
        myMonsterSlain = theMonsterSlainStatus;
    }

//    public void setItemTaken(boolean myItemTakenStatus) {
//        myItemTaken = theItemTakenStatus;
//    }


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
