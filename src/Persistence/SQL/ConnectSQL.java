package Persistence.SQL;

import Persistence.Files.ReadJson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class ConnectSQL {
    private static Connection connection = null;


    /**
     * Gets the connection to the database
     * @return Connection. Obtains the handler of the connection to the database
     */
    public static Connection getInstance() throws SQLException{
        if(connection == null){
            makeConnection();
        }

        return connection;
    }

    /**
     *
     */
    private ConnectSQL() throws SQLException{
        makeConnection();
    }

    /**
     * Method that is in charge of creating the connection with the database which configuration is in the config.json
     * file
     * @throws SQLException Throw that makes an exception if there has been any error while making the connection.
     *                      It will be handled with the try catch from where it is called.
     */
    private static void makeConnection() throws SQLException {
        connection =  DriverManager.getConnection("jdbc:mysql://"+
                        ReadJson.getConfigJson().getIpAddress() + ":" +
                        ReadJson.getConfigJson().getPort()+"/"+ ReadJson.getConfigJson().getName(),
                ReadJson.getConfigJson().getUsername(), ReadJson.getConfigJson().getPassword());
    }

    /**
     * Method that closes the connection made with the database
     * @throws SQLException Throw that makes an exception if there has been any error with the connection to the
     *                      database. It will be handled with the try catch from where it is called.
     */
    public static void closeConnection () throws SQLException {
        connection.close();
    }
}
