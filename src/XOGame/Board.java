package XOGame;

public interface Board {
    public void create_board();
    public void display_board();
    public boolean update(int row, int col, char c);
    public boolean is_winner(char c);
    public boolean is_draw();

}
