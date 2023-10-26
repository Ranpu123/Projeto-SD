package Response;

import Model.User;
import java.util.List;

public class AdminFindUsersResponse implements Response<AdminFindUsersResponse.Payload>{
    private Payload payload;
    
    public AdminFindUsersResponse(List<User> usuarios){
        this.payload = new Payload(usuarios);
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public static class Payload{
        private List<User> usuarios;

        public Payload(List<User> usuarios) {
            this.usuarios = usuarios;
        }

        public List<User> getUsuarios() {
            return usuarios;
        }

        public void setUsuarios(List<User> usuarios) {
            this.usuarios = usuarios;
        }
    }
}
