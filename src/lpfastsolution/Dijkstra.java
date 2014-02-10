/*
 * WorldFS, 2.9.2014
 * Dijkstra's shortest path algorithm
 */
package lpfastsolution;


public class Dijkstra {
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
    
    public int[] run(int source,int destination){
        if(source<0 || destination<0 || source>=inputGraph[0].length || destination>=inputGraph[0].length || source==destination){
            return null;
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
        int[] result = dijkstra.run(0, 6);
        
        
    }
}
