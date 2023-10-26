
import Json.JsonHelper;
import Requests.Handler.LoginHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import jdk.net.ExtendedSocketOptions;
import Response.Response;
import Requests.Router;
import Response.LoginResponse;
import Response.LogoutResponse;
import javax.security.auth.login.LoginContext;

import Exception.ServerResponseException;
import java.net.SocketOption;
import java.net.SocketOptions;
import java.util.Scanner;
import java.util.Set;

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

    private Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
        start();
    }
    
    public static void main(String[] args){
        Integer porta = 0;
        
        Scanner scan = new Scanner(System.in);
        System.err.print("Informe a porta: ");
        porta = scan.nextInt();
        
        if(porta == 0 || porta == null){
            porta = 24800;
        }
        
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Connection Socket Created: "+serverSocket.getLocalPort());
            
            while (true) {
                try {
                    System.out.println("Waiting for Connection");
                    new Server(serverSocket.accept());
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
                
                if(response instanceof LogoutResponse){
                    System.out.println("Due to Logout request, closing thread: "+this.getName());
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }

        assert (clientSocket.isClosed());
    }
    
}
