package game.ensearunner;

import constants.ensearunner.ModeConstants;
import constants.ensearunner.RectangleConstants;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {
    protected double x_scene;
    protected double y_scene;
    protected ImageView sprite;
    protected int mode;

    protected Rectangle2D hitBox;

    protected int currentFrameIndex;
    protected final long framePerSecond;
    protected int maxFrameIndex;
    protected int frameOffset;

    protected long lastTime = 0;

    protected String type;

    public AnimatedThing(String fileName) {

        type = fileName;


        Image spriteSheet = new Image("file:src/img/" + fileName + ".png");
        sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(15,0,75,100));

        hitBox = new Rectangle2D(0, 0, 0, 0);

        currentFrameIndex = 0;
        framePerSecond = 13L;
        maxFrameIndex = 5;
        frameOffset = 84;

        switch (fileName) {
            case "heros" -> mode = ModeConstants.RUNNING;
            case "foe" -> mode = ModeConstants.WAITING;
            case "flyingFoe" -> mode = ModeConstants.FLYING;
        }
    }

    public void update(long time) {
        if(time - lastTime > 1000000000L / framePerSecond) {

            switch (type) {
                case "heros":
                    if (mode == ModeConstants.RUNNING) {
                        currentFrameIndex++;
                        if (currentFrameIndex >= RectangleConstants.heroRects.get(0).size()) {
                            currentFrameIndex = 0;
                        }
                        sprite.setViewport(RectangleConstants.heroRects.get(0).get(currentFrameIndex));
                        hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.heroRects.get(0).get(currentFrameIndex).getWidth(), RectangleConstants.heroRects.get(0).get(currentFrameIndex).getHeight());
                    } else if (mode == ModeConstants.JUMPING_UP) {
                        sprite.setViewport(RectangleConstants.heroRects.get(1).get(0));
                        hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.heroRects.get(1).get(0).getWidth(), RectangleConstants.heroRects.get(1).get(0).getHeight());
                    } else if (mode == ModeConstants.JUMPING_DOWN) {
                        sprite.setViewport(RectangleConstants.heroRects.get(1).get(1));
                        hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.heroRects.get(1).get(1).getWidth(), RectangleConstants.heroRects.get(1).get(1).getHeight());
                    } else if (mode == ModeConstants.DYING) {
                        sprite.setViewport(RectangleConstants.heroRects.get(2).get(0));
                    } else if (mode == ModeConstants.SHOOTING) {
                        currentFrameIndex++;
                        if (currentFrameIndex >= RectangleConstants.heroRects.get(3).size()) {
                            currentFrameIndex = -1;
                            mode = ModeConstants.RUNNING;
                        } else {
                            sprite.setViewport(RectangleConstants.heroRects.get(3).get(currentFrameIndex));
                            hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.heroRects.get(3).get(currentFrameIndex).getWidth(), RectangleConstants.heroRects.get(3).get(currentFrameIndex).getHeight());
                        }
                    } else if (mode == ModeConstants.JUMPING_SHOOTING) {
                        currentFrameIndex++;
                        if (currentFrameIndex >= RectangleConstants.heroRects.get(4).size()) {
                            currentFrameIndex = -1;
                            mode = ModeConstants.JUMPING_UP;
                        } else {
                            sprite.setViewport(RectangleConstants.heroRects.get(4).get(currentFrameIndex));
                            hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.heroRects.get(4).get(currentFrameIndex).getWidth(), RectangleConstants.heroRects.get(3).get(currentFrameIndex).getHeight());
                        }
                    }
                    break;
                case "foe":
                    if (mode == ModeConstants.STANDING) {
                        currentFrameIndex++;
                        if (currentFrameIndex >= RectangleConstants.foeRects.get(1).size()) {
                            currentFrameIndex = 0;
                        }
                        sprite.setViewport(RectangleConstants.foeRects.get(1).get(currentFrameIndex));
                        hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.foeRects.get(1).get(currentFrameIndex).getWidth(), RectangleConstants.foeRects.get(1).get(currentFrameIndex).getHeight());
                    } else if (mode == ModeConstants.RISING) {
                        currentFrameIndex++; //
                        if (currentFrameIndex >= RectangleConstants.foeRects.get(0).size()) {
                            currentFrameIndex = -1;
                            mode = ModeConstants.STANDING;
                        } else {
                            sprite.setViewport(RectangleConstants.foeRects.get(0).get(currentFrameIndex));
                        }
                    } else if (mode == ModeConstants.DYING) {
                        if (currentFrameIndex != -1) {
                            currentFrameIndex++;
                            if (currentFrameIndex >= RectangleConstants.foeRects.get(2).size()) {
                                currentFrameIndex = -1;
                            }
                            if (currentFrameIndex != -1) {
                                sprite.setViewport(RectangleConstants.foeRects.get(2).get(currentFrameIndex));
                                hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.foeRects.get(2).get(currentFrameIndex).getWidth(), RectangleConstants.foeRects.get(2).get(currentFrameIndex).getHeight());
                            }
                        }
                    }
                    break;
                case "flyingFoe":
                    //System.out.println("ChangeFrame");
                    if (mode == ModeConstants.FLYING) {
                        currentFrameIndex++;
                        if (currentFrameIndex >= RectangleConstants.flyingFoeRects.get(0).size()) {
                            currentFrameIndex = 0;
                        }
                        sprite.setViewport(RectangleConstants.flyingFoeRects.get(0).get(currentFrameIndex));
                        hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.flyingFoeRects.get(0).get(currentFrameIndex).getWidth(), RectangleConstants.flyingFoeRects.get(0).get(currentFrameIndex).getHeight());
                    } else if (mode == ModeConstants.DYING) {
                        sprite.setViewport(RectangleConstants.flyingFoeRects.get(1).get(0));
                        //hitBox = new Rectangle2D(x_scene, y_scene, RectangleConstants.flyingFoeRects.get(1).get(0).getWidth(), RectangleConstants.flyingFoeRects.get(1).get(0).getHeight());
                    }
                    break;
                case "dustTrail":
                    currentFrameIndex++;
                    if (currentFrameIndex >= RectangleConstants.dustTrailRects.size()) {
                        currentFrameIndex = 0;
                    }
                    sprite.setViewport(RectangleConstants.dustTrailRects.get(currentFrameIndex));
                    break;
            }

            lastTime = time;
        }
        sprite.setX(x_scene);
        sprite.setY(y_scene);
    }

    // Getters :
    public ImageView getSprite() { return sprite; }
    public double getX_scene() { return x_scene; }
    public double getY_scene() { return y_scene; }

    // Setters :
    public void setX_scene(int x) {
        x_scene = x;
        //sprite.setX(x_scene);
    }
    public void setY_scene(int y) {
        y_scene = y;
        //sprite.setY(y_scene);
    }
    public void setSpritePos(int x, int y) {
        x_scene = x;
        y_scene = y;

        //sprite.setX(x);
        //sprite.setY(y);
    }
    public void MovePos_scene(double x, double y) {
        x_scene += (int)x;
        y_scene -= (int)y;
    }

    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public boolean isAlive() { return mode != ModeConstants.DYING; }

    public void setInvisible() {
        this.getSprite().setVisible(false);
    }

}
