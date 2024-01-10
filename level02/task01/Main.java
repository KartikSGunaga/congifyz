package task01;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinMaxTicTacToe playGame = new MinMaxTicTacToe();

        String[] board = {"0","1","2","3","4","5","6","7","8"};
        GameState state = new GameState(0, board);

        while (!playGame.checkGameEnd(state)) {
            board[playGame.selectOptimalMove(state)] = "X";

            if (!playGame.checkGameEnd(state)) {
                printBoard(state);
                System.out.println(": ");

                int user = Integer.parseInt(scanner.nextLine());
                state.changeState("O", user);
            }
        }
        printBoard(state);
        System.out.println("Game over!");

    }

    public static void printBoard(GameState state) {
        for (int i = 0; i < 7; i +=3) {
            System.out.println(state.positionIndex(i) + " "
                    + state.positionIndex(i + 1) + " " + state.positionIndex(i + 2));
        }
    }
}
