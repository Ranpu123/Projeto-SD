/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Misc.StatementSetter;
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
            user.setSenha(resultSet.getString("senha"));
            user.setTipo(resultSet.getBoolean("tipo"));
            user.setToken(resultSet.getString("token"));
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

    public void atualizarUsuario(User user) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, tipo = ?  WHERE registro = ?";
        atualizar(sql, statement -> {
            statement.setString(1,user.getNome());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getSenha());
            statement.setBoolean(4,user.isTipo());
            statement.setInt(5,user.getRegistro());
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
}
