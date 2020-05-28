package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Calculator {
    private int amount;
    private String currencyValue;

    public Calculator(int amount, String currencyValue) {
        this.amount = amount;
        this.currencyValue = currencyValue;
    }

    public double calculate() {
        return amount * Double.valueOf(currencyValue);
    }
}
