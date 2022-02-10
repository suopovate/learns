package cn.vt.serializeable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Person extends Papa implements Serializable {

    public String fuckingName;
    int fuckingAge;
    Address fuckingAddress;

    public Person(String fuckingName) {
        super();
        super.fuckingName = fuckingName;
        this.fuckingName = fuckingName;
    }

    @Builder
    @Data
    public static class Address implements Serializable {
        String fuckingCity;
        String fuckingTown;
        String fuckingZone;
        String fuckingStreet;
    }
}
