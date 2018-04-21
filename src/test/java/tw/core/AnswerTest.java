package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    private Answer mAnswer;

    @Before
    public void setUp() {
        mAnswer = new Answer();
        mAnswer.setNumList(Arrays.asList("1", "2", "3", "4"));
    }

    @Test
    public void createAnswerTest() {
        Assert.assertEquals("1 4 78 34", Answer.createAnswer("1 4 78 34").toString());
        Assert.assertEquals("1 4 -1 34", Answer.createAnswer("1 4 -1 34").toString());
        Assert.assertEquals("1 & -1 34", Answer.createAnswer("1 & -1 34").toString());
    }

    @Rule
    public ExpectedException mExpectedException =  ExpectedException.none();

    @Test
    public void validateShouldThrowsExceptionWithRepeatDigit() throws OutOfRangeAnswerException {
        mExpectedException.expect(OutOfRangeAnswerException.class);
        mExpectedException.expectMessage("Answer format is incorrect");
        mAnswer.setNumList(Arrays.asList("1", "3", "3", "5"));
        mAnswer.validate();
    }

    @Test
    public void validateShouldThrowsExceptionWithDigitBiggerThan9() throws OutOfRangeAnswerException {
        mExpectedException.expect(OutOfRangeAnswerException.class);
        mExpectedException.expectMessage("Answer format is incorrect");
        mAnswer.setNumList(Arrays.asList("1", "10", "3", "5"));
        mAnswer.validate();
    }

    @Test
    public void validateShouldThrowsExceptionWithFormatWrong() throws OutOfRangeAnswerException {
        mExpectedException.expect(NumberFormatException.class);
        mAnswer.setNumList(Arrays.asList("1", "*7", "3", "5"));
        mAnswer.validate();
    }

    @Test
    public void shouldGet0A0BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("5 6 7 8")).getValue();
        Assert.assertEquals(0, record[0]);
        Assert.assertEquals(0, record[1]);
    }

    @Test
    public void shouldGet0A1BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("3 6 7 8")).getValue();
        Assert.assertEquals(0, record[0]);
        Assert.assertEquals(1, record[1]);
    }

    @Test
    public void shouldGet0A2BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("3 6 1 8")).getValue();
        Assert.assertEquals(0, record[0]);
        Assert.assertEquals(2, record[1]);
    }

    @Test
    public void shouldGet0A3BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("2 3 1 8")).getValue();
        Assert.assertEquals(0, record[0]);
        Assert.assertEquals(3, record[1]);
    }

    @Test
    public void shouldGet0A4BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("4 3 2 1")).getValue();
        Assert.assertEquals(0, record[0]);
        Assert.assertEquals(4, record[1]);
    }

    @Test
    public void shouldGet1A0BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("1 6 7 8")).getValue();
        Assert.assertEquals(1, record[0]);
        Assert.assertEquals(0, record[1]);
    }

    @Test
    public void shouldGet1A1BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("1 6 2 8")).getValue();
        Assert.assertEquals(1, record[0]);
        Assert.assertEquals(1, record[1]);
    }

    @Test
    public void shouldGet1A2BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("1 3 2 8")).getValue();
        Assert.assertEquals(1, record[0]);
        Assert.assertEquals(2, record[1]);
    }

    @Test
    public void shouldGet1A3BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("1 4 2 3")).getValue();
        Assert.assertEquals(1, record[0]);
        Assert.assertEquals(3, record[1]);
    }

    @Test
    public void shouldGet4A0BRecord() {
        int[] record = mAnswer.check(Answer.createAnswer("1 2 3 4")).getValue();
        Assert.assertEquals(4, record[0]);
        Assert.assertEquals(0, record[1]);
    }
}