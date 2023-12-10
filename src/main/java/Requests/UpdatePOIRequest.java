/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import Model.Posicao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class UpdatePOIRequest extends Request<UpdatePOIRequest.Payload>{

    public UpdatePOIRequest(Header header,@Valid Payload payload) {
        super(header, payload);
    }

    public UpdatePOIRequest(String token, Long id, String nome, Double X, Double Y, Double Z, String aviso, Boolean acessivel){
        super(new Header(token, RequestOperations.ATUALIZAR_PDI),
                new Payload(id, nome, new Posicao(X, Y, Z), aviso, acessivel));
    }
    
    public static class Payload{
        @Positive
        @NotNull
        Long id;
        String nome;
        Posicao posicao;
        String aviso;
        Boolean acessivel;

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
