package presentation;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JPanel content;

    private JPanel buttons;
    private JButton clientViewButton;
    private JButton productViewButton;
    private JButton tableViewButton;

    private JPanel clientView;
    private JTextField clientId;
    private JTextField clientName;
    private JTextField clientAddres;
    private JTextField clientEmail;
    private JTextField clientAge;

    public View()
    {
        content = new JPanel();
        content.setSize( 1000, 1000);
        ((FlowLayout)content.getLayout()).setAlignment(FlowLayout.LEFT);


        createButtons();
        createClientsView();

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

    private JButton createButton(String msg, Dimension d)
    {
        JButton b = new JButton(msg);
        b.setPreferredSize(d);
        b.setMaximumSize(d);
        return  b;
    }

    private void createClientsView()
    {
        clientView = new JPanel();
        Dimension d = new Dimension(1000, 200);
        clientView.setPreferredSize(d);
        clientView.setMaximumSize(d);
        clientView.setLayout(new BoxLayout(clientView, BoxLayout.X_AXIS));

        Dimension textFieldDim = new Dimension(200, 30);
        clientId = createJTextField(textFieldDim);
        clientName = createJTextField(textFieldDim);
        clientAddres = createJTextField(textFieldDim);
        clientEmail = createJTextField(textFieldDim);
        clientAge = createJTextField(textFieldDim);

        Dimension panelDim = new Dimension(150, 150);
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setPreferredSize(panelDim);
        textFieldsPanel.setMaximumSize(panelDim);
        textFieldsPanel.setLayout(new BoxLayout(textFieldsPanel, BoxLayout.Y_AXIS));
        textFieldsPanel.add(clientId);
        textFieldsPanel.add(clientName);
        textFieldsPanel.add(clientAddres);
        textFieldsPanel.add(clientEmail);
        textFieldsPanel.add(clientAge);

        clientView.add(textFieldsPanel);

        content.add(clientView);
    }

    private JTextField createJTextField(Dimension d)
    {
        JTextField f = new JTextField();
        f.setPreferredSize(d);
        f.setMaximumSize(d);
        return f;
    }
}
