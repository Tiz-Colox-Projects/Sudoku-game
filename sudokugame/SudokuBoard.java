package sudokugame;

import java.util.Random;

public class SudokuBoard {

    public int[] board;
    private final int DIM = 81;

    public SudokuBoard(){
        board = new int[DIM];
    }
    //TEST
    public void genBoard(){
        Random rand = new Random();
        for(int i=0;i<DIM;i++){
            board[i] = 1 + rand.nextInt(9);
        }
    }
    //TEST
    public int getElement(int pos){
        return board[pos];
    }
}
