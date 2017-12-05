package Utils;

import static Interfaces.TUI.checkMove;

/**
 * Created by as on 12/5/17.
 */
public class Utils {
    public static int incomingLettertoNumberConverter(String letter) {
        switch (letter) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            default:
                return -1;
        }
    }
    
    public static int incomingNumbertoNumberConverter(String nr) {
        switch (nr) {
            case "8":
                return 0;
            case "7":
                return 1;
            case "6":
                return 2;
            case "5":
                return 3;
            case "4":
                return 4;
            case "3":
                return 5;
            case "2":
                return 6;
            case "1":
                return 7;
            default:
                return -1;
        }
    }

    //Convert fx 6444 to e2e4
    public static String ourMoveToNormalConverter(String move){
        StringBuffer sb = new StringBuffer();
        String[] parts = move.split("");
        String tmp = parts[0];
        parts[0] = parts[1];
        parts[1] = tmp;
        tmp = parts[2];
        parts[2] = parts[3];
        parts[3] = tmp;
        sb.append(outgoingNumbertoLetterConverter(parts[0]));
        sb.append(outgoingNumbertoNumberConverter(parts[1]));
        sb.append(outgoingNumbertoLetterConverter(parts[2]));
        sb.append(outgoingNumbertoNumberConverter(parts[3]));
        return sb.toString();
    }
    
    public static char outgoingNumbertoLetterConverter(String number) {
        switch (number) {
            case "0":
                return 'a';
            case "1":
                return 'b';
            case "2":
                return 'c';
            case "3":
                return 'd';
            case "4":
                return 'e';
            case "5":
                return 'f';
            case "6":
                return 'g';
            case "7":
                return 'h';
            default:
                return ' ';
        }
    }
    
    public static int outgoingNumbertoNumberConverter(String nr) {
        switch (nr) {
            case "0":
                return 8;
            case "1":
                return 7;
            case "2":
                return 6;
            case "3":
                return 5;
            case "4":
                return 4;
            case "5":
                return 3;
            case "6":
                return 2;
            case "7":
                return 1;
            default:
                return -1;
        }
    }

    //Convert fx e2e4 to 6444
    public static String normalToOurMoveConverter(String move) {
        move=move.toUpperCase();
        StringBuffer sb = new StringBuffer();
        String[] parts = move.split("");
        try {
            String tmp = parts[0];
            parts[0] = parts[1];
            parts[1] = tmp;
            tmp = parts[2];
            parts[2] = parts[3];
            parts[3] = tmp;
            sb.append(incomingNumbertoNumberConverter(parts[0]));
            sb.append(incomingLettertoNumberConverter(parts[1]));
            sb.append(incomingNumbertoNumberConverter(parts[2]));
            sb.append(incomingLettertoNumberConverter(parts[3]));
        }
        catch(ArrayIndexOutOfBoundsException e){
            return "";
        }
        checkMove(sb.toString());
        return sb.toString();
    }
    
    //converts enemy move to be correct form
  	public static String enemyMoveConverted(String move){
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
  		newMove += move.toUpperCase().charAt(4);
  		return newMove;		
  	}
}