package roll_the_ball.models;

import java.util.Objects;

public class Cordonnee
{
    private int x;
    private int y;


    public Cordonnee(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cordonnee cordonnee = (Cordonnee) o;
        return x == cordonnee.x &&
                y == cordonnee.y;
    }


    @Override
    public String toString() {
        return "Cordonnee{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
