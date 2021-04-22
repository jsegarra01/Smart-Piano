package Persistence.SQL;

import Persistence.Json.ReadConfigJson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class ConnectSQL {
    private static Connection connection = null;


    /**
     *
     * @return
     */
    public static Connection getInstance(){
        if(connection == null){
            try {
                makeConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return connection;
    }

    /**
     *
     */
    private ConnectSQL(){
        try {
            makeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method that is in charge of creating the connection with the database which configuration is in the config.json
     * file
     * @throws SQLException Throw that makes an exception if there has been any error while making the connection.
     *                      It will be handled with the try catch from where it is called.
     */
    private static void makeConnection() throws SQLException {
        connection =  DriverManager.getConnection("jdbc:mysql://"+
                        ReadConfigJson.getConfigJson().getIpAddress() + ":" +
                        ReadConfigJson.getConfigJson().getPort()+"/"+ ReadConfigJson.getConfigJson().getName(),
                ReadConfigJson.getConfigJson().getUsername(), ReadConfigJson.getConfigJson().getPassword());
    }

    /**
     * Method that closes the connection made with the database
     * @throws SQLException Throw that makes an exception if there has been any error with the connection to the
     *                      database. It will be handled with the try catch from where it is called.
     */
    public void closeConnection () throws SQLException {
        connection.close();
    }
}
