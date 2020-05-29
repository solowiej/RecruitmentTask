package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    void calculate() {
        //given
        final int amount = 5;
        final double currencyValue = 1.0991;
        calculator.setAmount(amount);
        calculator.setCurrencyValue(currencyValue);

        //when
        final double actualResult = calculator.calculate();

        //then
        assertEquals(5.4955, actualResult, 0.001);
    }
}