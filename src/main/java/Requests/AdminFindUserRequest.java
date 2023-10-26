/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author vinic
 */
public class AdminFindUserRequest extends Request<AdminFindUserRequest.Payload>{

    public AdminFindUserRequest(String token ,Integer registro) {
        super(new Header(token, RequestOperations.ADMIN_BUSCAR_USUARIO), new Payload(registro));
    }
    
    public AdminFindUserRequest(Header header,@Valid Payload payload) {
        super(header, payload);
    }
    
    public static class Payload{
        @Positive
        Integer registro;

        public Payload(Integer registro) {
            this.registro = registro;
        }

        public Integer getRegistro() {
            return registro;
        }

        public void setRegistro(Integer registro) {
            this.registro = registro;
        }
    }
}
