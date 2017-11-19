package lab.aop;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import lab.aop.AopLog;
import lab.model.Bar;
import lab.model.Customer;
import lab.model.CustomerBrokenException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ExtendWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")

public class AopAspectJExceptionTest {

	@Autowired
	private Bar bar;
    
	@Autowired
    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        
//        customer.setBroke(true);
    }

    @Test(expected=CustomerBrokenException.class)
    public void testAfterThrowingAdvice() {
 
    	bar.sellSquishee(customer);
    	
        assertTrue("Customer is not broken ", AopLog.getStringValue().contains("Hmmm..."));
        System.out.println(AopLog.getStringValue());
    }
}