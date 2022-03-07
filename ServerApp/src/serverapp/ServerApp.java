/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serverapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 0B6136649
 */
public class ServerApp {

    private ServerSocket server;
    

    public ServerApp(String ipAddress, int port) throws Exception {

        this.server = new ServerSocket(port, 1, InetAddress.getByName(ipAddress));
        

    }

    public ServerApp(int port) throws Exception {

        this.server = new ServerSocket(port, 1, InetAddress.getLocalHost());

    }

    private void listen() throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        while ((data = in.readLine()) != null) {
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);
        }
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

    public static void main(String[] args) throws Exception {
        ServerApp app = null;
        if (args.length == 1) {
            app = new ServerApp(Integer.parseInt(args[0]));
        } else if (args.length == 1) {
            app = new ServerApp(args[0],Integer.parseInt(args[1]));
        }else {
            System.out.println("Error, the server takes 1 param as port or two params as ip and port");
            System.exit(-1);
        }

        System.out.println("\r\nRunning Server: "
                + "Host=" + app.getSocketAddress().getHostAddress()
                + " Port=" + app.getPort());

        app.listen();
    }

}
