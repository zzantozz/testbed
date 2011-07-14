package com.foo;

// Import our collections (Maps and Lists)
import java.util.*;

public class Elements {
    // Instead of having 14 separate variables, you can store your
    // "element effects" in a Map, using the element name as a key.
    private static Map<String, Double> elementMap = new HashMap<String, Double>();

    static {
        elementMap.put("fire", 1.0);
        elementMap.put("water", 2.0);
        // Put your other elements in the map here.
    }

    public static void main(String[] args) {
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
                // They didn't ask to quit, so store the element they entered
                elements.add(element.toLowerCase());
            }
        }
        double yourHp = 1234.0;
        System.out.println("Starting HP: " + yourHp);
        // Now there are between 1 and 5 elements stored in the list
        for (String element : elements) {
            yourHp += elementMap.get(element);
        }
        System.out.println("Ending HP: " + yourHp);
    }
}

//example:    yourHP = yearBorn * logHPCalc; <- I want to make the element variable affect this, as this is the main calculation for statistical data.
// Example 2: yourHP = yearBorn * logHPCalc + [selection of 1 to 5 elements here each with separate "+ elemVariable's"]