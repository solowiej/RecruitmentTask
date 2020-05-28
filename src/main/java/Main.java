import model.Calculator;
import model.Cube;
import model.ScanerContentLoader;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private static final File XMLFILE = new File("eurofxref-daily.xml");

    public static void main(String[] args) {
        System.out.println("Welcome in Euro currency calculator!");

        Calculator calculator;
        XmlParser xmlParser;
        try {
            xmlParser = new XmlParser();
            calculator = new Calculator();

            Map<String, String> availableCurrency = currencyMap(xmlParser, XMLFILE);
            String currencyCode = ScanerContentLoader.INSTANCE.loadCurrencyCode(availableCurrency);
            String currencyValue = getCurrencyValue(availableCurrency, currencyCode);

            calculator.setCurrencyValue(currencyValue);
            calculator.setAmount(ScanerContentLoader.INSTANCE.loadAmountFromUser());

            System.out.println("Result: " + calculator.calculate() + " " + currencyCode);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrencyValue(Map<String, String> availableCurrency, String currencyCode) {
        return availableCurrency.get(currencyCode);
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