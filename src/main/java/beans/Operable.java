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
public interface Operable {
    boolean isOperand();
    boolean isOperator();
    boolean isLeftParenthesis();
    boolean isRightParenthesis();
    boolean isSymbol();
}
