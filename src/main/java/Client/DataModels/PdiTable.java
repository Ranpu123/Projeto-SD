/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client.DataModels;

import Model.PDI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vinic
 */
public class PdiTable extends AbstractTableModel {
    
    List<PDI> pdis = new ArrayList<>();
    String colunas[] = {"Id", "Nome", "Posicao", "Aviso", "Acessível"};
    
    public PDI getPDI(int row){
        return pdis.get(row);
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return pdis.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return pdis.get(rowIndex).getId();
            case 1:
                return pdis.get(rowIndex).getNome();
            case 2:
                return ("["+pdis.get(rowIndex).getPosicao().getX()+", "+pdis.get(rowIndex).getPosicao().getY()+"]");
            case 3:
                return pdis.get(rowIndex).getAviso();
            case 4:
                return pdis.get(rowIndex).getAcessivel();
            default:
                throw new AssertionError();
        }
    }
    
    public void updateData(List<PDI> newData) {
        pdis.clear();
        pdis.addAll(newData);
        fireTableDataChanged();
    }
    
}
