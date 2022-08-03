package com.sudoku;

import model.SudokuGrid;
import model.SudokuInitializerException;
import utils.GameConsole;
import view.GridFrame;
import view.GridPanel;

import java.io.IOException;
import java.nio.file.Path;

public class SudokuViewMain {
    public static void main(String[] args) {
        SudokuGrid sudokuGrid = new SudokuGrid();
        Path path = Path.of("./sudoku.txt");
        Boolean ifException = false;
        try {
            sudokuGrid.sudokuInitializer(path);
        } catch (IOException e) {
            System.out.println("ERROR: unable to read from file");
            ifException = true;
        }catch(SudokuInitializerException e){
            System.out.println(e.getMessage());
            ifException = true;
        }
        if(ifException!= true) {
            GridPanel panel = new GridPanel(sudokuGrid);
            GridFrame gridFrame = new GridFrame("Sudoku", sudokuGrid);
            gridFrame.setDefaultCloseOperation(GridFrame.EXIT_ON_CLOSE);
            gridFrame.setContentPane((GridPanel) panel);
            gridFrame.pack();
            gridFrame.setVisible(true);
        }
    }
}
