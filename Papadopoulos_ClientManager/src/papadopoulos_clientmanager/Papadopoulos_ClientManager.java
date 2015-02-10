/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_clientmanager;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import papadopoulos_common.ClientToClientManager;

/**
 *
 * @author alepapadop
 */
public class Papadopoulos_ClientManager {

    /**
     * @param args the command line arguments
     */
    
    private static Papadopoulos_ClientManager _client_manager = null;
    
    private Registry _client_manager_registry;
    
    private ArrayList<ClientManagerUserData> _client_manager_user_data_array;
    
    private void SetClientManager(Papadopoulos_ClientManager client_manager)
    {
        _client_manager = client_manager;
    }
    
    public static Papadopoulos_ClientManager GetClientManager()
    {
        return _client_manager;
    }
    
    public ArrayList<ClientManagerUserData> GetClientManagerUserDataArray()
    {
        return _client_manager_user_data_array;
    }
    
    private void InitClientManagerUserDataArray()
    {
        _client_manager_user_data_array = new ArrayList<>();
    }
    
    private void AddClientManagerUserDataArray(ClientManagerUserData data)
    {
        _client_manager_user_data_array.add(data);
    }
    
    public ClientManagerUserData GetIndexClientManagerUserDataArray(int index)
    {
        return _client_manager_user_data_array.get(index);
    }
    
    private void CreateDummyData()
    {        
        // user1/user1/1500, user2/user2/2500, user3/user3/3500
        ClientManagerUserData user;
        
        InitClientManagerUserDataArray();
        
        user = new ClientManagerUserData();
        user.SetUsername("user1");
        user.SetPassword("user1");
        user.SetCredit(1500);
        AddClientManagerUserDataArray(user);
                
        user = new ClientManagerUserData();
        user.SetUsername("user2");
        user.SetPassword("user2");
        user.SetCredit(2500);
        AddClientManagerUserDataArray(user);
                
        user = new ClientManagerUserData();
        user.SetUsername("user3");
        user.SetPassword("user3");
        user.SetCredit(3500);
        AddClientManagerUserDataArray(user);
    }
    
    private void SetClientManagerRegistry(Registry registry)
    {
        _client_manager_registry = registry;
    }
    
    public Registry GetClientManagerRegistry()
    {
        return _client_manager_registry;
    }
    
    private void StartClientManagerServer()
    {
        try {
            ClientToClientManagerImpl imp = new ClientToClientManagerImpl();
            ClientToClientManager stub = (ClientToClientManager)UnicastRemoteObject.exportObject(imp, 0); //make it avaible to incoming calls
            
            Registry registry = LocateRegistry.createRegistry(1099);
            
            registry.rebind("ClientManager", stub);
            
            //Naming.rebind("axa", stub); //stub is a proxy class, bind a remote object with a name
            System.out.println("Server ready");
        } catch (Exception e) {
            System.out.println("Server main " + e.getMessage());
        }
    }
    
    private void StartClientManager()
    {
        try {            
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1100);
            SetClientManagerRegistry(registry);
                                    
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ClientManager " + e.getMessage());
        }
    }
    
    public Papadopoulos_ClientManager()
    {
        CreateDummyData();
        System.setSecurityManager(null);
        StartClientManagerServer();
        StartClientManager();
    }
    
    public static void main(String[] args) {
        
        Papadopoulos_ClientManager client_manager = new Papadopoulos_ClientManager();
        client_manager.SetClientManager(client_manager);
    }
    
}
