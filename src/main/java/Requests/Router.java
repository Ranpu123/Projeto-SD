/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;


import Requests.Handler.LoginHandler;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import Requests.Handler.LogoutHandler;
import Requests.Handler.AdminCreateUserHandler;


import Exception.BadRequestException;
import Exception.ServerResponseException;
import Requests.Handler.AdminDeleteUserHandler;
import Requests.Handler.AdminFindUserHandler;
import Requests.Handler.AdminFindUsersHandler;
import Requests.Handler.AdminUpdateUserHandler;
import Requests.Handler.CreateUserHandler;
import Requests.Handler.DeleteUserHandler;
import Requests.Handler.FindUserHandler;
import Requests.Handler.UpdateUserHandler;


/**
 *
 * @author vinic
 */
public class Router {
    public static Response<?> handleRequest(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        Request<JsonObject> request = gson.fromJson(jsonRequest, Request.class);
        //System.out.println("\n"+request.getHeader().getOperation()+"\n");
        String op = request.getHeader().getOperation();
        
        Response<?> res;
        
        switch (op) {
            case RequestOperations.LOGIN:
                res = LoginHandler.handle(jsonRequest);
                return res;
            case RequestOperations.LOGOUT:
                res = LogoutHandler.handle(jsonRequest);
                return res;
            case RequestOperations.ADMIN_CADASTRAR_USUARIO:
                res = AdminCreateUserHandler.handle(jsonRequest);
                return res;
            case RequestOperations.ADMIN_ATUALIZAR_USUARIO:
                res = AdminUpdateUserHandler.handle(jsonRequest);
                return res;
            case RequestOperations.ADMIN_BUSCAR_USUARIO:
                res = AdminFindUserHandler.handle(jsonRequest);
                return res;
            case RequestOperations.ADMIN_BUSCAR_USUARIOS:
                res = AdminFindUsersHandler.handle(jsonRequest);
                return res;
            case RequestOperations.ADMIN_DELETAR_USUARIO:
                res = AdminDeleteUserHandler.handle(jsonRequest);
                return res;
            case RequestOperations.CADASTRAR_USUARIO:
                res = CreateUserHandler.handle(jsonRequest);
                return res;
            case RequestOperations.ATUALIZAR_USUARIO:
                res = UpdateUserHandler.handle(jsonRequest);
                return res;
            case RequestOperations.BUSCAR_USUARIO:
                res = FindUserHandler.handle(jsonRequest);
                return res;
            case RequestOperations.DELETAR_USUARIO:
                res = DeleteUserHandler.handle(jsonRequest);
                return res;
            default:
                throw new BadRequestException(op + " operation is unsupported.");
        }
    }
}
