package game.ensearunner;

import constants.ensearunner.ModeConstants;

public class Foe extends AnimatedThing {

    protected String fileName;
    protected final double x; protected double y;
    protected double vx; protected double vy;
    protected double ax; protected double ay;

    public Foe(int x, int y, String fileName) {
        super(fileName);
        this.fileName = fileName;
        this.x = x; this.y = y;
        this.vx = 0; this.vy = 0;
        this.ax = 0; this.ay = 0;
    }

    public void die() {
        mode = ModeConstants.DYING;
    }

    /* GETTERS */
    public double getX() { return x; }
    public double getY() { return y; }
}
