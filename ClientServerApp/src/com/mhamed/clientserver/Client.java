/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mhamed.clientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author 0B6136649
 */
public class Client {

    private Socket socket;
    private Scanner scanner;

    private Client(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }

    private void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
    }

    public static String getAddress() {
        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            //System.out.println("Your current IP address : " + ip);
            //System.out.println("Your current Hostname : " + hostname);
            return ("Your current IP address : " + ip + "\n" + "Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            return (e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getAddress());
        System.out.println("try to connect to " + args[0] + ":" + args[1]);
        Client client = new Client(
                InetAddress.getByName(args[0]),
                Integer.parseInt(args[1]));

        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }

}
