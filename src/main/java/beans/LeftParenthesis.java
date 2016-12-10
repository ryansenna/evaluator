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
public class LeftParenthesis implements Operable{
    private String element;

    public LeftParenthesis(String element) {
        this.element = element;
    }

    public LeftParenthesis() {
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    
    @Override
    public boolean isLeftParenthesis() {
        return true;
    }

    @Override
    public boolean isOperand() {
        return false;
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
        return true;
    }
    
}
