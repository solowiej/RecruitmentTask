package model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
@XmlAccessorType(XmlAccessType.NONE)
@Data
public class Cube {
    @XmlAttribute
    private String time;
    @XmlAttribute
    private String currency;
    @XmlAttribute
    private String rate;
    @XmlElement(name = "Cube")
    private List<Cube> cubes;
}
