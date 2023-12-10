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
public class FindUserResponse implements Response<FindUserResponse.Payload> {
    private Payload payload;

    public FindUserResponse(String nome, String email, Integer registro, Boolean tipo) {
        this.payload = new Payload(nome, email, registro, tipo);
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public class Payload{
        String nome;
        String email;
        Integer registro;
        Boolean tipo;

        public Payload(String nome, String email, Integer registro, Boolean tipo) {
            this.nome = nome;
            this.email = email;
            this.registro = registro;
            this.tipo = tipo;
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

        public Integer getRegistro() {
            return registro;
        }

        public void setRegistro(Integer registro) {
            this.registro = registro;
        }

        public Boolean getTipo() {
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
