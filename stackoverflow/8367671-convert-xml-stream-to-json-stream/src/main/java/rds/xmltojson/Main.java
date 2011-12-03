package rds.xmltojson;

    import org.codehaus.jettison.mapped.MappedXMLOutputFactory;

    import javax.xml.stream.XMLEventReader;
    import javax.xml.stream.XMLEventWriter;
    import javax.xml.stream.XMLInputFactory;
    import java.io.StringReader;
    import java.util.HashMap;

    public class Main {
        public static void main(String[] args) throws Exception {
            String xml = "<root><foo>foo string</foo><bar><x>1</x><y>5</y></bar></root>";
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(new StringReader(xml));
            XMLEventWriter writer = new MappedXMLOutputFactory(new HashMap()).createXMLEventWriter(System.out);
            writer.add(reader);
            writer.close();
            reader.close();
        }
    }