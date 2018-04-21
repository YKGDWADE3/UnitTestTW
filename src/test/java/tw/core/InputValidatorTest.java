package tw.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator mInputValidator;

    @Before
    public void setUp() {
        mInputValidator = new InputValidator();
    }

    @Test
    public void validateTest() {
        Assert.assertTrue(mInputValidator.validate("1 2 3 4"));
        Assert.assertTrue(mInputValidator.validate("0 2 3 4"));
        Assert.assertFalse(mInputValidator.validate("1 10 3 4"));
        Assert.assertFalse(mInputValidator.validate("1 %5 3 4"));
        Assert.assertFalse(mInputValidator.validate("1 1 3 4"));
        Assert.assertFalse(mInputValidator.validate("1  3 4"));
        Assert.assertFalse(mInputValidator.validate("1 3 4"));
    }

}
