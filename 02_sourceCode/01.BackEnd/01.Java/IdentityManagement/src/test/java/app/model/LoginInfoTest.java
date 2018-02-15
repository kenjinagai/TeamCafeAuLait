package app.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.util.BeanValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginInfoTest {
    private LoginInfo defTestModel;

    @Autowired
    private BeanValidator validator;

    private static final String DEF_TEST_USER_ID = "test";
    private static final String DEF_TEST_PASSWORD = "test";
    private static final String DEF_TEST_TEXT_LENGHT_15 = "abcdefghijklmno";
    private static final String DEF_TEST_TEXT_LENGHT_16 = "abcdefghijklmnop";
    private static final String JAPANESE_TEXT = "試験";

    @Before
    public void 事前準備() {
        defTestModel = new LoginInfo();
        defTestModel.setUserId(DEF_TEST_USER_ID);
        defTestModel.setPassword(DEF_TEST_PASSWORD);
    }

    @Test
    public void 事前準備に使用した値がfalseが返される() {
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(false));
    }

    @Test
    public void userIdが15文字の場合falseが返される() {
        defTestModel.setUserId(DEF_TEST_TEXT_LENGHT_15);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(false));
    }

    @Test
    public void passwodが15文字の場合がfalseが返される() {
        defTestModel.setPassword(DEF_TEST_TEXT_LENGHT_15);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(false));
    }

    @Test
    public void userIdが16文字の場合true返される() {
        defTestModel.setUserId(DEF_TEST_TEXT_LENGHT_16);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(true));
    }

    @Test
    public void passwordが16文字の場合trueが返される() {
        defTestModel.setPassword(DEF_TEST_TEXT_LENGHT_16);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(true));
    }

    @Test
    public void userIdが日本語の場合trueが返される() {
        defTestModel.setUserId(JAPANESE_TEXT);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(true));
    }

    @Test
    public void passwordが日本語の場合trueが返される() {
        defTestModel.setPassword(JAPANESE_TEXT);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(true));
    }

    @Test
    public void userIdがnullの場合trueが返される() {
        defTestModel.setUserId(null);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(true));
    }

    @Test
    public void passwordがnullの場合trueが返される() {
        defTestModel.setPassword(null);
        assertThat(validator.hasConstraintViolations(this.defTestModel), is(true));
    }

}