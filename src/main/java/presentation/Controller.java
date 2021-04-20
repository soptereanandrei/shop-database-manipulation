package presentation;

import bussinessLayer.BillCreator;
import bussinessLayer.InputChecker;
import dataAccessLayer.AbstractDAO;
import dataAccessLayer.ConnectionFactory;
import dataAccessLayer.DBUtils;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

public class Controller {

    private View view;

    public Controller(View view)
    {
        this.view = view;

        view.getClientViewButton().addActionListener(new ClientsViewListener());
        view.getProductViewButton().addActionListener(new ProductsViewListener());
        view.getOrderViewButton().addActionListener(new OrdersViewListener());
        view.getClientAddButton().addActionListener(new AddClientListener());
        view.getClientEditButton().addActionListener(new EditClientListener());
        view.getClientDeleteButton().addActionListener(new DeleteClientListener());
        view.getClientViewTableButton().addActionListener(new ShowClientTableListener());
        view.getProductAddButton().addActionListener(new AddProductListener());
        view.getProductEditButton().addActionListener(new EditProductListener());
        view.getProductDeleteButton().addActionListener(new DeleteProductListener());
        view.getProductViewTableButton().addActionListener(new ShowProductTableListener());
        view.getCreateOrderButton().addActionListener(new CreateOrderListener());
    }

    class ClientsViewListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.changeView("client");
        }
    }

    class ProductsViewListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.changeView("product");
        }
    }

    class OrdersViewListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class<Client> clientClass = Client.class;
                ResultSet clientsRS = DBUtils.getTable(clientClass.getSimpleName());
                List<Client> clients = AbstractDAO.createObjects(clientClass, clientsRS);
                String[] clientsName = null;
                if (clients != null) {
                    clientsName = new String[clients.size()];
                    for (int i = 0; i < clientsName.length; i++)
                        clientsName[i] = clients.get(i).getName();
                }

                Class<Product> productClass = Product.class;
                ResultSet productsRS = DBUtils.getTable(productClass.getSimpleName());
                List<Product> products = AbstractDAO.createObjects(productClass, productsRS);
                String[] productsName = null;
                if (products != null) {
                    productsName = new String[products.size()];
                    for (int i = 0; i < productsName.length; i++)
                        productsName[i] = products.get(i).getName();
                }

                view.setAvailableClientsAndProductsToOrder(clientsName, productsName);
                view.changeView("order");
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 2);
            }
        }
    }

    class AddClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapClientInputFields();
            try {
                Client c = InputChecker.checkNewClient(input);
                if (DBUtils.findObject(c).next())
                    throw new Exception(c.toString() + " already exits");
                String msg = DBUtils.addNewObject(c);
                view.printLog(msg, 0);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 0);
            }
        }
    }

    class EditClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapClientInputFields();
            try {
                Client c = InputChecker.checkEditableClient(input);
                String msg = DBUtils.editObject(c);
                view.printLog(msg, 0);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 0);
            }
        }
    }

    class DeleteClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapClientInputFields();
            try {
                Client c = InputChecker.checkEditableClient(input);
                String msg = DBUtils.deleteObject(c);
                view.printLog(msg, 0);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 0);
            }
        }
    }

    class ShowClientTableListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class<Client> objectType = Client.class;
                ResultSet rs = DBUtils.getTable(objectType.getSimpleName());
                List<Client> clients = AbstractDAO.createObjects(objectType, rs);
                TableModel tableModel = AbstractDAO.createTable(objectType, clients);
                view.setTable(tableModel, 0);
                ConnectionFactory.close(rs);
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 0);
            }
        }
    }

    class AddProductListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapProductInputFields();
            try {
                Product p = InputChecker.checkProduct(input);
                if (DBUtils.findObject(p).next())
                    throw new Exception(p.toString() + " already exits");
                String msg = DBUtils.addNewObject(p);
                view.printLog(msg, 1);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 1);
            }
        }
    }

    class EditProductListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapProductInputFields();
            try {
                Product p = InputChecker.checkProduct(input);
                String msg = DBUtils.editObject(p);
                view.printLog(msg, 1);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 1);
            }
        }
    }

    class DeleteProductListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapProductInputFields();
            try {
                Product p = InputChecker.checkProduct(input);
                String msg = DBUtils.deleteObject(p);
                view.printLog(msg, 1);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 1);
            }
        }
    }

    class ShowProductTableListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class<Product> objectType = Product.class;
                ResultSet rs = DBUtils.getTable(objectType.getSimpleName());
                List<Product> clients = AbstractDAO.createObjects(objectType, rs);
                TableModel tableModel = AbstractDAO.createTable(objectType, clients);
                view.setTable(tableModel, 1);
                ConnectionFactory.close(rs);
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 1);
            }
        }
    }

    class CreateOrderListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String[] input = view.wrapOrderInputFields();
                Order order = InputChecker.checkOrder(input);
                Product searchProduct = new Product(order.getProductName(), -1);
                ResultSet resultSet = DBUtils.findObject(searchProduct);
                List<Product> foundProducts = AbstractDAO.createObjects(Product.class, resultSet);
                Product foundProduct = null;
                for (Product product : foundProducts) {
                    if (product.getQuantity() - order.getQuantity() >= 0)
                    {
                        foundProduct = product;
                        break;
                    }
                }
                if (foundProduct == null)
                    throw new Exception("Not enough quantity in stock");
                int newQuantity = foundProduct.getQuantity() - order.getQuantity();
                foundProduct.setQuantity(newQuantity);
                DBUtils.addNewObject(order);
                DBUtils.editObject(foundProduct);
                BillCreator.createBill(order);
                view.printLog("Succesfully created order " + order.toString(), 2);
                ConnectionFactory.close(resultSet);
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 2);
            }
        }
    }
}
