/*
 * WorldFS
 * random generator for input graphs
 */
package lpfastsolution;

import java.util.ArrayList;
import java.util.Random;


public class InputGraphGenerator {
    private int[][] demandCapacity;
    private int[][] unitCost;
    private final int numOfNodes = 35;
    
    public void generateDemandCapacity(int kx){
        demandCapacity = new int[numOfNodes][numOfNodes];
        Random random = new Random();

        int ix,jx;
        for(ix=0;ix<numOfNodes;ix++){              
            for(jx=ix;jx<numOfNodes;jx++){
                if(ix==jx){
                    demandCapacity[ix][jx]=0;
                }else{
                    demandCapacity[ix][jx]=random.nextInt(4);
                    demandCapacity[jx][ix] = demandCapacity[ix][jx];
                }
            }
        }
    }
    
    public void generateUnitCost(int kx){
        int nextNode;
        int ix,jx;
        Random random = new Random();
                
        unitCost = new int[numOfNodes][numOfNodes];
        
        ArrayList[] lowCostLink = new ArrayList[numOfNodes];
        for(ix=0;ix<numOfNodes;ix++){
            lowCostLink[ix] = new ArrayList();
        }
        
        for(ix=0;ix<numOfNodes;ix++){    
            //pick kx links to be low unitCost link
            while(lowCostLink[ix].size()<kx){
                nextNode = random.nextInt(numOfNodes);
                if(nextNode != ix && !lowCostLink[ix].contains(nextNode)){
                    lowCostLink[ix].add(nextNode);
                    lowCostLink[nextNode].add(ix);
                }
            }
        }
        
        
    }
    
    public void generate(int kx){
        //int numOfNodes = 35;
        
        unitCost = new int[numOfNodes][numOfNodes];
        Random random = new Random();
        int nextNode;
        int ix,jx;
        
        ArrayList[] lowCostLink = new ArrayList[numOfNodes];
        for(ix=0;ix<numOfNodes;ix++){
            lowCostLink[ix] = new ArrayList();
        }
        
        for(ix=0;ix<numOfNodes;ix++){    
            //pick kx links to be low unitCost link
            //System.out.printf("Node %d\n",ix);
            while(lowCostLink[ix].size()<kx){
                nextNode = random.nextInt(numOfNodes);
                //System.out.printf("%d ",nextNode);
                if(nextNode != ix && !lowCostLink[ix].contains(nextNode)){
                    //System.out.printf("    pick node %d\n",nextNode);
                    lowCostLink[ix].add(nextNode);
                    lowCostLink[nextNode].add(ix);
                }
            }
        }
        

        for(ix=0;ix<numOfNodes;ix++){              
            for(jx=ix;jx<numOfNodes;jx++){
                if(ix==jx){
                    demandCapacity[ix][jx]=0;
                    unitCost[ix][jx]=0;
                }else{
                    demandCapacity[ix][jx]=random.nextInt(4);
                    demandCapacity[jx][ix] = demandCapacity[ix][jx];
                    if(lowCostLink[ix].contains(jx) || lowCostLink[jx].contains(ix)){
                        unitCost[ix][jx] = 1;
                        unitCost[jx][ix] = 1;
                    }else{
                        unitCost[ix][jx] = 300;
                        unitCost[jx][ix] = 300;
                    }
                }
                
            }
        }
    
    
    }
    
    public int[][] getDemandCapacity(){
        return demandCapacity;
    }
    
    public int[][] getUnitCost(){
        return unitCost;
    }
    
    public void print(){
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
    
    public static void unitTest(){
        InputGraphGenerator igg = new InputGraphGenerator();
        igg.generate(3);
        igg.print();
    }
}

