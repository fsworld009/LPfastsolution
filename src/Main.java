
import lpfastsolution.Dijkstra;
import lpfastsolution.InputGraphGenerator;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Dijkstra.unitTest();
        //InputGraphGenerator.unitTest();
        InputGraphGenerator igg = new InputGraphGenerator();
        igg.generate(4);
        igg.print();
        
        Dijkstra dijkstra = new Dijkstra(igg.getUnitCost());
        dijkstra.run(0, 34, false);
        //dijkstra.inputGraph();
        
        
        
    }
}
