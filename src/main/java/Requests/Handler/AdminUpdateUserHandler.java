/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;

import Controller.UserController;
import Exception.BadRequestException;
import Exception.ServerResponseException;
import Misc.ConstraintViolated;
import Misc.Database;
import Misc.ValidateAdmin;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.User;
import Response.Response;
import Requests.AdminUpdateUserRequest;
import Requests.Request;
import Response.AdminCreateUserResponse;
import Response.AdminUpdateUserResponse;
import com.auth0.jwt.JWT;
import com.google.gson.Gson;
import java.sql.SQLException;
import jwt.JwtHelper;

/**
 *
 * @author vinic
 */
public class AdminUpdateUserHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<AdminUpdateUserRequest.Payload> req = gson.fromJson(jsonRequest, AdminUpdateUserRequest.class);
            ValidationHelper.validate(req);
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());
            
            
            if(controller.quantidadeAdm() < 2 && req.getPayload().getTipo() == false && JwtHelper.getId(req.getHeader().getToken()) == req.getPayload().getRegistro()){
                throw new BadRequestException(500,"Não é possível remover privilégios do último administrador.");
            }
            
            User user = controller.atualizarUsuario(req.getPayload().getRegistro(), 
                    req.getPayload().getNome(), req.getPayload().getEmail(), req.getPayload().getSenha(), req.getPayload().getTipo());
            
            if(user == null){
                throw new BadRequestException(400,"Server internal error: unable to register user");
            }
            
            AdminUpdateUserResponse res = new AdminUpdateUserResponse(user.getRegistro(), user.getNome(), user.getEmail(), user.isTipo());
            
            return res;
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());      
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());   
        }
    }
}
