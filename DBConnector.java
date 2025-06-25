////package app.ui;
////
////import java.sql.Connection;
////import java.sql.DriverManager;
////import java.sql.SQLException;
////
////public class DBConnector {
////    public static void main(String[] args) {
////        // Database URL, username, and password
////        // Ensure your MySQL server (XAMPP) is running and the database 'nepali_recepie' exists.
////        // The default port for MySQL is 3306.
////        String url = "jdbc:mysql://localhost:3306/nepali_recepie";
////        String username = "root";
////        String password = ""; // Often empty for XAMPP's default root user
////
////        Connection conn = null; // Initialize connection to null
////
////        try {
////            // Load the MySQL JDBC driver.
////            // This line registers the driver with the DriverManager.
////            // For MySQL Connector/J 8.x, use "com.mysql.cj.jdbc.Driver".
////            // For older versions (5.x), it might be "com.mysql.jdbc.Driver".
////            Class.forName("com.mysql.cj.jdbc.Driver");
////            System.out.println("MySQL JDBC Driver registered.");
////
////            // Establish the connection to the database
////            conn = DriverManager.getConnection(url, username, password);
////
////            if (conn != null) {
////                System.out.println("Connected to MySQL database successfully!");
////            } else {
////                System.out.println("Failed to establish connection.");
////            }
////
////        } catch (ClassNotFoundException e) {
////            // This exception is caught if the JDBC driver class is not found.
////            // Make sure the MySQL Connector/J JAR file is included in your project's classpath.
////            System.err.println("MySQL JDBC driver not found.");
////            e.printStackTrace();
////        } catch (SQLException e) {
////            // This exception is caught if there's an issue with the database connection itself
////            // (e.g., incorrect URL, wrong credentials, database not running).
////            System.err.println("Connection to MySQL database failed!");
////            System.err.println("SQLState: " + e.getSQLState());
////            System.err.println("Error Code: " + e.getErrorCode());
////            e.printStackTrace();
////        } finally {
////            // Always close the connection in a finally block to ensure it's closed
////            // even if an exception occurs.
////            try {
////                if (conn != null && !conn.isClosed()) {
////                    conn.close();
////                    System.out.println("Connection closed successfully.");
////                }
////            } catch (SQLException e) {
////                System.err.println("Error closing the connection.");
////                e.printStackTrace();
////            }
////        }
////    }
////}
//
//package app.ui;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnector {
//    // Method to get a database connection (you can reuse this elsewhere)
//    public static Connection getConnection() throws SQLException, ClassNotFoundException {
//        String url = "jdbc:mysql://localhost:3306/nepali_recepie";
//        String username = "root";
//        String password = "";
//
//        // Load driver (optional from JDBC 4.0+, but good to include)
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        return DriverManager.getConnection(url, username, password);
//    }
//
//    // ✅ Test code in the same file
//    public static void main(String[] args) {
//        try (Connection conn = getConnection()) {
//            if (conn != null) {
//                System.out.println("✅ Connected to MySQL database successfully!");
//            } else {
//                System.out.println("❌ Connection failed.");
//            }
//        } catch (ClassNotFoundException e) {
//            System.err.println("⚠ JDBC driver not found.");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.err.println("❌ Database connection error.");
//            e.printStackTrace();
//        }
//    }
//}

// src/app/ui/DBConnector.java
package app.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL  = "jdbc:mysql://localhost:3306/nepali_recipes";  // <— make sure this matches
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
