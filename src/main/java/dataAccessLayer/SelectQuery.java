package dataAccessLayer;

import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectQuery {

    private final static String findStatementSrting = "SELECT * FROM student";

    public static void getClients()
    {
        //Client client = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;

        try {
            Connection con = ConnectionFactory.getConnection();
            findStatement = con.prepareStatement(findStatementSrting);
            rs = findStatement.executeQuery();
            while (rs.next())
            {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            if (findStatement != null)
                ConnectionFactory.close(findStatement);
        }
    }

}
