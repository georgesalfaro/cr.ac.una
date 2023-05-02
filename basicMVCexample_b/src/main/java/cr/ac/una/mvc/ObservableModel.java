package cr.ac.una.mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * -------------------------------------------------------------------
 *
 * (c) 2021-2022
 *
 * @author Georges Alfaro S.
 * @version 1.0.1 2022-09-26
 *
 * --------------------------------------------------------------------
 */
public abstract class ObservableModel {

    public ObservableModel() {
        this.notifier = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener obs) {
        notifier.addPropertyChangeListener(obs);
    }

    public void removePropertyChangeListener(PropertyChangeListener obs) {
        notifier.removePropertyChangeListener(obs);
    }

    public void notifyListeners(String property, Object evt) {
        notifier.firePropertyChange(property, null, evt);
    }

    private final PropertyChangeSupport notifier;
}
