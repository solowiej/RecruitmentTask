import model.Envelope;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XmlParserTest {
    private XmlParser xmlParser;

    @BeforeEach
    public void setUp() throws JAXBException {
        xmlParser = new XmlParser();
    }

    @Test
    public void shouldParseEnvelopeXMLToObject() {
        //given
        File file = new File("eurofxref-daily.xml");

        //when
        Envelope envelope = xmlParser.parseEnvelopeXMLToObject(file);

        //then
        assertNotNull(envelope);
        assertNotNull(envelope.getSubject());

        assertNotNull(envelope.getSender());
        assertNotNull(envelope.getSender().getName());

        assertNotNull(envelope.getCube());
        assertNotNull(envelope.getCube().getCubes());

        assertNotNull(envelope.getCube().getCubes().get(0).getTime());
        assertNotNull(envelope.getCube().getCubes().get(0).getCubes());
        assertNotNull(envelope.getCube().getCubes().get(0).getCubes().get(0));
        assertNotNull(envelope.getCube().getCubes().get(0).getCubes().get(0).getCurrency());
        assertNotNull(envelope.getCube().getCubes().get(0).getCubes().get(0).getRate());
    }
}