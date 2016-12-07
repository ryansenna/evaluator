/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import beans.Operator;
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
    int countLeftParenthesis = 0;
    int countRightParenthesis = 0;
    int totalParCount = 0;

    public Calculator(Queue inf) {
        setInfix(inf);
        postfix = new Queue();
        stack = new Stack();
    }

    public Queue getPostfixExpression() {
        return postfix;
    }

    /**
     * This method will make the conversion between the received infix Q to
     * postfix
     */
    public void toPostfix() {
        int precedenceValue = 50 + countLeftParenthesis;
        int countExpressions = 0;
        while (!infix.isEmpty()) {// loop until the Q is empty.
            String element = infix.dequeue();// dequeue the first element.
            if (isElementNumeric(element)) {// check if it is numeric
                postfix.enqueue(element);// if it is numeric, automatically enqueue to postfix.
            } else {
                if (isElementLeftParenthesis(element)) {
                    countExpressions++;
                }
                if (isElementRightParenthesis(element)) {
                    countExpressions--;
                }

                if (isElementOperator(element)) {// check if it is an operator.
                    Operator operatorToAdd = toOperator(element);// get what operator is.
                    if (countExpressions != 0) {
                        operatorToAdd.setPrecedenceValue(precedenceValue);
                        precedenceValue--;
                    }
                    if (!stack.isEmpty()) {
                        Operator operatorInStack = stack.peek();
                        if (operatorToAdd.compareTo(operatorInStack) > 0) {
                            stack.push(operatorToAdd);
                        } else {
                            Operator returnedOp = stack.pop();
                            postfix.enqueue(returnedOp.getElement());
                            stack.push(operatorToAdd);
                        }
                    } else {
                        stack.push(operatorToAdd);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix.enqueue(stack.pop().getElement());
        }

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
        if (!parenthesisRules(inf)) {
            return false;
        }
        if(!operatorRules(inf)){
            return false;
        }
        countNumOfParenthesis(inf);
        if (totalParCount % 2 == 1) {
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

    private void countNumOfParenthesis(Queue inf) {
        for (int i = 0; i < inf.size(); i++) {
            if (isElementLeftParenthesis(inf.get(i))) {
                countLeftParenthesis++;
            }
            if (isElementRightParenthesis(inf.get(i))) {
                countRightParenthesis++;
            }
        }
        totalParCount = countLeftParenthesis + countRightParenthesis;
    }

    private boolean parenthesisRules(Queue inf) {
        // the right of a "(" must be either another "(" or a number.
        for (int i = 0; i < inf.size(); i++) {
            String element = inf.get(i);
            String nextElement = null;
            String beforeElement = null;
            try {
                nextElement = inf.get(i + 1);
            } catch (Exception e) {
                // means that there is no next element.
            }
            try {
                beforeElement = inf.get(i - 1);
            } catch (Exception e) {
                // means that there is no before element.
            }
            if (isElementLeftParenthesis(element)) {// check if the current is a left parenthesis
                if (nextElement != null) {
                    if (!isElementLeftParenthesis(nextElement)) {
                        if (!isElementNumeric(nextElement))// if it is not a left parenthesis.
                        {
                            return false;
                        }
                    }
                }
                if (beforeElement != null) {
                    if (!isElementOperator(beforeElement)) {
                        if (!isElementLeftParenthesis(beforeElement))// check if the element before is a symbol.
                        {
                            return false;
                        }
                    }
                }
            }
            if (isElementRightParenthesis(element)) {
                if (nextElement != null) {
                    if (!isElementRightParenthesis(nextElement)) {
                        if (!isElementOperator(nextElement)) {
                            return false;
                        }
                    }
                }
                if (beforeElement != null) {
                    if (!isElementNumeric(beforeElement)) {
                        if (!isElementRightParenthesis(beforeElement)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean operatorRules(Queue inf) {
        String currentElement = null;
        String nextElement = null;
        String beforeElement = null;
        for (int i = 0; i < inf.size(); i++) {
            currentElement = inf.get(i);
            try {
                nextElement = inf.get(i + 1);
            } catch (Exception e) {
                // means that there is no next element.
            }
            try {
                beforeElement = inf.get(i - 1);
            } catch (Exception e) {
                // means that there is no before element.
            }
            if (isElementOperator(currentElement)) {
                if (nextElement != null) {
                    if (!isElementNumeric(nextElement)) {
                        if (!isElementLeftParenthesis(nextElement)) {
                            return false;
                        }
                    }
                }
                if (beforeElement != null) {
                    if (!isElementNumeric(beforeElement)) {
                        if (!isElementRightParenthesis(beforeElement)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
