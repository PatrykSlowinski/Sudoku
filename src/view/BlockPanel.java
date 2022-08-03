package view;

import model.SudokuGrid;

import javax.swing.*;
import java.awt.*;

public class BlockPanel extends JPanel {
    private CellPanel[][] cells;
    private GridPanel gridPanel;
    public BlockPanel(SudokuGrid sudokuGame, int row, int column, GridPanel gridPanel){
        this.gridPanel = gridPanel;
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new GridLayout(3,3,0,0));
        this.cells = new CellPanel[3][3];

        for(int i = 0; i<3; i++)
        {
            for(int j = 0; j<3; j++){
                this.cells[i][j] = new CellPanel(sudokuGame, row*3 + i, column * 3 +j, this);
                this.add(this.cells[i][j]);
            }
        }
    }

    public void newCellSelected(int cellRow, int cellColumn)
    {
        this.gridPanel.newCellSelected(cellRow,cellColumn);
    }
    public void deselectCell(int cellRow, int cellColumn)
    {
        this.cells[cellRow % 3][cellColumn % 3].deselect();
    }
}
