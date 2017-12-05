package Interfaces;


import java.util.Scanner;

import Board.ChessBoard;
import Moves.AlphaBetaPruning;
import Moves.Moves;
import Utils.Utils;

public class TUI {
	static boolean gameOver = false;
	public static int playerTurn;
	public static int playAsWhite;
	public static boolean asWhite;
	public static Utils utils;

	public static void playgame(){

		//Introduction
		System.out.println("Welcome to this chess game, made by Group 1!");

		//continue game
		System.out.println("Do you want to continue a previous game. \n1. Yes\n2. No");
		boolean boo = continueGame();
		if(!boo){
			//player as white
			System.out.println("Do you want to play as white or black. \n1. White\n2. Black");
			asWhite = playerAsWhite();
		}		
		//Who starts
		if(asWhite){
			whoStart(1);
		}else{
			whoStart(2);
		}
	}
	



	//Check with the possibleMove list
	public static boolean validMove(String move){
		String possibleMoves = Moves.possibleMoves();
		for (int i = 0; i < possibleMoves.length(); i+=6) {
			if(move.equals(possibleMoves.substring(i, i+6))){
				return true;
			}
		}
		return false;
	}

	public static void userTurn(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		playerTurn = 1;
		check();
		System.out.println("\nPossible moves: " + possibleMoves(Moves.possibleMoves()));
		System.out.print("Write your move: ");
		String move = scan.nextLine();
		
		String ourMove = utils.winboardToOurMoveConverter(move);
//		if(asWhite){
			ourMove = checkMove(utils.winboardToOurMoveConverter(move));
//		}else{
//			ourMove = winboardToOurMoveConverter(UserMoveBlack(move));
//		}		
//		System.out.println("ourmove" + ourMove);
//		
//		String yooo = checkMove(ourMove);
//		System.out.println("yooo" + yooo);
	

		
		boolean valid = validMove(ourMove);
		if(valid){
			Moves.makeMove(ourMove);
//			if(asWhite){
				ChessBoard.drawWhiteBoard();
//			}else{
//				ChessBoard.drawBlackBoard();
//			}
			
		}
		else{
			System.out.println("Unvalid move. try again!");
			userTurn();
		}
		playerTurn = 0;
	}
	
	public static void enemyTurn(){
		playerTurn = 0;
		int beta = Integer.MAX_VALUE;
		int alpha = Integer.MIN_VALUE;
		AlphaBetaPruning.flipBoard();
		if(endGame()){
			gameOver = true;
		}else{
			System.out.println("\nEnemys turn! ");
			long startTime = System.currentTimeMillis();
			String moveEnemy = AlphaBetaPruning.alphaBeta(AlphaBetaPruning.globalDepth, beta, alpha, " ", 0);
			long endTime = System.currentTimeMillis();
			Moves.makeMove(moveEnemy);
			String moveEnemyConverted;
//			if(asWhite){
				moveEnemyConverted = utils.ourMoveToWinboardConverter(enemyMoveWhite(moveEnemy));
//			}else{
//				moveEnemyConverted = ourMoveToWinboardConverter(moveEnemy);
//			}
			System.out.println("Enemys move: " + moveEnemyConverted);
			System.out.println("It took " + (endTime-startTime) + " milliseconds!");
			AlphaBetaPruning.flipBoard();
			playerTurn = 1;
//			if(asWhite){
				ChessBoard.drawWhiteBoard();
//			}else{
//				ChessBoard.drawBlackBoard();
//			}		
			}
	}
	//convert my move if black
//	public static String UserMoveBlack(String move){
//		String newMove = "";
//		for (int i = 0; i < 4; i++) {
//			String ch = move.substring(i,i+1);
//			switch (ch) {
//			case "0": ch = "7"; newMove += ch; 
//			break;
//			case "1": ch = "6"; newMove += ch; 
//			break;
//			case "2": ch = "5"; newMove += ch; 
//			break;
//			case "3": ch = "4"; newMove += ch; 
//			break;
//			case "4": ch = "3"; newMove += ch; 
//			break;
//			case "5": ch = "2"; newMove += ch; 
//			break;
//			case "6": ch = "1"; newMove += ch; 
//			break;
//			case "7": ch = "0"; newMove += ch; 
//			break;	
//			}
//		}
//
//		return newMove;		
//	}
	//converts enemy move to be correct form
	public static String enemyMoveWhite(String move){
		String newMove = "";
		for (int i = 0; i < 4; i++) {
			String ch = move.substring(i,i+1);
			switch (ch) {
			case "0": ch = "7"; newMove += ch; 
			break;
			case "1": ch = "6"; newMove += ch; 
			break;
			case "2": ch = "5"; newMove += ch; 
			break;
			case "3": ch = "4"; newMove += ch; 
			break;
			case "4": ch = "3"; newMove += ch; 
			break;
			case "5": ch = "2"; newMove += ch; 
			break;
			case "6": ch = "1"; newMove += ch; 
			break;
			case "7": ch = "0"; newMove += ch; 
			break;	
			}
		}
		newMove += move.toUpperCase().charAt(4);
		return newMove;		
	}
	
