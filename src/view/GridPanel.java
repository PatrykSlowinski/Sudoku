package view;

import model.SudokuGrid;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private BlockPanel[][] blocks;
    private int selectedCellRow;
    private int selectedCellColumn;
    public GridPanel(SudokuGrid sudokuGame){
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(3,3,2,2));
        this.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        this.blocks = new BlockPanel[3][3];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                this.blocks[i][j] = new BlockPanel(sudokuGame, i, j, this);
                this.add(this.blocks[i][j]);
            }
        }
    }
    public void newCellSelected(int row, int column){
        if(this.selectedCellRow!=row || this.selectedCellColumn!=column){
            if (this.selectedCellRow != -1) {
            this.blocks[this.selectedCellRow/3][this.selectedCellColumn/3].deselectCell(this.selectedCellRow,this.selectedCellColumn);}
            this.selectedCellRow = row;
            this.selectedCellColumn = column;
        }
    }
}
