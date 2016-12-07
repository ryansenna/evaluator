/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import beans.Operator;
import java.util.List;
import queues.Queue;
import queues.Stack;

/**
 *
 * @author ryan Sena
 */
public class Calculator {

    private Queue infix;
    private Queue postfix;
    private Stack stack;

    public Calculator(Queue inf) {
        setInfix(inf);
        postfix = new Queue();
        stack = new Stack();
    }

    private boolean isElementLeftParenthesis(String element) {
        return element.contains("(");
    }

    private boolean isElementRightParenthesis(String element) {
        return element.contains(")");
    }

    private void setInfix(Queue inf) {
        if (!validateInfix(inf)) {
            throw new IllegalArgumentException("Error 200: Queue is not valid");
        }
        this.infix = inf;
    }

    public Queue getPostfixExpression() {
        return postfix;
    }

    /**
     * This method will make the conversion between the received infix Q to
     * postfix
     */
    public void toPostfix() {
        int counter = 0;
        while (!infix.isEmpty()) {
            String element = infix.dequeue();
            if (isElementNumeric(element)) {
                postfix.enqueue(element);
            }
            if (isElementSymbol(element)) {
                if (isElementLeftParenthesis(element)) {
                    counter++;
                }
                if (isElementRightParenthesis(element)) {
                    counter++;
                }
                if (isElementOperator(element)) {
                    Operator op = this.toOperator(element);
                    if(counter == 2){
                        Operator topOperator = stack.peek();
                        topOperator.setPrecedenceValue(3);
                        counter = 0;
                    }
                    Operator returnedOP = stack.push(op);
                    if (returnedOP != null) {
                        postfix.enqueue(returnedOP.getElement());
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix.enqueue(stack.pop().getElement());
        }

    }
    

    private boolean isOperandOnBothEnds(Queue inf) {
        String operand1 = inf.peek();
        String operand2 = inf.lastIn();
        char startSymbol = '(';
        char endSymbol = ')';

        if (!Character.isDigit(operand1.charAt(0))) {
            if (operand1.charAt(0) != startSymbol) {
                return false;
            }
        }
        if (!Character.isDigit(operand2.charAt(0))) {
            if (operand2.charAt(0) != endSymbol) {
                return false;
            }
        }

        return true;
    }

    /**
     * checks if the q contains the right symbols or numbers.
     *
     * @return
     */
    private boolean isQNumericOrSymbols(Queue inf) {
        String element = "";
        for (int i = 0; i < inf.size(); i++) {
            element = inf.get(i);
            if (element.length() > 1) {
                return false;
            }
            if (!isElementSymbol(element)) {
                if (!isElementNumeric(element)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     */
    private boolean validateInfix(Queue inf) {
        if (!isQNumericOrSymbols(inf)) {
            return false;
        }
        return isOperandOnBothEnds(inf);
    }

    private boolean isElementSymbol(String element) {
        String symbols = "+-*/()";
        return symbols.contains(element);
    }

    private boolean isElementOperator(String element) {
        String symbols = "+-*/";
        return symbols.contains(element);
    }

    private boolean isElementNumeric(String element) {
        String numbers = "1234567890";
        return numbers.contains(element);
    }

    private Operator toOperator(String element) {
        String parenthesis = "()";
        String addsub = "+-";
        String multidiv = "*/";
        if (parenthesis.contains(element)) {
            return new Operator(3, element);
        } else if (multidiv.contains(element)) {
            return new Operator(2, element);
        } else {
            return new Operator(1, element);
        }

    }
}
