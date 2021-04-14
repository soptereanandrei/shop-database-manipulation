package dataAccessLayer;

import java.sql.*;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.ch.jdbc.Driver";
    private static final String DBURL =  "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "ParolaRoot1234";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private static Connection connection;

    private ConnectionFactory()
    {
        /*
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        connection = createConnection();
    }


    private Connection createConnection() {
        try {
            Connection con = DriverManager.getConnection(DBURL, USER, PASS);
            return con;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Connection getConnection()
    {
        return connection;
    }

    public static void close(Connection connection)
    {
        try {
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void close(Statement statement)
    {
        try {
            statement.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void close(ResultSet resultSet)
    {
        try {
            resultSet.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
