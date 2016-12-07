
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
    public void init() {
        queue = new Queue();
    }

    public TestCalculator(String inputQ, String expectedResult) {
        this.inputQ = inputQ;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection goodInfixes() {
        return Arrays.asList(new Object[][]{
            {"2+3*4", "234*+"},// test different precedence operators.
            {"2+3-4", "23+4-"},// test same precedence operators.
            {"(2+3)*4", "23+4*"},// test parenthesis on a lower precedence operator on the beginning.
            {"2*(3+4)", "234+*"},// test parenthesis on a lower precedence operator on the end.
            {"(2+3)*(5-2)", "23+52-*"},// test lower precedence operators in parenthesis separated by a higher precedence operator.
            {"(((((3+5)+8)*7)-8)+9)", "35+8+7*8-9+"},// test nested parenthesis.
            // test bad infixes
            {"(2+3(2+3))", "null"},
            {"+3", "null"},
            {"(2+3)(2+3)", "null"},
            {"(2+3)(*)", "null"},
            {"2++3", "null"}
        });
    }

    @Test
    public void testToPostfix() {
        log.debug("Parameterized Queue is : " + inputQ);
        Queue infix = queue.toQueue(inputQ);
        Calculator c = null;
        String postfixString = "null";
        try {
            c = new Calculator(infix);
            c.toPostfix();
            postfixString = c.getPostfixExpression().toString();
        } catch (IllegalArgumentException iae) {
            // means that the infix was invalid.
        }
        assertEquals(expectedResult, postfixString);
    }
}
