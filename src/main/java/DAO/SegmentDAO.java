/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Misc.DatabaseGraph;
import Model.Segmento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import static org.neo4j.driver.Values.parameters;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

/**
 *
 * @author vinic
 */
public class SegmentDAO {
    private final DatabaseGraph driver;
    
    public SegmentDAO() {
        this.driver = new DatabaseGraph();
    }
    
    public void close() {
        driver.close();
    }
    
    public Segmento create(Segmento segmento){
        try (Session session = driver.getSession()) {
            String query = "MATCH (p1:PDI{id:$id1}), (p2:PDI{id:$id2}) "
                    + "WITH p1, p2, point.distance(point({x:p1.x, y:p1.y, z:p1.z}),point({x:p2.x, y:p2.y, z:p2.z})) as dist "
                    + "CREATE (p1)-[r:SEGMENT{aviso:$aviso, distancia:dist, acessivel:$acessivel}]->(p2) "
                    + "CREATE (p2)-[r2:SEGMENT{aviso:$aviso, distancia:dist, acessivel:$acessivel}]->(p1) "
                    + "RETURN r , p1, p2";
            Result result = session.run(query, parameters(
                "id1", segmento.getPdi_inicial(),
                "id2", segmento.getPdi_final(),
                "aviso", segmento.getAviso(),
                "acessivel", segmento.getAcessivel()
            ));

            if (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Relationship segNode = record.get("r").asRelationship();
                Node p1 = record.get("p1").asNode();
                Node p2 = record.get("p2").asNode();
                
                Segmento seg = new Segmento();
                seg.setPdi_inicial(p1.get("id").asLong());
                seg.setPdi_final(p2.get("id").asLong());
                seg.setAviso(segNode.get("aviso").asString());
                seg.setDistancia(segNode.get("distancia").asDouble());
                seg.setAcessivel(segNode.get("acessivel").asBoolean());

                return segmento;
            }

            return null;
        }
    }
    
    
    public Segmento update(Segmento segmento){
        try (Session session = driver.getSession()) {
            return session.executeWrite(tx -> {
                StringBuilder cypherQuery = new StringBuilder("match (p1:PDI{id:$id1})-[r:SEGMENT]-(p2:PDI{id:$id2})");

                 Map<String, Object> params = new HashMap<>();
                    params.put("id1", segmento.getPdi_inicial());
                    params.put("id2", segmento.getPdi_final());

                    if (segmento.getAviso() != null || segmento.getAcessivel() != null){
                        cypherQuery.append("SET ");
                    }
                    
                    if (segmento.getAviso() != null) {
                        cypherQuery.append("r.aviso = $aviso, ");
                        params.put("aviso", segmento.getAviso());
                    }

                    if (segmento.getAcessivel() != null) {
                        cypherQuery.append("r.acessivel = $acessivel, ");
                        params.put("acessivel", segmento.getAcessivel());
                    }

                    /*if (segmento.getDistancia() != null) {
                        cypherQuery.append("r.distancia = $distancia, ");
                        params.put("distancia", segmento.getDistancia());
                    }*/

                    if (segmento.getAviso() != null || segmento.getAcessivel() != null){
                        cypherQuery.setLength(cypherQuery.length() - 2);
                    }
                    
                    cypherQuery.append(" RETURN r, p1, p2");

                    Result result = tx.run(cypherQuery.toString(), params);
                    
                if (result.hasNext()) {
                    org.neo4j.driver.Record record = result.next();
                    Relationship segNode = record.get("r").asRelationship();
                    Node p1 = record.get("p1").asNode();
                    Node p2 = record.get("p2").asNode();

                    Segmento seg = new Segmento();
                    seg.setPdi_inicial(p1.get("id").asLong());
                    seg.setPdi_final(p2.get("id").asLong());
                    seg.setAviso(segNode.get("aviso").asString());
                    seg.setDistancia(segNode.get("distancia").asDouble());
                    seg.setAcessivel(segNode.get("acessivel").asBoolean());
                    
                    return segmento;
                }

                return null;
            });
        }
    }
    
    public Segmento delete(Long pdi_inicial, Long pdi_final){
        try (Session session = driver.getSession()) {
            String query = "MATCH (p1:PDI{id:$id1}), (p2:PDI{id:$id2}), (p1)-[r:SEGMENT]-(p2) DETACH DELETE r RETURN p1,p2";

            Result result = session.run(query, parameters(
                "id1", pdi_inicial,
                "id2", pdi_final
            ));
            Segmento seg = new Segmento();
            if (result.hasNext()) {
                seg.setPdi_inicial(pdi_inicial);
                seg.setPdi_final(pdi_final);
                return seg;
            }

            return null;
        }
    }
    
    public List<Segmento> readAll() {
        
        try (Session session = driver.getSession()) {
            String query = "MATCH (p1:PDI)-[r:SEGMENT]->(p2:PDI) RETURN r, p1, p2";
            
            List<Segmento> segmentos = new ArrayList<>();
            
            Result result = session.run(query);

            while (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Relationship segNode = record.get("r").asRelationship();
                Node p1 = record.get("p1").asNode();
                Node p2 = record.get("p2").asNode();
                
                Segmento seg = new Segmento();
                seg.setPdi_inicial(p1.get("id").asLong());
                seg.setPdi_final(p2.get("id").asLong());
                if(segNode.containsKey("aviso")){
                    seg.setAviso(segNode.get("aviso").asString());
                }
                seg.setDistancia(segNode.get("distancia").asDouble());
                seg.setAcessivel(segNode.get("acessivel").asBoolean());
                
                segmentos.add(seg);
            }
            return segmentos;
        }
    }
    public Segmento readOne(Long pdi_inicial, Long pdi_final) {
        
        try (Session session = driver.getSession()) {
            String query = "MATCH (p1:PDI)-[r:SEGMENT]->(p2:PDI) RETURN r, p1, p2";
            
            Result result = session.run(query);

            if (result.hasNext()) {
                org.neo4j.driver.Record record = result.next();
                Relationship segNode = record.get("r").asRelationship();
                Node p1 = record.get("p1").asNode();
                Node p2 = record.get("p2").asNode();
                
                Segmento seg = new Segmento();
                seg.setPdi_inicial(p1.get("id").asLong());
                seg.setPdi_final(p2.get("id").asLong());
                
                if(segNode.containsKey("aviso")){
                    seg.setAviso(segNode.get("aviso").asString());
                }
                
                seg.setDistancia(segNode.get("distancia").asDouble());
                seg.setAcessivel(segNode.get("acessivel").asBoolean());
                
                return seg;
            }
            return null;
        }
    }
    
}
