package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SudokuGrid {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";
    char c = '\u2551';
    private Cell[][] grid = new Cell[9][9];

    public SudokuGrid() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.grid[i][j] = new Cell();
                this.grid[i][j].empty();

            }
        }
        /*this.grid[3][4].setValue(4);
        this.grid[8][8].setValue(6);
        this.grid[7][7].setValue(4);
        this.grid[6][7].setValue(5);
        this.grid[7][6].setValue(4);*/
    }

    public int getValueAt(int x, int y) throws NumberOutOfRangeException, IndexOutOfBoundsException {
        if (x >= 0 && 9 > x) {
            if (y >= 0 && 9 > y) {
                return this.grid[x][y].getValue();
            } else {
                throw new IndexOutOfBoundsException("Invalid value for column " + y);
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid value for row " + x);
        }
    }

    public void setValueAt(int num, int x, int y) throws NumberOutOfRangeException, IndexOutOfBoundsException {
        if (x >= 0 && 9 > x) {
            if (y >= 0 && 9 > y) {
                if (this.grid[x][y].getState() == CellState.FIXED) {
                    System.out.println("The value of this cell is already fixed. Choose another cell");
                } else {
                    this.grid[x][y].setValue(num);
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid value for column " + y);
            }

        } else {
            throw new IndexOutOfBoundsException("Invalid value for row " + x);
        }
    }

    public void fixValueAt(int num, int x, int y) throws NumberOutOfRangeException, IndexOutOfBoundsException {
        if (x >= 0 && 9 > x) {
            if (y >= 0 && 9 > y) {
                if (this.grid[x][y].getState() == CellState.FIXED) {
                    System.out.println("The value of this cell is already fixed. Choose another cell");
                } else {
                    this.grid[x][y].fixValue(num);
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid value for column " + y);
            }

        } else {
            throw new IndexOutOfBoundsException("Invalid value for row " + x);
        }
    }

    public void emptyCell(int x, int y) throws NumberOutOfRangeException, IndexOutOfBoundsException {
        if (x >= 0 && 9 > x) {
            if (y >= 0 && 9 > y) {
                if (this.grid[x][y].getState() == CellState.FIXED) {
                    System.out.println("The value of this cell is already fixed. Choose another cell");
                } else {
                    this.grid[x][y].empty();
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid value for column " + y);
            }

        } else {
            throw new IndexOutOfBoundsException("Invalid value for row " + x);
        }
    }

    public CellState cellState(int x, int y) throws NumberOutOfRangeException, IndexOutOfBoundsException {
        if (x >= 0 && 9 > x) {
            if (y >= 0 && 9 > y) {
                return this.grid[x][y].getState();
            } else {
                throw new IndexOutOfBoundsException("Invalid value for column " + y);
            }
        } else {
            throw new IndexOutOfBoundsException("Invalid value for row " + x);
        }
    }

    public String toString() {

        StringBuilder sudokuStringBuilder = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0) {
                    if (j == 0) {
                        sudokuStringBuilder.append("   ");
                    }
                    sudokuStringBuilder.append(j + 1 + " | ");
                }
            }
            if (i == 0) sudokuStringBuilder.append("\n");
            sudokuStringBuilder.append(" ");
            sudokuStringBuilder.append(i % 3 == 0 ? "=" : "-");
            for (int j = 0; j < 9; ++j) {

                sudokuStringBuilder.append(i % 3 == 0 ? "====" : "----");
            }
            sudokuStringBuilder.append('\n');
            String color = new String();


            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j].getState() == CellState.FIXED) color = ANSI_BLUE;
                else if (this.grid[i][j].getState() == CellState.FILLED) {
                    if (this.repeated(i, j) == false) color = ANSI_BLACK;
                    else color = ANSI_RED;
                }
                if (j == 0) {
                    sudokuStringBuilder.append(i + 1);
                }
                sudokuStringBuilder.append(j % 3 == 0 ? c + " " : "| ");
                sudokuStringBuilder.append(this.grid[i][j].getState() == CellState.EMPTY ? " " : color + this.grid[i][j].getValue() + ANSI_RESET);
                sudokuStringBuilder.append(" ");
            }

            sudokuStringBuilder.append(c + "\n");
        }

        sudokuStringBuilder.append(" =");
        for (int j = 0; j < 9; j++) {
            sudokuStringBuilder.append("====");
        }

        return sudokuStringBuilder.toString();
    }

    public boolean isFull() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j].getState() == CellState.EMPTY) return false;
            }
        }
        return true;
    }

    protected int timesInSector(int num, int xmin, int ymin, int xmax, int ymax) {
        int count = 0;
        for (int i = xmin; i <= xmax; ++i) {
            for (int j = ymin; j <= ymax; ++j) {
                if (this.grid[i][j].getValue() == num) count++;
            }
        }
        return count;
    }

    public boolean repeated(int row, int column) {
        if (this.grid[row][column].getState() != CellState.EMPTY) {
            int num = this.grid[row][column].getValue();
            if ((this.timesInSector(num, row / 3 * 3, column / 3 * 3, row / 3 * 3 + 2, column / 3 * 3 + 2) > 1) || (this.timesInSector(num, row, 0, row, 8) > 1) || (this.timesInSector(num, 0, column, 8, column) > 1)) {
                return true;
            }
        }

        return false;
    }

    public boolean isValid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid[i][j].getState() != CellState.EMPTY) {
                    if ((this.timesInSector(this.grid[i][j].getValue(), i, 0, i, 8) > 1) || (this.timesInSector(this.grid[i][j].getValue(), 0, j, 8, j) > 1) || this.repeated(i, j) == true) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    public void sudokuInitializer(Path path) throws IOException, SudokuInitializerException {
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] data = line.split("\\s*[,]\\s*");
            if (data.length < 3) {
                throw new SudokuInitializerException("Error: Insuficient data from the file");
            }
            try {
                this.grid[(Integer.parseInt(data[0])) - 1][(Integer.parseInt(data[1])) - 1].fixValue(Integer.parseInt(data[2]));
            } catch (NumberFormatException e) {
                throw new SudokuInitializerException("Error: Incorrect type of initial values from the file");
            }
            for (String s : data) {
                if (Integer.parseInt(s) < 1 || Integer.parseInt(s) > 9) {
                    throw new SudokuInitializerException(" Error: Values out of index. Initial values have to be of range: [1,9]");
                }
            }


        }
    }

    public boolean result() {
        if (this.isFull()) {
            if (this.isValid()) {
                System.out.println(this.toString());
                System.out.println("Congratulations. You won the game.");
                return true;
            } else {
                System.out.println(this.toString());
                System.out.println("Sorry. You lost the game :(.");
                return true;
            }
        }
        return false;
    }

}
