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
    private int[] rewardForStates = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    MonteCarlo() {
        String currState = states[0];
        int currReward = rewardForStates[0];
        int stateCount = 0;
        for (int episode = 1; episode <= 50; episode++) {
            System.out.print("Episode " + episode + " ");
            int reward = beginEpisode(currState);

            if (currReward < reward) {
                currReward = reward;

                if (rewardForStates[stateCount] < currReward) {
                    rewardForStates[stateCount] = currReward;
                } else {
                    stateCount++;
                }
            }
            System.out.println("Reward for episode " + episode + " is: " + currReward + "\n");
        }
    }

    /**
     * @param currState recursively keep calling this method until currState has reached the final state
     */
    private int beginEpisode(String currState) {
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
                if (model.getNeighbors(tempState).get(0).equals("Class Begins")) {
                    System.out.println("Sequence of Agent's experience {" + sequenceOfExpierence + "}");
                    return reward + model.getReward(tempState, model.getNeighbors(tempState).get(0));
                }
                // Take the neighbor with the highest probability
                else if (currProbability < probability) {
                    currProbability = probability;
                    stateWeShouldTake = model.getNeighbors(tempState).get(neighbor);
                    sequenceOfExpierence = sequenceOfExpierence + stateWeShouldTake + ", ";
                    reward = reward + model.getReward(tempState, stateWeShouldTake);
                }
            }
            tempState = stateWeShouldTake;
        }
        return 0;
    }
}
