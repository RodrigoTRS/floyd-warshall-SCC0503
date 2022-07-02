package brabalog;

public class Position {


    int X;
    int Y;

    public Position(int x, int y) {
        X = x;
        Y = y;
    }

    public String toString() {
        return "(" + X + ", " + Y + ")";
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }
}
