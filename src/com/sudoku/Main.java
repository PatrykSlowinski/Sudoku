package com.sudoku;

import model.SudokuGrid;
import model.SudokuInitializerException;
import utils.GameConsole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException, SudokuInitializerException {
        SudokuGrid sudokuGrid = new SudokuGrid();
        /*System.out.println(sudokuGrid);
        System.out.println(sudokuGrid.isFull());
        System.out.println(sudokuGrid.isValid());

        System.out.println(sudokuGrid.repeated(7,7));
        System.out.println(sudokuGrid.isValid());*/
        Path path = Path.of("./sudoku.txt");
        /*try {
            sudokuGrid.sudokuInitializer(path);
        } catch (IOException e) {
            System.out.println("ERROR: unable to read from file");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
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
        //System.out.println(sudokuGrid);
        if(ifException!= true) {
            GameConsole game = new GameConsole(sudokuGrid);
            game.play();
        }
    }
}
