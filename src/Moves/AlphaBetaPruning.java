package Moves;

import java.util.Scanner;

import Rating.Rating;

public class AlphaBetaPruning {
	Moves moves = new Moves();
	Rating r = new Rating();
	int globalDepth = 4;
	
	public String alphaBeta(int depth, int beta, int alpha, String move, int player) {
		String list = moves.possibleMoves();
		
		if(depth == 0 || list.length() == 0){
			return move+(r.rating()); //*(player*2-1)
		}
		//sort later
		//either 1 or 0
		player=1-player;
		
		for (int i = 0; i < list.length(); i+=5) {
			moves.makeMove(list.substring(i,i+5));
			flipBoard();
			String returnString = alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);
			int value = Integer.valueOf(returnString.substring(5));
			flipBoard();
			moves.undoMove(list.substring(i,i+5));
			if(player == 0) {
				if(value<=beta){
					beta = value;
					if(depth == globalDepth){
						move=returnString.substring(0, 5);
					}
				}
			}else{
				if(value>alpha){
					alpha = value;
					if(depth == globalDepth){
						move=returnString.substring(0, 5);
					}
				}
			}
			if(alpha>= beta){
				if(player == 0){
					return move+beta;
				}else{
					return move+alpha;
				}
			}
		}
		if(player == 0){
			return move+beta;
		}else{
			return move+alpha;
		}
	}
	
	public static void flipBoard() {
		
	}
}

