package dungeongame.src.test;

import dungeongame.src.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    /**
     * The test maze.
     */
    Maze myMaze;

    /**
     * Set up to take place before each test.
     */
    @BeforeEach
    public void setup() {
        myMaze = Maze.getInstance();
    }

    /**
     * Testing the setsize method to check if it set the width and height of the maze properly.
     */
    @Test
    void MazeSizeTest() {
        myMaze.setMazeSize(5);
        Room[][] mazeRooms = myMaze.getRooms();
        int mazeWidth = mazeRooms[0].length;
        int mazeHeight = mazeRooms.length;

        assertEquals(5, mazeWidth);
        assertEquals(5, mazeHeight);

        assertThrows(IllegalArgumentException.class, () -> myMaze.setMazeSize(-1));
    }

    /**
     * Test to check that the getRooms() method returns a deep copy and doesn't violate encapsulation.
     */
    @Test
    void getRoomsReturnsDeepCopyTest() {
        myMaze.setMazeSize(5);
        myMaze.generateMaze();
        Room[][] mazeRooms = myMaze.getRooms();

        myMaze.setRoomItem(0,0, null);
        mazeRooms[0][0].setItem(new HealthPotion());

        assertFalse(myMaze.roomHasItem(0, 0));
        assertNull(myMaze.getRoomItem(0, 0));
    }

    /**
     * Test to check the has monster method.
     */
    @Test
    void roomHasMonsterTest() {
        myMaze.setMazeSize(5);
        myMaze.generateMaze();
        myMaze.setRoomMonster(0,0,new Ogre(1,1,1,1,1,1,"Bob"));
        Room[][] mazeRooms = myMaze.getRooms();
        mazeRooms[0][0].setItem(new HealthPotion());

        assertEquals(true, myMaze.roomHasMonster(0,0));

        myMaze.setRoomMonster(0,0, null);
        assertEquals(false, myMaze.roomHasMonster(0,0));
    }

    /**
     * Test to check the has item method.
     */
    @Test
    void roomHasItemTest() {
        myMaze.setMazeSize(5);
        myMaze.generateMaze();
        myMaze.setRoomItem(0,0, new HealthPotion());
        Room[][] mazeRooms = myMaze.getRooms();
        mazeRooms[0][0].setItem(new HealthPotion());

        assertEquals(true, myMaze.roomHasItem(0,0));

        myMaze.setRoomItem(0,0, null);
        assertEquals(false, myMaze.roomHasItem(0,0));
    }

    /**
     * Test to check the maze generation makes the maze traversable and spawns in the proper items and monsters.
     */
    @Test
    void mazeGeneration() {
        myMaze.setMazeSize(5);
        myMaze.generateMaze();
        Room curr = myMaze.getRooms()[0][0];
        int[] roomsMonstersItems = {0, 0, 0, 0};
        Set<Room> visited = new HashSet<>();

        int[] theRoomsMonstersItemsData= traverseMaze(curr, visited, roomsMonstersItems);

        assertEquals(25, theRoomsMonstersItemsData[0], "Maze is not Traversable.");
        assertTrue(theRoomsMonstersItemsData[1] > 0, "Traverser did not count any monsters.");
        assertTrue(theRoomsMonstersItemsData[2] > 0, "Traverser did not count any items.");
        assertEquals(4,theRoomsMonstersItemsData[3], "Traverser did not count the right number of Pillars.");
    }

    /**
     * Private helper method for the generate maze test.
     */
    private int[] traverseMaze(final Room theCurr, final Set<Room> visited, int[] theRoomsMonstersItems) {
        visited.add(theCurr);
        if(theCurr.getMonster() != null) {
            theRoomsMonstersItems[1] += 1;
        }
        if(theCurr.getItem() != null) {
            theRoomsMonstersItems[2] += 1;
        }
        if(theCurr.getItem() instanceof Pillar) {
            theRoomsMonstersItems[3] += 1;
        }

        List<Room> validNeighbors = new ArrayList<>();

        if (theCurr.getNorthNeighbor() != null && !visited.contains(theCurr.getNorthNeighbor())) {
            validNeighbors.add(theCurr.getNorthNeighbor());
        }
        if (theCurr.getSouthNeighbor() != null && !visited.contains(theCurr.getSouthNeighbor())) {
            validNeighbors.add(theCurr.getSouthNeighbor());
        }
        if (theCurr.getWestNeighbor() != null && !visited.contains(theCurr.getWestNeighbor())) {
            validNeighbors.add(theCurr.getWestNeighbor());
        }
        if (theCurr.getEastNeighbor() != null && !visited.contains(theCurr.getEastNeighbor())) {
            validNeighbors.add(theCurr.getEastNeighbor());
        }

        for (Room neighbor : validNeighbors) {
            if (!visited.contains(neighbor)) {
                traverseMaze(neighbor, visited, theRoomsMonstersItems);
            }
        }

        theRoomsMonstersItems[0] = visited.size();
        return theRoomsMonstersItems;
    }
}
