package cr.ac.una.mvc.example_a.model;

import cr.ac.una.mvc.ObservableAdapter;

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
public class Model extends ObservableAdapter implements ModelView {
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

        // https://www.geeksforgeeks.org/java-util-observable-class-java/
        //
        // Aquí se usa la implementación clásica del patrón
        // Observable/Observador de Java.
        // Como las clases que realizan el patrón se consideran
        // obsoletas desde la versión 14 de Java, se usa una implementación
        // propia que define Observable como una interfaz (en lugar de una
        // clase) que tiene una realización por defecto en la clase
        // ObservableAdapter.
        // La clase ObservableAdapter se puede usar como en el patrón original,
        // donde el modelo se define como una subclase, o se puede usar
        // por delegación. Esto permite que el modelo herede de otra clase
        // más general.
        //
        setChanged();
        notifyObservers(String.format("%s; k = %d", msg, k));
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
