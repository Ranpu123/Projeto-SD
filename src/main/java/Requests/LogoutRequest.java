package Requests;

import Model.Header;
import com.google.gson.JsonObject;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
