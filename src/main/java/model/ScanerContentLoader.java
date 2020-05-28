package model;

import java.util.Map;
import java.util.Scanner;

public class ScanerContentLoader {
    public static final ScanerContentLoader INSTANCE = new ScanerContentLoader();

    private ScanerContentLoader() {
    }

    public Scanner scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public String loadCurrencyCode(Map<String, String> currencyMap) {
        String currencyCode;
        do {
            System.out.print("Type currency code: ");
            currencyMap.forEach((key, value) -> System.out.print(key + " "));
            System.out.println();
            currencyCode = INSTANCE.scanner().nextLine().toUpperCase();

            if (!currencyMap.containsKey(currencyCode)) {
                currencyCode = null;
                System.err.println("Incorrect currency exchange rate, please enter it again.");
            }

        } while (currencyCode == null);
        return currencyCode;
    }

    public int loadAmountFromUser() {
        Integer amount = null;
        do {
            try {
                System.out.println("Type the amount of euros: ");
                String currencyAmount = INSTANCE.scanner().nextLine();
                amount = Integer.valueOf(currencyAmount);

            } catch (IllegalArgumentException iae) {
                System.err.println("Invalid data format, please enter type integer.");
            }
        } while (amount == null);
        return amount;
    }
}