	//check mate or stale mate message
	public static boolean endGame(){
		if(Moves.possibleMoves().length() == 0){
			if(!Moves.kingSafe()){
				if(playerTurn == 1){
					System.out.println("\nEnemy won by Checkmate!");
					return true;
				}else{
					System.out.println("\nYou won by Checkmate!");
					return true;
				}
			}
			else{
				if(playerTurn == 1){
					System.out.println("\nEnemy put you in Stalemate! Tie!");
					return true;
				}else{
					System.out.println("\nYou put enemy in Stalemate! Tie!");
					return true;
				}
			}
		}
		return false;
	}
	
	//check message
	public static boolean check(){
		if(Moves.possibleMoves().length() != 0 && !Moves.kingSafe()){
			System.out.println("\nEnemy says: Check!");
		}
		return false;
	}
	
	public static boolean playerAsWhite(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		playAsWhite = scan.nextInt();
		if(playAsWhite == 1 || playAsWhite == 2){
			if(playAsWhite == 2){
				AlphaBetaPruning.flipBoard();
				return false;
			}
		}else{
			System.out.println("Not legal choice. Try again!");
			playerAsWhite();
		}
		return true;
	}
        
	public static boolean continueGame(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int continueChoice = scan.nextInt();
		if(continueChoice == 1 || continueChoice == 2){
			if(continueChoice == 1){
				String boardString = scan.next();
//				System.out.println(boardString);

				String newBoard[][] = new String[8][8];

				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {

						if (boardString.charAt(i+(j*8)) == '0') {
							newBoard[j][i]=" ";
						}else{
							newBoard[j][i]=boardString.charAt(i+(j*8))+"";   
						}
					}
				}
				ChessBoard.setBoard(newBoard);
//				if(asWhite){
					ChessBoard.drawWhiteBoard();
//				}else{
//					ChessBoard.drawBlackBoard();
//				}				
				return true;
			}
		}else{
			System.out.println("Not legal choice. Try again!");
			continueGame();
		}
		return false;
	}
	
	public static void whoStart(int whoStarts){
		if(whoStarts == 1){
			//user start/the game
//			if(asWhite){
				ChessBoard.drawWhiteBoard();
//			}else{
//				ChessBoard.drawBlackBoard();
//			}			
			while(!gameOver){
				if(endGame()){
					gameOver = true;
					break;
				}else{
					userTurn();
					enemyTurn();
				}
			}
		}else{
			//enemy start/the game
//			if(asWhite){
				ChessBoard.drawWhiteBoard();
//			}else{
//				ChessBoard.drawBlackBoard();
//			}			
			while(!gameOver){
				enemyTurn();
				if(endGame()){
					gameOver = true;
					break;
				}else{
					userTurn();
				}
			}
		}
	}
	
	//Makes the possibleMoves list better looking with spaces
	public static String possibleMoves(String moves){
		String newMoves = "";
		for (int i = 0; i < moves.length(); i+=6) {
			if(moves.charAt(i+4) != ' '){
				newMoves += "["+utils.ourMoveToWinboardConverter(moves.substring(i, i+6))+"] ";
			}else{
				newMoves += "["+utils.ourMoveToWinboardConverter(moves.substring(i, i+6))+"] ";
			}
		}
		return newMoves;
	}
	
	public static String checkMove(String move) {
		//Pawnpromotion
		if(ChessBoard.board[Character.getNumericValue((move.charAt(0)))][Character.getNumericValue((move.charAt(1)))] == "P" && Character.getNumericValue((move.charAt(0))) == 1){
			if(ChessBoard.board[Character.getNumericValue((move.charAt(2)))][Character.getNumericValue((move.charAt(3)))] != " "){
				return move + ChessBoard.board[Character.getNumericValue((move.charAt(2)))][Character.getNumericValue((move.charAt(3)))] + "P";

			}else{
				return move + " P";
			}
		//Normal move
		}else{
			if(ChessBoard.board[Character.getNumericValue((move.charAt(2)))][Character.getNumericValue((move.charAt(3)))] != " "){
				return move + ChessBoard.board[Character.getNumericValue((move.charAt(2)))][Character.getNumericValue((move.charAt(3)))] + " ";
			}
			else{
				return move + "  ";
			}
		}
	}
}