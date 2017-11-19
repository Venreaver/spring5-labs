package lab.model;

import lombok.Data;

@Data
public class Customer implements Person {
    private String name;
    private boolean broke;

    public Customer(String name) {
        this.name = name;
    }
}
