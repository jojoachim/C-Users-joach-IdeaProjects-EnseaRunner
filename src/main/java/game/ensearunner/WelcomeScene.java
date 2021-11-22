package game.ensearunner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class WelcomeScene extends Scene {

    protected StaticThing picture;
    protected Button startBtn;
    protected Button startTestBtn;

    public WelcomeScene(Group root, int w, int h) {
        super(root, w, h);
        picture = new StaticThing("welcomePicture.png", 0, 0, 0, 0, 600, 400);

        startBtn = new Button("Start running");
        startBtn.setPrefSize(200, 50);
        startBtn.setTranslateX((w - startBtn.getPrefWidth())/2);
        startBtn.setTranslateY((h - startBtn.getPrefHeight())/2);

        startTestBtn = new Button("Learn how to play");
        startTestBtn.setPrefSize(200, 50);
        startTestBtn.setTranslateX((w - startTestBtn.getPrefWidth())/2);
        startTestBtn.setTranslateY(startBtn.getTranslateY() + startBtn.getPrefHeight() + 20);

        root.getChildren().add(picture.getSprite());
        root.getChildren().add(startBtn);
        root.getChildren().add(startTestBtn);

        startBtn.setOnAction( (event)-> {
            Camera.restartPos();
            Main.setCurrentSceneClassName("GameScene");
            Main.setNewScene();
            Main.startCurrentScene();
        });

        startTestBtn.setOnAction( (event)-> {
            Camera.restartPos();
            Main.setCurrentSceneClassName("TestScene");
            Main.setNewScene();
            Main.startCurrentScene();
        });
    }
}
