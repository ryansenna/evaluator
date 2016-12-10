/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Railanderson Sena
 */
public class Operand implements Operable {
    private String element;
    
    public Operand(){}
    public Operand(String element) {
        this.element = element;
    }
    public double toDouble(){
        return Double.parseDouble(element);
    }
    public String getElement() {
        return element;
    }

    @Override
    public boolean isLeftParenthesis() {
        return false;
    }

    @Override
    public boolean isOperand() {
        return true;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public boolean isRightParenthesis() {
        return false;
    }

    @Override
    public boolean isSymbol() {
        return false;
    }

    public void setElement(String element) {
        this.element = element;
    }
    
}
