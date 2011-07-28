package rds.asm;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 4:34 PM
 */
public class TestPojo {
    private int x;
    private int y;

    public TestPojo(int x, int y) {
        System.out.println("Instantiating");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "TestPojo{" +
                       "x=" + x +
                       ", y=" + y +
                       '}';
    }
}
