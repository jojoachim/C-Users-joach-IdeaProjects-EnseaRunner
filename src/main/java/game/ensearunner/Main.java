package game.ensearunner;

import constants.ensearunner.RectangleConstants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.invoke.TypeDescriptor;

public class Main extends Application {

    private static Stage stage;
    private static SceneHolder sceneHolder;
    private static String currentSceneClassName;

    public void start(Stage primaryStage){

        RectangleConstants.Init();

        currentSceneClassName = "WelcomeScene";
        sceneHolder = new SceneHolder();
        sceneHolder.setScene(currentSceneClassName);

        stage = primaryStage;
        stage.setTitle("ENSEA RUNNER");
        stage.setResizable(false);
        stage.setScene(sceneHolder.getScene());

        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        // write your code here
    }

    public static Scene getCurrentScene() {
        return stage.getScene();
    }
    public static String getCurrentSceneClassName() { return currentSceneClassName; }
    public static void setCurrentSceneClassName(String className) { currentSceneClassName = className; }

    public static void setNewScene() {
        sceneHolder.setScene(currentSceneClassName);
        stage.setScene(sceneHolder.getScene());
    }

    public static void startCurrentScene() {
        if(stage.getScene().getClass() == GameScene.class) {
            ((GameScene)stage.getScene()).start();
        }
        else if(stage.getScene().getClass() == TestScene.class) {
            ((TestScene)stage.getScene()).start();
        }
    }
}
