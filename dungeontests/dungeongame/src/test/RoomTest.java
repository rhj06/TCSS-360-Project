package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing the {@link Room} class.
 * <p>
 * This class includes tests for Room getCords, get rooms neighbors,
 * if room has a door north, south, east, and/or west, setting rooms neighbors,
 * getting monster contained in a room, getting an item contained in a room,
 * and a equals override method
 * </p>
 */
class RoomTest {

    private Room myRoom;
    private Room NeighborRoom;

    /**
     * Initialize myRoom and NeighborRoom
     */
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

    /**
     * Get Coordinates of myRoom
     */
    @Test
    void getCords() {
        Room test = new Room();
        test.setCords(1,1);

        assertEquals(test.getCords(), myRoom.getCords());
    }

    /**
     * get North Neighbor of myRoom
     */
    @Test
    void getNorthNeighbor() {
        assertNull(myRoom.getNorthNeighbor());
    }

    /**
     * get East Neighbor of myRoom
     */
    @Test
    void getEastNeighbor() {
        myRoom.setEastNeighbor(NeighborRoom);
        assertEquals(myRoom.getEastNeighbor(), NeighborRoom);
    }

    /**
     * get South Neighbor of myRoom
     */
    @Test
    void getSouthNeighbor() {
        myRoom.setSouthNeighbor(NeighborRoom);
        assertEquals(myRoom.getSouthNeighbor(), NeighborRoom);
    }

    /**
     * get West Neighbor of myRoom
     */
    @Test
    void getWestNeighbor() {
        myRoom.setWestNeighbor(NeighborRoom);
        assertEquals(myRoom.getWestNeighbor(), NeighborRoom);
    }

    /**
     * tests if myRoom has a North Door
     */
    @Test
    void isNorthDoor() {
        assertFalse(myRoom.isNorthDoor());
    }

    /**
     * tests if myRoom has an East Door
     */
    @Test
    void isEastDoor() {
        assertTrue(myRoom.isEastDoor());
    }

    /**
     * tests if myRoom has a South Door
     */
    @Test
    void isSouthDoor() {
        assertTrue(myRoom.isSouthDoor());
    }

    /**
     * tests if myRoom has a West Door
     */
    @Test
    void isWestDoor() {
        assertTrue(myRoom.isWestDoor());
    }

    /**
     * Set North Door of myRoom
     */
    @Test
    void setNorthDoor() {
        myRoom.setNorthDoor(myRoom.isNorthDoor());
        assertFalse(myRoom.isNorthDoor());
    }

    /**
     * Set East Door of myRoom
     */
    @Test
    void setEastDoor() {
        myRoom.setEastDoor(myRoom.isEastDoor());
        assertTrue(myRoom.isEastDoor());
    }

    /**
     * Set South Door of myRoom
     */
    @Test
    void setSouthDoor() {
        myRoom.setSouthDoor(myRoom.isSouthDoor());
        assertTrue(myRoom.isSouthDoor());
    }

    /**
     * Set West Door of myRoom
     */
    @Test
    void setWestDoor() {
        myRoom.setWestDoor(myRoom.isWestDoor());
        assertTrue(myRoom.isWestDoor());
    }

    /**
     * Test for setting and getting monster in myRoom
     */
    @Test
    void getMonster() {
        AbstractMonster monster = MonsterFactory.createMonster("slime");
        myRoom.setMonster(monster);
        assertEquals(monster, myRoom.getMonster());
    }

    /**
     * Test for setting and getting item in myRoom
     */
    @Test
    void getItem() {
        AbstractItem item = PotionFactory.createPotion("health");
        myRoom.setItem(item);
        assertEquals(item, myRoom.getItem());
    }

    /**
     * Test for if myRoom and NeighborRoom have the same coordinates
     */
    @Test
    void testEquals() {
        assertNotEquals(myRoom, NeighborRoom);
        NeighborRoom.setCords(1,1);
        assertEquals(myRoom, NeighborRoom);
    }
}