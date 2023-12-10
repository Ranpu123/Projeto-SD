package Response;

import jakarta.validation.constraints.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdminUpdateUserResponse implements Response<AdminUpdateUserResponse.Payload>{
    
    private Payload payload;

    public AdminUpdateUserResponse(Integer registro, String nome, String email, Boolean tipo) {
        this.payload = new Payload(registro, nome, email, tipo);
    }
    
    public class Payload{
        private Integer registro;
        private String nome;
        private String email;
        private Boolean tipo;

        public Payload(Integer registro, String nome, String email, Boolean tipo) {
            this.registro = registro;
            this.nome = nome;
            this.email = email;
            this.tipo = tipo;
        }

        public Integer getRegistro() {
            return registro;
        }

        public void setRegistro(Integer registro) {
            this.registro = registro;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Boolean isTipo() {
            return tipo;
        }

        public void setTipo(Boolean tipo) {
            this.tipo = tipo;
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
