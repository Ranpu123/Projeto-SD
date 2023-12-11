/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import Model.Comando;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class FindRouteResponse implements Response<FindRouteResponse.Payload> {
    private Payload payload;

    public FindRouteResponse(List<Comando> comandos){
        this.payload = new Payload(comandos);
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public static class Payload{
        List<Comando> comandos;

        public Payload(List<Comando> comandos) {
            this.comandos = comandos;
        }

        public List<Comando> getComandos() {
            return comandos;
        }

        public void setComandos(List<Comando> comandos) {
            this.comandos = comandos;
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
