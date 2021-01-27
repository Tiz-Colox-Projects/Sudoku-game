package sudokugame;

import javax.swing.*;

public class SudokuGUI extends JFrame{
    private JPanel mainPanel;

    public SudokuGUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

    }

    public static void main(String[] args){
        JFrame frame= new SudokuGUI("Sudoku Game");
        frame.setVisible(true);
    }
}
