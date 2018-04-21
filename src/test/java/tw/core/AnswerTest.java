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
        mAnswer.setNumList(Arrays.asList(new String[]{"1", "3", "3", "5"}));
        mAnswer.validate();
    }
}