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

        XmlParser xmlParser;
        Calculator calculator;
        String currencyCode;
        String currencyValue;
        int currencyAmount;
        try {
            xmlParser = new XmlParser();
            calculator = new Calculator();

            currencyCode = getCurrencyCode(xmlParser);
            currencyValue = availableCurrency.get(currencyCode);
            currencyAmount = ScanerContentLoader.INSTANCE.loadAmountFromUser();

            calculator.setCurrencyValue(currencyValue);
            calculator.setAmount(currencyAmount);

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
        return xmlParser.parseEnvelopeXMLToObject(XMLFILE).getCube().getCubes()
                .stream()
                .flatMap(cube -> cube.getCubes().stream())
                .collect(Collectors.toList());
    }
}