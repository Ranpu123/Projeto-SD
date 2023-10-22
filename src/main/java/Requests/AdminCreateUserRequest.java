/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Model.Header;

/**
 *
 * @author vinic
 */
public class AdminCreateUserRequest extends Request<AdminCreateUserRequest.Payload>{  

    public AdminCreateUserRequest(String token,String nome, String email, String senha, int registro, boolean tipo) {
        super(new Header(token, RequestOperations.ADMIN_CADASTRAR_USUARIO),
                new Payload(nome, email, senha, registro, tipo));
    }
    
    public AdminCreateUserRequest(Header header, Payload payload) {
        super(header, payload);
    }    
    
    public static class Payload{
        private String nome;
        private String email;
        private String senha;
        private int registro;
        private boolean tipo;

        public Payload(String nome, String email, String senha, int registro, boolean tipo) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
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

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public int getRegistro() {
            return registro;
        }

        public void setRegistro(int registro) {
            this.registro = registro;
        }

        public boolean isTipo() {
            return tipo;
        }

        public void setTipo(boolean tipo) {
            this.tipo = tipo;
        }
        
    }
}
