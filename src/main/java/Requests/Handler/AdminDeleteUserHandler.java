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
import Requests.AdminCreateUserRequest;
import Requests.AdminDeleteUserRequest;
import Requests.Request;
import Response.AdminDeleteUserResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
public class AdminDeleteUserHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<AdminDeleteUserRequest.Payload> req = gson.fromJson(jsonRequest, AdminDeleteUserRequest.class);
            
            ValidationHelper.validate(req);
            
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());
            
            if(controller.quantidadeAdm()>1){
                User user = controller.deletarUsuario(req.getPayload().getRegistro());
                if (user == null){
                    throw new BadRequestException("Não foi possível encontrar o usuário.");
                }
            }else{
                throw new BadRequestException("Usuário é o último administrador.");
            }
            
            AdminDeleteUserResponse res = new AdminDeleteUserResponse("Usuário deletado com sucesso: "+req.getPayload().getRegistro());
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
