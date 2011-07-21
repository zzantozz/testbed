package rds.testbed;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/20/11
 * Time: 8:22 PM
 */
public class JaxbAttributes {
    public static void main(String[] args) throws Exception {
        Marshaller marshaller = JAXBContext.newInstance(DataClass.class).createMarshaller();
        StringWriter stringWriter = new StringWriter();
        DataClass dataClass = new DataClass(
                       new Foo("this is what I'm talking about", "This is better"),
                       new Foo("a different attribute here", "So is this"));
        marshaller.marshal(dataClass, stringWriter);
        System.out.println(stringWriter);
    }

    @XmlRootElement(name = "DataClass")
    @XmlType(propOrder = {"myElement", "anotherElement"})
    static class DataClass {
        private Foo myElement;
        private Foo anotherElement;

        DataClass() {}
        public DataClass(Foo myElement, Foo anotherElement) {
            this.myElement = myElement;
            this.anotherElement = anotherElement;
        }

        public Foo getMyElement() { return myElement; }
        public void setMyElement(Foo myElement) { this.myElement = myElement; }
        public Foo getAnotherElement() { return anotherElement; }
        public void setAnotherElement(Foo anotherElement) { this.anotherElement = anotherElement; }
    }

    static class Foo {
        private String thisAtt;
        private String value;

        Foo() {}
        public Foo(String thisAtt, String value) {
            this.thisAtt = thisAtt;
            this.value = value;
        }

        @XmlAttribute
        public String getThisAtt() { return thisAtt; }
        public void setThisAtt(String thisAtt) { this.thisAtt = thisAtt; }
        @XmlValue
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }
}
