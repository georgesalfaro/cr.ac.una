package cr.ac.una.mvc;

/**
 * -------------------------------------------------------------------
 *
 * (c) 2021-2022
 *
 * @author Georges Alfaro S.
 * @version 2.0.0 2022-09-26
 *
 * --------------------------------------------------------------------
 */
public interface Observable {

    public void addObserver(Observer obs);

    public int countObservers();

    public void deleteObserver(Observer obs);

    public void deleteObservers();

    public boolean hasChanged();

    public void notifyObservers();

    public void notifyObservers(Object evt);

}
