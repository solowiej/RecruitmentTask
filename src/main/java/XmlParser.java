import model.Envelope;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParser {
    private final JAXBContext JAXBCONTENT;
    private final Unmarshaller UNMARSHALLER;

    public XmlParser() throws JAXBException {
        JAXBCONTENT = JAXBContext.newInstance(XmlParser.class);
        UNMARSHALLER = JAXBCONTENT.createUnmarshaller();
    }


    public Envelope parseEnvelopeToObject(File xmlFile) {
        Envelope envelope = null;
        try {
            JAXBContext JAXBCONTENT = JAXBContext.newInstance(Envelope.class);

            Unmarshaller UNMARSHALLER = JAXBCONTENT.createUnmarshaller();
            envelope = (Envelope) UNMARSHALLER.unmarshal(xmlFile);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return envelope;
    }
}
