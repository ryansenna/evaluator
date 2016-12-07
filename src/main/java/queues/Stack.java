/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import beans.Operator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Sena
 */
public class Stack {
    private List<Operator> stack;
    
    public Stack(){
        super();
        stack = new ArrayList<>();
    }
    
   /**
     * Returns the element on the top of the stack, but does not remove it.
     * @return 
     */
    public Operator peek(){
        return stack.get(stack.size()-1);
    }
    
   /**
     * Returns the element on the top of the stack,
     * removing it in the process.
     * @return 
     */
    public Operator pop(){
        Operator item = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return item;
    }
    
   /**
     * Pushes the element onto the stack. Element is also returned.
     * 
     * @return 
     */ 
    public Operator push(Operator element){
        Operator elementToReturn = null;
        if(!stack.isEmpty())
        {
            Operator elementInStack = stack.get(0);
            if(element.getPrecedenceValue() > elementInStack.getPrecedenceValue()){
                stack.add(element);
                elementToReturn = null;
            }
            else{
                stack.remove(elementInStack);
                stack.add(element);  
                elementToReturn = elementInStack;
            }
        }else{
            stack.add(element);
        }
        return elementToReturn;
    }
    /**
     * This method gets the size of the queue.
     * 
     * @return
     */
    public int size(){
        return stack.size();
    }
    /**
     * This method gets whether the queue has no items.
     * 
     * @return
     */
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    /**
     * Searches for an element in the stack.
     * 
     * @param element
     * @return 
     */
    public int search(String element){
        return stack.indexOf(element);
    }
}
