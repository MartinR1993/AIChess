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


//    public static void main(String[] args) {
//        //Test af FEN print
//        new FEN();
//    }

    public FEN() {
        System.out.println("First test, start board");
        printFen(board, 0,false);
        String board[][] = continueFen("rkbqabkr/pppppppp/8/8/8/8/PPPPPPPP/RKBQABKR - w");
        printFenToBoard(board);

        System.out.println("\n\n");
        
        System.out.println("Second test, random board");
        printFen(board2, 1,true);
        String board2[][] = continueFen("r1bqabkr/pPpp1pPp/P7/P2p4/p2P1PP1/pp6/PPPPP1pP/RKBQABKR - b");
        System.out.println(board2);
        printFenToBoard(board2);
    }

    public static void printFen(String board[][], int playerTurn, boolean asWhite) {
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

        //Add hvis tur det er (u/e)
        if (playerTurn == 0)
            str+=" u";
        if (playerTurn == 1)
            str+=" e";
        
//        if(asWhite){
//            str+=" x";
//        }else{
//            str+=" y";
//        }

        str+= ChessBoard.kingPositionC + " " + ChessBoard.kingPositionL;
        
//        //Noget castling ???    -> Also pretty easy. 'K' is for kingside castling for white, 'q' is for queenside castling for black. If there is none available, '-' is used.
//        str+=" KQkq";
//
//        //En passant ???    -> Usually not important, but you do show this by putting the target square for the capture. If there is none, place a '-'.
//        str+=" -";
//
//        //Half move mount ???    -> Pretty self explanatory. Number of half moves since a pawn or a piece was moved.
//        str+=" 0";
//
//        //Full move count ???    -> Even more self explanatory. It's the number that you would see in algebraic notation.
//        str+=" 0";

        System.out.println(str);
    }

    public static String[][] continueFen(String boardString) {
    	String newBoard[][] = new String[8][8];
    	boardString = boardString.substring(0, boardString.length());

    	//Lav 1 om til 0, 2 om til 00;

    	//Fjern /
    	String str = "";

    	for (int i = 0; i<boardString.length(); i++) {    		
    		if (Character.isDigit(boardString.charAt(i))) {

    			String number = boardString.substring(i, i+1);

    			try {
    				int num = Integer.parseInt(number);
    				for (int j = 0; j<num; j++) {
    					str+=" ";
    				}
    			} catch (NumberFormatException e) {}
    		}
    		else {
    			if (!boardString.substring(i, i+1).equals("/")){
    				str+=boardString.substring(i, i+1);
    			}
    		}

    	}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (str.charAt(i+(j*8)) == '0') {
					newBoard[j][i]=" ";
				}else{
					newBoard[j][i]=str.charAt(i+(j*8))+"";   
				}
			}
		}
        return newBoard;
    }
    
    
    public void printFenToBoard (String[][] board) {
    	int temp = 8;
    	System.out.println("\nThe Board: \n  a  b  c  d  e  f  g  h");
    	for (int i = 0; i < 8; i++) {            
    		System.out.println(temp + Arrays.toString(board[i]) + temp);
    		temp--;
    	}
    	System.out.println("  a  b  c  d  e  f  g  h");
    }
}