package rds.multipletransaction.one;

import rds.multipletransaction.two.SecondThing;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 2/18/12
 * Time: 6:16 PM
 */
public interface FirstThing {
    void go();
    void seeWhatsThere();
    void setSecondThing(SecondThing secondThing);
}
