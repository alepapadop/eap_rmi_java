/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import papadopoulos_common.ClientManagerUserHistory;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.*;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.*;
import papadopoulos_common.StorageManagerProductData;


/**
 *
 * @author alepapadop
 */
public class ClientLoginView {
    
    private JFrame                  _frame          = null;
    private JButton                 _login_button   = null;
    private JFormattedTextField     _user           = null;
    private JPasswordField          _pass           = null;
    private JFormattedTextField     _product        = null;
    private JFormattedTextField     _amount         = null;
    private JButton                 _order_button   = null;
    private JButton                 _product_button = null;
    private JButton                 _credit_button  = null;
    private JButton                 _history_button = null;
    
    private void SetProduct(JFormattedTextField text_field)
    {
        _product = text_field;
    }
    
    public JFormattedTextField GetProduct()
    {
        return _product;
    }
    
    private void SetAmount(JFormattedTextField text_field)
    {
        _amount = text_field;
    }
    
    public JFormattedTextField GetAmount()
    {
        return _amount;
    }
    
    private void SetOrderButton(JButton button)
    {
        _order_button = button;
    }
    
    public JButton GetOrderButton()
    {
        return _order_button;
    }
        
    private void SetProductButton(JButton button)
    {
        _product_button = button;
    }
    
    public JButton GetProductButton()
    {
        return _product_button;
    }
        
    private void SetCreditButton(JButton button)
    {
        _credit_button = button;
    }
    
    public JButton GetCreditButton()
    {
        return _credit_button;
    }
    
    private void SetHistoryButton(JButton button)
    {
        _history_button = button;
    }
    
    public JButton GetHistoryButton()
    {
        return _history_button;
    }
        
    private void SetUser(JFormattedTextField user)
    {
        _user = user;
    }
    
    public JFormattedTextField GetUser()
    {
        return _user;
    }
    
    private void SetPass(JPasswordField pass)
    {
        _pass = pass;
    }
    
    public JPasswordField GetPass()
    {
        return _pass;
    }
    
    private void SetFrame(JFrame frame)
    {
        _frame = frame;
    }
    
    private JFrame GetFrame()
    {
        return _frame;
    }
    
    private void SetLoginButton(JButton button)
    {
        _login_button = button;
    }
    
    private JButton GetLoginButton()
    {
        return _login_button;
    }
    
    private void ClientLoginPopulate()
    {
        JPanel              panel;
        GridLayout          grid_layout;
        JLabel              label;
        JFormattedTextField user;
        JButton             button;
        FlowLayout          flow_layout;
        JPasswordField      pass;
                
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        grid_layout = new GridLayout(2, 2);
        grid_layout.setVgap(10);
        grid_layout.setHgap(10);
        panel.setLayout(grid_layout);
        
        label = new JLabel("Username:");
        user = new JFormattedTextField();
        panel.add(label);
        panel.add(user);
        SetUser(user);
        
        label = new JLabel("Password:");
        pass = new JPasswordField();
        panel.add(label);
        panel.add(pass);
        SetPass(pass);
        
        GetFrame().add(panel, BorderLayout.NORTH);
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        flow_layout = new FlowLayout();
        button = new JButton("Login");
        SetLoginButton(button);
        
        panel.setLayout(flow_layout);
        panel.add(button);
        
        GetFrame().add(panel, BorderLayout.CENTER);        
                
    }
    
    public ClientLoginView()
    {        
        
        ClientWindow    client_window = new ClientWindow();
        
        SetFrame(client_window.GetFrame());
        GetFrame().setTitle("SDY50 Online Store - Login");        
        GetFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ClientLoginPopulate();
        GetFrame().pack();
        GetFrame().setSize(500, 150);
        GetFrame().setVisible(true);
        
    }
    
    private void ClearFrame(JFrame frame)
    {
        frame.getContentPane().removeAll();
        frame.repaint();        
    }
    
