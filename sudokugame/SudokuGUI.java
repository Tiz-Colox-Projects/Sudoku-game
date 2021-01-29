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
    private JTextField[] test;

    public SudokuGUI(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        Color fg = new Color(255, 255, 255);
        titleTextField.setForeground(fg);
        submitButton.setVisible(false);
        test=new JTextField[9]; //TODO: organizza gestione campo
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setVisible(false);
                difficultyComboBox.setVisible(false);
                submitButton.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new SudokuGUI("Sudoku Game");
        frame.setVisible(true);
        Dimension minSize = new Dimension(800, 600);
        //ImageIcon imgIcon=new ImageIcon();
        //frame.setIconImage(imgIcon);
        frame.setMinimumSize(minSize);
        frame.setResizable(false);

    }
}
