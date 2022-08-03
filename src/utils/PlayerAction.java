package utils;

public class PlayerAction {
    private int row;
    private int column;
    private int value;
    private Action action;

    public PlayerAction(int row, int column, int value, Action action){
        this.row = row;
        this.column = column;
        this.value = value;
        this.action = action;
    }

    public int getRow(){return this.row;}
    public int getColumn(){return this.column;}
    public int getValue(){return this.value;}
    public Action getAction(){return this.action;}
}
