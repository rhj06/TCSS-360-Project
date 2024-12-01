package dungeongame.src.controller;

import dungeongame.src.model.*;

import java.awt.*;

public class MazeTraverser {
    private Maze myMaze;
    private Point myPlayerCords;
    private Player myPlayer;

    public MazeTraverser(Player thePlayer){
        myMaze = Maze.getInstance();
        myPlayerCords = myMaze.getPlayerCords();
        myPlayer = thePlayer;
    }

    public void movePlayer(Directions theDir){
        if(theDir == Directions.NORTH){
            if(myMaze.canGoNorth()) {
                myMaze.goNorth();
            } else {
                System.out.println("Can't go North");
            }
        } else if(theDir == Directions.EAST) {
            if (myMaze.canGoEast()) {
                myMaze.goEast();
            } else {
                System.out.println("Can't go East");
            }
        }else if(theDir == Directions.SOUTH){
            if(myMaze.canGoSouth()) {
                myMaze.goSouth();
            } else {
                System.out.println("Can't go South");
            }
        } else if(theDir == Directions.WEST){
            if(myMaze.canGoWest()) {
                myMaze.goWest();
            } else {
                System.out.println("Can't go West");
            }
        }

        if(myMaze.roomHasItem(myPlayerCords.y, myPlayerCords.x)){
            Item item = myMaze.getRoomItem(myPlayerCords.y, myPlayerCords.x);
            PlayerInventory.getInstance().addItem(item);
            System.out.println(item.toString() + " has been added to the inventory");
            myMaze.setRoomItem(myPlayerCords.y, myPlayerCords.x, null);
        }

        if(myMaze.roomHasMonster(myPlayerCords.y, myPlayerCords.x)){
            AbstractMonster monster = myMaze.getRoomMonster(myPlayerCords.y, myPlayerCords.x);
            Arena arena = new Arena(myPlayer, monster);
        }

    }

}
