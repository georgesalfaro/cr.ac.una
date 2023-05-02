package cr.ac.una.mvc.example_b.model;

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
public interface ModelView {

    // La interfaz de la Vista-Modelo provee los métodos de acceso
    // al estado del modelo que son permitidos a la vista.
    // Normalmente, son métodos utilizados para actualizar la
    // interfaz, y no permiten completar modificaciones.
    //
    public int getK();

}
