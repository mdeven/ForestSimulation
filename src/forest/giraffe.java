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
public class giraffe extends Object {

    gdna dna;
    long id;
    int age;
    static long nextGid = 1;
    double currentheight;
    double necklength;
    double bodylength;
    double angle;
    boolean gender;
    static long nextgender = 1;
    boolean ReadyToReproduce;
    double eatingcapacity;
    double currentmaxspeed;
    double sight;
    
    double weighttrees;
    double weightfemales;
    double weightlions;
    double weightmales;
    
    boolean isHungry;
    boolean LookingForFemale;
    
    Random r = new Random();

    /*
     * Constructor to initialize giraffe created at the beginning of Forest
     */
    public giraffe(double x) {


        if ((nextgender % 2) == 0) {
            this.gender = true;
        } else {
            this.gender = false;
        }
        nextgender++;

        this.ReadyToReproduce = false;
        this.dna = new gdna();
        this.age = 0;
        this.alive = true;
        this.id = nextGid++;
        this.currentmaxspeed = 0;
        this.energy = Constants.ginitialenergy;
        this.currentheight = this.dna.height * Constants.initialGheightPerc / 100.0;
        this.eatingcapacity = Constants.geatingcapacity;
        this.x = x;
        this.necklength = this.dna.neckbodyratio * this.currentheight / (this.dna.neckbodyratio + 1);
        this.bodylength = this.currentheight / (this.dna.neckbodyratio + 1);
        this.angle = (Constants.gangle - Constants.mutabsgangle) + (2 * Constants.mutabsgangle * r.nextDouble());
        this.isSelected = false;
        this.weighttrees=Constants.gweighttrees;
        this.weightfemales=Constants.gweightfemales;
        this.weightlions=Constants.gweightlions;
        this.weightmales=Constants.gweightmales;
        this.sight=1.5*this.dna.maxspeed;
              
    }

    /*
     * Constructor to initialize offsprings of giraffes through Sexual
     * Reproduction.
     */
    public giraffe(giraffe parentfemale, giraffe parentmale) {

        if ((nextgender % 2) == 0) {
            this.gender = true;
        } else {
            this.gender = false;
        }
        nextgender++;
        this.ReadyToReproduce = false;
        this.dna = new gdna(parentfemale, parentmale);
        this.age = 0;
        this.alive = true;
        this.id = nextGid++;
        this.currentmaxspeed = 0;
        this.energy = Constants.ginitialenergy;
        this.currentheight = this.dna.height * Constants.initialGheightPerc / 100.0;
        this.eatingcapacity = Constants.geatingcapacity;
        this.x = parentfemale.x;
        this.necklength = this.dna.neckbodyratio * this.currentheight / (this.dna.neckbodyratio + 1);
        this.bodylength = this.currentheight / (this.dna.neckbodyratio + 1);
        this.isSelected = false;
        this.angle = ((parentfemale.angle * Constants.gfertilizationfactor + parentmale.angle * (1 - Constants.gfertilizationfactor)) - Constants.mutabsgangle) + (2 * Constants.mutabsgangle * r.nextDouble());
        this.weighttrees=Constants.gweighttrees;
        this.weightfemales=Constants.gweightfemales;
        this.weightlions=Constants.gweightlions;
        this.weightmales=Constants.gweightmales;
        this.sight=1.5*this.dna.maxspeed;
    }

