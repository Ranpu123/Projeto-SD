/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.PDIDAO;
import DAO.SegmentDAO;
import Model.PDI;
import Model.Segmento;
import java.util.List;

/**
 *
 * @author vinic
 */
public class SegmentController {
    public Segmento criarSegmento(Long pdi_inicial, Long pdi_final, String aviso, Boolean acessivel){
        SegmentDAO dao = new SegmentDAO();
        Segmento seg = new Segmento(pdi_inicial, pdi_final, aviso, acessivel);
        seg = dao.create(seg);
        dao.close();
        if(seg == null){
            return null;
        }
        return seg;
    }
    
    public Segmento atualizarSegmento(Long pdi_inicial, Long pdi_final, String aviso, Boolean acessivel){
        SegmentDAO dao = new SegmentDAO();
        Segmento seg = new Segmento(pdi_inicial, pdi_final, aviso, acessivel);
        seg = dao.update(seg);
        dao.close();
        if(seg == null){
            return null;
        }
        return seg;
    }
    
    public String deletarSegmento(Long pdi_inicial, Long pdi_final){
        SegmentDAO dao = new SegmentDAO();
        Segmento seg = dao.delete(pdi_inicial, pdi_final);
        dao.close();
        if(seg == null){
            return null;
        }
        return ("Segmento entre os pontos '"+pdi_inicial+"' e '"+pdi_final+"' foi deletado");
    }
    
    public List<Segmento> buscarSegmentos(){
        SegmentDAO dao = new SegmentDAO();
        List<Segmento> segmentos = dao.readAll();
        dao.close();
        return segmentos;
    }
    
    public Segmento bucarSegmento(Long pdi_incial, Long pdi_final){
        SegmentDAO dao = new SegmentDAO();
        Segmento seg = dao.readOne(pdi_incial, pdi_final);
        dao.close();
        if(seg == null){
            return null;
        }
        return seg;
    }
}
