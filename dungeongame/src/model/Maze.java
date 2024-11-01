package dungeongame.src.model;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Maze {
    private final Room[][] myRooms;
    private final Point myPlayerCords;
    private final int mySize;

    Maze(int theMazeSize){
        mySize = theMazeSize;
        myRooms = new Room[theMazeSize][theMazeSize];
        myPlayerCords = new Point(0, 0);
    }

    public int getSize() {
        return mySize;
    }

    public Room[][] getRooms() {
        return myRooms;
    }

    public boolean isValidMove(int theX, int theY) {
        return false;
    }

    public boolean hasMonster(int theX, int theY) {
        return false;
    }

    public boolean hasItem(int theX, int theY) {
        return false;
    }

    public Point getPlayerCords() {
        return myPlayerCords;
    }

    public void generateMaze() {
        //initialize rooms
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                myRooms[i][j] = new Room();
                myRooms[i][j].setCords(i, j);
            }
        }

        //setup neighbor connectinos
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                if (i > 0) {
                    myRooms[i][j].setNorthNeighbor(myRooms[i - 1][j]);
                    myRooms[i - 1][j].setSouthNeighbor(myRooms[i][j]);
                }
                if (i < mySize - 1) {
                    myRooms[i][j].setSouthNeighbor(myRooms[i + 1][j]);
                    myRooms[i + 1][j].setNorthNeighbor(myRooms[i][j]);
                }
                if (j > 0) {
                    myRooms[i][j].setWestNeighbor(myRooms[i][j - 1]);
                    myRooms[i][j - 1].setEastNeighbor(myRooms[i][j]);
                }
                if (j < mySize - 1) {
                    myRooms[i][j].setEastNeighbor(myRooms[i][j + 1]);
                    myRooms[i][j + 1].setWestNeighbor(myRooms[i][j]);
                }
            }
        }

        makeTraversable();
        addRandomDoors();
    }

    private void makeTraversable(){
        Room curr = myRooms[0][0];
        Set<Room> visited = new HashSet<>();

        travers(curr,  visited);


    }

    private void travers(Room curr, Set<Room> visited){
        visited.add(curr);

        List<Room> validNeighbors = new ArrayList<>();

        if (curr.getNorthNeighbor() != null && !visited.contains(curr.getNorthNeighbor())) {
            validNeighbors.add(curr.getNorthNeighbor());
        }
        if (curr.getSouthNeighbor() != null && !visited.contains(curr.getSouthNeighbor())) {
            validNeighbors.add(curr.getSouthNeighbor());
        }
        if (curr.getWestNeighbor() != null && !visited.contains(curr.getWestNeighbor())) {
            validNeighbors.add(curr.getWestNeighbor());
        }
        if (curr.getEastNeighbor() != null && !visited.contains(curr.getEastNeighbor())) {
            validNeighbors.add(curr.getEastNeighbor());
        }

        Collections.shuffle(validNeighbors);

        for (Room neighbor : validNeighbors) {
            if (!visited.contains(neighbor)) {
                if (neighbor == curr.getNorthNeighbor()) {
                    curr.setNorthDoor(true);
                    neighbor.setSouthDoor(true);

                } else if (neighbor == curr.getSouthNeighbor()) {
                    curr.setSouthDoor(true);
                    neighbor.setNorthDoor(true);

                } else if (neighbor == curr.getWestNeighbor()) {
                    curr.setWestDoor(true);
                    neighbor.setEastDoor(true);

                } else if (neighbor == curr.getEastNeighbor()) {
                    curr.setEastDoor(true);
                    neighbor.setWestDoor(true);
                }

                travers(neighbor, visited);
            }
        }
    }

    private void addRandomDoors(){
        //add additional random doors to make less hallways
        Random random = new Random();
        int numRandomDoors = mySize;

        for (int i = 1; i <= numRandomDoors; i++) {
            int roomI  = random.nextInt(mySize-1) + 1;
            int roomJ = random.nextInt(mySize-1) + 1;

            Room roomToChange = myRooms[roomI][roomJ];

            List<Room> validDoorChanges = new ArrayList<>();

            if (!roomToChange.isNorthDoor()) {
                validDoorChanges.add(roomToChange.getNorthNeighbor());
            }
            if (!roomToChange.isEastDoor()) {
                validDoorChanges.add(roomToChange.getEastNeighbor());
            }
            if (!roomToChange.isSouthDoor()) {
                validDoorChanges.add(roomToChange.getSouthNeighbor());
            }
            if (!roomToChange.isWestDoor()) {
                validDoorChanges.add(roomToChange.getWestNeighbor());
            }

            Collections.shuffle(validDoorChanges);

            if(!validDoorChanges.isEmpty()){
                Room neighborToChange = validDoorChanges.getFirst();

                if (roomToChange.getNorthNeighbor() == neighborToChange && roomToChange.getNorthNeighbor() != null) {
                    roomToChange.setNorthDoor(true);
                    neighborToChange.setSouthDoor(true);

                } else if (roomToChange.getEastNeighbor() == neighborToChange && roomToChange.getEastNeighbor() != null) {
                    roomToChange.setEastDoor(true);
                    neighborToChange.setWestDoor(true);

                } else if (roomToChange.getSouthNeighbor() == neighborToChange && roomToChange.getSouthNeighbor() != null) {
                    roomToChange.setSouthDoor(true);
                    neighborToChange.setNorthDoor(true);

                } else if (roomToChange.getWestNeighbor() == neighborToChange && roomToChange.getWestNeighbor() != null) {
                    roomToChange.setWestDoor(true);
                    neighborToChange.setEastDoor(true);
                }
            }
        }
    }

    public void displayMaze() {

    }

    public void printMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isNorthDoor()) {
                    System.out.print(" _ _ ");
                } else {
                    System.out.print(" ___ ");
                }
            }
            System.out.println();

            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isWestDoor()) {
                    System.out.print("=");
                } else {
                    System.out.print("|");
                }

                if (myRooms[i][j].isSouthDoor()) {
                    System.out.print("_ _");
                } else {
                    System.out.print("___");
                }

                if (myRooms[i][j].isEastDoor()) {
                    System.out.print("=");
                } else {
                    System.out.print("|");
                }

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze(5);

        maze.generateMaze();
        maze.printMaze();
    }

//    private void createTestMaze(Maze maze){
//
//    }
}
