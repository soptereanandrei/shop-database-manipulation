package bussinessLayer;

import model.Client;
import model.Order;
import model.Product;

import java.lang.reflect.Field;

public class InputChecker {

    /**
     * Method check if input represents valid informations of a new client
     * @param input array of string which represent input from GUI
     * @return a Client with valid informations
     * @throws Exception if have not valid input method throws a Exception
     */
    public static Client checkNewClient(String[] input) throws Exception
    {
        String[] validInput = new String[input.length];

        if (input[0].isBlank())
            throw new Exception("Invalid client name");

        for (int i = 0; i < input.length; i++)
        {
            if (input[i] != null && !input[i].isBlank())
            {
                validInput[i] = input[i];
            }
            else
                validInput[i] = null;
        }
        Integer age = Integer.parseInt(validInput[3]);
        if (age < 18 || age > 100)
            throw new Exception("Invalid client age");

        return new Client(validInput[0], validInput[1], validInput[2], age);
    }

    /**
     * Method check if input represent valid informations for a edit/delete operation
     * @param input array of string which represent input from GUI
     * @return a Client with valid informations
     * @throws Exception if have not valid input method throws a Exception
     */
    public static Client checkEditableClient(String[] input) throws Exception
    {
        String[] validInput = new String[input.length];

        if (input[0].isBlank())
            throw new Exception("Invalid client name");

        for (int i = 0; i < input.length; i++)
        {
            if (input[i] != null && !input[i].isBlank())
            {
                validInput[i] = input[i];
            }
            else
                validInput[i] = null;
        }
        int age = -1;
        if (validInput[3] != null)
            age = Integer.parseInt(validInput[3]);

        return new Client(validInput[0], validInput[1], validInput[2], age);
    }

    /**
     * Method check if input represents valid informations of a new product
     * @param input array of string which represent input from GUI
     * @return a Product with valid informations
     * @throws Exception if have not valid input method throws a Exception
     */
    public static Product checkProduct(String[] input) throws Exception
    {
        if (input[0].isBlank())
            throw new Exception("Invalid product name");

        int quantity = Integer.parseInt(input[1]);
        if (quantity < 0)
            throw new Exception("Invalid quantity, must be positive");

        return new Product(input[0], quantity);
    }

    /**
     * Method check if input represents valid informations of a new order
     * @param input array of string which represent input from GUI
     * @return a Order with valid informations
     * @throws Exception if have not valid input method throws a Exception
     */
    public static Order checkOrder(String[] input) throws Exception
    {
        if (input[0].isBlank())
            throw new Exception("Invalid product name");

        if (input[1].isBlank())
            throw new Exception("Invalid client name");

        int quantity = Integer.parseInt(input[2]);
        if (quantity <= 0)
            throw new Exception("Invalid quantity, must be greater than zero");

        return new Order(input[0], input[1], quantity);
    }

    /**
     * Method checks if the field is valid for query
     * @param f field to be checked
     * @param o object witch have field
     * @return true if it is valid else false
     */
    public static boolean checkField(Field f, Object o) throws Exception
    {
        Object val = f.get(o);
        if (val == null)
            return false;
        if (f.getType().getSimpleName().contentEquals("int"))
        {
            if ((int)val < 0)
                return false;
        }
        return true;
    }

}
