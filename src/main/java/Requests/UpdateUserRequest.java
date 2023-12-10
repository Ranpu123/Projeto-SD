/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class UpdateUserRequest extends Request<UpdateUserRequest.Payload>{
    
    public UpdateUserRequest(String token, String nome, String email, String senha) {
        super(new Header(token,RequestOperations.ATUALIZAR_USUARIO), 
                new Payload(nome, email, senha));
    }
    
    public UpdateUserRequest(Header header,@NotNull @Valid Payload payload) {
        super(header, payload);
    }
    
    public static class Payload{
        @Size(min = 3, max = 255, message = "Nome deve conter entre 3 e 255 caracteres.")
        private String nome;
        @Email
        private String email;
        private String senha;

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
