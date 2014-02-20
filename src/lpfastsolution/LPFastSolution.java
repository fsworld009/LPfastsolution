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
    public static final int numOfNodes = 35;
    public void run(int kx, String demandCapacityFilename, String unitCostFilename){
        int[][] unitCost;
        int[][] demandCapacity;
        
        
        InputGraphGenerator igg = new InputGraphGenerator();
        
        
       //only used for debug
        if(!demandCapacityFilename.equals("")){
            demandCapacity = FileProcessor.readMatrix(demandCapacityFilename);
        }else{
            igg.generateDemandCapacity(kx);
            demandCapacity = igg.getDemandCapacity();
        }
        
        if(!unitCostFilename.equals("")){
            unitCost = FileProcessor.readMatrix(unitCostFilename);
        }else{
            igg.generateUnitCost(kx);
            unitCost = igg.getUnitCost();
        
        }
        
        

        System.out.println(print("Capacity Demand",demandCapacity));
        System.out.println(print("Unit Link Cost",unitCost));
        //print(demandCapacity, unitCost);
        
        FileProcessor.writeMatrix(demandCapacity, "demandCapacity.txt");
        FileProcessor.writeMatrix(unitCost, "unitCost.txt");
        
        FileProcessor.writeGML(demandCapacity, "demandCapacityGML.txt");
        FileProcessor.writeGML(unitCost, "unitCostGML.txt");
        
        Dijkstra dijkstra = new Dijkstra(unitCost);
        //dijkstra.inputGraph();
        //int numOfNodes = 35;

        
        int ix, jx, zx;
        int[][] plannedCost = new int[numOfNodes][numOfNodes];
        int[][] plannedCapacity = new int[numOfNodes][numOfNodes];
        
        int[] shortestPath;
        
        int nodeOffset1, nodeOffset2;
        
        for(ix=0;ix<numOfNodes;ix++){              
            for(jx=0;jx<numOfNodes;jx++){
                if(ix != jx && demandCapacity[ix][jx] != 0){
                    shortestPath = dijkstra.run(ix, jx, false);
                    for(zx=0;zx<shortestPath.length-1;zx++){
                        nodeOffset1 = shortestPath[zx];
                        nodeOffset2 = shortestPath[zx+1];
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
                System.out.printf("%3d ", plannedCapacity[ix][jx]);               
            }
            System.out.println();
        }
        
        int totalCost=0;
        int usedLinkCounter = 0;
        
        System.out.printf("====Result Cost Graph====\n    ");
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
        }
        System.out.println();
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
            for(jx=0;jx<numOfNodes;jx++){
                System.out.printf("%3d ", plannedCost[ix][jx]);
                totalCost+=plannedCost[ix][jx];
                if(plannedCost[ix][jx] != 0){
                    usedLinkCounter++;
                }
                
            }
            System.out.println();
        }
        System.out.printf("number of used links: %d\n",usedLinkCounter);
        System.out.printf("total cost: %d\n",totalCost);
        System.out.printf("network density: %f\n",((double)usedLinkCounter)/((double)(numOfNodes*(numOfNodes-1))));
        
        FileProcessor.writeMatrix(plannedCost, "plannedCost.txt");
        FileProcessor.writeMatrix(plannedCapacity, "plannedCapacity.txt");
        
        FileProcessor.writeGML(plannedCost, "plannedCostGML.txt");
        FileProcessor.writeGML(plannedCapacity, "plannedCapacityGML.txt");
    }
    
    public void print(int[][] demandCapacity, int[][] unitCost){
        int ix,jx;
        System.out.printf("====Capacity Demand====\n    ");
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
        }
        System.out.println();
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
            for(jx=0;jx<numOfNodes;jx++){
                System.out.printf("%3d ", demandCapacity[ix][jx]);
            }
            System.out.println();
        }
        
        System.out.printf("\n\n====Unit Link Cost====\n    ");
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
        }
        System.out.println();
        for(ix=0;ix<numOfNodes;ix++){
            System.out.printf("N%02d ", ix);
            for(jx=0;jx<numOfNodes;jx++){
                System.out.printf("%3d ", unitCost[ix][jx]);
            }
            System.out.println();
        }
    }
    
    public String print(String title, int[][] matrix){
        String result = "";
        int ix,jx;
        result+= "====" + title + "====\n    ";
        for(ix=0;ix<numOfNodes;ix++){
            result+=String.format("N%02d ", ix);
        }
        result+="\n";
        for(ix=0;ix<numOfNodes;ix++){
            result+=String.format("N%02d ", ix);
            for(jx=0;jx<numOfNodes;jx++){
                result+=String.format("%3d ", matrix[ix][jx]);
            }
            result+="\n";
        }
        return result;
        
    }
}
