package tetris;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class FXMLDocumentController implements Initializable
{

    Timer timer = new Timer();

    class bajadaAuto extends TimerTask
    {

        @Override
        public void run()
        {
            bajar();
        }

    }

    private final int VACIO = 0;
    private final int PIEZA_ACTUAL = 2;
    private final int PIEZA_MUERTA = 1;
    private Pieza pieza = new Pieza((int) (Math.random() * 7));
    private ModeloTetris modelo = ModeloTetris.getModelo();
    private Color[] colores =
    {
        Color.YELLOW, Color.CYAN, Color.PURPLE, Color.GREEN, Color.RED, Color.ORANGE, Color.BLUE
    };
    @FXML
    private VBox caja;
    @FXML
    private HBox filas[];
    @FXML
    private Rectangle rectangulos[][];

    @FXML
    private Label puntuacion;
    @FXML
    private Label lineas;
    @FXML
    private Label maxPuntuacion;
    

    @FXML
    private void handleButtonAction(KeyEvent event)
    {
        if (event.getText().equals("d"))
        {
            derecha();
        }
        else if (event.getText().equals("a"))
        {
            izquierda();
        }
        else if (event.getText().equals("s"))
        {
            bajar();
        }
        else if (event.getText().equals("1"))
        {
            girar(-1);
        }
        else if (event.getText().equals("3"))
        {
            girar(1);
        }
                puntuacion.setText("" + modelo.getPuntuacion());
        lineas.setText("" + modelo.getLineas());
        maxPuntuacion.setText(""+modelo.getMaxPuntuacion());
    }

    private void girar(int sentido)
    {
        if (pieza.comprobarGiro(sentido))
        {
            pieza.girar(sentido);
        }
        dibujar();
    }

    private void dibujar()
    {
        modelo.comprobarLinea();
        modelo.limpirar(pieza);

        for (int i = 0; i < rectangulos.length; i++)
        {
            for (int j = 0; j < rectangulos[0].length; j++)
            {
                if (j == 0 || j == rectangulos[0].length - 1 || i == rectangulos.length - 1)
                {
                    rectangulos[i][j].setFill(Color.GRAY);
                }
                else if (modelo.getValorTetris(i, j) == VACIO)
                {
                    rectangulos[i][j].setFill(Color.DARKGRAY);
                }
                else if (modelo.getValorTetris(i, j) == PIEZA_MUERTA)
                {
                    rectangulos[i][j].setFill(Color.CORNFLOWERBLUE);
                }
                else
                {
                    rectangulos[i][j].setFill(colores[pieza.getTipo()]);
                }
            }
        }
    }

    private void derecha()
    {
        if (pieza.comprobarDerecha())
        {
            pieza.derecha();
        }
        dibujar();
    }

    private void izquierda()
    {
        if (pieza.comprobarIzquierda())
        {
            pieza.izquierda();
        }
        dibujar();
    }

    private void bajar()
    {
        if (pieza.comprobarBajar())
        {
            pieza.bajar();
        }
        else
        {
            timer.cancel();
            timer = new Timer();
            timer.schedule(new bajadaAuto(), 0,modelo.getVelocidad());
        }
        dibujar();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        filas = new HBox[modelo.getFilas()];
        for (int i = 0; i < filas.length; i++)
        {
            filas[i] = (HBox) caja.getChildren().get(i);
        }

        rectangulos = new Rectangle[modelo.getFilas()][modelo.getColumnas()];

        for (int i = 0; i < rectangulos.length; i++)
        {
            for (int j = 0; j < rectangulos[0].length; j++)
            {
                rectangulos[i][j] = new Rectangle();
                rectangulos[i][j] = (Rectangle) filas[i].getChildren().get(j);
            }
        }
        timer.schedule(new bajadaAuto(), 0,modelo.getVelocidad());
        dibujar();
    }

}
