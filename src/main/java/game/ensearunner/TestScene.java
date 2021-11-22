package game.ensearunner;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TestScene extends Scene {
    protected Camera camera;

    /* ANIMATED THINGS */
    protected Hero hero;
    protected AnimatedThing dustTrail;
    protected ShootList shootList;
    protected ArrayList<Foe> foeList;

    /* STATIC THINGS */
    protected StaticThing background1;
    protected StaticThing background2;

    /* RETRY BUTTON */
    protected Button exitBtn;

    /* TIMER */
    private final AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            hero.update(time);
            shootList.update(time);
            dustTrail.update(time);
            camera.update(time);
            update(time);
        }
    };

    public TestScene(Group root, int w, int h) {
        super(root , w, h);

        background1 = new StaticThing("desert2.png", 0, 0, 0, 0, 800, 620);
        background2 = new StaticThing("desert2.png", 800, 0, 0, 0, 800, 620);
        root.getChildren().add(background1.getSprite());
        root.getChildren().add(background2.getSprite());
        StaticThing clickRule = new StaticThing("clickRule.png", 10, 10, 0, 0, 300, 43);
        StaticThing spaceRule = new StaticThing("spaceRule.png", 10, 63, 0, 0, 300, 62);
        StaticThing enterRule = new StaticThing("enterRule.png", 10, 135, 0, 0, 300, 43);
        root.getChildren().add(clickRule.getSprite());
        root.getChildren().add(spaceRule.getSprite());
        root.getChildren().add(enterRule.getSprite());
        exitBtn = new Button("Exit");
        exitBtn.setPrefSize(100, 30);
        exitBtn.setTranslateX(w - exitBtn.getPrefWidth() - 10);
        exitBtn.setTranslateY(10);
        exitBtn.setFocusTraversable(false);
        root.getChildren().add(exitBtn);

        hero = new Hero(900, 435, "heros");
        root.getChildren().add(hero.getSprite());
        dustTrail = new DustTrail("dustTrail", hero);
        root.getChildren().add(dustTrail.getSprite());
        shootList = new ShootList(root, hero);


        /* CAMERA */
        foeList = new ArrayList<>();
        camera = new Camera(200, hero, foeList, shootList);

        /* AUDIO */
        Audio.stop();
        Audio.playSound("src/snd/inGame.wav");

        /* EVENTS */
        this.setOnMouseClicked( (event)-> {
            System.out.println("Jump");
            hero.jump();
        });
        this.setOnKeyPressed( (event)-> {
            System.out.println(hero.getShootReady());
            if (hero.getShootReady() && (event.getCode().getCode() == KeyEvent.VK_SPACE || event.getCode().getCode() == KeyEvent.VK_ENTER)) {
                hero.shoot();
                shootList.addShoot(event.getCode().getCode());
            }
        });
        exitBtn.setOnAction( e -> {
            ((TestScene)Main.getCurrentScene()).stop();
            Audio.stop();
            Main.setCurrentSceneClassName("WelcomeScene");
            Main.setNewScene();
        });
    }

    /* TIMER */
    public void start() {
        timer.start();
    }
    public void stop() {
        timer.stop();
    }

    /* VISIBLE THINGS */
    public void setDustTrailVisible(boolean b) {
        this.dustTrail.getSprite().setVisible(b);
    }

    /* UPDATE */
    public void update(long time) {

        /* BACKGROUND */
        background1.SetSpritePos((int)background1.getX()-(int)Camera.getX(), (int)background1.getY()+(int)camera.getY());
        background2.SetSpritePos((int)background2.getX()-(int)Camera.getX(), (int)background2.getY()+(int)camera.getY());

        if(background1.getX()-(int)Camera.getX() < -800) {
            background1.Move(1600, 0);
        }
        if(background2.getX()-(int)Camera.getX() < -800) {
            background2.Move(1600, 0);
        }

    }
}
