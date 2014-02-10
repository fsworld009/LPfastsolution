/*
 * WorldFS, 2.9.2014
 * Dijkstra's shortest path algorithm
 */
package lpfastsolution;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;


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
        
        
        int counter=0;
        
        while(!pq.isEmpty()){
            if(verbose){
                counter++;
                System.out.printf("==============Iteration %d==============\n",counter);
                System.out.printf("Priority Queue: ");
                Iterator iter = pq.iterator();
                while(iter.hasNext()){
                    System.out.printf("%d ",iter.next());
                }
                System.out.println();
            }
            
            
            
            
            nextNode = (int) pq.poll();
            if(verbose){
                System.out.printf("pick up node %d and remove it from PQ\n",nextNode);
                if(nextNode==destination){
                    System.out.printf("!!! reach destination, break loop\n");
                    break;
                }else{
                    System.out.printf("Check adjacent nodes of Node %d:\n",nextNode);
                }
            }else{
                if(nextNode==destination){
                    break;
                }
                
            }
            for(adjacentNode=0;adjacentNode<numOfNodes;adjacentNode++){
                if(adjacentNode !=nextNode && inputGraph[nextNode][adjacentNode] != 0){    //this node is an adjacent node of nextNode
                    newDistance = node[nextNode].distance + inputGraph[nextNode][adjacentNode];
                    if(verbose){
                        System.out.printf("    iter: check Node %d, newDistance = %d\n",adjacentNode, newDistance);
                    }
                    
                    if(node[adjacentNode].distance == -1){
                        
                        node[adjacentNode].distance = newDistance;
                        pq.add(adjacentNode);
                        node[adjacentNode].path=nextNode;
                        
                        if(verbose){
                            System.out.printf("         Condition: Node[%d].distance == -1\n",adjacentNode);
                            System.out.printf("             Update Node[%d]: distance = %d, path = Node %d\n",adjacentNode,node[adjacentNode].distance,node[adjacentNode].path);
                        }
                    }
                    
                    if(node[adjacentNode].distance > newDistance){
                        int oldDist = node[adjacentNode].distance;  //for verbose
                        node[adjacentNode].distance = newDistance;
                        
                        node[adjacentNode].path=nextNode;
                        if(verbose){
                            System.out.printf("         Condition: Node[%d].distance %d > newDistance %d\n",adjacentNode, oldDist, newDistance);
                            System.out.printf("             Update Node[%d]: distance = %d, path = Node %d\n",adjacentNode,node[adjacentNode].distance,node[adjacentNode].path);
                        }
                    }
                }
            }
        }
        
        //get result
        Stack stack = new Stack();
        int prevNode = destination;
        while(prevNode != source){
            stack.push(prevNode);
            prevNode = node[prevNode].path;
        }
        int[] result = new int[stack.size()+1];
        result[0] = source;
        for(int ix=1;ix<result.length;ix++){
            result[ix]=(Integer)stack.pop();
            
        }
        
        if(verbose){
            System.out.printf("The shortest path is:\n");
            for(int ix=0;ix<result.length;ix++){
                System.out.printf("%d ",result[ix]);
            }
            System.out.println();
        }
        
        
        return result;
        
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
