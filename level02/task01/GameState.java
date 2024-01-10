package task01;

public class GameState {
    private String[] state;
    private int position;

    public GameState(int position, String[] state) {
        this.position = position;
        this.state = state;
    }

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void changeState(String player, int pos) {
        state[pos] = player;
    }
    public String positionIndex(int i){
        return state[i];
    }

}
