/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class AdminCreateUserRequest extends Request<AdminCreateUserRequest.Payload>{  

    public AdminCreateUserRequest(String token, String nome, String email, String senha, Boolean tipo) {
        super(new Header(token, RequestOperations.ADMIN_CADASTRAR_USUARIO),
                new Payload(nome, email, senha, tipo));
    }
    
    public AdminCreateUserRequest(Header header,@NotNull @Valid Payload payload) {
        super(header, payload);
    }    
    
    public static class Payload{
        @NotBlank(message = "Nome não pode estar vazio.")
        @Size(min = 3, max = 255, message = "Nome deve conter entre 3 e 255 caracteres.")
        private String nome;
        @NotBlank(message = "Email não pode estar vazio.")
        @Email
        private String email;
        @NotBlank(message = "Senha não pode estar vazio.")
        private String senha;
        private Boolean tipo;

        public Payload(String nome, String email, String senha, Boolean tipo) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.tipo = tipo;
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

        public Boolean isTipo() {
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
