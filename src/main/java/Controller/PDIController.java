/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Exception.*;
import DAO.PDIDAO;
import Model.Comando;
import Model.PDI;
import Model.Posicao;
import Model.Segmento;
import Model.Vec2;
import java.util.List;
import org.neo4j.driver.exceptions.Neo4jException;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Map;
import org.neo4j.driver.Result;
import org.neo4j.driver.types.Relationship;

/**
 *
 * @author vinic
 */
public class PDIController {
    
    public PDI criarPDI(String nome, Double x, Double y, Double z, String aviso, Boolean acessivel) throws BadRequestException{
        PDIDAO dao = new PDIDAO();
        PDI pdi = new PDI(nome, x, y, 0.0, aviso, acessivel);
        pdi = dao.create(pdi);
        dao.close();
        if(pdi == null){
            throw new BadRequestException(500, "Error happened while creating POI");
        }
        return pdi;
    }
    
    public PDI atualizarPDI(Long id, String nome, Posicao posicao, String aviso, Boolean acessivel) throws BadRequestException{
        PDIDAO dao = new PDIDAO();
        PDI pdi = new PDI(id, nome, posicao, aviso, acessivel);
        pdi = dao.update(pdi);
        dao.close();
        if(pdi == null){
            throw new BadRequestException(500, "Error happened while creating POI");
        }
        return pdi;
    }
    
    public List<PDI> buscarPDIs() throws BadRequestException{
        PDIDAO dao = new PDIDAO();
        List<PDI> pdis = dao.readAll();
        dao.close();
        return pdis;
    }
    
    public PDI buscarPDI(Long idx) throws BadRequestException{
        PDIDAO dao = new PDIDAO();
        PDI pdi = dao.getPDI(idx);
        dao.close();
        if(pdi == null){
            throw new BadRequestException(500, "Error happened while creating POI");
        }
        return pdi;
    }
    
    public Long removerPDI(Long idx) throws BadRequestException{
        PDIDAO dao = new PDIDAO();
        Long id = dao.delete(idx);
        dao.close();
        if(id == null){
            throw new BadRequestException(500, "Error happened while creating POI");
        }
        return id;
    }
    
    public List<Comando> bucarRota(Long pdi_inicio, Long pdi_final){
        PDIDAO dao = new PDIDAO();
        Map<String, Object> out = dao.getPath(pdi_inicio, pdi_final);
        if(out == null){
            return null;
        }
        Path path = (Path) out.get("path");
        
        Iterable<Node> nodes = path.nodes();
        Iterable<Relationship> rel = path.relationships();
        List<String> avisosList = (List<String>) out.get("avisos");
        
        List<PDI> pdis = new ArrayList();
        for(Node pdiNode : nodes){
            PDI pdi = new PDI();
            pdi.setId(pdiNode.get("id").asLong());
            pdi.setNome(pdiNode.get("nome").asString());
            if(pdiNode.containsKey("aviso")){
                pdi.setAviso(pdiNode.get("aviso").asString());
            }
            pdi.setAcessivel(pdiNode.get("acessivel").asBoolean());
            pdi.setPosicao(new Posicao(pdiNode.get("x").asDouble(), pdiNode.get("y").asDouble(), pdiNode.get("z").asDouble()));
            pdis.add(pdi);
        }
        
        List<Segmento> segmentos = new ArrayList();
        int i = 0;
        for(Relationship r :rel){
            Segmento seg = new Segmento();
            seg.setDistancia(r.get("cost").asDouble());
            seg.setAviso(avisosList.get(i));
            i++;
            segmentos.add(seg);
        }
        
        List<Comando> comandos = processarRota(segmentos, pdis);
        
        return comandos;
    } 
    
    private List<Comando> processarRota(List<Segmento> segmentos, List<PDI> pdis){
        List<Comando> comandos = new ArrayList();
       
        Vec2 curdir = Vec2.directionTo(pdis.get(0).getPosicao().toVec(), pdis.get(1).getPosicao().toVec());
        comandos.add(new Comando(pdis.get(0).getNome(), pdis.get(1).getNome(), segmentos.get(0).getDistancia(), segmentos.get(0).getAviso(), "Frente"));
       
        for(int i = 1; i < pdis.size()-1; i++){
            PDI org = pdis.get(i);
            PDI dst = pdis.get(i+1);

            Segmento seg = segmentos.get(i);

            Vec2 nextDir = Vec2.directionTo(org.getPosicao().toVec(), dst.getPosicao().toVec());

            double angle = Math.toDegrees(curdir.angle(nextDir));
            String dir = new String();
            
            if (angle < 5 && angle > -5) {
                dir = "Frente";
            } else if (Math.abs(angle) < 45) {
                dir = "Levemente a " + (angle > 0 ? "Esquerda" : "Direita");
            } else {
                dir = "Acentuadamente a " + (angle > 0 ? "Esquerda" : "Direita");
            }
            
            Comando comando = new Comando(org.getNome(), dst.getNome(), seg.getDistancia(), seg.getAviso(), dir);
            comandos.add(comando);
            curdir = nextDir;
        }
       
        comandos.add(new Comando(pdis.get(pdis.size()-1).getNome(), null, 0.0, "Chegou ao seu destino!", null));
       
        return comandos;
    }
}
    