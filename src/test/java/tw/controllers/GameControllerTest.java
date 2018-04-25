package tw.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import static org.mockito.Mockito.*;


/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private AnswerGenerator answerGenerator;
    private InputCommand inputGuess;
    private Answer answer;
    private Answer answer2;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public final void before() throws OutOfRangeAnswerException {
        Answer actualAnswer = new Answer();
        actualAnswer.setNumList(Arrays.asList("1","2","3","4"));
        answerGenerator = mock(AnswerGenerator.class);
        inputGuess = mock(InputCommand.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        gameController = new GameController(new Game(answerGenerator),new GameView());
        System.setOut(new PrintStream(outContent));
        answer = new Answer();
        answer2= new Answer();
    }

    private String systemOut() { return outContent.toString(); }

    @Test
    public void should_print_beginMsg_when_beginGame() throws IOException {
        gameController.beginGame();
        Assert.assertTrue(systemOut().startsWith("------Guess Number Game, You have 6 chances to guess!  ------"));
    }

    @Test
    public void should_print_GuessHistory_and_fail_when_guess_is_all_wrong() throws IOException {
        answer.setNumList(Arrays.asList("5","6","7","8"));
        when(inputGuess.input()).thenReturn(answer);
        gameController.play(inputGuess);
        Assert.assertTrue(systemOut().contains(
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "Game Status: fail"));
        verify(inputGuess, times(6)).input();
    }
    @Test
    public void should_print_GuessHistory_and_success_when_guess_is_right_at_second_time() throws IOException {
        answer.setNumList(Arrays.asList("5","6","7","8"));
        answer2.setNumList(Arrays.asList("1","2","3","4"));
        when(inputGuess.input()).thenReturn(answer).thenReturn(answer2);
        gameController.play(inputGuess);
        Assert.assertTrue(systemOut().contains(
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                        "Game Status: success"));
        verify(inputGuess, times(2)).input();
    }

    @Test
    public void should_print_GuessHistory_and_success_when_guess_is_right_at_last_time() throws IOException {
        answer.setNumList(Arrays.asList("5","6","7","8"));
        answer2.setNumList(Arrays.asList("1","2","3","4"));
        when(inputGuess.input()).thenReturn(answer).thenReturn(answer).thenReturn(answer).thenReturn(answer).thenReturn(answer).thenReturn(answer2);
        gameController.play(inputGuess);
        Assert.assertTrue(systemOut().contains(
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 5 6 7 8, Guess Result: 0A0B]\n" +
                        "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                        "Game Status: success"));
        verify(inputGuess, times(6)).input();
    }

    @Test
    public void should_print_GuessHistory_and_fail_when_guess_3_right_numbers_but_2_in_wrong_positions() throws IOException {
        answer.setNumList(Arrays.asList("1","3","7","2"));
        when(inputGuess.input()).thenReturn(answer);
        gameController.play(inputGuess);
        Assert.assertTrue(systemOut().contains(
                        "[Guess Numbers: 1 3 7 2, Guess Result: 1A2B]\n" +
                        "[Guess Numbers: 1 3 7 2, Guess Result: 1A2B]\n" +
                        "[Guess Numbers: 1 3 7 2, Guess Result: 1A2B]\n" +
                        "[Guess Numbers: 1 3 7 2, Guess Result: 1A2B]\n" +
                        "[Guess Numbers: 1 3 7 2, Guess Result: 1A2B]\n" +
                        "[Guess Numbers: 1 3 7 2, Guess Result: 1A2B]\n" +
                        "Game Status: fail"));
        verify(inputGuess, times(6)).input();
    }

    @Test
    public void should_print_GuessHistory_and_success_when_guess_is_correct() throws IOException {
        answer.setNumList(Arrays.asList("1","2","3","4"));
        when(inputGuess.input()).thenReturn(answer);
        gameController.play(inputGuess);
        Assert.assertTrue(systemOut().contains(
                        "[Guess Numbers: 1 2 3 4, Guess Result: 4A0B]\n" +
                        "Game Status: success"));
        verify(inputGuess, times(1)).input();
    }
}