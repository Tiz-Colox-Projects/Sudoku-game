package sudokugame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;


public class SudokuGUI extends JFrame{

    private JPanel mainPanel;
    private JLabel titleTextField;
    private JButton playButton;
    private JButton exitButton;
    private final String[] levels = {"Easy","Medium","Hard"};
    private JComboBox difficultyComboBox = new JComboBox(levels);
    private JButton submitButton;
    private JPanel gamePanel;
    private JButton newGameButton;
    private JFormattedTextField[][] grid;
    private final int DIM = 9;
    private SudokuBoard board;

    public SudokuGUI(String title) {

        super(title); //Initialize
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        Color fg = new Color(255, 255, 255); //Set background
        titleTextField.setForeground(fg);

        submitButton.setVisible(false);
        newGameButton.setVisible(false);

        grid = new JFormattedTextField[DIM][DIM];

        gamePanel.setLayout(new GridLayout(9,9,4,4));
        gamePanel.setVisible(false);

        //prepareDifficultyComboBox();

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

    private void clearBoard(){
        for(int y=0;y<9;y++){
            for(int x=0;x<9;x++){
                grid[y][x].setValue('\0');
            }
        }
    }

    private void initializeBoard(){
        clearBoard();
        int N = 9;
        int K = 20; //TODO: add input from combobox
        board = new SudokuBoard(N, K);
        board.fillValues();
        int temp;
        for(int y=0;y<DIM;y++){
            for(int x=0;x<DIM;x++){
                temp = board.getElement(x, y);
                if(temp != 0){
                    grid[y][x].setValue(temp);
                }
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
        /*ls.add("Easy");
        ls.add("Medium");
        ls.add("Hard");
        int c=0;
        for(int i=0; i<ls.size(); i++){
            System.out.println(ls.get(i));
            difficultyComboBox.add("Easy",c);
            difficultyComboBox.addItem(ls.indexOf(i));
        }
        //difficultyComboBox.setModel(ls.toArray());
        for(String value : ls){
            //difficultyComboBox.add();
        }*/ //TODO: fix levels menu
    }


    public static void main(String[] args) {
        //ImageIcon imgIcon=new ImageIcon(); TODO: set icon image
        //frame.setIconImage(imgIcon);
        JFrame frame = new SudokuGUI("Sudoku Game");
        frame.setVisible(true);
        Dimension minSize = new Dimension(800, 600);
        frame.setMinimumSize(minSize);
        frame.setResizable(false);
    }
}
