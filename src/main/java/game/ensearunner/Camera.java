package game.ensearunner;

import constants.ensearunner.ModeConstants;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Camera {

    protected static double x = 300;
    protected double y;

    protected Text record;

    protected Hero hero;
    protected double ax; protected double vx;
    protected double ay; protected double vy;

    protected ArrayList<Foe> foeList;
    protected ShootList shootList;

    protected double kx; protected double fx; protected double mx;
    protected double ky; protected double fy; protected double my;

    private long lastTime = 0;

    public Camera(int y, Hero hero, ArrayList<Foe> foeList, ShootList shootList) {
        this.y = y;
        this.hero = hero;

        record = new Text(String.valueOf(x));

        this.hero.setX_scene((int)hero.getX() - (int)x);
        this.hero.setY_scene((int)hero.getY() - y);

        this.foeList = foeList;

        this.shootList = shootList;

        for(Foe f : foeList) {
            f.setX_scene((int)f.getX() - (int)x);
            f.setY_scene((int)f.getY() - y);
        }

        kx = 2.0; fx = 1.2; mx = 1.0;
        ky = 20.0; fy = 0.5; my = 1.0;

        ax = -kx/mx*(hero.getX_scene() - 100) + fx/mx*vx; vx = 0.0;
        ay = 0; vy = 0;
    }

    public String toString() {
        return x + ", " + y;
    }

    public void update(long time) {
        record = new Text(String.valueOf(x));
        if(time - lastTime > 16000000L) {
            if((time-lastTime)/16000000L < 10) {

                //* HERO IN SCENE */
                ax = -kx/mx*(hero.getX_scene() - 100) - fx/mx*vx;
                vx += ax * (double)(time - lastTime) / 320000000L;

                if(!hero.isDead()) {
                    ay = +ky / my * (hero.getY_scene() - 235) - fy / my * vy;
                }
                else {
                    ay = +ky / my * (hero.getY_scene() - 280) - fy / my * vy;
                }
                vy += ay * (double)(time - lastTime) / 320000000L;

                hero.MovePos_scene((vx * (double)(time - lastTime) / 320000000L), vy * (double)(time - lastTime) / 320000000L);

                /* FOES IN SCENE */
                /* EARTHLY */
                if(foeList.size() != 0) { //Sûrement inutile
                    for (Foe f : foeList) {
                        f.setSpritePos((int) (f.getX() - x), (int) (f.getY() + y + (95 - f.getSprite().getViewport().getHeight())));

                        if (f.getX_scene() < 800 && f.mode == ModeConstants.WAITING) {
                            f.currentFrameIndex = 0;
                            f.mode = ModeConstants.RISING;
                        }

                        /* DETECTION DES COLLISIONS */
                        /* WITH HERO */
                        if (!hero.isInvincible() && f.isAlive()) {
                            if (f.getHitBox().intersects(hero.getHitBox())) {
                                hero.takeDamages(1);
                                hero.setInvincibility();
                                System.out.println(hero.getLifePoints());
                            }
                        }

                        for (Shoot s : shootList.getShootList()) {
                            if (s.getHitBox().intersects(f.getHitBox())) {
                                f.die();
                            }
                        }
                    }
                }

                /* SHOOTS IN SCENE */
                shootList.update(time);

                /* CAMERA */
                x = hero.getX() - hero.getX_scene();
                y = -hero.getY() + hero.getY_scene();

                if(Math.abs(hero.getX_scene() - 100) < 1.1) {
                    hero.setX_scene(100);
                } // Arrêt total du ressort.
                if(Math.abs(ay) < 0.5) {
                    if(!hero.isDead())
                        hero.setY_scene(235);
                    else {
                        hero.setY_scene(280);
                        System.out.println("ok");
                    }
                }
            }
            lastTime = time;
        }
    }

    /* GETTERS */
    public static double getX() { return x; }
    public double getY() { return y; }

    /* SETTERS */
    public static void restartPos() { x = 300; }
}
