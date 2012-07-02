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
public class Lion extends Object {

    LionDNA dna;
    int age;
    long id;
    int nexthunt;
    static long nextLid = 1;
    static long nextgender = 1;
    boolean gender;
    double currentsize;
    boolean lookingformate;
    boolean lookingforfood;
    boolean flyingstate;
    double currentmaxspeed;
    static Random r = new Random();

    public Lion(double x) {

        if ((nextgender++ % 2) == 0) {
            this.gender = true;
        } else {
            this.gender = false;
        }

        this.dna = new LionDNA();
        this.alive = true;
        this.age = 0;
        this.nexthunt = Constants.restperiod;
        this.id = nextLid++;
        this.energy = Constants.initialLenergy;
        this.lookingformate = false;
        this.lookingforfood = false;
        this.flyingstate=false;
        this.x = x;
        this.y=Constants.InitialAltitude;
        this.currentmaxspeed = 0;
        this.isSelected=false;
        this.currentsize=Constants.initialLsizePerc*this.dna.size/100;

    }

    public Lion(Lion parentfemale, Lion parentmale) {


        if ((nextgender++ % 2) == 0) {
            this.gender = true;
        } else {
            this.gender = false;
        }

        this.dna = new LionDNA(parentfemale, parentmale);
        this.alive = true;
        this.age = 0;
        this.nexthunt = Constants.restperiod;
        this.id = nextLid++;
        this.energy = Constants.initialLenergy;
        this.lookingformate = false;
        this.lookingforfood = false;
        this.flyingstate=true;
        this.x = parentfemale.x - 10 + r.nextDouble() * 20;
        this.y= parentfemale.y;
        this.currentmaxspeed = 0;
        this.isSelected=false;
        this.currentsize=Constants.initialLsizePerc*this.dna.size/100;
    }

