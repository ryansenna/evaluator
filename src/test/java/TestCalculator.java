
import beans.Operable;
import calculator.Calculator;
import queues.Queue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1333612
 */
@RunWith(Parameterized.class)
public class TestCalculator {

    private String inputQ;
    private Double expectedResult;
    private Queue queue;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Before
    public void init() {
        queue = new Queue();
    }

    public TestCalculator(String inputQ, Double expectedResult) {
        this.inputQ = inputQ;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection goodInfixes() {
        return Arrays.asList(new Object[][]{
            {"2+2",4.0},
            {"3*4",12.0},
            {"2+3*4", 14.0},
            {"2+3-4", 1.0},
            {"(2+3)*4", 20.0},
            {"2*(3+4)", 14.0},
            {"(2+3)*(5-2)", 15.0},
            {"(((((3+5)+8)*7)-8)+9)", 113.0},
            {"5/0",0.0},
//          test bad infixes
            {"(2+3(2+3))", 0.0},
            {"+3", 0.0},
            {"(2+3)(2+3)", 0.0},
            {"(2+3)(*)",0.0},
            {"2++3", 0.0},
//          more testing
            {"5/2*7+8*8-9",73.0},
            {"2+4*3/3", 6.0},
            {"52+(1+2)*4-3", 61.0},
            {"52+((1+2)*4)-3",61.0},
            {"(52+1+2)*4-3", 217.0},
            {"123.60/25.7", 5.0},
            {"123.60/25.7+3.45",8.0},
            {"123.60*(28.7+3.45)", 3974.0},
            {"(516.46-215.68)/(123.60+28.69)",2.0},
            {"(10*7)+(9-5)*3+4",86.0},
            {"(10.536*58.52026)+(9421.48745-361.251)*1457.475+7510",13213195.0}
        });
    }

    @Test
    public void testToPostfix() {
        log.debug("Parameterized Queue is : " + inputQ);
        Queue<Operable> infix = queue.toQueue(inputQ);
        Calculator c = null;
        Double ans = 0.0;
        try {
            c = new Calculator(infix);
            ans = c.getAnswer();
            ans = (double)Math.round(ans);
        } catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
        }
        assertEquals(expectedResult,ans);
    }
}
