package Moves;
import Board.ChessBoard;

public class Moves {
	ChessBoard chessBoard = new ChessBoard();
	
	@SuppressWarnings("static-access")
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
	@SuppressWarnings("static-access")
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
	@SuppressWarnings("static-access")
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
	@SuppressWarnings("static-access")
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
	@SuppressWarnings("static-access")
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
	@SuppressWarnings("static-access")
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
	@SuppressWarnings("static-access")
	public String possibleP(int i) {
		String list = "";
		String oldPiece;
		int row = i/8;
		int col = i%8;
		for (int j = -1; j <= 1; j+=2){
			try{ 
				//Captures right
				if (Character.isLowerCase(chessBoard.board[row-1][col+j].charAt(0)) && i >= 16){
					oldPiece = chessBoard.board[row-1][col+j];
					chessBoard.board[row][col] = " ";
					chessBoard.board[row-1][col+j] = "P";
					if(kingSafe()) {
						list = list+row+col+(row-1)+(col+j)+oldPiece;
					}
					chessBoard.board[row][col] = "P";
					chessBoard.board[row-1][col+j] = oldPiece;
				}
			}catch(Exception e) {}
			try{ 
				//Promotion and capture
				if (Character.isLowerCase(chessBoard.board[row-1][col+1].charAt(0)) && i < 16){
					String[] temp = {"Q","R","B","K"};
					for (int k = 0; k < 4 ; k++) {
						oldPiece = chessBoard.board[row-1][col+j];
						chessBoard.board[row][col] = " ";
						chessBoard.board[row-1][col+j] = temp[k];
						if(kingSafe()) {
							//column1, column2, captured-piece, new-piece
							list = list+col+(col+j)+oldPiece+temp[k]+"P";
						}
						chessBoard.board[row][col] = "P";
						chessBoard.board[row-1][col+j] = oldPiece;
					}
				}
			}catch(Exception e) {}
		}
		try{ 
			//Move one forward
			if (" ".equals(chessBoard.board[row-1][col]) && i >= 16){
				oldPiece = chessBoard.board[row-1][col];
				chessBoard.board[row][col] = " ";
				chessBoard.board[row-1][col] = "P";
				if(kingSafe()) {
					list = list+row+col+(row-1)+(col)+oldPiece;
				}
				chessBoard.board[row][col] = "P";
				chessBoard.board[row-1][col] = oldPiece;
			}
		}catch(Exception e) {}
		try{ 
			//Promotion, no capture
			if (" ".equals(chessBoard.board[row-1][col]) && i < 16){
				String[] temp = {"Q","R","B","K"};
				for (int k = 0; k < 4 ; k++) {
					oldPiece = chessBoard.board[row-1][col];
					chessBoard.board[row][col] = " ";
					chessBoard.board[row-1][col] = temp[k];
					if(kingSafe()) {
						//column1, column2, captured-piece, new-piece
						list = list+col+(col)+oldPiece+temp[k]+"P";
					}
					chessBoard.board[row][col] = "P";
					chessBoard.board[row-1][col] = oldPiece;
				}
			}
		}catch(Exception e) {}
		try{ 
			//Move two forward
			if (" ".equals(chessBoard.board[row-1][col]) && " ".equals(chessBoard.board[row-2][col]) && i >= 48){
				oldPiece = chessBoard.board[row-2][col];
				chessBoard.board[row][col] = " ";
				chessBoard.board[row-2][col] = "P";
				if(kingSafe()) {
					list = list+row+col+(row-2)+(col)+oldPiece;
				}
				chessBoard.board[row][col] = "P";
				chessBoard.board[row-2][col] = oldPiece;
			}
		}catch(Exception e) {}
		return list;
	}
	
	@SuppressWarnings("static-access")
	public boolean kingSafe() {
		//Bishop/Queen
		int temp = 1;
		for (int i = -1; i <= 1; i+=2){
			for (int j = -1; j <= 1; j+=2){
				try{
					//all four diagonals
					while(" ".equals(chessBoard.board[chessBoard.kingPositionC/8+temp*i][chessBoard.kingPositionC%8+temp*j])){temp++;}
					if ("b".equals(chessBoard.board[chessBoard.kingPositionC/8+temp*i][chessBoard.kingPositionC%8+temp*j]) ||
							"q".equals(chessBoard.board[chessBoard.kingPositionC/8+temp*i][chessBoard.kingPositionC%8+temp*j])){
						return false;
					}
				}catch(Exception e) {}
				temp = 1;
			}
		}
		
		//Rock/Queen
		for (int i = -1; i <= 1; i+=2){
			try{
				//Horizontal
				while(" ".equals(chessBoard.board[chessBoard.kingPositionC/8][chessBoard.kingPositionC%8+temp*i])){temp++;}
				if ("r".equals(chessBoard.board[chessBoard.kingPositionC/8][chessBoard.kingPositionC%8+temp*i]) ||
						"q".equals(chessBoard.board[chessBoard.kingPositionC/8][chessBoard.kingPositionC%8+temp*i])){
					return false;
				}
			}catch(Exception e) {}
			temp = 1;
			try{
				//Vertical
				while(" ".equals(chessBoard.board[chessBoard.kingPositionC/8+temp*i][chessBoard.kingPositionC%8])){temp++;}
				if ("r".equals(chessBoard.board[chessBoard.kingPositionC/8+temp*i][chessBoard.kingPositionC%8]) ||
						"q".equals(chessBoard.board[chessBoard.kingPositionC/8+temp*i][chessBoard.kingPositionC%8])){
					return false;
				}
			}catch(Exception e) {}
			temp = 1;
		}
		
		//Knight
		for (int i = -1; i <= 1; i+=2){
			for (int j = -1; j <= 1; j+=2){
				try{
					//two cols one row
					if ("k".equals(chessBoard.board[chessBoard.kingPositionC/8+i][chessBoard.kingPositionC%8+j*2])){
						return false;
					}
				}catch(Exception e) {}
				try{
					//two rows one col
					if ("k".equals(chessBoard.board[chessBoard.kingPositionC/8+i*2][chessBoard.kingPositionC%8+j])){
						return false;
					}
				}catch(Exception e) {}
			}
		}
		
		//Pawn
		//Don't check if king is in one of the to last lines.
		if(chessBoard.kingPositionC >= 16){
			try{
				//left
				if ("p".equals(chessBoard.board[chessBoard.kingPositionC/8-1][chessBoard.kingPositionC%8-1])){
					return false;
				}
			}catch(Exception e) {}
			try{
				//right
				if ("p".equals(chessBoard.board[chessBoard.kingPositionC/8-1][chessBoard.kingPositionC%8+1])){
					return false;
				}
			}catch(Exception e) {}
		}
		
		//King
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				if(i != 0 || j != 0){
					try{
						if ("a".equals(chessBoard.board[chessBoard.kingPositionC/8+i][chessBoard.kingPositionC%8+j])){
							return false;
						}
					}catch(Exception e) {}
				}
			}
		}
		return true;
	}
}
