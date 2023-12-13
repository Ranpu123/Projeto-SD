/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client.DataModels;

import Model.Comando;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vinic
 */
public class RotaTable extends AbstractTableModel {
    
    List<Comando> comandos = new ArrayList<>();
    String colunas[] = {"Inicio", "Fim", "Distancia", "Direção", "Aviso"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return comandos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return comandos.get(rowIndex).getNome_inicio();
            case 1:
                return comandos.get(rowIndex).getNome_final();
            case 2:
                return comandos.get(rowIndex).getDistancia();
            case 3:
                return comandos.get(rowIndex).getDirecao();
            case 4:
                return comandos.get(rowIndex).getAviso();
            default:
                throw new AssertionError();
        }
    }
    
    public void updateData(List<Comando> newData) {
        comandos.clear();
        comandos.addAll(newData);
        fireTableDataChanged();
    }
}
