/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vinic
 */
public class User {
    private int id;
    private int registro;
    private String email;
    private String senha;
    private String nome;
    private boolean tipo;
    private String token;

    public User(int registro, String email, String senha, String nome, boolean tipo, int id, String token) {
        this.id = id;
        this.registro = registro;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
        this.token = token;
    }

    public User(int registro, String email, String senha, String nome, boolean tipo) {
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

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
