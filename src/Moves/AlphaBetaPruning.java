package Moves;

import Board.ChessBoard;
import Rating.Rating;

public class AlphaBetaPruning {
	public static int globalDepth = 4;

	public static String alphaBeta(int depth, int beta, int alpha, String move, int player) {
		String list = Moves.possibleMoves();

		if(depth == 0 || list.length() == 0){
			return move+(Rating.rating(list.length(), depth)*(player*2-1)); 
		}
		list=Moves.sortMoves(list);
		//either 1 or 0
		player=1-player;

		for (int i = 0; i < list.length(); i+=5) {
			Moves.makeMove(list.substring(i,i+5));
			flipBoard();
			String returnString = alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);
			int value = Integer.valueOf(returnString.substring(5));
			flipBoard();
			Moves.undoMove(list.substring(i,i+5));
			if(player == 0) {
				if(value <= beta){
					beta = value;
					if(depth == globalDepth){
						move = returnString.substring(0, 5);
					}
				}
			}else{
				if(value > alpha){
					alpha = value;
					if(depth == globalDepth){
						move = returnString.substring(0, 5);
					}
				}
			}
			if(alpha >= beta){
				if(player == 0){
					return move+beta;
				}else{
					return move+alpha;
				}
			}
		}
		if(player == 0){
			return move + beta;
		}else{
			return move + alpha;
		}
	}

	public static void flipBoard() {
		String temp;
        for (int i=0;i<32;i++) {
            int r=i/8, c=i%8;
            if (Character.isUpperCase(ChessBoard.board[r][c].charAt(0))) {
                temp=ChessBoard.board[r][c].toLowerCase();
            } else {
                temp=ChessBoard.board[r][c].toUpperCase();
            }
            if (Character.isUpperCase(ChessBoard.board[7-r][7-c].charAt(0))) {
            	ChessBoard.board[r][c]=ChessBoard.board[7-r][7-c].toLowerCase();
            } else {
            	ChessBoard.board[r][c]=ChessBoard.board[7-r][7-c].toUpperCase();
            }
            ChessBoard.board[7-r][7-c]=temp;
        }
        int kingTemp=ChessBoard.kingPositionC;
        ChessBoard.kingPositionC=63-ChessBoard.kingPositionL;
        ChessBoard.kingPositionL=63-kingTemp;
	}
}

