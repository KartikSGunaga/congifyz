package task01;

import java.util.ArrayList;

public class MinMaxTicTacToe {

    public boolean checkGameEnd(GameState state) {
        int filledSpots = 0;

        for (int i = 0; i < 9; i++) {
            if (state.positionIndex(i).equals('X') || state.positionIndex(i).equals('O')) {
                filledSpots++;
            }

            if (filledSpots == 9)
                return true;

            String seq = evaluateCombination(state, i);

            if (seq.equals("XXX"))
                return true;
            else if (seq.equals("OOO"))
                return true;
        }
        return false;
    }

    private ArrayList<GameState> computePossibleMoves(GameState state) {
        ArrayList<GameState> possibleFills = new ArrayList<>();
        int human = 0, cpu = 0;
        String player;

        // Determine who's play:
        for(String pos : state.getState()) {
            if (pos.equals("X"))
                human++;
            else if (pos.equals("O"))
                cpu++;
        }

        if (human <= cpu)
            player = "X";
        else
            player = "O";

        for (int i = 0; i < 9; i++){
            String[] dummyState = state.getState().clone();

            if (dummyState[i] != "X" || dummyState[i] != "O") {
                dummyState[i] = player;
                possibleFills.add(new GameState(i, dummyState));
            }
        }
        return possibleFills;
    }

    private String evaluateCombination(GameState state, int pos) {
        switch (pos) {
            case 0:
                return state.positionIndex(0) + state.positionIndex(1) + state.positionIndex(2);
            case 1:
                return state.positionIndex(3) + state.positionIndex(4) + state.positionIndex(5);
            case 2:
                return state.positionIndex(6) + state.positionIndex(7) + state.positionIndex(8);
            case 3:
                return state.positionIndex(0) + state.positionIndex(3) + state.positionIndex(6);
            case 4:
                return state.positionIndex(1) + state.positionIndex(4) + state.positionIndex(7);
            case 5:
                return state.positionIndex(2) + state.positionIndex(5) + state.positionIndex(8);
            case 6:
                return state.positionIndex(0) + state.positionIndex(4) + state.positionIndex(8);
            case 7:
                return state.positionIndex(2) + state.positionIndex(4) + state.positionIndex(6);
            default:
                return "";
        }
    }

    private int calculateScore(GameState state) {
        for (int i = 0; i < 9; i++) {
            String seq = evaluateCombination(state, i);

            if (seq.equals("XXX")) {
                return -1;
            } else if (seq.equals("OOO")) {
                return 1;
            }
        }
        return 0;
    }

    public int selectOptimalMove(GameState state) {
        ArrayList<GameState> possibleFills = computePossibleMoves(state);
        ArrayList<Integer> fills = new ArrayList<>();

        for (GameState pos : possibleFills) {
            fills.add(findMaxValue(pos));
        }

        int min = fills.get(0);
        int optimalIndex = 0;

        for (int i = 1; i < fills.size(); i++) {
            if (fills.get(i) > min) {
                min = fills.get(i);
                optimalIndex = min;
            }
        }

        System.out.println(possibleFills);
        System.out.println(fills);

        int play = possibleFills.get(optimalIndex).getPosition();
        System.out.println("Optimal play: " + play);
        return play;

    }

    private int findMinValue(GameState state) {
        if (checkGameEnd(state)) {
            return calculateScore(state);
        }
        int minimum = (int) Double.POSITIVE_INFINITY;
        for (GameState possibleFill: computePossibleMoves(state)) {
            minimum = Math.min(minimum, findMaxValue(possibleFill));
        }
        System.out.println(minimum);
        return minimum;
    }

    private int findMaxValue(GameState state) {
        if (checkGameEnd(state)) {
            return calculateScore(state);
        }
        int maximum = (int) -Double.POSITIVE_INFINITY;
        for (GameState possibleFill: computePossibleMoves(state)) {
            maximum = Math.max(maximum, findMinValue(possibleFill));
        }
        System.out.println(maximum);
        return maximum;
    }

}
