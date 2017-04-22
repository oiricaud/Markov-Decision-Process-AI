/**
 *
 * Created by oscarricaud on 4/21/17.
 */
public class MonteCarlo {
    Model model = new Model();
    private String[] states = new String[]
            {
                    "State 1: Rested, Homework Undone 8p", "State 2: Tired, Homework Undone 10p",
                    "State 3: Rested, Homework Undone 10p", "State 4: Rested, Homework Done 10p",
                    "State 5: Rested, Homework Undone 8a", "State 6: Rested, Homework Done 8a",
                    "State 7: Tired, Homework Undone 10a", "State 8: Rested, Homework Undone 10a",
                    "State 9: Rested, Homework Done 10a", "State 10: Tired, Homework Done", "Class Begins"
            };

    MonteCarlo() {
        String currState = states[0];
        for (int episode = 0; episode < 50; episode++) {
            System.out.print("Episode " + episode + " ");
            beginEpisode(currState);
            System.out.println("\n");
        }
    }

    /**
     * @param currState recursively keep calling this method until currState has reached the final state
     */
    private void beginEpisode(String currState) {
        String sequenceOfExpierence = "";
        String stateWeShouldTake = "";
        int reward = 0;
        String tempState = currState;
        while (!tempState.equals("Final State:")) {

            float currProbability = 0;
            // traverse through all neighbors of current node
            for (int neighbor = 0; neighbor < model.getNeighbors(tempState).size(); neighbor++) {
                // Obtain probabilities for each neighbor
                float probability = model.probabilityPickingParticular(tempState);
                // Check if we have reached a terminal state, if so end
                if (tempState.equals("State 7: Tired, Homework Undone 10a") || tempState.equals("State 8: Rested, Homework Undone 10a")
                        || tempState.equals("State 9: Rested, Homework Done 10a") || tempState.equals("State 10: Tired, Homework Done")) {
                    System.out.println("Sequence of Agent's experience {" + sequenceOfExpierence + "Final State: " +
                            "Class Begins }");
                    return;
                }
                // Take the neighbor with the highest probability
                if (currProbability < probability) {
                    currProbability = probability;
                    stateWeShouldTake = model.getNeighbors(tempState).get(neighbor);
                    sequenceOfExpierence = sequenceOfExpierence + stateWeShouldTake + ", ";
                    System.out.println("State we should take is: " + stateWeShouldTake);
                    System.out.println("Current probability " + currProbability);
                }

            }

            tempState = stateWeShouldTake;

        }

    }
}
