/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Exception.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import Model.User;

public class UserDAO extends GenericDAO<User> {

    public UserDAO(Connection connection) {
        super(connection);
    }

    public List<User> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM usuarios";

        return listar(sql, resultSet -> {
            User user = new User();
            user.setRegistro(resultSet.getInt("registro"));
            user.setNome(resultSet.getString("nome"));
            user.setEmail(resultSet.getString("email"));
            user.setTipo(resultSet.getBoolean("tipo"));
            return user;
        });
    }

    public void incluirUsuario(User user) throws SQLException {
        String sql = "INSERT INTO usuarios (nome,email,senha,tipo) VALUES (?, ?, ?, ?)";

        incluir(sql, statement -> {
            statement.setString(1, user.getNome());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getSenha());
            statement.setBoolean(4, user.isTipo());
        });
    }

    public void removerUsuario(User user) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE registro = ?";

        remover(sql, statement->{
            statement.setInt(1,user.getRegistro());
        });
    }

    public void atualizarUsuario(User user) throws SQLException, ServerResponseException {
        String sql = "UPDATE usuarios SET ";
        boolean hasSet = false;

        if (user.getNome() != null) {
            sql += "nome = ?, ";
            hasSet = true;
        }

        if (user.getEmail() != null) {
            sql += "email = ?, ";
            hasSet = true;
        }

        if (user.getSenha() != null) {
            sql += "senha = ?, ";
            hasSet = true;
        }

        if (user.isTipo() != null) {
            sql += "tipo = ?, ";
            hasSet = true;
        }

        if (!hasSet) {
            throw new BadRequestException(400,"Nenhum campo para atualizar foi fornecido.");
        }

        sql = sql.substring(0, sql.length() - 2);
        sql += " WHERE registro = ?";

        atualizar(sql, statement -> {
            int i = 1;
            if (user.getNome() != null) {
                statement.setString(i++, user.getNome());
            }
            if (user.getEmail() != null) {
                statement.setString(i++, user.getEmail());
            }
            if (user.getSenha() != null) {
                statement.setString(i++, user.getSenha());
            }
            if (user.isTipo()!= null) {
                statement.setBoolean(i++, user.isTipo());
            }
            statement.setInt(i, user.getRegistro());
        });
    }

    public User buscarUsuario(int registro)throws SQLException{
        String sql = "SELECT * FROM usuarios WHERE registro = ?;";
        
        return retornar(sql, (resultSet) ->{
            User user = new User();
            user.setRegistro(resultSet.getInt("registro"));
            user.setNome(resultSet.getString("nome"));
            user.setEmail(resultSet.getString("email"));
            user.setSenha(resultSet.getString("senha"));
            user.setTipo(resultSet.getBoolean("tipo"));
            user.setToken(resultSet.getString("token"));
            return user;
        }, statement ->{
            statement.setInt(1, registro);
        });
    }
    
    public User autenticar(String email, String senha)throws SQLException{
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?;";
        
        return retornar(sql, (resultSet) -> {
            User user = new User();
            user.setRegistro(resultSet.getInt("registro"));
            user.setNome(resultSet.getString("nome"));
            user.setEmail(resultSet.getString("email"));
            user.setSenha(resultSet.getString("senha"));
            user.setTipo(resultSet.getBoolean("tipo"));
            user.setToken(resultSet.getString("token"));
            return user;
        }, statement ->{
            statement.setString(1, email);
            statement.setString(2, senha);
        });
    }
    
    public void atualizarToken(int registro, String token) throws SQLException{
        String sql = "UPDATE usuarios SET token = ? WHERE registro = ?;";
        
        atualizar(sql, statement ->{
            statement.setString(1, token);
            statement.setInt(2, registro);
        });
    }
}
