/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import com.google.gson.JsonObject;

/**
 *
 * @author vinic
 */
public class LogoutRequest extends Request<LogoutRequest.Payload>{
    
    public LogoutRequest(String token){
        super(new Header(token, RequestOperations.LOGOUT),new Payload());
    }
    
    public LogoutRequest(Header header) {
        super(header, null);
    }
    
    public static class Payload{
        public Payload() {
        }
    }
}
