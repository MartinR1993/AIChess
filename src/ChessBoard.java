
public class ChessBoard {

    String[][] board;
    String emptyField = " #";
    String king = " K"; //Konge
    String queen = " Q"; //Dronning
    String pawn = " P"; //Bonde
    String bishop = " B"; //Løber
    String knight = " Kn"; //Springer
    String rook = " R"; //Tårn

    public static void main(String[] args) {
        new ChessBoard();
    }

    public ChessBoard() {
        buildBoard();
        printBoard();

    }

    public void buildBoard() {
        board = new String[8][8];
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                board[i][j] = emptyField;
            }
        }
        //Placer brikker her:
        board[1][2] = king;
    }


    public void printBoard() {
        for(int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }








}
