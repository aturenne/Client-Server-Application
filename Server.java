//Alyssa Turenne

import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
    final static int PORT = 25413;

    public static void main (String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + PORT + ", " + e);
            System.exit(1);
        }
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed: " + PORT + ", " + e);
                continue;
            }
            new ServerThread(clientSocket).start();
        }
    }
}


class ServerThread extends Thread {
    Socket socket = null;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader is = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            PrintWriter os = new PrintWriter(socket.getOutputStream(), true);
            String inputLine;
            //separate the command given by the client into tokens and make appropriate variables for each
            while ((inputLine = is.readLine()) != null) {
                double price = 0;
                StringTokenizer tok = new StringTokenizer(inputLine, " ");
                int tokens = tok.countTokens();
                if (tokens != 2 && tokens != 4) {
                    os.println("Insufficient input");
                    os.flush();
                    continue;
                }
                String locomotive = tok.nextToken();
                String seatType = tok.nextToken();
                int numAdults = 0;
                int numChildren = 0;
                //if statements to set the price based on given ticket price information
                if (seatType.equals("Caboose")) {
                    if (locomotive.equals("Diesel")) {
                        price = 850;
                    } else if (locomotive.equals("Steam")) {
                        price = 950;
                    }
                    os.println("$" + price);
                    os.flush();
                    continue;
                } else {
                    try {
                        numAdults = Integer.parseInt(tok.nextToken());
                        numChildren = Integer.parseInt(tok.nextToken());
                    } catch (NumberFormatException e) {
                        os.println("Number Format Error");
                        os.flush();
                        continue;
                    }
                    if (numAdults < 0 || numChildren < 0) {
                        os.println("Invalid number entered.");
                        os.flush();
                        continue;
                    }
                    if (locomotive.equals("Diesel")) {
                        if (seatType.equals("Presidential")) {
                            price = (numAdults * 77) + (numChildren * 57);
                        } else if (seatType.equals("FirstClass")) {
                            price = (numAdults * 57) + (numChildren * 37);
                        } else if (seatType.equals("OpenAir")) {
                            price = (numAdults * 27) + (numChildren * 22);
                        }
                    } else if (locomotive.equals("Steam")) {
                        if (seatType.equals("Presidential")) {
                            price = (numAdults * 87) + (numChildren * 67);
                        } else if (seatType.equals("FirstClass")) {
                            price = (numAdults * 67) + (numChildren * 47);
                        } else if (seatType.equals("OpenAir")) {
                            price = (numAdults * 37) + (numChildren * 32);
                        }
                    }
                }
                //send back to client
                os.println("$" + price);
                os.flush();
            }

            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}