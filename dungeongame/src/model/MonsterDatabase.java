package dungeongame.src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class for managing a SQLite database containing monster data for the Dungeon Adventure game.
 * This class handles the creation of the database file and a monsters table, if they do not already exist.
 */
public class MonsterDatabase {

    /**
     * The URL for the SQLite database file.
     * Adjust the path if needed based on the project structure.
     */
    private static final String DB_URL = "jdbc:sqlite:.idea/resources/monsters.db";

    /**
     * Constructs a new MonsterDatabase instance.
     * The database connection is initialized on demand by the {@code initializeDatabase} method.
     */
    public MonsterDatabase() {
    }

    /**
     * Initializes the database by creating the database file and the monsters table if they do not exist.
     */
    public void initializeDatabase() {
        createDatabase();
        createMonstersTable();
    }

    /**
     * Creates the SQLite database file if it does not already exist.
     * If the database file exists, this method connects to it to ensure it is accessible.
     */
    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Database created or connected successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error creating or connecting to the database: " + e.getMessage());
        }
    }

    /**
     * Creates the monsters table in the database if it does not already exist.
     */
    private void createMonstersTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS monsters (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                max_health INTEGER NOT NULL,
                min_attack INTEGER NOT NULL,
                max_attack INTEGER NOT NULL,
                speed INTEGER NOT NULL,
                defense INTEGER NOT NULL
            );
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Monsters table created.");
        } catch (SQLException e) {
            System.out.println("Error creating the monsters table: " + e.getMessage());
        }
    }
}