package cn.vt.util;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.mapping.IdGenerator;
import org.springframework.format.datetime.joda.LocalDateParser;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author vate
 */
public class SnowFlakeIdGenerator extends IdentityGenerator {

    private Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return snowflake.nextId() + RandomUtil.randomInt(2);
    }

    public static void main(String[] args) {
        Console.log(LocalDateTime.now());
        Console.log(LocalDateTime.now(ZoneId.of("UTC+8")));
        Console.log(LocalDateTime.now(ZoneId.of("UTC+7")));
        Console.log(LocalDateTime.now(ZoneId.of("UTC+6")));
        Console.log(LocalDateTime.now(ZoneId.of("UTC-12")));
        Console.log(LocalDateTime.now(ZoneId.of("UTC+12")));
        Console.log(LocalDateTime.now(ZoneId.of("UTC")));

        DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
    }
}
