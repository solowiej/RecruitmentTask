package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Calculator {
    private int amount;
    private String currencyValue;

    public double calculate() {
        return amount * Double.valueOf(currencyValue);
    }
}
