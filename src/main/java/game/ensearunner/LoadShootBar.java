package game.ensearunner;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LoadShootBar extends StaticThing { // Loading bar showing when the shoot is ready.

    protected Rectangle loading;
    protected static long requiredDeltaTime = 1600000000L;
    protected Hero hero;


    public LoadShootBar(String fileName, double x, double y, int x_vp, int y_vp, int w_vp, int h_vp, Hero hero, Group root) {
        super(fileName, x, y, x_vp, y_vp, w_vp, h_vp);
        this.hero = hero;
        loading = new Rectangle(x+5, y+5, w_vp-10, h_vp-10);
        loading.setFill(Color.GREEN);
        root.getChildren().add(loading);
    }

    public void update(long time) {
        long deltaTime = (time - hero.getLastShoot());

        double proportion = (double)deltaTime/(double)requiredDeltaTime;
        double width = 190*proportion;
        if(width > 190) {
            width = 190;
            hero.setShootReady(true);
        }
        loading.setWidth((int) width);
    }
}
