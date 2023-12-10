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
import Requests.DeletePOIRequest;
import Requests.Request;
import Response.DeletePOIResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
public class DeletePOIHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<DeletePOIRequest.Payload> req = gson.fromJson(jsonRequest, DeletePOIRequest.class);
            
            ValidationHelper.validate(req);
            
            PDIController controller = new PDIController();
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());

            Long id = controller.removerPDI(req.getPayload().getId());
            
            if(id == null){
                throw new BadRequestException(400,"Server internal error: unable to register user");
            }
            
            DeletePOIResponse res = new DeletePOIResponse("Ponto com id '"+id+"' foi deletado");
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
