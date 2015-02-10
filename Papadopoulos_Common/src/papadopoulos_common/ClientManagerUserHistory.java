/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_common;

import java.io.Serializable;

/**
 *
 * @author alepapadop
 */
public class ClientManagerUserHistory implements Serializable {
    private String  _product;
    private int     _amount;


    public String GetProduct() {
        return _product;
    }
    
    public void SetProduct(String _product) {
        this._product = _product;
    }

    public int GetAmount() {
        return _amount;
    }

    public void SetAmount(int _amount) {
        this._amount = _amount;
    }
    
    
    
}
