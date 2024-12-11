package dungeongame.src.controller;

import dungeongame.src.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Saves and loads games states
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/16/2024
 */
public class GameSaver implements Serializable {
    @Serial
    private static final long serialVersionUID = 35681618646L;

    private static GameSaver uniqueInstance;

    //private Maze myMaze;
    //private PlayerInventory myPlayerInventory;
    private Player myPlayer;

    /**
     * Constructor for the GamesSaver.
     *
     * //@param theMaze the dungeon game maze.
     * //@param thePlayerInventory the player's inventory.
     * @param thePlayer the game player.
     */
    private GameSaver() {
        //myMaze = theMaze;
        //myPlayerInventory = thePlayerInventory;
        myPlayer = null;
    }

    public static GameSaver getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameSaver();
        }
        return uniqueInstance;
    }

    public void setPlayer(Player thePlayer) {
        myPlayer = thePlayer;
    }

    public AbstractDungeonCharacter getPlayer() {
        return ((AbstractDungeonCharacter)myPlayer);
    }

    public void saveGame(){
        String filename = "DungeonAdventureSave.da";
        try{
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //out.writeObject(myMaze);
            //out.writeObject(myPlayerInventory);

            out.writeObject(Maze.getInstance());
            out.writeObject(PlayerInventory.getInstance());
            out.writeObject(myPlayer);
            out.writeObject(MapTileList.getInstance());

            System.out.println(MapTileList.getInstance().getList());
//            out.writeObject(MapTileList.getInstance());
            for (MapTile tile: MapTileList.getInstance().getList()) {
                out.writeObject(tile);
            }


            out.close();
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException while saving: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException while saving: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadGame(){
        String filename = "DungeonAdventureSave.da";
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            //myMaze = (Maze)in.readObject();
            //myPlayerInventory = (PlayerInventory)in.readObject();
            Maze.getInstance().updateFrom((Maze)in.readObject());
            PlayerInventory.getInstance().updateFrom((PlayerInventory)in.readObject());
            Player thePlayer = (Player)in.readObject();

            MapTileList list = (MapTileList)in.readObject();
            System.out.println("List @ Load = " + list);

            MapTileList.getInstance().setList(list.getList());


            String playerClass = thePlayer.getClass().getName();


            //System.out.println(playerClass);

            if(playerClass.contains("Wizard")) {
                myPlayer = new Wizard(0, 0, 0, 0, 0,0, null);
            } else if (playerClass.contains("Warrior")) {
                myPlayer = new Warrior(0, 0, 0, 0, 0,0, null);
            } else if (playerClass.contains("Thief")) {
                myPlayer = new Thief(0, 0, 0, 0, 0,0, null);
            }

            ((AbstractDungeonCharacter)myPlayer).updateFrom((AbstractDungeonCharacter) thePlayer);
            //((AbstractDungeonCharacter)myPlayer).initializeTransientFields();

            for (MapTile tile: MapTileList.getInstance().getList()) {
                MapTile temp = (MapTile) in.readObject();
                System.out.println("Tile @ Load = " + temp);
                if (tile.getClass().getName().contains("Four_Way_Tile")) {
                    tile = temp;
//                     = new Four_Way_Tile(temp.getPositionX(), temp.getPositionY(), 50,50);
                    System.out.println("Four_Way_Tile @ Load = " + tile);
                }
            }


            in.close();
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException while loading: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException while loading: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException while loading: " + e.getMessage());
            e.printStackTrace();
        }
    }

////    //test main
//    public static void main(String[] args){
//        Maze maze = Maze.getInstance();
//        maze.setMazeSize(5);
//        PlayerInventory playerInventory = PlayerInventory.getInstance();
//        Wizard wizard = new Wizard(10,10,10,10,10, 10, "10");
//        maze.generateMaze();
//        maze.printMaze();
//        System.out.println();
//
//        GameSaver gameSaver = GameSaver.getInstance();
//        gameSaver.saveGame();
//
//        AbstractDungeonCharacter character = new Warrior(1,1,1,1,1,1,"10");
//        System.out.println(character);
//
//        Maze.getInstance().setMazeSize(4);
//        maze.generateMaze();
//        maze.printMaze();
//        character = gameSaver.getPlayer();
//        System.out.println(character);
//
//
//        gameSaver.loadGame();
//
//        Maze.getInstance().printMaze();
//
//    }
}
