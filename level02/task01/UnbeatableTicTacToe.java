package task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UnbeatableTicTacToe {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Integer> emptyPositions = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

    static ArrayList<Integer> playerPositions = new ArrayList<>();

    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    // Winning combinations
    static List<List<Integer>> winCombos = Arrays.asList(
            Arrays.asList(0, 1, 2),
            Arrays.asList(3, 4, 5),
            Arrays.asList(6, 7, 8),
            Arrays.asList(0, 3, 6),
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8),
            Arrays.asList(0, 4, 8),
            Arrays.asList(6, 4, 2)
    );

    char[] gameBoard = {'-', '-', '-', '-', '-', '-', '-', '-', '-'};

    // Print game board
    public void printBoard() {
        System.out.println("\nCurrent board:");

        for(int i=0; i<gameBoard.length; i++) {
            System.out.print(gameBoard[i]);

            if((i+1) % 3 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }

    // Check for winner or tie
    public int checkWin() {
        for(List<Integer> l : winCombos) {
            if(playerPositions.containsAll(l)) {
                return 10;
            } else if(cpuPositions.containsAll(l)) {
                return -10;
            }
        }

        if(emptyPositions.isEmpty()) {
            return 0;
        }

        return -1;
    }

    // Place player move
    public void placeMove(String player) {
        int movePos;
        if(player.equals("human")) {
            movePos = getPlayerMove();
            playerPositions.add(movePos);
            gameBoard[movePos] = 'X';
            emptyPositions.remove(Integer.valueOf(movePos));

        } else if(player.equals("cpu")) {
            movePos = 1; //TODO: Start here
            cpuPositions.add(movePos);
            gameBoard[movePos] = 'O';
            emptyPositions.remove(Integer.valueOf(movePos));
        }
    }

//    public int miniMax(char[] newBoard, String player) {
//        String huPlayer = "player", aiPlayer = "cpu";
//
//        ArrayList<Integer> availSpots = emptyPositions(newBoard);
//
//        int score = checkWin(newBoard, huPlayer) ? -10 : (checkWin(newBoard, aiPlayer) ? 20 : (availSpots.isEmpty() ? 0 : 0));
//
//        if (score == -10 || score == 20 || score == 0) {
//            return score;
//        }
//
//        ArrayList<Integer> moves = new ArrayList<>();
//        for (int i = 0; i < availSpots.size(); i++) {
//            int move = availSpots.get(i);
//            newBoard[move] = (player.equals(aiPlayer)) ? 'X' : 'O';
//
//            if (player.equals(aiPlayer)) {
//                int result = miniMax(newBoard, huPlayer);
//                moves.add(result);
//            } else {
//                int result = miniMax(newBoard, aiPlayer);
//                moves.add(result);
//            }
//
//            newBoard[move] = '-';
//        }
//
//        int bestMove = -1;
//        int bestScore = (player.equals(aiPlayer)) ? -10000 : 10000;
//
//        for (int i = 0; i < moves.size(); i++) {
//            int currentScore = moves.get(i);
//
//            if ((player.equals(aiPlayer) && currentScore > bestScore) ||
//                    (player.equals(huPlayer) && currentScore < bestScore)) {
//                bestScore = currentScore;
//                bestMove = i;
//            }
//        }
//
//        return bestMove;
//    }


    public int getPlayerMove() {
        int movePos;
        while(true) {
            System.out.print("\nEnter move pos: ");
            movePos = scanner.nextInt();

            if(!emptyPositions.contains(movePos)) {
                System.out.println(movePos + " is occupied!");
                continue;
            }
            break;
        }

        return movePos;
    }

}