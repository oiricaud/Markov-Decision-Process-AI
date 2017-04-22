/**
 *
 * Created by oscarricaud on 4/21/17.
 */
public class MonteCarlo {
    Model model = new Model();
    private String [] states = new String[]{"State 1: RU 8p", "State 2: TU 10p", "State 3: RU 10p", "State 4: RD 10p",
            "State 5: RU 8a", "State 6: RD 8a", "State 7: TU 10a", "State 8: RU 10a",
            "State 9: RD 10a", "State 10: 10a", "Final State:"};

    MonteCarlo() {
        String currState = states[0];
        for (int episode = 0; episode < 50; episode++) {
            beginEpisode(currState);
        }
    }

    /**
     * @param currState recursively keep calling this method until currState has reached the final state
     */
    private void beginEpisode(String currState) {
        String sequenceOfExpierence = "";
        String stateWeShouldTake = "";
        if (currState.equals("Final State:")) {
            return;
        } else {
            String tempState = currState;
            if (model.getNeighbors(tempState).equals("Final State:")) {
                return;
            }
            while (!tempState.equals("Final State:")) {
                float currProbability = 0;
                // traverse through all neighbors of current node
                for (int neighbor = 0; neighbor < model.getNeighbors(tempState).size(); neighbor++) {
                    // Obtain probabilities for each neighbor
                    float probability = model.probabilityPickingParticular(states[neighbor]);
                    // Take the neighbor with the highest probability
                    if (currProbability < probability) {
                        currProbability = probability;
                        stateWeShouldTake = model.getNeighbors(tempState).get(neighbor);
                        sequenceOfExpierence = sequenceOfExpierence + stateWeShouldTake + ", ";
                        tempState = stateWeShouldTake;
                        System.out.println("Current probability " + currProbability);
                    }
                }

                System.out.println("State we should take is: " + stateWeShouldTake);
            }
        }
        System.out.println("State we should take is: " + stateWeShouldTake);
        System.out.println("Sequence of Agent's experience {" + sequenceOfExpierence + "}");
    }
}
