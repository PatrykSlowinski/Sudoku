package model;

public class Cell {
    private int value;
    private CellState cellstate;
    public Cell(){};

    public Cell(int value, CellState cellState) throws NumberOutOfRangeException{
        this.value = value;
        this.cellstate = cellState;
    }
    public int getValue() throws EmptyCellException {
        return this.value;
    }
    public void setValue(int value) throws NumberOutOfRangeException{
        this.value = value;
        this.cellstate = CellState.FILLED;

    }
    public CellState getState() { return this.cellstate; }
    public void empty() {
        this.cellstate = CellState.EMPTY;
        //this.value = 0;
    }
    public void fixValue(int value) throws NumberOutOfRangeException{
        this.cellstate = CellState.FIXED;
        this.value = value;
    }



}