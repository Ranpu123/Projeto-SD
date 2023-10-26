/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Response;

/**
 *
 * @author vinic
 */
public class LoginResponse implements Response<LoginResponse.Payload>{
    
    private Payload payload;

    public LoginResponse(String token) {
        this.payload = new Payload(token);
    }
    
    @Override
    public Payload payload() {
        return payload;
    }
    
    public class Payload{
        private String token;

        public Payload(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
