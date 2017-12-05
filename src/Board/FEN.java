package Board;

public class FEN {
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

        str+= " " + ChessBoard.kingPositionC + " " + ChessBoard.kingPositionL;
        
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
}