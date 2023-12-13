/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class PDI {
    private Long id;
    private String nome;
    private Posicao posicao;
    private String aviso;
    private Boolean acessivel;

    public PDI() {
    }
    
    public PDI(String nome, Double x, Double y, Double z, String aviso, Boolean acessivel) {
        this.nome = nome;
        this.posicao = new Posicao(x, y, z);
        this.aviso = aviso;
        this.acessivel = acessivel;
    }
    
    public PDI(Long id, String nome, Double x, Double y, Double z, String aviso, Boolean acessivel) {
        this.id = id;
        this.nome = nome;
        this.posicao = new Posicao(x, y, z);
        this.aviso = aviso;
        this.acessivel = acessivel;
    }

    public PDI(Long id, String nome, Posicao posicao, String aviso, Boolean acessivel) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return this.nome;
    }
    
}
