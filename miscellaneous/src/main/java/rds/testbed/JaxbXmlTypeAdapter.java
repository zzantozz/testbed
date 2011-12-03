package rds.testbed;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 9/17/11
 * Time: 1:51 PM
 */
public class JaxbXmlTypeAdapter {
    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Foo.class);
        StringWriter stringWriter = new StringWriter();
        context.createMarshaller().marshal(new Foo(new Bar(10)), stringWriter);
        System.out.println(stringWriter);
        Object o = context.createUnmarshaller().unmarshal(new StringReader(stringWriter.toString()));
        System.out.println(o);
    }

    @XmlRootElement
    static class Foo {
        private Bar bar;
        Foo() {}
        Foo(Bar bar) { this.bar = bar; }
        @XmlJavaTypeAdapter(BarAdapter.class)
        public Bar getBar() { return bar; }
        public void setBar(Bar bar) { this.bar = bar; }
        public String toString() { return "Foo{bar=" + bar + '}'; }
    }

    static class Bar {
        private int x;
        Bar(int x) { this.x = x; }
        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
        public String toString() { return "Bar{x=" + x + '}'; }
    }

    static class JaxbFriendlyBar {
        private int x;
        JaxbFriendlyBar() {}
        public int getX() { return x; }
        public void setX(int x) { this.x = x; }
    }

    static class BarAdapter extends XmlAdapter<JaxbFriendlyBar, Bar> {
        @Override
        public JaxbFriendlyBar marshal(Bar bar) throws Exception {
            JaxbFriendlyBar result = new JaxbFriendlyBar();
            result.setX(bar.getX());
            return result;
        }

        @Override
        public Bar unmarshal(JaxbFriendlyBar jaxbFriendlyBar) throws Exception {
            return new Bar(jaxbFriendlyBar.getX());
        }
    }
}
