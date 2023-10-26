package Requests.Handler;

import Controller.UserController;
import Exception.*;
import Misc.ConstraintViolated;
import Misc.Database;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.User;

import Requests.CreateUserRequest;
import Response.CreateUserResponse;
import Requests.Request;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;

public class CreateUserHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<CreateUserRequest.Payload> req = gson.fromJson(jsonRequest, CreateUserRequest.class);
            ValidationHelper.validate(req);
            
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            User user = controller.cadastrarUsuario(req.getPayload().getNome(), req.getPayload().getEmail(),
                    req.getPayload().getSenha(), false);
            
            CreateUserResponse res = new CreateUserResponse(user.getRegistro(), user.getNome(),
                    user.getEmail(), user.isTipo());
            
            return res;
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());
        } catch (JsonSyntaxException e){
            throw new BadRequestException(e.getMessage());
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());
        }
    }
}
