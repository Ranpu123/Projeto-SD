package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateUserRequest extends Request<CreateUserRequest.Payload>{

    public CreateUserRequest(Header header, @Valid Payload payload) {
        super(header, payload);
    }

    public CreateUserRequest(String nome, String email, String senha) {
        super(new Header(null, RequestOperations.CADASTRAR_USUARIO), new Payload(nome, email, senha));
    }
    
    public static class Payload{
        @NotBlank(message = "O nome não pode estar vazio.")
        String nome;
        @NotBlank(message = "O email não pode estar vazio")
        @Email
        String email;
        @NotBlank(message = "A senha não pode estar vazia")
        String senha;
        Boolean tipo = false;
        
        public Payload(String nome, String email, String senha) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
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

        public Boolean getTipo() {
            return tipo;
        }

        public void setTipo(Boolean tipo) {
            this.tipo = tipo;
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
