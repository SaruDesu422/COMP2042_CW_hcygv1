package model;

import java.io.BufferedReader;
import java.io.FileReader;

public class HighScore {

    private final String COMMA_DELIMITER = ",";
    String[] highscoreInfo;

    public HighScore() {
        read();
        for (int i = 0; i < highscoreInfo.length; i++) {
            System.out.printf("%d %s\n", i+1, highscoreInfo[i]);
        }
    }

    private void read() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/highscore.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                highscoreInfo = line.split(COMMA_DELIMITER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHighscoreInfo(String[] highscore) {

    }

    public String[] getHighscoreInfo() {
        return this.highscoreInfo;
    }
}