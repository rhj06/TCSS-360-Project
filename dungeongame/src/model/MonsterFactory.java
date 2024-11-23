package dungeongame.src.model;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Random;

public final class MonsterFactory {
    private static final String monstersDatabase = ".idea/resources/montsers.db";

    private static final String[] NAMES = {
            "Alduin", "Baalor", "Cthara", "Draxis", "Eldar", "Todd", "Gorlak",
            "Hexar", "Kaleb", "Jareth", "Kaelis", "Lazrak", "Mordred", "Nyx",
            "Oberon", "David", "Professor Tom", "Ragnar", "Sargoth", "Thalor", "Ulric",
            "Vexor", "Wulfric", "Xargon", "Jeff", "Zalrok", "Zephyr", "Rynor",
            "Ryan", "Malakar"};

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
             Statement stmt = conn.createStatement();) {

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

    private static String getRandomName() {
        return NAMES[new Random().nextInt(NAMES.length)];
    }

}
