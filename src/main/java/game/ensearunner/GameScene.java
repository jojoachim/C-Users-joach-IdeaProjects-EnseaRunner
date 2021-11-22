package game.ensearunner;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameScene extends Scene {
    protected Camera camera;
    protected int numberOfLives;

    /* ANIMATED THINGS */
    protected Hero hero;
    protected AnimatedThing dustTrail;
    //protected ArrayList<Foe> foeList;
    protected FoeList foeList;
    protected ShootList shootList;

    /* STATIC THINGS */
    protected StaticThing background1;
    protected StaticThing background2;
    protected StaticThing heart1;
    protected StaticThing heart2;
    protected StaticThing heart3;
    protected StaticThing shootIsReady;
    protected LoadShootBar loadShootBar;
    protected Record record;

    /* BUTTONS */
    protected Button retryBtn;
    protected Button backHomeBtn;

    /* TIMER */
    private final AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            hero.update(time);
            shootList.update(time);
            dustTrail.update(time);
            camera.update(time);
            loadShootBar.update(time);
            update(time);
            for(Foe f : foeList.getFoeList()) {
                f.update(time);
            }
        }
    };

    public GameScene(Group root, int w, int h) { //pane à la place de gorup ?
        super(root , w, h);
        numberOfLives = 3;

        background1 = new StaticThing("desert2.png", 0, 0, 0, 0, 800, 620);
        background2 = new StaticThing("desert2.png", 800, 0, 0, 0, 800, 620);
        root.getChildren().add(background1.getSprite());
        root.getChildren().add(background2.getSprite());
        heart1 = new StaticThing("heart.png", 10, 10, 0, 0, 53, 53);
        heart2 = new StaticThing("heart.png", 10 + 53 + 5, 10, 0, 0, 53, 53);
        heart3 = new StaticThing("heart.png", 10 + 2*53 + 5*2, 10, 0, 0, 53, 53);
        root.getChildren().add(heart1.getSprite());
        root.getChildren().add(heart2.getSprite());
        root.getChildren().add(heart3.getSprite());
        this.foeList = new FoeList(root);
        hero = new Hero(900, 435, "heros");
        root.getChildren().add(hero.getSprite());
        dustTrail = new DustTrail("dustTrail", hero);
        root.getChildren().add(dustTrail.getSprite());
        shootList = new ShootList(root, hero);
        loadShootBar = new LoadShootBar("progressBarBorder.png", 10, 70, 0, 0, 200, 50, hero, root);
        root.getChildren().add(loadShootBar.getSprite());

        /* BUTTONS */
        retryBtn = new Button("Retry");
        retryBtn.setPrefSize(100, 30);
        retryBtn.setTranslateX((w-retryBtn.getPrefWidth())/2);
        retryBtn.setTranslateY((h-retryBtn.getPrefHeight())/2);
        retryBtn.setVisible(false);
        backHomeBtn = new Button("Back home");
        backHomeBtn.setPrefSize(100, 30);
        backHomeBtn.setTranslateX(retryBtn.getTranslateX());
        backHomeBtn.setTranslateY(retryBtn.getTranslateY() + retryBtn.getPrefHeight() + 10);
        backHomeBtn.setVisible(false);
        root.getChildren().add(retryBtn);
        root.getChildren().add(backHomeBtn);

        camera = new Camera(200, hero, foeList.getFoeList(), shootList); //Hero peut être mis dans shootList.
        record = new Record(root);

        /* AUDIO */
        Audio.playSound("src/snd/inGame.wav");

        /* EVENTS */
        this.setOnMouseClicked( (event)-> {
            hero.jump();
        });
        this.setOnKeyPressed( (event)-> {
            if (hero.getShootReady() && (event.getCode().getCode() == KeyEvent.VK_SPACE || event.getCode().getCode() == KeyEvent.VK_ENTER)) {
                hero.shoot();
                shootList.addShoot(event.getCode().getCode());
                hero.setShootReady(false);
            }
        });
        retryBtn.setOnAction( e -> {
            ((GameScene)Main.getCurrentScene()).stop();
            Audio.stop();
            Camera.restartPos();
            Main.setCurrentSceneClassName("GameScene");
            Main.setNewScene();
            Main.startCurrentScene();
        });
        backHomeBtn.setOnAction( e -> {
            ((GameScene)Main.getCurrentScene()).stop();
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
    public void setButtonsVisible() {
        retryBtn.setVisible(true);
        backHomeBtn.setVisible(true);
    }
    public void setDustTrailVisible(boolean b) {
        this.dustTrail.getSprite().setVisible(b);
    }


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

        /* HEARTS */
        if(hero.getLifePoints() == 2) {
            heart3.getSprite().setViewport(new Rectangle2D(53, 0, 53, 53));
        }
        if(hero.getLifePoints() == 1) {
            heart2.getSprite().setViewport(new Rectangle2D(53, 0, 53, 53));
        }
        if(hero.getLifePoints() == 0) {
            heart1.getSprite().setViewport(new Rectangle2D(53, 0, 53, 53));
        }
        Record.update();
    }
}