    private void ClientOrderPopulate()
    {
        JPanel              panel;
        GridLayout          grid_layout;
        JLabel              label;
        JFormattedTextField text_field;
        JButton             button;
        FlowLayout          flow_layout;
                
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        grid_layout = new GridLayout(2, 2);
        grid_layout.setVgap(10);
        grid_layout.setHgap(10);
        panel.setLayout(grid_layout);
        
        label = new JLabel("Product Code:");
        text_field = new JFormattedTextField();
        SetProduct(text_field);
        panel.add(label);
        panel.add(text_field);                
                
        label = new JLabel("Amount:");
        text_field = new JFormattedTextField();
        SetAmount(text_field);
        panel.add(label);
        panel.add(text_field);
        
        GetFrame().add(panel, BorderLayout.NORTH);
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        flow_layout = new FlowLayout();
        
        button = new JButton("Place Your Order");
        panel.add(button);
        SetOrderButton(button);
        button = new JButton("View Our Products");
        panel.add(button);
        SetProductButton(button);
        button = new JButton("View Your Credit");
        panel.add(button);
        SetCreditButton(button);
        button = new JButton("View Your Orders");
        panel.add(button);
        SetHistoryButton(button);
        
        panel.setLayout(flow_layout);        
        
        GetFrame().add(panel, BorderLayout.CENTER);
        
    }
    
    public void ClientOrderView()
    {
        GetFrame().setVisible(false);
        GetFrame().setTitle("SDY50 Online Store");
        GetFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ClearFrame(GetFrame());
        ClientOrderPopulate();
        GetFrame().pack();
        GetFrame().setVisible(true);
    }
    
    public void LoginFailed()
    {
        JFrame  warning_frame = new JFrame();
        
        JOptionPane.showMessageDialog(warning_frame, 
                                     "The username or password are wrong please try again",
                                     "Login Error",
                                      JOptionPane.WARNING_MESSAGE);
    }
    
