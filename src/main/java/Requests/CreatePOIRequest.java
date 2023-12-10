/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;
import Model.Posicao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class CreatePOIRequest extends Request<CreatePOIRequest.Payload>{

    public CreatePOIRequest(Header header, @Valid Payload payload) {
        super(header, payload);
    }

    public CreatePOIRequest(String token, String nome, Double X, Double Y, Double Z, String aviso, Boolean acessivel){
        super(new Header(token, RequestOperations.CADASTRAR_PDI),
                new Payload(nome, new Posicao(X, Y, Z), aviso, acessivel));
    }
    
    public static class Payload{
        @NotBlank(message="o campo nome e obrigatori.")
        String nome;
        @Valid
        Posicao posicao;
        String aviso;
        @NotNull
        Boolean acessivel;

        public Payload(String nome, Posicao posicao, String aviso, Boolean acessivel) {
            this.nome = nome;
            this.posicao = posicao;
            this.aviso = aviso;
            this.acessivel = acessivel;
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
