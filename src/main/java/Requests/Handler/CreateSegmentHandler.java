/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;

import Controller.SegmentController;
import Exception.BadRequestException;
import Exception.ServerResponseException;
import Misc.ConstraintViolated;
import Misc.ValidateAdmin;
import Misc.ValidateToken;
import Misc.ValidationHelper;
import Model.PDI;
import Model.Segmento;
import Requests.CreateSegmentRequest;
import Requests.Request;
import Response.CreateSegmentResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
public class CreateSegmentHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<CreateSegmentRequest.Payload> req = gson.fromJson(jsonRequest, CreateSegmentRequest.class);
            
            ValidationHelper.validate(req);
            
            SegmentController controller = new SegmentController();
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());

            Segmento seg = controller.criarSegmento(req.getPayload().getPdi_inicial(), req.getPayload().getPdi_final(), 
                    req.getPayload().getAviso(), req.getPayload().getAcessivel());
            
            if(seg == null){
                throw new BadRequestException(400,"Server internal error: unable to register segment");
            }
            
            CreateSegmentResponse res = new CreateSegmentResponse(seg.getPdi_inicial(), seg.getPdi_final(), seg.getAviso(), seg.getAcessivel());
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
