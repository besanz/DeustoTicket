package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.net.URL;

public class ImageTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
            try {
                URL imageUrl = new URL((String) value);
                ImageIcon icon = new ImageIcon(imageUrl);
                setIcon(icon);
                setText("");
            } catch (Exception e) {
                e.printStackTrace();
                setText("Error");
            }
        }
        setHorizontalAlignment(JLabel.CENTER);
        return this;
    }
}