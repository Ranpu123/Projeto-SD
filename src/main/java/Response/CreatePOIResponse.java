/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import Model.Posicao;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class CreatePOIResponse implements Response<CreatePOIResponse.Payload>{
    
    private Payload payload;

    public CreatePOIResponse(Long id, String nome, Posicao posicao, String aviso, Boolean acessivel) {
        this.payload = new Payload(id, nome, posicao, aviso, acessivel);
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public static class Payload{
        private Long id;
        private String nome;
        private Posicao posicao;
        private String aviso;
        private Boolean acessivel;

        public Payload(Long id, String nome, Posicao posicao, String aviso, Boolean acessivel) {
            this.id = id;
            this.nome = nome;
            this.posicao = posicao;
            this.aviso = aviso;
            this.acessivel = acessivel;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Posicao getPosicao() {
            return posicao;
        }

        public void setPosicao(Posicao posicao) {
            this.posicao = posicao;
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
