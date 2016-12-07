

import calculator.Calculator;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import queues.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1333612
 */
public class TestCalculator {
    
    @Test
    public void testToPostfix(){
        Queue infix = new Queue();
        Boolean b = false;
        infix.enqueue("2");
        infix.enqueue("+");
        infix.enqueue("3");
        infix.enqueue("*");
        infix.enqueue("4");
        String postToCompare = "234*+";
        Calculator c = new Calculator(infix);
        c.toPostfix();
        Queue postfix = c.getPostfixExpression();
        if(postfix.toString().equalsIgnoreCase(postToCompare))
            b = true;
                    
        assertTrue(b);
    }
}
