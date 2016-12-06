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
public class Postfix {
    private List<String> queue;
    
    public Postfix(){
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
        //validateNumber(number);
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
}
