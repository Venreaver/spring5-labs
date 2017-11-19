package lab.ioc;

import lab.model.Person;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:ioc.xml")
class SpringTCFAppTest {
    @Autowired
    private Person person;

    @Test
    void testInitPerson() {
        MatcherAssert.assertThat(person, Is.is(HelloWorldTest.getExpectedPerson()));
    }
}
