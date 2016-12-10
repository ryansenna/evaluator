/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import beans.LeftParenthesis;
import beans.Operable;
import beans.Operand;
import beans.Operator;
import beans.RightParenthesis;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Sena
 */
public class Queue<T> {

    private List<T> queue;

    public Queue() {
        super();
        queue = new ArrayList<>();
    }

    /**
     * This method will add the number to the tail of the queue. it returns true
     * if it adds it.
     *
     * @param number
     * @return
     */
    public boolean enqueue(T number) {
        return queue.add(number);
    }

    /**
     * This method returns the item at the head of the queue and removes it from
     * it.
     *
     * @return
     */
    public T dequeue() {
        T item = queue.get(0);
        queue.remove(0);
        return item;
    }

    /**
     * This method returns the item at the head, without removing it.
     *
     * @return
     */
    public T peek() {
        return queue.get(0);
    }

    /**
     * This method returns the item at the tail, without removing it.
     *
     * @return
     */
    public T lastIn() {
        return queue.get(queue.size() - 1);
    }

    public T get(int element) {
        return queue.get(element);
    }

    /**
     * This method gets the size of the queue.
     *
     * @return
     */
    public int size() {
        return queue.size();
    }

    /**
     * This method gets whether the queue has no items.
     *
     * @return
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private Operator getOperator(String element) {
        String addsub = "+-";
        String multidiv = "*/";
        Operator o = null;
        if (multidiv.contains(element)) {
            o = new Operator(2, element);
        } else if (addsub.contains(element)) {
            o = new Operator(1, element);
        }
        return o;

    }

    private void validateNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (!isAllowedSymbol(c)) {
                if (!Character.isDigit(c)) {
                    throw new IllegalArgumentException("Exception 100: The number especified is not a number.");
                }
            }
        }
    }

    private boolean isAllowedSymbol(char c) {
        String allowedSymbols = "+-*/()";
        int count = 0;
        for (int i = 0; i < allowedSymbols.length(); i++) {
            if (c == allowedSymbols.charAt(i)) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        }
        return true;
    }

    public String toString() {
        String toReturn = "";
        for (int i = 0; i < queue.size(); i++) {
            toReturn += queue.get(i);
        }
        return toReturn;
    }

    public Queue<Operable> toQueue(String something) {
        Queue<Operand> ofOperands = new Queue();
        Queue<Operable> finalAns = new Queue();
        Operator operator = new Operator();
        Operand operand = new Operand();
        LeftParenthesis l = new LeftParenthesis();
        RightParenthesis r = new RightParenthesis();
        String newElement = "";

        for (int i = 0; i < something.length(); i++) {
            String element = something.charAt(i) + "";
            if (isElementNumeric(element) || element.equals(".")) {
                newElement += element;
            } else {
                if (!newElement.isEmpty()) {
                    operand = new Operand(newElement);
                    finalAns.enqueue(operand);
                    newElement = "";
                    operand = null;
                }
                operator = getOperator(element);
                if (operator != null) {
                    finalAns.enqueue(operator);
                } else if (isElementLeftParenthesis(element)) {
                    l.setElement(element);
                    finalAns.enqueue(l);
                } else {
                    r.setElement(element);
                    finalAns.enqueue(r);
                }
            }
        }
        // pick the last one
        if (!newElement.isEmpty()) {
            operand = new Operand(newElement);
            finalAns.enqueue(operand);
        }
        return finalAns;
    }

    public boolean isElementNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    private boolean isElementOperator(String element) {
        String symbols = "+-*/";
        return symbols.contains(element);
    }

    private boolean isElementLeftParenthesis(String element) {
        return element.contains("(");
    }

    private boolean isElementRightParenthesis(String element) {
        return element.contains(")");
    }
}
