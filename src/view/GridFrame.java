package view;

import model.SudokuGrid;

import javax.swing.*;

public class GridFrame extends JFrame implements GameView {
    private GridPanel panel;

    public GridFrame(String title, SudokuGrid model){
        super(title);
        this.panel = new GridPanel(model);
        this.setVisible(true);
    }
    @Override
    public void showMessage(String m) {
        JOptionPane.showMessageDialog(this,m);
    }
}
