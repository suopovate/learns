package cn.vt;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import cn.vt.dao.CustomerRepository;
import cn.vt.entity.Customer;
import cn.vt.mock.NameRandom;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author vate
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableTransactionManagement
@Slf4j
public class App {
    public static void main(String[] args) {
        new SpringApplication(App.class).run(args);
    }
    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            for (int i = 0; i < 1000; i++) {
                Customer save = repository.save(new Customer(NameRandom.getChineseName(), NameRandom.getChineseName(), RandomUtil.randomDay(-50, 50).getTime(), RandomUtil.randomString(10)));
                Console.log(save);
            }
            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

//            // fetch an individual customer by ID
//            Customer customer = repository.findById(1L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
        };
    }
}
