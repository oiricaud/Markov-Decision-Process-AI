import java.util.LinkedList;
import java.util.Random;

/**
 *
 * Created by oscarricaud on 4/21/17.
 */
public class MonteCarlo {
    private LinkedList<State> tree = new LinkedList<State>();
    private State[] states = new State[]
            {
                    new State(0, "Student is Rested, Homework is Undone. Current time 8pm", 0),
                    new State(1, "Student is Tired, Homework is Undone. Current time 10pm", 0),
                    new State(2, "Student is Rested, Homework is Undone. Current time 10pm", 0),
                    new State(3, "Student is Rested, Homework is Done. Current time 10pm", 0),
                    new State(4, "Student is Rested, Homework is Undone. Current time 8am", 0),
                    new State(5, "Student is Rested, Homework is Done. Current time 8am", 0),
                    new State(6, "Student is Tired, Homework is Undone. Current time 10am", 0),
                    new State(7, "Student is Rested, Homework is Undone. Current time 10am", 0),
                    new State(8, "Student is Rested, Homework is Done. Current time 10am", 0),
                    new State(9, "Student is Tired, Homework is Done. Current time 10am", 0),
                    new State(10, "Class Begins. Current time 11am", 0)
            };

    MonteCarlo() {

        buildNeighbors();
        LinkedList<State> currReward = new LinkedList<State>();
        State currState = states[0];
        for (int episode = 1; episode <= 1; episode++) {
            System.out.print("Episode " + episode + " ");
            State map = beginEpisode(currState);
            System.out.println("map " + map.getTotalScore());
            // if (currReward != null) {
            //     System.out.println("Reward for episode " + episode + " is: " + tree.getFirst().getTotalScore() +
            // "\n");
            // }

        }
        for (int i = 0; i < tree.size(); i++) {
            System.out.println("Tree " + tree.get(i).getTotalScore());
        }

    }

    private void buildNeighbors() {
        // Add all neighbors for each state
        for (int i = 0; i < states.length; i++) {
            LinkedList<State> tempNeighbors = new LinkedList<State>();
            if (i == 0) {
                tempNeighbors.add(states[1]);
                tempNeighbors.add(states[2]);
                tempNeighbors.add(states[3]);
                states[0].setNeighbors(tempNeighbors);
            }
            if (i == 1) {
                tempNeighbors.add(states[4]);
                tempNeighbors.add(states[7]);
                states[1].setNeighbors(tempNeighbors);
            }
            if (i == 2) {
                tempNeighbors.add(states[4]);
                tempNeighbors.add(states[5]);
                tempNeighbors.add(states[7]);
                states[2].setNeighbors(tempNeighbors);
            }
            if (i == 3) {
                tempNeighbors.add(states[5]);
                tempNeighbors.add(states[8]);
                states[3].setNeighbors(tempNeighbors);
            }
            if (i == 4) {
                tempNeighbors.add(states[6]);
                tempNeighbors.add(states[7]);
                tempNeighbors.add(states[8]);
                states[4].setNeighbors(tempNeighbors);
            }
            if (i == 5) {
                tempNeighbors.add(states[8]);
                tempNeighbors.add(states[9]);
                states[5].setNeighbors(tempNeighbors);
            }
            if (i == 6) {
                tempNeighbors.add(states[10]);
                states[6].setNeighbors(tempNeighbors);
            }
            if (i == 7) {
                tempNeighbors.add(states[10]);
                states[7].setNeighbors(tempNeighbors);
            }
            if (i == 8) {
                tempNeighbors.add(states[10]);
                states[8].setNeighbors(tempNeighbors);
            }
            if (i == 9) {
                tempNeighbors.add(states[10]);
                states[9].setNeighbors(tempNeighbors);
            }
        }
    }

    /**
     * @param currState current state we are in
     */
    private State beginEpisode(State currState) {
        State currentState = currState;
        //  while (currentState.isTerminal() == false) {
            if (currentState.isLeaf()) { // Is it a terminal state?
                if (currentState.getNumberOfVisits() == 0) {
                    System.out.println("        Node has NOT been visited before");
                    rollOut(currentState);
                } else { // Node has been visited before
                    System.out.println("        Node has been visited before");
                    for (int action = 0; action < currentState.getNeighbors().size(); action++) {
                        tree.add(currentState.getNeighbors().get(action));
                    }
                    currentState = tree.getFirst();
                    rollOut(currentState);
                }
            } else { // Current state is not a terminal state, make the current node =  random child node
                currentState = chooseRandomState(currentState);
            }
        return currentState;
    }

    private State chooseRandomState(State currentState) {
        // traverse through all neighbors of current node

        int bound = currentState.getNeighbors().size();
        State randomState = randomState(bound, currentState.getNeighbors());
        return randomState;
    }

