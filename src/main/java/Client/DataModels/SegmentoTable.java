/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client.DataModels;

import Model.Segmento;
import Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vinic
 */
public class SegmentoTable extends AbstractTableModel{

    List<Segmento> segs = new ArrayList<>();
    String colunas[] = {"Inicio", "Fim", "Distancia", "Aviso", "Acessível"};
    
    public Segmento getSegmento(int row){
        return segs.get(row);
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return segs.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return segs.get(rowIndex).getPdi_inicial();
            case 1:
                return segs.get(rowIndex).getPdi_final();
            case 2:
                return segs.get(rowIndex).getDistancia();
            case 3:
                return segs.get(rowIndex).getAviso();
            case 4:
                return segs.get(rowIndex).getAcessivel();
            default:
                throw new AssertionError();
        }
    }
    
    public void updateData(List<Segmento> newData) {
        segs.clear();
        segs.addAll(newData);
        fireTableDataChanged();
    }
}
