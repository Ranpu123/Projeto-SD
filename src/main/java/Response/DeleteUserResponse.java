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
public class DeleteUserResponse implements Response<DeleteUserResponse.Payload>{
    private Payload payload;

    public DeleteUserResponse(String mensagem) {
        this.payload = new Payload(mensagem);
    }
    
    public static class Payload{
        String mensagem;

        public Payload(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