    private int rollOut(State state) {
        State currentState = state;
        int currentActionValue = 0;
        int rewardValue = 0;
        // Loop until we find the terminal state, class begins
        while (currentState.isTerminal() == false) {
            System.out.println("\t State: " + currentState.getNode() + ", " + currentState.getStateName());
            System.out.println("\t \t \t reward: " + currentState.getTotalScore());

            if (currentState.isTerminal() || (currentState == states[6] || (currentState == states[7] ||
                    (currentState == states[8] || (currentState == states[9]))))) {
                System.out.println("\t \t \t finalState: " + currentState.getNode());
                System.out.println("\t \t \t Reward: " + rewardValue);
                return currentState.getTotalScore();
            } else { // Otherwise choose a random action of the current state
                currentActionValue = chooseRandomAction(currentState);
                currentState = simulate(currentActionValue, currentState);
                rewardValue = rewardValue + currentActionValue;
            }
        }

        return rewardValue;
    }

    private State simulate(int actionValue, State currentState) {
        // HEIGHT = 0
        // NODE 0
        // S0 -> S1
        if ((currentState == states[0] && (actionValue == 2))) {           // PARTY
            currentState = states[1];
        }
        // S0 -> S2
        else if ((currentState == states[0] && (actionValue == 0))) {           // REST
            currentState = states[2];
        }
        // S0 -> S3
        else if ((currentState == states[0] && (actionValue == -1))) {           // STUDY
            currentState = states[3];
        }
        // HEIGHT = 1
        // NODE = 1
        // S1 -> S4
        else if ((currentState == states[1] && (actionValue == 2))) {           // PARTY
            currentState = states[4];
        }
        // NODE = 1
        // S1 -> S7
        else if ((currentState == states[1] && (actionValue == 0))) {           // REST
            currentState = states[7];
        }
        // NODE = 2
        // S2 -> S4
        else if ((currentState == states[2] && (actionValue == 0))) {           // REST
            currentState = states[4];
        }
        // NODE = 2
        // S2 -> S7
        else if ((currentState == states[2] && (actionValue == 2))) {           // PARTY
            currentState = states[7];
        }
        // NODE = 2
        // S2 -> S5
        else if ((currentState == states[2] && (actionValue == -1))) {           // STUDY
            currentState = states[5];
        }
        // NODE = 3
        // S3 -> S5
        else if ((currentState == states[3] && (actionValue == 0))) {           // REST
            currentState = states[5];
        }
        // NODE = 3
        // S3 -> S9
        else if ((currentState == states[3] && (actionValue == 2))) {           // PARTY
            currentState = states[9];
        }
// HEIGHT = 2
        // NODE = 4
        // S4 -> S6
        else if ((currentState == states[4] && (actionValue == 2))) {           // PARTY
            currentState = states[6];
        }
        // NODE = 4
        // S4 -> S7
        else if ((currentState == states[4] && (actionValue == 0))) {           // REST
            currentState = states[7];
        }
        // NODE = 4
        // S4 -> S8
        else if ((currentState == states[4] && (actionValue == -1))) {           // STUDY
            currentState = states[8];
        }
        // NODE = 5
        // S5 -> S9
        else if ((currentState == states[5] && (actionValue == 0))) {           // REST
            currentState = states[8];
        }
        // NODE = 5
        // S5 -> S10
        else if ((currentState == states[5] && (actionValue == 2))) {           // PARTY
            currentState = states[9];
        }
        // NODE = 6
        // S6 -> S10
        else if ((currentState == states[6]) && (actionValue == 2)) {
            currentState = states[10];
        }
        // NODE = 7
        // S7 -> S10
        else if ((currentState == states[7]) && ((actionValue == 2 || actionValue == 0))) {
            currentState = states[10];
        }
        // NODE = 8
        // S8 -> S10
        else if ((currentState == states[8]) && ((actionValue == -1 || actionValue == 0 || actionValue == 4)) || (actionValue == 3)) {
            currentState = states[10];
        }
        // NODE = 9
        // S9 -> S10
        // NODE = 8
        // S8 -> S10
        else if ((currentState == states[9]) && ((actionValue == 2))) {
            currentState = states[10];
        }
        if (currentState == states[10]) {
            return null;
        }
        currentState.setTotalScore(actionValue);
        currentState.setStateValue(actionValue);
        currentState.countVisit(1);
        System.out.println("\t \t \t Student decides to " + currentState.whatActionIsIt(actionValue));
        return currentState;
    }

    private int chooseRandomAction(State currentState) {
        // At random, pick a random neighbor with given probability for each state is equal.
        int bound = currentState.getActions().size();
        int pickRandomAction = random(bound, currentState.getNeighbors());
        //int actionValue = pickRandomState.getActions();
        return pickRandomAction;
    }

    private int random(int high, LinkedList<State> neighbors) {
        Random r = new Random();
        int low = 0;
        int result = r.nextInt(neighbors.size() - low) + low;
        int probActions = r.nextInt(neighbors.get(result).getActions().size() - 1 - low) + low;
        System.out.println("result random " + result);
        System.out.println("probActions " + probActions);
        return neighbors.get(result).getActions().get(probActions);
    }

    private State randomState(int bound, LinkedList<State> neighbors) {
        Random r = new Random();
        int low = 0;
        int result = r.nextInt(bound - low) + low;
        return neighbors.get(result);
    }
}
