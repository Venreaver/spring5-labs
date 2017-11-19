package lab.aop;

import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:ioc.xml")
public class AopAspectJTest {
    @Autowired
    private Bar bar;
    @Autowired
    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        bar.sellSquishee(customer);
    }

    @Test
    public void testBeforeAdvice() {
        assertThat(AopLog.getStringValue(), containsString("Hello"));
        assertThat(AopLog.getStringValue(), containsString("How are you doing?"));
    }

    @Test
    public void testAfterAdvice() {
        assertThat(AopLog.getStringValue(), containsString("Good Bye!"));
    }

    @Test
    public void testAfterReturningAdvice() {
        assertThat(AopLog.getStringValue(), containsString("Good Enough?"));
    }

    @Test
    public void testAroundAdvice() {
        assertThat(AopLog.getStringValue(), containsString("Hi!"));
        assertThat(AopLog.getStringValue(), containsString("See you!"));
    }

    @Test
    public void testAllAdvices() {
        assertThat(bar.getClass(), not(instanceOf(ApuBar.class)));
    }
}