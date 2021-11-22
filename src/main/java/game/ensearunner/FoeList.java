package game.ensearunner;

import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Random;

public class FoeList {

    private final ArrayList<Foe> foeList;

    public FoeList(Group root) {
        Random random = new Random();
        foeList = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            if(random.nextInt(2) == 0)
                foeList.add(new Foe(random.nextInt(200) + i*500 + 2000, 430, "foe")); // 10 ennemis, un seul sur chaque intervalle de 200 à partir de 900
            else
                foeList.add(new FlyingFoe(random.nextInt(200) + i*400 + 2000)); // 10 ennemis, un seul sur chaque intervalle de 200 à partir de 900

            root.getChildren().add(foeList.get(i).getSprite());
        }
    }

    public ArrayList<Foe> getFoeList() { return foeList; }

}
