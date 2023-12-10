/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Misc.DatabaseGraph;
import Model.PDI;
import Model.Posicao;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Record;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.types.Node;

import static org.neo4j.driver.Values.parameters;
import org.neo4j.driver.types.Path;

/**
 *
 * @author vinic
 */
public class PDIDAO {
    private final DatabaseGraph driver;
    
    public PDIDAO() {
        this.driver = new DatabaseGraph();
    }
    
    public void close() {
        driver.close();
    }
    
    public PDI create(PDI pdi){
        
        Session session = driver.getSession();
        String query = "CREATE (pdi:PDI {id: $id ,nome: $nome, aviso: $aviso, acessivel: $acessivel, " +
                "x: $x, y: $y, z: $z}) RETURN pdi.id AS id";

        Result result = session.run(query, parameters(
            "id", Instant.now().toEpochMilli(),
            "nome", pdi.getNome(),
            "aviso", pdi.getAviso(),
            "acessivel", pdi.getAcessivel(),
            "x", pdi.getPosicao().getX(),
            "y", pdi.getPosicao().getY(),
            "z", pdi.getPosicao().getZ()
        ));

        if (result.hasNext()) {
            Record record = result.next();
            pdi.setId(record.get("id").asLong());
            return pdi;
        }

        return null;
    }
    
    public PDI update(PDI pdi) {
        Session session = driver.getSession();
        return session.executeWrite(tx -> {
            StringBuilder cypherQuery = new StringBuilder("MATCH (pdi:PDI{id: $id}) SET ");

            Map<String, Object> params = new HashMap<>();
            params.put("id", pdi.getId());

            if (pdi.getNome() != null) {
                cypherQuery.append("pdi.nome = $nome, ");
                params.put("nome", pdi.getNome());
           }

            if (pdi.getAviso() != null) {
                cypherQuery.append("pdi.aviso = $aviso, ");
                params.put("aviso", pdi.getAviso());
            }

            if (pdi.getAcessivel() != null) {
                cypherQuery.append("pdi.acessivel = $acessivel, ");
                params.put("acessivel", pdi.getAcessivel());
            }

            if (pdi.getPosicao() != null) {
                if(pdi.getPosicao().getX() != null){
                    cypherQuery.append("pdi.x = $x, ");
                    params.put("x", pdi.getPosicao().getX());
                }
                if(pdi.getPosicao().getY() != null){
                    cypherQuery.append("pdi.y = $y, ");
                    params.put("y", pdi.getPosicao().getY());
                }
                if(pdi.getPosicao().getZ() != null){
                    cypherQuery.append("pdi.z = $z, ");
                    params.put("z", pdi.getPosicao().getZ());
                } 
            }
     

            cypherQuery.setLength(cypherQuery.length() - 2);
            cypherQuery.append(" RETURN pdi");

            Result result = tx.run(cypherQuery.toString(), params);

            if (result.hasNext()) {
                Record record = result.next();
                Node pdiNode = record.get("pdi").asNode();
                
                PDI p = new PDI();

                p.setId(pdiNode.get("id").asLong());
                p.setNome(pdiNode.get("nome").asString());
                p.setAviso(pdiNode.get("aviso").asString());
                p.setAcessivel(pdiNode.get("acessivel").asBoolean());
                p.setPosicao(new Posicao(pdiNode.get("x").asDouble(), pdiNode.get("y").asDouble(), pdiNode.get("z").asDouble()));
                return p;
            }

            return null;
        });
    }
    
    public List<PDI> readAll(){
        
        Session session = driver.getSession();
        String query = "match (p:PDI) return (p);";
            
        List<PDI> pdis = new ArrayList<>();
            
        Result result = session.run(query);
        if(!result.hasNext()){
            return null;
        }

        while (result.hasNext()) {
            Record record = result.next();
            Node pdiNode = record.get("p").asNode();

            PDI pdi = new PDI();
            pdi.setId(pdiNode.get("id").asLong());  
            pdi.setNome(pdiNode.get("nome").asString());
            pdi.setAviso(pdiNode.get("aviso").asString());
            pdi.setAcessivel(pdiNode.get("acessivel").asBoolean());

            
            pdi.setPosicao(new Posicao(pdiNode.get("x").asDouble(), pdiNode.get("y").asDouble(), pdiNode.get("z").asDouble()));

            pdis.add(pdi);
            System.out.println(pdi.getNome());
        }
        return pdis;
    }
    
    public Long delete(Long id) {
        
        Session session = driver.getSession();
        String query = "MATCH (pdi:PDI{id: $id}) DETACH DELETE pdi RETURN \"OK\"";

        Result result = session.run(query, parameters("id", id));
        
        if(result.hasNext()){
            return id;
        }
        return null;
    }
    
    public PDI getPDI(Long idx){
        
        Session session = driver.getSession();

        String query = "MATCH (pdi:PDI {id: $id}) RETURN pdi";
        Result result = session.run(query, parameters(
            "id", idx
        ));

        if (result.hasNext()) {
            Record record = result.next();
            Node pdiNode = record.get("p").asNode();
                
            PDI pdi = new PDI();
                
            pdi.setId(pdiNode.get("id").asLong());
            pdi.setNome(pdiNode.get("nome").asString());
            pdi.setAviso(pdiNode.get("aviso").asString());
            pdi.setAcessivel(pdiNode.get("acessivel").asBoolean());
            pdi.setPosicao(new Posicao(pdiNode.get("x").asDouble(), pdiNode.get("y").asDouble(), pdiNode.get("z").asDouble()));
            return pdi;
        }

        return null;
    }
    
    public Map<String, Object> getPath(Long pdi_inicial, Long pdi_final){
        
        Session session = driver.getSession();

        String query =  "CALL gds.graph.project.cypher($tempId, 'MATCH (n:PDI) RETURN id(n) as id', 'MATCH (n)-[r:SEGMENT]->(m) WHERE r.acessivel = true RETURN id(n) AS source, id(m) as target, r.distancia as weight') YIELD nodeCount\n" +
                        "MATCH (p1:PDI{id:$id1}), (p2:PDI{id:$id2})\n" +
                        "CALL gds.shortestPath.dijkstra.stream($tempId, {sourceNode: p1, targetNode: p2, relationshipWeightProperty: 'weight'}) YIELD path\n" +
                        "WITH path CALL gds.graph.drop($tempId) YIELD graphName RETURN path, COLLECT{WITH nodes(path) as pathNodes UNWIND range(0,size(pathNodes)-2) as i WITH pathNodes[i] as a, pathNodes[i+1] as b MATCH (a)-[r:SEGMENT]->(b) RETURN r.aviso as aviso} as avisos";
        
        Result result = session.run(query, parameters(
            "id1", pdi_inicial,
            "id2", pdi_final,
            "tempId", ""+Instant.now().toEpochMilli()
        ));

        if (result.hasNext()) {
            Map<String, Object> path = result.next().asMap();
            return path;
        }

        return null;
    }
    
}
