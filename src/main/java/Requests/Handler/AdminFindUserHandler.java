/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;

import Controller.UserController;
import Misc.ConstraintViolated;
import Misc.ValidationHelper;
import Exception.*;
import Misc.Database;
import Misc.ValidateAdmin;
import Misc.ValidateToken;
import Model.User;

import Response.Response;
import Response.AdminFindUserResponse;
import Requests.AdminFindUserRequest;
import Requests.Request;
import com.google.gson.Gson;
import java.sql.SQLException;

public class AdminFindUserHandler {
    public static Response<?> handle(String jsonRequest) throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<AdminFindUserRequest.Payload> req = gson.fromJson(jsonRequest, AdminFindUserRequest.class);
            ValidationHelper.validate(req);
            
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());
            
            User user = controller.buscarUsuario(req.getPayload().getRegistro());
            
            if(user == null){
                throw new ResourceNotFoundException(""+req.getPayload().getRegistro());
            }
            
            AdminFindUserResponse res = new AdminFindUserResponse(user.getNome(), user.getEmail(), 
                    user.getRegistro(), user.isTipo());
            return res;
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());
        }
    }
}
