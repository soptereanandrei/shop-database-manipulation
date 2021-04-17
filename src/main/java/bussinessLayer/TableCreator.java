package bussinessLayer;

import javax.swing.JTable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TableCreator {

    /**
     * Method create a table from ResultSet and print to table
     * @param table where to print result
     * @param rs the ResultSet
     */
    public static <T> ArrayList<T> createTable(JTable table, ResultSet rs) throws Exception
    {
        ArrayList<T> list = new ArrayList<T>();

        return null;
    }
}
