package cr.ac.una.mvc.example_b;

import cr.ac.una.mvc.example_b.control.ApplicationControl;
import cr.ac.una.mvc.example_b.view.ApplicationWindow;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
public class BasicExampleB {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | UnsupportedLookAndFeelException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

        new BasicExampleB().init();

        System.out.println("Aplicación inicializada..");
        System.out.println();
    }

    private void init() {

        // https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        // https://docs.oracle.com/javase/7/docs/api/javax/swing/SwingUtilities.html
        //
        // Las actualizaciones de la GUI, a través de Swing, deben ocurrir en el
        // subproceso de despacho de eventos (Event Dispatch Thread - EDT),
        // y cualquier otro código debe utilizar un hilo separado.
        // El método permite que otros subprocesos actualicen la GUI de la
        // manera correcta, ya que no interrumpen al ciclo principal de atención
        // de eventos.
        // Al utilizar un nuevo hilo, la invocación al método run() se hace de
        // manera asíncrona, en cuanto el EDT pueda ejecutarlo.
        //
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });

        // El código anterior utiliza una expresión lambda para sustituir el uso
        // de una clase anónima que implementa la interfaz Runnable, ya que se
        // trata de una interfaz funcional.
        //
        // https://www.baeldung.com/java-8-functional-interfaces
        // https://www.tutorialspoint.com/how-to-implement-the-runnable-interface-using-lambda-expression-in-java
        //
        // SwingUtilities.invokeLater(new Runnable() {
        //     @Override
        //     public void run() {
        //         createAndShowGUI();
        //     }
        // });
    }

    private void createAndShowGUI() {
        System.out.println("Iniciando interfaz..");

        // Observe que el orden de creación de las instancias de los
        // componentes principales de la aplicación es:
        // 1. el modelo (a través del constructor de la clase de control)
        // 2. el controlador (director o gestor principal)
        // 3. la ventana (vista) principal
        //
        // Una vez que se ha creado la ventana principal, ésta se muestra
        // para permitir que el usuario inicie la interacción.
        //
        new ApplicationWindow("Ejemplo Básico MVC (b)",
                new ApplicationControl()).init();
    }

}