    @Override
    public void tick() {

        if (this != null) {
            this.age++;
            if (this.alive == true && this.age != this.dna.maxage && this.energy > 0) {
                this.eat();
            }

            if (this != null) {
               this.move();
            }

            if (this != null) {
                this.grow();
            }

            if (this != null) {
                this.setspeed();
            }

            if (this.age > this.dna.maxage) {
                this.kill();
            }

            if (this.energy < Constants.ArenaMinEnergy) {
                this.kill();
            }

            if (this.gender == false && this.ReadyToReproduce == true) {
                for (giraffe g : Forest.arrayOfAliveGiraffes) {
                    if (g != null) {
                        if (g.gender == true && g.ReadyToReproduce == true&&(Math.abs(g.x-this.x)<=g.sight)) {
                            Forest.arrayOfReproducingGiraffesF.add(this);
                            Forest.arrayOfReproducingGiraffesM.add(g);
                            this.energy -= this.dna.energytoreproduce;
                            g.energy -= g.dna.energytoreproduce;
                            //System.out.println(g.id);
                            if (g.energy < g.dna.energytoreproduce) {
                                g.ReadyToReproduce = false;
                            }
                            break;
                        }
                    }
                }
            }
            
            if(this.gender){
                if(this.energy>0.8*this.dna.energytoreproduce){
                   this.isHungry=false;
                   this.LookingForFemale=true;                    
                }
                if(this.energy>0.2*this.dna.energytoreproduce&&this.energy<0.8*this.dna.energytoreproduce){
                    this.isHungry=false;
                this.LookingForFemale=false;
                }
                if(this.energy<0.2*this.dna.energytoreproduce){
                    this.isHungry=true;
                this.LookingForFemale=false;
                }
                  
            }
            
            else{
                this.LookingForFemale=false;
                if(this.energy<0.2*this.dna.energytoreproduce){
                    this.isHungry=true;
                }
                else{
                    this.isHungry=false;
                }
            }
          }
        
        if(this.isHungry){
            this.weighttrees=0.8;
            this.weightfemales=0;
            this.weightlions=0.2;
            this.weightmales=0;
        }
        if(this.LookingForFemale){
            this.weighttrees=0.2;
            this.weightfemales=0.6;
            this.weightlions=0.2;
        }
        if(this.isHungry==false&&this.LookingForFemale==false&&this.gender){
            this.weighttrees=0.3;
            this.weightfemales=0.1;
            this.weightlions=0.3;
            this.weightmales=0.3;
        }
        if(this.isHungry==false&&this.gender==false){
            this.weighttrees=0.4;
            this.weightlions=0.4;
            this.weightfemales=0;
            this.weightmales=0.2;
        }
            

    }

    public void kill() {
        this.alive = false;
        this.energy = 0;
        Forest.arrayOfDeadGiraffes.add(this);
    }

    public int reproduce(giraffe g) {
        
        //System.out.println("OFFSPRING "+this.energy);
        Forest.arrayOfGiraffesToBeAdded.add(new giraffe(this, g));
        return 1;
    }

    public void eat() {
        long start=Math.round(r.nextDouble()*Forest.arrayOfAliveTrees.size());
        //System.out.println("sfa "+start);
          if(start < Forest.arrayOfAliveTrees.size()/2 ){
            for (int i=(int)start;i<Forest.arrayOfAliveTrees.size();i++) { 
                if (Forest.arrayOfAliveTrees.get(i) != null && this.ContainsTree(Forest.arrayOfAliveTrees.get(i).x, Constants.ArenaMaxY - Forest.arrayOfAliveTrees.get(i).y) == true) {
                    if (Forest.arrayOfAliveTrees.get(i).energy > this.eatingcapacity) {
                        this.energy += this.eatingcapacity;
                        //System.out.println(this.energy);
                        Forest.arrayOfAliveTrees.get(i).energy -= this.eatingcapacity;
                        break;
                } 
                    else if (Forest.arrayOfAliveTrees.get(i).energy < this.eatingcapacity) {
                        this.energy += Forest.arrayOfAliveTrees.get(i).energy;
                        //System.out.println(this.energy);
                        Forest.arrayOfAliveTrees.get(i).energy = 0;
                        Forest.arrayOfAliveTrees.get(i).kill();
                        break;
                    }
                }
            }
        }
          else{
              for (int i=(int)start-1;i>0;i--) { 
                if (Forest.arrayOfAliveTrees.get(i) != null && this.ContainsTree(Forest.arrayOfAliveTrees.get(i).x, Constants.ArenaMaxY - Forest.arrayOfAliveTrees.get(i).y) == true) {
                    if (Forest.arrayOfAliveTrees.get(i).energy > this.eatingcapacity) {
                        this.energy += this.eatingcapacity;
                        //System.out.println(this.energy);
                        Forest.arrayOfAliveTrees.get(i).energy -= this.eatingcapacity;
                        break;
                } 
                    else if (Forest.arrayOfAliveTrees.get(i).energy < this.eatingcapacity) {
                        this.energy += Forest.arrayOfAliveTrees.get(i).energy;
                        //System.out.println(this.energy);
                        Forest.arrayOfAliveTrees.get(i).energy = 0;
                        Forest.arrayOfAliveTrees.get(i).kill();
                        break;
                    }

                }
            }
          }
    }

