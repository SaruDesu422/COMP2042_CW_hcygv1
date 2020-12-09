package test;

import view.*;

import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

/**
 * Unit test for certain methods in the app.
 * 
 */
public class AppTest extends ApplicationTest {

    @Test
    public void updateScoreSheetTest() {
        int score, scoreTest;
        List<String[]> highScoreTest = new ArrayList<>();
        for (int i = 0; i < ScoreBoard.MAXLEVEL; i++) {
            String[] emptyLine = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
            highScoreTest.add(emptyLine);
        }
        for (int i = 0; i < ScoreBoard.MAXLEVEL; i++) {
            score = (int)(Math.random() * 1000) + 500;
            highScoreTest.set(i, ScoreBoard.updateData(score, highScoreTest.get(i)));
            scoreTest = Integer.valueOf(highScoreTest.get(i)[0]);
            assertEquals(score, scoreTest);
        }
    }
}