/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_clientmanager;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.rmi.RemoteException;
import java.util.ArrayList;
import static papadopoulos_clientmanager.Papadopoulos_ClientManager.GetClientManager;
import papadopoulos_common.ClientManagerToStorageManager;
import papadopoulos_common.ClientManagerUserHistory;
import papadopoulos_common.ClientToClientManager;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.ClientAndStorageManagerDown;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.ClientManagerDown;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.ClientManagerOK;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.PingOK;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.StorageManagerDown;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.StorageManagerOK;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.NoCredit;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.OK;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.UnknownError;
import papadopoulos_common.StorageManagerProductData;


/**
 *
 * @author alepapadop
 */
public class ClientToClientManagerImpl implements ClientToClientManager {
    
    
    private Boolean Authenticate(String user, String pass)
    {
        Boolean ret_val = FALSE;
        ArrayList<ClientManagerUserData> user_data = GetClientManager().GetClientManagerUserDataArray();
                       
        for (ClientManagerUserData i : user_data) {
            if (i.GetUsername().equals(user) && i.GetPassword().equals(pass)) {                
                ret_val = TRUE;                
            }
        }
        return ret_val;
    }
    
    private double CreditData(String user)
    {
        double ret_val = -1;
        ArrayList<ClientManagerUserData> user_data = GetClientManager().GetClientManagerUserDataArray();
        
        for (ClientManagerUserData i : user_data) {            
            if (i.GetUsername().equals(user)) {            
                    ret_val = i.GetCredit();                    
            }
        }
        return ret_val;
    }
    
    private ArrayList<ClientManagerUserHistory> GetHistoryDataArray(String user)
    {
        ArrayList<ClientManagerUserHistory> ret_val = null;
        ArrayList<ClientManagerUserData> user_data = GetClientManager().GetClientManagerUserDataArray();        
        
        for (ClientManagerUserData i : user_data) {     
            if (i.GetUsername().equals(user)) {                    
                    ret_val = i.GetHistoryArray();
            }            
        }
        return ret_val;
    }
    
    private void UpdateUserHistoryArray(ArrayList<ClientManagerUserHistory> array, String product, int amount)
    {
                        
        if (!array.isEmpty()) {            
            for (ClientManagerUserHistory i : array) {
                if (i.GetProduct().equals(product)) {                    
                    i.SetAmount(i.GetAmount() + amount);                    
                    return;
                }
            }
        }                
        
        ClientManagerUserHistory history = new ClientManagerUserHistory();
        history.SetProduct(product);
        history.SetAmount(amount);

        array.add(history);            

    }
       
    
    @Override
    public Boolean SentLoginCredentials(String user, String pass) {                
                
        return Authenticate(user, pass); 
    }
    
    
    @Override
    public double GetCredit(String user) throws RemoteException {
        return CreditData(user);
    }

    @Override
    public ArrayList<StorageManagerProductData> GetProduct() throws RemoteException {
        try {
            ClientManagerToStorageManager manager_to_storage = (ClientManagerToStorageManager)GetClientManager().GetClientManagerRegistry().lookup("StorageManager");                        
            
            return manager_to_storage.GetProductList();
            
        } catch(RemoteException e) {
             System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ClientManager " + e.getMessage());
        }
        
        return null;
    }

    @Override
    public ArrayList<ClientManagerUserHistory> GetHistory(String user) throws RemoteException {
                                
        return GetHistoryDataArray(user); 
    }

    @Override
    public int SetOrder(String user, String product_code, int amount) throws RemoteException {
        int ret_val = UnknownError.ordinal();
        
        try {
            double price = 0;
            int index = 0;
            ArrayList<ClientManagerUserData> user_data = GetClientManager().GetClientManagerUserDataArray();
            
            ClientManagerToStorageManager manager_to_storage = (ClientManagerToStorageManager)GetClientManager().GetClientManagerRegistry().lookup("StorageManager");
                                                
            price = manager_to_storage.GetProductPrice(product_code);
            
            for (ClientManagerUserData k : user_data) {
                if (k.GetUsername().equals(user)) {
                    if (k.GetCredit() < amount * price) {
                        return NoCredit.ordinal();
                    }
                    break;
                }
                ++index;
            }
            
            user_data.get(index).SetCredit(user_data.get(index).GetCredit() - amount * price);
            
            ret_val = manager_to_storage.SetOrderData(product_code, amount);
            
            if (ret_val == OK.ordinal()) {                
                ArrayList<ClientManagerUserHistory> array = GetHistoryDataArray(user);
                
                UpdateUserHistoryArray(array, product_code, amount);
            }
            
        } catch(RemoteException e) {
             System.out.println(e.getMessage());             
        } catch (Exception e) {
            System.out.println("ClientManager SetOrder " + e.getMessage());            
        }
        
        return ret_val;
    }

    @Override
    public int PingServers() throws RemoteException {
        int ret_val1 = ClientManagerOK.ordinal();
        int ret_val2 = StorageManagerOK.ordinal();
        int ret_val = PingOK.ordinal();
        
        if (GetClientManager() == null) {
            ret_val1 = ClientManagerDown.ordinal();
        }
        
        if (ret_val1 == ClientManagerOK.ordinal()) {
            try {
                
                ClientManagerToStorageManager manager_to_storage = (ClientManagerToStorageManager)GetClientManager().GetClientManagerRegistry().lookup("StorageManager");                        

                ret_val2 = manager_to_storage.PingStorageManager();

            } catch(RemoteException e) {
                 System.out.println(e.getMessage());
                 ret_val2 = StorageManagerDown.ordinal();
            } catch (Exception e) {
                System.out.println("ClientManager " + e.getMessage());
                ret_val2 = StorageManagerDown.ordinal();
            }
        }
        if (ret_val1 == ClientManagerOK.ordinal() && ret_val2 == StorageManagerOK.ordinal()) {
            
            ret_val = PingOK.ordinal();
            
        } else if (ret_val1 == ClientManagerDown.ordinal() && ret_val2 == StorageManagerOK.ordinal()) {
            
            ret_val = ClientManagerDown.ordinal();
            
        } else if (ret_val1 == ClientManagerOK.ordinal() && ret_val2 == StorageManagerDown.ordinal()) {
            
            ret_val = StorageManagerDown.ordinal();
            
        } else {
            
            ret_val = ClientAndStorageManagerDown.ordinal();
            
        }
        
        return ret_val;
    }

}
