package Main;

import Board.ChessBoard;
import Interfaces.GUI;
import Interfaces.TUI;
import Interfaces.Winboard;

public class Main {
	public static void main(String[] args) {

		//Bigger case king position
		while(!"A".equals(ChessBoard.board[ChessBoard.kingPositionC/8][ChessBoard.kingPositionC%8])) {
			ChessBoard.kingPositionC++;
		}
		//Lower case king position
		while(!"a".equals(ChessBoard.board[ChessBoard.kingPositionL/8][ChessBoard.kingPositionL%8])) {
			ChessBoard.kingPositionL++;
		}
		TUI.playgame();
	}
}
