package Requests;

import Model.Header;
import com.google.gson.JsonObject;
import jakarta.validation.constraints.NotNull;

public class LogoutRequest extends Request<LogoutRequest.Payload>{
    
    public LogoutRequest(@NotNull String token){
        super(new Header(token, RequestOperations.LOGOUT),new Payload());
    }
    
    public LogoutRequest(@NotNull Header header) {
        super(header, null);
    }
    
    public static class Payload{
        public Payload() {
        }
    }
}
