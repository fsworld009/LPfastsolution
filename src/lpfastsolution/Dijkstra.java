/*
 * WorldFS, 2.9.2014
 * Dijkstra's shortest path algorithm
 */
package lpfastsolution;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Dijkstra {
    private class Node{
        public int distance;    //distance to source in the current iteration
        public int path;        //previous node in the shortest path found in the current iteration
    }
    private int[][] inputGraph;
    //private int[] result;
    
    public Dijkstra(int[][] inputGraph){
        this.inputGraph(inputGraph);
    }
    
    public void inputGraph(int[][] inputGraph){
        int length=inputGraph.length;
        this.inputGraph = new int[length][length];
        for(int ix=0;ix<length;ix++){
            for(int jx=0;jx<length;jx++){
                this.inputGraph[ix][jx] = inputGraph[ix][jx];
            }
        }
    }
    
    public void printInputGraph(){
        for(int ix=0;ix<inputGraph.length;ix++){
            for(int jx=0;jx<inputGraph[0].length;jx++){
                System.out.printf("%d ",inputGraph[ix][jx]);
            }
            System.out.println();
        }
    }
    
    public int[] run(int source,int destination,boolean verbose){
        int numOfNodes = inputGraph[0].length;
        if(source<0 || destination<0 || source>=numOfNodes || destination>=numOfNodes || source==destination){
            return null;
        }
        
        final Node[] node = new Node[numOfNodes];
        // int[] distance = new int[numOfNodes];
        for(int ix=0;ix<numOfNodes;ix++){
            node[ix] = new Node();
            if(ix==source){
                node[ix].distance=0;
            }else{
                node[ix].distance=-1;
            }
        }
        
        PriorityQueue pq = new PriorityQueue(numOfNodes,new Comparator<Integer>(){
            public int compare(Integer s1, Integer s2) {
                //s1.intValue();
                return Integer.compare(node[s1].distance, node[s2].distance);
            }
        });
        pq.add(source);
        int nextNode, adjacentNode, newDistance;
        while(!pq.isEmpty()){
            nextNode = (int) pq.poll();
            System.out.printf("Iteration: pick node %d\n",nextNode);
            for(adjacentNode=0;adjacentNode<numOfNodes;adjacentNode++){
                if(adjacentNode !=nextNode && inputGraph[nextNode][adjacentNode] != 0){    //this node is an adjacent node of nextNode
                    newDistance = node[nextNode].distance + inputGraph[nextNode][adjacentNode];
                    
                    if(node[adjacentNode].distance == -1){
                        node[adjacentNode].distance = newDistance;
                        pq.add(adjacentNode);
                        node[adjacentNode].path=nextNode;
                    }
                    
                    if(node[adjacentNode].distance > newDistance){
                        node[adjacentNode].distance = newDistance;
                        
                        node[adjacentNode].path=nextNode;
                    }
                }
            }
        }
        
        
        return new int[3];
        
    }
    
    public static void unitTest(){
        int[][] testGraph = new int[7][7];
        testGraph[0]=new int[]{0,2,1,4,0,0,0};
        testGraph[1]=new int[]{2,0,2,0,3,0,0};
        testGraph[2]=new int[]{1,2,0,2,5,7,0};
        testGraph[3]=new int[]{4,0,2,0,0,4,0};
        testGraph[4]=new int[]{0,3,5,0,0,0,1};
        testGraph[5]=new int[]{0,0,7,4,0,0,3};
        testGraph[6]=new int[]{0,0,0,0,1,3,0};
        Dijkstra dijkstra = new Dijkstra(testGraph);
        dijkstra.printInputGraph();
        int[] result = dijkstra.run(0, 6, true);
        
        
    }
    


}
