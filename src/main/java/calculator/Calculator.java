/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.List;
import queues.Queue;
import queues.Stack;

/**
 *
 * @author ryan Sena
 */
public class Calculator {
   private Queue infix;
   private Stack post;
   List<String> stackOfSymbols;
   List<String> stackOfNumbers;
   public Calculator(){
       super();
       infix = new Queue();
       post = new Stack();
       
   }
   
   public Calculator(Queue inf){
       this.infix = inf;
       post = new Stack();
   }
   
   public Stack getPostfixExpression(){
       return post;
   }
   /**
    * This method will make the conversion between the received
    * infix Q to postfix
    */
   private void toPostfix(){
       //String 
   }
}
