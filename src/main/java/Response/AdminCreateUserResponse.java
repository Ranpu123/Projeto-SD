/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

import Model.Header;

/**
 *
 * @author vinic
 */
public class AdminCreateUserResponse implements Response<AdminCreateUserResponse.Payload>{
    
    private Payload payload;
    
    public AdminCreateUserResponse(int registro, String nome, String email, boolean tipo) {
        this.payload = new Payload(registro, nome, email, tipo);
    }

    @Override
    public Payload payload() {
        return payload;
    }
    
    public static class Payload{
        private int registro;
        private String nome;
        private String email;
        private boolean tipo;

        public Payload(int registro, String nome, String email, boolean tipo) {
            this.registro = registro;
            this.nome = nome;
            this.email = email;
            this.tipo = tipo;
        }

        public int getRegistro() {
            return registro;
        }

        public void setRegistro(int registro) {
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

        public boolean isTipo() {
            return tipo;
        }

        public void setTipo(boolean tipo) {
            this.tipo = tipo;
        }
        
    }
}
