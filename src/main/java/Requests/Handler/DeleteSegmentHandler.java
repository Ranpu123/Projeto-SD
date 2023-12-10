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
import Model.Segmento;
import Requests.DeleteSegmentRequest;
import Requests.Request;
import Response.DeleteSegmentResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
public class DeleteSegmentHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<DeleteSegmentRequest.Payload> req = gson.fromJson(jsonRequest, DeleteSegmentRequest.class);
            
            ValidationHelper.validate(req);
            
            SegmentController controller = new SegmentController();
            
            if (req == null || req.getHeader() == null || req.getPayload() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());

            String msg = controller.deletarSegmento(req.getPayload().getPdi_inicial(), req.getPayload().getPdi_final());
            
            if(msg == null){
                throw new BadRequestException(400,"Server internal error: unable to register segment");
            }
            
            DeleteSegmentResponse res = new DeleteSegmentResponse(msg);
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
