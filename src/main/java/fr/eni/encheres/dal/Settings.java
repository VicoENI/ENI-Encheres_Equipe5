package fr.eni.encheres.dal;

import java.util.Properties;


/**
 * Class in charge of loading the configuration file
 * @author @VicoENI
 * @version 1.0
 */
public class Settings {

    // Attributs
    private static Properties properties;

    /*
     * Static block in charge of loading the configuration file
     */
    static {
        try {
            properties = new Properties();
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Impossible de charger le fichier de configuration", e);
        }
    }

    /*
     * Method in charge of returning the value of a key in the configuration file
     * @param key String
     * @return parametre String
     * @throws SQLException
     */
    public static String getProperty(String key) {
        String parametre = properties.getProperty(key, null);
        return parametre;
    }
}
