package tw.core.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tw.core.exception.OutOfRangeAnswerException;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {
    private AnswerGenerator mAnswerGenerator;

    @Mock
    private RandomIntGenerator mRandomIntGenerator = Mockito.mock(RandomIntGenerator.class);

    @Before
    public void setUp() {
        mAnswerGenerator = new AnswerGenerator(mRandomIntGenerator);
    }


    @Test
    public void answerGeneratorTest() throws OutOfRangeAnswerException {
        Mockito.when(mRandomIntGenerator.generateNums(9,4)).thenReturn("1 3 5 7");
        Assert.assertEquals("1 3 5 7", mAnswerGenerator.generate().toString());
    }
}

