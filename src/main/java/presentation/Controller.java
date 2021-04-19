package presentation;

import bussinessLayer.InputChecker;
import dataAccessLayer.AbstractDAO;
import dataAccessLayer.DBUtils;
import model.Client;
import model.Product;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
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
            view.changeView("order");
        }
    }

    class AddClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapClientInputFields();
            try {
                Client c = InputChecker.checkNewClient(input);
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
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 1);
            }
        }
    }
}
