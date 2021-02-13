/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Riccardo
 */
public class PlayTable {
    private final int D1=3;
    private final int D2=9;
    private int dispBoxes[][]=new int[D2][D2];
    private boolean uncoveredBoxes[][]=new boolean[D2][D2];

    public PlayTable() {
        for(int i=0; i<D2; i++) {
            for(int j=0; j<D2; j++) {
                uncoveredBoxes[i][j]=false;
            }
        }
    }

    /*public int getDispBoxes(int r, int c) {
        if(uncoveredBoxes[r][c]) return dispBoxes[r][c];
        else return 0;
    }*/
    public int getDispBoxes(int r, int c) {
        return dispBoxes[r][c];
    }
    
    private boolean verifyColumn(int r, int c) {
        for(int i=0; i<D2; i++) {
            if(i!=r && dispBoxes[r][c]==dispBoxes[i][c]) return false;
        }
        return true;
    }
    
    private boolean verifyRow(int r, int c) {
        for(int i=0; i<D2; i++) {
            if(i!=c && dispBoxes[r][c]==dispBoxes[r][i]) return false;
        }
        return true;
    }
    
    private boolean verifySquare(int r, int c) {
        int r1, c1, i;
        for(i=r; i%3!=0; i--);
        r1=i;
        for(i=c; i%3!=0; i--);
        c1=i;
        for(int j=r1; j-r1<D1; j++) {
            for(int k=c1; k-c1<D1; k++) {
                if(dispBoxes[j][k]==dispBoxes[r][c] && (j!=r || k!=c)) return false;
            }
        }
        return true;
    }
    
    public void initialGeneration() {
        int nRepeats=4;
        for(int c=0; c<=nRepeats; c++) {
            switch(c) {
                case 0: {
                    for(int i=0; i<D2; i++) {
                        do {
                            dispBoxes[i][D2/2]=(int)(Math.random()*9+1);
                        } while(!verifyColumn(i,D2/2));
                    }
                    for(int i=0; i<D2; i++) {
                        if(i!=D2/2) {
                            do {
                                dispBoxes[D2/2][i]=(int)(Math.random()*9+1);
                            } while(!verifyRow(D2/2,i) || !verifySquare(D2/2,i));
                        }
                    }
                    break;
                }
                default: {
                    for(int i=0; i<D2; i++) {
                        if(dispBoxes[i][D2/2-c]!=0) {
                            do {
                                dispBoxes[i][D2/2-c]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(i,D2/2-c) || !verifyRow(i,D2/2-c) || !verifySquare(i,D2/2-c));
                        }
                    }
                    for(int i=0; i<D2; i++) {
                        if(dispBoxes[i][D2/2+c]!=0) {
                            do {
                                dispBoxes[i][D2/2+c]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(i,D2/2+c) || !verifyRow(i,D2/2+c) || !verifySquare(i,D2/2+c));
                        }
                    }
                    for(int i=0; i<D2; i++) {
                        if(dispBoxes[D2/2-c][i]!=0) {
                            do {
                                dispBoxes[D2/2-c][i]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(D2/2-c,i) || !verifyRow(D2/2-c,i) || !verifySquare(D2/2-c,i));
                        }
                    }
                    for(int i=0; i<D2; i++) {
                        if(dispBoxes[D2/2+c][i]!=0) {
                            do {
                                dispBoxes[D2/2+c][i]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(D2/2+c,i) || !verifyRow(D2/2+c,i) || !verifySquare(D2/2+c,i));
                        }
                    }
                }
            }
        }
    }
}