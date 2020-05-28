package model;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class Sender {
    @XmlElement
    private String name;
}
