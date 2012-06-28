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
public class Ldna {

    double maxage;
    double maxspeed;
    double energytoreproduce;
    double maxvision;
    double camouflagevision;
    double preyefficiency;
    double size;
    static Random r = new Random();

    public Ldna() {

        this.maxage = Constants.lmaxage;
        this.maxspeed = Constants.lmaxspeed;
        this.energytoreproduce = Constants.lenergytoreproduce;
        this.maxvision = Constants.lmaxvision;
        this.preyefficiency = Constants.preyefficiencyper;
        this.size = Constants.lsize;
        this.camouflagevision=this.maxvision*Constants.lcamouflagevision;

    }

    public Ldna(Lion parentfemale, Lion parentmale) {

        this.maxage = Constants.lmaxage;
        this.energytoreproduce = Constants.lenergytoreproduce;
        this.maxspeed = parentfemale.dna.maxspeed * Constants.lfertiliationfactor + parentmale.dna.maxspeed * (1 - Constants.lfertiliationfactor);
        this.maxvision = parentfemale.dna.maxvision * Constants.lfertiliationfactor + parentmale.dna.maxvision * (1 - Constants.lfertiliationfactor);
        this.camouflagevision=parentfemale.dna.camouflagevision* Constants.lfertiliationfactor + parentmale.dna.camouflagevision * (1 - Constants.lfertiliationfactor);
        this.preyefficiency = Constants.preyefficiencyper;
        this.size = parentfemale.dna.size * Constants.lfertiliationfactor + parentmale.dna.size * (1 - Constants.lfertiliationfactor);
        this.mutate();
    }

    public void mutate() {
        this.maxspeed = (this.maxspeed - Constants.mutabslspeed) + 2 * Constants.mutabslspeed * r.nextDouble();
        this.maxvision = (this.maxvision - Constants.mutabslvision) + 2 * Constants.mutabslvision * r.nextDouble();
        this.preyefficiency = (this.preyefficiency - Constants.mutabslefficiency) + 2 * Constants.mutabslefficiency * r.nextDouble();
        this.size = (this.size - Constants.mutabssize) + 2 * Constants.mutabssize * r.nextDouble();
        this.camouflagevision = (this.camouflagevision - Constants.mutabslvision) + 2 * Constants.mutabslvision * r.nextDouble();
    }
}
