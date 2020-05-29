package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Calculator {
    private int amount;
    private double currencyValue;

    public double calculate() {
        return amount * currencyValue;
    }
}
