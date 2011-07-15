package rds.testbed;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/13/11
 * Time: 5:00 PM
 */
public class IndexOf {
    private char[] someText;

    public IndexOf(String someText) {
        this.someText = someText.toCharArray();
    }

    public int indexOf(String fragment) {

        char[] temp = fragment.toCharArray();

        for (int i = 0; i < someText.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if ((someText[i + j] == temp[j]) && (j == temp.length - 1)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public int indexOf2(String fragment) {
        char[] searchFor = fragment.toCharArray();
        for (int i = 0; i < someText.length; i++) {
            for (int j = 0; j < searchFor.length; j++) {
                if ((someText[i + j] == searchFor[j]) && (j == searchFor.length - 1)) {
                    return i;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(new IndexOf("paplay").indexOf2("play"));
    }
}
