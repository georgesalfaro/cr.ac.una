package cr.ac.una.mvc.example_a.view;

import cr.ac.una.gui.StatusBar;
import cr.ac.una.mvc.Observable;
import cr.ac.una.mvc.Observer;
import cr.ac.una.mvc.example_a.control.ApplicationControl;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * -------------------------------------------------------------------
 *
 * (c) 2021-2022
 *
 * @author Georges Alfaro S.
 * @version 2.1.0 2021-09-13
 *
 * --------------------------------------------------------------------
 */
public class InfoWindow extends JFrame implements Observer {

    public InfoWindow(String title, ApplicationControl mainControl)
            throws HeadlessException {
        super(title);
        this.mainControl = mainControl;
        setup();
    }

    private void setup() {
        setupComponents(getContentPane());
        setResizable(false);
        setSize(480, 120);
        setLocationByPlatform(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                // Se elimina la ventana de la lista de observadores..
                mainControl.remove(InfoWindow.this);

                // Y se cierra, pero SIN cerrar la aplicación.
                dispose();

                // Cuando la ventana principal se cierra, indica a
                // la clase de control que cierre la aplicación.
                // Al cerrar la aplicación y el ciclo de atención de
                // eventos, todas las ventanas controladas por el
                // mismo hilo son cerradas.
            }

        });
    }

    private void setupComponents(Container c) {
        c.setLayout(new BorderLayout());

        JPanel b = new JPanel();
        b.setLayout(new FlowLayout(FlowLayout.LEFT));
        b.add(testBtn = new JButton("Prueba"));
        c.add(BorderLayout.CENTER, b);

        c.add(BorderLayout.PAGE_END, status = new StatusBar());

        testBtn.addActionListener((ActionEvent e) -> {
            doTest2();
        });

    }

    public void init() {
        setTitle(String.format("%s %d", getTitle(), ++instances));
        mainControl.register(this);
        status.init();

        setVisible(true);
    }

    public void doTest2() {
        mainControl.doTest2(getTitle());
        System.out.println();
    }

    @Override
    public void update(Observable subject, Object evt) {

        // La ventana muestra un mensaje cada vez que recibe un evento
        // desde el modelo.
        // La ventana no registra ni depende de aquella que haya
        // generado el evento.
        //
        status.showMessage(String.format("Evento recibido: %s", evt));
    }

    @Override
    public String toString() {
        return String.format("VentanaInfo('%s')", getTitle());
    }

    private static int instances = 0;
    private final ApplicationControl mainControl;
    private JButton testBtn;
    private StatusBar status;
}
