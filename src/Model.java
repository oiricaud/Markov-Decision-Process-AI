/**
 *
 * Created by oscarricaud on 4/21/17.
 */
public class Model {

    private String experience;

    public Model(){
        String [] states = new String[]{"State 1: RU 8p", "State 2: TU 10p", "State 3: RU 10p", "State 4: RD 10p",
                                        "State 5: RU 8a", "State 6: RD 8a", "State 7: TU 10a", "State 8: RU 10a",
                                        "State 9: RD 10a", "State 10: 10a", "Final State: "};

        Graph graph = new Graph(11);

        // State Number, State value
        graph.addNode(1, 0);
        graph.addNode(2, 0);
        graph.addNode(3, 0);
        graph.addNode(4, 0);
        graph.addNode(5, 0);
        graph.addNode(6, 0);
        graph.addNode(7, 0);
        graph.addNode(8, 0);
        graph.addNode(9, 0);
        graph.addNode(10, 0);

        // Source, Destination, Weight
        graph.addEdge(1, 2, 2); // State 1
        graph.addEdge(1, 3, 0);
        graph.addEdge(1, 4, 1);

        // State 2
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 0);

        // State 3
        graph.addEdge(3, 5, 0); // Source, Destination, Weight
        graph.addEdge(3, 8, 2);
        graph.addEdge(3, 6, -1);

        // State 4
        graph.addEdge(4, 6, 2); // Source, Destination, Weight
        graph.addEdge(4, 9, 2); // Source, Destination, Weight

        // State 5
        graph.addEdge(5, 7, 2); // Source, Destination, Weight
        graph.addEdge(5, 8, 0); // Source, Destination, Weight
        graph.addEdge(5, 9, -1); // Source, Destination, Weight

        // State 6
        graph.addEdge(6, 9, 0); // Source, Destination, Weight
        graph.addEdge(6, 10, 2); // Source, Destination, Weight

        // State 7
        graph.addEdge(7, 11, -1); // Source, Destination, Weight

        // State 8
        graph.addEdge(8, 11, 0); // Source, Destination, Weight

        // State 9
        graph.addEdge(9, 11, 4); // Source, Destination, Weight

        // State 10
        graph.addEdge(10, 11, 3); // Source, Destination, Weight


        graph.printState();

    }

    /**
     * @return the ordered sequence of states/actions/rewards that
     *  occur in the episode
     */
    public String getExperience() {
        return experience;
    }


    /**
     * @param action
     * @return the value of reward for specific action that is taken
     */
    public int getRewardAction(String action){
        return action.equals("P") ? 2 : action.equals("R") ? 0 : action.equals("S") ? -1 : -2;
    }

    /**
     * @param currentState currentState of the tree
     * @param nextState the next state we are simulating
     * @return the transition probability
     */
    public double getTransitionProbabilities(String currentState, String nextState){
        if(currentState.equals("State 3: RU 10p") && nextState.equals("State 5: RU 8a")){
            return .5;
        }
        else if(currentState.equals("State 3: RU 10p") && nextState.equals("State 8: RU 10a")){
            return .5;
        }
        else if(currentState.equals("State 4: RD 10p") && nextState.equals("State 6: RD 8a")){
            return .5;
        }
        else if(currentState.equals("State 4: RD 10p") && nextState.equals("State 9: RD 10a")){
            return .5;
        }

        return 1.0; // "all those not labeled are probability 1.0 from assignment #4"
    }

    /**
     * @param action we define an action of student to either be a Party, Rest or Study
     */
    public int probabilityPickingParticular(String action, String fromState){
        return (1/numberOfActionsThatCanBePerformed(fromState));
    }

    private int numberOfActionsThatCanBePerformed(String state) {
        if(state.equals("State 1: RU 8p")){
            return 3;
        }
        if(state.equals("State 2: TU 10p")){
            return 2;
        }
        if(state.equals("State 3: RU 10p")){
            return 3;
        }
        if(state.equals("State 4: RD 10p")){
            return 3;
        }
        if(state.equals("State 5: RU 8a")){
            return 3;
        }
        if(state.equals("State 6: RD 8a")){
            return 2;
        }
        if(state.equals("State 7: TU 10a")){
            return -1;
        }
        if(state.equals("State 8: RU 10a")){
            return 0;
        }
        if(state.equals("State 9: RD 10a")){
            return 4;
        }
        if(state.equals("State 10: 10a")){
            return 3;
        }
        return -1;
    }
}
