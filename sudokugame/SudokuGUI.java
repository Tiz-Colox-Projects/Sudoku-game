package sudokugame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SudokuGUI extends JFrame{

    private JPanel mainPanel;
    private JLabel titleTextField;
    private JButton playButton;
    private JButton exitButton;
    private JComboBox difficultyComboBox;
    private JButton submitButton;
    private JPanel gamePanel;
    private Container c = getContentPane();
    private JTextField[][] grid;
    private final int DIM = 9;

    public SudokuGUI(String title) {

        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        Color fg = new Color(255, 255, 255);
        titleTextField.setForeground(fg);


        submitButton.setVisible(false);

        grid = new JTextField[DIM][DIM];

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
                if(true){
                    //Insert winner message
                }else{
                    //Insert loser message
                }
            }
        });
    }

    private void prepareBoard(){
        gamePanel.setVisible(true);
        Font boxFont = new Font("", Font.BOLD,25);
        for(int y=0;y<DIM;y++) {
            for (int x = 0; x < DIM; x++) {
                grid[y][x] = new JTextField();
                grid[y][x].setFont(boxFont);
                gamePanel.add(grid[y][x],y,x);
                grid[y][x].setHorizontalAlignment(JTextField.CENTER);
            }
        }
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
