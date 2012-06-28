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
public class AutosomalGene {

    boolean fromMaleParent;
    boolean fromFemaleParent;
    static long assigncamo = 0;
    Random r = new Random();

    public AutosomalGene() {
        if (r.nextBoolean()) {
            this.fromFemaleParent = true;
            this.fromMaleParent = true;
        } else {
            this.fromFemaleParent = false;
            this.fromMaleParent = false;
        }
    }

    public AutosomalGene(giraffe parentfemale, giraffe parentmale) {

        if (parentfemale.dna.camouflage.fromFemaleParent && parentfemale.dna.camouflage.fromMaleParent) {
            this.fromFemaleParent = true;
        } else if (parentfemale.dna.camouflage.fromFemaleParent == false && parentfemale.dna.camouflage.fromMaleParent == false) {
            this.fromFemaleParent = false;
        } else {
            this.fromFemaleParent = r.nextBoolean();
        }

        if (parentmale.dna.camouflage.fromFemaleParent && parentmale.dna.camouflage.fromMaleParent) {
            this.fromMaleParent = true;
        } else if (parentmale.dna.camouflage.fromFemaleParent == false && parentmale.dna.camouflage.fromMaleParent == false) {
            this.fromMaleParent = false;
        } else {
            this.fromMaleParent = r.nextBoolean();
        }

    }
}
