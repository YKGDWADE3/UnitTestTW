package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}