package sudokugame;

import java.lang.*;

//                                          https://www.geeksforgeeks.org/program-sudoku-generator/
/*
        Background:

        Following are the rules of Suduku for a player.

        In all 9 sub matrices 3×3 the elements should be 1-9, without repetition.
        In all rows there should be elements between 1-9 , without repetition.
        In all columns there should be elements between 1-9 , without repetition.

        The task is to generate a 9 x 9 Suduku grid that is valid, i.e., a player can fill the grid following above set of rules.

        A simple naive solution can be.

        Randomly take any number 1-9.
        Check if it is safe to put in the cell.(row , column and box)
        If safe, place it and increment to next location and go to step 1.
        If not safe, then without incrementing go to step 1.
        Once matrix is fully filled, remove k no. of elements randomly to complete game.
*/


public class SudokuBoard {

    int[] mat[];
    int[] backup[];
    int N; // number of columns/rows.
    int SRN; // square root of N
    int K; // No. Of missing digits

    // Constructor
    public SudokuBoard(int N, int K) {

        this.N = N;
        this.K = K;

        // Compute square root of N
        Double SRNd = Math.sqrt(N);
        SRN = SRNd.intValue();

        mat = new int[N][N];
        backup = new int[N][N];

    }

    // Sudoku Generator
    public void fillValues()
    {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SRN);

        // Copy mat to check when submit
        copyMat();

        // Remove Randomly K digits to make game
        removeKDigits();
    }

    public boolean checkCorrect(){
        for(int i=0; i<N; i++){
            for(int c=0; c<N; c++){
                if(mat[i][c] != backup[i][c]) return false;
            }
        }
        return true;
    }

    private void copyMat(){
        for(int i=0; i<N; i++){
            for(int c=0; c<N; c++){
                backup[i][c] = mat[i][c];
            }
        }
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal()
    {

        for (int i = 0; i<N; i=i+SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<SRN; i++)
            for (int j = 0; j<SRN; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<SRN; i++)
        {
            for (int j=0; j<SRN; j++)
            {
                do
                {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));

                mat[row+i][col+j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%SRN, j-j%SRN, num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<N; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<N; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j>=N && i<N-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=N && j>=N)
            return true;

        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N-SRN)
        {
            if (j==(int)(i/SRN)*SRN)
                j =  j + SRN;
        }
        else
        {
            if (j == N-SRN)
            {
                i = i + 1;
                j = 0;
                if (i>=N)
                    return true;
            }
        }

        for (int num = 1; num<=N; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                mat[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;

                mat[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    public void removeKDigits()
    {
        int count = K;
        while (count != 0)
        {
            int cellId = randomGenerator(N*N);

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId/N);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (mat[i][j] != 0)
            {
                count--;
                mat[i][j] = 0;
            }
        }
    }

    public int getElement(int x, int y){
        return mat[y][x];
    }

    //TEST
    public void print(){
        for(int i=0; i<N; i++){
            for(int c=0; c<N; c++){
                System.out.print(backup[i][c] + " ");
            }
            System.out.print("\n");
        }
    }
}
