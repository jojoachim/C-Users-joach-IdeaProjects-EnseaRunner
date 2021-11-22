package game.ensearunner;

import javafx.scene.Group;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import static java.lang.Integer.parseInt;

public class Record {
    protected static int bestScore;
    protected static Label bestScoreLabel;
    protected static Label currentRecordLabel;

    public Record(Group root) {

        String bestScoreStr;
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/best_score.txt"));
            bestScoreStr = in.readLine();
            bestScore = parseInt(bestScoreStr);
            bestScoreLabel = new Label("Best score : " + bestScoreStr);
            bestScoreLabel.setLayoutX(440);
            bestScoreLabel.setLayoutY(15);
            bestScoreLabel.setScaleX(2.5);
            bestScoreLabel.setScaleY(2.5);

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot load best score.");
        }

        currentRecordLabel = new Label();
        currentRecordLabel.setLayoutX(390);
        currentRecordLabel.setLayoutY(45);
        currentRecordLabel.setScaleX(2.5);
        currentRecordLabel.setScaleY(2.5);

        root.getChildren().add(bestScoreLabel);
        root.getChildren().add(currentRecordLabel);
    }

    public static void update() {
        currentRecordLabel.setText(String.valueOf((int) Camera.getX() / 50));
    }

    public static boolean newBestScore() {
        return ( parseInt(currentRecordLabel.getText()) > bestScore);
    }

    public static void writeNewBestScore() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/best_score.txt"));
            out.write(currentRecordLabel.getText());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
