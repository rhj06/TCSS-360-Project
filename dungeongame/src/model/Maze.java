package dungeongame.src.model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Represents the dungeon in the dungeon adventure game.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 1.0
 */
final public class Maze implements Serializable {
    @Serial
    private static final long serialVersionUID = 3545354534L;
    /** unique instance of Maze */
    private static Maze uniqueInstance;
    /** Item generation percent */
    private static final int ITEM_GEN_PERCENT = 30;
    /** Monster spawn rate */
    private static final int MONSTER_SPAWN_RATE = 30;
    /** Constant of 2 */
    private static final int TWO = 2;
    /** Constant of 3 */
    private static final int THREE = 3;
    /** Constant of 4 */
    private static final int FOUR = 4;
    /** Constant of 5 */
    private static final int FIVE = 5;
    /** Constant of 6 */
    private static final int SIX = 6;
    /** Constant of 7 */
    private static final int SEVEN = 7;
    /** Constant of 8 */
    private static final int EIGHT = 8;
    /** Constant of 100 */
    private static final int HUNDRED = 100;
    /** Property change support */
    private final PropertyChangeSupport myPCS;
    /** Matrix containing each room*/
    private Room[][] myRooms;
    /** Coordinates for Player */
    private Point myPlayerCords;
    /** Size of matrix */
    private int mySize;
    /** Does an exit exist? */
    private boolean myHasExit;


    /**
     * Maze Constructor
     */
    private Maze(){
        myPCS = new PropertyChangeSupport(this);
        mySize = 0;
        myRooms = new Room[0][0];
        myPlayerCords = new Point(0, 0);
        myHasExit = false;
    }

