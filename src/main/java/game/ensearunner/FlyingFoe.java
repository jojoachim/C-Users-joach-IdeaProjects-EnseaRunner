package game.ensearunner;

import constants.ensearunner.ModeConstants;

import java.util.Random;

public class FlyingFoe extends Foe{

    protected boolean isActivated;

    protected long lastTimeMove;
    protected long lastTimeAnim;

    public FlyingFoe(int x) {
        super(x, 300, "flyingFoe");
        isActivated = false;

        mode = ModeConstants.FLYING; // utile ?
    }

    public void update(long time) {
        super.update(time);
        if(isActivated) {
            if(time - lastTimeMove > 1600000000L) { lastTimeMove = time; }

            /* FRAMES */
            if(time-lastTimeAnim > 64000000L) {
                //Code dans animatedThing.
            }

            /* MOVE */
            if(time-lastTimeMove > 16000000L) {
                if(this.isAlive()) {
                    ay -= 1;
                    vy += ay * (time - lastTimeMove) / 320000000L;
                    y += vy * (time - lastTimeMove) / 320000000L;
                    lastTimeMove = time;
                }
                else {
                    y += 3;
                }
            }
        }
        else {
            if (this.x_scene < 700) {
                isActivated = true;

                Random random = new Random();
                ay = random.nextInt(10)*2+15;
            }
        }
    }
}
