package game.ensearunner;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ShootList {
    private ArrayList<Shoot> shootList;
    private Hero hero;

    public ShootList(Group root, Hero hero) {
        this.hero = hero;
        shootList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            shootList.add(new Shoot("weapons2", hero));
            root.getChildren().add(shootList.get(i).getSprite());
        }
    }

    public void addShoot(int keyCode) {
        if(hero.getShootReady()) {
            int cpt = 0;
            while (cpt < shootList.size() - 1 && shootList.get(cpt).getY_scene() != -100) {
                cpt++;
            }
            shootList.get(cpt).setX((int) hero.getX()+200);
            shootList.get(cpt).setY((int) hero.getY()-50);
            shootList.get(cpt).setX_scene((int) hero.getX_scene());
            shootList.get(cpt).setY_scene((int) hero.getY_scene());
            shootList.get(cpt).setVx(120);

            if(keyCode == KeyEvent.VK_SPACE) {
                shootList.get(cpt).setVy(-80);
            }
            else if(keyCode == KeyEvent.VK_ENTER) {
                shootList.get(cpt).setVy(-150);
            }
            shootList.get(cpt).setAy(80);
        }
    }

    public void update(long time) {
        for(Shoot s : shootList) {
            s.update(time);
        }
    }

    public ArrayList<Shoot> getShootList() { return shootList; }
}
