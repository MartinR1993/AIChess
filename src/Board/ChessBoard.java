package Board;

import java.util.Arrays;

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
	//FEJL i denne state med pawn promotion
//	public static String board[][] = {
//		    //0   1   2   3   4   5   6   7
//			{"r","k","b","q","a","b","k","r"},//0
//			{"p","p","p","p","P","p","p","p"},//1
//			{" "," "," "," "," "," "," "," "},//2
//			{" "," "," "," "," "," "," "," "},//3
//			{" "," "," "," "," ","P","P"," "},//4
//			{" "," "," "," "," "," "," "," "},//5
//			{"P","P","P","P","P"," ","p","P"},//6
//			{"R","K","B","Q","A","B","K","R"}};//7		
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
	
	//Case king
	public static int kingPositionC;
	//Lowercase king
	public static int kingPositionL;
	
	public static void drawBoard(){
//		int temp = 8;
//		System.out.println("\nThe Board: \n  a  b  c  d  e  f  g  h");
		System.out.println("\nThe Board: \n  0  1  2  3  4  5  6  7");
		for (int i = 0; i < 8; i++) {
			//java.util.Arrays sets up the print nice
			System.out.println(i + Arrays.toString(board[i]));
//			System.out.println(temp + Arrays.toString(board[i]));
//			temp--;
		}
	}
}
