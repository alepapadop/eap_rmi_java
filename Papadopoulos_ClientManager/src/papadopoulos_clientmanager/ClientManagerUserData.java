/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_clientmanager;

import java.util.ArrayList;
import papadopoulos_common.ClientManagerUserHistory;

/**
 *
 * @author alepapadop
 */
public class ClientManagerUserData {
    
    private String _username;
    private String _password;
    private double _credit;
    private ArrayList<ClientManagerUserHistory> _history;
    
    public synchronized void SetUsername(String user)
    {
        _username = user;
    }
    
    public String GetUsername()
    {
        return _username;
    }
    
    public synchronized void SetPassword(String pass)
    {
        _password = pass;
    }
    
    public String GetPassword()
    {
        return _password;
    }
    
    public synchronized void SetCredit(double credit)
    {
        _credit = credit;
    }
    
    public double GetCredit()
    {
        return _credit;
    }
    
    public synchronized void SetHistoryArray(ArrayList<ClientManagerUserHistory> list)
    {
        _history = list;
    }
    
    public ArrayList<ClientManagerUserHistory> GetHistoryArray()
    {
        return _history;
    }
    
    private void InitHistoryArray()
    {
        ArrayList<ClientManagerUserHistory> list = new ArrayList<>();
        SetHistoryArray(list);
    
    }
    
    public ClientManagerUserHistory GetHistoryArrayIndex(int index)
    {
        ArrayList<ClientManagerUserHistory> list = GetHistoryArray();
        ClientManagerUserHistory user_history = list.get(index);
        return user_history;
    }
    
    private synchronized void SetHistoryArrayEntry(ClientManagerUserHistory user_history)
    {
        ArrayList<ClientManagerUserHistory> list = GetHistoryArray();
        list.add(user_history);
    }
    
    public synchronized void InsertNewHistoryEntry(String product, int amount)
    {
        ClientManagerUserHistory user_history = new ClientManagerUserHistory();
        user_history.SetAmount(amount);
        user_history.SetProduct(product);
        SetHistoryArrayEntry(user_history);
    }
    
    ClientManagerUserData()
    {
        InitHistoryArray();
    }
}
