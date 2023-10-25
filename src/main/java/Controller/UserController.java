/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author vinic
 */
public class UserController {
    private UserDAO dao;

    public UserController(Connection connection) {
        this.dao = new UserDAO(connection);
    }
    
    public User login(String email, String senha) throws SQLException{
        User user = dao.autenticar(email, senha);
        return user;
    }
    
    public User atualizarToken(String token, User user) throws SQLException{
        user.setToken(token);
        dao.atualizarUsuario(user);
        return user;
    }
    
    public User cadastrarUsuario(String nome, String email, String senha, boolean tipo) throws SQLException{
        User user = new User(email, senha, nome, tipo);
        dao.incluirUsuario(user);
        user = dao.autenticar(email, senha);
        return user;
    }
    
    
}
