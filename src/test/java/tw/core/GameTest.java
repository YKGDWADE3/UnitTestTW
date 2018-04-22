package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
    private Game game;
    private AnswerGenerator answerGenerator;
    private Answer inputAnswer;

    @Before
    public final void before() throws OutOfRangeAnswerException {
        Answer actualAnswer = new Answer();
        actualAnswer.setNumList(Arrays.asList("1","2","3","4"));
        answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        game = new Game(answerGenerator);
        inputAnswer = new Answer();
    }

    @Test
    public void should_guess_result_be_0A0B_when_inputNumbers_all_wrong() {
        inputAnswer.setNumList(Arrays.asList("5","6","7","8"));
        GuessResult guessResult = game.guess(inputAnswer);
        assertEquals("0A0B", guessResult.getResult());
        assertEquals(inputAnswer.toString(), guessResult.getInputAnswer().toString());
    }

    @Test
    public void should_guess_result_be_4A0B_when_inputNumbers_all_right() {
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        GuessResult guessResult = game.guess(inputAnswer);
        assertEquals("4A0B", guessResult.getResult());
        assertEquals(inputAnswer.toString(), guessResult.getInputAnswer().toString());
    }

    @Test
    public void should_guess_result_be_0A4B_when_inputNumbers_all_in_wrong_positions() {
        inputAnswer.setNumList(Arrays.asList("4","3","2","1"));
        GuessResult guessResult = game.guess(inputAnswer);
        assertEquals("0A4B", guessResult.getResult());
        assertEquals(inputAnswer.toString(), guessResult.getInputAnswer().toString());
    }

    @Test
    public void should_guess_result_be_2A1B_when_inputNumbers_3_right_numbers_but_1_in_wrong_positions() {
        inputAnswer.setNumList(Arrays.asList("1","2","5","3"));
        GuessResult guessResult = game.guess(inputAnswer);
        assertEquals("2A1B", guessResult.getResult());
        assertEquals(inputAnswer.toString(), guessResult.getInputAnswer().toString());
    }

    @Test
    public void should_checkStatus_be_SUCCESS_when_guess_is_correct() {
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        game.guess(inputAnswer);
        assertEquals("success", game.checkStatus());
    }

    @Test
    public void should_checkStatus_be_SUCCESS_when_guess_is_correct_at_6th_time() {
        for (int i = 0; i < 5; i++) {
            inputAnswer.setNumList(Arrays.asList("1","2","5","3"));
            game.guess(inputAnswer);
        }
        inputAnswer.setNumList(Arrays.asList("1","2","3","4"));
        game.guess(inputAnswer);
        assertEquals("success", game.checkStatus());
    }

    @Test
    public void should_checkStatus_be_CONTINUE_when_guess_is_not_correct_and_guessTimes_less_than_MAX_TIMES() {
        for (int i = 0; i < 5; i++) {
            inputAnswer.setNumList(Arrays.asList("1","2","5","3"));
            game.guess(inputAnswer);
        }
        assertEquals("continue", game.checkStatus());
    }

    @Test
    public void should_checkStatus_be_FAIL_when_guess_is_not_correct_and_guessTimes_equals_6() {
        for (int i = 0; i < 6; i++) {
            inputAnswer.setNumList(Arrays.asList("1","2","5","3"));
            game.guess(inputAnswer);
        }
        assertEquals("fail", game.checkStatus());
    }
}
