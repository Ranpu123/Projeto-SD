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
public class Comando {
    private String nome_inicio;
    private String nome_final;
    private Double distancia;
    private String aviso;
    private String direcao;

    public Comando(String nome_inicio, String nome_final, Double distancia, String aviso, String direcao) {
        this.nome_inicio = nome_inicio;
        this.nome_final = nome_final;
        this.distancia = distancia;
        this.aviso = aviso;
        this.direcao = direcao;
    }

    public String getNome_inicio() {
        return nome_inicio;
    }

    public void setNome_inicio(String nome_inicio) {
        this.nome_inicio = nome_inicio;
    }

    public String getNome_final() {
        return nome_final;
    }

    public void setNome_final(String nome_final) {
        this.nome_final = nome_final;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
