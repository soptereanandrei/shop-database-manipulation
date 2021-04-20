package bussinessLayer;

import model.Order;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BillCreator {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss");

    public static void createBill(Order order) throws Exception
    {
        LocalDateTime now = LocalDateTime.now();
        String time = dateTimeFormatter.format(now);

        FileWriter fileWrite = new FileWriter("Bill(" + time + ").txt");
        fileWrite.write("Bill creation time : " + time + "\n");
        fileWrite.append("Product name : " + order.getProductName() + "\n");
        fileWrite.append("Client name : " + order.getClientName() + "\n");
        fileWrite.append("Quantity : " + order.getQuantity() + "\n");
        fileWrite.close();
    }

}
