package dungeongame.src.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MonsterDatabase {

    private static final String DB_URL = "jdbc:sqlite:.idea/resources/montsers.db"; // Adjust path if needed

    public MonsterDatabase() {
        // Initialization deferred to connect on demand
    }

    // Method to initialize the database and monsters table if needed
    public void initializeDatabase() {
        createDatabase();
        createMonstersTable();
    }

    // Method to create a database file if it doesn't exist
    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Database created or connected successfully.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to create the monsters table
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
            System.out.println(e.getMessage());
        }
    }
}