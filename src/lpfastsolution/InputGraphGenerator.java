/*
 * WorldFS
 * random generator for input graphs
 */
package lpfastsolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class InputGraphGenerator {
    private int[][] demand;
    private int[][] cost;
    private int numOfNodes = 35;
    
    public void generate(int kx){
        //int numOfNodes = 35;
        demand = new int[numOfNodes][numOfNodes];
        cost = new int[numOfNodes][numOfNodes];
        Random random = new Random();
        int nextNode;
        int ix,jx;
        
        ArrayList[] lowCostLink = new ArrayList[numOfNodes];
        for(ix=0;ix<numOfNodes;ix++){
            lowCostLink[ix] = new ArrayList();
        }
        
        for(ix=0;ix<numOfNodes;ix++){    
            //pick kx links to be low cost link
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
                    demand[ix][jx]=0;
                    cost[ix][jx]=0;
                }else{
                    demand[ix][jx]=random.nextInt(4);
                    demand[jx][ix] = demand[ix][jx];
                    if(lowCostLink[ix].contains(jx) || lowCostLink[jx].contains(ix)){
                        cost[ix][jx] = 1;
                        cost[jx][ix] = 1;
                    }else{
                        cost[ix][jx] = 300;
                        cost[jx][ix] = 300;
                    }
                }
                
            }
        }
    
    
    }
    
    public int[][] getDemand(){
        return demand;
    }
    
    public int[][] getUnitCost(){
        return cost;
    }
    
    public void print(){
        int ix,jx;
        System.out.printf("====Capacity Demand====\n");
        for(ix=0;ix<numOfNodes;ix++){
            for(jx=0;jx<numOfNodes;jx++){
                System.out.printf("%d ", demand[ix][jx]);
            }
            System.out.println();
        }
        
        System.out.printf("\n\n====Unit Link Cost====\n");
        for(ix=0;ix<numOfNodes;ix++){
            for(jx=0;jx<numOfNodes;jx++){
                System.out.printf("%03d ", cost[ix][jx]);
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

