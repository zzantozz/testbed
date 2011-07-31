package rds.log4j;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 5:01 PM
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger("anything");

    public static void main(String[] args) {
        log.info("This will be logged at info");
        log.debug("This won't be logged because it's at debug");
        DOMConfigurator.configure(Main.class.getResource("/log4j-reconfigure.xml"));
        log.info("This will be logged at info");
        log.debug("This will be logged because the reconfiguration enables debug");
    }
}
