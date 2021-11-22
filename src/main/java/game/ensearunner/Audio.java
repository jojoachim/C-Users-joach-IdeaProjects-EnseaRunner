package game.ensearunner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {

    private static Clip clip;

    public Audio(String song) {

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(song));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { if(!clip.isActive()) {clip.start();} }
    public static void stop() {if(clip!=null) {clip.close(); }}

    public static void playSound(String song) {
        Audio a = new Audio(song);
        a.play();
    }
}
