package Board;

import java.util.Arrays;

public class FEN {
    public static String board[][] = {
            //0   1   2   3   4   5   6   7
            {"r","k","b","q","a","b","k","r"},//0
            {"p","p","p","p","p","p","p","p"},//1
            {" "," "," "," "," "," "," "," "},//2
            {" "," "," "," "," "," "," "," "},//3
            {" "," "," "," "," "," "," "," "},//4
            {" "," "," "," "," "," "," "," "},//5
            {"P","P","P","P","P","P","P","P"},//6
            {"R","K","B","Q","A","B","K","R"}};//7

    	public static String board2[][] = {
		    //0   1   2   3   4   5   6   7
			{"r"," ","b","q","a","b","k","r"},//0
			{"p","P","p","p"," ","p","P","p"},//1
			{"P"," "," "," "," "," "," "," "},//2
			{"P"," "," ","p"," "," "," "," "},//3
			{"p"," "," ","P"," ","P","P"," "},//4
			{"p","p"," "," "," "," "," "," "},//5
			{"P","P","P","P","P"," ","p","P"},//6
			{"R","K","B","Q","A","B","K","R"}};//7


    public static void main(String[] args) {
        //Test af FEN print
        new FEN();
    }


    public FEN() {
        System.out.println("First test, start board");
        printFen(board, 1);
        System.out.println("Second test, random board");
        printFen(board2, 2);

    }

    public void printFen(String board[][], int playerTurn) {
        String str ="";
        int count;

        for (int i = 0; i < 8; i++) {
            count = 0;
            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals(" "))
                    count++;
                else {
                    if (count != 0) {
                        str+=count;
                        count = 0;
                    }
                    str+=board[i][j];
                }
            }
            if (count != 0)
                str+=count;
            if(i != 7)
                str+="/";
        }

        //Add hvis tur det er (w/b)
        if (playerTurn == 1)
            str+=" w";
        if (playerTurn == 2)
            str+=" b";

        //Noget castling ???    -> Also pretty easy. 'K' is for kingside castling for white, 'q' is for queenside castling for black. If there is none available, '-' is used.
        str+=" KQkq";

        //En passant ???    -> Usually not important, but you do show this by putting the target square for the capture. If there is none, place a '-'.
        str+=" -";

        //Half move mount ???    -> Pretty self explanatory. Number of half moves since a pawn or a piece was moved.
        str+=" 0";

        //Full move count ???    -> Even more self explanatory. It's the number that you would see in algebraic notation.
        str+=" 0";

        System.out.println(str);
    }

    public String continueFen() {
        return "";
    }



}
