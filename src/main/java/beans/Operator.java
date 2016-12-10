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
public class Operator implements Comparable<Operator>, Operable{
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
        return true;
    }

    @Override
    public boolean isRightParenthesis() {
        return false;
    }

    @Override
    public boolean isSymbol() {
        return true;
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

    @Override
    public int compareTo(Operator o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        
        if(this == o)
            return EQUAL;
        if(this.getPrecedenceValue() < o.getPrecedenceValue())
            return BEFORE;
        if(this.getPrecedenceValue() > o.getPrecedenceValue())
            return AFTER;
        return EQUAL;
    }
 
    
}
