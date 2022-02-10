package cn.vt.serializeable;

import cn.hutool.core.lang.Console;
import cn.vt.serializeable.Person.Address;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.io.*;

public class TestSerialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        final Address address = Address.builder()
//                .fuckingCity("fc")
//                .fuckingTown("ft")
//                .fuckingZone("fz")
//                .fuckingStreet("fs")
//                .build();
//        final Person person = Person.builder()
//                .fuckingAge(123)
//                .fuckingName("123")
//                .fuckingAddress(address)
//                .build();
//        try(
//             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./objectio.bi"));
//             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./objectio.bi"));
//        ){
//            oos.writeObject(person);
//            oos.flush();asdasd
//            final Object readObject = ois.readObject();
//        }aaasa
        exploreObjectInMemory();
    }

    public static void exploreObjectInMemory() {
        Console.log(VM.current().details());
        Person person = new Person("123");
        System.out.println(person.getFuckingName());
        System.out.println(System.identityHashCode(person));
        int i = 10_000_0000;
        Console.log(
                ClassLayout.parseInstance(person).toPrintable()
        );
        Person[] people = new Person[5];
        people[1] = new Person("123");
        people[2] = new Person("1234");
        people[3] = new Person("1235");
        Console.log(
                ClassLayout.parseInstance(people).toPrintable()
        );
    }
}
