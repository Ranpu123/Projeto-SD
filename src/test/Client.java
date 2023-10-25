import Json.JsonHelper;
import Model.Header;
import Requests.LoginRequest;
import Requests.LogoutRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Requests.Request;
import Requests.RequestOperations;
import Response.LoginResponse;
import jdk.net.ExtendedSocketOptions;


public class Client {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int port = 24800;

        try (Socket echoSocket = new Socket(serverHost, port);
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     echoSocket.getInputStream()));
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))
        ) {
            repl(out, in, stdin);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHost);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + serverHost);
            System.exit(1);
        }
    }

    private static void repl(PrintWriter out, BufferedReader in, BufferedReader stdin) {
        String token = null;
        try {
            /*while (true) {*/
                //Request<?> request = new LoginRequest(new Header(null,RequestOperations.LOGIN), new LoginRequest.Payload("viniciuscerqueira@alunos.utfpr.edu.br", "teste"));
                Request<?> request = new LoginRequest("admin@google.com", "admin");
                String jsonRequest = JsonHelper.toJson(request);
                out.println(jsonRequest);
                System.out.println();
                System.out.println("Objeto de envio criado: " + request);
                System.out.println("Enviado: " + jsonRequest);
                System.out.println();

                String jsonResponse = in.readLine();
                if (jsonResponse == null) {
                    System.err.println("Erro recebendo dados do servidor");
                    /*break;*/
                }
                System.out.println("Recebido: " + jsonResponse);
                LoginResponse res = JsonHelper.fromJson(jsonResponse, LoginResponse.class);
                token = res.payload().getToken();
                
                request = new LogoutRequest(token);
                jsonRequest = JsonHelper.toJson(request);
                out.println(jsonRequest);
                System.out.println();
                System.out.println("Objeto de envio criado: " + request);
                System.out.println("Enviado: " + jsonRequest);
                System.out.println();
                
                jsonResponse = in.readLine();
                if (jsonResponse == null) {
                    System.err.println("Erro recebendo dados do servidor");
                    /*break;*/
                }

                System.out.println("Recebido: " + jsonResponse);
                
                System.out.println();

            /*}*/
        } catch (IOException e) {
            System.out.println("error reading stdin");
        }
    }
}