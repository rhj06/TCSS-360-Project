package dungeongame.src.model;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Represents the dungeon in the dungeon adventure game.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/10/2024
 */
final public class Maze implements Serializable {
    @Serial
    private static final long serialVersionUID = 3545354534L;
    private static Maze uniqueInstance;

    private static final int ITEM_GEN_PERCENT = 30;
    private static final int MONSTER_SPAWN_RATE = 30;

    private Room[][] myRooms;
    private Point myPlayerCords;
    private int mySize;

    /**
     * Constructor for the maze object.
     *
     * //@param theMazeSize, the number of room columns and rows that make up the dungeon.
     */
    private Maze(){
        mySize = 0;
        myRooms = new Room[0][0];
        myPlayerCords = new Point(0, 0);
    }

    public static Maze getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Maze();
        }
        return uniqueInstance;
    }

    public void updateFrom(Maze theOtherMaze) {
        myRooms = theOtherMaze.myRooms;
        myPlayerCords = theOtherMaze.myPlayerCords;
        mySize = theOtherMaze.mySize;
    }

    public void setMazeSize(int theMazeSize){
        mySize = theMazeSize;
        myRooms = new Room[theMazeSize][theMazeSize];
    }

    /**
     * Returns the size of the dungeon, number of rows and columns.
     */
    public int getSize() {
        return mySize;
    }

    /**
     * Returns 2D array that stores the rooms that make up the maze.
     */
    public Room[][] getRooms() {
        return myRooms;
    }

    /**
     * Returns true if the move to the input coordinates is valid, that is if the coordinates are in the maze,
     * if the player is in a neighboring room, and if there is a door from the current room to the target room.
     *
     * @param theX The column value the target room.
     * @param theY The row value of the target room.
     */
    public boolean isValidMove(final int theX, final int theY) {
        return false;
    }

    /**
     * Returns true if the room at the target coordinates contains a monster.
     *
     * @param theX The column value the target room.
     * @param theY The row value of the target room.
     */
    public boolean hasMonster(final int theX, final int theY) {
        return false;
    }

    /**
     * Returns true if the room at the target coordinates contains an item.
     *
     * @param theX The column value the target room.
     * @param theY The row value of the target room.
     */
    public boolean hasItem(final int theX, final int theY) {
        return false;
    }

    /**
     * Returns the current coordinates of the player as a point object.
     */
    public Point getPlayerCords() {
        return myPlayerCords;
    }

    public boolean goNorth(){
        boolean canGoNorth = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isNorthDoor()){
            myPlayerCords.setLocation(myPlayerCords.x, myPlayerCords.y-1);
            canGoNorth = true;
        }
        return canGoNorth;
    }

    public boolean goEast(){
        boolean canGoEast = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isEastDoor()){
            myPlayerCords.setLocation(myPlayerCords.x+1, myPlayerCords.y);
            canGoEast = true;
        }
        return canGoEast;
    }

    public boolean goSouth(){
        boolean canGoSouth = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isSouthDoor()){
            myPlayerCords.setLocation(myPlayerCords.x, myPlayerCords.y+1);
            canGoSouth = true;
        }
        return canGoSouth;
    }

    public boolean goWest(){
        boolean canGoWest = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isWestDoor()){
            myPlayerCords.setLocation(myPlayerCords.x-1, myPlayerCords.y);
            canGoWest = true;
        }
        return canGoWest;
    }



    /**
     * Generates the maze, creating connections between the rooms and setting up doors and walls.
     */
    public void generateMaze() {
        //initialize rooms
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                myRooms[i][j] = new Room();
                myRooms[i][j].setCords(i, j);
            }
        }

        //setup neighbor connections
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
        generateItems();
        generatePillars();
        generateMonsters();
    }

    private void makeTraversable(){
        Room curr = myRooms[0][0];
        Set<Room> visited = new HashSet<>();

        travers(curr,  visited);


    }

    private void travers(final Room curr, final Set<Room> visited){
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

    private void generateItems(){
        Random random = new Random();
        List<Integer> items = new ArrayList<>();
        items.add(1);
        items.add(2);
        items.add(3);

        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                int gen = random.nextInt(100);
                if(gen <= ITEM_GEN_PERCENT) {
                    Collections.shuffle(items);
                    int potion = items.getFirst();
                    if(potion == 1){
                        myRooms[i][j].setItem(new HealthPotion());
                    } else if (potion == 2){
                        myRooms[i][j].setItem(new SpeedPotion());
                    } else if (potion == 3){
                        myRooms[i][j].setItem(new VisionPotion());
                    }
                }
            }
        }
    }

    private void generatePillars(){
        Pillar EncapsulationPillar = new Pillar("Encapsulation Pillar");
        Pillar InheritancePillar = new Pillar("Inheritance Pillar");
        Pillar PolymorphismPillar = new Pillar("Polymorphism Pillar");
        Pillar AbstractionPillar = new Pillar("Abstraction Pillar");

        List<Pillar> pillars = new ArrayList<>();
        pillars.add(EncapsulationPillar);
        pillars.add(InheritancePillar);
        pillars.add(PolymorphismPillar);
        pillars.add(AbstractionPillar);

        Random random = new Random();

        while (!pillars.isEmpty()) {
            Room room = myRooms[random.nextInt(mySize)][random.nextInt(mySize)];
            if (!(room.getItem() instanceof Pillar)){
                room.setItem(pillars.getFirst());
                pillars.remove(pillars.getFirst());
            }
        }
    }

    private void generateMonsters(){
        ArrayList<String> monsterTypes = new ArrayList<>();
        monsterTypes.add("Goblin");
        monsterTypes.add("Ogre");
        monsterTypes.add("Skeleton");
        monsterTypes.add("Slime");

        Random random = new Random();

        int monstersToSpawn = ((mySize*mySize) * MONSTER_SPAWN_RATE) / 100;

        while(monstersToSpawn != 0) {
            Room room = myRooms[random.nextInt(mySize)][random.nextInt(mySize)];
            if (room.getMonster() == null){
                Collections.shuffle(monsterTypes);
                AbstractMonster monster = MonsterFactory.createMonster(monsterTypes.getFirst());
                room.setMonster(monster);
                monstersToSpawn--;
            }
        }
    }

    /**
     * Prints the maze to the console.
     */
    public void printMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isNorthDoor()) {
                    System.out.print("     ");
                } else {
                    System.out.print(" ___ ");
                }
            }
            System.out.println();

            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isWestDoor()) {
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }

                if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getItem() == null) {
                    System.out.print("   ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getItem() instanceof HealthPotion) {
                    System.out.print(" H ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getItem() instanceof SpeedPotion) {
                    System.out.print(" S ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getItem() instanceof VisionPotion) {
                    System.out.print(" V ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getItem() instanceof Pillar) {
                    System.out.print(" P ");
                } else if (myRooms[i][j].getItem() instanceof HealthPotion) {
                    System.out.print("_H_");
                } else if (myRooms[i][j].getItem() instanceof SpeedPotion) {
                    System.out.print("_S_");
                } else if (myRooms[i][j].getItem() instanceof VisionPotion) {
                    System.out.print("_V_");
                } else if (myRooms[i][j].getItem() instanceof Pillar) {
                    System.out.print("_P_");
                } else {
                    System.out.print("___");
                }

                if (myRooms[i][j].isEastDoor()) {
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }

            }
            System.out.println();
        }
    }

    /**
     * Prints the maze to the console.
     */
    public void printMonsterMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isNorthDoor()) {
                    System.out.print("     ");
                } else {
                    System.out.print(" ___ ");
                }
            }
            System.out.println();

            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isWestDoor()) {
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }

                if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getMonster() == null) {
                    System.out.print("   ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getMonster() instanceof Goblin) {
                    System.out.print(" G ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getMonster() instanceof Ogre) {
                    System.out.print(" O ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getMonster() instanceof Skeleton) {
                    System.out.print(" S ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getMonster() instanceof Slime) {
                    System.out.print(" L ");
                } else if (myRooms[i][j].getMonster() instanceof Goblin) {
                    System.out.print("_G_");
                } else if (myRooms[i][j].getMonster() instanceof Ogre) {
                    System.out.print("_O_");
                } else if (myRooms[i][j].getMonster() instanceof Skeleton) {
                    System.out.print("_S_");
                } else if (myRooms[i][j].getMonster() instanceof Slime) {
                    System.out.print("_L_");
                } else {
                    System.out.print("___");
                }

                if (myRooms[i][j].isEastDoor()) {
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }

            }
            System.out.println();
        }
    }

//testing player movement functions
    public void printPlayerCordMaze() {
        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isNorthDoor()) {
                    System.out.print("     ");
                } else {
                    System.out.print(" ___ ");
                }
            }
            System.out.println();

            for (int j = 0; j < mySize; j++) {
                if (myRooms[i][j].isWestDoor()) {
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }

                if (myRooms[i][j].isSouthDoor() && myRooms[myPlayerCords.y][myPlayerCords.x] != myRooms[i][j]) {
                    System.out.print("   ");
                } else if (myRooms[i][j].isSouthDoor() && myRooms[myPlayerCords.y][myPlayerCords.x] == myRooms[i][j]) {
                    System.out.print(" P ");
                } else if (myRooms[myPlayerCords.y][myPlayerCords.x] == myRooms[i][j]) {
                    System.out.print("_P_");
                } else {
                    System.out.print("___");
                }

                if (myRooms[i][j].isEastDoor()) {
                    System.out.print(" ");
                } else {
                    System.out.print("|");
                }

            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Maze maze = Maze.getInstance();
        maze.setMazeSize(5);

//

        maze.generateMaze();
        maze.printMaze();
        maze.printPlayerCordMaze();
        maze.printMonsterMaze();
//
//        maze.goEast();
//        maze.printPlayerCordMaze();
//        maze.goSouth();
//        maze.printPlayerCordMaze();
//        maze.goWest();
//        maze.printPlayerCordMaze();
//        maze.goNorth();
//        maze.printPlayerCordMaze();
//
//        maze.goSouth();
//        maze.printPlayerCordMaze();
//        maze.goEast();
//        maze.printPlayerCordMaze();
//        maze.goNorth();
//        maze.printPlayerCordMaze();
//        maze.goWest();
//        maze.printPlayerCordMaze();

//        AbstractMonster goblin = MonsterFactory.createMonster("Goblin");
//        System.out.println(goblin.toString());
    }
}
