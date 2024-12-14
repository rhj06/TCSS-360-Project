package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room myRoom;
    private Room NeighborRoom;

    @BeforeEach
    void setUp() {
        myRoom = new Room();
        NeighborRoom = new Room();
        myRoom.setCords(1,1);
        myRoom.setWestDoor(true);
        myRoom.setEastDoor(true);
        myRoom.setNorthDoor(false);
        myRoom.setSouthDoor(true);
        NeighborRoom.setWestDoor(true);
        NeighborRoom.setEastDoor(true);
        NeighborRoom.setSouthDoor(true);
        NeighborRoom.setCords(0,0);
        NeighborRoom.setNorthDoor(true);
    }

    @Test
    void getCords() {
        Room test = new Room();
        test.setCords(1,1);

        assertEquals(test.getCords(), myRoom.getCords());
    }

    @Test
    void getNorthNeighbor() {
        assertNull(myRoom.getNorthNeighbor());
    }

    @Test
    void getEastNeighbor() {
        myRoom.setEastNeighbor(NeighborRoom);
        assertEquals(myRoom.getEastNeighbor(), NeighborRoom);
    }

    @Test
    void getSouthNeighbor() {
        myRoom.setSouthNeighbor(NeighborRoom);
        assertEquals(myRoom.getSouthNeighbor(), NeighborRoom);
    }

    @Test
    void getWestNeighbor() {
        myRoom.setWestNeighbor(NeighborRoom);
        assertEquals(myRoom.getWestNeighbor(), NeighborRoom);
    }

    @Test
    void isNorthDoor() {
        assertFalse(myRoom.isNorthDoor());
    }

    @Test
    void isEastDoor() {
        assertTrue(myRoom.isEastDoor());
    }

    @Test
    void isSouthDoor() {
        assertTrue(myRoom.isSouthDoor());
    }

    @Test
    void isWestDoor() {
        assertTrue(myRoom.isWestDoor());
    }

    @Test
    void setNorthDoor() {
        myRoom.setNorthDoor(myRoom.isNorthDoor());
        assertFalse(myRoom.isNorthDoor());
    }

    @Test
    void setEastDoor() {
        myRoom.setEastDoor(myRoom.isEastDoor());
        assertTrue(myRoom.isEastDoor());
    }

    @Test
    void setSouthDoor() {
        myRoom.setSouthDoor(myRoom.isSouthDoor());
        assertTrue(myRoom.isSouthDoor());
    }

    @Test
    void setWestDoor() {
        myRoom.setWestDoor(myRoom.isWestDoor());
        assertTrue(myRoom.isWestDoor());
    }

    @Test
    void getMonster() {
        AbstractMonster monster = MonsterFactory.createMonster("slime");
        myRoom.setMonster(monster);
        assertEquals(monster, myRoom.getMonster());
    }

    @Test
    void getItem() {
        AbstractItem item = PotionFactory.createPotion("health");
        myRoom.setItem(item);
        assertEquals(item, myRoom.getItem());
    }

    @Test
    void testEquals() {
        assertFalse(myRoom.equals(NeighborRoom));
        NeighborRoom.setCords(1,1);
        assertTrue(myRoom.equals(NeighborRoom));
    }
}