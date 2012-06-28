package forest;

import java.awt.Color;
import java.util.Random;

/*
 * The tree class consisting of - TreeDNA id age energy alive state
 */
public class Tree extends Object {

    TreeDNA dna;
    long id;
    static long nextid = 1;
    int age;
    double stemlength;
    double leafarea;
    Color color;
    boolean readyToReproduce;

    /*
     * Constructor when parent is specified
     */
    public Tree(Tree parent, Tree parent2) {
        Random rand = new Random();

        /*
         * Gets TreeDNA from parent by calling the TreeDNA constructor id is
         * assigned by the next id static variable The intial energy is set at
         * 500 units.
         */

        this.dna = new TreeDNA(parent, parent2);
        this.id = nextid++;
        this.age = 0;
        this.energy = Constants.TreeInitialEnergy;
        this.alive = true;
        this.stemlength = Constants.TreeInitialStemLength;
        this.leafarea = Constants.TreeInitialLeafArea;
        if (rand.nextDouble() < Constants.ArenaWindBias) {
            this.x = ((parent.x + parent2.x) / 2) + Constants.TreeSeedSpreadMin + (Constants.TreeSeedSpreadMax - Constants.TreeSeedSpreadMin) * rand.nextDouble();
        } else {
            this.x = ((parent.x + parent2.x) / 2) - Constants.TreeSeedSpreadMin - (Constants.TreeSeedSpreadMax - Constants.TreeSeedSpreadMin) * rand.nextDouble();
        }

        if (this.x < 0 || this.x > Constants.ArenaMaxX) {
            this.alive = false;
        }
        this.y = Constants.ArenaMaxY - (this.stemlength + this.leafarea);
        this.isSelected = false;
        this.readyToReproduce = false;

        if (rand.nextDouble() > 0.5) {
            this.color = Forest.MutateColor(parent.color);
        } else {
            this.color = Forest.MutateColor(parent2.color);
        }

    }

    /*
     * Constructor for trees without a parent
     */
    public Tree(double x, Color color) {

        /*
         * Calls the TreeDNA constructor without a parameter. id is assigned by
         * the nextid static variable and default energy is set at 500 units.
         */

        this.dna = new TreeDNA();
        this.id = nextid++;
        this.age = 0;
        this.energy = Constants.TreeInitialEnergy;
        this.stemlength = Constants.TreeInitialStemLength;
        this.leafarea = Constants.TreeInitialLeafArea;
        this.alive = true;
        this.x = x;
        this.y = Constants.ArenaMaxY - (this.stemlength + this.leafarea);
        this.isSelected = false;
        this.color = color;
        this.readyToReproduce = false;

    }

    /*
     * The CreateSeed method is used to create an offspring of the tree that is
     * used to call this method.
     */
    public static void CreateSeed(Tree t1, Tree t2) {
        t1.energy -= Constants.TreeEnergyToSeed;
        t2.energy -= Constants.TreeEnergyToSeed;

        Forest.arrayOfAliveTrees.add(new Tree(t1, t2));
    }

    /*
     * The tick() method is used to - increment energy of the tree increment age
     * of the tree check if age>maxage; if so then the kill() method is called
     * if energy>TreeEnergyToSeed then the tree is added to the temporary array
     * arrayOfReproducing Trees
     */
    public void tick() {
        //this.energy=this.energy+10;
        this.age++;
        if (this.age > this.dna.maxage) {
            this.kill();
        }
        if (this.alive == true && this.age != this.dna.maxage && this.energy > 0) {
            this.grow();
            this.y = Constants.ArenaMaxY - (this.stemlength + this.leafarea);
        }

        if (this.energy > this.dna.energytoseed) {
            this.readyToReproduce = true;
            Forest.arrayOfReproducingTrees.add(this);
        }

        if (this.energy < Constants.ArenaMinEnergy) {
            this.kill();
        }


    }

    /*
     * The kill() method is used to kill the given tree The tree is added to the
     * arrayOfDeadTrees temporary Arraylist from where it will be deleted in the
     * next iteration.
     */
    public void kill() {
        this.alive = false;
        this.energy = 0;
        Forest.arrayOfDeadTrees.add(this);
        //if(this.id==Forest.selectedtree.id)
        // Forest.selectedtree=null;

    }

    public boolean ContainsPoint(double x, double y) {
        if (((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)) <= (this.leafarea * this.leafarea)) {
            return true;
        }
        return false;
    }

    public void sendEnergy(LightBeam l) {
        this.energy = this.energy + l.energy;

    }

    public void grow() {
        double stemlengthgrown = Constants.TreeParameterForGrowth * ((this.dna.stemlength - this.stemlength) / this.dna.maxage);
        double leafareagrown = Constants.TreeParameterForGrowth * ((this.dna.leafarea - this.leafarea) / this.dna.maxage);
        this.stemlength = this.stemlength + stemlengthgrown;
        this.leafarea = this.leafarea + leafareagrown;
        
        if(this.isSelected){
            System.out.println(this.id+" "+stemlengthgrown * Constants.TreeEnergyPerHeight+" "+leafareagrown * Constants.TreeEnergyPerLeafArea);
           
        }

        this.energy = this.energy - stemlengthgrown * Constants.TreeEnergyPerHeight;
        this.energy = this.energy - leafareagrown * Constants.TreeEnergyPerLeafArea;

    }
}
