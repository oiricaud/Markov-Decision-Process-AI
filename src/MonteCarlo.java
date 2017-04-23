import java.util.LinkedList;
import java.util.Random;

/**
 * Created by oscarricaud on 4/21/17.
 */
public class MonteCarlo {
    LinkedList<State> tree = new LinkedList<State>();
    private State[] states = new State[]
            {
                    new State(0, "Rested, Homework Undone 8p", 0), new State(1, "Tired, Homework Undone 10p", 0),
                    new State(2, "Rested, Homework Undone 10p", 0), new State(3, "Rested, Homework Done 10p", 0),
                    new State(4, "Rested, Homework Undone 8a", 0), new State(5, "Rested, Homework Done 8a", 0),
                    new State(6, "Tired, Homework Undone 10a", 0), new State(7, "Rested, Homework Undone 10a", 0),
                    new State(8, "Rested, Homework Done 10a", 0), new State(9, "Tired, Homework Done", 0),
                    new State(10, "Class Begins", 0)
            };


    MonteCarlo() {

        buildNeighbors();
        LinkedList<State> currReward = new LinkedList<State>();
        for (int episode = 1; episode <= 1; episode++) {
            System.out.print("Episode " + episode + " ");
            currReward = beginEpisode(states);
            if (currReward != null) {
                System.out.println("Reward for episode " + episode + " is: " + currReward.getFirst().getTotalScore() + "\n");
            }
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
     * @param states recursively keep calling this method until currState has reached the final state
     */
    private LinkedList<State> beginEpisode(State[] states) {
        State currentState = states[0];
        while (currentState.isTerminal() == false) {
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
        }

        return null;
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
        // Loop until we find the terminal state, class begins
        while (currentState.isTerminal() == false) {
            if (currentState.isTerminal()) {
                return currentState.getTotalScore();
            } else { // Otherwise choose a random action of the current state
                currentActionValue = chooseRandomAction(currentState);
                currentState = simulate(currentActionValue, currentState);
            }
        }
        return currentActionValue;
    }

    private State simulate(int actionValue, State currentState) {
        currentState.setTotalScore(actionValue);
        currentState = currentState.getNeighbors().get(actionValue);
        return currentState;
    }

    private int chooseRandomAction(State currentState) {
        // At random, pick a random neighbor with given probability for each state is equal.
        int bound = currentState.getNeighbors().size();
        int pickRandomAction = random(bound, currentState.getNeighbors());
        //int actionValue = pickRandomState.getActions();
        return pickRandomAction;
    }

    private int random(int bound, LinkedList<State> neighbors) {
        Random r = new Random();
        int low = 0;
        int result = r.nextInt(bound - low) + low;
        int numberOfActionsWeCanTake = neighbors.get(result).getActions().size();
        int randomAction = r.nextInt(numberOfActionsWeCanTake - low) + low;
        return neighbors.get(result).getActions().get(randomAction);
    }

    private State randomState(int bound, LinkedList<State> neighbors) {
        Random r = new Random();
        int low = 0;
        int result = r.nextInt(bound - low) + low;
        return neighbors.get(result);
    }
}
