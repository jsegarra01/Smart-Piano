package Persistence.SQL;

import Persistence.Files.ReadJson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class "ConnectSQL" is in charge of establishing a connection with the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 28 June 2021
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

}
