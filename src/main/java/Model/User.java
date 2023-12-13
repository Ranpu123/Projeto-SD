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
public class User {
    private int registro;
    private String email;
    private String senha;
    private String nome;
    private Boolean tipo;
    private String token;

    public User(int registro, String email, String senha, String nome, Boolean tipo, String token) {
        this.registro = registro;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
        this.token = token;
    }

    public User(String email, String senha, String nome, Boolean tipo) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
    }
    
    public User(int registro, String email, String senha, String nome, Boolean tipo) {
        this.registro = registro;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
    }

    public User(){
    }
    
    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean isTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
