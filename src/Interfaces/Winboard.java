package Interfaces;

/*
 * Test of connection for a Java chess engine to Winboard
 * This program simulates the chess engine.
 * It receives commands from Winboard and diplays them in a textarea.
 * You can type commands to Winboard in a textfield.
 * Syntax: move e7e5
 * See: Chess Engine Communication Protocol.mht
 *
 * Bjørn Christensen, 4/2 2012
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Winboard extends JFrame implements Runnable {
    JLabel labelToWinboard = new JLabel("To Winboard      ");
    JLabel labelFromWinboard = new JLabel("From Winboard");
    JTextField textToWinboard = new JTextField(18);
    JTextArea textFromWinboard = new JTextArea(16,25);
    JButton buttonSend= new JButton("Send");

    public Winboard() {

    }
    @Override
    public void run() {
        System.out.println("Winboard thread started");
            JPanel panelSend = new JPanel();
            JPanel panelreceive = new JPanel();

            panelSend.add(labelToWinboard);
            panelSend.add(textToWinboard);
            panelSend.add(buttonSend);
            panelreceive.add(labelFromWinboard);
            panelreceive.add(textFromWinboard);

            textFromWinboard.setBorder(new LineBorder(Color.BLACK));
            buttonSend.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    String text=textToWinboard.getText();
                    System.out.println(text);
                    textToWinboard.setText("");
                }
            });

            setLayout(new BorderLayout());
            add(panelSend, BorderLayout.NORTH);
            add(panelreceive, BorderLayout.CENTER);

            setTitle("WinboardTest");
            pack();
            setLocationRelativeTo(null); // Center the frame
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            // Mainloop
            Scanner keyboard=new Scanner(System.in);
            String received="";
            while(true) {
                String input=keyboard.nextLine();
                received+=input+"\n";
                textFromWinboard.setText(received);
            }
        }


    /*public static void main(String[] args) {
        Winboard frame = new Winboard();
    }*/
}