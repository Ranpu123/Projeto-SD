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
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        @NotNull
        private Integer registro;

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
