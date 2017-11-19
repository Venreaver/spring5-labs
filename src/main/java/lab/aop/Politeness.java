package lab.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import lab.model.Customer;
import lab.model.Squishee;

@Aspect
public class Politeness {
    @Pointcut("execution(* sellSquishee(..))")
    public void sellingMethod() {}

    @Before("sellingMethod()")
    public void sayHello(JoinPoint joinPiont) {
        AopLog.append("Hello " + ((Customer) joinPiont.getArgs()[0]).getName() + ". How are you doing? \n");
    }

    @AfterReturning(pointcut = "sellingMethod()", returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        AopLog.append("Is " + ((Squishee) retVal).getName() + " Good Enough? \n");
    }

    @AfterThrowing("sellingMethod()")
    public void sayYouAreNotAllowed() {
        AopLog.append("Hmmm... \n");
    }

    @After("sellingMethod()")
    public void sayGoodBye() {
        AopLog.append("Good Bye! \n");
    }

    @Around("sellingMethod()")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        AopLog.append("Hi! \n");
        Object retVal = pjp.proceed();
        AopLog.append("See you! \n");
        return retVal;
    }
}