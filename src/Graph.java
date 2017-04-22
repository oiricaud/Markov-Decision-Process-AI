/**
 *
 * Created by oscarricaud on 4/21/17.
 */
public class Graph {
    private int[] costs;
    private int[][] adjacencyMatrix;
    private int size; // number of nodes in the graph

    public Graph(int size) {
        this.size = size;
        costs = new int[size]; //Initialized with all 0s
        adjacencyMatrix = new int[size][size];//Initialized with all 0s
    }

    public void addNode(int name, int cost) {
        costs[name - 1] = cost;
    }

    public void addEdge(int sourceName, int destinationName, int weight) {
        int sourceIndex = sourceName - 1;
        int destinationIndex = destinationName - 1;
        adjacencyMatrix[sourceIndex][destinationIndex] = weight;
        /*
         * In case we would have had non directional graph [ or bidirectional
         * graph with both the edges between two nodes, n1 and n2 having same
         * weight and this statement being true for all the pair of connected
         * nodes], we just need to add(uncomment) below statement
         */
        //adjacencyMatrix[destinationIndex][sourceIndex] = weight;
    }

    public void printState(){
        for(int i = 0 ; i < size; i++){
            for(int j = 0 ; j < size ; j++){
                if(adjacencyMatrix[i][j] != 0){
                    System.out.println("State: "+(i+1)+", Cost: " + costs[i] + " is connected with " + "State: "+(j+1)
                            + " , Cost: "+costs[j] + " with reward value:  " + adjacencyMatrix[i][j]);
                }
            }
        }
    }
}