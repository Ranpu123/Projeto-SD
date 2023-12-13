/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;

import Controller.PDIController;
import Exception.BadRequestException;
import Exception.ServerResponseException;
import Misc.ConstraintViolated;
import Misc.ValidateAdmin;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.PDI;
import Requests.Request;
import Requests.FindPOIsRequest;
import Response.Response;
import Response.FindPOIsResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vinic
 */
public class FindPOIsHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<FindPOIsRequest.Payload> req = gson.fromJson(jsonRequest, FindPOIsRequest.class);
            
            ValidationHelper.validate(req);
            
            PDIController controller = new PDIController();
            
            if (req == null || req.getHeader() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            //ValidateAdmin.check(req.getHeader());

            List<PDI> pdis = controller.buscarPDIs();
            
            FindPOIsResponse res = new FindPOIsResponse(pdis);
            return res;
        } catch (JsonSyntaxException e) {
            throw new BadRequestException(e.getMessage());
        } catch (ConstraintViolated e){
            throw new BadRequestException(e.getMessage());            
        } catch (SQLException e) {
            throw new BadRequestException(e.getMessage());   
        }
    }
}
