/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        @NotNull
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
