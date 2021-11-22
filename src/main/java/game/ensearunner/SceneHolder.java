package game.ensearunner;

import javafx.scene.Group;
import javafx.scene.Scene;

public class SceneHolder {
    //protected Group root;
    protected static Scene scene;

    public SceneHolder() {
        scene = new Scene(new Group(), 600, 400);
    }

    public Scene getScene() { return scene; }

    public void setScene(String className) {
        switch (className) {
            case "WelcomeScene" -> scene = new WelcomeScene(new Group(), 600, 400);
            case "GameScene" -> scene = new GameScene(new Group(), 600, 400);
            case "TestScene" -> scene = new TestScene(new Group(), 600, 400);
        }
    }

}
