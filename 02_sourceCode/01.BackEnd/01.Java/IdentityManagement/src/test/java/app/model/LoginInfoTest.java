package app.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoginInfoTest {
    private LoginInfo defTestModel;
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
    public void 事前準備に使用した値がtrueが返される() {
        assertThat(defTestModel.validParam(), is(true));
    }

    @Test
    public void userIdが15文字の場合trueが返される() {
        defTestModel.setUserId(DEF_TEST_TEXT_LENGHT_15);
        assertThat(defTestModel.validParam(), is(true));
    }

    @Test
    public void passwodが15文字の場合がtrue返される() {
        defTestModel.setPassword(DEF_TEST_TEXT_LENGHT_15);
        assertThat(defTestModel.validParam(), is(true));
    }

    @Test
    public void userIdが16文字の場合falseが返される() {
        defTestModel.setUserId(DEF_TEST_TEXT_LENGHT_16);
        assertThat(defTestModel.validParam(), is(false));
    }

    @Test
    public void passwordが16文字の場合falseが返される() {
        defTestModel.setPassword(DEF_TEST_TEXT_LENGHT_16);
        assertThat(defTestModel.validParam(), is(false));
    }

    @Test
    public void userIdが日本語の場合falseが返される() {
        defTestModel.setUserId(JAPANESE_TEXT);
        assertThat(defTestModel.validParam(), is(false));
    }

    @Test
    public void passwordが日本語の場合falseが返される() {
        defTestModel.setPassword(JAPANESE_TEXT);
        assertThat(defTestModel.validParam(), is(false));
    }

    @Test
    public void userIdがnullの場合falseが返される() {
        defTestModel.setUserId(null);
        assertThat(defTestModel.validParam(), is(false));
    }

    @Test
    public void passwordがnullの場合falseが返される() {
        defTestModel.setPassword(null);
        assertThat(defTestModel.validParam(), is(false));
    }

    @Test
    public void toString試験() {
        defTestModel.toString();
    }
}