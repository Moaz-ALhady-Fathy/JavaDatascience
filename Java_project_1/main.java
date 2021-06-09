
import Assignments.Pyramid;
import Assignments.pyramidDAO;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Moaz AL-hady
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        pyramidDAO pdao=new pyramidDAO();
        List<Pyramid> pyramids = pdao.readPyramidFromCSV("‪D:\\ITI\\JAVA\\Assignments\\1\\pyramids.csv");
        int i = 0;
        for (Pyramid p:pyramids)
        {
            System.out.println("#"+(i++)+p);
        }
        
    }
    
}
