/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import papadopoulos_common.ClientManagerUserHistory;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.PingOK;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.AmountMoreThanStock;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.NoStock;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.OK;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.ProductNotExist;
import papadopoulos_common.StorageManagerProductData;

/**
 *
 * @author alepapadop
 */
public class ClientLoginController {
    
    private final int invalid_text_field = Integer.MIN_VALUE;
    
    private final ClientLoginView view = new ClientLoginView();
    
    private final ClientLoginModel model = new ClientLoginModel();
    
    private String _auth_user;
    
    private void SetAuthUser(String auth_user)
    {
        _auth_user = auth_user;
    }
    
    private String GetAuthUser()
    {
        return _auth_user;
    }
    
    private String GetStringFromJFormmatedTextField(JFormattedTextField formatted_text_field)
    {
                
        return formatted_text_field.getText();                
    }
    
    private String GetStringFromJPasswordField(JPasswordField password_field)
    {
        char[] password = password_field.getPassword();
        return new String(password);
    }
    
    // Returns a double from the input fields
    private int GetIntFromJFormattedTextFiedl(JFormattedTextField text_field)
    {
        String str;
        int ret_val;
        
        str = text_field.getText();
        if (str.length() != 0) {
            try {
                ret_val = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                ret_val = invalid_text_field;
            }
        } else {
            ret_val = invalid_text_field;
        }
        
        return ret_val;
    }
    
    private void FireLoginAction(Boolean auth_reply)
    {
        if (auth_reply) {
            view.ClientOrderView();
        } else {
            view.LoginFailed();
        }
    }        
    
    private void SetListenersOrder()
    {
        
        view.CreditButtonActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {                        
                        double credit;
                        model.Credit(GetAuthUser());
                        credit = model.GetCreditReply();
                        
                        view.CreditInfo(credit);
                    }
                }
        );
        
        view.ProductButtonActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        ArrayList<StorageManagerProductData> array;                                                
                        
                        model.ProductList();
                        array = model.GetProductReply();
                        
                        view.ProductInfo(array);
                        
                    }
                }
        );
        
        view.OrderButtonActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        String product_code = GetStringFromJFormmatedTextField(view.GetProduct());
                        int amount = GetIntFromJFormattedTextFiedl(view.GetAmount());
                        
                        if (amount == invalid_text_field || amount <= 0) {
                            view.AmountInfo();
                        } else if (product_code.equals("")) {
                            view.CodeInfo();
                        } else {
                            int reply;
                            
                            model.Order(GetAuthUser(), product_code, amount);
                            reply = model.GetOrderReply();
                            
                            // if (reply == OK.ordinal()) {
                            //    System.out.println("OK");
                            //} else if (reply == NoStock.ordinal()) {
                            //    System.out.println("No Stock");
                            //} else if (reply == AmountMoreThanStock.ordinal()) {
                             //   System.out.println("Too much amount");
                            //} else if (reply == ProductNotExist.ordinal()) {
                            //    System.out.println("No product");
                            //}
                            view.OrderInfo(reply);
                        }
                    }
                }
        );
        
        view.HistoryButtonActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        ArrayList<ClientManagerUserHistory> array;
                        model.History(GetAuthUser());
                        
                        array = model.GetHistoryReply();
                        
                        view.UserHistoryInfo(array);
                    }
                }
        );
    }
    
    private void SetListeners()
    {
        view.LoginButtonActionListener(
            new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent evt) 
                {
                    
                    model.Ping();
                    if (model.GetPingReply() != PingOK.ordinal()) {
                          view.PingInfo(model.GetPingReply());
                    } else {

                        Boolean auth_reply = false;
                        SetAuthUser(null);
                        String user = GetStringFromJFormmatedTextField(view.GetUser());
                        String pass = GetStringFromJPasswordField(view.GetPass());
                        model.SentLoginCredentials(user, pass);
                        auth_reply = model.GetAuthReply();                    
                        FireLoginAction(auth_reply);
                        if (auth_reply) {
                            SetAuthUser(user);
                            SetListenersOrder();
                        }
                    }
                    
                }
            }
        );
        
        
    }
    
    public ClientLoginController()
    {
        
        SetListeners();
    }
}
