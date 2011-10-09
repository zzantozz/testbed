package rds.asm;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 4:34 PM
 */
public class TestPojo {
    private int x;
    private final int y;

    public TestPojo(int x) {
        this.x = x;
        this.y = 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "TestPojo{" +
                       "x=" + x +
                       ", y=" + y +
                       '}';
    }
}
