package Response;


import Model.Segmento;
import Response.Response;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinic
 */
public class FindSegmentsResponse implements Response<FindSegmentsResponse.Payload> {
    
    private Payload payload;

    public FindSegmentsResponse(List<Segmento> segmentos) {
        this.payload = new Payload(segmentos);
    }

    @Override
    public Payload payload() {
        return payload;
    }
    
    public static class Payload{
        List<Segmento> segmentos;

        public Payload(List<Segmento> segmentos) {
            this.segmentos = segmentos;
        }

        public List<Segmento> getSegmentos() {
            return segmentos;
        }

        public void setSegmentos(List<Segmento> segmentos) {
            this.segmentos = segmentos;
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
