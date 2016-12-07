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
public class Queue {
    private List<String> queue;
    
    public Queue(){
        super();
        queue = new ArrayList<>();
    }
    
    /**
     * This method will add the number to the tail of the queue.
     * it returns true if it adds it.
     * @param number
     * @return 
     */
    public boolean enqueue(String number){
        return queue.add(number);
    }
    /**
     * This method returns the item at the head of the 
     * queue and removes it from it.
     * @return 
     */
    public String dequeue(){
        String item = queue.get(0);
        queue.remove(0);
        return item;
    }
    
    /**
     * This method returns the item at the head, without removing it.
     * 
     * @return 
     */ 
    public String peek(){
        return queue.get(0);
    }
   /**
     * This method returns the item at the tail, without removing it.
     * 
     * @return 
     */ 
    public String lastIn(){
        return queue.get(queue.size()-1);
    }
    
    public String get(int element){
        return queue.get(element);
    }
    /**
     * This method gets the size of the queue.
     * 
     * @return
     */
    public int size(){
        return queue.size();
    }
    /**
     * This method gets whether the queue has no items.
     * 
     * @return
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    
    
    private void validateNumber(String number){
        for(int i = 0; i < number.length(); i++){
            char c = number.charAt(i);
            if(!isAllowedSymbol(c)){
                if(!Character.isDigit(c))
                    throw new IllegalArgumentException("Exception 100: The number especified is not a number.");
            }
        }
    }

    private boolean isAllowedSymbol(char c) {
        String allowedSymbols = "+-*/()";
        int count = 0;
        for(int i = 0; i < allowedSymbols.length(); i++){
            if(c == allowedSymbols.charAt(i))
                count++;
        }
        if(count == 0){
            return false;
        }
        return true;
    }
    
    public String toString(){
        String toReturn = "";
        for(int i = 0; i < queue.size(); i++){
            toReturn += queue.get(i);
        }
        return toReturn;
    }
    
    public Queue toQueue(String something){
        Queue q = new Queue();
        
        for(int i =0; i < something.length(); i++){
            q.enqueue(something.charAt(i)+"");
        }
        return q;
    }
    
}
