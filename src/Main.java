

import lpfastsolution.LPFastSolution;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Dijkstra.unitTest();
        //InputGraphGenerator.unitTest();
        LPFastSolution lpf = new LPFastSolution();
        //lpf.run(13,"","");
        lpf.run(3,"demandCapacity.txt","");
        //lpf.run(13,"demandCapacity.txt","unitCost.txt");
        
        
    }
}
