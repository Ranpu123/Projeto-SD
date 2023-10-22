/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests.Handler;
import Controller.UserController;
import Misc.Database;
import Model.User;
import Requests.LoginRequest;
import Requests.LogoutRequest;
import Response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.sql.SQLException;
import jwt.JwtHelper;
import Response.LogoutResponse;

import Exception.*;
import Misc.ValidateAdmin;
import Misc.ValidateToken;
/**
 *
 * @author vinic
 */
public class LogoutHandler {
    public static Response<?> hangleLogout(String jsonRequest) throws ServerResponseException{
        Gson gson = new Gson();
        
        try {
            LogoutRequest req = gson.fromJson(jsonRequest, LogoutRequest.class);
            
            if (req == null || req.getHeader() == null){
                throw new BadRequestException(500,"Server internal error: unable to get request.");
            }
            
            ValidateToken.check(req.getHeader());
            
            LogoutResponse res = new LogoutResponse("desconectado");
            
            return res;
        } catch (JsonSyntaxException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
