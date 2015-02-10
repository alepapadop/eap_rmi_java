/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_storagemanager;


import java.rmi.RemoteException;
import java.util.ArrayList;
import papadopoulos_common.ClientManagerToStorageManager;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.StorageManagerDown;
import static papadopoulos_common.Papadopoulos_Common.ServerPing.StorageManagerOK;
import papadopoulos_common.StorageManagerProductData;
import static papadopoulos_storagemanager.Papadopoulos_StorageManager.GetStorageManager;



/**
 *
 * @author alepapadop
 */
public class ClientManagerToStorageManagerImpl implements ClientManagerToStorageManager {

    @Override
    public ArrayList<StorageManagerProductData> GetProductList() throws RemoteException {        
        
        return GetStorageManager().GetStoragetManagerUserDataArray();
        
    }

    @Override
    public int SetOrderData(String product_code, int amount) throws RemoteException {
        
        return GetStorageManager().GetStorageManagerUpdateProcessResult(product_code, amount);
        
    }

    @Override
    public double GetProductPrice(String product_code) throws RemoteException {
        
        return GetStorageManager().GetPrice(product_code);
        
    }

    @Override
    public int PingStorageManager() throws RemoteException {
        
        if (GetStorageManager() == null) {
            return StorageManagerDown.ordinal();
        }
        
        return StorageManagerOK.ordinal();
    }
    
}
