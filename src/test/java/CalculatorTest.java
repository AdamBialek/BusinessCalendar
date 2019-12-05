import com.businesscalendar.Calculator;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CalculatorTest {

    @Test
    public void checkResult() {
        Calculator calculator=new Calculator();
        calculator.setCalculatorNumber1("3");
        calculator.setOperation("+");
        calculator.setNumber1(BigDecimal.ONE);

        Assertions.assertEquals("4",calculator.result(""));
    }

    @Test
    public void checkNegate() {
        Calculator calculator=new Calculator();
        calculator.setCalculatorNumber1("5");
        Assertions.assertEquals("-5",calculator.negate());
    }

    @Test
    public void checkEraseCharacter() {
        Calculator calculator=new Calculator();
        calculator.setCalculatorNumber1("425");
        Assertions.assertEquals("42",calculator.erase());
    }

    @Test
    public void checkAddCharacterToNumber() {
        Calculator calculator=new Calculator();
        calculator.setCalculatorNumber1("123");
        Assertions.assertEquals("1234",calculator.assignButtonValue(4));
    }

}
