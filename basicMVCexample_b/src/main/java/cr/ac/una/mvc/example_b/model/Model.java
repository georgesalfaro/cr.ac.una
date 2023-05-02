package cr.ac.una.mvc.example_b.model;

import cr.ac.una.mvc.ObservableModel;

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
public class Model extends ObservableModel implements ModelView {
    //<editor-fold desc="constructors">

    public Model() {
        System.out.println("Inicializando modelo..");
    }

    //</editor-fold>
    //<editor-fold desc="methods">
    public void test1() {
        System.out.println("Model.test1()");
        updateData("test1()");
    }

    public void test2(String msj) {
        System.out.printf("Model.test2(%s)", msj);
        updateData(String.format("test2()@'%s'", msj));
    }

    private void updateData(String msg) {
        k++;

        // El uso de un PropertyChangeListener permite enviar
        // eventos desde el modelo asociados a atributos o
        // métodos específicos.
        // El primer parámetro del método indica cuál es el
        // nombre del atributo que es modificado, junto con el
        // valor original (nulo en este caso) y el valor actual
        // de dicho atributo. Aquí se utiliza el mensaje para
        // notificar cuál es el método que hace la actualización.
        //
        notifyListeners(String.format("%s; k", msg), k);
    }

    //</editor-fold>
    //<editor-fold desc="attributes">
    @Override
    public int getK() {
        return k;
    }

    // Componentes del modelo (aquí es solamente un contador)
    private int k = 0;
    //</editor-fold>
}
