package Interfaces;


import java.util.Scanner;

import Board.ChessBoard;
import Board.FEN;
import Moves.AlphaBetaPruning;
import Moves.Moves;
import Utils.Utils;

public class TUI {
	static boolean gameOver = false;
	public static int playerTurn;
	public static int playAsWhite;
	public static boolean asWhite;
	public static String boardString;

        //Method to start the game/program in TUI
	public static void playgame(){

		//Introduction
		System.out.println("Welcome to this chess game, made by Group 1!");

		System.out.println("Do you want to continue a previous game. \n1. Yes\n2. No");
		boolean boo = continueGame();
		if(!boo){
			//Case king position
			while(!"A".equals(ChessBoard.board[ChessBoard.kingPositionC/8][ChessBoard.kingPositionC%8])) {
				ChessBoard.kingPositionC++;
			}
			//Lowercase king position
			while(!"a".equals(ChessBoard.board[ChessBoard.kingPositionL/8][ChessBoard.kingPositionL%8])) {
				ChessBoard.kingPositionL++;
			}
			
			//player as white
			System.out.println("Do you want to play as white or black. \n1. White\n2. Black");
			asWhite = playerAsWhite();
                        
			//Who starts
			if(asWhite){
				playerTurn = 0;
				whoStart(1);
			}else{
				playerTurn = 1;
				whoStart(2);
			}
		}else{
			//continue game
			if(boardString.substring(boardString.length()-1).startsWith("u")){
				playerTurn = 0;
				whoStart(1);
			}else if(boardString.substring(boardString.length()-1).startsWith("e")){
				playerTurn = 1;
				whoStart(2);
			}
		}
	}

	public static void userTurn(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		playerTurn = 1;
		String ourMove = "";
		boolean valid = false;
		checkMessage();
		System.out.println("\nPossible moves: " + printPossibleMoves(Moves.possibleMoves()));
		System.out.print("Write your move: ");
		String move = scan.nextLine();
		try {
			ourMove = Utils.normalToOurMoveConverter(move);
			ourMove = checkAMove(Utils.normalToOurMoveConverter(move));
			valid = Moves.validMove(ourMove);
		} catch (Exception e) {
		}
		if(valid){
			Moves.doAMove(ourMove);
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
		AlphaBetaPruning.spinBoard();
		if(endGame()){
			gameOver = true;
		}else{
			System.out.println("\nEnemys turn! ");
			long startTime = System.currentTimeMillis();
			String moveEnemy = AlphaBetaPruning.alphaBeta(AlphaBetaPruning.globalDepth, beta, alpha, " ", 0);
			long endTime = System.currentTimeMillis();
			Moves.doAMove(moveEnemy);
			String moveEnemyConverted;
			moveEnemyConverted = Utils.ourMoveToNormalConverter(Utils.enemyMoveConverted(moveEnemy));
			System.out.println("Enemys move: " + moveEnemyConverted);
			System.out.println("It took " + (endTime-startTime) + " milliseconds!");
			AlphaBetaPruning.spinBoard();
			ChessBoard.drawBoard();
			playerTurn = 1;
		}
	}

	
	//check mate or stale mate message
	public static boolean endGame(){
		if(Moves.possibleMoves().length() == 0){
			if(!Moves.isKingSafe()){
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
	public static boolean checkMessage(){
		if(Moves.possibleMoves().length() != 0 && !Moves.isKingSafe()){
			System.out.println("\nEnemy says: Check!");
		}
		return false;
	}
	
	//Choose about being white or black
	public static boolean playerAsWhite(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		playAsWhite = scan.nextInt();
		if(playAsWhite == 1 || playAsWhite == 2){
			if(playAsWhite == 2){
				AlphaBetaPruning.spinBoard();
				return false;
			}
		}else{
			System.out.println("Not legal choice. Try again!");
			playerAsWhite();
		}
		return true;
	}
    
	//Use the FEN string to continue a game
	public static boolean continueGame(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int continueChoice = scan.nextInt();
		if(continueChoice == 1 || continueChoice == 2){
			if(continueChoice == 1){
                                System.out.println("Insert FEN String:");
				boardString = scan.next();
				boardString += " " + scan.next();
				int aC = scan.nextInt();
				ChessBoard.kingPositionC = aC ;
				int aL = scan.nextInt();
				ChessBoard.kingPositionL = aL;
				String [][] newBoard = FEN.continueFen(boardString);
				ChessBoard.setBoard(newBoard);				

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
	public static String printPossibleMoves(String moves){
		String newMoves = "";
		for (int i = 0; i < moves.length(); i+=6) {
			if(moves.charAt(i+4) != ' '){
				newMoves += "["+Utils.ourMoveToNormalConverter(moves.substring(i, i+6))+"] ";
			}else{
				newMoves += "["+Utils.ourMoveToNormalConverter(moves.substring(i, i+6))+"] ";
			}
		}
		return newMoves;
	}
	
	//This method check if a move is a normal move or a pawnpromotion move and adds the rest to the move string
	public static String checkAMove(String move) {
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