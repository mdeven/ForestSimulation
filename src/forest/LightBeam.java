/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forest;

/**
 *
 * @author abc
 */
public class LightBeam extends Object {

    public LightBeam(double x, double energy) {
        this.x = x;
        this.energy = energy;
        this.alive = true;
        this.y = 0;
    }

    public LightBeam(double x, double y, double energy) {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.alive = true;
    }

    public void tick() {
        this.y += 2;
        //this.energy-=0.01;
        if (y >= Constants.ArenaMaxY) {
            this.alive = false;
            Forest.arrayOfDeadBeams.add(this);
        }
        if (this.energy <= 0) {
            this.alive = false;
            Forest.arrayOfDeadBeams.add(this);
        }
    }
}
