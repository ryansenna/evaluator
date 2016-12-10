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
public class RightParenthesis implements Operable{
    private String element;

    public RightParenthesis(String element) {
        this.element = element;
    }

    public RightParenthesis() {
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
    @Override
    public boolean isLeftParenthesis() {
        return false;
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
        return true;
    }

    @Override
    public boolean isSymbol() {
        return true;
    }
    
}
