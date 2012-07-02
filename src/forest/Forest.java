package forest;

/**
 *
 * @author Aditya
 */

/*
 * Import various libraries
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

public class Forest {

    /*
     * The variable abort is used to store the 'paused' state of the program. It
     * is true if program is running and false if program is paused. 'timegone'
     * is used to store the no. of years that have passed, so that when program
     * resumes it can display the correct time.
     */
    static boolean abort;
    static int timegone;
    /*
     * Create a 'Graphs' object
     */
    static Graphs graph = new Graphs("Graphs");
    /*
     * ArrayOfAliveTrees stores all the alive trees currently in the forest.
     * ArrayOfDeadTrees is a temporary ArrayList used to store the trees that
     * are to be deleted in the next iteration. ArrayOfReproducingTrees is also
     * a temporary ArrayList used to store the trees that are going to reproduce
     * in the next iteration. ArrayOfTreesToBeAdded stores trees that have just
     * been born. They will be added to the arrayOfAliveTrees in the next
     * iteration.
     */
    static ArrayList<Tree> arrayOfAliveTrees = new ArrayList<Tree>();
    static CopyOnWriteArrayList<Tree> arrayOfDeadTrees = new CopyOnWriteArrayList<Tree>();
    static CopyOnWriteArrayList<Tree> arrayOfReproducingTrees = new CopyOnWriteArrayList<Tree>();
    static CopyOnWriteArrayList<Tree> arrayOfTreesToBeAdded = new CopyOnWriteArrayList<Tree>();
    static CopyOnWriteArrayList<Tree> arrayOfAliveTreesCopy = new CopyOnWriteArrayList<Tree>();
    /*
     * ArrayOfLightBeams stores the Light Beams that are currently falling
     * ArrayOfDeadBeams is a temporary arraylist that stores light beams that
     * have fallen down or have been absorbed. They will be deleted in the next
     * iteration.
     */
    static CopyOnWriteArrayList<LightBeam> arrayOfLightBeams = new CopyOnWriteArrayList<LightBeam>();
    static CopyOnWriteArrayList<LightBeam> arrayOfDeadBeams = new CopyOnWriteArrayList<LightBeam>();
    static CopyOnWriteArrayList<LightBeam> arrayOfLightBeamsCopy = new CopyOnWriteArrayList<LightBeam>();
    /*
     * ArrayOfAliveGiraffes stores all the alive giraffes currently in the
     * forest. ArrayOfDeadGiraffes is a temporary ArrayList used to store the
     * trees that are to be deleted in the next iteration.
     * ArrayOfReproducingGiraffesF and ArrayOfReproducingGiraffesM are also
     * temporary ArrayLists used to store the female and male giraffes that are
     * going to reproduce in the next iteration. ArrayOfGiraffesToBeAdded stores
     * trees that have just been born. They will be added to the
     * arrayOfAliveTrees in the next iteration.
     */
    static ArrayList<Giraffe> arrayOfAliveGiraffes = new ArrayList<Giraffe>();
    static CopyOnWriteArrayList<Giraffe> arrayOfDeadGiraffes = new CopyOnWriteArrayList<Giraffe>();
    static CopyOnWriteArrayList<Giraffe> arrayOfReproducingGiraffesF = new CopyOnWriteArrayList<Giraffe>();
    static CopyOnWriteArrayList<Giraffe> arrayOfGiraffesToBeAdded = new CopyOnWriteArrayList<Giraffe>();
    static CopyOnWriteArrayList<Giraffe> arrayOfAliveGiraffesCopy = new CopyOnWriteArrayList<Giraffe>();
    static CopyOnWriteArrayList<Giraffe> arrayOfReproducingGiraffesM = new CopyOnWriteArrayList<Giraffe>();
    /*
     * ArrayOfAliveLions is the main Array for Lions. ArrayOfDeadLion is a
     * temporary ArrayList used to store the Lions that are to be deleted in the
     * next iteration. There are 2 Arrays arrayOfReproducingLionsF and
     * arrayOfReproducingLionsM for female and male Lions as in Giraffes,who
     * mate are stored at same index in those two arrays.
     */
    static ArrayList<Lion> arrayOfAliveLions = new ArrayList<Lion>();
    static CopyOnWriteArrayList<Lion> arrayOfDeadLions = new CopyOnWriteArrayList<Lion>();
    static CopyOnWriteArrayList<Lion> arrayOfReproducingLionsF = new CopyOnWriteArrayList<Lion>();
    static CopyOnWriteArrayList<Lion> arrayOfReproducingLionsM = new CopyOnWriteArrayList<Lion>();
    static CopyOnWriteArrayList<Lion> arrayOfLionsToBeAdded = new CopyOnWriteArrayList<Lion>();
    static CopyOnWriteArrayList<Lion> arrayOfAliveLionsCopy = new CopyOnWriteArrayList<Lion>();

    /*
     * This function is used to create 'nooftrees' trees to the forest. The
     * trees created are without parent as they mark the start of the
     * simulation.
     */
    public static void create(int nooftrees) {
        /*
         * defaultspacing stores the defaultspacing between trees upon creation.
         * It is also used as an iterating variable when creating trees.
         */
        double defaultspacing = (Constants.ArenaMaxX / (nooftrees + 1));

        /*
         * set abort to true since program will run after this.
         */
        abort = true;
        /*
         * i,a and b are different iterating variables.
         */
        int i, a, b;

        /*
         * New object of the class Random. Will be used to generate light beams
         * randomly.
         */
        Random rand = new Random();

        /*
         * Initialize arrays before adding
         */

        if (arrayOfAliveTrees.isEmpty()) {
            arrayOfAliveTrees.add(null);
        }
        if (arrayOfDeadTrees.isEmpty()) {
            arrayOfDeadTrees.add(null);
        }
        if (arrayOfReproducingTrees.isEmpty()) {
            arrayOfReproducingTrees.add(null);
        }
        if (arrayOfTreesToBeAdded.isEmpty()) {
            arrayOfTreesToBeAdded.add(null);
        }
        if (arrayOfLightBeams.isEmpty()) {
            arrayOfLightBeams.add(null);
        }
        if (arrayOfDeadBeams.isEmpty()) {
            arrayOfDeadBeams.add(null);
        }
        if (arrayOfAliveTreesCopy.isEmpty()) {
            arrayOfAliveTreesCopy.add(null);
        }
        if (arrayOfLightBeamsCopy.isEmpty()) {
            arrayOfLightBeamsCopy.add(null);
        }
        if (arrayOfAliveGiraffes.isEmpty()) {
            arrayOfAliveGiraffes.add(null);
        }
        if (arrayOfDeadGiraffes.isEmpty()) {
            arrayOfDeadGiraffes.add(null);
        }
        if (arrayOfReproducingGiraffesF.isEmpty()) {
            arrayOfReproducingGiraffesF.add(null);
        }
        if (arrayOfReproducingGiraffesM.isEmpty()) {
            arrayOfReproducingGiraffesM.add(null);
        }
        if (arrayOfGiraffesToBeAdded.isEmpty()) {
            arrayOfGiraffesToBeAdded.add(null);
        }
        if (arrayOfAliveLions.isEmpty()) {
            arrayOfAliveLions.add(null);
        }
        if (arrayOfDeadLions.isEmpty()) {
            arrayOfDeadLions.add(null);
        }
        if (arrayOfReproducingLionsF.isEmpty()) {
            arrayOfReproducingLionsF.add(null);
        }
        if (arrayOfReproducingLionsM.isEmpty()) {
            arrayOfReproducingLionsM.add(null);
        }
        if (arrayOfLionsToBeAdded.isEmpty()) {
            arrayOfLionsToBeAdded.add(null);
        }

        /*
         * Create nooftrees trees upon creation with default spacing
         */

        for (i = 1; i <= nooftrees; i++) {
            if (i % 2 == 0) {
                arrayOfAliveTrees.add(i, new Tree(defaultspacing, Constants.TreeDefaultColor1));
            } else {
                arrayOfAliveTrees.add(i, new Tree(defaultspacing, Constants.TreeDefaultColor2));
            }
            defaultspacing = defaultspacing + (Constants.ArenaMaxX / (nooftrees + 1));
        }

        for (a = 0; a < 275; a++) {
            for (b = 0; b < Constants.ArenaNoOfLightBeams; b++) {
                arrayOfLightBeams.add(new LightBeam((rand.nextDouble()) * Constants.ArenaMaxX, 2 * a, 40.0));
            }
        }
    }
    /*
     * This comparator is used when comparing two arrays. Here we compare them
     * on the basis of the stemlengths. This comparator will be required when
     * sorting the array based on height. We will need this for eg, if a light
     * beam is found in two trees, the taller one gets it
     */
    static final Comparator<Tree> comparator = new Comparator<Tree>() {

        @Override
        public int compare(Tree a, Tree b) {
            if (a != null && b != null) {
                if (a.stemlength > b.stemlength) {
                    return 1;
                }
                if (a.stemlength < b.stemlength) {
                    return -1;
                }
                return 0;
            }

            if (a == null && b != null) {
                return -1;
            }
            if (a != null && b == null) {
                return 1;
            }
            return 0;
        }
    };
    /*
     * Comparator for giraffes. Based on necklength.
     */
    static final Comparator<Giraffe> bigneck = new Comparator<Giraffe>() {

        @Override
        public int compare(Giraffe a, Giraffe b) {
            if (a != null && b != null) {
                if (a.necklength > b.necklength) {
                    return -1;
                } else if (a.necklength < b.necklength) {
                    return 1;
                }
            }

            if (a == null && b != null) {
                return -1;
            }
            if (a != null && b == null) {
                return 1;
            }

            return 0;
        }
    };

    /*
     * This function is used to move the forest forward by some years.
     */
    public static void time(int years) {

        /*
         * i,j,k are iteration variables.
         */

        int i, j, k;

        /*
         * bornyear and count variables are used to create lions one at a time
         * and not together.
         */
        int bornyear = Constants.ArenaYearAfterLionsToBeAdded;
        int count = 1;
        /*
         * A new random obbject. Will be used while created new light beams
         */
        Random rand = new Random();

        /*
         * This loop iterators for 'years' years.
         */

        for (i = timegone; i < years && abort; i++) {


            /*
             * Add Giraffes and Lions at the right time.
             */

            if (i == Constants.ArenaYearAfterGiraffesToBeAdded) {
                int p;
                double gspacing = Constants.ArenaMaxX / (Constants.ArenaInitialGiraffes + 1);
                for (p = 1; p <= Constants.ArenaInitialGiraffes; p++) {
                    arrayOfAliveGiraffes.add(p, new Giraffe(gspacing));
                    gspacing += Constants.ArenaMaxX / (Constants.ArenaInitialGiraffes + 1);

                }
            }

            if (i == bornyear && Constants.ArenaInitialLions != 0) {
                arrayOfAliveLions.add(count++, new Lion(rand.nextDouble() * Constants.ArenaMaxX));
                if ((bornyear + Constants.lmaxage / Constants.ArenaInitialLions) < (2000 + Constants.lmaxage)) {
                    bornyear += Constants.lmaxage / Constants.ArenaInitialLions;
                }
            }
            /*
             * Clear Dead Trees.
             */
            arrayOfAliveTrees.removeAll(arrayOfDeadTrees);
            arrayOfDeadTrees.clear();

            /*
             * Add new trees to the forest.
             */
            arrayOfAliveTrees.addAll(arrayOfTreesToBeAdded);
            arrayOfTreesToBeAdded.clear();

            /*
             * Remove dead light beams.
             */
            arrayOfLightBeams.removeAll(arrayOfDeadBeams);
            arrayOfDeadBeams.clear();

            /*
             * Clear Dead Giraffes.
             */
            arrayOfAliveGiraffes.removeAll(arrayOfDeadGiraffes);
            arrayOfDeadGiraffes.clear();

            /*
             * Add new giraffes to the forest.
             */
            arrayOfAliveGiraffes.addAll(arrayOfGiraffesToBeAdded);
            arrayOfGiraffesToBeAdded.clear();
            /*
             * Clear Dead Lions.
             */
            arrayOfAliveLions.removeAll(arrayOfDeadLions);
            arrayOfDeadLions.clear();

            /*
             * Add new Lions to the Forest.
             */
            arrayOfAliveLions.addAll(arrayOfLionsToBeAdded);
            arrayOfLionsToBeAdded.clear();

            /*
             * Tick all alive trees.
             */
            for (Tree t : arrayOfAliveTrees) {
                if (t != null) {
                    t.tick();
                }
            }

            /*
             * Tick all light beams.
             */
            for (LightBeam l : arrayOfLightBeams) {
                if (l != null) {
                    l.tick();
                }
            }

            /*
             * Tick all Giraffes.
             */
            for (Giraffe g : arrayOfAliveGiraffes) {
                if (g != null) {
                    g.tick();

                }
            }

            /*
             * Tick all Lions.
             */
            for (Lion l : arrayOfAliveLions) {
                if (l != null) {
                    l.tick();
                }

            }

            /*
             * For all trees ready to reproduce, call the CreateSeed function
             * and then clear the temporary array.
             */
            for (Tree t : arrayOfReproducingTrees) {
                if (t != null && t.readyToReproduce) {
                    for (Tree t2 : arrayOfReproducingTrees) {
                        if (t2 != null && t != t2 && (Math.abs((t2.x - t.x)) <= Constants.TreeDistToRepr) && (Forest.RGBDistance(t.color, t2.color) <= Constants.TreeColDistToRepr && t2.readyToReproduce)) {
                            if (!t.readyToReproduce) {
                                break;
                            }

                            Tree.CreateSeed(t, t2);
                            t.readyToReproduce = false;
                            t2.readyToReproduce = false;
                        }
                    }
                }
            }
            arrayOfReproducingTrees.clear();

            /*
             * For all giraffes ready to reproduce, call the reproduce function
             * and then clear the temporary array.
             */
            for (j = 0; j < arrayOfReproducingGiraffesF.size(); j++) {
                if (arrayOfReproducingGiraffesF.get(j) != null) {
                    arrayOfReproducingGiraffesF.get(j).reproduce(arrayOfReproducingGiraffesM.get(j));
                }
            }

            arrayOfReproducingGiraffesF.clear();
            arrayOfReproducingGiraffesM.clear();

            for (Giraffe g : arrayOfAliveGiraffes) {
                if (g != null && g.energy > g.dna.energytoreproduce) {
                    g.ReadyToReproduce = true;
                } else if (g != null && g.energy < g.dna.energytoreproduce) {
                    g.ReadyToReproduce = false;
                }
            }

            /*
             * For all Lions ready to reproduce, call the reproduce function and
             * then clear the temporary arrays for male and female Lions.
             */
            for (j = 0; j < arrayOfReproducingLionsF.size(); j++) {
                if (arrayOfReproducingLionsF.get(j) != null && arrayOfReproducingLionsM.get(j) != null) {
                    arrayOfReproducingLionsF.get(j).reproduce(arrayOfReproducingLionsM.get(j));
                }
            }

            arrayOfReproducingLionsF.clear();
            arrayOfReproducingLionsM.clear();

            /*
             * Sorts the array based on the comparator,i.e. height.
             */
            Collections.sort(arrayOfAliveTrees, comparator);

            /*
             * Sort giraffes according to necklength
             */
            Collections.sort(arrayOfAliveGiraffes, bigneck);

            /*
             * Creates a fixed amount of light beam with a fixed amount of
             * energy.
             */
            for (j = 0; j < Constants.ArenaNoOfLightBeams; j++) {
                arrayOfLightBeams.add(new LightBeam((rand.nextDouble()) * Constants.ArenaMaxX, 0, Constants.ArenaEnergyOfLightBeam));
            }

            /*
             * Varies through the forest to find trees containing light beams.
             * If found, energy from the light beam is transferred to the plant
             * and the light beam is removed from the forest.
             */
            for (k = arrayOfAliveTrees.size() - 1; k > 0; k--) {
                if (arrayOfAliveTrees.get(k) != null) {
                    for (LightBeam lb : arrayOfLightBeams) {
                        if (arrayOfAliveTrees.get(k).ContainsPoint(lb.x, lb.y) && lb.alive) {
                            arrayOfAliveTrees.get(k).sendEnergy(lb);
                            lb.alive = false;
                            arrayOfDeadBeams.add(lb);
                        }
                    }
                }
            }



            Constants.restperiod = 10 + (int) 2 * arrayOfAliveLions.size();
            for (Giraffe g : Forest.arrayOfAliveGiraffes) {
                if (g != null) {
                    g.eatingcapacity = 15 - Forest.arrayOfAliveGiraffes.size() / 15.0;
                }
            }


            /*
             * Remove all dead trees,light beams,giraffes,lions again before
             * reloading the Arena.
             */

            arrayOfAliveTrees.removeAll(arrayOfDeadTrees);
            arrayOfDeadTrees.clear();
            arrayOfLightBeams.removeAll(arrayOfDeadBeams);
            arrayOfDeadBeams.clear();
            arrayOfAliveGiraffes.removeAll(arrayOfDeadGiraffes);
            arrayOfDeadGiraffes.clear();
            arrayOfAliveLionsCopy.clear();
            arrayOfAliveLionsCopy.addAll(arrayOfAliveLions);


            /*
             * Update graph every 100 years.
             */
            if (i % 100 == 0) {
                Graphs.SeriesPlantHeight.add(i, averageheight());
                Graphs.SeriesTreePopulation.add(i, arrayOfAliveTrees.size() - 1);
                Graphs.SeriesGiraffePopulation.add(i, Forest.arrayOfAliveGiraffes.size() - 1);
                Graphs.SeriesGiraffeHeight.add(i, GiraffeAvgHeight());
                Graphs.SeriesLionPopulation.add(i, arrayOfAliveLions.size() - 1);
                Graphs.SeriesGiraffeNeckLength.add(i, GiraffeAvgNL());
                Graphs.dataset.removeAllSeries();
                Graphs.dataset.addSeries(Graphs.SeriesPlantHeight);
                Graphs.dataset.addSeries(Graphs.SeriesTreePopulation);
                Graphs.dataset.addSeries(Graphs.SeriesGiraffePopulation);
                Graphs.dataset.addSeries(Graphs.SeriesGiraffeHeight);
                Graphs.dataset.addSeries(Graphs.SeriesGiraffeNeckLength);
                Graphs.dataset.addSeries(Graphs.SeriesLionPopulation);
                graph.repaint();
            }




            /*
             * Creates a copy of the Trees and light beam array to be used to
             * draw the trees. This is needed because we cannot iterate over the
             * original array to paint it and also modify it at the same time.
             */

            arrayOfAliveTreesCopy.clear();
            arrayOfAliveTreesCopy.addAll(arrayOfAliveTrees);

            arrayOfLightBeamsCopy.clear();
            arrayOfLightBeamsCopy.addAll(arrayOfLightBeams);

            arrayOfAliveGiraffesCopy.clear();
            arrayOfAliveGiraffesCopy.addAll(arrayOfAliveGiraffes);


            /*
             * Update GUI Components.
             */
            GUI.LabelNumberOfTrees.setText(arrayOfAliveTreesCopy.size() - 1 + "");
            GUI.LabelTAvgHeight.setText(Math.round(averageheight() * 100) / 100.0 + "");
            GUI.LabelAverageLeafArea.setText(Math.round(100 * averageleafarea()) / 100.0 + "");
            GUI.LabelYearsPassed.setText("" + (i + 1));
            GUI.LabelNumberOfGiraffes.setText(arrayOfAliveGiraffesCopy.size() - 1 + "");
            GUI.LabelNumberOfLions.setText(arrayOfAliveLionsCopy.size() - 1 + "");
            GUI.LabelGAvgHeight.setText(Math.round(100 * Forest.GiraffeAvgHeight()) / 100.0 + "");
            GUI.LabelGAvgNeckLength.setText(Math.round(100 * Forest.GiraffeAvgNL()) / 100.0 + "");

            /*
             * Update GUI info for selected tree/giraffe/lion
             */
            for (Tree t : Forest.arrayOfAliveTrees) {
                if (t != null && t.isSelected) {
                    GUI.LabelID.setText(t.id + "");
                    GUI.LabelSelectedAge.setText(t.age + "");
                    GUI.LabelSelectedHeight.setText(((Math.round(t.stemlength * 100)) / 100.0) + "");
                    GUI.LabelSelectedLeafArea.setText(((Math.round(t.leafarea * 100)) / 100.0) + "");
                    GUI.LabelSelectedEnergy.setText(Math.round(t.energy * 100) / 100.0 + "");
                }
            }

            for (Giraffe g : Forest.arrayOfAliveGiraffes) {

                if (g != null && g.isSelected) {
                    GUI.LabelGID.setText(g.id + "");
                    GUI.LabelSelectedGAge.setText(g.age + "");
                    GUI.LabelSelectedGHeight.setText(((Math.round(g.bodylength * 100)) / 100.0) + "");
                    GUI.LabelSelectedGEnergy.setText(Math.round(g.energy * 100) / 100.0 + "");
                    if (g.isHungry) {
                        GUI.LabelSelectedGStatus.setText("Looking for food");
                    }
                    if (g.LookingForFemale) {
                        GUI.LabelSelectedGStatus.setText("Looking for females");
                    }
                    if (g.isHungry == false && g.LookingForFemale == false) {
                        GUI.LabelSelectedGStatus.setText("Loitering");
                    }
                }
            }


            for (Lion l : Forest.arrayOfAliveLions) {
                if (l != null && l.isSelected) {
                    GUI.LabelLID.setText(l.id + "");
                    GUI.LabelLAge.setText(l.age + "");
                    GUI.LabelSelectedLSize.setText(((Math.round(l.currentsize * 100)) / 100.0) + "");
                    GUI.LabelLEnergy.setText(Math.round(l.energy * 100) / 100.0 + "");
                }
            }
            if (arrayOfAliveTrees.size() <= 1 && arrayOfAliveGiraffes.size() <= 1 && arrayOfAliveLions.size() <= 1) {
                Forest.abort = false;
            }


            /*
             * Reload the Arena with updated trees.
             */

            GUI.Arena.repaint();
            GUI.Arena.revalidate();

            /*
             * Sleep for some time
             */
            try {
                Thread.sleep(Constants.ArenaRate);
            } catch (InterruptedException ex) {
                Logger.getLogger(Forest.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /*
     * Computes the average height of the forest.
     */
    public static double averageheight() {
        if (arrayOfAliveTrees.size() <= 1) {
            return 0;
        }

        double avght = arrayOfAliveTrees.get(1).stemlength;
        int i;
        for (i = 2; i < arrayOfAliveTrees.size(); i++) {
            if (arrayOfAliveTrees.get(i) != null) {
                avght = (1 - 1.0 / i) * avght + (arrayOfAliveTrees.get(i).stemlength / (i));
            }
        }
        return avght;
    }

    /*
     * Computes the average leaf area of the forest.
     */
    public static double averageleafarea() {
        if (arrayOfAliveTrees.size() <= 1) {
            return 0;
        }

        double avgla = arrayOfAliveTrees.get(1).leafarea;
        int i;
        for (i = 1; i < arrayOfAliveTrees.size(); i++) {
            if (arrayOfAliveTrees.get(i) != null) {
                avgla = (1 - 1.0 / i) * avgla + (arrayOfAliveTrees.get(i).leafarea / (i));
            }
        }
        return avgla;
    }

    /*
     * Computes average height of the Giraffes.
     */
    public static double GiraffeAvgHeight() {
        if (arrayOfAliveGiraffes.size() <= 1) {
            return 0;
        }

        double avght = arrayOfAliveGiraffes.get(1).currentheight;
        int i;
        for (i = 2; i < arrayOfAliveGiraffes.size(); i++) {
            if (arrayOfAliveGiraffes.get(i) != null) {
                avght = (1 - 1.0 / i) * avght + (arrayOfAliveGiraffes.get(i).currentheight / (i));
            }
        }
        return avght;
    }

    /*
     * Calculate average Giraffe neck length
     */
    public static double GiraffeAvgNL() {
        if (arrayOfAliveGiraffes.size() <= 1) {
            return 0;
        }

        double avght = arrayOfAliveGiraffes.get(1).necklength;
        int i;
        for (i = 2; i < arrayOfAliveGiraffes.size(); i++) {
            if (arrayOfAliveGiraffes.get(i) != null) {
                avght = (1 - 1.0 / i) * avght + (arrayOfAliveGiraffes.get(i).necklength / (i));
            }
        }
        return avght;
    }
    /*
     * Calculate average x coordinate for giraffes
     */

    public static double gaverageX() {
        if (arrayOfAliveGiraffes.size() <= 1) {
            return 0;
        }

        double avgx = arrayOfAliveGiraffes.get(1).x;
        int i;
        for (i = 2; i < arrayOfAliveGiraffes.size(); i++) {
            if (arrayOfAliveGiraffes.get(i) != null) {
                avgx = (1 - 1.0 / i) * avgx + (arrayOfAliveGiraffes.get(i).x / (i));
            }
        }
        return avgx;
    }

    /*
     * Calculate average x coordinate for lions
     */
    public static double laverageX() {
        if (arrayOfAliveLions.size() <= 1) {
            return 0;
        }

        double avgx = arrayOfAliveLions.get(1).x;
        int i;
        for (i = 2; i < arrayOfAliveLions.size(); i++) {
            if (arrayOfAliveLions.get(i) != null) {
                avgx = (1 - 1.0 / i) * avgx + (arrayOfAliveLions.get(i).x / (i));
            }
        }
        return avgx;
    }
    /*
     * Calculate average y coordinate for lions
     */

    public static double laverageY() {
        if (arrayOfAliveLions.size() <= 1) {
            return 0;
        }

        double avgy = arrayOfAliveLions.get(1).y;
        int i;
        for (i = 2; i < arrayOfAliveLions.size(); i++) {
            if (arrayOfAliveLions.get(i) != null) {
                avgy = (1 - 1.0 / i) * avgy + (arrayOfAliveLions.get(i).y / (i));
            }
        }
        return avgy;
    }

    /*
     * The run function. Is called when someone clicks the 'run' button.
     */
    public static void run() {
        /*
         * Creates a forest with 'initialtres' no. of trees
         */
        Forest.create(Constants.ArenaInitialTrees);

        /*
         * Advances the forest forward by 'ArenaMoveByYears' years
         */
        Forest.time(Constants.ArenaMoveByYears);

    }
    /*
     * Is called when the stop button is pressed. Clears all data.
     */

    public static void stop() {
        Forest.timegone = 0;
        abort = false;
        arrayOfAliveTrees.clear();
        arrayOfDeadTrees.clear();
        arrayOfAliveGiraffes.clear();
        arrayOfDeadGiraffes.clear();
        arrayOfLightBeams.clear();
        arrayOfReproducingTrees.clear();
        arrayOfTreesToBeAdded.clear();
        arrayOfReproducingGiraffesF.clear();
        arrayOfReproducingGiraffesM.clear();
        arrayOfGiraffesToBeAdded.clear();
        arrayOfDeadBeams.clear();
        arrayOfAliveTreesCopy.clear();
        arrayOfLightBeamsCopy.clear();
        arrayOfAliveGiraffesCopy.clear();
        arrayOfAliveLions.clear();
        arrayOfAliveLionsCopy.clear();
        arrayOfDeadLions.clear();
        arrayOfReproducingLionsM.clear();
        arrayOfReproducingLionsF.clear();
        GUI.Arena.repaint();
        GUI.Arena.revalidate();
        Graphs.SeriesPlantHeight.clear();
        Graphs.SeriesTreePopulation.clear();
        Graphs.SeriesGiraffePopulation.clear();
        Graphs.SeriesGiraffeNeckLength.clear();
        Graphs.SeriesGiraffeHeight.clear();
        Graphs.SeriesLionPopulation.clear();
        Forest.graph.repaint();


    }
    /*
     * Is called when the pause button is clicked
     */

    public static void pause() {
        if (abort == true) {
            abort = false;
            GUI.ButtonPaused.setText("Resume");
        } else {
            if (abort == false) {
                abort = true;
                GUI.ButtonPaused.setText("Pause");
            }

        }
        if (abort == true) {
            Forest.timegone = Integer.parseInt(GUI.LabelYearsPassed.getText());
            SwingWorker s = new SwingWorker<Integer, Void>() {

                @Override
                public Integer doInBackground() {
                    Forest.time(Constants.ArenaMoveByYears);

                    return 0;
                }

                @Override
                public void done() {
                }
            };
            s.execute();
        }
    }
    /*
     * Is used to compute 'distance' between two colours assuming the r,g,b
     * values to be like 3-d space
     */

    public static double RGBDistance(Color c1, Color c2) {
        double diffr = c1.getRed() - c2.getRed();
        double diffg = c1.getGreen() - c2.getGreen();
        double diffb = c1.getBlue() - c2.getBlue();

        return Math.sqrt((diffr * diffr) + (diffg * diffg) + (diffb * diffb));

    }

    /*
     * Used to mutate the colour
     */
    public static Color MutateColor(Color c) {
        Random rand = new Random();
        int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
        if (rand.nextBoolean()) {
            r = (int) (c.getRed() - Constants.TreeMutCDist + (2 * Constants.TreeMutCDist * rand.nextDouble()));
        }
        if (rand.nextBoolean()) {
            g = (int) (c.getGreen() - Constants.TreeMutCDist + (2 * Constants.TreeMutCDist * rand.nextDouble()));
        }
        if (rand.nextBoolean()) {
            b = (int) (c.getBlue() - Constants.TreeMutCDist + (2 * Constants.TreeMutCDist * rand.nextDouble()));
        }

        if (r > 255) {
            r = 255;
        }
        if (r < 0) {
            r = 0;
        }
        if (g > 255) {
            g = 255;
        }
        if (g < 0) {
            g = 0;
        }
        if (b > 255) {
            b = 255;
        }
        if (b < 0) {
            b = 0;
        }


        return new Color(r, g, b);
    }
}