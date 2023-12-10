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
import Requests.FindSegmentsRequest;
import Requests.Request;
import Response.FindSegmentsResponse;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vinic
 */
public class FindSegmentsHandler {
    public static Response<?> handle(String jsonRequest)throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            Request<FindSegmentsRequest.Payload> req = gson.fromJson(jsonRequest, FindSegmentsRequest.class);
            
            ValidationHelper.validate(req);
            
            SegmentController controller = new SegmentController();
            
            if (req == null || req.getHeader() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            ValidateAdmin.check(req.getHeader());

            List<Segmento> segmentos = controller.buscarSegmentos();
            
            FindSegmentsResponse res = new FindSegmentsResponse(segmentos);
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
