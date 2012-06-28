package forest;

public abstract class Object {

    double x;
    double y;
    double energy;
    boolean alive = true;
    boolean isSelected = false;

    public abstract void tick();
}