    @Override
    public void tick() {
        if (this != null) {
            this.nexthunt--;
            this.age++;
            this.setspeed();
            
            if(this.flyingstate)
                this.flyingstate=false;
            else
                this.flyingstate=true;
            
            if (this.age > this.dna.maxage) {
                this.kill();
            }

            if (this.energy < Constants.ArenaMinEnergy) {
                this.kill();
            }
            
            
             if (this.energy > this.dna.energytoreproduce) {
                    this.lookingformate = true;
                } else if (this.energy < this.dna.energytoreproduce) {
                    this.lookingformate = false;
                }
             
              if (this.nexthunt <= 0) {
                    this.lookingforfood = true;
                } else if (this.nexthunt != 0) {
                    this.lookingforfood = false;
                }
             
            this.grow();
            this.move();
            
            if (this.alive == true && this.age != this.dna.maxage && this.lookingforfood) {
                this.hunt();
            }
            
            if (this.gender == false && this.lookingformate == true) {
                for (Lion l : Forest.arrayOfAliveLions) {
                    if (l != null) {
                        if (l.gender == true && l.lookingformate == true) {
                            //System.out.println("YUP");
                            Forest.arrayOfReproducingLionsF.add(this);
                            Forest.arrayOfReproducingLionsM.add(l);
                            this.energy -= this.dna.energytoreproduce;
                            l.energy -= l.dna.energytoreproduce;
                            if (l.energy < l.dna.energytoreproduce) {
                                l.lookingformate = false;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public int reproduce(Lion l) {
        //System.out.println("OFFSPRING "+this.energy);
        Forest.arrayOfLionsToBeAdded.add(new Lion(this, l));
        return 1;
    }

   public void hunt() {
        //System.out.println("safsa  " + this.x);
        if (r.nextDouble() < this.dna.preyefficiency / 100.0){
            for (Giraffe g : Forest.arrayOfAliveGiraffes) {
                if (g != null && g.alive) {
                    double vision;
                    if(g.dna.camouflage.fromFemaleParent && g.dna.camouflage.fromMaleParent)
                        vision=this.dna.camouflagevision;
                    else
                        vision=this.dna.maxvision;
                                                                        
                    if (Math.sqrt((g.x - this.x)*(g.x - this.x) + (this.y-g.currentheight)*(this.y-g.currentheight)) < vision) {
                        //System.out.println("vision  " + this.x);
                        if (g.x > this.x){
                        
                            if(Math.sqrt((g.x+g.currentmaxspeed - this.x)*(g.x+g.currentmaxspeed - this.x) + (this.y-g.currentheight)*(this.y-g.currentheight))< this.currentmaxspeed){
                                //System.out.println("hunt  " + this.x+"  "+this.id);
                                this.energy += g.energy;
                                this.nexthunt = Constants.restperiod;
                                this.y=0;
                                this.x += (g.currentmaxspeed + g.x - this.x);
                                g.x+= g.currentmaxspeed;
                                g.kill();
                                break;
                                
                            }
                        }   
                    
                        if (g.x < this.x) {
                            
                            if(Math.sqrt((g.x-g.currentmaxspeed - this.x)*(g.x-g.currentmaxspeed - this.x) + (this.y-g.currentheight)*(this.y-g.currentheight))< this.currentmaxspeed){
                                System.out.println("hunt  " + this.x+"  "+this.id);
                                this.energy += g.energy;
                                this.nexthunt = Constants.restperiod;
                                this.x += (g.x - g.currentmaxspeed - this.x);
                                g.x-= g.currentmaxspeed;
                                g.kill();
                                break;
                            } 
                        }
                    }
                }
            }
        }
    }   
    
    
        

    public void kill() {
        this.alive = false;
        this.energy = 0;
        Forest.arrayOfDeadLions.add(this);
    }

    public boolean ContainsPoint(double x, double y) {
        
        double circlex=this.x-(0.1*this.currentsize);
        double circley=(Constants.ArenaMaxY-this.y)-(0.6*this.currentsize);
        double circleradius=0.15*this.currentsize;
        
        if((circlex-x)*(circlex-x)+(circley-y)*(circley-y)<=circleradius*circleradius)
            return true;
        
        double ellipsecentrex=this.x+0.45*this.currentsize;
        double ellipsecentrey=(Constants.ArenaMaxY-this.y)-0.45*this.currentsize;
        double ellipsemajor=0.55*this.currentsize;
        double ellipseminor=0.25*this.currentsize;
        
        if(((ellipsecentrex-x)*(ellipsecentrex-x)/(ellipsemajor*ellipsemajor))+((ellipsecentrey-y)*(ellipsecentrey-y)/(ellipseminor*ellipseminor))<=1)
                return true;
        
        
        return false;
    }

    
    
    public void setspeed() {
        double matureage = 0.2 * this.dna.maxage;
        double oldage = 0.7 * this.dna.maxage;
        if (this.age < matureage) {
            this.currentmaxspeed = Math.sin((age / matureage) * 3.14159/2) * this.dna.maxspeed;
        } else if (this.age > oldage) {
            this.currentmaxspeed = Math.cos(((age - oldage) / (this.dna.maxage - oldage)) * 3.14159/2) * this.dna.maxspeed;
        }
        //System.out.println(age+" "+currentmaxspeed);
    }
    
    
    
    public void move(){
        double dist;
        //For X COORDINATE.
        if(r.nextBoolean()){
            int girafferight=0,giraffeleft=0;
            for(Giraffe g: Forest.arrayOfAliveGiraffes){
                double vision;
                if(g!=null){
                    if( g.dna.camouflage.fromFemaleParent && g.dna.camouflage.fromMaleParent)
                        vision=this.dna.camouflagevision;
                    else   
                        vision=this.dna.maxvision;
             
                    if( g.x<this.x && Math.abs(this.x-g.x)<Math.sqrt( vision*vision - (this.y-g.currentheight)*(this.y-g.currentheight)))
                        giraffeleft++;
                    if( g.x>this.x && Math.abs(this.x-g.x)<Math.sqrt( vision*vision - (this.y-g.currentheight)*(this.y-g.currentheight)))
                        girafferight++;
                }
            }
           if(girafferight>giraffeleft){
               dist = r.nextDouble() * this.currentmaxspeed/Math.sqrt(2);
                if ((this.x + dist) > Constants.ArenaMaxX) {
                    this.x -= dist;
                } 
                else {
                    this.x += dist;
                }
           }
            else{
                dist = r.nextDouble() * this.currentmaxspeed/Math.sqrt(2);
                if ((this.x - dist) < 0) {
                    this.x += dist;
                } else {
                    this.x -= dist;
                }
            }
        }
        else{
            if (r.nextBoolean()) {
            dist = r.nextDouble() * this.currentmaxspeed/Math.sqrt(2) ;
            if ((this.x + dist) > Constants.ArenaMaxX) {
                this.x -= dist;
            } else {
                this.x += dist;
            }
        } 
            else {
                dist = r.nextDouble() * this.currentmaxspeed/Math.sqrt(2);
                if ((this.x - dist) < 0) {
                    this.x += dist;
                } 
                else {
                    this.x -= dist;
                }
            }
        }
        
        //For Y COORDINATE
        if(r.nextBoolean()){
           if(Forest.laverageY() < (Constants.upperAltitudeLimit-Constants.lowerAltitudeLimit)/2){
               dist = r.nextDouble() * this.currentmaxspeed/2;
                if ((this.y + dist) > Constants.upperAltitudeLimit) {
                    this.y -= dist;
                } else {
                    this.y += dist;
                }
           }
            else{
                dist = r.nextDouble() * this.currentmaxspeed/2;
                if ((this.y - dist) < Constants.lowerAltitudeLimit) {
                    this.y += dist;
                } else {
                    this.y -= dist;
                }
            }
        }
        else{
            if (r.nextDouble() >= 0.5) {
            dist = r.nextDouble() * this.currentmaxspeed/2 ;
            if ((this.y + dist) > Constants.ArenaMaxY) {
                this.y -= dist;
            } else {
                this.y += dist;
            }
        } 
            else {
                dist = r.nextDouble() * this.currentmaxspeed/2;
                if ((this.y - dist) < 0) {
                    this.y += dist;
                }    
                else {
                    this.y -= dist;
                }
            }
        }
    }
    
    
    public void grow() {
        double sizegrown = Constants.parameterforLiongrowth * (this.dna.size - this.currentsize) / this.dna.maxage;
        this.currentsize += sizegrown;
        //this.energy -= Constants.genergyperunitheight;
    }
    
}
