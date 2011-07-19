package rds.testbed;

// Import our collections (Maps and Lists)
import java.util.*;

public class Elements {
    // Instead of having 14 separate variables, you can store your
    // "element effects" in a Map, using the element name as a key.
    private static Map<String, Integer> elementMap = new HashMap<String, Integer>();

    static {
        elementMap.put("fire", 10 * 2 * 2 / 32);
        elementMap.put("ice", 10 * 2 * 3 / 32);
        elementMap.put("water", 10 * 2 * 4 / 32);
        elementMap.put("wind", 10 * 2 * 5 / 32);
        elementMap.put("earth", 10 * 2 * 6 / 32);
        elementMap.put("poison", 10 * 2 * 7 / 32);
        elementMap.put("gravity", 10 * 2 * 8 / 32);
        elementMap.put("shadow", 10 * 2 * 9 / 32);
        elementMap.put("light", 10 * 2 * 10 / 32);
        elementMap.put("elec", 10 * 2 * 11 / 32);
        elementMap.put("holy", 10 * 2 * 12 / 32);
        elementMap.put("anti", 10 * 2 * 13 / 32);
        elementMap.put("void", 10 * 2 * 14 / 32);
        elementMap.put("time", 10 * 2 * 15 / 32);
    }

    public static void main(String[] args) {
        List<Integer> elementValues = getElementValues();
        double yourHp = 1234.0;
        System.out.println("Starting HP: " + yourHp);
        for (Integer elementValue : elementValues) {
            yourHp += elementValue;
        }
        System.out.println("Ending HP: " + yourHp);
    }

    private static List<Integer> getElementValues() {
        // Your input scanner:
        Scanner sin = new Scanner(System.in);
        // This list will keep track of all the elements entered by the user
        List<String> elements = new ArrayList<String>();
        // Ask for up to 5 elements
        while (elements.size() < 5) {
            System.out.println("Select up to 5 elements ('q' to finish selecting) [" + elements.size() + " selected so far]: ");
            // Get what the user entered (they have to hit "Enter" before it gets read)
            String element = sin.next();
            if (element.trim().equals("q")) {
                if (elements.size() == 0) {
                    // If the user asked to quit but hasn't selected any elements,
                    // prompt them again
                    System.out.println("You must select at least one element:");
                } else {
                    // If they want to quit and have selected at least one, then
                    // break out of the while loop and keep going.
                    break;
                }
            } else {
                // They didn't ask to quit, so maybe they entered an element
                if (elementMap.get(element) == null) {
                    // If elementMap.get() returns null, then it's not a valid element
                    System.out.println("Not a valid element, try again:");
                } else {
                    // They entered a good element, so store it
                    elements.add(element.toLowerCase());
                }
            }
        }
        // Now there are between 1 and 5 elements stored in the elements list,
        // and we just have to get their values that are stored in the map.
        List<Integer> elementValues = new ArrayList<Integer>();
        for (String element : elements) {
            elementValues.add(elementMap.get(element));
        }
        return elementValues;
    }
}
