package Main;
import Board.ChessBoard;
import Moves.Moves;

public class Main {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		ChessBoard chessboard = new ChessBoard();
		Moves moves = new Moves();
		//Bigger case king position
		while(!"A".equals(ChessBoard.board[ChessBoard.kingPositionC/8][chessboard.kingPositionC%8])) {
			chessboard.kingPositionC++;
		}
		//Lower case king position
		while(!"a".equals(chessboard.board[chessboard.kingPositionL/8][chessboard.kingPositionL%8])) {
			chessboard.kingPositionL++;
		}
		System.out.println(moves.possibleMoves());
	}
}
