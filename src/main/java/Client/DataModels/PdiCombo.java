/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client.DataModels;

import Model.PDI;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author vinic
 */
public class PdiCombo extends AbstractListModel<PDI> implements ComboBoxModel<PDI>{
    private List<PDI> pdis;
    private PDI selecionadoItem;

    public PdiCombo() {
        this.pdis = new ArrayList<>();
    }
    
    public List<PDI> getPdis(){
        return this.pdis;
    }
    
    @Override
    public int getSize() {
        return pdis.size();
    }

    @Override
    public PDI getElementAt(int index) {
        return pdis.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selecionadoItem = (PDI) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selecionadoItem;
    }
    
    public void updateData(List<PDI> novaListaPDI) {
        this.pdis.clear();
        this.pdis = novaListaPDI;
        fireContentsChanged(this, 0, novaListaPDI.size() - 1);
    }
}
