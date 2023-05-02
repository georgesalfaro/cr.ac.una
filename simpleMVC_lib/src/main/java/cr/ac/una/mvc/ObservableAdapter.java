package cr.ac.una.mvc;

import java.util.LinkedList;
import java.util.List;

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
public class ObservableAdapter implements Observable {

    public ObservableAdapter() {
        this.observers = new LinkedList<>();
        this.hasChanged = false;
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    protected void clearChanged() {
        hasChanged = false;
    }

    @Override
    public int countObservers() {
        return observers.size();
    }

    @Override
    public void deleteObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void deleteObservers() {
        observers.clear();
    }

    @Override
    public boolean hasChanged() {
        return hasChanged;
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(Object evt) {
        if (hasChanged()) {
            observers.forEach(obs -> {
                obs.update(this, evt);
            });
        }
    }

    protected void setChanged() {
        hasChanged = true;
    }

    private final List<Observer> observers;
    private boolean hasChanged;
}
