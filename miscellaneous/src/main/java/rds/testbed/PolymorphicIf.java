package rds.testbed;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/14/11
 * Time: 2:13 PM
 */
public class PolymorphicIf {
    public static Screen getNextScreen() {

        JSONObject jsonObject = RatingsUtils.getCurrentJsonObjectFromServer();

        if (isNextProgramScreen(jsonObject)) {
            ParentRatingsObject parentRatingsObject = JsonBusinessObjectFactory.createParentRatingsObject(jsonObject);
            return new NextProgramScreen(parentRatingsObject);
        } else if (isTimerScreen(jsonObject)) {
            ChildWithParentRatingsObject childWithParentRatingsObject = JsonBusinessObjectFactory.createChildWithParentRatingsObject(jsonObject);
            return new TimerScreen(childWithParentRatingsObject);
        } else if (isNextContestantPreJudgeScreen(jsonObject)) {
            ChildWithParentRatingsObject childWithParentRatingsObject = JsonBusinessObjectFactory.createChildWithParentRatingsObject(jsonObject);
            return new NextContestantPreJudgingScreen(childWithParentRatingsObject);
        } else if (isNextContestantJudgeScreen(jsonObject)) {
            ChildWithParentRatingsObject childWithParentRatingsObject = JsonBusinessObjectFactory.createChildWithParentRatingsObject(jsonObject);
            return new TimerScreen(childWithParentRatingsObject);
        } else {
            return null;
        }
    }

    private static List<ScreenProvider> screenProviders = screenProviders();

    public static Screen getNextScreen(JSONObject jsonObject) {
        for (ScreenProvider screenProvider : screenProviders) {
            if (screenProvider.supports(jsonObject)) {
                return screenProvider.getScreen(jsonObject);
            }
        }
        return null;
    }

    interface ScreenProvider {
        boolean supports(JSONObject jsonObject);
        Screen getScreen(JSONObject jsonObject);
    }

    private static List<ScreenProvider> screenProviders() {
        return Arrays.asList(
                new ScreenProvider() {
                    public boolean supports(JSONObject jsonObject) {
                        return isNextProgramScreen(jsonObject);
                    }

                    public Screen getScreen(JSONObject jsonObject) {
                        ParentRatingsObject parentRatingsObject = JsonBusinessObjectFactory.createParentRatingsObject(jsonObject);
                        return new NextProgramScreen(parentRatingsObject);
                    }
                },
                new ScreenProvider() {
                    public boolean supports(JSONObject jsonObject) {
                        return isTimerScreen(jsonObject);
                    }

                    public Screen getScreen(JSONObject jsonObject) {
                        ChildWithParentRatingsObject childWithParentRatingsObject = JsonBusinessObjectFactory.createChildWithParentRatingsObject(jsonObject);
                        return new TimerScreen(childWithParentRatingsObject);
                    }
                },
                new ScreenProvider() {
                    public boolean supports(JSONObject jsonObject) {
                        return isNextContestantPreJudgeScreen(jsonObject);
                    }

                    public Screen getScreen(JSONObject jsonObject) {
                        ChildWithParentRatingsObject childWithParentRatingsObject = JsonBusinessObjectFactory.createChildWithParentRatingsObject(jsonObject);
                        return new NextContestantPreJudgingScreen(childWithParentRatingsObject);
                    }
                },
                new ScreenProvider() {
                    public boolean supports(JSONObject jsonObject) {
                        return isNextContestantJudgeScreen(jsonObject);
                    }

                    public Screen getScreen(JSONObject jsonObject) {
                        ChildWithParentRatingsObject childWithParentRatingsObject = JsonBusinessObjectFactory.createChildWithParentRatingsObject(jsonObject);
                        return new TimerScreen(childWithParentRatingsObject);
                    }
                }
        );
    }

    private static boolean isTimerScreen(JSONObject jsonObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.PolymorphicIf.isTimerScreen");
    }

    private static boolean isNextContestantPreJudgeScreen(JSONObject jsonObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.PolymorphicIf.isNextContestantPreJudgeScreen");
    }

    private static boolean isNextContestantJudgeScreen(JSONObject jsonObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.PolymorphicIf.isNextContestantJudgeScreen");
    }

    private static boolean isNextProgramScreen(JSONObject jsonObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.PolymorphicIf.isNextProgramScreen");
    }
}

class NextContestantPreJudgingScreen extends Screen {
    public NextContestantPreJudgingScreen(ChildWithParentRatingsObject childWithParentRatingsObject) {}
}

class NextProgramScreen extends Screen {
    public NextProgramScreen(ParentRatingsObject parentRatingsObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.NextProgramScreen.NextProgramScreen");
    }
}

class TimerScreen extends Screen {
    public TimerScreen(ChildWithParentRatingsObject childWithParentRatingsObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.TimerScreen.TimerScreen");
    }
}

class ChildWithParentRatingsObject {
}

class ParentRatingsObject {
}

class Screen {
}

class JSONObject {
}

class RatingsUtils {
    public static JSONObject getCurrentJsonObjectFromServer() { return null;}
}

class JsonBusinessObjectFactory {
    public static ChildWithParentRatingsObject createChildWithParentRatingsObject(JSONObject jsonObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.PolymorphicIf.JsonBusinessObjectFactory.createChildWithParentRatingsObject");
    }

    public static ParentRatingsObject createParentRatingsObject(JSONObject jsonObject) {
        // TODO: Write me
        throw new UnsupportedOperationException("Not written com.rackspace.monitoring.PolymorphicIf.JsonBusinessObjectFactory.createParentRatingsObject");
    }
}
