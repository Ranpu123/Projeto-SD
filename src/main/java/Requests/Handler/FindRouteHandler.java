/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;

import Controller.PDIController;
import Exception.BadRequestException;
import Exception.ServerResponseException;
import Misc.ConstraintViolated;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.Comando;
import Requests.FindRouteRequest;
import Requests.Request;
import Response.FindRouteResponse;
import Response.Response;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import jwt.JwtHelper;

/**
 *
 * @author vinic
 */
public class FindRouteHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<FindRouteRequest.Payload> req = gson.fromJson(jsonRequest, FindRouteRequest.class);
            ValidationHelper.validate(req);
            PDIController controller = new PDIController();
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            
            List<Comando> comandos = controller.bucarRota(req.getPayload().getPdi_inicial(),req.getPayload().getPdi_final());
            
            if(comandos == null){
                throw new BadRequestException(400,"Server internal error: unable to register user");
            }
            
            FindRouteResponse res = new FindRouteResponse(comandos);
            
            return res;
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());      
        } catch (SQLException e){
            throw new BadRequestException(e.getErrorCode(),e.getMessage());   
        }
    }  
}
