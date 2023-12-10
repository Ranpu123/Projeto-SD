/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class FindSegmentsRequest extends Request<FindSegmentsRequest.Payload> {

    public FindSegmentsRequest(Header header, @Valid Payload payload) {
        super(header, payload);
    }

    public FindSegmentsRequest(String token) {
        super(new Header(token, RequestOperations.BUSCAR_SEGMENTOS), null);
    }
    
    public static class Payload{

        public Payload() {
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
