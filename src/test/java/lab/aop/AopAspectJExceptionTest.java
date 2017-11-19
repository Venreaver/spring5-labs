package lab.aop;

import lab.model.Bar;
import lab.model.Customer;
import lab.model.CustomerBrokenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:ioc.xml")
public class AopAspectJExceptionTest {
    @Autowired
    private Bar bar;
    @Autowired
    private Customer brokenCustomer;

    @Test
    public void testAfterThrowingAdvice() {
        assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(brokenCustomer));
        assertThat(AopLog.getStringValue(), containsString("Hmmm..."));
    }
}