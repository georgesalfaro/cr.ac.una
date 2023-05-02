package cr.ac.una.mvc;

/**
 * -------------------------------------------------------------------
 *
 * (c) 2021-2022
 *
 * @author Georges Alfaro S.
 * @version 1.2.0 2021-10-26
 *
 * --------------------------------------------------------------------
 */
public interface Observer {

    public void update(Observable subject, Object event);

}
