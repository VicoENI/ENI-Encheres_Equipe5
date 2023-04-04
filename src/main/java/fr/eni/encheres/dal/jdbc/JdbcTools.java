package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.encheres.dal.Settings;

/**
 * Class in charge of managing the connection to the database
 * @author VicoENI
 * @version 1.0
 */
public class JdbcTools {

    // Création des variables de connexion à la base de données
    private static String urldb;
    private static String userdb;
    private static String passworddb;

    /**
     * Static block in charge of loading the JDBC driver and initializing the connection variables
     */
    static {
        try {
            Class.forName(Settings.getProperty("driverdb"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Impossible de charger le driver JDBC", e);
        }
        urldb = Settings.getProperty("urldb");
        userdb = Settings.getProperty("userdb");
        passworddb = Settings.getProperty("passworddb");
    }

    /**
     * Method in charge of returning a connection to the database
     * @return connection Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(urldb, userdb, passworddb);
        return connection;
    }
    
}
