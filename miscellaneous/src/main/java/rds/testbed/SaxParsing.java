package rds.testbed;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/16/11
 * Time: 10:13 PM
 */
public class SaxParsing {
    public static void main(String[] args) throws Exception {
        String xmlNoNs =
                "<rootElement>" +
                    "<foo bar=\"1\"/>" +
                "</rootElement>";
        String xmlNs =
                "<x:rootElement xmlns=\"http://foo.com\">" +
                    "<foo bar=\"1\"/>" +
                "</x:rootElement>";
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        System.out.println("Parsing without ns");
        saxParser.parse(new ByteArrayInputStream(xmlNoNs.getBytes()), new Handler());
        System.out.println("Parsing with ns");
        saxParser.parse(new ByteArrayInputStream(xmlNs.getBytes()), new Handler());
    }

    static class Handler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println("Got qName {" + qName + "} and localName {" + localName + "}");
        }
    }
}
