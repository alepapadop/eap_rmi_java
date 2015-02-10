/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_client;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author alepapadop
 */
public class Papadopoulos_Client {

    private static Registry _client_registry;
    
    public static Registry GetClientRegistry()
    {
        return _client_registry;
    }
    
    private void SetClientRegistry(Registry registry)
    {
        _client_registry = registry;
    }
    
    private void StartClient()
    {        
        System.setSecurityManager(null);
        try {            
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            SetClientRegistry(registry);
                                    
            ClientLoginController controller = new ClientLoginController();
            
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Client " + e.getMessage());
        }
        
    }
    
    public Papadopoulos_Client()
    {
        StartClient();
    }
    
    public static void main(String[] args) {
                    
        Papadopoulos_Client client = new Papadopoulos_Client();
        
    }
    
}
