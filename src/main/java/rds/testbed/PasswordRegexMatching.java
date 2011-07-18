package rds.testbed;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 5:50 PM
 */
public class PasswordRegexMatching {
    public static void main(String[] args) {
        String password = "a4a4b4cdef";
        System.out.println(password.matches("^(?=.{8,})(.*[-+_!@#$%^&*.,?0-9].*){2,}"));
//        char[] passwordCharacters = password.toCharArray();
//        Arrays.sort(passwordCharacters);
//        String sortedPassword = new String(passwordCharacters);
//        Pattern pattern = Pattern.compile("^(?=.{8,})(?=.*[-+_!@#$%^&*.,?0-9]{2,}).*$");
//        System.out.println(pattern.matcher(sortedPassword).matches());

//        List<Character> passwordCharacters = Arrays.asList('a', 'b', 'c', '5', '$');
//        int matches = CollectionUtils.countMatches(passwordCharacters, new NumberOrSpecialCharacterPredicate());
//        System.out.println(matches);
   }
   static class NumberOrSpecialCharacterPredicate implements Predicate {
        private static final String symbols = "0123456789-+_!@#$%^&*.,?";
        public boolean evaluate(Object object) {
            return symbols.indexOf((Character) object) >= 0;
        }
    }
}

