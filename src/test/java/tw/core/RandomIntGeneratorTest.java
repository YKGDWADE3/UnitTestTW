package tw.core;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    private RandomIntGenerator mIntGenerator;

    @Before
    public void setIntGenerator() {
        mIntGenerator = new RandomIntGenerator();
    }

    @Mock
    private RandomIntGenerator mMockIntGenerator = Mockito.mock(RandomIntGenerator.class);


    @Test
    public void shouldGenerateSmallerThanMax() {
        String numberString1 = mIntGenerator.generateNums(10, 4);
        int maxNumber = Arrays.stream(numberString1.split(" ")).mapToInt(value -> Integer.parseInt(value)).max().getAsInt();
        Assert.assertTrue(maxNumber <= 10);

        String numberString2 = mIntGenerator.generateNums(100, 4);
        int maxNumber2 = Arrays.stream(numberString2.split(" ")).mapToInt(value -> Integer.parseInt(value)).max().getAsInt();
        Assert.assertTrue(maxNumber2 <= 100);
    }

    @Rule
    public ExpectedException mExpectedException =  ExpectedException.none();

    @Test
    public void shouldDigitMaxBiggerThanNeed() throws IllegalArgumentException {
        mExpectedException.expect(IllegalArgumentException.class);
        mExpectedException.expectMessage("Can't ask for more numbers than are available");
        mIntGenerator.generateNums(10, 11);

    }
}