    /**
     * get the instance of the maze
     * creates instance if maze does not exist
     * @return uniqueInstance of Maze
     */
    public static Maze getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Maze();
        }
        return uniqueInstance;
    }

    /**
     * update Maze object from other Maze
     * @param theOtherMaze Other Maze Object
     */
    public void updateFrom(final Maze theOtherMaze) {
        myRooms = theOtherMaze.myRooms;
        myPlayerCords = theOtherMaze.myPlayerCords;
        mySize = theOtherMaze.mySize;
    }

    /**
     * Set the size of Room[][] matrix
     * @param theMazeSize Size of Matrix
     */
    public void setMazeSize(final int theMazeSize){
        if (theMazeSize <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }
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
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(myRooms);
            objectOutputStream.flush();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Room[][]) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Deep Copy of Maze array failed", e);
        }
        //return myRooms;
    }

    /**
     * does exit exist?
     * @return true if exit exists
     */
    public boolean hasExit() {
        return myHasExit;
    }

    /**
     * Returns true if the room at the target coordinates contains a monster.
     */
    public boolean roomHasMonster(final int theI, final int theJ) {
        return myRooms[theI][theJ].getMonster() != null;
    }

    /**
     * Get monster contained in Room
     * @param theI Y Coordinate of Room
     * @param theJ X Coordinate of Room
     * @return Monster contained in Room[theI][theJ]
     */
    public AbstractMonster getRoomMonster(final int theI, final int theJ){
        return myRooms[theI][theJ].getMonster();
    }

    /**
     * Set a monster in a given room
     * @param theI Y coordinate of Room
     * @param theJ X coordinate of Room
     * @param theMonster Monster Object
     */
    public void setRoomMonster(final int theI, final int theJ, final AbstractMonster theMonster){
        myRooms[theI][theJ].setMonster(theMonster);
    }

    /**
     * Returns true if the room at the target coordinates contains an item.
     */
    public boolean roomHasItem(final int theI, final int theJ){
        return myRooms[theI][theJ].getItem() != null;
    }

    /**
     * Get item contained in room
     * @param theI Y coordinate of Room
     * @param theJ X coordinate of Room
     * @return item in Room[theI][theJ]
     */
    public Item getRoomItem(final int theI, final int theJ){
        return myRooms[theI][theJ].getItem();
    }

    /**
     * Set an Item in a given room
     * @param theI Y coordinate of Room
     * @param theJ X coordinate of Room
     * @param theItem Item Object
     */
    public void setRoomItem(final int theI, final int theJ, final Item theItem){
        myRooms[theI][theJ].setItem(theItem);
    }

    /**
     * Returns the current coordinates of the player as a point object.
     */
    public Point getPlayerCords() {
        return myPlayerCords;
    }

    /**
     * Add property change listener
     * @param theListener Property Change Listener
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }

    /**
     * remove property change listener
     * @param theListener Property Change Listener
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }

    /**
     * Fire Property Change Listener with given message
     * @param theMessage theMessage to send
     */
    public void notifyMessage(final String theMessage) {
            myPCS.firePropertyChange("change in direction", null, theMessage);
    }

    /**
     * Can Player travel North
     * @return true if player can move
     */
    public boolean canGoNorth(){
        boolean canGoNorth = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isNorthDoor()){
            canGoNorth = true;
        }
        return canGoNorth;
    }

    /**
     * Can Player travel East
     * @return true if player can move
     */
    public boolean canGoEast(){
        boolean canGoEast = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isEastDoor()){
            canGoEast = true;
        }
        return canGoEast;
    }

    /**
     * Can Player travel South
     * @return true if player can move
     */
    public boolean canGoSouth(){
        boolean canGoSouth = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isSouthDoor()){
            canGoSouth = true;
        }
        return canGoSouth;
    }

    /**
     * Can Player travel West
     * @return true if player can move
     */
    public boolean canGoWest(){
        boolean canGoWest = false;
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x];
        if (playerStartRoom.isWestDoor()){
            canGoWest = true;
        }
        return canGoWest;
    }

    /**
     * Move Player coordinates north in Room Matrix
     */
    public void goNorth(){
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x]; //Needed?
        if (canGoNorth()){
            Point oldCords = myPlayerCords;
            myPlayerCords.setLocation(myPlayerCords.x, myPlayerCords.y-1);
            myPCS.firePropertyChange("Player Moved", oldCords, myPlayerCords);
            notifyMessage("change in direction");
        }
    }

    /**
     * Move Player coordinates east in Room Matrix
     */
    public void goEast(){
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x]; //Needed?
        if (canGoEast()){
            Point oldCords = myPlayerCords;
            myPlayerCords.setLocation(myPlayerCords.x+1, myPlayerCords.y);
            myPCS.firePropertyChange("Player Moved", oldCords, myPlayerCords);
            notifyMessage("change in direction");
        }
    }

    /**
     * Move Player coordinates south in Room Matrix
     */
    public void goSouth(){
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x]; //Needed?
        if (canGoSouth()){
            Point oldCords = myPlayerCords;
            myPlayerCords.setLocation(myPlayerCords.x, myPlayerCords.y+1);
            myPCS.firePropertyChange("Player Moved", oldCords, myPlayerCords);
            notifyMessage("change in direction");
        }
    }

    /**
     * Move Player coordinates west in Room Matrix
     */
    public void goWest(){
        Room playerStartRoom = myRooms[myPlayerCords.y][myPlayerCords.x]; //Needed?
        if (canGoWest()){
            Point oldCords = myPlayerCords;
            myPlayerCords.setLocation(myPlayerCords.x-1, myPlayerCords.y);
            myPCS.firePropertyChange("Player Moved", oldCords, myPlayerCords);
            notifyMessage("change in direction");
        }
    }

    /**
     * Get Neighboring rooms of Players current location
     * [1][2][3]
     * [4][P][5]
     * [6][7][8]
     * @param theI Y Coordinate of player
     * @param theJ X Coordinate of player
     * @return Room[8] of neighboring rooms of player
     */
    public Room[] getNeighborsClockwise(final int theI, final int theJ) {
        Room room = myRooms[theI][theJ];
        Room[] rooms = new Room[EIGHT]; // Default initialized to null

        if (room.getNorthNeighbor() != null) {
            rooms[1] = room.getNorthNeighbor();

            if (room.getNorthNeighbor().getEastNeighbor() != null) {
                rooms[TWO] = room.getNorthNeighbor().getEastNeighbor();
            }
        }

        if (room.getEastNeighbor() != null) {
            rooms[FOUR] = room.getEastNeighbor();

            if (room.getEastNeighbor().getSouthNeighbor() != null) {
                rooms[SEVEN] = room.getEastNeighbor().getSouthNeighbor();
            }
        }

        if (room.getSouthNeighbor() != null) {
            rooms[SIX] = room.getSouthNeighbor();

            if (room.getSouthNeighbor().getWestNeighbor() != null) {
                rooms[FIVE] = room.getSouthNeighbor().getWestNeighbor();
            }
        }

        if (room.getWestNeighbor() != null) {
            rooms[THREE] = room.getWestNeighbor();

            if (room.getWestNeighbor().getNorthNeighbor() != null) {
                rooms[0] = room.getWestNeighbor().getNorthNeighbor();
            }
        }

        return rooms;
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
        spawnPlayerStart();
    }

    /**
     * Makes Room[][] Traversable
     */
    private void makeTraversable(){
        Room curr = myRooms[0][0];
        Set<Room> visited = new HashSet<>();

        travers(curr,  visited);
    }

    /**
     * Traverse through Room[][]
     * @param theCurr Current Room
     * @param theVisited Visited Room
     */
    private void travers(final Room theCurr, final Set<Room> theVisited){
        theVisited.add(theCurr);

        List<Room> validNeighbors = new ArrayList<>();

        if (theCurr.getNorthNeighbor() != null && !theVisited.contains(theCurr.getNorthNeighbor())) {
            validNeighbors.add(theCurr.getNorthNeighbor());
        }
        if (theCurr.getSouthNeighbor() != null && !theVisited.contains(theCurr.getSouthNeighbor())) {
            validNeighbors.add(theCurr.getSouthNeighbor());
        }
        if (theCurr.getWestNeighbor() != null && !theVisited.contains(theCurr.getWestNeighbor())) {
            validNeighbors.add(theCurr.getWestNeighbor());
        }
        if (theCurr.getEastNeighbor() != null && !theVisited.contains(theCurr.getEastNeighbor())) {
            validNeighbors.add(theCurr.getEastNeighbor());
        }

        Collections.shuffle(validNeighbors);

        for (Room neighbor : validNeighbors) {
            if (!theVisited.contains(neighbor)) {
                if (neighbor == theCurr.getNorthNeighbor()) {
                    theCurr.setNorthDoor(true);
                    neighbor.setSouthDoor(true);

                } else if (neighbor == theCurr.getSouthNeighbor()) {
                    theCurr.setSouthDoor(true);
                    neighbor.setNorthDoor(true);

                } else if (neighbor == theCurr.getWestNeighbor()) {
                    theCurr.setWestDoor(true);
                    neighbor.setEastDoor(true);

                } else if (neighbor == theCurr.getEastNeighbor()) {
                    theCurr.setEastDoor(true);
                    neighbor.setWestDoor(true);
                }

                travers(neighbor, theVisited);
            }
        }
    }

    /**
     * Add random doors to Room
     */
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

    /**
     * Generate random item for Room
     */
    private void generateItems() {
        Random random = new Random();
        List<Integer> items = new ArrayList<>(Arrays.asList(1, TWO, THREE));

        for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
                int gen = random.nextInt(HUNDRED);
                if (gen <= ITEM_GEN_PERCENT) {
                    Collections.shuffle(items);
                    int potion = items.getFirst();
                    if (potion == 1) {
                        myRooms[i][j].setItem(new HealthPotion());
                    } else if (potion == TWO) {
                        myRooms[i][j].setItem(new SpeedPotion());
                    } else if (potion == THREE) {
                        myRooms[i][j].setItem(new VisionPotion());
                    }
                    System.out.println("Item added at (" + i + ", " + j + "): " + myRooms[i][j].getItem());
                }
            }
        }
    }

    /**
     * Generate Pillar in Room containing Mini_Boss
     */
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
        BossFactory bossFactory = new BossFactory();

        while (!pillars.isEmpty()) {
            Room room = myRooms[random.nextInt(mySize)][random.nextInt(mySize)];
            if (!(room.getItem() instanceof Pillar)){
                room.setItem(pillars.getFirst());
                pillars.remove(pillars.getFirst());
            }

            if(!(room.getMonster() instanceof Boss)){
                Boss miniBoss = bossFactory.createBoss("mini_boss");
                room.setMonster(miniBoss);
            }

        }
    }

    /**
     * Generate Random Monster in Room
     */
    private void generateMonsters(){
        ArrayList<String> monsterTypes = new ArrayList<>();
        monsterTypes.add("Goblin");
        monsterTypes.add("Ogre");
        monsterTypes.add("Skeleton");
        monsterTypes.add("Slime");

        Random random = new Random();

        int monstersToSpawn = ((mySize*mySize) * MONSTER_SPAWN_RATE) / HUNDRED;

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
     * Spawn exit in random location in Room[][]
     */
    void spawnExit(){
        Random random = new Random();
        int i = 0;
        int j = 0;
        int side = random.nextInt(FOUR);
        BossFactory bossFactory = new BossFactory(); //Is this needed?

        boolean roomSpawnable = false;

        while (!roomSpawnable) {
            if (side == 0) {
//                i = 0;
                j = random.nextInt(mySize);
            } else if (side == 1) {
                i = random.nextInt(mySize);
                j = mySize - 1;
            } else if (side == TWO) {
                i = mySize - 1;
                j = random.nextInt(mySize);
            } else {
//                if (side == THREE)
                i = random.nextInt(mySize);
//                j = 0;
            }

            if(myRooms[i][j] != myRooms[myPlayerCords.y][myPlayerCords.x]){
                roomSpawnable = true;
            }

        }
        Boss finalBoss = new BossFactory().createBoss("final_boss");
        myRooms[i][j].setMonster(finalBoss);
        myHasExit = true;
        System.out.println("Exit has been spawned.");
    }

    /**
     * Spawn Player on an edge of the Room[][]
     */
    void spawnPlayerStart(){
        Random random = new Random();
        int i = 0;
        int j = 0;
        boolean roomSpawnable = false;

        while (!roomSpawnable) {
            int side = random.nextInt(4);

            if (side == 0) {
                i = 0;
                j = random.nextInt(mySize);
            } else if (side == 1) {
                i = random.nextInt(mySize);
                j = mySize - 1;
            } else if (side == TWO) {
                i = mySize - 1;
                j = random.nextInt(mySize);
            } else {
//                if (side == THREE)
                i = random.nextInt(mySize);
                j = 0;
            }

            roomSpawnable = !(myRooms[i][j].getItem() instanceof Pillar);
        }

        myPlayerCords.x = j;
        myPlayerCords.y = i;

        myRooms[i][j].setItem(null);
        myRooms[i][j].setMonster(null);

        System.out.println("Exit has been spawned.");
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
                } else if (myRooms[i][j].isSouthDoor() && myRooms[i][j].getMonster() instanceof Boss) {
                    System.out.print(" B ");
                } else if (myRooms[i][j].getMonster() instanceof Goblin) {
                    System.out.print("_G_");
                } else if (myRooms[i][j].getMonster() instanceof Ogre) {
                    System.out.print("_O_");
                } else if (myRooms[i][j].getMonster() instanceof Skeleton) {
                    System.out.print("_S_");
                } else if (myRooms[i][j].getMonster() instanceof Slime) {
                    System.out.print("_L_");
                } else if (myRooms[i][j].getMonster() instanceof Boss) {
                    System.out.print("_B_");
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
     * Print Room[][] only containing Players location
     * Testing player movement functions
     */
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
}
