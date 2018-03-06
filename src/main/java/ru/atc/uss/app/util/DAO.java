package ru.atc.uss.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Ivan Ivankov {@literal <iivankov@at-consulting.ru>}
 */
public class DAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DAO.class);

    private Connection connection = null;

    private static HashMap<String, Connection> connectionList;

    private static String connectionString = null;
    private String password;

    public DAO() throws IOException {
        if(connectionString == null )connectionList = new HashMap<String, Connection>();
    }

    public static Connection getConnection (String connectionString, String login, String password) {
        if(!connectionList.containsKey(connectionString + login))
            addConnection(connectionString, login, password);
        return connectionList.get(connectionString + login);
    }

    private static void addConnection (String connectionString, String login, String password) {
        try {
            Class.forName(Config.JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(
                    connectionString, login, password);
            connectionList.put(connectionString + login, connection);
        } catch (Exception e) {
            LOGGER.error("Can't create jdbc connection with " + connectionString, e);
            throw new RuntimeException("Can't connect to DB." + e.getMessage());
        }
    }

    public void closeAll () {
        for(Connection conn : connectionList.values()) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("Connection can't be closed", e);
            }
        }
    }

    public void close(String connectionString, String login) {
        try {
            connectionList.get(connectionString + login).close();
        } catch (SQLException e) {
            LOGGER.error("Connection can't be closed", e);
        }
    }

}
