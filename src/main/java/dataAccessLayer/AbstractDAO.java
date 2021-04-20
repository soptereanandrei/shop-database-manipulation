package dataAccessLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO {

    /**
     * git s
     * @param type class of the objects type
     * @param resultSet the ResultSet from database
     */
    public static <T> List<T> createObjects(Class<T> type, ResultSet resultSet) throws Exception
    {
        List<T> objects = new ArrayList<>();
        Constructor constructor = type.getDeclaredConstructor();

        try {
            while (resultSet.next())
            {
                T instance = (T)constructor.newInstance();
                for (Field field : type.getDeclaredFields())
                {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                objects.add(instance);
            }
            return  objects;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method create from a list of objects a table
     * @param type class of the objects type
     * @param objects list of objects
     * @param <T> type
     * @return a TableModel
     * @throws Exception
     */
    public static <T> TableModel createTable(Class<T> type, List<T> objects) throws Exception
    {
        DefaultTableModel model = new DefaultTableModel();

        Field[] fields = type.getDeclaredFields();
        Method[] getters = new Method[fields.length];

        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            model.addColumn(fieldName);
            PropertyDescriptor propertyDescriptors = new PropertyDescriptor(fieldName, type);
            getters[i] = propertyDescriptors.getReadMethod();
        }

        for (T object : objects)
        {
            Object[] rowData = new Object[model.getColumnCount()];
            for (int i = 0; i < fields.length; i++)
            {
                String fieldName = fields[i].getName();
                Object value = getters[i].invoke(object);
                rowData[i] = value;
            }
            model.addRow(rowData);
        }

        return model;
    }
}
