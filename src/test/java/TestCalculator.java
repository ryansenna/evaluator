

import calculator.Calculator;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import queues.Queue;

import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.slf4j.LoggerFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1333612
 */
@RunWith(Parameterized.class)
public class TestCalculator {
    private String inputQ;
    private String expectedResult;
    private Queue queue;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
    @Before
    public void init(){
        queue = new Queue();
    }
    public TestCalculator(String inputQ, String expectedResult){
        this.inputQ = inputQ;
        this.expectedResult = expectedResult;
    }
    @Parameterized.Parameters
    public static Collection primeNumbers(){
        return Arrays.asList(new Object[][]{
            {"2+3*4","234*+"}
        });
    }
    @Test
    public void testToPostfix(){
        log.debug("Parameterized Queue is : " + inputQ);
        Queue infix = queue.toQueue(inputQ);
        Calculator c = new Calculator(infix);
        c.toPostfix();
        assertEquals(expectedResult,c.getPostfixExpression().toString());
    }
}
