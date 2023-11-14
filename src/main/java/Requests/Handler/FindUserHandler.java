/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;

import Controller.UserController;
import Exception.*;
import Exception.ServerResponseException;
import Misc.ConstraintViolated;
import Misc.Database;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.User;
import Requests.Request;
import Requests.FindUserRequest;
import Response.Response;
import Response.FindUserResponse;
import com.google.gson.Gson;
import java.sql.SQLException;
import jwt.JwtHelper;

/**
 *
 * @author vinic
 */
public class FindUserHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<FindUserRequest.Payload> req = gson.fromJson(jsonRequest, FindUserRequest.class);
            ValidationHelper.validate(req);
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            
            User user = controller.buscarUsuario(JwtHelper.getId(req.getHeader().getToken()));
            
            if(user == null){
                throw new BadRequestException(400,"Server internal error: unable to register user");
            }
            
            FindUserResponse res = new FindUserResponse(user.getNome(), user.getEmail(), user.getRegistro(), user.isTipo());
            
            return res;
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());      
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());   
        }
    }
}
