package Response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogoutResponse implements Response<LogoutResponse.Payload>{
    
    private Payload payload;

    public LogoutResponse(String mensagem){
        payload = new Payload(mensagem);
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public class Payload{
        private String mensagem;

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
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
