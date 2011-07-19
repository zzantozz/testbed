package rds.testbed;

import com.thoughtworks.xstream.XStream;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/19/11
 * Time: 5:38 PM
 */
public class XStreamReadingNamespaceAttributes {
    public static void main(String[] args) {
        String xml = "<x:foo xmlns:x=\"http://foo.com\">" +
                             "<bar xmlns=\"http://bar.com\"/>" +
                             "</x:foo>";
        XStream xstream = new XStream();
        xstream.alias("x:foo", Foo.class);
        xstream.useAttributeFor(Foo.class, "xmlns");
        xstream.aliasField("xmlns:x", Foo.class, "xmlns");
        xstream.alias("bar", Bar.class);
        xstream.useAttributeFor(Bar.class, "xmlns");
        xstream.aliasField("xmlns", Foo.class, "xmlns");
        Object o = xstream.fromXML(xml);
        System.out.println("Unmarshalled a " + o.getClass());
        System.out.println("Value: " + o);
    }

    static class Foo {
        private String xmlns;
        private Bar bar;
        public String toString() {
            return "Foo{xmlns='" + xmlns + "', bar=" + bar + '}';
        }
    }

    static class Bar {
        private String xmlns;
        public String toString() {
            return "Bar{xmlns='" + xmlns + "'}";
        }
    }
}
