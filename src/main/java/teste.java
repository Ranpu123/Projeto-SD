
import Controller.PDIController;
import DAO.PDIDAO;
import Model.Comando;
import Model.PDI;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vinic
 */
public class teste {
    public static void main(String[] args) {
        PDIController con = new PDIController();
        //con.bucarRota(1701883922416L, 784654L);
        
        List<Comando> coma = con.bucarRota(1702173385194L, 1702173666382L);
        
        for (Comando com : coma){
            System.err.println(com.toString());
        }
    }
}
