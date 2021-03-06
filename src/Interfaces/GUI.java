package Interfaces;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import Board.ChessBoard;
import Moves.AlphaBetaPruning;
import Moves.Moves;

import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

public class GUI {
	static boolean gameOver = false;
	public static int playerTurn;
	public static int playAsWhite;
    public static Winboard frame;


    public static void playgame(){

Thread thread = new Thread(frame=new Winboard());
thread.start();
		//Introduction
		System.out.println("Welcome to this chess game, made by Group 1!");
                
                //continue game
		/*System.out.println("Do you want to continue a previous game. \n1. Yes\n2. No");
		boolean boo = continueGame();
                if(!boo){
                    //player as white
                    System.out.println("Do you want to play as white or black. \n1. White\n2. Black");
                    playerAsWhite();
                }
                //Who starts/the game
		System.out.println("Do you want to start? \n1. Yes\n2. No");*/
		whoStart();
               
	}
	//Convert fx 6444 to e2e4
	public static String ourMoveToWinboardConverter(String move)
	{
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
	public static String winboardToOurMoveConverter(String move) {

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
        while(sb.length()<6){
            sb.append(" ");
        }
        return sb.toString();

	}

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

	public static String getLastLine(){
        int end = frame.textFromWinboard.getDocument().getLength();
		String text ="";
        try {

            if(end>=5){
                text= frame.textFromWinboard.getText(end - 5,4);

			//	frame.textFromWinboard.setText("");
                return text;
            }
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
        return text;
    }

	//Check with the possibleMove list
	public static String validMove(String move){
		if(move.length()>=4){
		String possibleMoves = Moves.possibleMoves();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < possibleMoves.length(); i+=6) {
			list.add(possibleMoves.substring(i,i+6));
		}
		for (int i=0;i<list.size();i++){
			if(move.substring(0,3).equals(list.get(i).substring(0,3))){
				return list.get(i);
			}
		}}

		return "";
	}

	public static void userTurn(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		playerTurn = 1;
		check();
		System.out.println("\nPossible moves: " + possibleMoves(Moves.possibleMoves()));
		System.out.print("Write your move: ");
	//	String move = scan.nextLine();

		String move = winboardToOurMoveConverter(getLastLine());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//move=winboardToOurMoveConverter(getLastLine());
        System.out.println(getLastLine());
        System.out.println(winboardToOurMoveConverter(getLastLine()));
        String valid = validMove(move);
		if(!(valid.equals(""))){
			Moves.doAMove(move);
            System.out.println("Making move: "+move);
//			ChessBoard.drawBoard();
		}
		else{
			System.out.println("Invalid move '"+getLastLine()+"'**'"+move+"'. try again!");
			userTurn();
		}
		playerTurn = 0;
	}

	public static void enemyTurn(){
		playerTurn = 0;
		int beta = Integer.MAX_VALUE;
		int alpha = Integer.MIN_VALUE;
		AlphaBetaPruning.spinBoard();
		if(endGame()){
			gameOver = true;
		}else{
			System.out.println("\nEnemys turn! ");
			long startTime = System.currentTimeMillis();
			String moveEnemy = AlphaBetaPruning.alphaBeta(AlphaBetaPruning.globalDepth, beta, alpha, " ", 0);
			long endTime = System.currentTimeMillis();
			Moves.doAMove(moveEnemy);

			String moveEnemyConverted = enemyMove(moveEnemy);
            System.out.println("Enemys move: " + moveEnemyConverted);
			frame.textToWinboard.setText("move "+ourMoveToWinboardConverter(moveEnemyConverted));;
            frame.buttonSend.doClick();
            frame.textToWinboard.setText("move "+ourMoveToWinboardConverter(moveEnemyConverted));;
            frame.buttonSend.doClick();

			System.out.println("It took " + (endTime-startTime) + " milliseconds!");
			AlphaBetaPruning.spinBoard();
			playerTurn = 1;
//			ChessBoard.drawBoard();
		}
	}
	
	//converts enemy move to be correct form
	public static String enemyMove(String move){
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
	
	//check mate or stale mate message
	public static boolean endGame(){
		if(Moves.possibleMoves().length() == 0){
			if(!Moves.isKingSafe()){
				if(playerTurn == 1){
					frame.textFromWinboard.append("Enemy won by checkmate!");
					System.out.println("\nEnemy won by Checkmate!");
					return true;
				}else{
					System.out.println("\nYou won by Checkmate!");
					frame.textFromWinboard.append("you won by checkmate!");
					return true;
				}
			}
			else{
				if(playerTurn == 1){
					System.out.println("\nEnemy put you in Stalemate! Tie!");
					frame.textFromWinboard.append("Enemy put you in a Stalemate! Tie!");
					return true;
				}else{
					System.out.println("\nYou put enemy in Stalemate! Tie!");
					frame.textFromWinboard.append("Enemy put you in a stalemate! Tie!");
					return true;
				}
			}
		}
		return false;
	}
	
	//check message
	public static boolean check(){
		if(Moves.possibleMoves().length() != 0 && !Moves.isKingSafe()){
			System.out.println("\nEnemy says: Check!");
		}
		return false;
	}
	
	public static void playerAsWhite(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		playAsWhite = scan.nextInt();
		if(playAsWhite == 1 || playAsWhite == 2){
			if(playAsWhite == 2){
				AlphaBetaPruning.spinBoard();
			}
		}else{
			System.out.println("Not legal choice. Try again!");
			playerAsWhite();
		}
	}
        
        	public static boolean continueGame(){
//		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int continueChoice = scan.nextInt();
		if(continueChoice == 1 || continueChoice == 2){
                        if(continueChoice == 1){
                            String boardString = scan.next();
                            System.out.println(boardString);
                            
                            String newBoard[][] = new String[8][8];
                            
                            
                            for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
 
                                    if (boardString.charAt(i+(j*8)) == '0') {
                                        newBoard[j][i]=" ";
                                    }else{
                                    newBoard[j][i]=boardString.charAt(i+(j*8))+"";   
                                    }
                                    
                                }
                            }
                            ChessBoard.setBoard(newBoard);
//                            ChessBoard.drawBoard();
                            
                            return true;
			}
                        
		}else{
			System.out.println("Not legal choice. Try again!");
			continueGame();
		}
                return false;
	}
	
	public static void whoStart(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
	//	int whoStarts = scan.nextInt();
        int whoStarts = 1;
		if(whoStarts == 1 || whoStarts == 2){
			if(whoStarts == 1){
				//user start/the game
           //     frame = new Winboard();
//				ChessBoard.drawBoard();
				while(!gameOver){
					if(endGame()){
						gameOver = true;
						break;
					}else{
					userTurn();
					enemyTurn();
					}
				}
			}else{
				//enemy start/the game
//				ChessBoard.drawBoard();
				while(!gameOver){
					enemyTurn();
					if(endGame()){
						gameOver = true;
						break;
					}else{
					userTurn();
					}
				}
			}
		}else{
			System.out.println("Not legal choice. Try again!");
			whoStart();
		}
	}
	
	//Makes the possibleMoves list better looking with spaces
	public static String possibleMoves(String moves){
		String newMoves = "";
		for (int i = 0; i < moves.length(); i+=6) {
			if(moves.charAt(i+4) != ' '){
				newMoves += "["+moves.substring(i, i+6)+"] ";
			}else{
				newMoves += "["+moves.substring(i, i+6)+"] ";
			}
		}
		return newMoves;
	}




}
