package ioc;

import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class HelloWorldTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "classpath:ioc.xml";

    private BeanFactory context =
            new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);

    @Test
    void testInitPerson() {
        Person person = context.getBean("person", Person.class);
        assertThat(person, is(getExpectedPerson()));
    }

    static Person getExpectedPerson() {
        List<String> contacts = new ArrayList<>();
        contacts.add("asd@asd.ru");
        contacts.add("+7-234-456-67-89");
        return new UsualPerson()
                .setAge(35)
                .setName("John Smith")
                .setCountry(new Country()
                        .setId(1)
                        .setName("Russia")
                        .setCodeName("RU")).setContacts(contacts);
    }
}