    public void CreditInfo(double credit)
    {
        JFrame  credit_frame = new JFrame();
        
        JOptionPane.showMessageDialog(credit_frame, 
                                     "Your credit is " + credit,
                                     "Credit Info",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void HistoryInfo()
    {
        JFrame  history_frame = new JFrame();
        
        JOptionPane.showMessageDialog(history_frame, 
                                     "You have not orders yet.",
                                     "History Info",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void CodeInfo()
    {
        JFrame  code_frame = new JFrame();
        
        JOptionPane.showMessageDialog(code_frame, 
                                     "Your code is empty",
                                     "Order Info",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
        
    public void AmountInfo()
    {
        JFrame  amount_frame = new JFrame();
        
        JOptionPane.showMessageDialog(amount_frame, 
                                     "The amount number is invalid",
                                     "Order Info",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void OrderInfo(int code)
    {
        JFrame  amount_frame = new JFrame();
        String msg = "";
        
        if (code == NoStock.ordinal()) {
            msg = "There is no stock for this product. Please check the product list";
        } else if (code == AmountMoreThanStock.ordinal()) {
            msg = "You ordered more than the stock. Please check the product list";
        } else if (code == ProductNotExist.ordinal()) {
            msg = "This product code does not exist. Please check the product list";
        } else if (code == NoCredit.ordinal()) {
            msg = "Your Credit is not enough for this order. Please check your credit";
        } else if (code == OK.ordinal()) {
            msg = "Your order is executed. Please check your orders";
        }
        
        JOptionPane.showMessageDialog(amount_frame, 
                                      msg,
                                     "Order Info",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void PingInfo(int code)
    {
                        
        if (code != PingOK.ordinal()) {
            JFrame  amount_frame = new JFrame();
            String msg = "";
            
            if (code == ClientManagerDown.ordinal()) {
               msg = "Please Start The Client Manager Server";
            } else if (code == StorageManagerDown.ordinal()) {
               msg = "Please Start the Storage Manager Server";
            }

            JOptionPane.showMessageDialog(amount_frame, 
                                         msg,
                                        "Order Info",
                                         JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void ProductInfoFirstRow(JPanel panel)
    {
        JLabel  label;
        
        label = new JLabel("Name");        
        panel.add(label);
        label = new JLabel("Code");        
        panel.add(label);
        label = new JLabel("Type");        
        panel.add(label);
        label = new JLabel("Rating");        
        panel.add(label);
        label = new JLabel("Stock");        
        panel.add(label);
        label = new JLabel("Price");        
        panel.add(label);
    }
    
    private void ProductInfoPopulateRow(StorageManagerProductData product, JPanel panel)
    {
        JFormattedTextField text_field;
        
        text_field = new JFormattedTextField();
        panel.add(text_field);
        text_field.setText(product.GetName());             
        text_field.setEditable(false);
        
        text_field = new JFormattedTextField();
        panel.add(text_field);
        text_field.setText(product.GetCode());             
        text_field.setEditable(false);
        
        text_field = new JFormattedTextField();
        panel.add(text_field);
        text_field.setText(product.GetType());             
        text_field.setEditable(false);
        
        text_field = new JFormattedTextField();
        panel.add(text_field);
        text_field.setText(product.GetRating());             
        text_field.setEditable(false);
        
        text_field = new JFormattedTextField();
        panel.add(text_field);        
        text_field.setText(((Integer)product.GetStock()).toString());
        text_field.setEditable(false);
        
        text_field = new JFormattedTextField();
        panel.add(text_field);
        text_field.setText(((Double)product.GetPrice()).toString());
        text_field.setEditable(false);
        
    }
    
    public void ProductInfo(ArrayList<StorageManagerProductData> array)
    {
        JFrame product_frame = new JFrame();
        
        JPanel              panel;
        GridLayout          grid_layout;
        
        product_frame.setTitle("Products");
        product_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        grid_layout = new GridLayout(array.size() + 1, 6);
        panel.setLayout(grid_layout);
                        
        ProductInfoFirstRow(panel);
        
        for (StorageManagerProductData i : array) {
            ProductInfoPopulateRow(i, panel);
        }
        
        product_frame.add(panel);
        product_frame.pack();
        product_frame.setVisible(true);
        
    }
    
    private void HistoryInfoFirstRow(JPanel panel)
    {
        JLabel  label;
        
        label = new JLabel("Product");        
        panel.add(label);        
        label = new JLabel("Amount");        
        panel.add(label);
        
        
    }
    
    private void HistoryInfoPopulateRow(ClientManagerUserHistory history, JPanel panel)
    {
        JFormattedTextField text_field;
        
        text_field = new JFormattedTextField();
        panel.add(text_field);
        text_field.setText(history.GetProduct());             
        text_field.setEditable(false);
        
        text_field = new JFormattedTextField();
        panel.add(text_field);
        text_field.setText(((Integer)history.GetAmount()).toString());             
        text_field.setEditable(false);
    }
    
    public void UserHistoryInfo(ArrayList<ClientManagerUserHistory> array)
    {
        if (!array.isEmpty()) {
            JFrame history_frame = new JFrame();

            JPanel              panel;
            GridLayout          grid_layout;

            history_frame.setTitle("Order History");
            history_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));            
            grid_layout = new GridLayout(array.size() + 1, 2);
            panel.setLayout(grid_layout);

            HistoryInfoFirstRow(panel);


            for (ClientManagerUserHistory i : array) {
                HistoryInfoPopulateRow(i, panel);
            }

            history_frame.add(panel);
            history_frame.pack();
            history_frame.setVisible(true);
            history_frame.setSize(500, 150);
        } else {
            HistoryInfo();
        }
        
    }
    
    public void LoginButtonActionListener(ActionListener listener)
    {
        GetLoginButton().addActionListener(listener);
    }
        
    public void CreditButtonActionListener(ActionListener listener)
    {
        GetCreditButton().addActionListener(listener);
    }
        
    public void HistoryButtonActionListener(ActionListener listener)
    {
        GetHistoryButton().addActionListener(listener);
    }
        
    public void OrderButtonActionListener(ActionListener listener)
    {
        GetOrderButton().addActionListener(listener);
    }
        
    public void ProductButtonActionListener(ActionListener listener)
    {
        GetProductButton().addActionListener(listener);
    }
    
    
}
