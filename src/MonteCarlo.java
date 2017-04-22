/**
 *
 * Created by oscarricaud on 4/21/17.
 */
public class MonteCarlo {
    private String [] states = new String[]{"State 1: RU 8p", "State 2: TU 10p", "State 3: RU 10p", "State 4: RD 10p",
            "State 5: RU 8a", "State 6: RD 8a", "State 7: TU 10a", "State 8: RU 10a",
            "State 9: RD 10a", "State 10: 10a", "Final State:"};
    Model model = new Model();

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
        // traverse through the heigh of the tree ln10 = 2.30 about 3
        if (currState.equals("Final State:")) {
            return;
        } else {
                float currProbability = 0;
                String stateWeShouldTake = "";
                // traverse through all neighbors of current node
                for (int neighbor = 0; neighbor < model.getNeighbors(currState).size(); neighbor++) {
                    // Obtain probabilities for each neighbor
                    float probability = model.probabilityPickingParticular(states[neighbor]);
                    // Take the neighbor with the highest probability
                    if (currProbability < probability) {
                        currProbability = probability;
                        stateWeShouldTake = states[neighbor];
                        System.out.println("Current probability " + currProbability);
                    }
                    System.out.println("State we should take is: " + stateWeShouldTake);
                }
        }
    }
}
