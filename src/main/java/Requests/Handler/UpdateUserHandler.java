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
import Requests.Request;
import Requests.UpdateUserRequest;
import Response.UpdateUserResponse;
import Response.Response;
import com.google.gson.Gson;
import java.sql.SQLException;
import jwt.JwtHelper;

/**
 *
 * @author vinic
 */
public class UpdateUserHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<UpdateUserRequest.Payload> req = gson.fromJson(jsonRequest, UpdateUserRequest.class);
            ValidationHelper.validate(req);
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            
            User user = controller.atualizarUsuario(JwtHelper.getId(req.getHeader().getToken()), 
                    req.getPayload().getNome(), req.getPayload().getEmail(), req.getPayload().getSenha(), null);
            
            if(user == null){
                throw new BadRequestException(400,"Server internal error: unable to register user");
            }
            
            UpdateUserResponse res = new UpdateUserResponse(user.getRegistro(), user.getNome(), user.getEmail(), user.isTipo());
            
            return res;
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());      
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());   
        }
    }
}
