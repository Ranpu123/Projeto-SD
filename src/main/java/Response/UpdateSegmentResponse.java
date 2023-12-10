/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class UpdateSegmentResponse implements Response<UpdateSegmentResponse.Payload> {
    
    private Payload payload;

    public UpdateSegmentResponse(Long pdi_inicial, Long pdi_final, String aviso, Boolean acessivel) {
        this.payload = new Payload(pdi_inicial, pdi_final, aviso, acessivel);
    }

    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public class Payload{
        Long pdi_inicial;
        Long pdi_final;
        
        String aviso;
        Boolean acessivel;

        public Payload(Long pdi_inicial, Long pdi_final, String aviso, Boolean acessivel) {
            this.pdi_inicial = pdi_inicial;
            this.pdi_final = pdi_final;
            this.aviso = aviso;
            this.acessivel = acessivel;
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

        public String getAviso() {
            return aviso;
        }

        public void setAviso(String aviso) {
            this.aviso = aviso;
        }

        public Boolean getAcessivel() {
            return acessivel;
        }

        public void setAcessivel(Boolean acessivel) {
            this.acessivel = acessivel;
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
