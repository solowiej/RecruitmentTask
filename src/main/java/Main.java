import model.Calculator;
import model.Cube;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static final File XMLFILE = new File("eurofxref-daily.xml");
    private static Map<String, String> availableCurrency;

    public static void main(String[] args) {
        System.out.println("Welcome in Euro currency calculator!");

        Calculator calculator;
        XmlParser xmlParser;
        try {
            xmlParser = new XmlParser();
            calculator = new Calculator();

            String currencyCode = getCurrencyCode(xmlParser);
            String currencyValue = availableCurrency.get(currencyCode);

            calculator.setCurrencyValue(currencyValue);
            calculator.setAmount(ScanerContentLoader.INSTANCE.loadAmountFromUser());

            System.out.println("Result: " + calculator.calculate() + " " + currencyCode);

        } catch (JAXBException e) {
            throw new IllegalArgumentException();
        }
    }

    private static String getCurrencyCode(XmlParser xmlParser) {
        availableCurrency = currencyMap(xmlParser);
        return ScanerContentLoader.INSTANCE.loadCurrencyCode(availableCurrency);
    }


    private static Map<String, String> currencyMap(XmlParser xmlParser) {
        return cubeList(xmlParser)
                .stream()
                .collect(Collectors.toMap(
                        Cube::getCurrency,
                        Cube::getRate
                ));
    }

    private static List<Cube> cubeList(XmlParser xmlParser) {
        return xmlParser.parseEnvelopeToObject(XMLFILE).getCube().getCubes()
                .stream()
                .flatMap(cube -> cube.getCubes().stream())
                .collect(Collectors.toList());
    }
}