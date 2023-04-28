package data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {

    private static final String DB_URL = "jdbc:mysql://db4free.net:3306/guticket?useSSL=false";
    private static final String DB_USERNAME = "guticket";
    private static final String DB_PASSWORD = "guticket23";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                System.err.println("Error loading MySQL driver: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error connecting to the database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }
    }

    public static void createTables() {
        String createStaffTable = "CREATE TABLE IF NOT EXISTS staff (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL" +
                ");";

        String createUserTable = "CREATE TABLE IF NOT EXISTS user (" +
                "dni VARCHAR(255) NOT NULL PRIMARY KEY," +
                "nombre VARCHAR(255) NOT NULL," +
                "apellidos VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL," +
                "ticket_id INT" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createStaffTable);
            statement.execute(createUserTable);
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    public static void insertSampleData() {
        String[] staffUsernames = {"besanz", "jokinson", "JAArana", "aritznieto"};
        String staffPassword = "admin";

        String[] userDnis = {"12345678A", "23456789B", "34567890C", "45678901D", "56789012E"};
        String[] userNames = {"Ane", "Jorge", "Nerea", "Mikel", "Amaia"};
        String[] userLastNames = {"Gonzalez", "Etxebarria", "Lopez", "Garcia", "Arrieta"};
        String[] userEmails = {"ane@deusto.es", "jorge@opendeusto.es", "nerea@deusto.es", "mikel@opendeusto.es", "amaia@deusto.es"};
        String[] userPasswords = {"ane", "jorge", "nerea", "mikel", "amaia"};

        try (Statement statement = connection.createStatement()) {
            for (int i = 0; i < staffUsernames.length; i++) {
                String insertStaff = "INSERT INTO staff (username, password) VALUES ('" + staffUsernames[i] + "', '" + staffPassword + "');";
                statement.execute(insertStaff);
            }

            for (int i = 0; i < userDnis.length; i++) {
                String insertUser = "INSERT INTO user (dni, nombre, apellidos, email, password) VALUES ('" +
                        userDnis[i] + "', '" + userNames[i] + "', '" + userLastNames[i] + "', '" + userEmails[i] + "', '" + userPasswords[i] + "');";
                statement.execute(insertUser);
            }

        } catch (SQLException e) {
            System.err.println("Error inserting sample data: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connection to the database established successfully.");

            try {
                createTables();
                System.out.println("Tables created successfully.");
            } catch (Exception e) {
                System.err.println("Failed to create tables: " + e.getMessage());
            }

            if (getConnection() != null) {
                try {
                    insertSampleData();
                    System.out.println("Sample data inserted successfully.");
                } catch (Exception e) {
                    System.err.println("Failed to insert sample data: " + e.getMessage());
                }
            } else {
                System.err.println("Connection closed before inserting sample data.");
            }

            try {
                closeConnection();
                System.out.println("Connection to the database closed successfully.");
            } catch (Exception e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        } else {
            System.err.println("Failed to connect to the database.");
        }
    }
}
