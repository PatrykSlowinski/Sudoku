package utils;

import model.Cell;
import model.CellState;
import model.SudokuGrid;

import java.util.Scanner;

public class GameConsole {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";
    char c = '\u2551';
    private SudokuGrid sudoku;
    private Scanner scanner;

    public GameConsole(SudokuGrid sudokuGrid){
        this.sudoku = sudokuGrid;
        this.scanner = new Scanner(System.in);
    }
    public PlayerAction playerAction(){
        int value = 0;
        int row = 0;
        int column = 0;
        int action = 0;
        do{
            System.out.println("Choose row (between 1 and 9): ");
            if(this.scanner.hasNextInt()){
                row = this.scanner.nextInt();
            }
            else{
                this.scanner.nextLine();
            }
        }while(row<1 || row>9);

        do{
            System.out.println("Choose column (between 1 and 9): ");
            if(this.scanner.hasNextInt()){
                column = this.scanner.nextInt();
            }
            else{
               // System.out.println("Wrong value. You have to choose column between 1 and 9: ");
                this.scanner.nextLine();
            }
        }while(column<1 || column>9);

        do {
            System.out.println("Choose action: Set a value (1) or Empty a cell (2): ");
            if(this.scanner.hasNextInt()) {
                action = this.scanner.nextInt();
            }
            else{
                this.scanner.nextLine();
                //System.out.println("Wrong value. You have to choose 1 or 2: ");
            }
        }while(action!=1 && action!=2);

        PlayerAction playerAction;

        if (action == 1) {

                do {
                    System.out.println("Choose a new value for the cell (between 1 and 9): ");
                    if (this.scanner.hasNextInt()) {
                        value = this.scanner.nextInt();
                    } else {
                        this.scanner.nextLine();
                    }

                } while(value < 1 || value>9);
                System.out.println("-----------------------------------------------------");


            playerAction = new PlayerAction(row-1, column-1, value, Action.FILLCELL);
        } else {
            playerAction = new PlayerAction(row-1, column-1, 0, Action.EMPTYCELL);
        }

        return playerAction;
    }

    public void play(){
        while(true){
            System.out.println(this.sudoku.toString());
            PlayerAction playerAction = this.playerAction();
            if(playerAction.getAction().equals(Action.FILLCELL)){
                this.sudoku.setValueAt(playerAction.getValue(), playerAction.getRow(),playerAction.getColumn());
            }
            else this.sudoku.emptyCell(playerAction.getRow(),playerAction.getColumn());
            boolean result = sudoku.result();
            if(result == true){
                break;
            }
        }
    }


}
