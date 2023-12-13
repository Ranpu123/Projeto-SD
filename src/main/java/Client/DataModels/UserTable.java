/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client.DataModels;

import Model.Comando;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vinic
 */
public class UserTable extends AbstractTableModel{
    List<User> usuarios = new ArrayList<>();
    String colunas[] = {"Registro", "Nome", "Email", "Tipo"};

    public User getUsuario(int row) {
        return usuarios.get(row);
    }
    
    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return usuarios.get(rowIndex).getRegistro();
            case 1:
                return usuarios.get(rowIndex).getNome();
            case 2:
                return usuarios.get(rowIndex).getEmail();
            case 3:
                return usuarios.get(rowIndex).isTipo();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    public void updateData(List<User> newData) {
        usuarios.clear();
        usuarios.addAll(newData);
        fireTableDataChanged();
    }
}
