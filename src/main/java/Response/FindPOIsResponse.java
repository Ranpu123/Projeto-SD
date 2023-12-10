/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import Model.PDI;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class FindPOIsResponse  implements Response<FindPOIsResponse.Payload>{
    private Payload payload;

    public FindPOIsResponse(List<PDI> pdis) {
        this.payload = new Payload(pdis);
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public static class Payload{
        List<PDI> pdis;

        public Payload(List<PDI> pdis) {
            this.pdis = pdis;
        }

        public List<PDI> getPdis() {
            return pdis;
        }

        public void setPdis(List<PDI> pdis) {
            this.pdis = pdis;
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
