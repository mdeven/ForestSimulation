package forest;

import java.awt.Color;

/**
 *
 * This file basically contains all parameters that can
 * be varied by the user+some parameters that can be varied only from here.
 */


public class Constants {
    
    /*
     * All Arena Parameters
     */
    
    static double ArenaMaxX = 1050;
    static double ArenaMaxY = 550;
    
    static int ArenaInitialTrees = 100;
    static int ArenaInitialLions = 8;
    static int ArenaInitialGiraffes = 30;
    
    static int ArenaNoOfLightBeams = 50;
    static int ArenaEnergyOfLightBeam = 120;
    
    static double ArenaMinEnergy = 0;
    
    static int ArenaRate = 0;
    
    static double ArenaWindBias = 0.5;
     
    static boolean ArenaShowLight=false;
    
    static int ArenaMoveByYears=100;
    
    
    
    /*
     * All variable Tree Parameters
     */
    
    static final int TreeMaxAge = 100;
    static int Treemaxagecopy = 100;
    static double TreeEnergyToSeed = 450;
    static double TreeMaxStemLength = 150;
    static double TreeMaxLeafArea = 10;
    static double TreeParameterForGrowth = 2.5;
    static double TreeMutStemLength = 1;
    static double TreeMutLeafArea = 0.1;
    static double TreeInitialEnergy = 190;
    static double TreeSeedSpreadMin = 20;
    static double TreeSeedSpreadMax = 100;
    static double TreeInitialLeafArea = 1;
    static double TreeInitialStemLength = 10;
    static Color TreeDefaultColor1 = Color.green;
    static Color TreeDefaultColor2=Color.red;
    static Color TreeDeadColor = Color.BLACK;
    static Color TreeBornColor = Color.WHITE;
    static double TreeDistToRepr = 100;
    static double TreeMutCDist = 10;
    static double TreeColDistToRepr=70;
    static double TreeEnergyPerHeight = 0.6;
    static double TreeEnergyPerLeafArea = 8;
    
   
    
    
    static Color selectedcolor = Color.RED;
    static Color malegiraffe = new Color(128, 64, 0);
    static Color femalegiraffe = Color.PINK;
    static Color selectedgiraffe = new Color(128, 0, 64);
    static Color camouflage1 = new Color(0, 64, 0);
    static Color camouflage2 = new Color(128, 128, 64);
    static Color malelion = new Color(128, 0, 0);
    static Color femalelion = new Color(64, 0, 0);
    
    
    static int gmaxage = 120;
    static double gheight = 140.0;
    static double gratio = 1;
    static double genergytoreproduce = 350;
    static double geatingcapacity = 12;
    static double ginitialenergy = 200;
    static double gmaxspeed = 60;
    static double gangle = 60;
    static double gfertilizationfactor = 0.5;
    static double mutabsgheight = 3.0;
    static double mutabsgnbratio = 0.05;
    static double mutabsgangle = 2;
    static double mutabsgspeed = 1;
   
    
    
    static int lmaxage = 100;
    static int restperiod = 20;
    static double lmaxspeed = 150;
    static double lsize = 60;
    static double InitialAltitude=150;
    static double upperAltitudeLimit=200;
    static double lowerAltitudeLimit=50;
    static double lenergytoreproduce = 300;
    static double lmaxvision = 200;
    static double lcamouflagevision=140;
    static double preyefficiencyper = 50;
    static double initialLenergy = 100;
    static double lfertiliationfactor = 0.5;
    
    static double mutabslspeed = 1;
    static double mutabslvision = 1;
    static double mutabslefficiency = 2;
    static double mutabssize=1;
    
    static double initialGheightPerc = 30;
    static double initialLsizePerc = 40;
    static double parameterforgiraffegrowth = 3.5;
    static double parameterforLiongrowth = 3.5;
    static double genergyperunitheight = 2;
   
    static Color LionSelectedColor=Color.RED;
    
    static double gweighttrees=0.3;
    static double gweightlions=0.3;
    static double gweightfemales=0.1;
    static double gweightmales=0.2;
    
    static double GEnergyForMoving=0.03;
    static double LEnergyForMoving=0.04;
}
