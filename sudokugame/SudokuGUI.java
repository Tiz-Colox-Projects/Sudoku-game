package sudokugame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Vector;

import static javax.swing.JOptionPane.showMessageDialog;


public class SudokuGUI extends JFrame{

    private JPanel mainPanel;
    private JLabel titleTextField;
    private JButton playButton;
    private JButton exitButton;
    private JComboBox difficultyComboBox;
    private JButton submitButton;
    private JPanel gamePanel;
    private JButton newGameButton;
    private Container c = getContentPane();
    private JFormattedTextField[][] grid;
    private final int DIM = 9;
    private SudokuBoard board;

    public SudokuGUI(String title) {

        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        Color fg = new Color(255, 255, 255);
        titleTextField.setForeground(fg);

        board = new SudokuBoard();

        submitButton.setVisible(false);
        newGameButton.setVisible(false);

        grid = new JFormattedTextField[DIM][DIM];

        gamePanel.setLayout(new GridLayout(9,9,4,4));
        gamePanel.setVisible(false);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                titleTextField.setVisible(false);
                playButton.setVisible(false);
                difficultyComboBox.setVisible(false);
                submitButton.setVisible(true);
                prepareBoard();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameButton.setVisible(true);
                //TODO: add control
                setBoxesEditable(false);
                if(true){
                    showMessageDialog(null, "Correct :)");
                }else{
                    showMessageDialog(null, "Wrong :(");
                }
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameButton.setVisible(false);
                setBoxesEditable(true);
                initializeBoard();
            }
        });
    }

    private void initializeBoard(){
        board.genBoard();
        for(int y=0;y<DIM;y++){
            for(int x=0;x<DIM;x++){
                grid[y][x].setValue(board.getElement(y+x));
            }
        }
    }

    private void prepareBoard(){
        gamePanel.setVisible(true);
        Font boxFont = new Font("", Font.BOLD,25);
        for(int y=0;y<DIM;y++) {
            for (int x = 0; x < DIM; x++) {
                grid[y][x] = new JFormattedTextField();
                grid[y][x].setFont(boxFont);
                gamePanel.add(grid[y][x],y,x);
                grid[y][x].setHorizontalAlignment(JTextField.CENTER);
            }
        }
        initializeBoard();
    }

    private void setBoxesEditable(boolean is){
        for(int i=0;i<DIM;i++){
            for(int c=0;c<DIM;c++)
                grid[i][c].setEditable(is);
        }
    }

    private void prepareDifficultyComboBox(){
        Vector<String> difficulty = new Vector<String>();
        difficulty.add("Easy");
        difficulty.add("Medium");
        difficulty.add("Hard");
        for(String value : difficulty){
            //difficultyComboBox.add(value);
        } //TODO: fix
    }


    public static void main(String[] args) {
        //ImageIcon imgIcon=new ImageIcon();
        //frame.setIconImage(imgIcon);
        JFrame frame = new SudokuGUI("Sudoku Game");
        frame.setVisible(true);
        Dimension minSize = new Dimension(800, 600);
        frame.setMinimumSize(minSize);
        frame.setResizable(false);
    }
}
