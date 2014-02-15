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
    public void run(int kx, String demandCapacityFilename, String unitCostFilename){
        int[][] unitCost;
        int[][] demandCapacity;
        
        InputGraphGenerator igg = new InputGraphGenerator();
        
        
        //igg.generate(kx);
        igg.generateDemandCapacity(kx);
        igg.generateUnitCost(kx);
        igg.print();
        unitCost = igg.getUnitCost();
        demandCapacity = igg.getDemandCapacity();
        
        FileProcessor.writeMatrix(demandCapacity, "demandCapacity.txt");
        FileProcessor.writeMatrix(unitCost, "unitCost.txt");
        
        FileProcessor.writeGML(demandCapacity, "demandCapacityGML.txt");
        FileProcessor.writeGML(unitCost, "unitCostGML.txt");
        
        Dijkstra dijkstra = new Dijkstra(unitCost);
        //dijkstra.inputGraph();
        int numOfNodes = 35;

        
        int ix, jx, zx;
        int[][] plannedCost = new int[numOfNodes][numOfNodes];
        int[][] plannedCapacity = new int[numOfNodes][numOfNodes];
        
        int[] shortestPath;
        
        int nodeOffset1, nodeOffset2;
        
        for(ix=0;ix<numOfNodes;ix++){              
            for(jx=ix;jx<numOfNodes;jx++){
                if(ix != jx && demandCapacity[ix][jx] != 0){
                    
                    shortestPath = dijkstra.run(ix, jx, false);
                    for(zx=0;zx<shortestPath.length-1;zx++){
                        //nodeOffset1 is always < nodeOffset2
                        if(shortestPath[zx]<=shortestPath[zx+1]){
                            nodeOffset1 = shortestPath[zx];
                            nodeOffset2 = shortestPath[zx+1];
                        }else{
                            nodeOffset1 = shortestPath[zx+1];
                            nodeOffset2 = shortestPath[zx];
                        }
                        plannedCost[nodeOffset1][nodeOffset2] += unitCost[nodeOffset1][nodeOffset2]*demandCapacity[ix][jx];
                        plannedCapacity[nodeOffset1][nodeOffset2] += demandCapacity[ix][jx];

                    }
               }
            }
        }
        
        
        System.out.printf("====Result Capacity Graph====\n    ");
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
        }
        System.out.println();
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
            for(jx=0;jx<numOfNodes;jx++){
                if(jx<ix){
                    System.out.printf("    ");
                }else{
                    System.out.printf("%3d ", plannedCapacity[ix][jx]);
                }
                
            }
            System.out.println();
        }
        
        int totalCost=0;
        
        System.out.printf("====Result Cost Graph====\n    ");
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
        }
        System.out.println();
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
            for(jx=0;jx<numOfNodes;jx++){
                if(jx<ix){
                    System.out.printf("    ");
                }else{
                    System.out.printf("%3d ", plannedCost[ix][jx]);
                    totalCost+=plannedCost[ix][jx];
                }
                
            }
            System.out.println();
        }
        System.out.printf("total cost: %d\n",totalCost);
        
        FileProcessor.writeMatrix(plannedCost, "plannedCost.txt");
        FileProcessor.writeMatrix(plannedCapacity, "plannedCapacity.txt");
        
        FileProcessor.writeGML(plannedCost, "plannedCostGML.txt");
        FileProcessor.writeGML(plannedCapacity, "plannedCapacityGML.txt");
    }
}
