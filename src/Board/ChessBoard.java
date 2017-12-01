package Board;

import java.util.Arrays;

import Moves.AlphaBetaPruning;
import Moves.Moves;

public class ChessBoard {
	//Real board
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
//		
	//test stalemate/checkmate
//	public static String board[][] = {
//		    //0   1   2   3   4   5   6   7
//			{" "," "," "," "," "," "," ","a"},//0
//			{" "," "," "," "," "," "," "," "},//1
//			{" "," "," "," "," ","A"," "," "},//2
//			{" "," "," "," "," "," ","Q"," "},//3
//			{" "," "," "," "," "," "," "," "},//4
//			{" "," "," "," "," "," "," "," "},//5
//			{" "," "," "," "," "," "," "," "},//6
//			{" "," "," "," "," "," "," "," "}};//7	
//	
//	//test pawnpromotion
//	public static String board[][] = {
//		    //0   1   2   3   4   5   6   7
//			{"r"," ","b","q","a","b","k","r"},//0
//			{"p","P","p","p"," ","p","P","p"},//1
//			{"P"," "," "," "," "," "," "," "},//2
//			{"P"," "," ","p"," "," "," "," "},//3
//			{"p"," "," ","P"," ","P","P"," "},//4
//			{"p","p"," "," "," "," "," "," "},//5
//			{"P","P","P","P","P"," ","p","P"},//6
//			{"R","K","B","Q","A","B","K","R"}};//7	

    public static void setBoard(String[][] board) {
        ChessBoard.board = board;
    }
	
	//Case king
	public static int kingPositionC;
	//Lowercase king
	public static int kingPositionL;
	
	public static void drawWhiteBoard(){
		int temp = 8;
		System.out.println("\nThe Board: ");
//		System.out.println("\nThe Board: \n  a  b  c  d  e  f  g  h");
//		System.out.println("\nThe Board: \n  0  1  2  3  4  5  6  7");
                String str ="";
                for (int i = 0; i < 8; i++) {
			//java.util.Arrays sets up the print nice
//			System.out.println(i + Arrays.toString(board[i]));
                        
                        
			System.out.println(temp + Arrays.toString(board[i]));
			temp--;

		}
    			System.out.println("  a  b  c  d  e  f  g  h");

                
    			//String for continuegame
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                            str = str + board[i][j]; 
                    }
                }
                  str =  str.replaceAll(" ", "0");
                System.out.println(str);
                
	}
	public static void drawBlackBoard(){
		int temp = 1;
		System.out.println("\nThe Board: ");
//		System.out.println("\nThe Board: \n  a  b  c  d  e  f  g  h");
//		System.out.println("\nThe Board: \n  0  1  2  3  4  5  6  7");
                String str ="";
                for (int i = 0; i < 8; i++) {
			//java.util.Arrays sets up the print nice
//			System.out.println(i + Arrays.toString(board[i]));
                        
			System.out.println(temp + Arrays.toString(board[i]));
			temp++;
			

		}
    			System.out.println("  h  g  f  e  d  c  b  a");
                
    			//String for continuegame
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                            str = str + board[i][j]; 
                    }
                }
                  str =  str.replaceAll(" ", "0");
                System.out.println(str);
                
	}
}
