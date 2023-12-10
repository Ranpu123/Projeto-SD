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
public class DeletePOIRequest extends Request<DeletePOIRequest.Payload>{

    public DeletePOIRequest(Header header,@Valid Payload payload) {
        super(header, payload);
    }

    public DeletePOIRequest(String token, Long id) {
        super(new Header(token, RequestOperations.DELETAR_PDI), new Payload(id));
    }
    
    public static class Payload{
        @Positive
        @NotNull
        Long id;

        public Payload(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
