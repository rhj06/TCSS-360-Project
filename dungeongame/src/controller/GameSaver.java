package dungeongame.src.controller;

import dungeongame.src.model.Maze;
import dungeongame.src.model.Player;
import dungeongame.src.model.PlayerInventory;
import dungeongame.src.model.Wizard;
import java.io.*;

/**
 * Saves and loads games states
 *
 * @author Ryan Johnson, David Bessex, Kaleb Anagnostou
 * @version 11/16/2024
 */
public class GameSaver implements Serializable {
    @Serial
    private static final long serialVersionUID = 35681618646L;

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
    public GameSaver(Player thePlayer) {
        //myMaze = theMaze;
        //myPlayerInventory = thePlayerInventory;
        myPlayer = thePlayer;
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
            myPlayer = (Player)in.readObject();

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

    //test main
    public static void main(String[] args){
        Maze maze = Maze.getInstance();
        maze.setMazeSize(5);
        PlayerInventory playerInventory = PlayerInventory.getInstance();
        Wizard wizard = new Wizard(10,10,10,10,10, 10, "10");
        maze.generateMaze();
        maze.printMaze();
        System.out.println();

        GameSaver gameSaver = new GameSaver(wizard);
        gameSaver.saveGame();

        Maze.getInstance().setMazeSize(4);
        maze.generateMaze();

        gameSaver.loadGame();

        Maze.getInstance().printMaze();

    }
}
