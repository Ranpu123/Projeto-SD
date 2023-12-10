package Requests;

import Model.Header;
import Model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdminFindUsersRequest extends Request<AdminFindUsersRequest.Payload>{

    public AdminFindUsersRequest(@Valid @NotBlank String token) {
        super(new Header(token, RequestOperations.ADMIN_BUSCAR_USUARIOS), new Payload());
    }    

    public AdminFindUsersRequest(Header header) {
        super(header, new Payload());
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
