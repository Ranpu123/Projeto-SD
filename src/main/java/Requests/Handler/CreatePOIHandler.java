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
import Requests.CreatePOIRequest;
import Requests.Request;
import Response.CreatePOIResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
public class CreatePOIHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<CreatePOIRequest.Payload> req = gson.fromJson(jsonRequest, CreatePOIRequest.class);
            
            ValidationHelper.validate(req);
            
            PDIController controller = new PDIController();
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());

            PDI pdi = controller.criarPDI(req.getPayload().getNome(), req.getPayload().getPosicao().getX(), req.getPayload().getPosicao().getY(),
                    req.getPayload().getPosicao().getZ(), req.getPayload().getAviso(), req.getPayload().getAcessivel());
            
            if(pdi == null){
                throw new BadRequestException(400,"Server internal error: unable to register user");
            }
            
            CreatePOIResponse res = new CreatePOIResponse(pdi.getId(),pdi.getNome(),pdi.getPosicao(),pdi.getAviso(),pdi.getAcessivel());
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
