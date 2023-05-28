import java.awt.event.*;
import javax.swing.*;

public class ToolBarButton extends JButton {
    public ToolBarButton(String iconFile, String tooltipText, ActionListener actionListener) {
        setIcon(new ImageIcon(iconFile));
        setToolTipText(tooltipText);
        setSize(32, 32);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        addActionListener(actionListener);
    }
}
