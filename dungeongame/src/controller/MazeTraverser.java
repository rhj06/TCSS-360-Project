package dungeongame.src.controller;

import dungeongame.src.model.*;
import dungeongame.src.view.ArenaScreen;
import dungeongame.src.view.InventoryScreen;
import dungeongame.src.view.RoomDescription;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Manages player movement in the Maze.
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 12/12/2024
 */
public class MazeTraverser {
    /** The singleton instance of the MazeTraverser class. */
    private static MazeTraverser uniqueInstance;

    /** The singleton instance of the Maze class. */
    private final Maze myMaze;

    /** The coordinates of the Player in the maze. */
    private final Point myPlayerCords;

    /** The player object. */
    private Player myPlayer;

    /** The description of the room the player is in. */
    private RoomDescription myRoomDescription;

    /** The inventory screen to be displayed when called. */
    private InventoryScreen inventoryScreen;

    /** Property change support to fire property change events. */
    private final PropertyChangeSupport myPcs;

    /**
     * Private constructor for the MazeTraverser object
     */
    private MazeTraverser(){
        myMaze = Maze.getInstance();
        myPlayerCords = myMaze.getPlayerCords();
        myPlayer = null;
        myRoomDescription = null;
        myPcs = new PropertyChangeSupport(this);
    }

    /**
     * Getter for the singleton instance of the MazeTraverser class.
     */
    public static MazeTraverser getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new MazeTraverser();
        }
        return uniqueInstance;
    }

    /**
     * Sets the player field.
     *
     * @param thePlayer thePlayer that is traversing the Maze.
     */
    public void setPlayer(Player thePlayer){
        myPlayer = thePlayer;
    }

    /**
     * Sets the RoomDescription field.
     *
     * @param theRoomDescription theRoomDescription of the room the player is in.
     */
    public void setRoomDescription(RoomDescription theRoomDescription){
        myRoomDescription = theRoomDescription;
    }

    /**
     * Sets the Inventory screen field.
     *
     * @param theInventoryScreen the InventoryScreen to be displayed when called.
     */
    public void setInventoryScreen(InventoryScreen theInventoryScreen){
        inventoryScreen = theInventoryScreen;
    }

    /**
     * Adds a property change listener to the MazeTraverser.
     *
     * @param theListener the listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * Changes the players coordinates in the maze, in accordance with the Maze instance.
     *
     * @param theDir the direction the player has chosen to go.
     */
    public void movePlayer(Directions theDir){
        if(theDir == Directions.NORTH){
            if(myMaze.canGoNorth()) {
                myMaze.goNorth();
                myRoomDescription.updateDescription("New Coordinates I = " + myMaze.getPlayerCords().y + ", J = " + myMaze.getPlayerCords().x);
            } else {
                myRoomDescription.updateDescription("Can't go north");
                System.out.println("Can't go North");
            }
        } else if(theDir == Directions.EAST) {
            if (myMaze.canGoEast()) {
                myMaze.goEast();
                myRoomDescription.updateDescription("New Coordinates I = " + myMaze.getPlayerCords().y + ", J = " + myMaze.getPlayerCords().x);
            } else {
                myRoomDescription.updateDescription("Can't go east");
                System.out.println("Can't go East");
            }
        }else if(theDir == Directions.SOUTH){
            if(myMaze.canGoSouth()) {
                myMaze.goSouth();
                myRoomDescription.updateDescription("New Coordinates I = " + myMaze.getPlayerCords().y + ", J = " + myMaze.getPlayerCords().x);
            } else {
                myRoomDescription.updateDescription("Can't go south");
                System.out.println("Can't go South");
            }
        } else if(theDir == Directions.WEST){
            if(myMaze.canGoWest()) {
                myMaze.goWest();
                myRoomDescription.updateDescription("New Coordinates I = " + myMaze.getPlayerCords().y + ", J = " + myMaze.getPlayerCords().x);
            } else {
                myRoomDescription.updateDescription("Can't go west");
                System.out.println("Can't go West");
            }
        }

        if(myMaze.roomHasItem(myPlayerCords.y, myPlayerCords.x)){
            Item item = myMaze.getRoomItem(myPlayerCords.y, myPlayerCords.x);

            if (inventoryScreen != null) {
                inventoryScreen.addPotion();
            }
            PlayerInventory.getInstance().addItem(item);
            myRoomDescription.updateDescription((item.toString() + " has been added to the inventory"));
            System.out.println(item.toString() + " has been added to the inventory");
            myMaze.setRoomItem(myPlayerCords.y, myPlayerCords.x, null);
        }

        if (myMaze.roomHasMonster(myPlayerCords.y, myPlayerCords.x)) {
            AbstractMonster monster = myMaze.getRoomMonster(myPlayerCords.y, myPlayerCords.x);
            myRoomDescription.updateDescription("You have encountered a monster: " + monster);
            Arena arena = new Arena(myPlayer, monster);
            new ArenaScreen(myPlayer, monster, arena);

            arena.addPropertyChangeListener(evt -> {
                if ("final_boss_killed".equals(evt.getPropertyName())) {
                    myPcs.firePropertyChange("final_boss_killed", false, true);
                }
            });

            if (((AbstractDungeonCharacter) myPlayer).getHealth() <= 0) {
                firePlayerDeadEvent();
            }
        }

        System.out.println("Items");
        myMaze.printMaze();
        System.out.println("Player");
        myMaze.printPlayerCordMaze();
        System.out.println("Monsters");
        myMaze.printMonsterMaze();
    }

    /**
     * Fires a property change event notifying listeners the player has died.
     */
    public void firePlayerDeadEvent() {
        myPcs.firePropertyChange("player_dead", false, true);
    }

}
