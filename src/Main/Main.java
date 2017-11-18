package Main;
import java.util.Scanner;

import Board.ChessBoard;
import Moves.AlphaBetaPruning;
import Moves.Moves;

public class Main {
	public static void main(String[] args) {
		boolean gameOver = false;
		Scanner scan = new Scanner(System.in);
		//Bigger case king position
		while(!"A".equals(ChessBoard.board[ChessBoard.kingPositionC/8][ChessBoard.kingPositionC%8])) {
			ChessBoard.kingPositionC++;
		}
		//Lower case king position
		while(!"a".equals(ChessBoard.board[ChessBoard.kingPositionL/8][ChessBoard.kingPositionL%8])) {
			ChessBoard.kingPositionL++;
		}
		//Introduction
		System.out.println("Welcome to this chess game, made by Group 1!");
		System.out.print("You start. ");
		ChessBoard.drawBoard();

		while(!gameOver){
			int beta = Integer.MAX_VALUE;
			int alpha = Integer.MIN_VALUE;
			System.out.print("Write your move: ");
			//check på hvilket træk der er lovligt!
			String move = scan.nextLine();
			Moves.makeMove(move);
			ChessBoard.drawBoard();
			
			System.out.println("Enemys turn: ");
			AlphaBetaPruning.flipBoard();
			String moveEnemy = AlphaBetaPruning.alphaBeta(4, beta, alpha, " ", 0);
			Moves.makeMove(moveEnemy);
			System.out.println("Enemys move: " + moveEnemy);
			AlphaBetaPruning.flipBoard();
			ChessBoard.drawBoard();
		}
		//do and undo moves
//		moves.makeMove("6050 ");
//		moves.undoMove("6050 ");
//		ab.flipBoard();
		scan.close();
		System.out.println("Possible moves: \n" + Moves.possibleMoves());
		ChessBoard.drawBoard();
	}
}
