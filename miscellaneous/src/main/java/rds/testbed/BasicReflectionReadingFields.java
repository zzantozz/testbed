package rds.testbed;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 3/4/13
 * Time: 11:25 AM
 */
public class BasicReflectionReadingFields {
    public static void main(String[] args) {
        Book b = new Book("Mostly Harmless", "Douglas Adams");
        FieldPanel fp = new FieldPanel();
        String str = "author";
        fp.namelabel.settext(str);
        fp.textField.settext(getField(b, str));

    }

    private static String getField(Book b, String str) {
        try {
            Field field = Book.class.getDeclaredField(str);
            field.setAccessible(true);
            return (String) field.get(b);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("Bad field name: " + str, e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to access field " + str + " after making it accessible", e);
        }
    }

    static class Book {
        private String title;
        private String author;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
    }

    static class TextField {
        void settext(String s) {
            System.out.println("Setting text field to '" + s + "'");
        }
    }

    static class NameLabel {
        void settext(String s) {
            System.out.println("Setting name label to '" + s + "'");
        }
    }

    static class FieldPanel {
        private NameLabel namelabel;
        private TextField textField;
    }
}
