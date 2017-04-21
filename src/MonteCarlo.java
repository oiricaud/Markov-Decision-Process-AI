/**
 *
 * Created by oscarricaud on 4/21/17.
 */
public class MonteCarlo {

    public MonteCarlo() {
        Model model = new Model();

        for (int episode = 0; episode < 50; episode++) {
            System.out.println("Episode: " + episode + '\n' + " Agent's sequence of experience: "
                    + model.getExperience());
        }
    }
}
