
package cr.ac.una.gui;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class StatusBar extends JPanel {

    public StatusBar() {
        setup();
    }
    private void  setup() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }
    
    private JLabel statusLabel;
}
