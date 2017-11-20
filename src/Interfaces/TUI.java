package Interfaces;

import java.util.Scanner;

import Board.ChessBoard;
import Moves.AlphaBetaPruning;
import Moves.Moves;

public class TUI {
	static boolean gameOver = false;
	public static Scanner scan = new Scanner(System.in);

	public static void playgame(){
		//Introduction
		System.out.println("Welcome to this chess game, made by Group 1!");
		System.out.print("You start. ");
		ChessBoard.drawBoard();
		//The game
		while(!gameOver){
			if(endGame()){
				gameOver = false;
				break;
			}
			userTurn();
			enemyTurn();
		}
	}
	
	//Convert fx 6444 to e2e4 (also with capping)
	public static String convertMove(){
		//TODO maybe?
		return "";
	}
	//Check with the possibleMove list
	public static boolean validMove(String move){
		String possibleMoves = Moves.possibleMoves();
		for (int i = 0; i < possibleMoves.length(); i+=5) {
			if(move.equals(possibleMoves.substring(i, i+5))){
				return true;
			}
		}
		return false;
	}

	public static void userTurn(){
		check();
		System.out.println("\nPossible moves: " + Moves.possibleMoves());
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
	}
	public static void enemyTurn(){
		int beta = Integer.MAX_VALUE;
		int alpha = Integer.MIN_VALUE;
		System.out.println("\nEnemys turn! ");
		AlphaBetaPruning.flipBoard();
		if(!endGame()){
		long startTime = System.currentTimeMillis();
		String moveEnemy = AlphaBetaPruning.alphaBeta(AlphaBetaPruning.globalDepth, beta, alpha, " ", 0);
		long endTime = System.currentTimeMillis();
		Moves.makeMove(moveEnemy);
		String moveEnemyConverted = enemyMove(moveEnemy);
		System.out.println("Enemys move: " + moveEnemyConverted);
		System.out.println("It took " + (endTime-startTime) + " milliseconds!");
		AlphaBetaPruning.flipBoard();
		ChessBoard.drawBoard();
		}else{
			System.out.println("You won!");
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
		return newMove;		
	}
	
	//check mate or stale mate message
	public static boolean endGame(){
		if(Moves.possibleMoves().length() == 0){
			if(!Moves.kingSafe()){
				System.out.println("\nEnemy says: Checkmate!");
				return true;
			}
			else{
				System.out.println("\nEnemy says: Stalemate!");
				return true;
			}
		}
		return false;
	}
	
	//check message
	public static boolean check(){
		if(Moves.possibleMoves().length() != 0 && !Moves.kingSafe()){
			System.out.println("\nEnemy says: Check");
		}
		
		return false;
	}
}
