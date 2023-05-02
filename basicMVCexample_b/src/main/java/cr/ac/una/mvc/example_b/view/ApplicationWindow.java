package cr.ac.una.mvc.example_b.view;

import cr.ac.una.gui.StatusBar;
import cr.ac.una.mvc.example_b.control.ApplicationControl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import cr.ac.una.mvc.example_b.model.ModelView;

 
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
public class ApplicationWindow extends JFrame implements PropertyChangeListener {

    public ApplicationWindow(String title, ApplicationControl mainControl)
            throws HeadlessException {
        super(title);

        // Asocia la clase de control para poder delegar los métodos
        // que van a ser ejecutados desde la interfaz.
        //
        this.mainControl = mainControl;

        setup();
    }

    private void setup() {
        setupComponents(getContentPane());

        setResizable(true);
        setSize(640, 360);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        // En lugar de definir un comportamiento por defecto para
        // la ventana, se asocia un WindowListener de manera que
        // la ventana utilice el mismo método cuando se cierra por
        // medio del control en la barra de nombre o cuando se
        // selecciona la opción desde un menú u otro control de
        // interfaz.
        //
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }

        });
    }

    private void setupComponents(Container c) {
        c.setLayout(new BorderLayout());

        setupMenus();

        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (background == null) {
                    try {
                        background = ImageIO.read(this.getClass()
                                .getResourceAsStream(BKGND_IMAGE_PATH));
                    } catch (IOException ex) {
                        System.err.printf("Excepción: '%s'%n", ex.getMessage());
                    }
                }

                if (background != null) {
                    // Dibuja la imagen de fondo en el centro del contenedor.
                    g.drawImage(background,
                            (getWidth() - background.getWidth(this)) / 2,
                            (getHeight() - background.getHeight(this)) / 2,
                            this);
                }
            }

        };

        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        mainPanel.add(testBtn = new JButton("Prueba"));
        c.add(BorderLayout.CENTER, mainPanel);

        c.add(BorderLayout.PAGE_END, status = new StatusBar());

        // Define los eventos asociados a cada componente de la ventana.
        // Los menúes son configurados en un método separado.
        //
        testBtn.addActionListener(e -> {
            doTest1();
        });
    }

    private void setupMenus() {
        mainMenu = new JMenuBar();

        mainMenu.add(fileMenu = new JMenu("Archivo"));
        fileMenu.add(quitItem = new JMenuItem("Salir"));

        mainMenu.add(windowMenu = new JMenu("Ventana"));
        windowMenu.add(windowItem = new JMenuItem("Abrir Ventana"));

        setJMenuBar(mainMenu);

        quitItem.addActionListener(e -> {
            closeWindow();
        });

        windowItem.addActionListener(e -> {
            openInfoWindow();
        });
        windowItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));

    }

    public void init() {

        // Por medio del gestor o clase de control, se registra
        // la ventana con el modelo, para que pueda recibir notificaciones
        // de los eventos que requieran actualizar la interfaz.
        //
        mainControl.register(this);

        status.init();

        setVisible(true);
        status.showMessage(String.format("Interfaz inicializada (%d, %d)..",
                getWidth(), getHeight()));
        System.out.println();

        // Se usa un temporizador para que los mensajes que aparecen en
        // la barra de estado se borren luego de un tiempo especificado.
        //
        status.setTimed(true);
        status.setMaxTime(MAX_MSG_TIME);
    }

    public void doTest1() {
        System.out.println("Delegando ejecución al gestor de la aplicación..");
        mainControl.doTest1();
        System.out.println();
    }

    public void openInfoWindow() {

        // Observe que la ventana principal NO tiene ningún registro
        // de cada una de las ventanas secundarias de información
        // que van siendo creadas. Cada ventana se comporta de manera
        // completamente independiente e interactúa directamente
        // con la clase de control, al mismo tiempo que recibe eventos
        // desde el modelo.
        //
        new InfoWindow("Ventana Información", mainControl).init();
    }

    public boolean confirmClose() {
        Object[] options = {"Sí", "No"};
        return JOptionPane.showOptionDialog(this,
                "¿Desea cerrar la aplicación?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options, // texto de los botones
                options[0] // opción por defecto
        ) == JOptionPane.OK_OPTION;
    }

    public void closeWindow() {
        if (confirmClose()) {
            System.out.println("Cerrando la aplicación..");

            mainControl.remove(this);
            mainControl.closeApplication();
        }
    }

    @Override
    public String toString() {
        return String.format("VentanaAplicacion('%s')", getTitle());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        // La ventana recibe una notificación de un evento desde el modelo.
        //
        // En este método, la ventana debe actualizar todos los objetos
        // que muestran algún elemento del modelo en la interfaz.
        // Observe que no hay ninguna referencia al modelo desde los
        // parámetros del método. Si es necesario obtener el estado del
        // modelo, se solicitará a la clase de control.
        //
        status.showMessage(
                String.format("Evento recibido: %s = %s",
                        evt.getPropertyName(), evt.getNewValue())
        );

        ModelView model = mainControl.getModel();
        System.out.printf("(VistaModelo) k = %d%n", model.getK());

        // Aquí se actualiza la interfaz (cuando sea necesario)..
    }

    private final ApplicationControl mainControl;
    private static final int MAX_MSG_TIME = 5_000;
    private static final String BKGND_IMAGE_PATH = "imgs/UNA.png";
    private Image background = null;

    private JMenuBar mainMenu;

    private JMenu fileMenu;
    private JMenuItem quitItem;

    private JMenu windowMenu;
    private JMenuItem windowItem;

    private JButton testBtn;
    private StatusBar status;

    // La ventana no maneja ninguna información del modelo.
    // Todas las operaciones son delegadas a la clase de control.
}
