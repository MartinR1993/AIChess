package Interfaces;

import java.util.Scanner;

import Board.ChessBoard;
import Moves.AlphaBetaPruning;
import Moves.Moves;

public class TUI {
	static boolean gameOver = false;
	public static int playerTurn;
	public static int playAsWhite;
	public static boolean asWhite; 

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
	
	//Convert fx 6444 to e2e4 (also with capping)
	public String moveConverter(String move) {

		move=move.toUpperCase();
		StringBuffer sb = new StringBuffer();
		String[] parts = move.split("");
		String tmp=parts[0];
		parts[0]=parts[1];
		parts[1]=tmp;
		tmp=parts[2];
		parts[2]=parts[3];
		parts[3]=tmp;

		sb.append(number2NumberConverter(parts[0]));
		sb.append(letter2NumberConverter(parts[1]));
		sb.append(number2NumberConverter(parts[2]));
		sb.append(letter2NumberConverter(parts[3]));

		return sb.toString();
	}

	public int letter2NumberConverter(String letter) {
		switch (letter) {
			case "A":
				return 0;
			case "B":
				return 1;
			case "C":
				return 2;
			case "D":
				return 3;
			case "E":
				return 4;
			case "F":
				return 5;
			case "G":
				return 6;
			case "H":
				return 7;
			default:
				return -1;
		}
	}
	
	public int number2NumberConverter(String nr) {
		switch (nr) {
			case "8":
				return 0;
			case "7":
				return 1;
			case "6":
				return 2;
			case "5":
				return 3;
			case "4":
				return 4;
			case "3":
				return 5;
			case "2":
				return 6;
			case "1":
				return 7;
			default:
				return -1;
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
		boolean valid = validMove(move);
		if(valid){
			Moves.makeMove(move);
			ChessBoard.drawBoard();
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
			String moveEnemyConverted = enemyMove(moveEnemy);
			System.out.println("Enemys move: " + moveEnemyConverted);
			System.out.println("It took " + (endTime-startTime) + " milliseconds!");
			AlphaBetaPruning.flipBoard();
			playerTurn = 1;
			ChessBoard.drawBoard();
		}
	}
	
	//converts enemy move to be correct form
	public static String enemyMove(String move){
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
				ChessBoard.drawBoard();
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
			ChessBoard.drawBoard();
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
			ChessBoard.drawBoard();
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
				newMoves += "["+moves.substring(i, i+6)+"] ";
			}else{
				newMoves += "["+moves.substring(i, i+6)+"] ";
			}
		}
		return newMoves;
	}
}