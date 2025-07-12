/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author FCGI-ADMIN
 */
public class ColorRenderer extends DefaultTableCellRenderer {

    private final int targetRow;
    private final int targetColumn;
    private final Map<Point, Color> alteredCellColors;

    public ColorRenderer(int targetRow, int targetColumn) {
        this.targetRow = targetRow;
        this.targetColumn = targetColumn;
        this.alteredCellColors = new HashMap<>();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (row == targetRow && column == targetColumn) {
            component.setBackground(Color.GREEN);
            setHorizontalAlignment(CENTER);
        } else {
            Point cellKey = new Point(row, column);
            if (alteredCellColors.containsKey(cellKey)) {
                component.setBackground(alteredCellColors.get(cellKey));
            } else {
                component.setBackground(Color.WHITE);
            }
            setHorizontalAlignment(CENTER);
        }

        return component;
    }

    public void setAlteredCellColor(int row, int column, Color color) {
        alteredCellColors.put(new Point(row, column), color);
    }

    public Color getAlteredCellColor(int row, int column) {
        Point cellKey = new Point(row, column);
        return alteredCellColors.get(cellKey);
    }
}