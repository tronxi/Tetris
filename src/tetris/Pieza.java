package tetris;

import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javax.swing.Timer;

public class Pieza
{

    private final int NUM_POSICIONES = 4;
    private final int VACIO = 0;
    private final int PIEZA_ACTUAL = 2;
    private final int PIEZA_MUERTA = 1;
    private ModeloTetris modelo;
    private Posicion posiciones[];
    private Posicion posicionesNuevas[];
    private int tipo;
    private int estado;

    public Pieza(int tipo)
    {
        this.tipo = tipo;
        modelo = ModeloTetris.getModelo();
        posiciones = new Posicion[NUM_POSICIONES];
        posicionesNuevas = new Posicion[NUM_POSICIONES];
        inicializarPieza();
    }
    public Posicion[] getPosiciones()
    {
        return posiciones;
    }
    public boolean contenida(int x, int y)
    {
        for(int i = 0; i < posiciones.length; i++)
        {
            if(posiciones[i].getX() == x && posiciones[i].getY() == y)
            {
                return true;
            }
        }
        return false;
    }
    public boolean comprobarBajar()
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            if (modelo.getValorTetris((posiciones[i].getX() + 1), posiciones[i].getY()) == PIEZA_MUERTA)
            {
                matar();
                return false;
            }
        }
        return true;
    }
    public int getTipo()
    {
        return tipo;
    }
    public boolean comprobarDerecha()
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            if (modelo.getValorTetris((posiciones[i].getX()), posiciones[i].getY() + 1) == PIEZA_MUERTA)
            {
                return false;
            }
        }
        return true;
    }

    public boolean comprobarIzquierda()
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            if (modelo.getValorTetris((posiciones[i].getX()), posiciones[i].getY() - 1) == PIEZA_MUERTA)
            {
                //matar();
                return false;
            }
        }
        return true;
    }

    public void izquierda()
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            posicionesNuevas[i].izquierda();

        }
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posiciones[i].getX(), posiciones[i].getY(), VACIO);
        }
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posicionesNuevas[i].getX(), posicionesNuevas[i].getY(), PIEZA_ACTUAL);
            posiciones[i].setX(posicionesNuevas[i].getX());
            posiciones[i].setY(posicionesNuevas[i].getY());
        }
    }

    public void derecha()
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            posicionesNuevas[i].derecha();

        }
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posiciones[i].getX(), posiciones[i].getY(), VACIO);
        }
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posicionesNuevas[i].getX(), posicionesNuevas[i].getY(), PIEZA_ACTUAL);
            posiciones[i].setX(posicionesNuevas[i].getX());
            posiciones[i].setY(posicionesNuevas[i].getY());
        }
    }

    public boolean comprobarGiro(int sentido)
    {
        if (tipo == 0)
        {

        }
        else if (tipo == 1)
        {
            if (estado == 0)
            {
                posicionesNuevas[0].setX(posiciones[0].getX());
                posicionesNuevas[0].setY(posiciones[0].getY() + 2);

                posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                posicionesNuevas[2].setX(posiciones[2].getX() + 2);
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() + 3);
                posicionesNuevas[3].setY(posiciones[3].getY() - 1);
            }
            else if (estado == 1)
            {
                posicionesNuevas[0].setX(posiciones[0].getX());
                posicionesNuevas[0].setY(posiciones[0].getY() - 2);

                posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                posicionesNuevas[2].setX(posiciones[2].getX() - 2);
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() - 3);
                posicionesNuevas[3].setY(posiciones[3].getY() + 1);

            }
        }
        else if (tipo == 2)
        {
            if (sentido == 1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 0);
                    posicionesNuevas[0].setY(posiciones[0].getY());

                    posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 0);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 0);
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX() + 0);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 0);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 0);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 0);
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 0);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 0);

                    posicionesNuevas[1].setX(posiciones[1].getX() + 0);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 0);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                }
            }
            //aquie comprobar
            else if (sentido == -1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX());
                    posicionesNuevas[0].setY(posiciones[0].getY());

                    posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY());
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY());
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX());
                    posicionesNuevas[0].setY(posiciones[0].getY());

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                }

            }
        }
        else if (tipo == 3)
        {
            if (estado == 0)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                posicionesNuevas[1].setX(posiciones[1].getX());
                posicionesNuevas[1].setY(posiciones[1].getY() - 2);

                posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                posicionesNuevas[3].setX(posiciones[3].getX());
                posicionesNuevas[3].setY(posiciones[3].getY());
            }
            else if (estado == 1)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                posicionesNuevas[1].setX(posiciones[1].getX());
                posicionesNuevas[1].setY(posiciones[1].getY() + 2);

                posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                posicionesNuevas[3].setX(posiciones[3].getX());
                posicionesNuevas[3].setY(posiciones[3].getY());
            }
        }
        else if (tipo == 4)
        {
            if (estado == 0)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() + 2);
                posicionesNuevas[0].setY(posiciones[0].getY());

                posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                posicionesNuevas[2].setX(posiciones[2].getX());
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                posicionesNuevas[3].setY(posiciones[3].getY() - 1);
            }
            else if (estado == 1)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() - 2);
                posicionesNuevas[0].setY(posiciones[0].getY());

                posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                posicionesNuevas[2].setX(posiciones[2].getX());
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                posicionesNuevas[3].setY(posiciones[3].getY() + 1);
            }
        }
        else if (tipo == 5)
        {
            if (sentido == 1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() - 2);
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() + 2);
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                }
            }
            else if (sentido == -1)
            {

                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() + 2);
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() - 2);
                }
            }
        }
        else if (tipo == 6)
        {
            if (sentido == 1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() + 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() - 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                }
            }
            else if (sentido == -1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() + 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() - 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                }
            }
        }
        if (piezaValida(posicionesNuevas))
        {
            return true;
        }
        else
        {
            for (int i = 0; i < posicionesNuevas.length; i++)
            {
                posicionesNuevas[i].setX(posiciones[i].getX());
                posicionesNuevas[i].setY(posiciones[i].getY());
            }
            return false;
        }
    }

    public void girar(int sentido)
    {
        if (tipo == 0)
        {

        }
        else if (tipo == 1)
        {
            if (estado == 0)
            {
                posicionesNuevas[0].setX(posiciones[0].getX());
                posicionesNuevas[0].setY(posiciones[0].getY() + 2);

                posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                posicionesNuevas[2].setX(posiciones[2].getX() + 2);
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() + 3);
                posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                estado = 1;
            }
            else if (estado == 1)
            {

                posicionesNuevas[0].setX(posiciones[0].getX());
                posicionesNuevas[0].setY(posiciones[0].getY() - 2);

                posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                posicionesNuevas[2].setX(posiciones[2].getX() - 2);
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() - 3);
                posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                estado = 0;
            }
        }
        else if (tipo == 2)
        {
            if (sentido == 1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 0);
                    posicionesNuevas[0].setY(posiciones[0].getY());

                    posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 0);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 0);
                    estado += sentido;
                    if (estado < 0)
                    {
                        estado = 3;
                    }
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX() + 0);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 0);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 0);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 0);
                    estado += sentido;
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 0);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 0);

                    posicionesNuevas[1].setX(posiciones[1].getX() + 0);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 0);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                    estado += sentido;
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                    posicionesNuevas[2].setX(posiciones[2].getX() + 0);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 0);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                    estado += sentido;
                    if (estado > 3)
                    {
                        estado = 0;
                    }
                }
            }
            //aqui
            else if (sentido == -1)
            {
                //sentido = 1;
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                    estado = 3;
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX());
                    posicionesNuevas[0].setY(posiciones[0].getY());

                    posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                    posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY());
                    estado = 0;
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY());
                    estado = 1;
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX());
                    posicionesNuevas[0].setY(posiciones[0].getY());

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                    estado = 2;
                }
            }
        }
        else if (tipo == 3)
        {

            if (estado == 0)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                posicionesNuevas[1].setX(posiciones[1].getX());
                posicionesNuevas[1].setY(posiciones[1].getY() - 2);

                posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                posicionesNuevas[3].setX(posiciones[3].getX());
                posicionesNuevas[3].setY(posiciones[3].getY());
                estado = 1;
            }
            else if (estado == 1)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                posicionesNuevas[1].setX(posiciones[1].getX());
                posicionesNuevas[1].setY(posiciones[1].getY() + 2);

                posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                posicionesNuevas[3].setX(posiciones[3].getX());
                posicionesNuevas[3].setY(posiciones[3].getY());
                estado = 0;
            }

        }
        else if (tipo == 4)
        {
            if (estado == 0)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() + 2);
                posicionesNuevas[0].setY(posiciones[0].getY());

                posicionesNuevas[1].setX(posiciones[1].getX() + 1);
                posicionesNuevas[1].setY(posiciones[1].getY() - 1);

                posicionesNuevas[2].setX(posiciones[2].getX());
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                estado = 1;
            }
            else if (estado == 1)
            {
                posicionesNuevas[0].setX(posiciones[0].getX() - 2);
                posicionesNuevas[0].setY(posiciones[0].getY());

                posicionesNuevas[1].setX(posiciones[1].getX() - 1);
                posicionesNuevas[1].setY(posiciones[1].getY() + 1);

                posicionesNuevas[2].setX(posiciones[2].getX());
                posicionesNuevas[2].setY(posiciones[2].getY());

                posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                estado = 0;
            }
        }
        else if (tipo == 5)
        {
            if (sentido == 1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() - 2);
                    estado = 1;
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                    estado = 2;
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() + 2);
                    estado = 3;
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                    estado = 0;
                }
            }
            else if (sentido == -1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                    estado = 3;
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() + 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() + 2);
                    estado = 0;
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 2);
                    posicionesNuevas[3].setY(posiciones[3].getY());
                    estado = 1;
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 1);
                    posicionesNuevas[2].setY(posiciones[2].getY() - 1);

                    posicionesNuevas[3].setX(posiciones[3].getX());
                    posicionesNuevas[3].setY(posiciones[3].getY() - 2);
                    estado = 2;
                }
            }
        }
        else if (tipo == 6)
        {
            if (sentido == 1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                    estado = 1;
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() + 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                    estado = 2;
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                    estado = 3;
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() - 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                    estado = 0;
                }
            }
            else if (sentido == -1)
            {
                if (estado == 0)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() + 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                    estado = 3;
                }
                else if (estado == 1)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() - 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() + 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() + 1);
                    estado = 0;
                }
                else if (estado == 2)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() - 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX());
                    posicionesNuevas[2].setY(posiciones[2].getY() - 2);

                    posicionesNuevas[3].setX(posiciones[3].getX() + 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                    estado = 1;
                }
                else if (estado == 3)
                {
                    posicionesNuevas[0].setX(posiciones[0].getX() + 1);
                    posicionesNuevas[0].setY(posiciones[0].getY() + 1);

                    posicionesNuevas[1].setX(posiciones[1].getX());
                    posicionesNuevas[1].setY(posiciones[1].getY());

                    posicionesNuevas[2].setX(posiciones[2].getX() - 2);
                    posicionesNuevas[2].setY(posiciones[2].getY());

                    posicionesNuevas[3].setX(posiciones[3].getX() - 1);
                    posicionesNuevas[3].setY(posiciones[3].getY() - 1);
                    estado = 2;
                }
            }
        }

        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posiciones[i].getX(), posiciones[i].getY(), VACIO);
        }
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posicionesNuevas[i].getX(), posicionesNuevas[i].getY(), PIEZA_ACTUAL);
            posiciones[i].setX(posicionesNuevas[i].getX());
            posiciones[i].setY(posicionesNuevas[i].getY());
        }
    }

    public void bajar()
    {

        for (int i = 0; i < posiciones.length; i++)
        {
            posicionesNuevas[i].bajar();

        }
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posiciones[i].getX(), posiciones[i].getY(), VACIO);
        }
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posicionesNuevas[i].getX(), posicionesNuevas[i].getY(), PIEZA_ACTUAL);
            posiciones[i].setX(posicionesNuevas[i].getX());
            posiciones[i].setY(posicionesNuevas[i].getY());
        }

    }

    public void imprimirValores()
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            System.out.println("posicion " + i + " " + posiciones[i].getX());
        }
    }

    private boolean piezaValida(Posicion[] posiciones)
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            if (posiciones[i].getX() < 0 || posiciones[i].getX() >= modelo.getFilas()
                    || posiciones[i].getY() < 0 || posiciones[i].getY() > modelo.getColumnas())
            {
                return false;
            }
            else
            {
                if (modelo.getValorTetris(posiciones[i].getX(), posiciones[i].getY()) == PIEZA_MUERTA)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void matar()
    {
        for (int i = 0; i < posiciones.length; i++)
        {
            modelo.setValor(posiciones[i].getX(), posiciones[i].getY(), PIEZA_MUERTA);
        }
        modelo.comprobarFin();
        tipo = (int) (Math.random() * 7);
        //tipo = 1;
        inicializarPieza();
    }

    private void inicializarPieza()
    {
        estado = 0;
        if (tipo == 0)
        {
            posiciones[0] = new Posicion(0, 4);
            posiciones[1] = new Posicion(0, 5);
            posiciones[2] = new Posicion(1, 4);
            posiciones[3] = new Posicion(1, 5);
        }
        else if (tipo == 1)
        {
            posiciones[0] = new Posicion(0, 3);
            posiciones[1] = new Posicion(0, 4);
            posiciones[2] = new Posicion(0, 5);
            posiciones[3] = new Posicion(0, 6);
        }
        else if (tipo == 2)
        {
            posiciones[0] = new Posicion(0, 5);
            posiciones[1] = new Posicion(1, 4);
            posiciones[2] = new Posicion(1, 5);
            posiciones[3] = new Posicion(1, 6);
        }
        else if (tipo == 3)
        {
            posiciones[0] = new Posicion(0, 5);
            posiciones[1] = new Posicion(0, 6);
            posiciones[2] = new Posicion(1, 4);
            posiciones[3] = new Posicion(1, 5);;
        }
        else if (tipo == 4)
        {
            posiciones[0] = new Posicion(0, 4);
            posiciones[1] = new Posicion(0, 5);
            posiciones[2] = new Posicion(1, 5);
            posiciones[3] = new Posicion(1, 6);
        }
        else if (tipo == 5)
        {
            posiciones[0] = new Posicion(0, 5);
            posiciones[1] = new Posicion(1, 5);
            posiciones[2] = new Posicion(2, 5);
            posiciones[3] = new Posicion(2, 6);
        }
        else if (tipo == 6)
        {
            posiciones[0] = new Posicion(0, 5);
            posiciones[1] = new Posicion(1, 5);
            posiciones[2] = new Posicion(2, 4);
            posiciones[3] = new Posicion(2, 5);
        }
        for (int i = 0; i < posiciones.length; i++)
        {
            posicionesNuevas[i] = new Posicion(posiciones[i].getX(), posiciones[i].getY());
            modelo.setValor(posiciones[i].getX(), posiciones[i].getY(), PIEZA_ACTUAL);
        }
    }
}
