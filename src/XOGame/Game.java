package XOGame;
import java.util.Scanner;

public class Game implements Board, Player {
    // attributes
    private int size;
    private int turn;
    String name;
    char symbol;
    private char[][] grid;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // methods
    Game(int size) {
        this.size = size;
        this.turn = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void create_board() {
        grid = new char[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                grid[i][j] = '.';
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void display_board() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.print('\n');
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean update(int row, int col, char c) {
        if (row <= size && row > 0 && col <= size && col > 0 && grid[row - 1][col - 1] == '.') {
            grid[row - 1][col - 1] = c;
            return true;
        } else {
            return false;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean is_winner(char c) {
        int cnt = 0;
        // check horizontal
        for (int i = 0; i < size; ++i) {
            cnt = 0;
            for (int j = 0; j < size; ++j) {
                if (grid[i][j] == c) cnt++;
            }
            if (cnt == size) return true;
        }
        // check vertical
        for (int j = 0; j < size; ++j) {
            cnt = 0;
            for (int i = 0; i < size; ++i) {
                if (grid[i][j] == c) cnt++;
            }
            if (cnt == size) return true;
        }
        // check diagonal
        // from left to right
        cnt = 0;
        for (int i = 0; i < size; ++i) {
            if (grid[i][i] == c) cnt++;
        }
        if (cnt == size) return true;
        // from right to left
        cnt = 0;
        for (int i = 0; i < size; ++i) {
            if (grid[i][size - 1 - i] == c) cnt++;
        }
        if (cnt == size) return true;

        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean is_draw() {
        int cnt = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (grid[i][j] != '.') cnt++;
            }
        }
        if (cnt == size * size) return true;
        else return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void create_player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String get_name() {
        return name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public char get_symbol() {
        return symbol;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void play_game() {
        Scanner input = new Scanner(System.in);
        create_board();
        Player[] players = new Game[2];
        for (int i = 0; i < 2; ++i) {
            players[i] = new Game(size);
            System.out.println("Player " + (i + 1) + ": Enter your name");
            String name = input.next();
            System.out.println("Player " + (i + 1) + " Enter your symbol");
            char c = input.next().charAt(0);
            players[i].create_player(name, c);
        }
        int row, col;
        while (true) {
            turn++;
            display_board();
            if (turn % 2 != 0) {
                System.out.println(players[0].get_name() + " role:");
                System.out.println("Enter values of row and column:");
                row = input.nextInt();
                col = input.nextInt();
                while (!update(row, col, players[0].get_symbol())) {
                    System.out.println("Invalid position, try again.");
                    System.out.println("Enter values of row and column:");
                    display_board();
                    row = input.nextInt();
                    col = input.nextInt();
                }
                if (is_winner(players[0].get_symbol())) {
                    System.out.println(players[0].get_name() + " Wins!");
                    break;
                }
            }
            else {
                System.out.println(players[1].get_name() + " role:");
                System.out.println("Enter values of row and column:");
                row = input.nextInt();
                col = input.nextInt();
                while (!update(row, col, players[1].get_symbol())) {
                    System.out.println("Invalid position, try again.");
                    System.out.println("Enter values of row and column:");
                    display_board();
                    row = input.nextInt();
                    col = input.nextInt();
                }
                if (is_winner(players[1].get_symbol())) {
                    System.out.println(players[1].get_name() + " Wins!");
                    break;
                }
            }
            if (is_draw()) {
                System.out.println("Draw!");
                break;
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
