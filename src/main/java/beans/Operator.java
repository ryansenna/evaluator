/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author 1333612
 */
public class Operator {
    private int precedenceValue;
    private String element;
    
    public Operator(){
        super();
    }
    
    public Operator(int precedenceValue, String element){
        this.precedenceValue = precedenceValue;
        this.element = element;
    }

    public int getPrecedenceValue() {
        return precedenceValue;
    }

    public void setPrecedenceValue(int precedenceValue) {
        this.precedenceValue = precedenceValue;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
 
    
}
