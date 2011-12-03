package rds.testbed;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionBasedComparator {
    public static void main(String[] args) {
        List<Foo> foos = Arrays.asList(new Foo("a", "z"), new Foo("z", "a"), new Foo("n", "n"));
        Collections.sort(foos, new ReflectiveComparator("s"));
        System.out.println(foos);
        Collections.sort(foos, new ReflectiveComparator("t"));
        System.out.println(foos);
    }

    static class Foo {
        private String s;
        private String t;

        public Foo(String s, String t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public String toString() {
            return "Foo{" +
                           "s='" + s + '\'' +
                           ", t='" + t + '\'' +
                           '}';
        }
    }

    private static class ReflectiveComparator implements Comparator<Object> {
        private String fieldName;

        public ReflectiveComparator(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public int compare(Object o1, Object o2) {
            try {
                Field field = o1.getClass().getDeclaredField(fieldName);
                if (!Comparable.class.isAssignableFrom(field.getType())) {
                    System.out.println(field.getType());
                    throw new IllegalStateException("Field not Comparable: " + field);
                }
                field.setAccessible(true);
                Comparable o1FieldValue = (Comparable) field.get(o1);
                Comparable o2FieldValue = (Comparable) field.get(o2);
                return o1FieldValue.compareTo(o2FieldValue);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException("Field doesn't exist", e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Field inaccessible", e);
            }
        }
    }
}