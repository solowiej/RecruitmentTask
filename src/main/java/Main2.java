import model.Calculator;
import model.Cube;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main2 {
    private static final File xmlFile = new File("eurofxref-daily.xml");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Euro currency calculator! Commands: [s -show currency] [c - calculate] [q - quit]");

        XmlParser xmlParser;
        Calculator calculator;
        try {
            xmlParser = new XmlParser();
            Map<String, String> availableCurrency = currencyMap(xmlParser, xmlFile);

            String currencyType;

            int currencyAmount;

            String line;

            do {
                line = scanner.next();
                switch (line) {
                    case "s":
                        availableCurrency.forEach((key, value) -> System.out.print(key + " "));
                        System.out.println();
                        break;
                    case "c":
                        System.out.println("Type currency:");
                        currencyType = scanner.next().trim().toUpperCase();


                        if (!availableCurrency.containsKey(currencyType) && !currencyType.equalsIgnoreCase("q")) {
                            System.out.println("There is no such currency. Try again.");
                            continue;
                        }

                        String currencyValue = availableCurrency.get(currencyType);
                        System.out.println("Type the amount of euros:");


                        if (!scanner.hasNextInt()) {
                            System.out.print("Please type integer. ");
                            continue;
                        }

                        currencyAmount = scanner.nextInt();

                        calculator = new Calculator(currencyAmount, currencyValue);
                        System.out.println(calculator.calculate() + " " + currencyType);

                        break;
                    case "q":
                        break;
                    default:
                        System.out.println("Bad command. Try again.");
                }
            } while (!line.equalsIgnoreCase("q"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static Map<String, String> currencyMap(XmlParser xmlParser, File file) {
        return cubeList(xmlParser, file)
                .stream()
                .collect(Collectors.toMap(
                        Cube::getCurrency,
                        Cube::getRate
                ));
    }

    private static List<Cube> cubeList(XmlParser xmlParser, File file) {
        return xmlParser.parseEnvelopeToObject(file).getCube().getCubes()
                .stream()
                .flatMap(cube -> cube.getCubes().stream())
                .collect(Collectors.toList());
    }
}
