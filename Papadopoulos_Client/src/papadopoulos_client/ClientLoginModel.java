/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_client;

import static java.lang.Boolean.FALSE;
import java.rmi.RemoteException;
import java.util.ArrayList;
import papadopoulos_common.ClientManagerUserHistory;
import papadopoulos_common.ClientToClientManager;
import papadopoulos_common.StorageManagerProductData;

/**
 *
 * @author alepapadop
 */
public class ClientLoginModel {
    
    Boolean _user_reply = FALSE;
    Boolean _pass_reply = FALSE;
    Boolean _auth_reply = FALSE;
    double  _credit_reply = 0;
    ArrayList<StorageManagerProductData> _product_reply = null;
    ArrayList<ClientManagerUserHistory> _history_reply = null;
    int     _order_reply;
    int     _ping_reply;
    
    private void SetOrderReply(int order_reply)
    {
        _order_reply = order_reply;
    }
    
    public int GetOrderReply()
    {
        return _order_reply;
    }
    
    private void SetHistoryReply(ArrayList<ClientManagerUserHistory> user_history)
    {
        _history_reply = user_history;
    }
    
    public ArrayList<ClientManagerUserHistory> GetHistoryReply()
    {
        return _history_reply;
    }
    
    private void SetProductReply(ArrayList<StorageManagerProductData> array)
    {
        _product_reply = array;
    }
    
    public ArrayList<StorageManagerProductData> GetProductReply()
    {
        return _product_reply;
    }
    
    private void SetCreditReply(double credit)
    {
        _credit_reply = credit;
    }
    
    public double GetCreditReply()
    {
        return _credit_reply;
    }
    
    private void SetUserReply(Boolean user_reply)
    {
        _user_reply = user_reply;
    }
    
    public Boolean GetUserReply()
    {
        return _user_reply;
    }
    
    private void SetPassReply(Boolean pass_reply)
    {
        _pass_reply = pass_reply;
    }
    
    public Boolean GetPassReply()
    {
        return _pass_reply;
    }
    
    private void SetAuthReply(Boolean auth_reply)
    {
        _auth_reply = auth_reply;
    }
    
    public Boolean GetAuthReply()
    {
        return _auth_reply;
    }
    
    public void SentLoginCredentials(String user, String pass)
    {
        try {

            Boolean auth_reply;
            
            ClientToClientManager client_to_clientmanager = (ClientToClientManager)Papadopoulos_Client.GetClientRegistry().lookup("ClientManager");

            auth_reply = client_to_clientmanager.SentLoginCredentials(user, pass);
            
            SetAuthReply(auth_reply);
            
        } catch( RemoteException e) {
            System.out.println("Remote Exception Client " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Client " + e.getMessage());
        }
    }
    
    public void Credit(String user)
    {
         try {
            double credit;
            
            ClientToClientManager client_to_clientmanager = (ClientToClientManager)Papadopoulos_Client.GetClientRegistry().lookup("ClientManager");

            credit = client_to_clientmanager.GetCredit(user);
                        
            SetCreditReply(credit);
            
        } catch( RemoteException e) {
            System.out.println("Remote Exception Client " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Client " + e.getMessage());
        }
    }
    
    public void ProductList()
    {
         try {
            ArrayList<StorageManagerProductData> products;
            
            ClientToClientManager client_to_clientmanager = (ClientToClientManager)Papadopoulos_Client.GetClientRegistry().lookup("ClientManager");

            products = client_to_clientmanager.GetProduct();
                        
            SetProductReply(products);
            
        } catch( RemoteException e) {
            System.out.println("Remote Exception Client " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Client " + e.getMessage());
        }
    }
    
    public void History(String user)
    {
         try {
            ArrayList<ClientManagerUserHistory> history;
            
            ClientToClientManager client_to_clientmanager = (ClientToClientManager)Papadopoulos_Client.GetClientRegistry().lookup("ClientManager");

            history = client_to_clientmanager.GetHistory(user);
            
            
            SetHistoryReply(history);
            
        } catch( RemoteException e) {
            System.out.println("Remote Exception Client " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Client " + e.getMessage());
        }
    }
    
    public void Order(String user, String product, int amount)
    {
        try {
            int order_reply;
            
            ClientToClientManager client_to_clientmanager = (ClientToClientManager)Papadopoulos_Client.GetClientRegistry().lookup("ClientManager");

            order_reply = client_to_clientmanager.SetOrder(user, product, amount);
                        
            SetOrderReply(order_reply);
            
        } catch( RemoteException e) {
            System.out.println("Remote Exception Client " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Client " + e.getMessage());
        }
        
        
    }
    
    private void SetPingReply(int ping)
    {
        _ping_reply = ping;
    }
    
    public int GetPingReply()
    {
        return _ping_reply;
    }
    
    public void Ping()
    {
        try {
            int reply;
            ClientToClientManager client_to_clientmanager = (ClientToClientManager)Papadopoulos_Client.GetClientRegistry().lookup("ClientManager");

            reply = client_to_clientmanager.PingServers();
            
            SetPingReply(reply);
            
        } catch( RemoteException e) {
            System.out.println("Remote Exception Client " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Client " + e.getMessage());
        }
    }
    
}
