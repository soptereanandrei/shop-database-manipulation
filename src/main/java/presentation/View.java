package presentation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {

    public JPanel content;

    private JPanel views;

    private JPanel buttons;
    private JButton clientViewButton;
    private JButton productViewButton;
    private JButton tableViewButton;

    private JTextField clientId;
    private JTextField clientName;
    private JTextField clientAddress;
    private JTextField clientEmail;
    private JTextField clientAge;
    private JButton clientAddButton;
    private JButton clientEditButton;
    private JButton clientDeleteButton;
    private JButton clientViewTableButton;
    private JTextArea clientLogArea;
    private JTable clientsTable;

    private JTextField productId;
    private JTextField productName;
    private JTextField productCantity;
    private JButton productAddButton;
    private JButton productEditButton;
    private JButton productDeleteButton;
    private JButton productViewTableButton;
    private JTextArea productLogArea;
    private JTable productsTable;

    private JTextField orderProductName;
    private JTextField orderClientName;
    private JTextField orderQuantity;
    private JButton createOrderButton;
    private JTextArea orderLogArea;
    private JTable ordersTable;

    public View()
    {
        content = new JPanel();
        content.setSize( 1000, 1000);
        ((FlowLayout)content.getLayout()).setAlignment(FlowLayout.LEFT);


        createButtons();
        views = new JPanel(new CardLayout());
        Dimension viewsDim = new Dimension(1000, 150);
        views.add(createClientsView(viewsDim), "client");
        views.add(createProductsView(viewsDim), "product");
        views.add(createOrdersView(viewsDim), "order");

        //test
        ((CardLayout)views.getLayout()).show(views, "order");

        content.add(views);

        add(content);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);
    }

    private void createButtons()
    {
        Dimension d = new Dimension(600, 100);
        Dimension buttonsDim = new Dimension(100, 50);

        buttons = new JPanel();
        buttons.setPreferredSize(d);
        buttons.setMaximumSize(d);

        clientViewButton = createButton("Clients", buttonsDim);
        productViewButton = createButton("Products", buttonsDim);
        tableViewButton = createButton("Orders", buttonsDim);

        buttons.add(clientViewButton);
        buttons.add(Box.createRigidArea(new Dimension(10, 100)));
        buttons.add(productViewButton);
        buttons.add(Box.createRigidArea(new Dimension(10, 100)));
        buttons.add(tableViewButton);

        buttons.setLayout(new FlowLayout());
        ((FlowLayout)buttons.getLayout()).setAlignment(FlowLayout.LEFT);

        content.add(buttons);
    }


    private JPanel createClientsView(Dimension viewDim)
    {
        JPanel clientsView = new JPanel();
        clientsView.setLayout(new BoxLayout(clientsView, BoxLayout.Y_AXIS));

        JPanel clientsInputView = new JPanel();
        clientsInputView.setPreferredSize(viewDim);
        clientsInputView.setMaximumSize(viewDim);
        clientsInputView.setLayout(new BoxLayout(clientsInputView, BoxLayout.X_AXIS));
        JPanel inputPanel = createInputPanelOfClientView();
        JPanel buttonPanel = createButtonPanelOfClientView();
        clientsInputView.add(inputPanel);
        clientsInputView.add(buttonPanel);
        clientsView.add(clientsInputView);

        clientLogArea = new JTextArea(20, 100);
        clientsView.add(new JScrollPane(clientLogArea));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Client ID");
        model.addColumn("Client Name");
        model.addColumn("Client Address");
        model.addColumn("Client Email");
        model.addColumn("Client Age");
        clientsTable = new JTable(model);
        clientsView.add(new JScrollPane(clientsTable));

        return clientsView;
    }

    private JPanel createInputPanelOfClientView()
    {
        JPanel inputPanel = new JPanel();
        Dimension panelDim = new Dimension(1000, 400);
        inputPanel.setPreferredSize(panelDim);
        inputPanel.setMaximumSize(panelDim);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        Dimension textFieldDim = new Dimension(200, 30);
        clientId = createJTextField(textFieldDim);
        clientName = createJTextField(textFieldDim);
        clientAddress = createJTextField(textFieldDim);
        clientEmail = createJTextField(textFieldDim);
        clientAge = createJTextField(textFieldDim);

        inputPanel.add(createInputRow("Client ID:             ", clientId));
        inputPanel.add(createInputRow("Client Name:      ", clientName));
        inputPanel.add(createInputRow("Client Address: ", clientAddress));
        inputPanel.add(createInputRow("Client Email:       ", clientEmail));
        inputPanel.add(createInputRow("Client Age:          ", clientAge));

        inputPanel.setAlignmentX(0.0f);
        return inputPanel;
    }

    private JPanel createButtonPanelOfClientView()
    {
        Dimension panelDimension = new Dimension(400, 400);

        JPanel buttonCol1 = new JPanel();
        buttonCol1.setPreferredSize(panelDimension);
        buttonCol1.setMaximumSize(panelDimension);
        buttonCol1.setLayout(new BoxLayout(buttonCol1, BoxLayout.Y_AXIS));

        JPanel buttonCol2 = new JPanel();
        buttonCol2.setPreferredSize(panelDimension);
        buttonCol2.setMaximumSize(panelDimension);
        buttonCol2.setLayout(new BoxLayout(buttonCol2, BoxLayout.Y_AXIS));

        Dimension buttonDim = new Dimension(200, 50);
        clientAddButton = createButton("Add Client", buttonDim);
        clientEditButton = createButton("Edit Client", buttonDim);
        clientDeleteButton = createButton("Delete Client", buttonDim);
        clientViewTableButton = createButton("View Client Table", buttonDim);

        buttonCol1.add(clientAddButton);
        buttonCol1.add(clientEditButton);

        buttonCol2.add(clientDeleteButton);
        buttonCol2.add(clientViewTableButton);

        JPanel buttonsPanel = new JPanel();
        Dimension allDim = new Dimension(panelDimension.width * 2, panelDimension.height);
        buttonsPanel.setPreferredSize(allDim);
        buttonsPanel.setMaximumSize(allDim);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.add(buttonCol1);
        buttonsPanel.add(buttonCol2);

        buttonsPanel.setAlignmentX(0.0f);
        return buttonsPanel;
    }

    private JPanel createProductsView(Dimension viewDim)
    {
        JPanel productsView = new JPanel();
        productsView.setLayout(new BoxLayout(productsView, BoxLayout.Y_AXIS));

        JPanel productsInputView = new JPanel();
        productsInputView.setPreferredSize(viewDim);
        productsInputView.setMaximumSize(viewDim);
        productsInputView.setLayout(new BoxLayout(productsInputView, BoxLayout.X_AXIS));
        JPanel inputPanel = createInputPanelOfProductsView();
        JPanel buttonPanel = createButtonPanelOfProductsView();
        productsInputView.add(inputPanel);
        productsInputView.add(buttonPanel);
        productsView.add(productsInputView);

        productLogArea = new JTextArea(20, 100);
        productsView.add(new JScrollPane(productLogArea));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Product Name");
        model.addColumn("Product Quntity");
        productsTable = new JTable(model);
        productsView.add(new JScrollPane(productsTable));

        return productsView;
    }

    private JPanel createInputPanelOfProductsView()
    {
        JPanel inputPanel = new JPanel();
        Dimension panelDim = new Dimension(1000, 400);
        inputPanel.setPreferredSize(panelDim);
        inputPanel.setMaximumSize(panelDim);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        Dimension textFieldDim = new Dimension(200, 30);
        productId = createJTextField(textFieldDim);
        productName = createJTextField(textFieldDim);
        productCantity = createJTextField(textFieldDim);

        inputPanel.add(createInputRow("Product ID:           ", productId));
        inputPanel.add(createInputRow("Product Name:    ", productName));
        inputPanel.add(createInputRow("Product Cantity:  ", productCantity));

        inputPanel.setAlignmentX(0.0f);
        return inputPanel;
    }

    private JPanel createButtonPanelOfProductsView()
    {
        Dimension panelDimension = new Dimension(400, 400);

        JPanel buttonCol1 = new JPanel();
        buttonCol1.setPreferredSize(panelDimension);
        buttonCol1.setMaximumSize(panelDimension);
        buttonCol1.setLayout(new BoxLayout(buttonCol1, BoxLayout.Y_AXIS));

        JPanel buttonCol2 = new JPanel();
        buttonCol2.setPreferredSize(panelDimension);
        buttonCol2.setMaximumSize(panelDimension);
        buttonCol2.setLayout(new BoxLayout(buttonCol2, BoxLayout.Y_AXIS));

        Dimension buttonDim = new Dimension(200, 50);
        productAddButton = createButton("Add Product", buttonDim);
        productEditButton = createButton("Edit Product", buttonDim);
        productDeleteButton = createButton("Delete Product", buttonDim);
        productViewTableButton = createButton("View Product Table", buttonDim);

        buttonCol1.add(productAddButton);
        buttonCol1.add(productEditButton);

        buttonCol2.add(productDeleteButton);
        buttonCol2.add(productViewTableButton);

        JPanel buttonsPanel = new JPanel();
        Dimension allDim = new Dimension(panelDimension.width * 2, panelDimension.height);
        buttonsPanel.setPreferredSize(allDim);
        buttonsPanel.setMaximumSize(allDim);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.add(buttonCol1);
        buttonsPanel.add(buttonCol2);

        buttonsPanel.setAlignmentX(0.0f);
        return buttonsPanel;
    }

    private JPanel createOrdersView(Dimension viewDim)
    {
        JPanel orderView = new JPanel();
        orderView.setLayout(new BoxLayout(orderView, BoxLayout.Y_AXIS));

        JPanel orderInputView = new JPanel();
        orderInputView.setPreferredSize(viewDim);
        orderInputView.setMaximumSize(viewDim);
        orderInputView.setLayout(new BoxLayout(orderInputView, BoxLayout.X_AXIS));
        JPanel inputPanel = createInputPanelOfOrdersView();
        JPanel buttonPanel = createButtonPanelOfOrdersView();
        orderInputView.add(inputPanel);
        orderInputView.add(buttonPanel);
        orderView.add(orderInputView);

        orderLogArea = new JTextArea(20, 100);
        orderView.add(new JScrollPane(orderLogArea));

        return orderView;
    }

    private JPanel createInputPanelOfOrdersView()
    {
        JPanel inputPanel = new JPanel();
        Dimension panelDim = new Dimension(500, 400);
        inputPanel.setPreferredSize(panelDim);
        inputPanel.setMaximumSize(panelDim);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        Dimension textFieldDim = new Dimension(200, 30);
        orderProductName = createJTextField(textFieldDim);
        orderClientName = createJTextField(textFieldDim);
        orderQuantity = createJTextField(textFieldDim);

        inputPanel.add(createInputRow("Product Name:  ", orderProductName));
        inputPanel.add(createInputRow("Client Name:      ", orderClientName));
        inputPanel.add(createInputRow("Order Quantity: ", orderQuantity));

        inputPanel.setAlignmentX(0.0f);
        return inputPanel;
    }

    private JPanel createButtonPanelOfOrdersView()
    {
        JPanel buttonsPanel = new JPanel();
        Dimension panelDimension = new Dimension(400, 400);
        buttonsPanel.setPreferredSize(panelDimension);
        buttonsPanel.setMaximumSize(panelDimension);

        Dimension buttonDim = new Dimension(200, 50);
        createOrderButton = createButton("Create order", buttonDim);

        buttonsPanel.add(createOrderButton);

        buttonsPanel.setAlignmentX(0.0f);
        return buttonsPanel;
    }

    private JButton createButton(String msg, Dimension d)
    {
        JButton b = new JButton(msg);
        b.setPreferredSize(d);
        b.setMaximumSize(d);
        b.setAlignmentX(0.0f);
        return  b;
    }

    private JTextField createJTextField(Dimension d)
    {
        JTextField f = new JTextField();
        f.setPreferredSize(d);
        f.setMaximumSize(d);
        return f;
    }


    private JPanel createInputRow(String name, JTextField textField)
    {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

        rowPanel.add(new JLabel(name));
        rowPanel.add(textField);

        rowPanel.setAlignmentX(0.0f);
        return rowPanel;
    }

}
