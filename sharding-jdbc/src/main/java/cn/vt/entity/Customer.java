package cn.vt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(generator = "customSnowFlakeId")
  @GenericGenerator(name = "customSnowFlakeId",strategy = "cn.vt.util.SnowFlakeIdGenerator")
  private Long id;
  private String firstName;
  private String lastName;
  private Long birthday;
  private String addr;

  public Customer(String firstName,String lastName,Long birthday,String addr) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.addr = addr;
  }
}