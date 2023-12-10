package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdminUpdateUserRequest extends Request<AdminUpdateUserRequest.Payload>{

    public AdminUpdateUserRequest(String token, Integer registro, String nome, String email, String senha, Boolean tipo) {
        super(new Header(token,RequestOperations.ADMIN_ATUALIZAR_USUARIO), 
                new Payload(registro, nome, email, senha, tipo));
    }
    
    public AdminUpdateUserRequest(Header header,@NotNull @Valid Payload payload) {
        super(header, payload);
    }

    public static class Payload{
        @Positive
        @NotNull
        private Integer registro;
        @Size(min = 3, max = 255, message = "Nome deve conter entre 3 e 255 caracteres.")
        private String nome;
        @Email
        private String email;
        private String senha;
        private Boolean tipo;

        public Payload(Integer registro, String nome, String email, String senha, Boolean tipo) {
            this.registro = registro;
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.tipo = tipo;
        }

        public Integer getRegistro() {
            return registro;
        }

        public void setRegistro(Integer registro) {
            this.registro = registro;
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
