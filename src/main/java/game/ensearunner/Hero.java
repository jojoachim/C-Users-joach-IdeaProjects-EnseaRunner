package game.ensearunner;

import constants.ensearunner.ModeConstants;
import javafx.scene.image.Image;

public class Hero extends AnimatedThing {
    private final String fileName;
    private String currentName; //Switch with red version to blink after a choc.

    private double x;
    private double y;

    private int lifePoints;
    private double invincibility;

    protected final double gravity = 230;
    protected final double vyRush = 280;
    protected double ay; protected double vy;
    protected double ax; protected double vx; // Pour quand on perd.

    protected long lastTime; //pour le update.


    protected long lastShoot;

    protected boolean shootReady;

    /* CONSTRUCTEURS */
    public Hero(int x, int y, String fileName) {
        super(fileName);
        this.fileName = fileName + ".png";
        currentName = fileName;

        this.x = x;
        this.y = y;

        lifePoints = 3;

        ax = 0; vx = 50;
        ay = 0.0; vy = 0.0;

        lastTime = 0;
        lastShoot = 0;

        shootReady = (Main.getCurrentSceneClassName().equals("TestScene"));
    }


    public void update(long time) {
        super.update(time);

        if(time - lastTime > 16000000L) {
            if((time-lastTime)/16000000L > 5) { // Prevents a huge difference due to the beginning.
                lastTime = time;
                lastShoot = time;
            }

            // Decrease invincibility :
            if(isInvincible()) {
                invincibility -= (time - lastTime);

                /* BLINKING DURING INVINCIBILITY */
                if(currentName.equals("heros.png")) { // VERIFIER LE .PNG
                    currentName = "herosHurt.png";
                }
                else {
                    currentName = "heros.png";
                }
                sprite.setImage(new Image("file:C:/Users/joach/IdeaProjects/EnseaRunner/src/img/" + currentName));
            }

            if(invincibility < 0) {
                invincibility = 0;
                sprite.setImage(new Image("file:C:/Users/joach/IdeaProjects/EnseaRunner/src/img/" + fileName));
            }

            /* X */
            vx += ax*(time-lastTime)/160000000L;
            x += vx*(time-lastTime)/160000000L;

            if(isDead()) {
                if(mode != ModeConstants.DYING) { // Pour la première fois où on y rente.
                    if(mode != ModeConstants.JUMPING_UP && mode != ModeConstants.JUMPING_DOWN) {
                        ay = gravity;
                        vy -= 200;
                    }
                    mode = ModeConstants.DYING;
                    ax = -2;
                }

                vy += ay*(double)(time - lastTime)/320000000L;
                y += vy*(double)(time - lastTime)/320000000L;

                if(vx < 0) {
                    vx = 0;
                    if(Record.newBestScore()) {
                        Record.writeNewBestScore();
                    }
                    ((GameScene)Main.getCurrentScene()).stop();
                    ((GameScene)Main.getCurrentScene()).setButtonsVisible();
                }
            }

            /* Y */
            if(mode == ModeConstants.JUMPING_UP || mode == ModeConstants.JUMPING_DOWN || mode == ModeConstants.DYING) {
                vy += ay * (double) (time - lastTime) / 320000000L;
                if(Math.abs(y - 435) < 0.01) { // Jump has just started
                    this.MovePos_scene(0, vy * (double) (time - lastTime) / 320000000L);
                }
                y += vy * (double) (time - lastTime) / 320000000L;

                if(vy > -5 && mode != ModeConstants.DYING) {
                    mode = ModeConstants.JUMPING_DOWN;
                }
            }

            if(!isDead()) {
                if (y > 435.1) {
                    ay = 0;
                    vy = 0;
                    y = 435;
                    if (Main.getCurrentScene().getClass() == GameScene.class)
                        ((GameScene) Main.getCurrentScene()).setDustTrailVisible(true);
                    else if (Main.getCurrentScene().getClass() == TestScene.class)
                        ((TestScene) Main.getCurrentScene()).setDustTrailVisible(true);

                    if (mode != ModeConstants.DYING)
                        mode = ModeConstants.RUNNING;
                }
            }
            else {
                if (y > 470.1) {
                    ay = 0;
                    vy = 0;
                    y = 470;
                    if (Main.getCurrentScene().getClass() == GameScene.class)
                        ((GameScene) Main.getCurrentScene()).setDustTrailVisible(true);
                    else if (Main.getCurrentScene().getClass() == TestScene.class)
                        ((TestScene) Main.getCurrentScene()).setDustTrailVisible(true);

                    if (mode != ModeConstants.DYING)
                        mode = ModeConstants.RUNNING;
                }
            }

            lastTime = time;
        }
    }

    public void jump() {
        if(mode == ModeConstants.RUNNING) {
            if(Main.getCurrentScene().getClass() == GameScene.class) {
                ((GameScene) Main.getCurrentScene()).setDustTrailVisible(false);
            }
            else if(Main.getCurrentScene().getClass() == TestScene.class) {
                ((TestScene) Main.getCurrentScene()).setDustTrailVisible(false);
            }
            Audio.playSound("C:/Users/joach/IdeaProjects/EnseaRunner/src/snd/jump.wav");
            mode = ModeConstants.JUMPING_UP;
            ay = gravity;
            vy = -vyRush;
        }
    }

    public void shoot() {
        lastShoot = lastTime;
        if (mode == ModeConstants.RUNNING) {
            mode = ModeConstants.SHOOTING;
        } else if (mode == ModeConstants.JUMPING_UP || mode == ModeConstants.JUMPING_DOWN) {
            mode = ModeConstants.JUMPING_SHOOTING;
        }
    }

    public void takeDamages(int d) {
        lifePoints -= d;
        if(lifePoints < 0) {
            lifePoints = 0;
        }
    }

    public void setShootReady(boolean b) {
        shootReady = b;
    }
    public boolean getShootReady() {
        return shootReady;
    }

    public boolean isInvincible() {
        return (invincibility > 0);
    }

    public boolean isDead() {
        return lifePoints <= 0;
    }

    /* GETTERS */
    public double getX() { return x; }
    public double getY() { return y; }
    public int getLifePoints() { return lifePoints; }

    public long getLastShoot() { return lastShoot; }

    /* SETTERS */
    public void setInvincibility() {
        invincibility = 1000000000L;
    }
}

