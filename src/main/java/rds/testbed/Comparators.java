package rds.testbed;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/16/11
 * Time: 9:23 PM
 */
public class Comparators {
    class Sample {
        private String a;
        private String b;
        private String c;
    }

    class ASampleComparator implements Comparator<Sample> {
        public int compare(Sample o1, Sample o2) {
            return o1.a.compareTo(o2.a);
        }
    }

    class BSampleComparator implements Comparator<Sample> {
        public int compare(Sample o1, Sample o2) {
            return o1.b.compareTo(o2.b);
        }
    }

    class CSampleComparator implements Comparator<Sample> {
        public int compare(Sample o1, Sample o2) {
            return o1.c.compareTo(o2.c);
        }
    }

    public Comparator<Sample> pickComparator(int flag) {
        switch (flag) {
            case 0:
                return new ASampleComparator();
            case 1:
                return new BSampleComparator();
            case 2:
                return new CSampleComparator();
            default:
                throw new IllegalArgumentException("Bad flag value: " + flag);
        }
    }

}
