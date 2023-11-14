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
import Misc.ValidateAdmin;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.User;
import Requests.DeleteUserRequest;
import Requests.Request;
import Response.DeleteUserResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;
import jwt.JwtHelper;

/**
 *
 * @author vinic
 */
public class DeleteUserHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<DeleteUserRequest.Payload> req = gson.fromJson(jsonRequest, DeleteUserRequest.class);
            
            ValidationHelper.validate(req);
            
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            
            String token = req.getHeader().getToken();
            
            User user = controller.login(req.getPayload().getEmail(), req.getPayload().getSenha());
            
            if (user == null){
                throw new UnauthorizedAccessException();
            }
            
            if(controller.quantidadeAdm()<2 && JwtHelper.getAdminStatus(token)){
                throw new BadRequestException("Usuário é o último administrador.");
            }
            
            user = controller.deletarUsuario(JwtHelper.getId(token));
            
            if (user == null){
                throw new BadRequestException("Não foi possível encontrar o usuário.");
            }
            
            DeleteUserResponse res = new DeleteUserResponse("Usuário deletado com sucesso: "+JwtHelper.getId(token));
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
