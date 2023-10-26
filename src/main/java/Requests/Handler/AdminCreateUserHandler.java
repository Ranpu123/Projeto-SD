/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;

import Controller.UserController;
import Exception.*;
import Misc.ConstraintViolated;
import Misc.Database;
import Misc.ValidateAdmin;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.User;
import Response.Response;
import Requests.AdminCreateUserRequest;
import Requests.Request;
import Response.AdminCreateUserResponse;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;
import jwt.JwtHelper;

/**
 *
 * @author vinic
 */
public class AdminCreateUserHandler{
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<AdminCreateUserRequest.Payload> req = gson.fromJson(jsonRequest, AdminCreateUserRequest.class);
            
            ValidationHelper.validate(req);
            
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());
            
            User user = controller.cadastrarUsuario(req.getPayload().getNome(), req.getPayload().getEmail()
                    , req.getPayload().getSenha(), true);
            
            if(user == null){
                throw new BadRequestException(400,"Server internal error: unable to register user");
            }
            
            AdminCreateUserResponse res = new AdminCreateUserResponse(user.getRegistro(), user.getNome(), user.getEmail(), user.isTipo());
            return res;
        } catch (JsonSyntaxException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());
        } catch (ConstraintViolated e){
            throw new BadRequestException(e.getMessage());            
        }
    }
    
}
