package tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloTetris
{

    private final int VACIO = 0;
    private final int PIEZA_ACTUAL = 2;
    private final int PIEZA_MUERTA = 1;
    private final int FILAS = 21;
    private final int COLUMNAS = 12;
    private int lineas;
    private int puntuacion;
    private int tetris[][];
    private int velocidad;
    private int maxPuntuacion;
    private static ModeloTetris modelo = null;

    private String rutaPuntuacion = "puntuaciones.txt";

    private ModeloTetris()
    {
        tetris = new int[FILAS][COLUMNAS];
        try
        {
            DataInputStream entrada = new DataInputStream(new FileInputStream(rutaPuntuacion));
            maxPuntuacion = entrada.readInt();
            entrada.close();
        }
        catch (Exception e)
        {

        }

        inicializar();
    }


    private void inicializar()
    {
        velocidad = 1000;
        puntuacion = 0;
        lineas = 0;
        for (int i = 0; i < tetris.length; i++)
        {
            for (int j = 0; j < tetris[0].length; j++)
            {
                if (j == 0 || j == COLUMNAS - 1 || i == FILAS - 1)
                {
                    tetris[i][j] = PIEZA_MUERTA;
                }
                else
                {
                    tetris[i][j] = VACIO;
                }
            }
        }
    }

    public void setLineas(int l)
    {
        lineas = l;
    }

    public int getLineas()
    {
        return lineas;
    }

    public void setPuntuacion(int l)
    {
        puntuacion = l;
    }

    public int getPuntuacion()
    {
        return puntuacion;
    }

    public int getVelocidad()
    {
        return velocidad;
    }

    public boolean comprobarFin()
    {
        for (int i = 1; i < tetris[0].length - 1; i++)
        {
            if (tetris[0][i] == PIEZA_MUERTA)
            {
                inicializar();
                return true;
            }
        }
        return false;
    }

    public void imprimir()
    {
        for (int i = 0; i < tetris.length; i++)
        {
            for (int j = 0; j < tetris[0].length; j++)
            {
                System.out.print(" " + tetris[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static ModeloTetris getModelo()
    {
        if (modelo == null)
        {
            modelo = new ModeloTetris();
        }
        return modelo;
    }

    public void setValor(int x, int y, int valor)
    {
        tetris[x][y] = valor;
    }

    public int getValorTetris(int x, int y)
    {
        return tetris[x][y];
    }

    public int[][] getTetris()
    {
        return tetris;
    }

    public int getFilas()
    {
        return FILAS;
    }

    public int getColumnas()
    {
        return COLUMNAS;
    }

    public void limpirar(Pieza pieza)
    {
        for (int i = 0; i < tetris.length; i++)
        {
            for (int j = 0; j < tetris[0].length; j++)
            {
                if (tetris[i][j] == PIEZA_ACTUAL && !pieza.contenida(i, j))
                {
                    tetris[i][j] = VACIO;
                }
            }
        }
    }

    public int getMaxPuntuacion()
    {
        return maxPuntuacion;
    }

    public void comprobarLinea()
    {
        int puntosaux = 0;
        int[] aux = new int[tetris[0].length - 2];
        for (int i = 0; i < tetris.length - 1; i++)
        {
            for (int j = 1; j < tetris[0].length - 1; j++)
            {
                aux[j - 1] = tetris[i][j];
            }
            if (comprobarAux(aux))
            {
                puntosaux++;
                lineas++;
                borrarLinea(i);
            }
        }
        puntuacion += puntosaux * 100 * 1.25 * (1000 / velocidad);
        if (puntuacion > maxPuntuacion)
        {
            try
            {
                DataOutputStream salida = new DataOutputStream(new FileOutputStream(rutaPuntuacion));
                salida.writeInt(puntuacion);
                salida.close();
                maxPuntuacion = puntuacion;
            }
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(ModeloTetris.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(ModeloTetris.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void borrarLinea(int linea)
    {
        velocidad -= 10;
        for (int i = 0; i < tetris[0].length; i++)
        {
            tetris[linea][i] = VACIO;
        }
        for (int i = linea; i >= 0; i--)
        {
            for (int j = 0; j < tetris[0].length; j++)
            {
                if (i - 1 >= 0)
                {
                    tetris[i][j] = tetris[i - 1][j];
                }
                else
                {
                    for (int k = 0; k < tetris[0].length; k++)
                    {
                        if (k == 0 || k == tetris[0].length - 1)
                        {
                            tetris[0][k] = PIEZA_MUERTA;
                        }
                        else
                        {
                            tetris[0][k] = VACIO;
                        }
                    }
                }
            }
        }
    }

    private boolean comprobarAux(int[] aux)
    {
        for (int i = 0; i < aux.length; i++)
        {
            if (aux[i] != PIEZA_MUERTA)
            {
                return false;
            }
        }
        return true;
    }
}
