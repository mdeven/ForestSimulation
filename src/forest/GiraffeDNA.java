/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forest;

import java.util.Random;

/**
 *
 * @author ANURAG
 */
public class GiraffeDNA {

    double height;
    double neckbodyratio;
    int maxage;
    double energytoreproduce;
    double maxspeed;
    AutosomalGene camouflage;
    Random r = new Random();

    public GiraffeDNA() {
        this.height = Constants.gheight;
        this.neckbodyratio = Constants.gratio;
        this.maxage = Constants.gmaxage;
        this.energytoreproduce = Constants.genergytoreproduce;
        this.maxspeed = Constants.gmaxspeed;
        this.camouflage = new AutosomalGene();
    }

    public GiraffeDNA(Giraffe parentfemale, Giraffe parentmale) {
        this.camouflage = new AutosomalGene(parentfemale, parentmale);
        this.height = parentfemale.dna.height * Constants.gfertilizationfactor + parentmale.dna.height * (1 - Constants.gfertilizationfactor);
        this.neckbodyratio = parentfemale.dna.neckbodyratio * Constants.gfertilizationfactor + parentmale.dna.neckbodyratio * (1 - Constants.gfertilizationfactor);
        this.maxage = Constants.gmaxage;
        this.energytoreproduce = Constants.genergytoreproduce;
        this.maxspeed = Constants.gmaxspeed;
        this.mutate();
    }

    public void mutate() {
        this.height = (this.height - Constants.mutabsgheight) + (2 * Constants.mutabsgheight * r.nextDouble());
        this.neckbodyratio = (this.neckbodyratio - Constants.mutabsgnbratio) + (2 * Constants.mutabsgnbratio * r.nextDouble());
        this.maxspeed = (this.maxspeed - Constants.mutabsgspeed) + 2 * Constants.mutabsgspeed * r.nextDouble();
    }
}