    public void grow() {
        double heightgrown = Constants.parameterforgiraffegrowth * (this.dna.height - this.currentheight) / this.dna.maxage;
        this.currentheight += heightgrown;
        this.necklength = this.dna.neckbodyratio * this.currentheight / (this.dna.neckbodyratio + 1);
        this.bodylength = this.currentheight / (this.dna.neckbodyratio + 1);
        this.energy -= Constants.genergyperunitheight;
    }

    public boolean ContainsTree(double x, double y) {
        
        if ((((this.x - x) * (this.x - x) + (this.bodylength - y) * (this.bodylength - y)) < (this.necklength) * (this.necklength)) && y>this.bodylength) {
            //System.out.println(((this.x-x)*(this.x-x)+(this.bodylength-y)*(this.bodylength-y))+"  "+(this.necklength) * (this.necklength));
            //System.out.println(y+" "+this.bodylength);
            return true;
        }
        return false;
    }

    public boolean ContainsPoint(double x, double y) {
        double a = this.currentheight / 6;
        double b = this.bodylength / 6;
        double centreX = this.x - this.currentheight / 6;
        double centreY = Constants.ArenaMaxY - this.bodylength;

        if (((x - centreX) * (x - centreX) / (a * a) + (y - centreY) * (y - centreY) / (b * b)) <= 1) {
            return true;
        }
        return false;
    }

    public double DecideDirection() {
        int treeright=0,treeleft=0;
        double weightedtreediff;
        for(Tree t:Forest.arrayOfAliveTrees){
            if(t!=null&&t.x>this.x&&t.x<=(this.x+this.sight))
                treeright++;
            if(t!=null&&t.x<this.x&&t.x>=(this.x-this.sight))
                treeleft++;
         }
        if(treeright>0)
            weightedtreediff=this.weighttrees*(treeright-treeleft)/(treeright+treeleft);
        else {
            if(treeleft>0)
                weightedtreediff=-this.weighttrees;
            else 
                weightedtreediff=0;
        }
            
            
        
        int femaleright=0,femaleleft=0;
        double weightedfemalediff;
        for(giraffe t:Forest.arrayOfAliveGiraffes){
            if(t!=null&&t.x>this.x&&t.x<=(this.x+this.sight)&&t.gender==false)
                femaleright++;
            if(t!=null&&t.x<this.x&&t.x>=(this.x-this.sight)&&t.gender==false)
                femaleleft++;
         }
        if(femaleright>0)
        weightedfemalediff=this.weightfemales*(femaleright-femaleleft)/(femaleright+femaleleft);
        else if(femaleleft>0)
            weightedfemalediff=-this.weightfemales;
        else 
            weightedfemalediff=0;
        
        
        
        int lionright=0,lionleft=0;
        double weightedliondiff;
        for(Lion l:Forest.arrayOfAliveLions){
            if(l!=null&&l.x>this.x&&l.x<=(this.x+this.sight))
                lionright++;
            if(l!=null&&l.x<this.x&&l.x>=(this.x-this.sight))
                lionleft++;
           }
        if(lionright>0)
        weightedliondiff=this.weightlions*(-lionright+lionleft)/(lionright+lionleft);
        else if(lionleft>0)
            weightedliondiff=this.weightlions;
        else
            weightedliondiff=0;
        
        
        int maleright=0,maleleft=0;
        double weightedmalediff;
        for(giraffe t:Forest.arrayOfAliveGiraffes){
            if(t!=null&&t.x>this.x&&t.x<=(this.x+this.sight)&&t.gender)
                maleright++;
            if(t!=null&&t.x<this.x&&t.x>=(this.x-this.sight)&&t.gender)
                maleleft++;
         }
        if(maleright>0)
        weightedmalediff=this.weightmales*(-maleright+maleleft)/(maleright+maleleft);
        else if(maleleft>0)
            weightedmalediff=this.weightmales;
        else 
            weightedmalediff=0;
        
        
      //weightedtreediff=5;
      //System.out.println(weightedfemalediff);
       //System.out.println(weightedtreediff+weightedfemalediff+weightedliondiff>0);
        if((weightedtreediff+weightedfemalediff+weightedliondiff+weightedmalediff)>0)
            return 1;
        if((weightedtreediff+weightedfemalediff+weightedliondiff+weightedmalediff)==0)
        return 0.5;
        return 0;
                 
            
        
    }

