/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

/**
 *
 * @author vinic
 */
public class AdminDeleteUserResponse implements Response<AdminDeleteUserResponse.Payload>{
    private Payload payload;

    public AdminDeleteUserResponse(String mensagem) {
        this.payload = new Payload(mensagem);
    }

    @Override
    public Payload payload() {
        return payload;
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
    }
}
