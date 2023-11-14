/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author vinic
 */
public class DeleteUserRequest extends Request<DeleteUserRequest.Payload> {

    public DeleteUserRequest(String token, String email, String senha){
        super(new Header(token, RequestOperations.DELETAR_USUARIO), new Payload(email, senha));
    }

    public DeleteUserRequest(Header header, Payload payload) {
        super(header, payload);
    }
    
    public static class Payload{
        @Email(message = "O email é obrigatório.")
        String email;
        @NotBlank
        String senha;

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
