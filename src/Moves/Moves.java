package Moves;
import Board.ChessBoard;

public class Moves {

	ChessBoard chessBoard = new ChessBoard();
	public String possibleMoves(){
		String list = "";
		for (int i = 0; i < 64; i++) {
			switch (chessBoard.board[i/8][i%8]){
			case "P" : list+=possibleP(i);
			break;
			case "R" : list+=possibleR(i);
			break;
			case "K" : list+=possibleK(i);
			break;
			case "B" : list+=possibleB(i);
			break;
			case "Q" : list+=possibleQ(i);
			break;
			case "A" : list+=possibleA(i);
			break;
			}
		}
		return list;	
	}
	//Kings possible moves
	public String possibleA(int i) {
		String list = "";
		String oldPiece;
		int row = i/8;
		int col = i%8;
		for (int j = 0; j < 9; j++) {
			if(j != 4) {
				try {
					if(Character.isLowerCase(chessBoard.board[row-1+j/3][col-1+j%3].charAt(0)) || " ".equals(chessBoard.board[row-1+j/3][col-1+j%3])){
						oldPiece = chessBoard.board[row-1+j/3][col-1+j%3];
						chessBoard.board[row][col] = " ";
						chessBoard.board[row-1+j/3][col-1+j%3] = "A";
						int kingTemp = chessBoard.kingPositionC;
						chessBoard.kingPositionC = i+(j/3)*8+j%3-9;
						if(kingSafe()) {
							list = list+row+col+(row-1+j/3)+(col-1+j%3)+oldPiece;
						}
						chessBoard.board[row][col] = "A";
						chessBoard.board[row-1+j/3][col-1+j%3] = oldPiece;
						chessBoard.kingPositionC = kingTemp;
					}
				}catch(Exception e) {}
			}
		}
		return list;
	}
	//Queens possible moves
	public String possibleQ(int i) {
		String list = "";
		String oldPiece;
		int row = i/8;
		int col = i%8;
		//temp variable is so the queen can move as long as she want in one direction
		int temp = 1;
		//the 9 possible ways a queen can move
		//4 diagonals and then one north, east, west and south
		for (int j = -1; j <= 1; j++){
			for (int k = -1; k <= 1; k++){
				if(j != 0 || k != 0){
					try{
						while(" ".equals(chessBoard.board[row+temp*j][col+temp*k])){
							oldPiece = chessBoard.board[row+temp*j][col+temp*k];
							chessBoard.board[row][col] = " ";
							chessBoard.board[row+temp*j][col+temp*k] = "Q";
							if(kingSafe()) {
								list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece;
							}
							chessBoard.board[row][col] = "Q";
							chessBoard.board[row+temp*j][col+temp*k] = oldPiece;
							temp++;
						}
						if(Character.isLowerCase(chessBoard.board[row+temp*j][col+temp*k].charAt(0))){
							oldPiece = chessBoard.board[row+temp*j][col+temp*k];
							chessBoard.board[row][col] = " ";
							chessBoard.board[row+temp*j][col+temp*k] = "Q";
							if(kingSafe()) {
								list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece;
							}
							chessBoard.board[row][col] = "Q";
							chessBoard.board[row+temp*j][col+temp*k] = oldPiece;
						}
					}catch(Exception e) {}
					temp = 1;
				}
			}
		}
		return list;
	}
	//Possible Bishop moves 
	//Almost the same as the queen
	public String possibleB(int i) {
		String list = "";
		String oldPiece;
		int row = i/8;
		int col = i%8;
		//temp variable is so the bishop can move as long as she want in the diagonal directions
		int temp = 1;
		//the 4 possible ways a bishop can move
		//4 diagonals
		for (int j = -1; j <= 1; j+=2){
			for (int k = -1; k <= 1; k+=2){
					try{
						while(" ".equals(chessBoard.board[row+temp*j][col+temp*k])){
							oldPiece = chessBoard.board[row+temp*j][col+temp*k];
							chessBoard.board[row][col] = " ";
							chessBoard.board[row+temp*j][col+temp*k] = "B";
							if(kingSafe()) {
								list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece;
							}
							chessBoard.board[row][col] = "B";
							chessBoard.board[row+temp*j][col+temp*k] = oldPiece;
							temp++;
						}
						if(Character.isLowerCase(chessBoard.board[row+temp*j][col+temp*k].charAt(0))){
							oldPiece = chessBoard.board[row+temp*j][col+temp*k];
							chessBoard.board[row][col] = " ";
							chessBoard.board[row+temp*j][col+temp*k] = "B";
							if(kingSafe()) {
								list = list+row+col+(row+temp*j)+(col+temp*k)+oldPiece;
							}
							chessBoard.board[row][col] = "B";
							chessBoard.board[row+temp*j][col+temp*k] = oldPiece;
						}
					}catch(Exception e) {}
					temp = 1;
			}
		}
		return list;
	}
	//Possible Knight moves
	public String possibleK(int i) {
		String list = "";
		String oldPiece;
		int row = i/8;
		int col = i%8;
		for (int j = -1; j <= 1; j+=2){
			for (int k = -1; k <= 1; k+=2){
				//2 horizontal 1 vertical
				try{
					if (Character.isLowerCase(chessBoard.board[row+j][col+k*2].charAt(0)) || " ".equals(chessBoard.board[row+j][col+k*2])){
						oldPiece = chessBoard.board[row+j][col+k*2];
						chessBoard.board[row][col] = " ";
						chessBoard.board[row+j][col+k*2] = "K";
						if(kingSafe()) {
							list = list+row+col+(row+j)+(col+k*2)+oldPiece;
						}
						chessBoard.board[row][col] = "K";
						chessBoard.board[row+j][col+k*2] = oldPiece;
					}	
				}catch(Exception e) {}
				//1 horizontal 2 vertical
				try{
					if (Character.isLowerCase(chessBoard.board[row+j*2][col+k].charAt(0)) || " ".equals(chessBoard.board[row+j*2][col+k])){
						oldPiece = chessBoard.board[row+j*2][col+k];
						chessBoard.board[row][col] = " ";
						chessBoard.board[row+j*2][col+k] = "K";
						if(kingSafe()) {
							list = list+row+col+(row+j*2)+(col+k)+oldPiece;
						}
						chessBoard.board[row][col] = "K";
						chessBoard.board[row+j*2][col+k] = oldPiece;
					}	
				}catch(Exception e) {}
			}		
		}
		return list;
	}
	//Possible Rock moves
	public String possibleR(int i) {
		String list = "";
		String oldPiece;
		int row = i/8;
		int col = i%8;
		int temp = 1;
		for (int j = -1; j <= 1; j+=2){
			//Horizontal
			try{
				while(" ".equals(chessBoard.board[row][col+temp*j])){
					oldPiece = chessBoard.board[row][col+temp*j];
					chessBoard.board[row][col] = " ";
					chessBoard.board[row][col+temp*j] = "R";
					if(kingSafe()) {
						list = list+row+col+(row)+(col+temp*j)+oldPiece;
					}
					chessBoard.board[row][col] = "R";
					chessBoard.board[row][col+temp*j] = oldPiece;
					temp++;
				}
				if(Character.isLowerCase(chessBoard.board[row][col+temp*j].charAt(0))){
					oldPiece = chessBoard.board[row][col+temp*j];
					chessBoard.board[row][col] = " ";
					chessBoard.board[row][col+temp*j] = "R";
					if(kingSafe()) {
						list = list+row+col+(row)+(col+temp*j)+oldPiece;
					}
					chessBoard.board[row][col] = "R";
					chessBoard.board[row][col+temp*j] = oldPiece;
				}
			}catch(Exception e) {}
			temp = 1;
			//Vertical
			try{
				while(" ".equals(chessBoard.board[row+temp*j][col])){
					oldPiece = chessBoard.board[row+temp*j][col];
					chessBoard.board[row][col] = " ";
					chessBoard.board[row+temp*j][col] = "R";
					if(kingSafe()) {
						list = list+row+col+(row+temp*j)+(col)+oldPiece;
					}
					chessBoard.board[row][col] = "R";
					chessBoard.board[row+temp*j][col] = oldPiece;
					temp++;
				}
				if(Character.isLowerCase(chessBoard.board[row+temp*j][col].charAt(0))){
					oldPiece = chessBoard.board[row+temp*j][col];
					chessBoard.board[row][col] = " ";
					chessBoard.board[row+temp*j][col] = "R";
					if(kingSafe()) {
						list = list+row+col+(row+temp*j)+(col)+oldPiece;
					}
					chessBoard.board[row][col] = "R";
					chessBoard.board[row+temp*j][col] = oldPiece;
				}
			}catch(Exception e) {}
			temp = 1;
		}
		return list;
	}
	//Possible Pawn moves
	public String possibleP(int i) {
		String list = "";
		return list;
	}
	
	public boolean kingSafe() {
		return true;
	}
}
