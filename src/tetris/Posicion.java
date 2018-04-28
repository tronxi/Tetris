package tetris;

public class Posicion
{
    private int x, y;

    public Posicion(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
    
    public void bajar()
    {
        x++;
    }
    public void derecha()
    {
        y++;
    }
    public void izquierda()
    {
        y--;
    }
    
}
