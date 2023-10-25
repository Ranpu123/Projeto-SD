package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest extends Request<LoginRequest.Payload> {
    
    public LoginRequest(String email, String senha) {
        super(new Header(null, RequestOperations.LOGIN), new Payload(email, senha));
    }
    
    public LoginRequest(Header header, LoginRequest.Payload payload) {
        super(header, payload);
    }
    
    public static class Payload{
        @Email
        private String email;
        @NotBlank(message = "A senha não pode estar vazia.")
        private String senha;

        public Payload(String email, String senha) {
            this.email = email;
            this.senha = senha;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
    }
}
