package rds.junit;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 4/20/12
 * Time: 9:58 PM
 */
public class ToggleableIgnores implements MethodRule {
    public static final EmptyStatement EMPTY_STATEMENT = new EmptyStatement();

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        boolean invertIgnores = System.getProperty("junit.invertIgnores") != null;
        boolean isIgnored = method.getAnnotation(MyIgnore.class) != null;
        Statement[] s = { base, EMPTY_STATEMENT};
        return xnor(invertIgnores, isIgnored) ? s[0] : s[1];
    }

    private boolean xnor(boolean x, boolean y) {
        return !(x ^ y);
    }

    private static class EmptyStatement extends Statement {
        @Override
        public void evaluate() {
        }
    }
}
