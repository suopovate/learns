package mock;


import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Person implements Comparable<Person> {
    String name;
    Integer age;

    @Override
    public int compareTo(Person o) {
        return this.age.compareTo(o.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public String toString() {
        return age+"";
    }
}
