package dataAccessLayer;

import bussinessLayer.InputChecker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtils {

    /**
     * Method take a object and add it to database
     * @param o object to add in database
     * @throws Exception
     */
    public static String addNewObject(Object o) throws Exception
    {
        Class objClass = o.getClass();
        Connection con = ConnectionFactory.getConnection();
        if (con == null)
            throw new Exception("Cannot create connection to database\n");

        PreparedStatement statement = null;
        Field[] fields = objClass.getDeclaredFields();
        for (Field f : fields)
            f.setAccessible(true);

        if (findObject(o, fields))
            return o.toString() + "already exits";

        String query = createInsertQuery(objClass, fields);
        statement = con.prepareStatement(query);

        for (int i = 0; i < fields.length; i++)
        {
            fields[i].setAccessible(true);
            statement.setObject(i + 1, fields[i].get(o));
        }

        int row = statement.executeUpdate();
        ConnectionFactory.close(statement);

        String result = "Row affected : " + row + "\n";
        if (row > 0)
            return result + "Succesfully added : " + o.toString();
        return result + "Don't managed to add object : " + o.toString();
    }

    /**
     * Method edit a existing object in database, null fields remain unchanged
     * @param o object to edit
     * @return a message of edit status
     */
    public static String editObject(Object o) throws Exception
    {
        Class objClass = o.getClass();
        Connection con = ConnectionFactory.getConnection();
        if (con == null)
            throw new Exception("Cannot create connection to database\n");

        PreparedStatement statement = null;
        Field[] fields = objClass.getDeclaredFields();
        for (Field f : fields)
            f.setAccessible(true);

        String query = createUpdateQuery(o, fields);
        statement = con.prepareStatement(query);

        int pos = 1;
        for (int i = 1; i < fields.length; i++)
        {
            if (InputChecker.checkField(fields[i], o))
            {
                statement.setObject(pos, fields[i].get(o));
                pos++;
            }
        }

        int row = statement.executeUpdate();
        ConnectionFactory.close(statement);

        String result = "Row affected : " + row + "\n";
        if (row > 0)
            return result + "Succesfully edited : " + o.toString();
        return result + "Don't found any object : " + o.toString();
    }

    public static String deleteObject(Object o) throws Exception
    {
        Class objClass = o.getClass();
        Connection con = ConnectionFactory.getConnection();
        if (con == null)
            throw new Exception("Cannot create connection to database\n");

        PreparedStatement statement = null;
        Field[] fields = objClass.getDeclaredFields();
        for (Field f : fields)
            f.setAccessible(true);

        String query = createDeleteQuery(o, fields);
        statement = con.prepareStatement(query);

        int pos = 1;
        for (Field field : fields) {
            if (InputChecker.checkField(field, o)) {
                statement.setObject(pos, field.get(o));
                pos++;
            }
        }

        int row = statement.executeUpdate();
        ConnectionFactory.close(statement);


        String result = "Row affected : " + row + "\n";
        if (row > 0)
            return result + "Succesfully delete : " + o.toString();
        return result + "Don't found any object : " + o.toString();
    }

    /**
     * Method return all the table with given name
     * @param tableName name of the table
     * @return a ResultSet with all rows of the table
     * @throws Exception
     */
    public static ResultSet getTable(String tableName) throws Exception
    {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM " + tableName + ";";
        PreparedStatement statement = con.prepareStatement(query);

        return statement.executeQuery();
    }

    private static boolean findObject(Object o, Field[] fields) throws Exception
    {
        PreparedStatement statement;
        Connection con = ConnectionFactory.getConnection();

        String query = createSelectQuery(o, fields);
        statement = con.prepareStatement(query);

        int pos = 1;
        for (Field field : fields) {
            if (InputChecker.checkField(field, o)) {
                statement.setObject(pos, field.get(o));
                pos++;
            }
        }

        ResultSet rs = statement.executeQuery();
        boolean find = rs.next();
        ConnectionFactory.close(rs);
        ConnectionFactory.close(statement);

        return find;
    }

    private static String createInsertQuery(Class objectClass, Field[] fields)
    {
        StringBuilder sb = new StringBuilder();
        int i;

        sb.append("INSERT INTO ");
        sb.append(objectClass.getSimpleName());
        sb.append(" (");
        for (i = 0; i < fields.length - 1; i++)
            sb.append(fields[i].getName() + ", ");
        sb.append(fields[i].getName());
        sb.append(") VALUES (");
        for (i = 0; i < fields.length - 1; i++)
            sb.append("?, ");
        sb.append("?);");

        return sb.toString();
    }

    private static String createUpdateQuery(Object o, Field[] fields) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        int countAppends = 0;

        sb.append("UPDATE ");
        sb.append(o.getClass().getSimpleName());
        sb.append(" SET ");
        for (int i = 1; i < fields.length; i++)
        {
            if (InputChecker.checkField(fields[i], o)) {
                if (countAppends > 0)
                    sb.append(", ");
                sb.append(fields[i].getName() + " = ?");
                countAppends++;
            }
        }
        sb.append(" WHERE " + fields[0].getName() + " = \"" + fields[0].get(o) + "\";");

        return sb.toString();
    }

    private static String createDeleteQuery(Object o, Field[] fields) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        int countAppends = 0;

        sb.append("DELETE FROM ");
        sb.append(o.getClass().getSimpleName());
        sb.append(" WHERE ");
        for (int i = 0; i < fields.length; i++)
        {
            if (InputChecker.checkField(fields[i], o)) {
                if (countAppends > 0) {
                    sb.append(" AND ");
                }
                sb.append(fields[i].getName() + " = ?");
                countAppends++;
            }
        }
        sb.append(";");

        return sb.toString();
    }

    private static String createSelectQuery(Object o, Field[] fields) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        int countAppends = 0;

        sb.append("SELECT id FROM ");
        sb.append(o.getClass().getSimpleName());
        sb.append(" WHERE ");
        for (Field field : fields) {
            if (InputChecker.checkField(field, o)) {
                if (countAppends > 0)
                    sb.append(" AND ");
                sb.append(field.getName() + " = ?");
                countAppends++;
            }
        }
        sb.append(";");

        return sb.toString();
    }

    /*
    private static void setValue(PreparedStatement statement, int i, Field field, Object o) throws Exception
    {
        Class statementClass = statement.getClass();

        String fieldTypeName = field.getType().getSimpleName();
        fieldTypeName = fieldTypeName.substring(0,1).toUpperCase() + fieldTypeName.substring(1);
        Method setMethod = statementClass.getMethod(
                "set" + fieldTypeName,
                int.class, field.getType());
        setMethod.invoke(statement, i, field.get(o));
    }*/
}
