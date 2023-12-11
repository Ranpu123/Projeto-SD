/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class FindRouteRequest extends Request<FindRouteRequest.Payload>{

    public FindRouteRequest(Header header, @Valid Payload payload) {
        super(header, payload);
    }

    public FindRouteRequest(String token ,Long pdi_inicial, Long pdi_final) {
        super(new Header(token, RequestOperations.BUSCAR_ROTA), 
                new Payload(pdi_inicial, pdi_final));
    }
    
    public static class Payload{
        @NotNull(message="Campo pdi_inicial e obrigatorio.")
        Long pdi_inicial;
        @NotNull(message="Campo pdi_final e obrigatorio.")
        Long pdi_final;

        public Payload(Long pdi_inicial, Long pdi_final) {
            this.pdi_inicial = pdi_inicial;
            this.pdi_final = pdi_final;
        }

        public Long getPdi_inicial() {
            return pdi_inicial;
        }

        public void setPdi_inicial(Long pdi_inicial) {
            this.pdi_inicial = pdi_inicial;
        }

        public Long getPdi_final() {
            return pdi_final;
        }

        public void setPdi_final(Long pdi_final) {
            this.pdi_final = pdi_final;
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
