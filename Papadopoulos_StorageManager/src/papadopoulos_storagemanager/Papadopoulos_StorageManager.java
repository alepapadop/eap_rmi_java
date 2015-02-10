/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_storagemanager;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import papadopoulos_common.ClientManagerToStorageManager;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.AmountMoreThanStock;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.NoStock;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.OK;
import static papadopoulos_common.Papadopoulos_Common.StorageDataUpdate.ProductNotExist;
import papadopoulos_common.StorageManagerProductData;

/**
 *
 * @author alepapadop
 */
public class Papadopoulos_StorageManager {
    
    private static Papadopoulos_StorageManager _storage_manager = null;
    
    private Registry _storage_manager_registry = null;
    
    private ArrayList<StorageManagerProductData> _storage_manager_product_data_array;
    
    private synchronized void SetStorageManager(Papadopoulos_StorageManager storage_manager)
    {
        _storage_manager = storage_manager;
    }
    
    public static Papadopoulos_StorageManager GetStorageManager()
    {
        return _storage_manager;
    }
    
    public ArrayList<StorageManagerProductData> GetStoragetManagerUserDataArray()
    {
        return _storage_manager_product_data_array;
    }
    
    private void InitStorageManagerProductDataArray()
    {
        _storage_manager_product_data_array = new ArrayList<>();
    }
    
    private synchronized void AddStorageManagerProductDataArray(StorageManagerProductData data)
    {
        _storage_manager_product_data_array.add(data);
    }
    
    public StorageManagerProductData GetIndexStorageManagerProductDataArray(int index)
    {
        return _storage_manager_product_data_array.get(index);
    }
    
    private void CreateNewProductEntry(String name, String code, String type,
                                       String rating, int stock, double price)
    {
        StorageManagerProductData product = new StorageManagerProductData();
        product.SetName(name);
        product.SetCode(code);
        product.SetType(type);
        product.SetRating(rating);
        product.SetStock(stock);
        product.SetPrice(price);
        AddStorageManagerProductDataArray(product);
    }
    
    private void CreateDummydata()
    {
        InitStorageManagerProductDataArray();
        CreateNewProductEntry("Samsung Galaxy S4", "SM-2CA1", "Smartphone", "8.5/10", 5, 280);
        
        CreateNewProductEntry("Samsung Galaxy S5", "SM-2D02", "Smartphone", "9.5/10", 01, 410);
        CreateNewProductEntry("Windows 8.1 Pro", "MS-2013", "Operating System", "7.5/10", 15, 150);
        CreateNewProductEntry("Acer Aspire E5-571-57BR", "AS-992A", "Laptop", "9.5/10", 5, 480);
        CreateNewProductEntry("Apple MacBook Pro", "APL-2221", "Laptop", "9/10", 20, 1700);
        
        CreateNewProductEntry("Apple iPhone 5s", "APL-2A11", "Smartphone", "8/10", 4, 450);
        CreateNewProductEntry("Apple iPhone 6", "APL-2D11", "Smartphone", "8/10", 5, 650);
        CreateNewProductEntry("Dell Inspiron i3647", "DL21-4221", "Desktop", "9.5/10", 12, 550);
       
    }
    
    private void SetStorageManagerRegistry(Registry registry)
    {
        _storage_manager_registry = registry;
    }
    
    private Registry GetStorageManagerRegistry()
    {
        return _storage_manager_registry;
    }
    
    private void StartStorageManagerServer()
    {
        try {
            ClientManagerToStorageManagerImpl imp = new ClientManagerToStorageManagerImpl();
            ClientManagerToStorageManager stub = (ClientManagerToStorageManager)UnicastRemoteObject.exportObject(imp, 0); //make it avaible to incoming calls
            
            Registry registry = LocateRegistry.createRegistry(1100);
            SetStorageManagerRegistry(registry);
            
            registry.rebind("StorageManager", stub);
                
            System.out.println("StorageManager Server ready");
        } catch (Exception e) {
            System.out.println("Server main " + e.getMessage());
        }
    }
    
    private synchronized void UpdateEntry(StorageManagerProductData data, int amount)
    {
        int old_val = data.GetStock();
        data.SetStock(old_val - amount);
    }
    
    public int GetStorageManagerUpdateProcessResult(String product_code, int amount)
    {
        int flag = OK.ordinal();
        ArrayList<StorageManagerProductData> array = GetStoragetManagerUserDataArray();
        
        for (StorageManagerProductData i : array) {
            if (i.GetCode().equals(product_code)) {
                if (i.GetStock() == 0) {
                    flag = NoStock.ordinal();
                    return flag;
                } else if (i.GetStock() < amount) {
                    flag = AmountMoreThanStock.ordinal();
                    return flag;
                } else {
                    UpdateEntry(i, amount);
                    return flag;
                }
            }
        }
        
        return ProductNotExist.ordinal();
    }
    
    public double GetPrice(String product_code)
    {
        ArrayList<StorageManagerProductData> array = GetStoragetManagerUserDataArray();
        
        for (StorageManagerProductData i : array) {
            if (i.GetCode().equals(product_code)) {
                return i.GetPrice();
            }
        }
        
        return -1;
    }
    
    public Papadopoulos_StorageManager()
    {
        CreateDummydata();
        System.setSecurityManager(null);
        StartStorageManagerServer();
    }
    
    public static void main(String[] args) {
        
        Papadopoulos_StorageManager storage_manager = new Papadopoulos_StorageManager();
        storage_manager.SetStorageManager(storage_manager);
    }
    
}
