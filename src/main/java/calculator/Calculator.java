/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import beans.Operable;
import beans.Operand;
import beans.Operator;
import java.util.ArrayList;
import java.util.List;
import queues.Queue;
import queues.Stack;

/**
 *
 * @author ryan Sena
 */
public class Calculator {

    private Queue<Operable> infix;
    private Queue<Operable> postfix;
    private Stack<Operator> stack;
    int countLeftParenthesis = 0;
    int countRightParenthesis = 0;
    int totalParCount = 0;

    public Calculator(Queue<Operable> inf) {
        setInfix(inf);
        postfix = new Queue();
        stack = new Stack<Operator>();
    }

    public Queue getPostfixExpression() {
        return postfix;
    }

    /**
     * This method will make the conversion between the received infix Q to
     * postfix
     */
    private void toPostfix() {
        int precedenceValue = 50 + countLeftParenthesis;
        int countExpressions = 0;
        while (!infix.isEmpty()) {// loop until the Q is empty.
            Operable element = infix.dequeue();// dequeue the first element.
            if (element.isOperand()) {// check if it is numeric
                postfix.enqueue(element);// if it is numeric, automatically enqueue to postfix.
            } else {
                if (element.isLeftParenthesis()) {
                    countExpressions++;
                }
                if (element.isRightParenthesis()) {
                    countExpressions--;
                }

                if (element.isOperator()) {// check if it is an operator.
                    Operator operatorToAdd = (Operator) element;// get what operator is.
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
                            postfix.enqueue(returnedOp);
                            stack.push(operatorToAdd);
                        }
                    } else {
                        stack.push(operatorToAdd);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix.enqueue(stack.pop());
        }

    }

    public double getAnswer() {
        this.toPostfix();
        if (postfix.isEmpty()) {
            throw new IllegalArgumentException("You can't get an answer if you dont toPostfix");
        }

        Stack<Operand> operandStack = new Stack<Operand>();
        List<String> expressionArray = new ArrayList<>();
        while (!postfix.isEmpty()) {
            Operable element = postfix.dequeue();
            if (element.isOperand()) {
                operandStack.push((Operand) element);
            } else {
                Operand num1 = operandStack.pop();
                Operand num2 = operandStack.pop();
                Operand result = doMath(num1, num2, (Operator) element);
                operandStack.push(result);
            }
        }
        return operandStack.pop().toDouble();
    }

    private Operand doMath(Operand o1, Operand o2, Operator operator) {
        double result = 0.0;
        Operand o3 = new Operand();
        double num1 = o1.toDouble();
        double num2 = o2.toDouble();

        if (operator.getElement().equals("+")) {
            result = num1 + num2;
            o3.setElement(result + "");
        }
        if (operator.getElement().equals("-")) {
            if (num2 > num1) {
                result = num2 - num1;
            } else {
                result = num1 - num2;
            }
            o3.setElement(result + "");
        }
        if (operator.getElement().equals("*")) {
            result = num1 * num2;
            o3.setElement(result + "");
        }
        if (operator.getElement().equals("/")) {
            if (num2 > num1) {
                if (num1 == 0) {
                    throw new IllegalArgumentException("NAN");
                }
                result = num2 / num1;
            } else {
                if (num2 == 0) {
                    throw new IllegalArgumentException("NAN");
                }
                result = num1 / num2;
            }
            o3.setElement(result + "");
        }
        return o3;
    }

    private void setInfix(Queue<Operable> inf) {
        if (!validateInfix(inf)) {
            throw new IllegalArgumentException("Error 200: Queue is not valid");
        }
        this.infix = inf;
    }

    private boolean isOperandOnBothEnds(Queue<Operable> inf) {
        Operable operand1 = inf.peek();
        Operable operand2 = inf.lastIn();

        if (!operand1.isOperand()) {
            if (!operand1.isLeftParenthesis()) {
                return false;
            }
        }
        if (!operand2.isOperand()) {
            if (!operand2.isRightParenthesis()) {
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
    private boolean isQNumericOrSymbols(Queue<Operable> inf) {
        Operable element = null;
        for (int i = 0; i < inf.size(); i++) {
            element = inf.get(i);
            if (!element.isSymbol()) {
                if (!element.isOperand()) {
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
        if (!operatorRules(inf)) {
            return false;
        }
        countNumOfParenthesis(inf);
        if (totalParCount % 2 == 1) {
            return false;
        }
        return isOperandOnBothEnds(inf);
    }

    public boolean isElementNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private void countNumOfParenthesis(Queue<Operable> inf) {
        for (int i = 0; i < inf.size(); i++) {
            Operable element = inf.get(i);
            if (element.isLeftParenthesis()) {
                countLeftParenthesis++;
            }
            if (element.isRightParenthesis()) {
                countRightParenthesis++;
            }
        }
        totalParCount = countLeftParenthesis + countRightParenthesis;
    }

    private boolean parenthesisRules(Queue<Operable> inf) {
        // the right of a "(" must be either another "(" or a number.
        for (int i = 0; i < inf.size(); i++) {
            Operable currentElement = inf.get(i);
            Operable nextElement = null;
            Operable beforeElement = null;
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
            if (currentElement.isLeftParenthesis()) {// check if the current is a left parenthesis
                if (nextElement != null) {
                    if (!nextElement.isLeftParenthesis()) {
                        if (!nextElement.isOperand())// if it is not a left parenthesis.
                        {
                            return false;
                        }
                    }
                }
                if (beforeElement != null) {
                    if (!beforeElement.isOperator()) {
                        if (!beforeElement.isLeftParenthesis())// check if the element before is a symbol.
                        {
                            return false;
                        }
                    }
                }
            }
            if (currentElement.isRightParenthesis()) {
                if (nextElement != null) {
                    if (!nextElement.isRightParenthesis()) {
                        if (!nextElement.isOperator()) {
                            return false;
                        }
                    }
                }
                if (beforeElement != null) {
                    if (!beforeElement.isOperand()) {
                        if (!beforeElement.isRightParenthesis()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean operatorRules(Queue<Operable> inf) {
        Operable currentElement = null;
        Operable nextElement = null;
        Operable beforeElement = null;
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
            if (currentElement.isOperator()) {
                if (nextElement != null) {
                    if (!nextElement.isOperand()) {
                        if (!nextElement.isLeftParenthesis()) {
                            return false;
                        }
                    }
                }
                if (beforeElement != null) {
                    if (!beforeElement.isOperand()) {
                        if (!beforeElement.isRightParenthesis()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
