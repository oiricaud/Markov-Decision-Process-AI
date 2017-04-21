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
}
