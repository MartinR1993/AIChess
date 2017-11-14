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
		int temp = 1;
		for (int j = -1; j <= 1; j++){
			for (int k = -1; k <= 1; k++){
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
		return list;
	}
	public String possibleB(int i) {
		String list = "";
		return list;
	}
	public String possibleK(int i) {
		String list = "";
		return list;
	}
	public String possibleR(int i) {
		String list = "";
		return list;
	}
	public String possibleP(int i) {
		String list = "";
		return list;
	}
	
	public boolean kingSafe() {
		return true;
	}
}
