
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
import Response.AdminFindUsersResponse;
import Requests.Request;
import Requests.AdminFindUsersRequest;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;

public class AdminFindUsersHandler {
    public static Response<?> handle(String jsonRequest) throws ServerResponseException{
        Gson gson = new Gson();
        try {
            Request<AdminFindUsersRequest.Payload> req = gson.fromJson(jsonRequest, AdminFindUsersRequest.class);
            ValidationHelper.validate(req);
            UserController controller = new UserController(Database.getConnection());
            
            if (req == null || req.getHeader() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());
            
            List<User> usuarios = controller.listarUsuarios();
            
            AdminFindUsersResponse res = new AdminFindUsersResponse(usuarios);
            return res;
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());
        }
    }
}
