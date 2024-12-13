package dungeongame.src.model;

import java.io.*;
import java.util.ArrayList;

/**
 * MapTileList used to Serialize Objects in the ArrayList<MapTile>
 * Implements Serializable for save feature
 * @author David Bessex, Kaleb Anagnostou, Ryan Johnson
 * @version 1.0
 */
public final class MapTileList implements Serializable {

    @Serial
    private static final long serialVersionUID = 534215432523L;
    /** MapTileList uniqueInstance */
    private static MapTileList uniqueInstance;

    private ArrayList<MapTile> myList;

    private MapTileList() {
        myList = new ArrayList<>();
    }

    /**
     * Get the unique instance of MapTileList
     * @return this.MapTileList
     */
    public static MapTileList getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MapTileList();
            System.out.println("New List Created for Unique Instance.");
        }
        System.out.println("UniqueInstance = " + uniqueInstance.getList());
        return uniqueInstance;
    }

    public void setList(ArrayList<MapTile> theList) {
        myList = theList;
    }

    public ArrayList<MapTile> getList() {
        System.out.println("List @ Get = " + myList);
        return myList;
    }

}
