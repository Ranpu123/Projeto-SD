/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author vinic
 */
public class FindUserRequest extends Request<FindUserRequest.Payload>{

    public FindUserRequest(@Valid @NotBlank String token) {
        super(new Header(token, RequestOperations.BUSCAR_USUARIO), new FindUserRequest.Payload());
    }
    
     public FindUserRequest(Header header,@Valid Payload payload) {
        super(header, payload);
    }
    
    public static class Payload{
        public Payload() {
        }
    }
}
