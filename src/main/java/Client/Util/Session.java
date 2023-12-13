package Client.Util;


import Model.User;

public class Session {
    private String token;
    private User user;
    private static Session session = null;

    private Session() {
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }

        return session;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}