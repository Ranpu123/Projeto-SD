/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Misc.RowMapper;
import Misc.StatementSetter;

public class GenericDAO<T> {
    private Connection connection;

    public GenericDAO(Connection connection) {
        this.connection = connection;
    }

    public List<T> listar(String sql, RowMapper<T> rowMapper) throws SQLException {
        List<T> lista = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                T objeto = rowMapper.mapRow(resultSet);
                lista.add(objeto);
            }
        }

        return lista;
    }

    public void incluir(String sql, StatementSetter setter) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setter.setParameters(statement);
            statement.executeUpdate();
        }
    }

    public void remover(String sql, StatementSetter setter) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setter.setParameters(statement);
            statement.executeUpdate();
        }
    }

    public void atualizar(String sql, StatementSetter setter) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setter.setParameters(statement);
            statement.executeUpdate();
        }
    }

    public T retornar(String sql, RowMapper<T> rowMapper, StatementSetter setter) throws SQLException {
        
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            setter.setParameters(statement);
            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return rowMapper.mapRow(resultSet);
                }
            }
        }

        return null;
    }
}
