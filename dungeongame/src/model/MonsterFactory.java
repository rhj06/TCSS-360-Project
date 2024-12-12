package dungeongame.src.model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Random;

/**
 * Factory class for creating monsters in the dungeon game.
 * The MonsterFactory retrieves monster attributes from a SQLite database and instantiates appropriate monster objects
 * based on the provided type.
 *
 * @version 1.0
 * @author Kaleb Anagnostou, Ryan Johnson, David Bessex
 */
public final class MonsterFactory {

    /** Path to the SQLite database containing monster data. */
    private static final String monstersDatabase = ".idea/resources/montsers.db";

    /** Array of possible monster names for random name generation. */
    private static final String[] NAMES = {
            "Alduin", "Baalor", "Cthara", "Draxis", "Eldar", "Todd", "Gorlak",
            "Hexar", "Kaleb", "Jareth", "Kaelis", "Lazrak", "Mordred", "Nyx",
            "Oberon", "David", "Professor Tom", "Ragnar", "Sargoth", "Thalor", "Ulric",
            "Vexor", "Wulfric", "Xargon", "Jeff", "Zalrok", "Zephyr", "Rynor",
            "Ryan", "Malakar"};

    /**
     * Creates a monster instance of the specified type.
     * The monster's attributes (e.g., health, attack, speed) are fetched from the database and used to initialize the
     * appropriate monster subclass.
     *
     * @param theMonster theMonster the type of monster to create
     * @return an instance of the specified monster type
     * @throws IllegalArgumentException if the monster type is invalid
     */
    public static AbstractMonster createMonster(final String theMonster) {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:" + monstersDatabase);

        int maxHealth = 0;
        int minAttack = 0;
        int maxAttack = 0;
        int speed = 0;
        int defense = 0;
        int dropRate = 0;

        String query = "SELECT * FROM monsters WHERE name = '" + theMonster +"'";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                maxHealth = rs.getInt("health");
                minAttack = rs.getInt("min_attack");
                maxAttack = rs.getInt("max_attack");
                speed = rs.getInt("speed");
                defense = rs.getInt("defense");
                dropRate = rs.getInt("item_drop_rate");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        if (theMonster.equalsIgnoreCase("goblin")) {
            return new Goblin(maxHealth, minAttack, maxAttack, speed, defense, dropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("slime")) {
            return new Slime(maxHealth, minAttack, maxAttack, speed, defense, dropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("skeleton")) {
            return new Skeleton(maxHealth, minAttack, maxAttack, speed, defense, dropRate, getRandomName());
        } else if (theMonster.equalsIgnoreCase("ogre")) {
            return new Ogre(maxHealth, minAttack, maxAttack, speed, defense, dropRate, getRandomName());
        } else {
            throw new IllegalArgumentException("Invalid monster name: " + theMonster);
        }
    }

    /**
     * Generates a random name for a monster.
     * @return a randomly selected monster name.
     */
    private static String getRandomName() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }
}
