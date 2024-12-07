package dungeongame.src.controller;

import dungeongame.src.model.*;
import dungeongame.src.view.ArenaScene;
import dungeongame.src.view.ArenaScreen;
import dungeongame.src.view.InventoryScreen;
import dungeongame.src.view.RoomDescription;

import java.awt.*;

public class MazeTraverser {
    private static MazeTraverser uniqueInstance;

    private Maze myMaze;
    private Point myPlayerCords;
    private Player myPlayer;
    private RoomDescription myRoomDescription;
    private InventoryScreen inventoryScreen;

    private MazeTraverser(){
        myMaze = Maze.getInstance();
        myPlayerCords = myMaze.getPlayerCords();
        myPlayer = null;
        myRoomDescription = null;
    }

    public static MazeTraverser getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new MazeTraverser();
        }
        return uniqueInstance;
    }

    public void setPlayer(Player thePlayer){
        myPlayer = thePlayer;
    }

    public void setRoomDescription(RoomDescription theRoomDescription){
        myRoomDescription = theRoomDescription;
    }

    public void setInventoryScreen(InventoryScreen theInventoryScreen){ inventoryScreen = theInventoryScreen; }

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
                inventoryScreen.addPotion(item);
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
            new ArenaScreen(myPlayer, monster, arena);// Automatically sets up and shows the window
            //arena.combat();
        }

        System.out.println("Items");
        myMaze.printMaze();
        System.out.println("Player");
        myMaze.printPlayerCordMaze();
        System.out.println("Monsters");
        myMaze.printMonsterMaze();
    }

}
