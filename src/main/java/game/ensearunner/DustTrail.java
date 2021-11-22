package game.ensearunner;

public class DustTrail extends AnimatedThing {

    protected final long framePerSecond;
    protected Hero hero;

    public DustTrail(String fileName, Hero hero) {
        super(fileName);
        framePerSecond = 20L;
        this.hero = hero;
    }

    public void update(long time) {
        super.update(time);
        this.getSprite().setX(hero.getX_scene() - 50);
        if(!hero.isDead()) {
            this.getSprite().setY(hero.getY_scene() + 55);
        }
        else {
            this.setInvisible();
            this.getSprite().setY(hero.getY_scene() + 30);
        }
    }
}
