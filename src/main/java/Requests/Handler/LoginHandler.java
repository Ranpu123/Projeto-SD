/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;
import Controller.UserController;
import Misc.Database;
import Model.User;
import Requests.LoginRequest;
import Requests.Request;
import Response.LoginResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.sql.SQLException;
import jwt.JwtHelper;

import Exception.ServerResponseException;
import Exception.BadRequestException;
import Exception.ResourceNotFoundException;

/**
 *
 * @author vinic
 */
public class LoginHandler {
    public static Response<?> handleLogin(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            LoginRequest req = gson.fromJson(jsonRequest, LoginRequest.class);
            UserController controller = new UserController(Database.getConnection());
            
            User logado = controller.login(req.getPayload().getEmail(), req.getPayload().getSenha());
            
            if(logado == null){
                throw new ResourceNotFoundException(req.getPayload().getEmail());
            }
            
            String token = JwtHelper.createJWT(logado.isTipo(), logado.getRegistro());
            controller.atualizarToken(token, logado);
            LoginResponse res = new LoginResponse(token);
            
            return res;
        } catch (JsonSyntaxException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SQLException e){
            throw new BadRequestException(500,"Internal Server error: "+e.getMessage());
        }
    }
}
