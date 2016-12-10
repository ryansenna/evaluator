/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Sena
 */
public class Stack<T> {
    private List<T> stack;
    
    public Stack(){
        super();
        stack = new ArrayList<T>();
    }
    
   /**
     * Returns the element on the top of the stack, but does not remove it.
     * @return 
     */
    public T peek(){
        return stack.get(stack.size()-1);
    }
    
   /**
     * Returns the element on the top of the stack,
     * removing it in the process.
     * @return 
     */
    public T pop(){
        //this.sort();
        T item = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return item;
    }
    
   /**
     * Pushes the element onto the stack. Element is also returned.
     * 
     * @return 
     */ 
    public T push(T element){
        stack.add(element);
        return element;
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

//    public void sort() {
//        Collections.sort(stack, new OperatorCompare());
//    }
}
