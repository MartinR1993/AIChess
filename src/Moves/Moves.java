package Moves;
import Board.ChessBoard;
import Rating.Rating;

public class Moves {
    
    public static void doAMove(String move) {
        //regular moves
        if(move.charAt(5) != 'P'){
            //string : oldXposition(0),oldYposition(1),newXpostition(2),newYposition(3), Captured-piece(4), Space(5)
            ChessBoard.board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = ChessBoard.board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
            ChessBoard.board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
            if("A".equals(ChessBoard.board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
                ChessBoard.kingPositionC = 8*Character.getNumericValue(move.charAt(2))+Character.getNumericValue(move.charAt(3));
            }
        }
        //pawn promotion
        else{
            //string : oldXposition(0),oldYposition(1),newXpostition(2),newYposition(3), Captured-piece(4), "P"(5)
            ChessBoard.board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = "Q";
            ChessBoard.board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
        }
    }
    
    public static void undoAMove(String move) {
        //regular moves
        if(move.charAt(5) != 'P'){
            //string : oldXposition(0),oldYposition(1),newXpostition(2),newYposition(3),Captured-piece(4), Space(5)
            ChessBoard.board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = ChessBoard.board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
            ChessBoard.board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = String.valueOf(move.charAt(4));
            if("A".equals(ChessBoard.board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
                ChessBoard.kingPositionC = 8*Character.getNumericValue(move.charAt(0))+Character.getNumericValue(move.charAt(1));
            }
        }
        //pawn promotion
        else{
            //string : oldXposition(0),oldYposition(1),newXpostition(2),newYposition(3), Captured-piece(4), "P"(5)
            ChessBoard.board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = "P";
            ChessBoard.board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = String.valueOf(move.charAt(4));
        }
    }
    
    //Check with the possibleMove list
    public static boolean validMove(String move){
        String possibleMoves = possibleMoves();
        for (int i = 0; i < possibleMoves.length(); i+=6) {
            if(move.equals(possibleMoves.substring(i, i+6))){
                return true;
            }
        }
        return false;
    }
    
    
    //finds all possible moves and add them to a string
    public static String possibleMoves(){
        String list = "";
        for (int i = 0; i < 64; i++) {
            switch (ChessBoard.board[i/8][i%8]){
                case "Q" : list += possibleQ(i);
                break;
                case "K" : list += possibleK(i);
                break;
                case "B" : list += possibleB(i);
                break;
                case "R" : list += possibleR(i);
                break;
                case "P" : list += possibleP(i);
                break;
                case "A" : list += possibleA(i);
                break;
            }
        }
        return list;
    }
    //Kings possible moves
    public static String possibleA(int i) {
        String list = "";
        String oldPiece;
        int row = i/8;
        int col = i%8;
        for (int j = 0; j < 9; j++) {
            if(j != 4) {
                try {
                    if(Character.isLowerCase(ChessBoard.board[row-1+j/3][col-1+j%3].charAt(0)) || " ".equals(ChessBoard.board[row-1+j/3][col-1+j%3])){
                        oldPiece = ChessBoard.board[row-1+j/3][col-1+j%3];
                        ChessBoard.board[row][col] = " ";
                        ChessBoard.board[row-1+j/3][col-1+j%3] = "A";
                        int kingTemp = ChessBoard.kingPositionC;
                        ChessBoard.kingPositionC = i+(j/3)*8+j%3-9;
                        if(isKingSafe()) {
                            list = list+row+col+(row-1+j/3)+(col-1+j%3)+oldPiece+" ";
                        }
                        ChessBoard.board[row][col] = "A";
                        ChessBoard.board[row-1+j/3][col-1+j%3] = oldPiece;
                        ChessBoard.kingPositionC = kingTemp;
                    }
                }catch(Exception e) {}
            }
        }
        return list;
    }
    //Queens possible moves
    public static String possibleQ(int i) {
        String list = "";
        String oldPiece;
        int row = i/8;
        int col = i%8;
        //temp variable is so the queen can move as long as she want in one direction
        int temp = 1;
        //the 9 possible ways a queen can move
        //4 diagonals and then one north, east, west and south
        for (int j = -1; j <= 1; j++){
            for (int k = -1; k <= 1; k++){
                if(j != 0 || k != 0){
                    try{
                        while(" ".equals(ChessBoard.board[row+temp*j][col+temp*k])){
                            oldPiece = ChessBoard.board[row+temp*j][col+temp*k];
                            ChessBoard.board[row][col] = " ";
                            ChessBoard.board[row+temp*j][col+temp*k] = "Q";
                            if(isKingSafe()) {
                                list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece+" ";
                            }
                            ChessBoard.board[row][col] = "Q";
                            ChessBoard.board[row+temp*j][col+temp*k] = oldPiece;
                            temp++;
                        }
                        if(Character.isLowerCase(ChessBoard.board[row+temp*j][col+temp*k].charAt(0))){
                            oldPiece = ChessBoard.board[row+temp*j][col+temp*k];
                            ChessBoard.board[row][col] = " ";
                            ChessBoard.board[row+temp*j][col+temp*k] = "Q";
                            if(isKingSafe()) {
                                list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece+" ";
                            }
                            ChessBoard.board[row][col] = "Q";
                            ChessBoard.board[row+temp*j][col+temp*k] = oldPiece;
                        }
                    }catch(Exception e) {}
                    temp = 1;
                }
            }
        }
        return list;
    }
    //Possible Bishop moves
    //Almost the same as the queen
    public static String possibleB(int i) {
        String list = "";
        String oldPiece;
        int row = i/8;
        int col = i%8;
        //temp variable is so the bishop can move as long as she want in the diagonal directions
        int temp = 1;
        //the 4 possible ways a bishop can move
        //4 diagonals
        for (int j = -1; j <= 1; j+=2){
            for (int k = -1; k <= 1; k+=2){
                try{
                    while(" ".equals(ChessBoard.board[row+temp*j][col+temp*k])){
                        oldPiece = ChessBoard.board[row+temp*j][col+temp*k];
                        ChessBoard.board[row][col] = " ";
                        ChessBoard.board[row+temp*j][col+temp*k] = "B";
                        if(isKingSafe()) {
                            list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece+" ";
                        }
                        ChessBoard.board[row][col] = "B";
                        ChessBoard.board[row+temp*j][col+temp*k] = oldPiece;
                        temp++;
                    }
                    if(Character.isLowerCase(ChessBoard.board[row+temp*j][col+temp*k].charAt(0))){
                        oldPiece = ChessBoard.board[row+temp*j][col+temp*k];
                        ChessBoard.board[row][col] = " ";
                        ChessBoard.board[row+temp*j][col+temp*k] = "B";
                        if(isKingSafe()) {
                            list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece+" ";
                        }
                        ChessBoard.board[row][col] = "B";
                        ChessBoard.board[row+temp*j][col+temp*k] = oldPiece;
                    }
                }catch(Exception e) {}
                temp = 1;
            }
        }
        return list;
    }
    //Possible Knight moves
    public static String possibleK(int i) {
        String list = "";
        String oldPiece;
        int row = i/8;
        int col = i%8;
        for (int j = -1; j <= 1; j+=2){
            for (int k = -1; k <= 1; k+=2){
                //2 horizontal 1 vertical
                try{
                    if (Character.isLowerCase(ChessBoard.board[row+j][col+k*2].charAt(0)) || " ".equals(ChessBoard.board[row+j][col+k*2])){
                        oldPiece = ChessBoard.board[row+j][col+k*2];
                        ChessBoard.board[row][col] = " ";
                        ChessBoard.board[row+j][col+k*2] = "K";
                        if(isKingSafe()) {
                            list = list+row+col+(row+j)+(col+k*2)+oldPiece+" ";
                        }
                        ChessBoard.board[row][col] = "K";
                        ChessBoard.board[row+j][col+k*2] = oldPiece;
                    }
                }catch(Exception e) {}
                //1 horizontal 2 vertical
                try{
                    if (Character.isLowerCase(ChessBoard.board[row+j*2][col+k].charAt(0)) || " ".equals(ChessBoard.board[row+j*2][col+k])){
                        oldPiece = ChessBoard.board[row+j*2][col+k];
                        ChessBoard.board[row][col] = " ";
                        ChessBoard.board[row+j*2][col+k] = "K";
                        if(isKingSafe()) {
                            list = list+row+col+(row+j*2)+(col+k)+oldPiece+" ";
                        }
                        ChessBoard.board[row][col] = "K";
                        ChessBoard.board[row+j*2][col+k] = oldPiece;
                    }
                }catch(Exception e) {}
            }
        }
        return list;
    }
    //Possible Rock moves
    public static String possibleR(int i) {
        String list = "";
        String oldPiece;
        int row = i/8;
        int col = i%8;
        int temp = 1;
        for (int j = -1; j <= 1; j+=2){
            //Horizontal
            try{
                while(" ".equals(ChessBoard.board[row][col+temp*j])){
                    oldPiece = ChessBoard.board[row][col+temp*j];
                    ChessBoard.board[row][col] = " ";
                    ChessBoard.board[row][col+temp*j] = "R";
                    if(isKingSafe()) {
                        list = list+row+col+(row)+(col+temp*j)+oldPiece+" ";
                    }
                    ChessBoard.board[row][col] = "R";
                    ChessBoard.board[row][col+temp*j] = oldPiece;
                    temp++;
                }
                if(Character.isLowerCase(ChessBoard.board[row][col+temp*j].charAt(0))){
                    oldPiece = ChessBoard.board[row][col+temp*j];
                    ChessBoard.board[row][col] = " ";
                    ChessBoard.board[row][col+temp*j] = "R";
                    if(isKingSafe()) {
                        list = list+row+col+(row)+(col+temp*j)+oldPiece+" ";
                    }
                    ChessBoard.board[row][col] = "R";
                    ChessBoard.board[row][col+temp*j] = oldPiece;
                }
            }catch(Exception e) {}
            temp = 1;
            //Vertical
            try{
                while(" ".equals(ChessBoard.board[row+temp*j][col])){
                    oldPiece = ChessBoard.board[row+temp*j][col];
                    ChessBoard.board[row][col] = " ";
                    ChessBoard.board[row+temp*j][col] = "R";
                    if(isKingSafe()) {
                        list = list+row+col+(row+temp*j)+(col)+oldPiece+" ";
                    }
                    ChessBoard.board[row][col] = "R";
                    ChessBoard.board[row+temp*j][col] = oldPiece;
                    temp++;
                }
                if(Character.isLowerCase(ChessBoard.board[row+temp*j][col].charAt(0))){
                    oldPiece = ChessBoard.board[row+temp*j][col];
                    ChessBoard.board[row][col] = " ";
                    ChessBoard.board[row+temp*j][col] = "R";
                    if(isKingSafe()) {
                        list = list+row+col+(row+temp*j)+(col)+oldPiece+" ";
                    }
                    ChessBoard.board[row][col] = "R";
                    ChessBoard.board[row+temp*j][col] = oldPiece;
                }
            }catch(Exception e) {}
            temp = 1;
        }
        return list;
    }
    //Possible Pawn moves
    public static String possibleP(int i) {
        String list = "";
        String oldPiece;
        int row = i/8;
        int col = i%8;
        for (int j = -1; j <= 1; j+=2){
            try{
                //Captures
                if (Character.isLowerCase(ChessBoard.board[row-1][col+j].charAt(0)) && i >= 16){
                    oldPiece = ChessBoard.board[row-1][col+j];
                    ChessBoard.board[row][col] = " ";
                    ChessBoard.board[row-1][col+j] = "P";
                    if(isKingSafe()) {
                        list = list+row+col+(row-1)+(col+j)+oldPiece+" ";
                    }
                    ChessBoard.board[row][col] = "P";
                    ChessBoard.board[row-1][col+j] = oldPiece;
                }
            }catch(Exception e) {}
            try{
                //Promotion and capture
                if (Character.isLowerCase(ChessBoard.board[row-1][col+j].charAt(0)) && i < 16){
                    oldPiece = ChessBoard.board[row-1][col+j];
                    ChessBoard.board[row][col] = " ";
                    ChessBoard.board[row-1][col+j] = "Q";
                    if(isKingSafe()) {
                        list = list+row+col+(row-1)+(col+j)+oldPiece+"P";
                    }
                    ChessBoard.board[row][col] = "P";
                    ChessBoard.board[row-1][col+j] = oldPiece;
                }
            }catch(Exception e) {}
        }
        try{
            //Move one forward
            if (" ".equals(ChessBoard.board[row-1][col]) && i >= 16){
                oldPiece = ChessBoard.board[row-1][col];
                ChessBoard.board[row][col] = " ";
                ChessBoard.board[row-1][col] = "P";
                if(isKingSafe()) {
                    list = list+row+col+(row-1)+(col)+oldPiece+" ";
                }
                ChessBoard.board[row][col] = "P";
                ChessBoard.board[row-1][col] = oldPiece;
            }
        }catch(Exception e) {}
        try{
            //Promotion, no capture
            if (" ".equals(ChessBoard.board[row-1][col]) && i < 16){
                oldPiece = ChessBoard.board[row-1][col];
                ChessBoard.board[row][col] = " ";
                ChessBoard.board[row-1][col] = "Q";
                if(isKingSafe()) {
                    list = list+row+col+(row-1)+(col)+oldPiece+"P";
                }
                ChessBoard.board[row][col] = "P";
                ChessBoard.board[row-1][col] = oldPiece;
            }
        }catch(Exception e) {}
        try{
            //Move two forward
            if (" ".equals(ChessBoard.board[row-1][col]) && " ".equals(ChessBoard.board[row-2][col]) && i >= 48){
                oldPiece = ChessBoard.board[row-2][col];
                ChessBoard.board[row][col] = " ";
                ChessBoard.board[row-2][col] = "P";
                if(isKingSafe()) {
                    list = list+row+col+(row-2)+(col)+oldPiece+" ";
                }
                ChessBoard.board[row][col] = "P";
                ChessBoard.board[row-2][col] = oldPiece;
            }
        }catch(Exception e) {}
        return list;
    }
    
    public static String heuristic(String list) {
        int[] score = new int [list.length()/6];
        for (int i = 0; i < list.length(); i += 6) {
            doAMove(list.substring(i, i+6));
            score[i/6] =- Rating.rating(-1, 0);
            undoAMove(list.substring(i, i+6));
        }
        String newListA = "", newListB = list;
        //First few moves only, max 6 moves.
        for (int i = 0; i < Math.min(6, list.length()/6); i++) {
            int max =- 1000000;
            int maxLocation = 0;
            for (int j = 0; j < list.length()/6; j++) {
                if (score[j] > max) {
                	max = score[j]; 
                	maxLocation = j;
                }
            }
            //Sets the best score to negative, so it don't being searched again
            score[maxLocation] =- 1000000;
            //Adds best move to new list and delete from old list
            newListA += list.substring(maxLocation*6,maxLocation*6+6);
            newListB = newListB.replace(list.substring(maxLocation*6,maxLocation*6+6), "");
        }
        return newListA+newListB;
    }
    
    public static boolean isKingSafe() {
        //Bishop/Queen
        int temp = 1;
        for (int i = -1; i <= 1; i+=2){
            for (int j = -1; j <= 1; j+=2){
                try{
                    //all four diagonals
                    while(" ".equals(ChessBoard.board[ChessBoard.kingPositionC/8+temp*i][ChessBoard.kingPositionC%8+temp*j])){temp++;}
                    if ("b".equals(ChessBoard.board[ChessBoard.kingPositionC/8+temp*i][ChessBoard.kingPositionC%8+temp*j]) ||
                            "q".equals(ChessBoard.board[ChessBoard.kingPositionC/8+temp*i][ChessBoard.kingPositionC%8+temp*j])){
                        return false;
                    }
                }catch(Exception e) {}
                temp = 1;
            }
        }
        
        //Rock/Queen
        for (int i = -1; i <= 1; i+=2){
            try{
                //Horizontal
                while(" ".equals(ChessBoard.board[ChessBoard.kingPositionC/8][ChessBoard.kingPositionC%8+temp*i])){temp++;}
                if ("r".equals(ChessBoard.board[ChessBoard.kingPositionC/8][ChessBoard.kingPositionC%8+temp*i]) ||
                        "q".equals(ChessBoard.board[ChessBoard.kingPositionC/8][ChessBoard.kingPositionC%8+temp*i])){
                    return false;
                }
            }catch(Exception e) {}
            temp = 1;
            try{
                //Vertical
                while(" ".equals(ChessBoard.board[ChessBoard.kingPositionC/8+temp*i][ChessBoard.kingPositionC%8])){temp++;}
                if ("r".equals(ChessBoard.board[ChessBoard.kingPositionC/8+temp*i][ChessBoard.kingPositionC%8]) ||
                        "q".equals(ChessBoard.board[ChessBoard.kingPositionC/8+temp*i][ChessBoard.kingPositionC%8])){
                    return false;
                }
            }catch(Exception e) {}
            temp = 1;
        }
        
        //Knight
        for (int i = -1; i <= 1; i+=2){
            for (int j = -1; j <= 1; j+=2){
                try{
                    //two cols one row
                    if ("k".equals(ChessBoard.board[ChessBoard.kingPositionC/8+i][ChessBoard.kingPositionC%8+j*2])){
                        return false;
                    }
                }catch(Exception e) {}
                try{
                    //two rows one col
                    if ("k".equals(ChessBoard.board[ChessBoard.kingPositionC/8+i*2][ChessBoard.kingPositionC%8+j])){
                        return false;
                    }
                }catch(Exception e) {}
            }
        }
        
        //Pawn
        //Don't check if king is in one of the to last lines.
        if(ChessBoard.kingPositionC >= 16){
            try{
                //left
                if ("p".equals(ChessBoard.board[ChessBoard.kingPositionC/8-1][ChessBoard.kingPositionC%8-1])){
                    return false;
                }
            }catch(Exception e) {}
            try{
                //right
                if ("p".equals(ChessBoard.board[ChessBoard.kingPositionC/8-1][ChessBoard.kingPositionC%8+1])){
                    return false;
                }
            }catch(Exception e) {}
        }
        
        //King
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if(i != 0 || j != 0){
                    try{
                        if ("a".equals(ChessBoard.board[ChessBoard.kingPositionC/8+i][ChessBoard.kingPositionC%8+j])){
                            return false;
                        }
                    }catch(Exception e) {}
                }
            }
        }
        return true;
    }
}