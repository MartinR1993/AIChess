package Board;

public class ChessBoard {

	public String board[][] = {
		   // 0   1   2   3   4   5   6   7
			{"r","k","b","q","a","b","k","r"},//0
			{"p","p","p","p","p","p","p","p"},//1
			{" "," "," "," "," "," "," "," "},//2
			{" "," "," "," "," "," ","q"," "},//3
			{" "," "," "," "," "," "," ","r"},//4
			{" "," "," "," ","A"," "," "," "},//5
			{"P","P","P","P","P","P","P","P"},//6
			{"R","K","B","Q"," ","B","K","R"}};//7	
	//Case king
	public int kingPositionC;
	//Lowercase king
	public int kingPositionL;
	
}
