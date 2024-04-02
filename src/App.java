import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerS = "S";
    String playerO = "O";
    String currentPlayer = playerS;
    boolean gameOver = false;
    int turns = 0;

    App() {


        frame.setVisible(true);
        frame.setTitle("Tic-Tac-Toe");
        frame.setSize(600, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(true);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerS ? playerO : playerS;
                                textLabel.setText(currentPlayer + " 's turn");
                            }


                        }


                    }
                });


            }

        }
    }

    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "") continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                 setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }


            if (board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "") {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;

            }
            if (board[0][2].getText() == board[1][1].getText() &&
                    board[1][1].getText() == board[2][0].getText() &&
                    board[0][2].getText() != "") {
                    setWinner(board[0][2]);
                    setWinner(board[1][1]);
                    setWinner(board[2][0]);

                gameOver = true;
                return;
            }

            if (turns == 9){
                for (int r = 0; r < 3; r++){
                    for (c = 0; c < 3; c++){
                        setTie(board[r][c]);
                    }
                }
                gameOver = true;
            }
        }

        }

    private void setTie(JButton tile) {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.orange);
        textLabel.setText("Tie !");
    }

    private void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winnwr !");
    }


    public static void main(String[] args) {
        new App();
    }
}


