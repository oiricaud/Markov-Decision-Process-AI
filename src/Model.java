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
}
