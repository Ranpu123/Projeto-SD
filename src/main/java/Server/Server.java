package Server;

import Json.JsonHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import Response.Response;
import Requests.Router;
import Response.LogoutResponse;
import Response.LoginResponse;

import Exception.ServerResponseException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinic
 */
public class Server extends Thread {
    private final Socket clientSocket;
    private final UsersConnected usersConnected;

    private Server(Socket clientSocket, UsersConnected usersConnected) {
        this.clientSocket = clientSocket;
        this.usersConnected = usersConnected;
        UserConnected user = new UserConnected(clientSocket.getInetAddress().toString(), null);
        usersConnected.addUser(user);
        start();
    }
    
    public static void listen(int porta, UsersConnected usersConnected){
        
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Connection Socket Created: "+serverSocket.getLocalPort());
            
            while (true) {
                try {
                    System.out.println("Waiting for Connection");
                    new Server(serverSocket.accept(), usersConnected);
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 10008.");
            System.exit(1);
        }
    }
    
    @Override
    public void run() {
        System.out.println("New Communication Thread Started");

        try (clientSocket;
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            System.err.println("Connected to: "+clientSocket.getInetAddress()+"\n");
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recebido From["+clientSocket.getInetAddress()+"]: " + inputLine);
                Response<?> response;

                try {
                    response = Router.handleRequest(inputLine);
                } catch (ServerResponseException e) {
                    response = e.intoResponse();
                }
                
                if(clientSocket.isClosed()){
                    System.out.println("DESCONECTADO A FORÇA...");
                    break;
                }
                
                String jsonResponse = JsonHelper.toJson(response);
                System.out.println("Enviado To["+clientSocket.getInetAddress()+"]: "+ jsonResponse+"\n");
                out.println(jsonResponse);
                
                if(response instanceof LoginResponse){
                    var connect = new UserConnected(clientSocket.getInetAddress().toString(),
                            JsonHelper.fromJson(jsonResponse, LoginResponse.class).payload().getToken());
                    break;
                }
                
                if(response instanceof LogoutResponse){
                    System.out.println("Due to Logout request, closing thread: "+this.getName());
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }

        assert (clientSocket.isClosed() && !clientSocket.isConnected());
        usersConnected.removeUser(new UserConnected(clientSocket.getInetAddress().toString(), null));
    }
    
}
