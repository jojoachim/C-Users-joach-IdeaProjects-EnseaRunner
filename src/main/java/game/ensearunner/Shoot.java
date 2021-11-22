package game.ensearunner;

import javafx.geometry.Rectangle2D;

public class Shoot extends AnimatedThing {
    protected double x; protected double vx; protected double ax;
    protected double y; protected double vy; protected double ay;

    protected Hero hero;

    protected long lastTime;

    public Shoot(String fileName, Hero hero) {
        super(fileName);
        this.hero = hero;
        this.getSprite().setViewport(new Rectangle2D(0, 40, 145, 35));

        this.x_scene = -100;this.y_scene = -100;
        vx = 0; ax = 0;
        vy = 0; ay = 0;

        lastTime = 0;
    }

    public void update(long time) {

        if(time - lastTime > 80000000L) {
            lastTime = time;
        }

        if(getY_scene() > 600 || getX_scene() > 1200) {
            this.setX_scene(-100);
            this.setY_scene(-100);
        }

        if((time - lastTime) > 16000000L && this.isActive()) {
            vy += (ay * (double) (time - lastTime)) / 320000000L;
            y += (vy * (double) (time - lastTime)) / 320000000L;
            x += vx * (double) (time - lastTime) / 160000000L;

            hitBox = new Rectangle2D(getX_scene(), getY_scene(), 140, 30);

            this.setX_scene(-((int)this.hero.getX() + 100) + (int) this.x);
            this.setY_scene(-((int)this.hero.getY() - 300) + (int) this.y);

            lastTime = time;
        }
        sprite.setX(x_scene);
        sprite.setY(y_scene);
    }

    public boolean isActive() {
        return (this.getY_scene() != -100);
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVx(int v) { vx = v; }
    public void setVy(int v) { vy = v; }
    public void setAy(int a) { ay = a; }
}
