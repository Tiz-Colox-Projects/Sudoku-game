/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokugame;

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
    
    /*public void initialGeneration() {
        int nRepeats=1;
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
                        if(dispBoxes[i][D2/2-c]==0) {
                            do {
                                dispBoxes[i][D2/2-c]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(i,D2/2-c) || !verifyRow(i,D2/2-c) || !verifySquare(i,D2/2-c));
                        }
                    }
                    for(int i=0; i<D2; i++) {
                        if(dispBoxes[i][D2/2+c]==0) {
                            do {
                                dispBoxes[i][D2/2+c]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(i,D2/2+c) || !verifyRow(i,D2/2+c) || !verifySquare(i,D2/2+c));
                        }
                    }
                    for(int i=0; i<D2; i++) {
                        if(dispBoxes[D2/2-c][i]==0) {
                            do {
                                dispBoxes[D2/2-c][i]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(D2/2-c,i) || !verifyRow(D2/2-c,i) || !verifySquare(D2/2-c,i));
                        }
                    }
                    for(int i=0; i<D2; i++) {
                        if(dispBoxes[D2/2+c][i]==0) {
                            do {
                                dispBoxes[D2/2+c][i]=(int)(Math.random()*9+1);
                            } while(!verifyColumn(D2/2+c,i) || !verifyRow(D2/2+c,i) || !verifySquare(D2/2+c,i));
                        }
                    }
                }
            }
        }
    }*/
    
    private int getNInBox(int r, int c) {
        int r1, c1, i;
        for(i=r; i%3!=0; i--);
        r1=i;
        for(i=c; i%3!=0; i--);
        c1=i;
        i=0;
        for(int j=r1; j-r1<D1; j++) {
            for(int k=c1; k-c1<D1; k++) {
                if(dispBoxes[j][k]!=0) i++;
            }
        }
        return i;
    }
    
    private int getNInRow(int r) {
        int i=0;
        for(int j=0; j<D2; j++) {
            if(dispBoxes[r][j]!=0) i++;
        }
        return i;
    }
    
    private int getNInCol(int c) {
        int i=0;
        for(int j=0; j<D2; j++) {
            if(dispBoxes[j][c]!=0) i++;
        }
        return i;
    }
    
    public void initialGeneration(int n) {
        int r1;
        int c1;
        int r2;
        int c2;
        for(int i=0; i<n; i++) {
            do {
                r1=(int)(Math.random()*9);
                c1=(int)(Math.random()*9);
            } while(dispBoxes[r1][c1]!=0 || getNInBox(r1,c1)==(n*4)/9 || getNInRow(r1)>=(n*6)/9 || getNInCol(c1)>=(n*6)/9);
            r2=8-r1;
            c2=8-c1;
            do {
                dispBoxes[r1][c1]=(int)(Math.random()*9)+1;
            } while(!verifyColumn(r1,c1) || !verifyRow(r1,c1) || !verifySquare(r1,c1));
            do {
                dispBoxes[r1][c2]=(int)(Math.random()*9)+1;
            } while(!verifyColumn(r1,c2) || !verifyRow(r1,c2) || !verifySquare(r1,c2));
            do {
                dispBoxes[r2][c2]=(int)(Math.random()*9)+1;
            } while(!verifyColumn(r2,c2) || !verifyRow(r2,c2) || !verifySquare(r2,c2));
            do {
                dispBoxes[r2][c1]=(int)(Math.random()*9)+1;
            } while(!verifyColumn(r2,c1) || !verifyRow(r2,c1) || !verifySquare(r2,c1));
        }
    }
}