/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author alepapadop
 */
public interface ClientToClientManager extends Remote{
    

    public double   GetCredit(String user) throws RemoteException;
    public Boolean  SentLoginCredentials(String user, String pass) throws RemoteException;
    public ArrayList<StorageManagerProductData> GetProduct() throws RemoteException;
    public ArrayList<ClientManagerUserHistory> GetHistory(String user) throws RemoteException;
    public int  SetOrder(String user, String product_code, int amount) throws RemoteException;
    public int  PingServers() throws RemoteException;
    
}
