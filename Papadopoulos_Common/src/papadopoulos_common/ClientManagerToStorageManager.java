/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alepapadop
 */
public interface ClientManagerToStorageManager extends Remote {
    
    public ArrayList<StorageManagerProductData> GetProductList () throws RemoteException;
    public int SetOrderData(String product_code, int amount) throws RemoteException;
    public double GetProductPrice(String product_code) throws RemoteException;
    public int    PingStorageManager() throws RemoteException;
    
}
