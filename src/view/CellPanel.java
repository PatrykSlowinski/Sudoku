package view;

import model.CellState;
import model.SudokuGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellPanel extends JPanel {
    private static final Color CELL_COLOR = Color.WHITE;
    private static final Color FIXED_CELL_COLOR = Color.BLUE;
    private static final Color VALID_CELL_COLOR = Color.BLACK;
    private static final Color INVALID_CELL_COLOR = Color.RED;
    private static final int CELL_SIZE = 60;
    private static final int TEXT_SIZE = 30;
    private JTextField cellValue;
    private SudokuGrid sudokuGrid;
    private String value;
    private int row;
    private int column;

    boolean selected;
    private BlockPanel blockPanel;

    public CellPanel(SudokuGrid sudokuGrid, int r, int c, BlockPanel bp) {
        this.sudokuGrid = sudokuGrid;
        this.row = r;
        this.column = c;
        this.blockPanel = bp;
        Dimension preferredSize = new Dimension(60,60);
        this.setBackground(CELL_COLOR);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(0);
        flowLayout.setVgap(0);
        this.setLayout(flowLayout);
        if(this.sudokuGrid.cellState(this.row, this.column) == CellState.EMPTY){
            this.value = "";
        }
        else{
            this.value = Integer.toString(this.sudokuGrid.getValueAt(row,column));
        }

        this.cellValue = new JTextField();
        this.cellValue.setBackground(CELL_COLOR);
        if(this.sudokuGrid.cellState(this.row, this.column) == CellState.FIXED){
            this.cellValue.setForeground(FIXED_CELL_COLOR);
        }
        else{
            this.cellValue.setForeground(VALID_CELL_COLOR);
        }
        this.cellValue.setHorizontalAlignment(0);
        this.cellValue.setPreferredSize(preferredSize);
        this.cellValue.setFont(new Font("Serif", 0 ,30));
        this.cellValue.setText(this.value);
        this.add(this.cellValue);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.cellValue.setEditable(false);
        this.cellValue.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CellPanel.this.selectCell();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.cellValue.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(CellPanel.this.sudokuGrid.cellState(CellPanel.this.row,CellPanel.this.column)!=CellState.FIXED){
                    CellPanel.this.setForeground(VALID_CELL_COLOR);
                    char key = e.getKeyChar();
                        if(key>='1' && key<='9'){
                            CellPanel.this.cellValue.setText(Character.toString(key));
                            CellPanel.this.value = Character.toString(key);
                        }
                        else if(e.getKeyCode()==8 || e.getKeyCode()==127){
                            CellPanel.this.cellValue.setText("");
                            CellPanel.this.value = "";
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Wrong key. Only numeric keys from 1 to 9 are allowed!");
                        }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });



    }

    public void selectCell() {
        if((this.selected == false)){
            if(this.sudokuGrid.cellState(this.row, this.column)!=CellState.FIXED) {
                this.selected = true;
                this.blockPanel.newCellSelected(this.row, this.column);
                //if(this.sudokuGrid.cellState(this.row, this.column)!=CellState.FIXED){
                this.value = this.cellValue.getText();
                this.cellValue.setBackground(Color.GRAY);
                //}
            }
        }
    }
    public void deselect() {
        this.selected = false;
        if(this.value.equals("")){
            if(this.sudokuGrid.cellState(this.row, this.column)!=CellState.FIXED) {
                this.sudokuGrid.emptyCell(this.row, this.column);
            }
        }
        else{
            this.sudokuGrid.setValueAt(Integer.parseInt(this.value),this.row, this.column);
            if(this.sudokuGrid.repeated(this.row, this.column)){
                this.cellValue.setForeground(INVALID_CELL_COLOR);
            }
            else{
                this.cellValue.setForeground(VALID_CELL_COLOR);
            }
        }


        this.cellValue.setBackground(CELL_COLOR);}


}
