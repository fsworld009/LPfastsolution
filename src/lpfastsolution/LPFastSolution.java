/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lpfastsolution;

/**
 *
 * @author WorldFS
 */
public class LPFastSolution {
    public void run(){
        InputGraphGenerator igg = new InputGraphGenerator();
        igg.generate(4);
        igg.print();
        int[][] unitCost = igg.getUnitCost();
        int[][] demand = igg.getDemand();
        
        
        Dijkstra dijkstra = new Dijkstra(unitCost);
        //dijkstra.inputGraph();
        int numOfNodes = 35;

        
        int ix, jx, kx;
        int[][] plannedCost = new int[numOfNodes][numOfNodes];
        
        int[] shortestPath;
        
        for(ix=0;ix<numOfNodes;ix++){              
            for(jx=ix;jx<numOfNodes;jx++){
                if(ix != jx && demand[ix][jx] != 0){
                    
                    shortestPath = dijkstra.run(ix, jx, false);
                    for(kx=0;kx<shortestPath.length-1;kx++){
                        plannedCost[shortestPath[kx]][shortestPath[kx+1]] += unitCost[shortestPath[kx]][shortestPath[kx+1]]*demand[ix][jx];

                    }
               }
            }
        }
        
        for(ix=0;ix<numOfNodes;ix++){              
            for(jx=0;jx<numOfNodes;jx++){
                if(jx<ix){
                    //symmetry
                    plannedCost[jx][ix] = plannedCost[ix][jx];
                }
            }
        }
    }
}
