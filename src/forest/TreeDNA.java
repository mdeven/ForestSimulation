package forest;

import java.util.Random;

/*
 * The TreeDNA class consisting of - TreeMaxStemLength TreeMaxLeafArea no. of leaves maximum
 * age energy required to create offspring
 */
public class TreeDNA {

    double stemlength;
    double leafarea;
    int maxage = Constants.Treemaxagecopy;
    double energytoseed = Constants.TreeEnergyToSeed;

    /*
     * The TreeDNA constructor with argument as the parent Tree. It copies the
     * TreeDNA of the parent and then mutates it by calling the mutate method.
     */
    public TreeDNA(Tree parent, Tree parent2) {


        this.stemlength = (parent.dna.stemlength + parent2.dna.stemlength) / 2;
        this.leafarea = (parent.dna.leafarea + parent2.dna.leafarea) / 2;


        this.mutate();
    }

    /*
     * The TreeDNA constructor without parameters consisting of default
     * parameters for trees without parents
     */
    public TreeDNA() {
        this.stemlength = Constants.TreeMaxStemLength;
        this.leafarea = Constants.TreeMaxLeafArea;

    }

    /*
     * The mutate function. It modifies the TreeDNA to within perc percent of
     * the current TreeDNA.
     */
    public void mutate() {
        Random randNum = new Random();
        this.stemlength = (this.stemlength - Constants.TreeMutStemLength) + (2 * Constants.TreeMutStemLength * randNum.nextDouble());
        this.leafarea = (this.leafarea - Constants.TreeMutLeafArea) + (2 * Constants.TreeMutLeafArea * randNum.nextDouble());
    }
}
