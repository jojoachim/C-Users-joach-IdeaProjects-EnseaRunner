package constants.ensearunner;

import javafx.geometry.Rectangle2D;

import java.awt.*;
import java.util.ArrayList;

public class RectangleConstants {

    public static ArrayList<ArrayList<Rectangle2D>> heroRects = new ArrayList<>();
    public static ArrayList<ArrayList<Rectangle2D>> foeRects = new ArrayList<>();
    public static ArrayList<ArrayList<Rectangle2D>> flyingFoeRects = new ArrayList<>();
    public static ArrayList<Rectangle2D> dustTrailRects = new ArrayList<>();

    public RectangleConstants() {}

    public static void Init() {

        /* HERO */
        ArrayList<Rectangle2D> heroRunning = new ArrayList<>();
        ArrayList<Rectangle2D> heroJumping = new ArrayList<>();
        ArrayList<Rectangle2D> heroDying = new ArrayList<>();
        ArrayList<Rectangle2D> heroShooting = new ArrayList<>();
        ArrayList<Rectangle2D> heroJumpingShooting = new ArrayList<>();
        heroRunning.add(new Rectangle2D(22, 14, 53, 84));
        heroRunning.add(new Rectangle2D(96, 4, 64, 94));
        heroRunning.add(new Rectangle2D(175, 16, 75, 82));
        heroRunning.add(new Rectangle2D(275, 14, 53, 84));
        heroRunning.add(new Rectangle2D(350, 5, 61, 93));
        heroRunning.add(new Rectangle2D(428, 18, 72, 80));
        heroJumping.add(new Rectangle2D(25, 164, 46, 97));
        heroJumping.add(new Rectangle2D(96, 164, 62, 92));
        heroDying.add(new Rectangle2D(297, 497, 101, 64));

        heroShooting.add(new Rectangle2D(12, 340, 65, 84));
        heroShooting.add(new Rectangle2D(83, 330, 75, 94));
        heroShooting.add(new Rectangle2D(164, 342, 75, 82));
        heroShooting.add(new Rectangle2D(258, 340, 64, 84));
        heroShooting.add(new Rectangle2D(341, 331, 73, 93));
        heroShooting.add(new Rectangle2D(426, 344, 77, 80));

        heroJumpingShooting.add(new Rectangle2D(18, 494, 64, 97));
        heroJumpingShooting.add(new Rectangle2D(97, 493, 72, 92));

        heroRects.add(heroRunning);
        heroRects.add(heroJumping);
        heroRects.add(heroDying);
        heroRects.add(heroShooting);
        heroRects.add(heroJumpingShooting);

        /* FOES */
        ArrayList<Rectangle2D> foeRising = new ArrayList<>();
        ArrayList<Rectangle2D> foeStanding = new ArrayList<>();
        ArrayList<Rectangle2D> foeDying = new ArrayList<>();
        /*foeRising.add(new Rectangle2D(20, 101, 48, 20));
        foeRising.add(new Rectangle2D(140, 98, 48, 23));
        foeRising.add(new Rectangle2D(257, 90, 55, 32));
        foeRising.add(new Rectangle2D(376, 93, 65, 29));
        foeRising.add(new Rectangle2D(3, 215, 77, 27));
        foeRising.add(new Rectangle2D(116, 213, 81, 29));
        foeRising.add(new Rectangle2D(244, 221, 66, 21));
        foeRising.add(new Rectangle2D(361, 221, 71, 21));
        foeRising.add(new Rectangle2D(11, 330, 61, 31));
        foeRising.add(new Rectangle2D(128, 321, 71, 41));
        foeRising.add(new Rectangle2D(243, 313, 83, 49));
        foeRising.add(new Rectangle2D(360, 302, 89, 60));
        foeRising.add(new Rectangle2D(6, 409, 88, 72));
        foeRising.add(new Rectangle2D(122, 402, 92, 79));
        foeRising.add(new Rectangle2D(245, 403, 92, 78));
        foeRising.add(new Rectangle2D(347, 373, 120, 109));
        foeRising.add(new Rectangle2D(1, 490, 105, 111));
        foeRising.add(new Rectangle2D(121, 493, 117, 109));
        foeRising.add(new Rectangle2D(241, 506, 112, 96));*/
        foeRising.add(new Rectangle2D(20, 101, 48, 20));
        foeRising.add(new Rectangle2D(257, 90, 55, 32));
        foeRising.add(new Rectangle2D(3, 215, 77, 27));
        foeRising.add(new Rectangle2D(244, 221, 66, 21));
        foeRising.add(new Rectangle2D(11, 330, 61, 31));
        foeRising.add(new Rectangle2D(243, 313, 83, 49));
        foeRising.add(new Rectangle2D(6, 409, 88, 72));
        foeRising.add(new Rectangle2D(245, 403, 92, 78));
        foeRising.add(new Rectangle2D(1, 490, 105, 111));
        foeRising.add(new Rectangle2D(241, 506, 112, 96));

        foeStanding.add(new Rectangle2D(1, 627, 92, 94));
        foeStanding.add(new Rectangle2D(121, 629, 92, 92));
        foeStanding.add(new Rectangle2D(241, 630, 92, 91));
        foeStanding.add(new Rectangle2D(361, 629, 92, 92));

        foeDying.add(new Rectangle2D(111, 989, 101, 92));
        foeDying.add(new Rectangle2D(228, 995, 104, 86));
        foeDying.add(new Rectangle2D(351, 991, 101, 90));
        foeDying.add(new Rectangle2D(1, 1115, 91, 86));
        foeDying.add(new Rectangle2D(8, 1470, 81, 89));

        foeRects.add(foeRising);
        foeRects.add(foeStanding);
        foeRects.add(foeDying);

        ArrayList<Rectangle2D> flyingFoeFlying = new ArrayList<>();
        ArrayList<Rectangle2D> flyingFoeDying = new ArrayList<>();

        flyingFoeFlying.add(new Rectangle2D(22, 158, 92, 87));
        flyingFoeFlying.add(new Rectangle2D(260, 158, 95, 87));
        flyingFoeDying.add(new Rectangle2D(412, 136, 107, 132));

        flyingFoeRects.add(flyingFoeFlying);
        flyingFoeRects.add(flyingFoeDying);

        /* DUST TRAIL */

        dustTrailRects.add(new Rectangle2D(2, 2, 61, 49));
        dustTrailRects.add(new Rectangle2D(66, 2, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+64, 2, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+2*64, 2, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+3*64, 2, 61, 49));

        dustTrailRects.add(new Rectangle2D(2, 54, 61, 49));
        dustTrailRects.add(new Rectangle2D(66, 54, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+64, 54, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+2*64, 54, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+3*64, 54, 61, 49));

        dustTrailRects.add(new Rectangle2D(2, 54+52, 61, 49));
        dustTrailRects.add(new Rectangle2D(66, 54+52, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+64, 54+52, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+2*64, 54+52, 61, 49));
        dustTrailRects.add(new Rectangle2D(66+3*64, 54+52, 61, 49));
    }

    /*
    heros.jumpAndShoot1.rect = IntRect(18, 494, 64, 97);
    heros.jumpAndShoot2.rect = IntRect(97, 493, 72, 92);
    heros.pewPew.rect = IntRect(525, 368, 36, 25);*/
}
