/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_common;

/**
 *
 * @author alepapadop
 */
public class Papadopoulos_Common {
    
    public static enum LoginActions {
        LoginSucess, 
        LoginFailure
    }
    
    public static enum StorageDataUpdate {
        NoStock,
        AmountMoreThanStock,
        OK,
        ProductNotExist,
        UnknownError,
        NoCredit
    }
    
    public static enum ServerPing {
        ClientManagerDown,
        ClientManagerOK,
        StorageManagerDown,
        StorageManagerOK,
        ClientAndStorageManagerDown,
        PingOK
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
