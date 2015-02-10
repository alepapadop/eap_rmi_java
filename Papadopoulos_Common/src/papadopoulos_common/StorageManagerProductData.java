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
public class StorageManagerProductData implements Serializable {
    
    private String  _name;
    private String  _code;
    private String  _type;
    private String  _rating;
    private int     _stock;
    private double  _price;

    /**
     * @return the _name
     */
    public String GetName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void SetName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _code
     */
    public String GetCode() {
        return _code;
    }

    /**
     * @param _code the _code to set
     */
    public void SetCode(String _code) {
        this._code = _code;
    }

    /**
     * @return the _type
     */
    public String GetType() {
        return _type;
    }

    /**
     * @param _type the _type to set
     */
    public void SetType(String _type) {
        this._type = _type;
    }

    /**
     * @return the _rating
     */
    public String GetRating() {
        return _rating;
    }

    /**
     * @param _rating the _rating to set
     */
    public void SetRating(String _rating) {
        this._rating = _rating;
    }

    /**
     * @return the _stock
     */
    public int GetStock() {
        return _stock;
    }

    /**
     * @param _stock the _stock to set
     */
    public void SetStock(int _stock) {
        this._stock = _stock;
    }

    /**
     * @return the _price
     */
    public double GetPrice() {
        return _price;
    }

    /**
     * @param _price the _price to set
     */
    public void SetPrice(double _price) {
        this._price = _price;
    }
            
}
