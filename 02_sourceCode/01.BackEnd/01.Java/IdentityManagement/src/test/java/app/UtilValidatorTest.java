package app;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import javax.validation.constraints.NotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.util.UtilValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilValidatorTest {

    @Autowired
    private UtilValidator target;

    public class TestModel {
        @NotNull
        private final String notNullStr;

        public TestModel(final String notNullStr) {
            this.notNullStr = notNullStr;
        }
    }

    /*
     * UtilValidator#hasConstraintViolations Tests.
     */
    @Test
    public void hasViolation() {
        final TestModel ngTestModel = new TestModel(null);

        assertThat("TestModel.notNullStr is NotNull",
                this.target.hasConstraintViolations(ngTestModel), is(true));
    }

    @Test
    public void hasNoViolation() {
        final TestModel okTestModel = new TestModel("OK");

        assertThat("TestModel.notNullStr is NotNull",
                this.target.hasConstraintViolations(okTestModel), is(false));
    }
}
