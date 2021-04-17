package presentation;

import bussinessLayer.InputChecker;
import bussinessLayer.TableCreator;
import dataAccessLayer.DBUtils;
import model.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            Client c = null;
            try {
                c = InputChecker.checkNewClient(input);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 0);
            }

            try {
                String msg = DBUtils.addNewObject(c);
                view.printLog(msg, 0);
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 0);
            }
        }
    }

    class EditClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapClientInputFields();
            Client c = null;
            try {
                c = InputChecker.checkEditableClient(input);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 0);
            }

            try {
                String msg = DBUtils.editObject(c);
                view.printLog(msg, 0);
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 0);
            }
        }
    }

    class DeleteClientListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] input = view.wrapClientInputFields();
            Client c = null;
            try {
                c = InputChecker.checkEditableClient(input);
            }
            catch (Exception exception)
            {
                view.printLog("Invalid input : " + exception.getMessage() + "\n", 0);
            }

            try {
                String msg = DBUtils.deleteObject(c);
                view.printLog(msg, 0);
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 0);
            }
        }
    }

    class ShowClientTableListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = DBUtils.getTable("client");
                TableCreator.createTable(null, rs);
            }
            catch (Exception exception)
            {
                view.printLog(exception.getMessage(), 0);
            }
        }
    }
}
