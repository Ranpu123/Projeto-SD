/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author vinic
 */
public class AdminDeleteUserRequest extends Request<AdminDeleteUserRequest.Payload>{

    public AdminDeleteUserRequest(@NotBlank String token,@NotNull Integer registro) {
        super(new Header(token, RequestOperations.ADMIN_DELETAR_USUARIO),
                new Payload(registro));
    }

    public AdminDeleteUserRequest(@NotNull Header header, @Valid @NotNull Payload payload) {
        super(header, payload);
    }
    
    public static class Payload{
        @Positive
        private int registro;

        public Payload(int registro) {
            this.registro = registro;
        }

        public int getRegistro() {
            return registro;
        }

        public void setRegistro(int registro) {
            this.registro = registro;
        }
        
        
    }
}
