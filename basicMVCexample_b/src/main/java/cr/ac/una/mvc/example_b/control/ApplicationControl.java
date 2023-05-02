package cr.ac.una.mvc.example_b.control;

import cr.ac.una.mvc.example_b.model.Model;
import java.beans.PropertyChangeListener;
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
public class ApplicationControl {
    //<editor-fold desc="constructors">

    public ApplicationControl(Model data) {
        System.out.println("Iniciando gestor de la aplicación..");
        this.data = data;
    }

    public ApplicationControl() {

        // Si la aplicación no obtiene una referencia del modelo
        // externamente (como un recurso de conexión a una base
        // de data, por ejemplo), la clase de control crea la
        // instancia directamente.
        //
        this(new Model());
    }

    //</editor-fold>
    //<editor-fold desc="MVC">
    public void register(PropertyChangeListener newObserver) {
        // Asocia el modelo a la clase de control, para poder
        // ejecutar los métodos correspondientes.
        //
        System.out.printf("Registrando: %s..%n", newObserver);
        data.addPropertyChangeListener(newObserver);
    }

    public void remove(PropertyChangeListener current) {
        System.out.printf("Suprimiendo: %s..%n", current);
        data.removePropertyChangeListener(current);
    }

    //</editor-fold>
    //<editor-fold desc="methods (functionality)">
    public void doTest1() {
        System.out.println("ApplicationControl.doTest1()");
        data.test1();
    }

    public void doTest2(String msg) {
        System.out.println("ApplicationControl.doTest2()");
        data.test2(msg);
    }

    public ModelView getModel() {
        // El método regresa una referencia al modelo pero con
        // el tipo de la clase ModelView (ModelView) para limitar
        // los métodos a los que tendrá acceso la vista.
        return data;
    }
    //</editor-fold>
    //<editor-fold desc="métodos (control de la interfaz)">

    public void closeApplication() {
        System.out.println("Aplicación finalizada normalmente..");

        // Al cerrar la aplicación, todas las ventanas que son atendidas
        // por el EDT principal, son cerradas también. No es necesario
        // tener una referencia para cerrarlas de manera expícita.
        //
        System.exit(0);
    }
    //</editor-fold>
    //<editor-fold desc="attributes">

    private Model data;
    //</editor-fold>
}
