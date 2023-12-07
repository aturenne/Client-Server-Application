//Alyssa Turenne

import java.awt.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements ActionListener {
    static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        Client scc = new Client();
        scc.setVisible(true);
    }
    JRadioButton dieselButton;
    JRadioButton steamButton;
    ButtonGroup locomotiveGroup;

    JRadioButton cabooseButton;
    JRadioButton presidentialButton;
    JRadioButton firstClassButton;
    JRadioButton openAirButton;
    ButtonGroup seatingGroup;

    JLabel adultLabel;
    JTextField adultTF;
    JLabel childrenLabel;
    JTextField childrenTF;

    JButton calcButton;
    JLabel ansLabel;
    JTextField ansTF;

    JLabel errorLabel;

    public Client() {
        setTitle("Price Calculator");
        setBounds(100, 100, 320, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        dieselButton = new JRadioButton("Diesel");
        dieselButton.setBounds(25, 24, 100, 16);
        dieselButton.setSelected(true);
        getContentPane().add(dieselButton);

        steamButton = new JRadioButton("Steam");
        steamButton.setBounds(25, 64, 100, 16);
        getContentPane().add(steamButton);

        locomotiveGroup = new ButtonGroup();
        locomotiveGroup.add(dieselButton);
        locomotiveGroup.add(steamButton);

        cabooseButton = new JRadioButton("Caboose");
        cabooseButton.setBounds(170, 14, 100, 16);
        cabooseButton.setSelected(true);
        getContentPane().add(cabooseButton);

        presidentialButton = new JRadioButton("Presidential");
        presidentialButton.setBounds(170, 34, 140, 16);
        getContentPane().add(presidentialButton);

        firstClassButton = new JRadioButton("First Class");
        firstClassButton.setBounds(170, 54, 140, 16);
        getContentPane().add(firstClassButton);

        openAirButton = new JRadioButton("Open Air");
        openAirButton.setBounds(170, 74, 140, 16);
        getContentPane().add(openAirButton);

        seatingGroup = new ButtonGroup();
        seatingGroup.add(cabooseButton);
        seatingGroup.add(presidentialButton);
        seatingGroup.add(firstClassButton);
        seatingGroup.add(openAirButton);

        adultLabel = new JLabel("Adults");
        adultLabel.setBounds(30, 104, 50, 16);
        getContentPane().add(adultLabel);
        adultLabel.setVisible(false);

        adultTF = new JTextField();
        adultTF.setBounds(80, 104, 50, 16);
        getContentPane().add(adultTF);
        adultTF.setVisible(false);

        childrenLabel = new JLabel("Children");
        childrenLabel.setBounds(160, 104, 60, 16);
        getContentPane().add(childrenLabel);
        childrenLabel.setVisible(false);

        childrenTF = new JTextField();
        childrenTF.setBounds(225, 104, 50, 16);
        getContentPane().add(childrenTF);
        childrenTF.setVisible(false);

        calcButton = new JButton("Calculate");
        calcButton.setBounds(100, 144, 80, 16);
        getContentPane().add(calcButton);

        ansLabel = new JLabel("Answer");
        ansLabel.setBounds(80, 184, 50, 16);
        getContentPane().add(ansLabel);

        ansTF = new JTextField();
        ansTF.setBounds(150, 184, 75, 16);
        getContentPane().add(ansTF);
        ansTF.setEditable(false);

        errorLabel = new JLabel("");
        errorLabel.setBounds(40, 220, 200, 16);
        getContentPane().add(errorLabel);

        dieselButton.addActionListener(this);
        steamButton.addActionListener(this);
        cabooseButton.addActionListener(this);
        presidentialButton.addActionListener(this);
        firstClassButton.addActionListener(this);
        openAirButton.addActionListener(this);
        calcButton.addActionListener(this);

        connectToServer();
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Diesel") || action.equals("Steam")) {
            ansTF.setText("");
            errorLabel.setText("");
        }
        else if (action.equals("Caboose")) {
            ansTF.setText("");
            errorLabel.setText("");
            adultLabel.setVisible(false);
            adultTF.setVisible(false);
            childrenLabel.setVisible(false);
            childrenTF.setVisible(false);
        } else if (action.equals("Presidential") || action.equals("First Class") || action.equals("Open Air")) {
            ansTF.setText("");
            errorLabel.setText("");
            adultLabel.setVisible(true);
            adultTF.setVisible(true);
            childrenLabel.setVisible(true);
            childrenTF.setVisible(true);
        } else if (action.equals("Calculate")) {
            calculate();
        }
    }

// ========================================================================

    
    final static String server = "127.0.0.1";
    final static int port = 25413;
    Socket socket;
    PrintWriter os;
    BufferedReader is;


    // The following method connects to the Server
    void connectToServer() {
        try{
            socket = new Socket(server, port);
        } catch(Exception e){
            System.out.println("Cannot connect");
            System.exit(1);
        }
        try {
            is = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(new
                    BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }

    }

    // The following method sends an appropriate command to the server
    // Then reads the result and displays it in the answer text field
    void calculate() {
        //empty string variable that will be the command sent to the server
        String command = "";
        //empty string variable that will read the result from the server
        String result = "";
        //create variables for the user input in the GUI
        String numAdults = adultTF.getText();
        if(adultTF.getText().isEmpty()) {
            numAdults = "0";
        }
        String numChildren = childrenTF.getText();
        if(childrenTF.getText().isEmpty()){
            numChildren = "0";
        }
        //if statements to construct the command statement based on user input
        if (cabooseButton.isSelected()) {
            if (dieselButton.isSelected()) {
                command = "Diesel Caboose";
            } else if (steamButton.isSelected()) {
                command = "Steam Caboose";
            }
        }
        else if(presidentialButton.isSelected()){
            if(dieselButton.isSelected()){
                command = "Diesel Presidential " + numAdults + " " + numChildren;
            }
            else if(steamButton.isSelected()){
                command = "Steam Presidential " + numAdults + " " + numChildren;
            }
        }
        else if(firstClassButton.isSelected()){
            if(dieselButton.isSelected()){
                command = "Diesel FirstClass " + numAdults + " " + numChildren;
            }
            else if(steamButton.isSelected()){
                command = "Steam FirstClass " + numAdults + " " + numChildren;
            }
        }
        else if(openAirButton.isSelected()){
            if(dieselButton.isSelected()){
                command = "Diesel OpenAir " + numAdults + " " + numChildren;
            }
            else if(steamButton.isSelected()){
                command = "Steam OpenAir " + numAdults + " " + numChildren;
            }
        }
        try {
            os.println(command);    //send user input to the server
            os.flush();
            result = is.readLine();    //read server response
            //if the server sends an error, move it to the error label rather than the answer text field
            if(!result.contains("$")){
                errorLabel.setText(result);
                ansTF.setText("");
            }
            else{
                ansTF.setText(result);
                errorLabel.setText("");
            }
        } catch(IOException e){
            errorLabel.setText("ERROR: " + e);
        }

    }

}