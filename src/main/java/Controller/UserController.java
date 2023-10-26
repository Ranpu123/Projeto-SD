/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import java.sql.Connection;
import java.sql.SQLException;
import Exception.*;
import java.util.List;

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
    
    public User atualizarToken(String token, User user) throws SQLException, ServerResponseException{
        user.setToken(token);
        dao.atualizarToken(user.getRegistro(), token);
        return user;
    }
    
    public User cadastrarUsuario(String nome, String email, String senha, boolean tipo) throws SQLException{
        User user = new User(email, senha, nome, tipo);
        dao.incluirUsuario(user);
        user = dao.autenticar(email, senha);
        return user;
    }
    
    public User atualizarUsuario(int registro, String nome, String email, String senha, Boolean tipo) throws SQLException, ServerResponseException{
        User user = new User(email, senha, nome, tipo);
        user.setRegistro(registro);
        dao.atualizarUsuario(user);
        user = dao.buscarUsuario(registro);
        return user;
    }
    
    public User buscarUsuario(int registro)throws SQLException{
        User user = dao.buscarUsuario(registro);
        return user;
    }
    
    public List<User> listarUsuarios() throws SQLException{
        List<User> usuarios = dao.listarUsuarios();
        return usuarios;
    }
}