    public void setspeed() {
        double matureage = 0.2 * this.dna.maxage;
        double oldage = 0.7 * this.dna.maxage;
        if (this.age < matureage) {
            this.currentmaxspeed = Math.sin((age / matureage) * 3.14159 / 2) * this.dna.maxspeed;
        } else if (this.age > oldage) {
            this.currentmaxspeed = Math.cos(((age - oldage) / (this.dna.maxage - oldage)) * 3.14159 / 2) * this.dna.maxspeed;
        }
        //System.out.println(age+" "+currentmaxspeed);
    }

    public void move() {
        double distancemoved;
        
        if(this.DecideDirection()==1){
            distancemoved=(this.currentmaxspeed*r.nextDouble());
            this.x=this.x+distancemoved;
            if(this.x>Constants.ArenaMaxX){
                double temp=this.currentmaxspeed*r.nextDouble();
                this.x-=distancemoved-temp;
                distancemoved=temp;
                
            }
                
        }
       if(this.DecideDirection()==0){
           distancemoved=(this.currentmaxspeed*r.nextDouble());
            this.x=this.x-distancemoved;
            
            if(this.x<0){
                double temp=this.currentmaxspeed*r.nextDouble();
                this.x+=distancemoved+temp;
                distancemoved=temp;
                
            }
            
       }
       
       else{
           if(r.nextBoolean()){
               distancemoved=(this.currentmaxspeed*r.nextDouble());
            this.x=this.x+distancemoved;
              
           }
           else{
              distancemoved=(this.currentmaxspeed*r.nextDouble());
            this.x=this.x-distancemoved;
           }
       }
        
       this.energy-=distancemoved*Constants.GEnergyForMoving;
       
        if(this.x<0){
           this.energy+=(Constants.GEnergyForMoving)*(-this.x);
            this.x=0;
        }
            
        if(this.x>Constants.ArenaMaxX){
            this.energy+=(Constants.GEnergyForMoving)*(this.x-Constants.ArenaMaxX);
            this.x=Constants.ArenaMaxX;
        }
            
        
        
        
    }

  /*  
    public void move() {
        double dist;
        if(r.nextBoolean()){
           if(Forest.gaverageX() < Constants.ArenaMaxX/2){
               dist = r.nextDouble() * this.currentmaxspeed;
                if ((this.x + dist) > Constants.ArenaMaxX) {
                    this.x -= dist;
                } else {
                    this.x += dist;
                }
           }
            else{
                dist = r.nextDouble() * this.currentmaxspeed;
                if ((this.x - dist) < 0) {
                    this.x += dist;
                } else {
                    this.x -= dist;
                }
            }
        }
        else
            if (r.nextDouble() >= 0.5) {
            dist = r.nextDouble() * this.currentmaxspeed ;
            if ((this.x + dist) > Constants.ArenaMaxX) {
                this.x -= dist;
            } else {
                this.x += dist;
            }
        } else {
            dist = r.nextDouble() * this.currentmaxspeed;
            if ((this.x - dist) < 0) {
                this.x += dist;
            } else {
                this.x -= dist;
            }
        }
    }*/
    public void sprint() {
    }
}
