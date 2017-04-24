import java.util.LinkedList;

/**
 * Created by oscarricaud on 4/23/17.
 */
class State {
    private String stateName;
    private int stateValue;
    private int node;
    private LinkedList<State> neighbors;
    private boolean isLeaf;
    private int totalScore;
    private int numberOfVisits;
    private boolean isTerminal;
    // actionsAt0 = Party, actionsAt1 = Rest, actionsAt2 = Study
    private LinkedList<Integer> actions = new LinkedList<Integer>();

    State(int node, String state, int value) {
        setStateName(state);
        setStateValue(value);
        setNode(node);
        setTotalScore(0);
        setNumberOfVisits(0);
        setLeaf(true);
        setTerminal(false);
        if (node == 0) {
            actions.add(0, 2);  // Action at index 0 = Party value
            actions.add(1, 0);  // Action at index 1 = Rest value
            actions.add(2, -1); // Action at index 2 = Study value
        }
        if (node == 1) {
            actions.add(0, 2);  // Party
            actions.add(1, 0);  // Rest
            //actions.add(2, null);
        }
        if (node == 2) {
            actions.add(0, 2);  // Action at index 0 = Party value
            actions.add(1, 0);  // Action at index 1 = Rest value
            actions.add(2, -1); // Action at index 2 = Study value
        }
        if (node == 3) {
            actions.add(0, 2);  // Party
            actions.add(1, 0);  // Rest
            //actions.add(2, null);
        }
        if (node == 4) {
            actions.add(0, 2);  // Action at index 0 = Party value
            actions.add(1, 0);  // Action at index 1 = Rest value
            actions.add(2, -1); // Action at index 2 = Study value
        }
        if (node == 5) {
            actions.add(0, 2);  // Party
            actions.add(1, 0);  // Rest
            //actions.add(2, null);
        }
        if (node == 6) { // Any actions results into the same value
            actions.add(0, -1);
            actions.add(1, -1);
            actions.add(2, -1);
        }
        if (node == 7) { // Any actions results into the same value
            actions.add(0, 0);
            actions.add(1, 0);
            actions.add(2, 0);
            setLeaf(false);
        }
        if (node == 8) { // Any actions results into the same value
            actions.add(0, 4);
            actions.add(1, 4);
            actions.add(2, 4);
            setLeaf(false);
        }
        if (node == 9) { // Any actions results into the same value
            actions.add(0, 3);
            actions.add(1, 3);
            actions.add(2, 3);
            setLeaf(false);
        }
        // Terminal state
        if (node == 10) { // Final State, Class has started
            actions.add(0, null);  // Party
            actions.add(1, null);  // Rest
            actions.add(2, null); // Study
            setTerminal(true);
            setLeaf(false);
        }
    }

    String getStateName() {
        return stateName;
    }

    private void setStateName(String stateName) {
        this.stateName = stateName;
    }

    private int getStateValue() {
        return stateValue;
    }

    private void setStateValue(int stateValue) {
        this.stateValue = stateValue;
    }

    LinkedList<State> getNeighbors() {
        System.out.println("\t\t\t Has " + (neighbors.size()) + " neighbors. From left-> right ");
        for (State neighbor : neighbors) {
            System.out.println("\t\t\t\t State: " + neighbor.getNode() + ", " + neighbor.getStateName()
                    + ". Current node value " + neighbor.getStateValue());
        }
        return neighbors;
    }

    void setNeighbors(LinkedList<State> neighbors) {
        this.neighbors = neighbors;
    }

    int getNode() {
        return node;
    }

    private void setNode(int node) {
        this.node = node;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public LinkedList<Integer> getActions() {
        return actions;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }
}
